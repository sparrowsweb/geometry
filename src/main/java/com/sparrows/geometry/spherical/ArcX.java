/*package com.sparrows.geometry.spherical;

import java.util.Objects;

import com.sparrows.geometry.exception.AntipodalPoints;
import com.sparrows.geometry.exception.IdenticalOrAntipodalPoints;
import com.sparrows.geometry.spherical.SphericalObject;
import com.sparrows.geometry.spherical.SphericalPoint;
import com.sparrows.geometry.transformation.SphericalTransformation;

public class Arc implements SphericalObject<Arc> {

    private final SphericalPoint point1;
    private final SphericalPoint point2;

    public Arc(SphericalPoint point1, SphericalPoint point2) throws AntipodalPoints {
        if (point1.antipodal(point2)) {
            throw new AntipodalPoints();
        }
        this.point1 = point1;
        this.point2 = point2;
    }
    public Arc(Arc a) {
        this.point1 = a.point1;
        this.point2 = a.point2;
    }

    public SphericalPoint getPoint1() {
        return point1;
    }

    public SphericalPoint getPoint2() {
        return point2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arc that = (Arc) o;
        return Objects.equals(point1, that.point1) && Objects.equals(point2, that.point2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point1, point2);
    }

    // Comparison
    public boolean identical(Arc l) {
        return point1.identical(l.point1) && point2.identical(l.point2);
    }

    public boolean opposite(Arc l) {
        return point1.identical(l.point2) && point2.identical(l.point1);
    }
    public boolean identicalOrOpposite(Arc l) {
        return identical(l) || opposite(l);
    }

    public double length() {
        return point1.minorDistance(point2);
    }

    public Arc reverse() {
        try {
            return new Arc(point2,point1);
        } catch (AntipodalPoints antipodalPoints) {
            antipodalPoints.printStackTrace();
            return null;
        }
    }

    // Arc and Arc
    public double angle(Arc a) throws IdenticalOrAntipodalPoints {
        return new GreatCircle(this).angle(new GreatCircle(a));
    }

    // Transformations
    @Override
    public Arc sphericalTransform(SphericalTransformation t) {
        try {
            return new Arc(point1.sphericalTransform(t),point2.sphericalTransform(t));
        } catch (AntipodalPoints antipodalPoints) {
            antipodalPoints.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "<" + point1.toString() + " - " + point2.toString() + ">";
    }

}
*/