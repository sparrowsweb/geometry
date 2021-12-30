package com.sparrows.geometry.geometry2;

import com.sparrows.geometry.exception.ZeroVectorException;
import com.sparrows.geometry.transformation.AffineTransformation2;
import com.sparrows.geometry.transformation.LinearTransformation2;
import com.sparrows.geometry.transformation.Translation2;
import com.sparrows.geometry.maths.Maths;
import org.ejml.simple.SimpleMatrix;

import java.util.Objects;


public class Vector2 implements GeometryObject2<Vector2> {
    public static final Vector2 zero = new Vector2(0,0);
    public static final Vector2 xUnit = new Vector2(1,0);
    public static final Vector2 yUnit = new Vector2(0,1);

    private double x;
    private double y;

    // Constructors
    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Vector2() {
        this.x = 0;
        this.y = 0;
    }
    public Vector2(Point2 p1, Point2 p2) {
        this.x = p2.getX() - p1.getX();
        this.y = p2.getY() - p1.getY();
    }
    public Vector2(Point2 p) {
        this(p.getX(),p.getY());
    }
    public Vector2(Vector2 v) {
        this(v.x,v.y);
    }
    public Vector2(SimpleMatrix matrix) {
        this(matrix.get(0,0),matrix.get(1,0));
    }

    // Getters and Setters
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    // Comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var vector2 = (Vector2) o;
        return Double.compare(vector2.x, x) == 0 && Double.compare(vector2.y, y) == 0;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    public boolean identical(Vector2 v) {
        return Maths.equal(x,v.x) && Maths.equal(y,v.y);
    }
    public boolean opposite(Vector2 v) {
        return Maths.equal(x,-v.x) && Maths.equal(y,-v.y);
    }
    // parallel means same or opposite direction; zero is parallel to everything
    public boolean parallel(Vector2 v) {
        return Maths.equal(x*v.y - y*v.x, 0);
    }
    public boolean sameDirection(Vector2 v) {
        if (!parallel(v)) {
            return false;
        } else {
            int signX = Maths.sign(x);
            int signVX = Maths.sign(v.x);
            if (signX * signVX == 1) {
                return true;
            } else if (signX * signVX == -1) {
                return false;
            } else {
                return Maths.sign(y) * Maths.sign(v.y) >= 0;
            }
        }
    }
    public boolean oppositeDirection(Vector2 v) {
        if (!parallel(v)) {
            return false;
        } else {
            int signX = Maths.sign(x);
            int signVX = Maths.sign(v.x);
            if (signX * signVX == -1) {
                return true;
            } else if (signX * signVX == 1) {
                return false;
            } else {
                return Maths.sign(y) * Maths.sign(v.y) <= 0;
            }
        }
    }
    public boolean perpendicular(Vector2 v) {
        return Maths.equal(dot(v),0);
    }

    // Vector
    public boolean isZero() {
        return Maths.equal(x,0) && Maths.equal(y,0);
    }
    public double lengthSquared () {
        return x*x+y*y;
    }
    public double length () {
        return Math.sqrt(lengthSquared());
    }
    public Vector2 unit() throws ZeroVectorException {
        double len = length();
        if (Maths.equal(len, 0)) {
            throw new ZeroVectorException();
        }
        return divide(len);
    }
    public SimpleMatrix matrix() {
        return new SimpleMatrix(new  double[][]{{x},{y}});
    }
    public Vector2 reverse() {
        return new Vector2(-x,-y);
    }

    // Vector and scalar
    public Vector2 multiply(double factor) {
        return new Vector2(x*factor,y*factor);
    }
    public Vector2 negate() {
        return new Vector2(-x,-y);
    }
    public Vector2 divide(double x) {
        return multiply(1/x);
    }

    // Vector and vector
    public Vector2 add(Vector2 v) {
        return new Vector2(x+v.x,y+v.y);
    }
    public Vector2 subtract(Vector2 v) {
        return new Vector2(x-v.x,y-v.y);
    }
    public double dot(Vector2 v) {
        return x*v.x + y*v.y;
    }
    public double angle(Vector2 v) throws ZeroVectorException {
        if (isZero() || v.isZero()) {
            throw new ZeroVectorException();
        }
        double d = dot(v)/length()/v.length();
        double a = Math.acos(d);
        // catering for rounding errors that result in cosine being slightly over 1 or slightly under -1
        if (Double.isNaN(a)) {
            if (Maths.equal(d,1)) {
                a = 0;
            } else if (Maths.equal(d,-1)) {
                a = Math.PI;
            }
        }
        return a;
    }
    // positive (anti-clockwise) angle through which to rotate 'this' to get 'v'
    public double positiveAngle(Vector2 v) throws ZeroVectorException {
        double angle = angle(v);
        if (x*v.y - y*v.x < 0) {
            angle = -angle;
        }
        return angle;
    }

    // Transformations
    @Override
    public Vector2 linearTransform(LinearTransformation2 t) {
        return new Vector2(t.getMatrix().mult(matrix()));
    }

    @Override
    public Vector2 affineTransform(AffineTransformation2 t) {
        return linearTransform(t.linearTransformation());
    }

    @Override
    public Vector2 translate(Translation2 t) {
        return new Vector2(this);
    }
}
