package com.sparrows.geometry.geometry3;

import com.sparrows.geometry.exception.GeometryException;
import com.sparrows.geometry.exception.ParallelLinesException;
import com.sparrows.geometry.exception.SkewLines;
import com.sparrows.geometry.transformation.d3.AffineTransformation3;
import com.sparrows.geometry.transformation.d3.LinearTransformation3;
import com.sparrows.geometry.transformation.Translation3;
import com.sparrows.geometry.maths.Maths;

import java.util.Objects;

public class Line3 implements GeometryObject3<Line3> {

    public static final Line3 xAxis = new Line3(Point3.origin, Vector3.X_UNIT);
    public static final Line3 yAxis = new Line3(Point3.origin, Vector3.Y_UNIT);
    public static final Line3 zAxis = new Line3(Point3.origin, Vector3.Z_UNIT);

    private Point3 point;
    private Vector3 vector;

    // Constructors
    public Line3(Point3 point, Vector3 vector) throws IllegalArgumentException {
        this.point = point;
        this.vector = vector;
        normalise();
    }
    public Line3(Point3 point1, Point3 point2) throws IllegalArgumentException {
        this(point1, new Vector3(point1,point2));
    }
    private void normalise() throws IllegalArgumentException {
        vector = vector.unit();
        // choose point closest to the origin
        double t = -(point.getX()*vector.getX() + point.getY()*vector.getY() + point.getZ()*vector.getZ());
        point = new Point3(point.getX()+t*vector.getX(),point.getY()+t*vector.getY(),point.getZ()+t*vector.getZ());
    }

    // Getters and Setters
    public Point3 getPoint() {
        return point;
    }
    public Vector3 getVector() {
        return vector;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line3 line = (Line3) o;
        return Objects.equals(point, line.point) && Objects.equals(vector, line.vector);
    }
    @Override
    public int hashCode() {
        return Objects.hash(point, vector);
    }

    // Comparison
    public boolean identical(Line3 l) {
        return point.identical(l.point) && vector.identical(l.vector);
    }
    public boolean opposite(Line3 l) {
        return point.identical(l.point) && vector.opposite(l.vector);
    }
    public boolean identicalOrOpposite(Line3 l) {
        return identical(l) || opposite(l);
    }
    // parallel means same or opposite direction
    public boolean parallel(Line3 l) {
        return vector.parallel(l.vector);
    }
    public boolean sameDirection(Line3 l) {
        return vector.sameDirection(l.vector);
    }
    public boolean oppositeDirection(Line3 l) {
        return vector.oppositeDirection(l.vector);
    }
    public boolean perpendicular(Line3 l) {
        return vector.perpendicular(l.vector);
    }
    public boolean intersect(Line3 l) {
        return identicalOrOpposite(l) || !parallel(l);
    }
    public boolean intersectPoint(Line3 l) {
        return !parallel(l);
    }
    public boolean skew(Line3 l) {
        return false;
    }

    // Properties
    public Line3 reverse() {
        return new Line3(point, vector.reverse());
    }

    // Line and point
    public boolean contains(Point3 p) {
        return new Vector3(point,p).parallel(vector);
    }

    // Line and line
    public double angle(Line3 l) {
        return vector.angle(l.vector);
    }
    public Point3 intersection(Line3 l) throws ParallelLinesException, SkewLines {
        double t;
        boolean t1Found;
        Point3 i;

        if (parallel(l)) {
            throw new ParallelLinesException();
        }

        if (!Maths.equal(vector.getX(),l.vector.getX())) {
            if (!Maths.equal(vector.getX(),0)) {
                double x = (l.getVector().getX()*point.getX() - vector.getX()*l.getPoint().getX()) / (l.vector.getX() - vector.getX());
                t = (x - point.getX()) / vector.getX();
                t1Found = true;
            } else {
                double x = (vector.getX()*l.getPoint().getX() - l.getVector().getX()*point.getX()) / (vector.getX() - l.vector.getX());
                t = (x - l.getPoint().getX()) / l.getVector().getX();
                t1Found = false;
            }
        } else if (!Maths.equal(vector.getY(),l.vector.getY())) {
            if (!Maths.equal(vector.getY(), 0)) {
                double y = (l.getVector().getY() * point.getY() - vector.getY() * l.getPoint().getY()) / (l.vector.getY() - vector.getY());
                t = (y - point.getY()) / vector.getY();
                t1Found = true;
            } else {
                double y = (vector.getY() * l.getPoint().getY() - l.getVector().getY() * point.getY()) / (vector.getY() - l.vector.getY());
                t = (y - l.getPoint().getY()) / l.getVector().getY();
                t1Found = false;
            }
        } else {
            if (!Maths.equal(vector.getZ(), 0)) {
                double y = (l.getVector().getZ() * point.getZ() - vector.getZ() * l.getPoint().getZ()) / (l.vector.getZ() - vector.getZ());
                t = (y - point.getZ()) / vector.getZ();
                t1Found = true;
            } else {
                double y = (vector.getZ() * l.getPoint().getZ() - l.getVector().getZ() * point.getZ()) / (vector.getZ() - l.vector.getZ());
                t = (y - l.getPoint().getZ()) / l.getVector().getZ();
                t1Found = false;
            }
        }

        if (t1Found) {
            i = point.translate(vector.multiply(t));
            if (!l.contains(i)) {
                throw new SkewLines();
            }
        } else {
            i = l.getPoint().translate(l.getVector().multiply(t));
            if (!contains(i)) {
                throw new SkewLines();
            }
        }

        return i;
    }

    // Transformations
    @Override
    public Line3 linearTransform(LinearTransformation3 t) {
        return new Line3(point.linearTransform(t),vector.linearTransform(t));
    }

    @Override
    public Line3 affineTransform(AffineTransformation3 t) throws GeometryException {
        return new Line3(point.affineTransform(t),vector.affineTransform(t));
    }

    @Override
    public Line3 translate(Translation3 t) {
        return new Line3(point.translate(t),vector);
    }

    @Override
    public String toString() {
        return point.toString() + " + " + vector.toString() + "t";
    }
}
