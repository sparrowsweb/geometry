package com.sparrows.geometry.geometry3;

import com.sparrows.geometry.exception.ZeroVectorException;
import com.sparrows.geometry.spherical.SphericalPoint;
import com.sparrows.geometry.transformation.d3.AffineTransformation3;
import com.sparrows.geometry.transformation.d3.LinearTransformation3;
import com.sparrows.geometry.transformation.Translation3;
import com.sparrows.geometry.maths.Maths;
import org.ejml.simple.SimpleMatrix;

import java.util.Objects;


public class Vector3 implements GeometryObject3<Vector3> {
    public static final Vector3 zero = new Vector3(0,0,0);
    public static final Vector3 X_UNIT = new Vector3(1,0,0);
    public static final Vector3 Y_UNIT = new Vector3(0,1,0);
    public static final Vector3 Z_UNIT = new Vector3(0,0,1);

    private double x;
    private double y;
    private double z;

    // Constructors
    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Vector3() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }
    public Vector3(Point3 p1, Point3 p2) {
        this.x = p2.getX() - p1.getX();
        this.y = p2.getY() - p1.getY();
        this.z = p2.getZ() - p1.getZ();
    }
    public Vector3(Point3 p) {
        this(p.getX(),p.getY(),p.getZ());
    }
    public Vector3(SphericalPoint p) {
        this(p.getX(),p.getY(),p.getZ());
    }
    public Vector3(Vector3 v) {
        this(v.x,v.y,v.z);
    }
    public Vector3(SimpleMatrix matrix) {
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
    public void setXYZ(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var vector2 = (Vector3) o;
        return Double.compare(vector2.x, x) == 0 && Double.compare(vector2.y, y) == 0 && Double.compare(vector2.z, z) == 0;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
    public boolean identical(Vector3 v) {
        return Maths.equal(x,v.x) && Maths.equal(y,v.y) && Maths.equal(z,v.z);
    }
    public boolean opposite(Vector3 v) {
        return Maths.equal(x,-v.x) && Maths.equal(y,-v.y) && Maths.equal(z,-v.z);
    }
    // parallel means same or opposite direction; zero is parallel to everything
    public boolean parallel(Vector3 v) {
        return Maths.equal(x*v.y - y*v.x, 0) && Maths.equal(y*v.z - z*v.y, 0) && Maths.equal(z*v.x - x*v.z, 0);
    }
    public boolean sameDirection(Vector3 v) {
        return parallel(v) && unit().identical(v.unit());
    }
    public boolean oppositeDirection(Vector3 v) {
        return parallel(v) && !unit().identical(v.unit());
    }
    public boolean perpendicular(Vector3 v) {
        return Maths.equal(dot(v),0);
    }

    // Vector
    public boolean isZero() {
        return Maths.equal(x,0) && Maths.equal(y,0) && Maths.equal(z,0);
    }
    public double lengthSquared () {
        return x*x+y*y+z*z;
    }
    public double length () {
        return Math.sqrt(lengthSquared());
    }
    public Vector3 unit() {
        double len = length();
        if (Maths.equal(len, 0)) {
            throw new IllegalArgumentException("Zero vector.");
        }
        return divide(len);
    }
    public SimpleMatrix matrix() {
        return new SimpleMatrix(new  double[][]{{x},{y},{z}});
    }
    public Vector3 reverse() {
        return new Vector3(-x,-y,-z);
    }

    // Vector and scalar
    public Vector3 multiply(double factor) {
        return new Vector3(x*factor,y*factor,z*factor);
    }
    public Vector3 negate() {
        return new Vector3(-x,-y,-z);
    }
    public Vector3 divide(double x) {
        return multiply(1/x);
    }

    // Vector and vector
    public Vector3 add(Vector3 v) {
        return new Vector3(x+v.x,y+v.y,z+v.z);
    }
    public Vector3 subtract(Vector3 v) {
        return new Vector3(x-v.x,y-v.y,z-v.z);
    }
    public double dot(Vector3 v) {
        return x*v.x + y*v.y + z*v.z;
    }
    public Vector3 cross(Vector3 v) {
        return new Vector3(
                y*v.getZ()-z*v.getY(),
                z*v.getX()-x*v.getZ(),
                x*v.getY()-y*v.getX()
        );
    }
    public double angle(Vector3 v) {
        if (isZero() || v.isZero()) {
            throw new IllegalArgumentException("Zero Vector.");
        }
        double d = dot(v)/length()/v.length();
        return Maths.arcCosine(d);
    }

    // Transformations
    @Override
    public Vector3 linearTransform(LinearTransformation3 t) {
        return new Vector3(t.getMatrix().mult(matrix()));
    }

    @Override
    public Vector3 affineTransform(AffineTransformation3 t) {
        return linearTransform(t.linearTransformation());
    }

    @Override
    public Vector3 translate(Translation3 t) {
        return new Vector3(this);
    }

    @Override
    public String toString() {
        double x1 = Maths.equal(0,x) ? 0 : x;
        double y1 = Maths.equal(0,y) ? 0 : y;
        double z1 = Maths.equal(0,z) ? 0 : z;
        return "(" + x1 + "," + y1 + "," + z1 + ")";
    }
}
