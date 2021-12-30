package com.sparrows.geometry.geometry3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.sparrows.geometry.exception.CoplanarPoints;
import com.sparrows.geometry.exception.GeometryException;
import com.sparrows.geometry.exception.InvalidFrustum;
import com.sparrows.geometry.exception.InvalidRegularPolyhedronIndex;
import com.sparrows.geometry.exception.NotEnoughFaces;
import com.sparrows.geometry.exception.NotEnoughVertices;
import com.sparrows.geometry.exception.PolygonNotPlanar;
import com.sparrows.geometry.exception.ZeroVectorException;
import com.sparrows.geometry.maths.Maths;
import com.sparrows.geometry.transformation.Translation3;
import com.sparrows.geometry.utils.GeometryUtils;

public abstract class StandardPolyhedra {
    public static final Polyhedron tetrahedron;
    public static final Polyhedron cube;
    public static final Polyhedron octahedron;
    public static final Polyhedron dodecahedron;
    public static final Polyhedron icosahedron;
    public static final Polyhedron smallStellatedDodecahedron;
    public static final Polyhedron greatStellatedDodecahedron;
    public static final Polyhedron greatDodecahedron;
    public static final Polyhedron greatIcosahedron;

    static {
        Polyhedron h = null;
        try {
            h = StandardPolyhedra.regular(1);
        } catch (InvalidRegularPolyhedronIndex e) {
            //
        }
        tetrahedron = h;
        try {
            h = StandardPolyhedra.regular(2);
        } catch (InvalidRegularPolyhedronIndex e) {
            //
        }
        cube = h;
        try {
            h = StandardPolyhedra.regular(3);
        } catch (InvalidRegularPolyhedronIndex e) {
            //
        }
        octahedron = h;
        try {
            h = StandardPolyhedra.regular(4);
        } catch (InvalidRegularPolyhedronIndex e) {
            //
        }
        dodecahedron = h;
        try {
            h = StandardPolyhedra.regular(5);
        } catch (InvalidRegularPolyhedronIndex e) {
            //
        }
        icosahedron = h;
        try {
            h = StandardPolyhedra.regular(6);
        } catch (InvalidRegularPolyhedronIndex e) {
            //
        }
        smallStellatedDodecahedron = h;
        try {
            h = StandardPolyhedra.regular(7);
        } catch (InvalidRegularPolyhedronIndex e) {
            //
        }
        greatStellatedDodecahedron = h;
        try {
            h = StandardPolyhedra.regular(8);
        } catch (InvalidRegularPolyhedronIndex e) {
            //
        }
        greatDodecahedron = h;
        try {
            h = StandardPolyhedra.regular(9);
        } catch (InvalidRegularPolyhedronIndex e) {
            //
        }
        greatIcosahedron = h;
    }

    public static Polyhedron tetrahedron(Point3 a, Point3 b, Point3 c, Point3 d) throws CoplanarPoints {
        try {
            return new Polyhedron (
                    new Polygon3(a,b,c),
                    new Polygon3(b,a,d),
                    new Polygon3(c,b,d),
                    new Polygon3(a,c,d)
            );
        } catch (NotEnoughFaces e) {
            throw new CoplanarPoints();
        }
    }

    public static Polyhedron uniformPrism(int sides) throws GeometryException {
        return uniformPrism(sides, 1);
    }

    public static Polyhedron uniformPrism(int sides,int density) throws GeometryException {
        GeometryUtils.validateSidesDensity(sides, density);
        var face1 = Polygon3.regular(sides, density);
        var translation = new Translation3(Vector3.zUnit);
        return prism(face1,translation);
    }

    public static Polyhedron uniformAntiprism(int sides) throws GeometryException {
        return uniformAntiprism(sides,1);
    }

    public static Polyhedron uniformAntiprism(int sides,int density) throws GeometryException {
        GeometryUtils.validateSidesDensity(sides, density);
        var face1 = Polygon3.regular(sides, density);
        double height = Math.sqrt(1-1./4/ Maths.square(Math.cos(Maths.PI2*density/sides)));
        Polygon3 face2 = face1.translate(Vector3.zUnit.multiply(height)).rotate(Line3.zAxis,Math.PI*density/sides);
        List<Polygon3> faces = new ArrayList<>();
        faces.add(face1);
        for (var i = 0; i < sides; i++) {
            faces.add(new Polygon3(
                    face1.getVertex((i+1)%sides),
                    face1.getVertex(i),
                    face2.getVertex(i)));
            faces.add(new Polygon3(
                    face1.getVertex(i),
                    face2.getVertex((i+sides-1)%sides),
                    face2.getVertex(i)));
        }
        faces.add(face2.reverse());
        return new Polyhedron(faces);
    }

