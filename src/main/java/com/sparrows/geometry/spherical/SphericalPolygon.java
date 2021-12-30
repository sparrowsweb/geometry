package com.sparrows.geometry.spherical;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.sparrows.geometry.Polygon;
import com.sparrows.geometry.exception.AntipodalPoints;
import com.sparrows.geometry.exception.IdenticalVertices;
import com.sparrows.geometry.exception.InvalidSphericalPoint;
import com.sparrows.geometry.exception.InvalidVertexAngle;
import com.sparrows.geometry.exception.InvalidVertexAngles;
import com.sparrows.geometry.exception.NotEnoughSphericalVertices;
import com.sparrows.geometry.exception.PointArc;
import com.sparrows.geometry.maths.Maths;
import com.sparrows.geometry.transformation.SphericalTransformation;

/**
 * <p>A spherical polygon. This is defined by its vertices in order. Sides are assumed to be minor arcs.</p>
 */
public class SphericalPolygon extends Polygon implements SphericalObject<SphericalPolygon> {

    private final List<SphericalPoint> vertices;

    // Constructors
    public SphericalPolygon(List<SphericalPoint> vertices) throws NotEnoughSphericalVertices {
        if (vertices.size() < 2) {
            throw new NotEnoughSphericalVertices();
        }
        this.vertices = vertices;
    }
    public SphericalPolygon(SphericalPoint...vertices) throws NotEnoughSphericalVertices {
        this(Arrays.asList(vertices));
    }
    public SphericalPolygon(SphericalPolygon g) {
        this.vertices = g.vertices.stream().map(SphericalPoint::new).collect(Collectors.toList());
    }

    // Getters and Setters
    public List<SphericalPoint> getVertices() {
        return vertices;
    }

    public SphericalPoint getVertex(int v) {
        return vertices.get(v);
    }

    public int vertexCount() {
        return vertices.size();
    }

    public Arc side(int i) {
        try {
            return new Arc(vertices.get(i),vertices.get((i+1)%vertexCount()),true);
        } catch (AntipodalPoints | PointArc e) {
            e.printStackTrace();
            return null; // TBD handle half-circles
        }
    }

    // Comparison
    @Override
    public boolean identical(SphericalPolygon o) {
        if (vertexCount() != o.vertexCount()) {
            return false;
        }
        for (var shift = 0; shift < vertexCount(); shift++) {
            var same = true;
            for (var i = 0; i < vertexCount(); i++) {
                if (!vertices.get(i).identical(o.getVertex((i+shift)%vertexCount()))) {
                    same = false;
                    break;
                }
            }
            if (same) {
                return true;
            }
        }
        return false;
    }

    public boolean opposite(SphericalPolygon o) {
        return identical(o.reverse());
    }

    public boolean identicalOrOpposite(SphericalPolygon o) {
        return identical(o) || opposite(o);
    }

    public void validate() throws IdenticalVertices {
        // check vertices distinct
        for (var i = 0; i < vertexCount(); i++) {
            if (vertices.get(i).identical(vertices.get((i+1)%vertexCount()))) {
                throw new IdenticalVertices();
            }
        }/*TBD
        // check angles not 180 degrees
        for (var v = 0; v < vertexCount(); v++) {
            if (sideVector((v +vertexCount() - 1) % vertexCount()).parallel(sideVector(v))) {
                throw new ZeroExternalAngle(v);
            }
        }*/

    }

    public SphericalPolygon reverse() {
        ArrayList<SphericalPoint> newVertices = new ArrayList<>(vertices);
        Collections.reverse(newVertices);
        try {
            return new SphericalPolygon(newVertices);
        } catch (NotEnoughSphericalVertices e) {
            return null; // cannot happen
        }
    }

    private Boolean isEquilateral;
    public boolean isEquilateral() {
        if (isEquilateral == null) {
            double edgeLength = vertices.get(vertexCount() - 1).minorDistance(vertices.get(0));
            for (var v = 0; v < vertexCount() - 1; v++) {
                if (!Maths.equal(edgeLength, vertices.get(v).minorDistance(vertices.get(v + 1)))) {
                    isEquilateral = false;
                    break;
                }
            }
            if (isEquilateral == null) {
                isEquilateral = true;
            }
        }
        return isEquilateral;
    }

