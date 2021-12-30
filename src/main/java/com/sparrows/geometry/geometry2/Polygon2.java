package com.sparrows.geometry.geometry2;

import com.sparrows.geometry.Polygon;
import com.sparrows.geometry.exception.GeometryException;
import com.sparrows.geometry.exception.IdenticalVertices;
import com.sparrows.geometry.exception.NotEnoughVertices;
import com.sparrows.geometry.exception.ParallelLinesException;
import com.sparrows.geometry.exception.ZeroExternalAngle;
import com.sparrows.geometry.exception.ZeroVectorException;
import com.sparrows.geometry.transformation.AffineTransformation2;
import com.sparrows.geometry.transformation.LinearTransformation2;
import com.sparrows.geometry.transformation.Translation2;
import com.sparrows.geometry.maths.Maths;
import com.sparrows.geometry.utils.GeometryUtils;
import com.sparrows.geometry.maths.Range;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Polygon2 extends Polygon implements GeometryObject2<Polygon2> {
    public static final Polygon2 triangle;
    public static final Polygon2 square;
    public static final Polygon2 pentagon;
    public static final Polygon2 pentagram;
    public static final Polygon2 hexagon;
    public static final Polygon2 octagon;
    public static final Polygon2 octagram;
    public static final Polygon2 decagon;
    public static final Polygon2 decagram;

    static {
        triangle = regularNoException(3, 1);
        square = regularNoException(4, 1);
        pentagon = regularNoException(5, 1);
        pentagram = regularNoException(5,2);
        hexagon = regularNoException(6, 1);
        octagon = regularNoException(8,1);
        octagram = regularNoException(8,3);
        decagon = regularNoException(10,1);
        decagram = regularNoException(10,3);
    }

    private final List<Point2> vertices;

    // Constructors
    public Polygon2(List<Point2> vertices) {
        if (vertices.size() < 3) {
            throw new IllegalArgumentException("A polygon must have at least three vertices.");
        }
        this.vertices = vertices;
    }
    public Polygon2(Point2...vertices) {
        this(Arrays.asList(vertices));
    }
    public Polygon2(Polygon2 g) {
        this.vertices = g.vertices.stream().map(Point2::new).collect(Collectors.toList());
    }

    // Getters and Setters
    public List<Point2> getVertices() {
        return vertices;
    }

    public Point2 getVertex(int v) {
        return vertices.get(v);
    }

    public int vertexCount() {
        return vertices.size();
    }

    public Vector2 sideVector(int i) {
        if (i == vertexCount()-1) {
            return new Vector2(vertices.get(i), vertices.get(0));
        } else {
            return new Vector2(vertices.get(i), vertices.get(i + 1));
        }
    }

    public Line2 sideLine(int i) throws ZeroVectorException {
        if (i == vertexCount()-1) {
            return new Line2(vertices.get(i), vertices.get(0));
        } else {
            return new Line2(vertices.get(i), vertices.get(i + 1));
        }
    }

    // Comparison
    @Override
    public boolean identical(Polygon2 o) {
        if (vertexCount() != o.vertexCount()) {
            return false;
        }
        for (var shift = 0; shift < vertexCount(); shift++) {
            boolean same = true;
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

    public void validate() throws IdenticalVertices, ZeroExternalAngle {
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
    }

    // assumes p on l
    public static double tValue(Line2 l, Point2 p) {
        return new Vector2(l.getPoint(),p).dot(l.getVector());
    }

    // -1 left 1 right 0 on line
    public static int sideOfLine(Line2 l, Point2 p) {
        var pointVector = new Vector2(l.getPoint(),p);
        double cross = l.getVector().getY()*pointVector.getX() - l.getVector().getX()*pointVector.getY();
        return Maths.sign(cross);
    }
/*
    public class Range {
        private double d1;
        private double d2;
        public Range(double d1, double d2) {
            this.d1 = d1;
            this.d2 = d2;
        }

        public double getD1() {
            return d1;
        }

        public double getD2() {
            return d2;
        }
    }
*/
    private class Crossing {
        private double t;
        private int direction;
        public Crossing(double t, int direction) {
            this.t = t;
            this.direction = direction;
        }

        public double getT() {
            return t;
        }

        public int getDirection() {
            return direction;
        }
    }

    public List<Range> intersection(Line2 l) throws ZeroVectorException, ParallelLinesException {
        List<Crossing> crossings = new ArrayList<>();
        int lastSide = sideOfLine(l,getVertex(vertexCount()-1));
        for (var v = 0; v < vertexCount(); v++) {
            int side = sideOfLine(l,getVertex(v));
            if (side != lastSide) {
                lastSide = side;
                Line2 s = sideLine((v-1)%vertexCount());
                Point2 i = s.intersection(l);
                double t = tValue(l,i);
                System.out.println("side "+v+" crosses direction " + (side==1?"l->r":"r->l") + " intersect at " + i + " t = " + t);
                crossings.add(new Crossing(t,side));
            }
        }
        crossings.sort(Comparator.comparing(Crossing::getT));

        List<Range> ranges = new ArrayList<>();
        int density = 0;
        int oldDensity = 0;
        double start = 0;
        for (var crossing : crossings) {
            System.out.println("t = "+crossing.t+" direction = "+crossing.direction);
            density += crossing.getDirection();
            if (density == 0) {
                ranges.add(new Range(start, crossing.t));
                System.out.println("range " + start + " to " + crossing.t);
            }
            if (oldDensity == 0) {
                start = crossing.getT();
            }
            oldDensity = density;
        }

        return ranges;
    }

    @Override
    public Polygon2 linearTransform(LinearTransformation2 t) {
        return new Polygon2(vertices.stream().map(p -> p.linearTransform(t)).collect(Collectors.toList()));
    }

    @Override
    public Polygon2 affineTransform(AffineTransformation2 a) {
        return new Polygon2(vertices.stream().map(p -> p.affineTransform(a)).collect(Collectors.toList()));
    }

    @Override
    public Polygon2 translate(Translation2 t) {
        return new Polygon2(vertices.stream().map(p -> p.translate(t)).collect(Collectors.toList()));
    }

    public static Polygon2 regular(int sides, int density) throws GeometryException {
        GeometryUtils.validateSidesDensity(sides, density);
        var v0 = new Point2(-.5,-inradius(sides, density));
        double angle = 2*Math.PI*density/sides;
        List<Point2> vertices = new ArrayList<>();
        vertices.add(v0);
        for (var v = 1; v < sides; v++) {
            vertices.add(v0.rotateOrigin(v*angle));
        }
        return new Polygon2(vertices);
    }

    private static Polygon2 regularNoException(int sides, int density) {
        try {
            return regular(sides,density);
        } catch (GeometryException e) {
            return null; // assume won't happen
        }
    }
}