    public static Polyhedron twistedPrism(int sides, double twist) throws GeometryException {
        return twistedPrism(sides,1,twist);
    }

    public static Polyhedron twistedPrism(int sides,int density, double twist) throws GeometryException {
        GeometryUtils.validateSidesDensity(sides, density);
        var face1 = Polygon3.regular(sides, density);
        double height = 1;
        Polygon3 face2 = face1.translate(Vector3.zUnit.multiply(height)).rotate(Line3.zAxis,twist);
        List<Polygon3> faces = new ArrayList<>();
        faces.add(face1);
        for (var i = 0; i < sides; i++) {
            faces.add(new Polygon3(
                    face1.getVertex((i+1)%sides),
                    face1.getVertex(i),
                    face2.getVertex(i)));
            faces.add(new Polygon3(
                    face1.getVertex(i),
                    face2.getVertex((i+sides-1)%sides),
                    face2.getVertex(i)));
        }
        faces.add(face2.reverse());
        return new Polyhedron(faces);
    }

    public static Polyhedron regular(int index) throws InvalidRegularPolyhedronIndex {
        switch (index) {
            case 1:
                return regular(3,1, Math.acos(1./3));
            case 2:
                return regular(4,1, Maths.PI2);
            case 3:
                return regular(3,1, Math.acos(-1./3));
            case 4:
                return regular(5,1, Math.acos(-Maths.SQRT5/5));
            case 5:
                return regular(3,1, Math.acos(-Maths.SQRT5/3));
            case 6:
                return regular(5,2, Math.acos(-Maths.SQRT5/5));
            case 7:
                return regular(5,2, Math.acos(Maths.SQRT5/5));
            case 8:
                return regular(5,1, Math.acos(Maths.SQRT5/5));
            case 9:
                return regular(3,1, Math.acos(Maths.SQRT5/3));
            default:
                throw new InvalidRegularPolyhedronIndex();
        }
    }

    private static Polyhedron regular(int faceSides, int faceDensity, double dihedralAngle) {
        List<Polygon3> faces = new ArrayList<>();
        try {
            faces.add(Polygon3.regular(faceSides, faceDensity));
            for (var f = 0; f < faces.size(); f++) {
                for (var side = 0; side < faceSides; side++) {
                    Line3 axis = faces.get(f).sideLine(side);
                    Polygon3 nextFace = faces.get(f).rotate(axis, -dihedralAngle).reverse();
                    var skip = false;
                    for (Polygon3 face : faces) {
                        if (nextFace.identical(face)) {
                            skip = true;
                            break;
                        }
                    }
                    if (!skip) {
                        faces.add(nextFace);
                    }
                }
            }
            return new Polyhedron(faces);
        } catch (GeometryException e) {
            return null;
        }
    }