    private List<Double> vertexAngles;
    public double vertexAngle(int v) {
        if (vertexAngles == null) {
            vertexAngles = new ArrayList<>();
            for (var u = 0; u < vertexCount(); u++) {
                try {
                    vertexAngles.add(side((u + vertexCount() - 1) % vertexCount()).angle(side(u).reverse()));
                } catch (PointArc e) {
                    e.printStackTrace();
                }
            }
        }
        return vertexAngles.get(v);
    }

    private Boolean isEquiangular;
    public boolean isEquiangular() {
        if (isEquiangular == null) {
            double vertexAngle = vertexAngle(0);
            for (var v = 1; v < vertexCount(); v++) {
                if (!Maths.equal(vertexAngle, vertexAngle(v))) {
                    isEquiangular = false;
                    break;
                }
            }
            if (isEquiangular == null) {
                isEquiangular = true;
            }
        }
        return isEquiangular;
    }
    public boolean isRegular() {
        return isEquilateral() && isEquiangular();
    }

    /**
     * <p>Create a spherical triangle with the specified vertex angles. Vertex A will be at the north pole,
     * vertex B on the Greenwich meridian, and vertex C such that polygon ABC is anticlockwise. All arc will
     * be minor arcs.</p>
     * @param angleA the angle at vertex A
     * @param angleB the angle at vertex B
     * @param angleC the angle at vertex C
     * @return a spherical triangle with the specified vertex angles
     * @throws InvalidVertexAngle if a vertex angle is less than or equal to 0 or greater than or equal to π
     * @throws InvalidVertexAngles if sum of vertex angles is less than or equal to π or greater than or equal to 3π,
     * or the sum of any two angles exceeds the other angle by π or more
     */
    public static SphericalPolygon triangle(double angleA, double angleB, double angleC) throws InvalidVertexAngle, InvalidVertexAngles {
        if (Maths.lessThanOrEqual(angleA,0) || Maths.greaterThanOrEqual(angleA,Math.PI) ||
                Maths.lessThanOrEqual(angleB,0) || Maths.greaterThanOrEqual(angleB,Math.PI) ||
                Maths.lessThanOrEqual(angleC,0) || Maths.greaterThanOrEqual(angleC,Math.PI)) {
            throw new InvalidVertexAngle();
        }
        if (Maths.lessThanOrEqual(angleA+angleB+angleC,Math.PI) || Maths.greaterThanOrEqual(angleA+angleB+angleC,3*Math.PI)) {
            throw new InvalidVertexAngles();
        }
        if (Maths.greaterThanOrEqual(angleA + angleB, angleC + Math.PI) ||
                Maths.greaterThanOrEqual(angleB + angleC, angleA + Math.PI) ||
                Maths.greaterThanOrEqual(angleC + angleA, angleB + Math.PI)) {
            throw new InvalidVertexAngles();
        }
        try {
            SphericalPoint vertexA = SphericalPoint.northPole;
            double distanceAB = Maths.sphericalTriangleSideFromAngles(angleC,angleA,angleB);
            SphericalPoint vertexB = vertexA.rotate(new SphericalPoint(1,0,0),distanceAB);
            double distanceAC = Maths.sphericalTriangleSideFromAngles(angleB,angleC,angleA);
            SphericalPoint vertexC = vertexA.rotate(new SphericalPoint(1,0,0),distanceAC).rotate(SphericalPoint.northPole,angleA);
            return new SphericalPolygon(vertexA,vertexB,vertexC);
        } catch (InvalidSphericalPoint | NotEnoughSphericalVertices e) {
            // can't happen
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public SphericalPolygon sphericalTransform(SphericalTransformation t) {
        try {
            return new SphericalPolygon(vertices.stream().map(p -> p.sphericalTransform(t)).collect(Collectors.toList()));
        } catch (NotEnoughSphericalVertices notEnoughVertices) {
            return null;
        }
    }

}
