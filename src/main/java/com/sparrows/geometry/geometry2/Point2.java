package com.sparrows.geometry.geometry2;

import com.sparrows.geometry.transformation.AffineTransformation2;
import com.sparrows.geometry.transformation.LinearTransformation2;
import com.sparrows.geometry.transformation.Translation2;
import com.sparrows.geometry.maths.Maths;
import org.ejml.simple.SimpleMatrix;

import java.util.Objects;

public class Point2 implements GeometryObject2<Point2> {
    public static final Point2 origin = new Point2(0,0);

    private double x;
    private double y;

    // Constructors
    public Point2(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Point2() {
        this(0,0);
    }
    public Point2(Point2 p) {
        this(p.x, p.y);
    }
    public Point2(SimpleMatrix matrix) {
        this(matrix.get(0,0),matrix.get(1,0));
    }

    // Getters and Setters
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point2 point3 = (Point2) o;
        return Double.compare(point3.x, x) == 0 && Double.compare(point3.y, y) == 0;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    public boolean identical(Point2 p) {
        return Maths.equal(x, p.x) && Maths.equal(y, p.y);
    }

    // Properties
    public SimpleMatrix matrix() {
        return new SimpleMatrix(new double[][] {{this.x},{this.y}});
    }
    public SimpleMatrix augmentedMatrix() {
        return new SimpleMatrix(new double[][] {{this.x},{this.y},{1}});
    }

    // Two Points
    public double distanceSquared (Point2 p) {
        return Maths.square(p.x - x) + Maths.square(p.y - y);
    }
    public double distance (Point2 p) {
        return Math.sqrt(distanceSquared(p));
    }
    public Point2 bisect(Point2 p) {
        return new Point2((x+p.x)/2, (y+p.y)/2);
    }

    // Transformations
    @Override
    public Point2 linearTransform(LinearTransformation2 t) {
        return new Point2(t.getMatrix().mult(matrix()));
    }

    @Override
    public Point2 affineTransform(AffineTransformation2 t) {
        return new Point2(t.getMatrix().mult(augmentedMatrix()));
    }

    @Override
    public Point2 translate(Translation2 t) {
        return new Point2(t.getMatrix().plus(matrix()));
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