    public static Polyhedron prism(Polygon3 face, Translation3 translation) throws CoplanarPoints, PolygonNotPlanar {
        if (face.plane().contains(face.getVertex(0).translate(translation))) {
            throw new CoplanarPoints();
        }
        List<Polygon3> faceList = new ArrayList<>();
        faceList.add(face);
        Polygon3 face2 = face.translate(translation);
        faceList.add(face2.reverse());
        for (var i = 0; i < face.vertexCount(); i++) {
            faceList.add(new Polygon3(face.getVertex((i+1)%face.vertexCount()),face.getVertex(i),face2.getVertex(i),face2.getVertex((i+1)%face.vertexCount())));
        }
        try {
            return new Polyhedron(faceList);
        } catch (NotEnoughFaces e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Polyhedron pyramid(Polygon3 base, Point3 apex) throws PolygonNotPlanar, CoplanarPoints {
        if (base.plane().contains(apex)) {
            throw new CoplanarPoints();
        }
        List<Polygon3> faceList = new ArrayList<>();
        faceList.add(base);
        for (var i = 0; i < base.vertexCount(); i++) {
            faceList.add(new Polygon3(base.getVertex((i+1)%base.vertexCount()),base.getVertex(i),apex));
        }
        try {
            return new Polyhedron(faceList);
        } catch (NotEnoughFaces e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Polyhedron frustum(Polygon3 base, Polygon3 top) throws InvalidFrustum {
        if (base.vertexCount() != top.vertexCount()) {
            throw new InvalidFrustum();
        }
        List<Polygon3> faceList = new ArrayList<>();
        faceList.add(base);
        faceList.add(top.reverse());
        for (var i = 0; i < base.vertexCount(); i++) {
            faceList.add(new Polygon3(base.getVertex((i+1)%base.vertexCount()),base.getVertex(i),top.getVertex(i),top.getVertex((i+1)%top.vertexCount())));
        }
        try {
            return new Polyhedron(faceList);
        } catch (NotEnoughFaces e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Polyhedron twistedFrustum(Polygon3 base, Polygon3 top) throws InvalidFrustum {
        if (base.vertexCount() != top.vertexCount()) {
            throw new InvalidFrustum();
        }
        List<Polygon3> faceList = new ArrayList<>();
        faceList.add(base);
        faceList.add(top.reverse());
        for (var i = 0; i < base.vertexCount(); i++) {
            faceList.add(new Polygon3(
                    base.getVertex((i + 1) % base.vertexCount()),
                    base.getVertex(i),
                    top.getVertex(i)));
            faceList.add(new Polygon3(
                    base.getVertex(i),
                    top.getVertex((i + base.vertexCount() - 1) % base.vertexCount()),
                    top.getVertex(i)));
        }
        try {
            return new Polyhedron(faceList);
        } catch (NotEnoughFaces e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Polyhedron pyritohedron(double h) {
        try {
            Polygon3 face1 = new Polygon3(
                    new Point3(-(1 - h * h), -(1 + h), 0),
                    new Point3(+(1 - h * h), -(1 + h), 0),
                    new Point3(+1, -1, +1),
                    new Point3(0, -(1 - h * h), +(1 + h)),
                    new Point3(-1, -1, +1));
            Polygon3 face2 = face1.reflect(Plane3.z0).reverse();
            Polygon3 face3 = face1.reflect(Plane3.y0).reverse();
            Polygon3 face4 = face2.reflect(Plane3.y0).reverse();
            Polygon3 face5 = face1.rotate(Line3.zAxis, Maths.PI2).rotate(Line3.xAxis, Maths.PI2);
            Polygon3 face6 = face2.rotate(Line3.zAxis, Maths.PI2).rotate(Line3.xAxis, Maths.PI2);
            Polygon3 face7 = face3.rotate(Line3.zAxis, Maths.PI2).rotate(Line3.xAxis, Maths.PI2);
            Polygon3 face8 = face4.rotate(Line3.zAxis, Maths.PI2).rotate(Line3.xAxis, Maths.PI2);
            Polygon3 face9 = face1.rotate(Line3.xAxis, Maths.PI2).rotate(Line3.zAxis, Maths.PI2);
            Polygon3 face10 = face2.rotate(Line3.xAxis, Maths.PI2).rotate(Line3.zAxis, Maths.PI2);
            Polygon3 face11 = face3.rotate(Line3.xAxis, Maths.PI2).rotate(Line3.zAxis, Maths.PI2);
            Polygon3 face12 = face4.rotate(Line3.xAxis, Maths.PI2).rotate(Line3.zAxis, Maths.PI2);

            return new Polyhedron(Arrays.asList(face1, face2, face3, face4, face5, face6, face7, face8, face9, face10, face11, face12));
        } catch (NotEnoughFaces e) {
            return null;
        }
    }

    public static Polyhedron snub(Polyhedron h, double expansion, double twist) {
        Point3 centre = h.centroid();

        // move faces out and twist
        var newFaces = h.getFaces().stream().map(f -> {
            try {
                return f.translate(new Vector3(f.centroid(),centre)).rotate(new Line3(centre,f.centroid()),twist);
            } catch (ZeroVectorException e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList());

        // create new faces from old vertices
        for (var oldVertexNo = 0; oldVertexNo < h.vertexFaces().size(); oldVertexNo++) {
            List<Point3> vertexList = new ArrayList<>();
            var faces = h.vertexFaces().get(oldVertexNo);
            var vertices = h.vertexVertices().get(oldVertexNo);
            for (var f = 0; f < faces.size(); f++) {
                vertexList.add(new Point3(newFaces.get(faces.get(f)).getVertex(vertices.get(f))));
            }
            newFaces.add(new Polygon3(vertexList));
        }

        // create snub triangles from old edges
        for (var oldEdgeNo = 0; oldEdgeNo < h.edgeFaces().size(); oldEdgeNo++) {
            var faces = h.edgeFaces().get(oldEdgeNo);
            var sides = h.edgeSides().get(oldEdgeNo);
            LineSegment3 side1 = newFaces.get(faces.get(0)).side(sides.get(0));
            LineSegment3 side2 = newFaces.get(faces.get(1)).side(sides.get(1));
            newFaces.add(new Polygon3(side1.getPoint1(), side2.getPoint1(), side2.getPoint2()));
            newFaces.add(new Polygon3(side1.getPoint1(), side2.getPoint1(), side1.getPoint2()));
        }

        try {
            return new Polyhedron(newFaces);
        } catch (NotEnoughFaces notEnoughFaces) {
            return null;
        }
    }
}
