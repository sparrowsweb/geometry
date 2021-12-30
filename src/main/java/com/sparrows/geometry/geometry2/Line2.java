package com.sparrows.geometry.geometry2;

import com.sparrows.geometry.exception.GeometryException;
import com.sparrows.geometry.exception.ParallelLinesException;
import com.sparrows.geometry.exception.ZeroVectorException;
import com.sparrows.geometry.transformation.AffineTransformation2;
import com.sparrows.geometry.transformation.LinearTransformation2;
import com.sparrows.geometry.transformation.Translation2;
import com.sparrows.geometry.maths.Maths;

import java.util.Objects;

public class Line2 implements GeometryObject2<Line2> {

    public static final Line2 xAxis;
    public static final Line2 yAxis;

    static {
        Line2 temp;
        try {
            temp = new Line2(Point2.origin, Vector2.xUnit);
        } catch (ZeroVectorException e) {
            temp = null;
        }
        xAxis = temp;
        try {
            temp = new Line2(Point2.origin, Vector2.yUnit);
        } catch (ZeroVectorException e) {
            temp = null;
        }
        yAxis = temp;
    }

    private Point2 point;
    private Vector2 vector;

    // Constructors
    public Line2(Point2 point, Vector2 vector) throws ZeroVectorException {
        this.point = point;
        this.vector = vector;
        normalise();
    }
    public Line2(Point2 point1, Point2 point2) throws ZeroVectorException {
        this(point1, new Vector2(point1,point2));
    }
    private void normalise() throws ZeroVectorException {
        vector = vector.unit();
        // choose point closest to the origin
        double t = -(point.getX()*vector.getX() + point.getY()*vector.getY());
        point = new Point2(point.getX()+t*vector.getX(),point.getY()+t*vector.getY());
    }

    // Getters and Setters
    public Point2 getPoint() {
        return point;
    }
    public Vector2 getVector() {
        return vector;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line2 line = (Line2) o;
        return Objects.equals(point, line.point) && Objects.equals(vector, line.vector);
    }
    @Override
    public int hashCode() {
        return Objects.hash(point, vector);
    }

    // Comparison
    public boolean identical(Line2 l) {
        return point.identical(l.point) && vector.identical(l.vector);
    }
    public boolean opposite(Line2 l) {
        return point.identical(l.point) && vector.opposite(l.vector);
    }
    public boolean identicalOrOpposite(Line2 l) {
        return identical(l) || opposite(l);
    }
    // parallel means same or opposite direction
    public boolean parallel(Line2 l) {
        return vector.parallel(l.vector);
    }
    public boolean sameDirection(Line2 l) {
        return vector.sameDirection(l.vector);
    }
    public boolean oppositeDirection(Line2 l) {
        return vector.oppositeDirection(l.vector);
    }
    public boolean perpendicular(Line2 l) {
        return vector.perpendicular(l.vector);
    }
    public boolean intersect(Line2 l) {
        return identicalOrOpposite(l) || !parallel(l);
    }
    public boolean intersectPoint(Line2 l) {
        return !parallel(l);
    }

    // Properties
    public Line2 reverse() {
        try {
            return new Line2(point, vector.reverse());
        } catch (ZeroVectorException e) {
            return null; // can't happen
        }
    }

    // line and line
    public double angle(Line2 l) {
        try {
            return vector.angle(l.vector);
        } catch (ZeroVectorException e) {
            return 0; // this can't happen
        }
    }
    public double positiveAngle(Line2 l) {
        try {
            return vector.positiveAngle(l.vector);
        } catch (ZeroVectorException e) {
            return 0; // this can't happen
        }
    }
    public Point2 intersection(Line2 l) throws ParallelLinesException {
        double denominator = vector.getY()*l.vector.getX() - vector.getX()*l.vector.getY();
        if (Maths.equal(denominator,0)) {
            throw new ParallelLinesException();
        }
        double numerator;
        if (!Maths.equal(vector.getX(),0)) {
            numerator = vector.getX() * (l.point.getY() - point.getY()) - vector.getY()*(l.point.getX() - point.getX());
        } else {
            numerator = vector.getY() * (l.point.getX() - point.getX()) - vector.getX()*(l.point.getY() - point.getY());
        }
        double t = numerator/denominator;
        return new Point2(l.getPoint()).translate(l.getVector().multiply(t));
    }

    // Transformations
    @Override
    public Line2 linearTransform(LinearTransformation2 t) {
        try {
            return new Line2(point.linearTransform(t),vector.linearTransform(t));
        } catch (ZeroVectorException e) {
            return null;
        }
    }

    @Override
    public Line2 affineTransform(AffineTransformation2 t) throws GeometryException {
        return new Line2(point.affineTransform(t),vector.affineTransform(t));
    }

    @Override
    public Line2 translate(Translation2 t) {
        try {
            return new Line2(point.translate(t),vector);
        } catch (ZeroVectorException e) {
            return null; // can't happen
        }
    }
}
