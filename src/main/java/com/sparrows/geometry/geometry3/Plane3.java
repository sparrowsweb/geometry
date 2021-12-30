package com.sparrows.geometry.geometry3;

import com.sparrows.geometry.exception.GeometryException;
import com.sparrows.geometry.exception.PointsCollinearException;
import com.sparrows.geometry.exception.ZeroVectorException;
import com.sparrows.geometry.spherical.GreatCircle;
import com.sparrows.geometry.transformation.d3.AffineTransformation3;
import com.sparrows.geometry.transformation.d3.LinearTransformation3;
import com.sparrows.geometry.transformation.Translation3;
import com.sparrows.geometry.maths.Maths;

import java.util.Objects;

public class Plane3 implements GeometryObject3<Plane3> {
    public static final Plane3 x0;
    public static final Plane3 y0;
    public static final Plane3 z0;
    static {
        Plane3 temp;
        try {
            temp = new Plane3(Vector3.xUnit,0);
        } catch (ZeroVectorException e) {
            temp = null;
        }
        x0 = temp;
        try {
            temp = new Plane3(Vector3.yUnit,0);
        } catch (ZeroVectorException e) {
            temp = null;
        }
        y0 = temp;
        try {
            temp = new Plane3(Vector3.zUnit,0);
        } catch (ZeroVectorException e) {
            temp = null;
        }
        z0 = temp;
    }

    private Vector3 normal;
    private double distanceOrigin;

    // Constructors
    public Plane3(Vector3 normal, double distanceOrigin) throws ZeroVectorException {
        this.normal = normal.unit();
        this.distanceOrigin = distanceOrigin;
    }
    public Plane3(Vector3 normal, Point3 point) throws ZeroVectorException {
        this.normal = normal.unit();
        this.distanceOrigin = new Vector3(point).dot(this.normal);
    }
    public Plane3(Plane3 a) {
        this.normal = new Vector3(a.normal);
        this.distanceOrigin = a.distanceOrigin;
    }
    public Plane3(Point3 p1, Point3 p2, Point3 p3) throws PointsCollinearException {
        try {
            this.normal = new Vector3(p1,p2).cross(new Vector3(p2,p3)).unit();
        } catch (ZeroVectorException e) {
            throw new PointsCollinearException();
        }
        this.distanceOrigin = new Vector3(p1).dot(this.normal);
    }
    public Plane3(GreatCircle c) throws ZeroVectorException {
        this(new Vector3(c.getCentre()), Point3.origin);
    }

    // Getters and Setters
    public Vector3 getNormal() {
        return normal;
    }
    public double getDistanceOrigin() {
        return distanceOrigin;
    }
    public Point3 pointOnPlane() {
        return new Point3(this.normal.multiply(this.distanceOrigin));
    }

    // Comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var plane3 = (Plane3) o;
        return Double.compare(plane3.distanceOrigin, distanceOrigin) == 0 && Objects.equals(normal, plane3.normal);
    }
    @Override
    public int hashCode() {
        return Objects.hash(normal, distanceOrigin);
    }
    public boolean identical(Plane3 a) {
        return normal.identical(a.normal) && Maths.equal(distanceOrigin, a.distanceOrigin);
    }
    public boolean opposite(Plane3 a) {
        return normal.opposite(a.normal) && Maths.equal(distanceOrigin, -a.distanceOrigin);
    }
    public boolean identicalOrOpposite(Plane3 a) {
        return identical(a) || opposite(a);
    }

    // Plane and point
    public boolean contains(Point3 p) {
       return Maths.equal(p.getX()*normal.getX() + p.getY()*normal.getY() + p.getZ()*normal.getZ(),distanceOrigin);
    }

    // Plane and plane
    public double angle(Plane3 a) {
        try {
            return normal.angle(a.normal);
        } catch (ZeroVectorException e) {
            return 0;
        }
    }

    // Transformations
    @Override
    public Plane3 linearTransform(LinearTransformation3 t) throws GeometryException {
        Vector3 newNormal = normal.linearTransform(t).unit();
        double newD = new Vector3(pointOnPlane().linearTransform(t)).dot(newNormal);
        return new Plane3(newNormal,newD);
    }

    @Override
    public Plane3 affineTransform(AffineTransformation3 t) throws GeometryException {
        Vector3 newNormal = normal.affineTransform(t).unit();
        double newD = new Vector3(pointOnPlane().affineTransform(t)).dot(newNormal);
        return new Plane3(newNormal,newD);
    }

    @Override
    public Plane3 translate(Translation3 t) {
        try {
            return new Plane3(normal,new Vector3(pointOnPlane().translate(t)).dot(normal));
        } catch (ZeroVectorException e) {
            return null;// can't happen
        }
    }

    @Override
    public String toString() {
        return normal.toString() + "," + distanceOrigin;
    }
}
