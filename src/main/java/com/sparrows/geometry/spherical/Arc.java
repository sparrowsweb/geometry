package com.sparrows.geometry.spherical;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.sparrows.geometry.exception.AntipodalPoints;
import com.sparrows.geometry.exception.GreatCircleContainsArc;
import com.sparrows.geometry.exception.IdenticalSphericalPoints;
import com.sparrows.geometry.exception.InvalidSphericalArc;
import com.sparrows.geometry.exception.OverlappingArcs;
import com.sparrows.geometry.exception.PointArc;
import com.sparrows.geometry.maths.Maths;
import com.sparrows.geometry.transformation.SphericalTransformation;

public class Arc implements SphericalObject<Arc> {

    public enum ArcType {
        MINOR,
        MERIDIAN,
        MAJOR
    }
    private final SphericalPoint startPoint;
    private final SphericalPoint endPoint;
    private final SphericalPoint midpoint;
    private final ArcType arcType;

    // Constructors
    /**
    * <p>Create a minor or major Arc from endpoints. A meridian cannot be created with this method.</p>
    * @param startPoint start of the arc
    * @param endPoint end of the arc
    * @param minor true if this is a minor arc, false if a major arc
    */
    public Arc(SphericalPoint startPoint, SphericalPoint endPoint, boolean minor) throws AntipodalPoints, PointArc {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.midpoint = startPoint.midpoint(endPoint,minor);
        this.arcType = minor ? ArcType.MINOR : ArcType.MAJOR;
    }

    /**
     * <p>Create an arc from endpoints and midpoint.</p>
     * @param startPoint start of the arc
     * @param endPoint end of the arc
     * @param midpoint midpoint of the arc
     */
    public Arc(SphericalPoint startPoint, SphericalPoint endPoint, SphericalPoint midpoint) throws InvalidSphericalArc {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.midpoint = midpoint;
        if (startPoint.antipodal(endPoint)) {
            // 1 and 2 are antipodal, midpoint can be anywhere halfway between 1 or 2
            if (!Maths.equal(midpoint.minorDistance(startPoint),Maths.PI2)) {
                throw new InvalidSphericalArc();
            }
            this.arcType = ArcType.MERIDIAN;
        } else if (startPoint.identical(endPoint)) {
            // 1 and 2 are identical, midpoint must be at 1
            if (!midpoint.identical(startPoint)) {
                throw new InvalidSphericalArc();
            }
            this.arcType = ArcType.MINOR;
        } else {
            SphericalPoint minorMidpoint = null;
            try {
                minorMidpoint = startPoint.midpoint(endPoint,true);
            } catch (AntipodalPoints | PointArc e) {
                e.printStackTrace();
                // shouldn't happen
                throw new InvalidSphericalArc();
            }
            if (midpoint.identical(minorMidpoint)) {
                this.arcType = ArcType.MINOR;
            } else if (midpoint.antipodal(minorMidpoint)) {
                this.arcType = ArcType.MAJOR;
            } else {
                throw new InvalidSphericalArc();
            }
        }
    }

    /**
     * <p>Create an arc from another arc.</p>
     * @param arc the arc
     */
    public Arc(Arc arc) {
        this.startPoint = arc.startPoint;
        this.endPoint = arc.endPoint;
        this.midpoint = arc.midpoint;
        this.arcType = arc.arcType;
    }

