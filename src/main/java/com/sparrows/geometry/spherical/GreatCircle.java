package com.sparrows.geometry.spherical;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.sparrows.geometry.exception.AntipodalPoints;
import com.sparrows.geometry.exception.GreatCircleContainsArc;
import com.sparrows.geometry.exception.IdenticalOrAntipodalPoints;
import com.sparrows.geometry.exception.IdenticalOrOppositeGreatCircles;
import com.sparrows.geometry.exception.IdenticalSphericalPoints;
import com.sparrows.geometry.exception.InvalidSphericalPoint;
import com.sparrows.geometry.exception.PointArc;
import com.sparrows.geometry.exception.ZeroVectorException;
import com.sparrows.geometry.geometry3.Vector3;
import com.sparrows.geometry.maths.Maths;
import com.sparrows.geometry.transformation.SphericalTransformation;

public class GreatCircle implements SphericalObject<GreatCircle> {

    private final SphericalPoint centre;

    public GreatCircle(SphericalPoint centre) {
        this.centre = centre;
    }
    public GreatCircle(GreatCircle s) {
        this(s.centre);
    }

    /**
     * <p>The great circle passing through two given points. The orientation of the great circle is such that arc AB is
     * anticlockwise when viewed looking into the normal vector.</p>
     * @param a the first point
     * @param b the second point
     * @throws IdenticalOrAntipodalPoints if a and b or the same or opposite
     */
    public GreatCircle(SphericalPoint a, SphericalPoint b) throws AntipodalPoints, IdenticalSphericalPoints {
        this.centre = centreFromPoints(a,b);
    }
    public GreatCircle(Arc arc) throws PointArc {
        try {
            this.centre = centreFromPoints(arc.getStartPoint(), arc.getArcType() == Arc.ArcType.MERIDIAN ? arc.getMidpoint() : arc.getEndPoint());
        } catch (AntipodalPoints | IdenticalSphericalPoints e) {
            throw new PointArc();
        }
    }
    private SphericalPoint centreFromPoints (SphericalPoint a, SphericalPoint b) throws AntipodalPoints, IdenticalSphericalPoints {
        SphericalPoint result;
        if (a.identical(b)) {
            throw new IdenticalSphericalPoints();
        }
        if (a.identicalOrAntipodal(b)) {
            throw new AntipodalPoints();
        }
        result = new SphericalPoint(new Vector3(a).cross(new Vector3(b)).unit());
        return result;
    }

    public SphericalPoint getCentre() {
        return centre;
    }

    /**
     * <p>Intersection of two great circles</p>
     * @param c a great circle
     * @return A point p such that c1.centre, c2.centre, p is right-handed; the inverse is also a point of intersection.
     * @throws IdenticalOrOppositeGreatCircles
     */
    public SphericalPoint intersection(GreatCircle c) throws IdenticalOrOppositeGreatCircles {
        return new SphericalPoint(new Vector3(centre).cross(new Vector3(c.getCentre())).unit());
    }

    /**
     * <p>The intersection points of this great circle and an arc. There can be up to 2 intersections. An exception is thrown if
     * the arc is completely contained within this great circle, unless it is a point arc, in which case the point is returned.</p>
     * @param arc an arc
     * @return A list of points of intersection.
     * @exception GreatCircleContainsArc If the arc is contained within this great circle.
     */
    public List<SphericalPoint> intersections(Arc arc) throws GreatCircleContainsArc {
        List<SphericalPoint> intersections = new ArrayList<>();
        if (arc.getStartPoint().identical(arc.getEndPoint())) {
            if (contains(arc.getStartPoint())) {
                intersections.add(arc.getStartPoint());
            }
        } else if (contains(arc)) {
            throw new GreatCircleContainsArc();
        } else {
            try {
                SphericalPoint possibleIntersection = intersection(new GreatCircle(arc));
                if (arc.contains(possibleIntersection)) {
                    intersections.add(possibleIntersection);
                }
                if (arc.contains(possibleIntersection.invert())) {
                    intersections.add(possibleIntersection.invert());
                }
            } catch (PointArc e) {
                e.printStackTrace();
                // can't happen
            } catch (IdenticalOrOppositeGreatCircles identicalOrOppositeGreatCircles) {
                // arc is within great circle, treat as no intersection
            }
        }
        return intersections;
    }

    /**
     * <p>Is a given point on this great circle?</p>
     * @param point The point.
     * @return true if the point is on this great circle
     */
    public boolean contains(SphericalPoint point) {
        return Maths.equal(new Vector3(centre).dot(new Vector3(point)),0);
    }

    /**
     * <p>Is a given arc within this great circle?</p>
     * @param arc The arc.
     * @return true if the arc is within this great circle
     */
    public boolean contains(Arc arc) {
        return contains(arc.getStartPoint()) && contains(arc.getMidpoint());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GreatCircle that = (GreatCircle) o;
        return Objects.equals(centre, that.centre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(centre);
    }

    // Comparison
    public boolean identical(GreatCircle c) {
        return centre.identical(c.centre);
    }
    public boolean opposite(GreatCircle c) {
        return centre.antipodal(c.centre);
    }
    public boolean identicalOrOpposite(GreatCircle c) {
        return identical(c) || opposite(c);
    }

    public GreatCircle reverse() {
        return new GreatCircle(centre.antipode());
    }

    /**
     * <p>Find the angle between two great circles. this is the angle between the normals. The value returned is
     * between 0 and π. Note that the negative of this value is an equally valid solution.
     * @param c another great circle
     * @return the angle between this and c
     */
    public double angle(GreatCircle c) {
        double dot = new Vector3(centre).dot(new Vector3(c.centre));
        return Maths.arcCosine(dot);
    }

    @Override
    public GreatCircle sphericalTransform(SphericalTransformation t) {
        return new GreatCircle(centre.sphericalTransform(t));
    }

}
