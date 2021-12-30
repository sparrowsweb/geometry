package com.sparrows.geometry.geometry3;

import com.sparrows.geometry.exception.ZeroVectorException;
import com.sparrows.geometry.geometry2.Point2;
import com.sparrows.geometry.spherical.SphericalPoint;
import com.sparrows.geometry.transformation.d3.AffineTransformation3;
import com.sparrows.geometry.transformation.d3.LinearTransformation3;
import com.sparrows.geometry.transformation.Translation3;
import com.sparrows.geometry.maths.Maths;
import org.ejml.simple.SimpleMatrix;

import java.util.List;
import java.util.Objects;

public class Point3 implements GeometryObject3<Point3> {
    public static final Point3 origin = new Point3(0,0,0);

    private final double x;
    private final double y;
    private final double z;

    // Constructors
    public Point3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Point3() {
        this(0,0,0);
    }
    public Point3(Point3 p) {
        this(p.x, p.y, p.z);
    }
    public Point3(Vector3 v) {
        this(v.getX(), v.getY(), v.getZ());
    }
    public Point3(SphericalPoint p) {
        this(p.getX(), p.getY(), p.getZ());
    }
    public Point3(SimpleMatrix matrix) {
        this(matrix.get(0,0),matrix.get(1,0),matrix.get(2,0));
    }

    // Getters and Setters
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getZ() {
        return z;
    }

    // Comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var point3 = (Point3) o;
        return Double.compare(point3.x, x) == 0 && Double.compare(point3.y, y) == 0 && Double.compare(point3.z, z) == 0;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
    public boolean identical(Point3 p) {
        return Maths.equal(x, p.x) && Maths.equal(y, p.y) && Maths.equal(z, p.z);
    }

    // Properties
    public SimpleMatrix matrix() {
        return new SimpleMatrix(new double[][] {{this.x},{this.y},{this.z}});
    }
    public SimpleMatrix augmentedMatrix() {
        return new SimpleMatrix(new double[][] {{this.x},{this.y},{this.z},{1}});
    }

    // Two Points
    public double distanceSquared (Point3 p) {
        return Maths.square(p.x - x) + Maths.square(p.y - y) + Maths.square(p.z - z);
    }
    public double distance (Point3 p) {
        return Math.sqrt(distanceSquared(p));
    }
    public Point3 midpoint(Point3 p) {
        return new Point3((x+p.x)/2, (y+p.y)/2, (z+p.z)/2);
    }
    public Plane3 bisectingPlane(Point3 p) throws ZeroVectorException {
        return new Plane3(new Vector3(this,p), midpoint(p));
    }

    public static Point3 centroid(List<Point3> points) {
        double sumX = 0;
        double sumY = 0;
        double sumZ = 0;
        for (var p : points) {
            sumX += p.x;
            sumY += p.y;
            sumZ += p.z;
        }
        return new Point3(sumX/points.size(),sumY/points.size(),sumZ/points.size());
    }

    public static Point3 centroid(Point3...points) {
        double sumX = 0;
        double sumY = 0;
        double sumZ = 0;
        for (var p : points) {
            sumX += p.x;
            sumY += p.y;
            sumZ += p.z;
        }
        return new Point3(sumX/points.length,sumY/points.length,sumZ/points.length);
    }

    public Point3 project(Plane3 plane) {
        return translate(plane.getNormal().multiply(plane.getDistanceOrigin() - new Vector3(this).dot(plane.getNormal())));
    }

    public Point2 project2D() {
        return new Point2(x,y);
    }

    // Transformations
    @Override
    public Point3 linearTransform(LinearTransformation3 t) {
        return new Point3(t.getMatrix().mult(matrix()));
    }

    @Override
    public Point3 affineTransform(AffineTransformation3 t) {
        return new Point3(t.getMatrix().mult(augmentedMatrix()));
    }

    @Override
    public Point3 translate(Translation3 t) {
        return new Point3(t.getMatrix().plus(matrix()));
    }

    @Override
    public String toString() {
        double x1 = Maths.equal(0,x) ? 0 : x;
        double y1 = Maths.equal(0,y) ? 0 : y;
        double z1 = Maths.equal(0,z) ? 0 : z;
        return "(" + x1 + "," + y1 + "," + z1 + ")";
    }
}
