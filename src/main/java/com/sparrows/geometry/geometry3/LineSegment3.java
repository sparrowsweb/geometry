package com.sparrows.geometry.geometry3;

import com.sparrows.geometry.exception.ZeroVectorException;
import com.sparrows.geometry.maths.Maths;
import com.sparrows.geometry.transformation.d3.AffineTransformation3;
import com.sparrows.geometry.transformation.d3.LinearTransformation3;
import com.sparrows.geometry.transformation.Translation3;

import java.util.Objects;

public class LineSegment3 implements GeometryObject3<LineSegment3> {

    private Point3 point1;
    private Point3 point2;

    public LineSegment3(Point3 point1, Point3 point2) {
        this.point1 = point1;
        this.point2 = point2;
    }
    public LineSegment3(LineSegment3 s) {
        this(s.point1,s.point2);
    }

    public Point3 getPoint1() {
        return point1;
    }

    public Point3 getPoint2() {
        return point2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineSegment3 that = (LineSegment3) o;
        return Objects.equals(point1, that.point1) && Objects.equals(point2, that.point2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point1, point2);
    }

    // Comparison
    public boolean identical(LineSegment3 l) {
        return point1.identical(l.point1) && point2.identical(l.point2);
    }
    public boolean opposite(LineSegment3 l) {
        return point1.identical(l.point2) && point2.identical(l.point1);
    }
    public boolean identicalOrOpposite(LineSegment3 l) {
        return identical(l) || opposite(l);
    }

    // Properties
    public Point3 midpoint() {
        return point1.midpoint(point2);
    }
    public Plane3 bisectingPlane() throws ZeroVectorException {
        return new Plane3(new Vector3(point1,point2),midpoint());
    }
    public double length() {
        return point1.distance(point2);
    }

    // LineSegment and LineSegment
    public double angle(LineSegment3 l) {
        double cosine = new Vector3(point1,point2).dot(new Vector3(l.point1,l.point2))/length()/l.length();
        if (Maths.equal(cosine,1)) {
            return 0;
        } else if (Maths.equal(cosine,-1)) {
            return Math.PI;
        } else {
            return Math.acos(cosine);
        }
    }

    // Transformations
    @Override
    public LineSegment3 linearTransform(LinearTransformation3 t) {
        return new LineSegment3(point1.linearTransform(t),point2.linearTransform(t));
    }

    @Override
    public LineSegment3 affineTransform(AffineTransformation3 t) {
        return new LineSegment3(point1.affineTransform(t),point2.affineTransform(t));
    }

    @Override
    public LineSegment3 translate(Translation3 t) {
        return new LineSegment3(point1.translate(t),point2.translate(t));
    }

    @Override
    public String toString() {
        return "<" + point1.toString() + " - " + point2.toString() + ">";
    }
}
