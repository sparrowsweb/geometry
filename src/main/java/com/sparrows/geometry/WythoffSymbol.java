package com.sparrows.geometry;

import com.sparrows.geometry.exception.AntipodalPoints;
import com.sparrows.geometry.exception.CannotFindFermatPoint;
import com.sparrows.geometry.exception.DoesNotTileSphere;
import com.sparrows.geometry.exception.GeometryException;
import com.sparrows.geometry.exception.GreatCircleContainsArc;
import com.sparrows.geometry.exception.IdenticalOrOppositeGreatCircles;
import com.sparrows.geometry.exception.IdenticalSphericalPoints;
import com.sparrows.geometry.exception.InvalidSchwarzTriangle;
import com.sparrows.geometry.exception.InvalidSphericalPoint;
import com.sparrows.geometry.exception.InvalidWythoffSymbol;
import com.sparrows.geometry.exception.NotEnoughSphericalFaces;
import com.sparrows.geometry.exception.NotEnoughSphericalVertices;
import com.sparrows.geometry.exception.PointArc;
import com.sparrows.geometry.exception.ZeroVectorException;
import com.sparrows.geometry.maths.Rational;
import com.sparrows.geometry.maths.exceptions.InvalidRational;
import com.sparrows.geometry.maths.exceptions.ZeroDenominator;
import com.sparrows.geometry.spherical.Arc;
import com.sparrows.geometry.spherical.GreatCircle;
import com.sparrows.geometry.spherical.SchwarzTriangle;
import com.sparrows.geometry.spherical.SphericalPoint;
import com.sparrows.geometry.spherical.SphericalPolygon;
import com.sparrows.geometry.spherical.SphericalPolyhedron;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WythoffSymbol {
    public enum Type {
        ONE,
        TWO,
        THREE,
        SNUB
    }

    private final SchwarzTriangle schwarzTriangle;
    private final Type type;

    public WythoffSymbol(SchwarzTriangle schwarzTriangle, Type type) {
        this.schwarzTriangle = schwarzTriangle;
        this.type = type;
    }

    public WythoffSymbol(String s) throws InvalidWythoffSymbol, ZeroDenominator, InvalidRational, InvalidSchwarzTriangle, DoesNotTileSphere {
        if (s.matches("\\| *[0-9/]+ +[0-9/]+ +[0-9/]+")) {
            type = Type.SNUB;
        } else if (s.matches("[0-9/]+ *\\| *[0-9/]+ +[0-9/]+")) {
            type = Type.ONE;
        } else if (s.matches("[0-9/]+ +[0-9/]+ *\\| *[0-9/]+")) {
            type = Type.TWO;
        } else if (s.matches("[0-9/]+ *[0-9/]+ +[0-9/]+ *\\|")) {
            type = Type.THREE;
        } else {
            throw new InvalidWythoffSymbol();
        }
        List<String> elements = Arrays.stream(s.split("[| ]")).filter(x -> !x.isEmpty()).collect(Collectors.toList());
        if (elements.size() != 3) {
            throw new InvalidWythoffSymbol();
        }
        schwarzTriangle = new SchwarzTriangle(Rational.parseRational(elements.get(0)),Rational.parseRational(elements.get(1)),Rational.parseRational(elements.get(2)));
    }

    public Type getType() {
        return type;
    }

    public SchwarzTriangle getSchwarzTriangle() {
        return schwarzTriangle;
    }

    public int vertexCount() throws ZeroDenominator {
        switch (type) {
            case SNUB:
                return schwarzTriangle.getSymmetryGroupOrder() / 2;
            case ONE:
                return schwarzTriangle.getSymmetryGroupOrder() / (2 * (int) schwarzTriangle.getP().getNumerator());
            case TWO:
                if (schwarzTriangle.getP().add(schwarzTriangle.getQ()).equals(Rational.ONE) && schwarzTriangle.getR().lessThan(new Rational(schwarzTriangle.getSymmetryGroupOrder(), 8L))) {
                    return schwarzTriangle.getSymmetryGroupOrder() / 4;
                } else {
                    return schwarzTriangle.getSymmetryGroupOrder() / 2;
                }
            case THREE:
                return schwarzTriangle.getSymmetryGroupOrder();
            default:
                return 0;
        }
    }

    List<Rational> vertexCycle = new ArrayList<>();
    int vertexDensity;

    private void findVertexCycle() {
        vertexCycle = new ArrayList<>();
        switch (type) {
            case ONE:
                // p | q r -> (q.r)^p
                for (var i = 1; i <= schwarzTriangle.getP().getNumerator(); i++) {
                    if (!schwarzTriangle.getQ().equals(Rational.TWO)) {
                        vertexCycle.add(schwarzTriangle.getQ());
                    }
                    if (!schwarzTriangle.getR().equals(Rational.TWO)) {
                        vertexCycle.add(schwarzTriangle.getR());
                    }
                }
                vertexDensity = (int) schwarzTriangle.getR().getDenominator();
        }
    }

    public SphericalPolyhedron uniformPolyhedron() throws GeometryException {
        SphericalPolygon triangle = schwarzTriangle.triangle();

        SphericalPoint p = triangle.getVertex(0);
        SphericalPoint q = triangle.getVertex(1);
        SphericalPoint r = triangle.getVertex(2);

        GreatCircle opq = null;
        GreatCircle oqr = null;
        GreatCircle orp = null;
        try {
            opq = new GreatCircle(p, q);
            oqr = new GreatCircle(q, r);
            orp = new GreatCircle(r, p);
        } catch (AntipodalPoints | IdenticalSphericalPoints e) {
            e.printStackTrace();
        }

        SphericalPoint startVertex = null;
        if (type == Type.ONE) {
            startVertex = p;
        } else if (type == Type.TWO) {
            try {
                startVertex = wythoffMidpoint(p, q, r);
            } catch (GeometryException e) {
                // can't happen
                e.printStackTrace();
            }
        } else if (type == Type.THREE) {
            try {
                startVertex = SphericalPoint.incentre(p, q, r);
            } catch (GeometryException e) {
                // can't happen
                e.printStackTrace();
            }
        } else {
            try {
                startVertex = SphericalPoint.fermatPoint(p,q,r);
            } catch (GeometryException e) {
                throw new GeometryException("cannot find Fermat point");
            }
        }

        List<SphericalPolygon> faceList = new ArrayList<>();

        if (type == Type.ONE) {
            SphericalPoint reflectionInQr = startVertex.reflect(oqr);
            Arc lineThroughQr = null;
            try {
                lineThroughQr = new Arc(startVertex, reflectionInQr, true);
            } catch (AntipodalPoints | PointArc e) {
                e.printStackTrace();
            }

            try {
                SphericalPolygon newFace = polygonFromEdgesAndRotation(Collections.singletonList(lineThroughQr), q, schwarzTriangle.getQ());
                if (newFace.vertexCount() >= 3) {
                    faceList.add(newFace);
                }
            } catch (NotEnoughSphericalVertices e) {
                // ignore digons
            }

            try {
                SphericalPolygon newFace = polygonFromEdgesAndRotation(Collections.singletonList(lineThroughQr), r, schwarzTriangle.getR()).reverse();
                if (newFace.vertexCount() >= 3) {
                    faceList.add(newFace);
                }
            } catch (NotEnoughSphericalVertices e) {
                // ignore digons
            }
        } else if (type == Type.TWO) {
            SphericalPoint reflectionInPr = startVertex.reflect(orp);
            Arc lineThroughPr = null;
            try {
                lineThroughPr = new Arc(startVertex, reflectionInPr, true);
            } catch (AntipodalPoints | PointArc e) {
                e.printStackTrace();
            }

            try {
                SphericalPolygon newFace = polygonFromEdgesAndRotation(Collections.singletonList(lineThroughPr), p, schwarzTriangle.getP());
                if (newFace.vertexCount() >= 3) {
                    faceList.add(newFace);
                }
            } catch (NotEnoughSphericalVertices e) {
                // ignore digons
            }

            SphericalPoint reflectionInQr = startVertex.reflect(oqr);
            Arc lineThroughQr = null;
            try {
                lineThroughQr = new Arc(startVertex, reflectionInQr, true);
            } catch (AntipodalPoints | PointArc e) {
                e.printStackTrace();
            }

            try {
                SphericalPolygon newFace = polygonFromEdgesAndRotation(Collections.singletonList(lineThroughQr), q, schwarzTriangle.getQ()).reverse();
                if (newFace.vertexCount() >= 3) {
                    faceList.add(newFace);
                }
            } catch (NotEnoughSphericalVertices e) {
                // ignore digons
            }

            try {
                SphericalPolygon newFace = polygonFromEdgesAndRotation(Arrays.asList(lineThroughPr, lineThroughQr), r, schwarzTriangle.getR()).reverse();
                if (newFace.vertexCount() >= 3) {
                    faceList.add(newFace);
                }
            } catch (NotEnoughSphericalVertices e) {
                // ignore digons
            }
        }
        else if (type == Type.THREE) {
            SphericalPoint reflectionInPq = startVertex.reflect(opq);
            SphericalPoint reflectionInQr = startVertex.reflect(oqr);
            SphericalPoint reflectionInRp = startVertex.reflect(orp);
            Arc lineThroughPq = null;
            Arc lineThroughQr = null;
            Arc lineThroughRp = null;
            try {
                lineThroughPq = new Arc(startVertex, reflectionInPq, true);
                lineThroughQr = new Arc(startVertex, reflectionInQr, true);
                lineThroughRp = new Arc(startVertex, reflectionInRp, true);
            } catch (AntipodalPoints | PointArc e) {
                e.printStackTrace();
            }

            try {
                SphericalPolygon newFace;
                int evenDenominatorCount = (schwarzTriangle.getP().getDenominator() % 2 == 0 ? 1 : 0) +
                        (schwarzTriangle.getQ().getDenominator() % 2 == 0 ? 1 : 0) +
                        (schwarzTriangle.getR().getDenominator() % 2 == 0 ? 1 : 0);
                if (evenDenominatorCount != 1 || schwarzTriangle.getP().getDenominator() % 2 == 1) {
                    newFace = polygonFromEdgesAndRotation(Arrays.asList(lineThroughPq, lineThroughRp), p, schwarzTriangle.getP());
                    if (newFace.vertexCount() >= 3) {
                        faceList.add(newFace);
                    }
                }
                if (evenDenominatorCount != 1 || schwarzTriangle.getQ().getDenominator() % 2 == 1) {
                    newFace = polygonFromEdgesAndRotation(Arrays.asList(lineThroughQr, lineThroughPq), q, schwarzTriangle.getQ());
                    if (newFace.vertexCount() >= 3) {
                        faceList.add(newFace);
                    }
                }

                if (evenDenominatorCount != 1 || schwarzTriangle.getR().getDenominator() % 2 == 1) {
                    newFace = polygonFromEdgesAndRotation(Arrays.asList(lineThroughRp, lineThroughQr), r, schwarzTriangle.getR());
                    if (newFace.vertexCount() >= 3) {
                        faceList.add(newFace);
                    }
                }
            } catch (NotEnoughSphericalVertices e) {
                e.printStackTrace();
            }
        } else {
            // snub
            SphericalPoint reflectionInPq = startVertex.reflect(opq);
            SphericalPoint reflectionInQr = startVertex.reflect(oqr);
            SphericalPoint reflectionInRp = startVertex.reflect(orp);
            Arc lineThroughPq = null;
            Arc lineThroughQr = null;
            Arc lineThroughRp = null;
            try {
                lineThroughPq = new Arc(startVertex, reflectionInPq, true);
                lineThroughQr = new Arc(startVertex, reflectionInQr, true);
                lineThroughRp = new Arc(startVertex, reflectionInRp, true);
            } catch (AntipodalPoints | PointArc e) {
                e.printStackTrace();
            }

            SphericalPolygon snubTriangle = new SphericalPolygon(reflectionInPq,reflectionInQr,reflectionInRp);
            faceList.add(snubTriangle);

            if (schwarzTriangle.getP().getNumerator() != 2) {
                faceList.add(polygonFromEdgesAndRotation(Arrays.asList(new Arc(reflectionInPq, reflectionInRp, true)), p, schwarzTriangle.getP()));
            }
            if (schwarzTriangle.getQ().getNumerator() != 2) {
                faceList.add(polygonFromEdgesAndRotation(Arrays.asList(new Arc(reflectionInQr, reflectionInPq, true)), q, schwarzTriangle.getQ()));
            }
            if (schwarzTriangle.getR().getNumerator() != 2) {
                faceList.add(polygonFromEdgesAndRotation(Arrays.asList(new Arc(reflectionInRp, reflectionInQr, true)), r, schwarzTriangle.getR()));
            }

        }

        if (type == Type.SNUB) {
            for (var f = 0; f < faceList.size(); f++) {
                addToFaceList(faceList, faceList.get(f).rotate(p,2*Math.PI/schwarzTriangle.getP().toDouble()));
                addToFaceList(faceList, faceList.get(f).rotate(q,2*Math.PI/schwarzTriangle.getQ().toDouble()));
                addToFaceList(faceList, faceList.get(f).rotate(r,2*Math.PI/schwarzTriangle.getR().toDouble()));
            }
        } else {
            // reflect faces in all mirrors
            for (var f = 0; f < faceList.size(); f++) {
                addToFaceList(faceList, faceList.get(f).reflect(opq).reverse());
                addToFaceList(faceList, faceList.get(f).reflect(oqr).reverse());
                addToFaceList(faceList, faceList.get(f).reflect(orp).reverse());
            }
        }

        try {
            return new SphericalPolyhedron(faceList);
        } catch (NotEnoughSphericalFaces notEnoughFaces) {
            return null;
        }
    }

    // generate a spherical polygon by rotating arcs about a point
    private SphericalPolygon polygonFromEdgesAndRotation(List<Arc> startEdges, SphericalPoint axis, Rational angle) throws NotEnoughSphericalVertices {
        List<Arc> edgeList = new ArrayList<>(startEdges);
        for (var i = 1; i < angle.getNumerator(); i++) {
            for (var e : startEdges) {
                edgeList.add(e.rotate(axis, Math.PI * 2 * i / angle.toDouble()));
            }
        }

        return polygonFromLineSegments(edgeList);
    }

    private SphericalPolygon polygonFromLineSegments(List<Arc> faceEdgeList) throws NotEnoughSphericalVertices {
        SphericalPoint start = faceEdgeList.get(0).getStartPoint();
        var used = new boolean[faceEdgeList.size()];
        List<SphericalPoint> vertexList = new ArrayList<>();
        vertexList.add(start);
        used[0] = true;
        SphericalPoint nextVertex = faceEdgeList.get(0).getEndPoint();
        vertexList.add(nextVertex);

        for (;;) {
            // look for segment starting at nextVertex
            for (var i = 0; i < faceEdgeList.size(); i++) {
                if (!used[i]) {
                    if (faceEdgeList.get(i).getStartPoint().identical(nextVertex)) {
                        used[i] = true;
                        nextVertex = faceEdgeList.get(i).getEndPoint();
                        break;
                    } else if (faceEdgeList.get(i).getEndPoint().identical(nextVertex)) {
                        used[i] = true;
                        nextVertex = faceEdgeList.get(i).getStartPoint();
                        break;
                    }
                }
            }
            if (nextVertex.identical(start)) {
                // finished
                break;
            }
            vertexList.add(nextVertex);
        }

        return new SphericalPolygon(vertexList);
    }

    private void addToFaceList(List<SphericalPolygon> faceList, SphericalPolygon face) {
        if (faceList.stream().filter(x -> x.identicalOrOpposite(face)).findFirst().isEmpty()) {
            faceList.add(face);
        }
    }

    // Given a triangle PQR, returns the point X on PQ such that RX bisects angle PRQ
    private static SphericalPoint wythoffMidpoint (SphericalPoint p, SphericalPoint q, SphericalPoint r) throws PointArc, AntipodalPoints, IdenticalSphericalPoints, GreatCircleContainsArc {
        var bisectingGreatCircle = r.bisectingGreatCircle(p,q);
        var intersections = bisectingGreatCircle.intersections(new Arc(p,q,true));
        return intersections.get(0);
    }

    @Override
    public String toString() {
        if (type == Type.ONE) {
            return schwarzTriangle.getP() + "|" + schwarzTriangle.getQ() + " " + schwarzTriangle.getR();
        } else if (type == Type.TWO) {
            return schwarzTriangle.getP() + " " + schwarzTriangle.getQ() + "|" + schwarzTriangle.getR();
        } else if (type == Type.THREE) {
            return schwarzTriangle.getP() + " " + schwarzTriangle.getQ() + " " + schwarzTriangle.getR() + "|";
        } else {
            return "|" + schwarzTriangle.getP() + " " + schwarzTriangle.getQ() + " " + schwarzTriangle.getR();
        }
    }
}
