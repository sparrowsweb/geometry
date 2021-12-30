package com.sparrows.geometry.spherical;

import java.util.Objects;
import java.util.Random;

import org.ejml.simple.SimpleMatrix;

import com.sparrows.geometry.exception.AntipodalPoints;
import com.sparrows.geometry.exception.CannotFindFermatPoint;
import com.sparrows.geometry.exception.GeometryException;
import com.sparrows.geometry.exception.IdenticalOrAntipodalPoints;
import com.sparrows.geometry.exception.IdenticalOrOppositeGreatCircles;
import com.sparrows.geometry.exception.IdenticalSphericalPoints;
import com.sparrows.geometry.exception.InvalidSphericalPoint;
import com.sparrows.geometry.exception.NotEnoughSphericalVertices;
import com.sparrows.geometry.exception.NotEnoughVertices;
import com.sparrows.geometry.exception.PointArc;
import com.sparrows.geometry.exception.PointsCollinearException;
import com.sparrows.geometry.exception.ZeroVectorException;
import com.sparrows.geometry.geometry3.Plane3;
import com.sparrows.geometry.geometry3.Point3;
import com.sparrows.geometry.geometry3.Polygon3;
import com.sparrows.geometry.geometry3.Vector3;
import com.sparrows.geometry.maths.Maths;
import com.sparrows.geometry.transformation.SphericalTransformation;

public class SphericalPoint implements SphericalObject<SphericalPoint> {
    public static final SphericalPoint northPole = new SphericalPoint(0,0,1,true);
    public static final SphericalPoint southPole = new SphericalPoint(0,0,-1,true);

    private final double x;
    private final double y;
    private final double z;