    // Getters
    public SphericalPoint getStartPoint() {
        return startPoint;
    }
    public SphericalPoint getEndPoint() {
        return endPoint;
    }
    public SphericalPoint getMidpoint() {
        return midpoint;
    }
    public ArcType getArcType() {
        return arcType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arc arc = (Arc) o;
        return Objects.equals(startPoint, arc.startPoint) && Objects.equals(endPoint, arc.endPoint) && Objects.equals(midpoint, arc.midpoint) && arcType == arc.arcType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startPoint, endPoint, midpoint, arcType);
    }

    // Comparison
    public boolean identical(Arc arc) {
        return startPoint.identical(arc.startPoint) && endPoint.identical(arc.endPoint) && midpoint.identical(arc.midpoint);
    }
    public boolean opposite(Arc arc) {
        return startPoint.identical(arc.endPoint) && endPoint.identical(arc.startPoint) && midpoint.identical(arc.midpoint);
    }
    public boolean identicalOrOpposite(Arc arc) {
        return identical(arc) || opposite(arc);
    }

    // Properties
    public double length() {
        return startPoint.distance(endPoint, (arcType == ArcType.MINOR));
    }

    public Arc reverse() {
        try {
            return new Arc(endPoint, startPoint, midpoint);
        } catch (InvalidSphericalArc invalidSphericalArc) {
            return null;
        }
    }

    public boolean pointArc() {
        return startPoint.identical(endPoint) && arcType == ArcType.MINOR;
    }

    /**
     * <p>Arc with the same start and end points, but going the other way round the great circle.
     * A zero-length arc has no complement.</p>
     * @return The complementary arc.
     */
    public Arc complement() throws InvalidSphericalArc {
        return new Arc(startPoint, endPoint,midpoint.invert());
    }

    // Arc and Arc
    public double angle(Arc a) throws PointArc {
        return new GreatCircle(this).angle(new GreatCircle(a));
    }

    /**
     * <p>Is a given point on this arc?</p>
     * @param point The point.
     * @return true if the point is on this arc
     */
    public boolean contains(SphericalPoint point) {
        if (startPoint.identical(endPoint)) {
            return point.identical(startPoint);
        }
        try {
            if (!new GreatCircle(this).contains(point)) {
                return false;
            }
        } catch (PointArc e) {
            e.printStackTrace();
            return false;
        }
        return Maths.lessThanOrEqual(point.minorDistance(midpoint),startPoint.minorDistance(midpoint));
    }

    /**
     * <p>The intersection points of this arc and another arc. There can be up to 2 intersections. An exception is thrown if
     * the arcs overlap over a non-zero distance.</p>
     * @param arc an arc
     * @return A list of points of intersection.
     * @exception OverlappingArcs If one arc overlaps the other.
     */
    public List<SphericalPoint> intersections(Arc arc) throws OverlappingArcs {
        List<SphericalPoint> intersections = new ArrayList<>();
        if (arc.pointArc()) {
            if (contains(arc.getStartPoint())) {
                // this contains point arc
                intersections.add(arc.getStartPoint());
            }
        } else if (pointArc()) {
            if (arc.contains(startPoint)) {
                // arc contains point this
                intersections.add(getStartPoint());
            }
        } else {
            GreatCircle circle = null;
            GreatCircle circle2 = null;
            try {
                circle = new GreatCircle(this);
                circle2 = new GreatCircle(arc);
            } catch (PointArc pointArc) {
                // can't happen
                pointArc.printStackTrace();
            }
            if (circle.identicalOrOpposite(circle2)) {
                // arc on same great circle (TBD)
                throw new OverlappingArcs();
            } else {
                List<SphericalPoint> possibleIntersections = null;
                try {
                    possibleIntersections = new GreatCircle(this).intersections(arc);
                } catch (GreatCircleContainsArc | PointArc e) {
                    // cannot happen
                    e.printStackTrace();
                }
                for (var possibleIntersection : possibleIntersections) {
                    if (contains(possibleIntersection)) {
                        intersections.add(possibleIntersection);
                    }
                }
            }
        }
        return intersections;
    }

    // Transformations
    @Override
    public Arc sphericalTransform(SphericalTransformation t) {
        try {
            return new Arc(startPoint.sphericalTransform(t), endPoint.sphericalTransform(t),midpoint.sphericalTransform(t));
        } catch (InvalidSphericalArc antipodalPoints) {
            antipodalPoints.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "<" + startPoint.toString() + " - " + endPoint.toString() + ">";
    }

}
