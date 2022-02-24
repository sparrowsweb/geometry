package com.sparrows.geometry.geometry3;

import com.sparrows.geometry.Polygon;
import com.sparrows.geometry.exception.GeometryException;
import com.sparrows.geometry.exception.IdenticalVertices;
import com.sparrows.geometry.exception.PolygonNotPlanar;
import com.sparrows.geometry.exception.ZeroExternalAngle;
import com.sparrows.geometry.exception.ZeroVectorException;
import com.sparrows.geometry.geometry2.Polygon2;
import com.sparrows.geometry.maths.Maths;
import com.sparrows.geometry.spherical.SphericalPolygon;
import com.sparrows.geometry.transformation.d3.AffineTransformation3;
import com.sparrows.geometry.transformation.d3.LinearTransformation3;
import com.sparrows.geometry.transformation.Translation3;
import com.sparrows.geometry.utils.GeometryUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Polygon3 extends Polygon implements GeometryObject3<Polygon3> {
    public static final Polygon3 triangle = regular(3, 1);
    public static final Polygon3 square = regular(4, 1);
    public static final Polygon3 pentagon = regular(5, 1);
    public static final Polygon3 pentagram = regular(5,2);
    public static final Polygon3 hexagon = regular(6, 1);
    public static final Polygon3 octagon = regular(8,1);
    public static final Polygon3 octagram = regular(8,3);
    public static final Polygon3 decagon = regular(10,1);
    public static final Polygon3 decagram = regular(10,3);

    private final List<Point3> vertices;

    // Constructors
    public Polygon3(List<Point3> vertices) {
        if (vertices.size() < 3) {
            throw new IllegalArgumentException("A polygon must have at least three vertices.");
        }
        this.vertices = vertices;
    }
    public Polygon3(Point3...vertices) {
        this(Arrays.asList(vertices));
    }
    public Polygon3(Polygon3 g) {
        this.vertices = g.vertices.stream().map(Point3::new).collect(Collectors.toList());
    }
    public Polygon3(SphericalPolygon g) {
        this.vertices = g.getVertices().stream().map(Point3::new).collect(Collectors.toList());
    }

    // Getters and Setters
    public List<Point3> getVertices() {
        return vertices;
    }

    public Point3 getVertex(int v) {
        return vertices.get(v);
    }

    public int vertexCount() {
        return vertices.size();
    }

    public Vector3 sideVector(int i) {
        if (i == vertexCount()-1) {
            return new Vector3(vertices.get(i), vertices.get(0));
        } else {
            return new Vector3(vertices.get(i), vertices.get(i + 1));
        }
    }

    public LineSegment3 side(int i) {
        return new LineSegment3(vertices.get(i),vertices.get((i+1)%vertexCount()));
    }

    public Line3 sideLine(int i) throws ZeroVectorException {
        if (i == vertexCount()-1) {
            return new Line3(vertices.get(i), vertices.get(0));
        } else {
            return new Line3(vertices.get(i), vertices.get(i + 1));
        }
    }

    // Comparison
    @Override
    public boolean identical(Polygon3 o) {
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

    public boolean opposite(Polygon3 o) {
        return identical(o.reverse());
    }

    public boolean identicalOrOpposite(Polygon3 o) {
        return identical(o) || opposite(o);
    }

    public void validate() {
        // check vertices distinct
        for (var i = 0; i < vertexCount(); i++) {
            if (vertices.get(i).identical(vertices.get((i+1)%vertexCount()))) {
                throw new IdenticalVertices();
            }
        }
        // check angles not 180 degrees
        for (var v = 0; v < vertexCount(); v++) {
            if (sideVector((v +vertexCount() - 1) % vertexCount()).parallel(sideVector(v))) {
                throw new ZeroExternalAngle(v);
            }
        }
        // check planar
        if (vertexCount() > 3) {
            Plane3 plane = null;
            plane = new Plane3(vertices.get(0),vertices.get(1),vertices.get(2));
            for (var v = 3; v < vertexCount(); v++) {
                if (!plane.contains(vertices.get(v))) {
                    throw new PolygonNotPlanar();
                }
            }
        }

    }

    public Plane3 plane() {
        return new Plane3(vertices.get(0),vertices.get(1),vertices.get(2));
    }

    public Polygon3 reverse() {
        ArrayList<Point3> newVertices = new ArrayList<>(vertices);
        Collections.reverse(newVertices);
        return new Polygon3(newVertices);
    }

    public Point3 centroid() {
        return Point3.centroid(vertices);
    }

    public double angle(Polygon3 g) throws PolygonNotPlanar {
        return plane().angle(g.plane());
    }

    @Override
    public Polygon3 linearTransform(LinearTransformation3 t) {
        return new Polygon3(vertices.stream().map(p -> p.linearTransform(t)).collect(Collectors.toList()));
    }

    @Override
    public Polygon3 affineTransform(AffineTransformation3 a) {
        return new Polygon3(vertices.stream().map(p -> p.affineTransform(a)).collect(Collectors.toList()));
    }

    @Override
    public Polygon3 translate(Translation3 t) {
        return new Polygon3(vertices.stream().map(p -> p.translate(t)).collect(Collectors.toList()));
    }

    private Boolean isEquilateral;
    public boolean isEquilateral() {
        if (isEquilateral == null) {
            double edgeLength = vertices.get(vertexCount() - 1).distance(vertices.get(0));
            for (var v = 0; v < vertexCount() - 1; v++) {
                if (!Maths.equal(edgeLength, vertices.get(v).distance(vertices.get(v + 1)))) {
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
                vertexAngles.add(side((u + vertexCount() - 1) % vertexCount()).angle(side(u)));
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

    public static double regularInradius(int sides, int density) {
        return .5/Math.tan(Math.PI*density/sides);
    }
    public static double regularCircumradius(int sides, int density) {
        return .5/Math.sin(Math.PI*density/sides);
    }

    public Polygon2 project2D() {
        return new Polygon2(vertices.stream().map(Point3::project2D).collect(Collectors.toList()));
    }

    public static Polygon3 regular(int sides) {
        return regular(sides,1);
    }
    public static Polygon3 regular(int sides, int density) {
        GeometryUtils.validateSidesDensity(sides, density);
        var v0 = new Point3(-.5,-inradius(sides, density),0);
        double angle = 2*Math.PI*density/sides;
        List<Point3> vertices = new ArrayList<>();
        vertices.add(v0);
        for (var v = 1; v < sides; v++) {
            try {
                vertices.add(v0.rotateOrigin(Vector3.Z_UNIT, v * angle));
            } catch (GeometryException e) {
                //
            }
        }
        return new Polygon3(vertices);
    }

    public String objString() {
        String s = "";
        for (Point3 vertex : this.vertices) {
            s += "v " + vertex.getX() + " " + vertex.getY() + " " + vertex.getZ() + "\r\n";
        }
        s += "f ";
        for (int v = 0; v < this.vertices.size(); v++) {
            s += " " + (v + 1);
        }
        s += "\r\n";
        return s;
    }

}