    // Constructors
    private SphericalPoint(double x, double y, double z, boolean noCheck) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public SphericalPoint(double x, double y, double z) throws InvalidSphericalPoint {
        if (!Maths.equal(x*x + y*y + z*z, 1.0)) {
            throw new InvalidSphericalPoint();
        }
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public SphericalPoint(SphericalPoint p) {
        this.x = p.x;
        this.y = p.y;
        this.z = p.z;
    }
    public SphericalPoint(Vector3 v) throws InvalidSphericalPoint {
        this(v.getX(),v.getY(), v.getZ());
    }
    public SphericalPoint(Point3 p) throws InvalidSphericalPoint {
        this(p.getX(),p.getY(),p.getZ());
    }
    public SphericalPoint(SimpleMatrix matrix) throws InvalidSphericalPoint {
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
        var point = (SphericalPoint) o;
        return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0 && Double.compare(point.z, z) == 0;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    public boolean identical(SphericalPoint p) {
        return Maths.equal(x, p.x) && Maths.equal(y, p.y) && Maths.equal(z, p.z);
    }

    public boolean antipodal(SphericalPoint p) {
        return Maths.equal(x, -p.x) && Maths.equal(y, -p.y) && Maths.equal(z, -p.z);
    }
    public boolean identicalOrAntipodal(SphericalPoint p) {
        return identical(p) || antipodal(p);
    }

    // Properties
    public SphericalPoint antipode() {
        try {
            return new SphericalPoint(-x,-y,-z);
        } catch (InvalidSphericalPoint invalidSphericalPoint) {
            invalidSphericalPoint.printStackTrace();
            return null;
        }
    }
    public SimpleMatrix matrix() {
        return new SimpleMatrix(new double[][] {{this.x},{this.y},{this.z}});
    }

    // Two points
    /**
     * <p>The distance between two points measured through 3D Euclidean space.</p>
     * @param point The other point.
     * @return The distance.
     */
    public double euclideanDistance (SphericalPoint point) {
        return Math.sqrt(Maths.square(point.x - x) + Maths.square(point.y - y) + Maths.square(point.z - z));
    }
    /**
     * <p>The shortest great circle spherical distance between two points.</p>
     * @param point The other point.
     * @return The distance.
     */
    public double minorDistance(SphericalPoint point) {
        return Maths.arcCosine(new Vector3(this).dot(new Vector3(point)));
    }
    /**
     * <p>The spherical great circle distance between two points, along the minor or major arc.</p>
     * @param point The other point.
     * @param minor True if the minor arc distance is to the returned, false if the major arc.
     * @return The distance.
     */
    public double distance(SphericalPoint point, boolean minor) {
        if (minor) {
            return minorDistance(point);
        } else {
            return 2*Math.PI- minorDistance(point);
        }
    }
    /**
     * <p>The midpoint of between two points, along the minor or major arc.</p>
     * @param point The other point.
     * @param minor True if the minor arc is to the used, false if the major arc.
     * @return The distance.
     */
    public SphericalPoint midpoint(SphericalPoint point, boolean minor) throws AntipodalPoints, PointArc {
        if (antipodal(point)) {
            throw new AntipodalPoints();
        }
        if (identical(point) && !minor) {
            throw new PointArc();
        }

        try {
            var shortMidpoint = new SphericalPoint(new Vector3(this).add(new Vector3(point)).unit());
            return minor ? shortMidpoint : shortMidpoint.invert();
        } catch (ZeroVectorException | InvalidSphericalPoint e) {
            e.printStackTrace();
            return null;
        }
    }

    // Three points
    /**
     * <p>A great circle through this, bisecting angle A-this-B.</p>
     * @param a Point A.
     * @param b Point B.
     * @return A bisecting great circle.
     */
    public GreatCircle bisectingGreatCircle(SphericalPoint a, SphericalPoint b) throws AntipodalPoints, IdenticalSphericalPoints, PointArc {
        var arcA = new Arc(this,a,true);
        var arcB = new Arc(this,b,true);
        double angle = arcA.angle(arcB);
        Arc test = arcA.rotate(this,angle);
        if (!Maths.equal(test.angle(arcB),0)) {
            angle = -angle;
        }
        var bisection = arcA.rotate(this,angle/2);
        return new GreatCircle(bisection);
    }

    public static SphericalPoint incentre(SphericalPoint p, SphericalPoint q, SphericalPoint r) throws PointArc, AntipodalPoints, IdenticalSphericalPoints, IdenticalOrOppositeGreatCircles {
        var pq = r.bisectingGreatCircle(p,q);
        var qr = p.bisectingGreatCircle(q,r);
//        var rp = q.bisectingGreatCircle(r,p);
        var incentre = pq.intersection(qr);
//        var check = pq.intersection(rp);
        //       if (!check.identical(incentre)) {
        //         throw new AntipodalPoints();
        //   }
        return incentre;
    }

    public SphericalPoint projection(GreatCircle c) throws IdenticalOrAntipodalPoints, AntipodalPoints, IdenticalSphericalPoints, IdenticalOrOppositeGreatCircles {
        if (identicalOrAntipodal(c.getCentre())) {
            throw new IdenticalOrAntipodalPoints();
        }
        var perpendicularCircle = new GreatCircle(this,c.getCentre());
        SphericalPoint projection = c.intersection(perpendicularCircle);
        if (distance(projection,true) > Math.PI/2) {
            projection = projection.antipode();
        }
        return projection;
    }

    public static SphericalPoint fermatPoint(SphericalPoint a, SphericalPoint b, SphericalPoint c) throws NotEnoughSphericalVertices, ZeroVectorException, InvalidSphericalPoint, CannotFindFermatPoint, PointArc, AntipodalPoints, IdenticalOrOppositeGreatCircles, IdenticalSphericalPoints {
        SphericalPoint seed = SphericalPoint.incentre(a, b, c);
        try {
            return fermatPoint(a, b, c, seed);
        } catch (CannotFindFermatPoint e) {
            System.out.print("try 2...");
            try {
                return fermatPoint(a, b, c, seed.reflect(new GreatCircle(b, c)));
            } catch (CannotFindFermatPoint e2) {
                System.out.print("try 3");
                Random rng = new Random();
                for (int i = 1; i <= 1000; i++) {
                    if (i%10 == 0) System.out.print(".");
                    try {
                        Vector3 v = new Vector3(rng.nextDouble(), rng.nextDouble(), rng.nextDouble()).unit();
                        seed = new SphericalPoint(v.getX(), v.getY(), v.getZ());
                        return fermatPoint(a, b, c, seed);
                    } catch (Exception e3) {

                    }
                }
                throw new CannotFindFermatPoint();
      /*          System.out.print("try 3...");
                return fermatPoint(a,b,c,c);
                try {
                    return fermatPoint(a, b, c, seed.reflect(new GreatCircle(a, b)));
                } catch (CannotFindFermatPoint e3) {
                    System.out.print("try 4...");
                    return fermatPoint(a, b, c, seed.reflect(new GreatCircle(c, a)));
                }*/
           }
        }
    }

    public static SphericalPoint fermatPoint(SphericalPoint a, SphericalPoint b, SphericalPoint c, SphericalPoint seed) throws NotEnoughSphericalVertices, ZeroVectorException, InvalidSphericalPoint, CannotFindFermatPoint, PointArc, AntipodalPoints, IdenticalOrOppositeGreatCircles, IdenticalSphericalPoints {
        var triangle = new SphericalPolygon(a,b,c);
        SphericalPoint[] vertex = new SphericalPoint[]{a,b,c};
        double[] vertexAngle = new double[3];
        double[] sinVertexAngle = new double[3];
        for (var v = 0; v < 3; v++) {
            vertexAngle[v] = triangle.vertexAngle(v);
            sinVertexAngle[v] = Math.sin(vertexAngle[v]);
        }

        var fermat = seed;
        double diff = 1;

        for (var i = 0; i < 10000; i++) {
            for (var v = 0; v < 3; v++) {
                double thisDist = fermat.distance(vertex[v], true);
                double otherDist = fermat.distance(vertex[(v + 2) % 3], true);

                double expectedThisDist = Math.sin(otherDist) * sinVertexAngle[(v + 2) % 3] / sinVertexAngle[v];
                diff = expectedThisDist - Math.sin(thisDist);
//                System.out.println(i + " diff = " + diff);
                // move centre distance diff towards v-1
                var axis = new SphericalPoint(new Vector3(fermat).cross(new Vector3(vertex[(v + 2) % 3])).unit());
                fermat = fermat.rotate(axis, diff * .67
                );
//                System.out.println(fermat);
//                System.out.println("ratio " + Math.sin(fermat.distance(vertex[0], true)) * sinVertexAngle[0]);
//                System.out.println("ratio " + Math.sin(fermat.distance(vertex[1], true)) * sinVertexAngle[1]);
//                System.out.println("ratio " + Math.sin(fermat.distance(vertex[2], true)) * sinVertexAngle[2]);
            }
            //System.out.println(i + " diff = " + diff);
            if (Math.abs(diff) < Maths.ERROR_MARGIN/1000000) {
//                System.out.println(i + " iterations");
                return fermat;
            }
        }

        if (Math.abs(diff) < Maths.ERROR_MARGIN/1000) {
//            System.out.println("max iterations");
            return fermat;
        }

        throw new CannotFindFermatPoint();
    }

    public static SphericalPoint fermatPoint2(SphericalPoint a, SphericalPoint b, SphericalPoint c) throws GeometryException {

        var seed = SphericalPoint.incentre(a,b,c);
        var candidate = seed;

        var oab = new Plane3(Point3.origin, new Point3(a), new Point3(b));
        var obc = new Plane3(Point3.origin, new Point3(b), new Point3(c));
        var oca = new Plane3(Point3.origin, new Point3(c), new Point3(a));

        for (var i = 0; i < 100000; i++) {
            var candidate3 = new Point3(candidate);
            Point3 projectAB = candidate3.project(oab);
            Point3 projectBC = candidate3.project(obc);
            Point3 projectCA = candidate3.project(oca);

            var triangle = new Polygon3(projectAB,projectBC,projectCA);

            double angleB = triangle.vertexAngle(0);
            double angleC = triangle.vertexAngle(1);
            double angleA = triangle.vertexAngle(2);

            double diff = Maths.max(angleA,angleB,angleC) - Maths.min(angleA,angleB,angleC);
            System.out.println("angles: " + angleA + "," + angleB + "," + angleC + " (diff = " + diff + ")");
            Point3 moveTo, moveFrom;
            if (Math.abs(diff) < Maths.ERROR_MARGIN/1000) {
                break;
            }
            if (false) {
                if (angleA < angleB && angleA < angleC) {
                    moveTo = projectBC;
                } else if (angleB < angleC && angleB < angleA) {
                    moveTo = projectCA;
                } else {
                    moveTo = projectAB;
                }
            } else {
                if (angleA > angleB && angleA > angleC) {
                    moveFrom = projectBC;
                } else if (angleB > angleC && angleB > angleA) {
                    moveFrom = projectCA;
                } else {
                    moveFrom = projectAB;
                }
            }
//            System.out.println("move to " + moveTo);

            Point3 newPoint;
            if (false) {
                newPoint = new Point3(candidate).translate(new Vector3(new Point3(candidate), moveTo).multiply(diff * 0.5));
            } else {
                newPoint = new Point3(candidate).translate(new Vector3(new Point3(candidate), moveFrom).multiply(-diff * 0.5));
            }
            candidate = new SphericalPoint(newPoint.scale(1/newPoint.distance(Point3.origin)));
            System.out.println(i + " new try = " + candidate);

        }

        return candidate;
    }


    // Transformations
    @Override
    public SphericalPoint sphericalTransform(SphericalTransformation t) {
        try {
            return new SphericalPoint(t.getMatrix().mult(matrix()));
        } catch (InvalidSphericalPoint invalidSphericalPoint) {
            invalidSphericalPoint.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        double x1 = Maths.equal(0,x) ? 0 : x;
        double y1 = Maths.equal(0,y) ? 0 : y;
        double z1 = Maths.equal(0,z) ? 0 : z;
        return "(" + x1 + "," + y1 + "," + z1 + ")";
    }
}
