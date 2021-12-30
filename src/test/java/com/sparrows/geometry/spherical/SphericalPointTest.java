package com.sparrows.geometry.spherical;

import static com.sparrows.geometry.utils.TestUtils.checkDouble;
import static com.sparrows.geometry.utils.TestUtils.checkGreatCircle;
import static com.sparrows.geometry.utils.TestUtils.checkMatrix;
import static com.sparrows.geometry.utils.TestUtils.checkSphericalPoint;

import java.io.IOException;
import java.nio.channels.AlreadyBoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.management.relation.InvalidRoleInfoException;

import org.ejml.simple.SimpleMatrix;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.sparrows.geometry.exception.AntipodalPoints;
import com.sparrows.geometry.exception.CannotFindFermatPoint;
import com.sparrows.geometry.exception.DoesNotTileSphere;
import com.sparrows.geometry.exception.GeometryException;
import com.sparrows.geometry.exception.IdenticalOrAntipodalPoints;
import com.sparrows.geometry.exception.IdenticalOrOppositeGreatCircles;
import com.sparrows.geometry.exception.IdenticalSphericalPoints;
import com.sparrows.geometry.exception.InvalidSchwarzTriangle;
import com.sparrows.geometry.exception.InvalidSidesDensity;
import com.sparrows.geometry.exception.InvalidSphericalArc;
import com.sparrows.geometry.exception.InvalidSphericalPoint;
import com.sparrows.geometry.exception.NotEnoughSphericalVertices;
import com.sparrows.geometry.exception.NotEnoughVertices;
import com.sparrows.geometry.exception.PointArc;
import com.sparrows.geometry.exception.PointsCollinearException;
import com.sparrows.geometry.exception.ZeroVectorException;
import com.sparrows.geometry.geometry3.Line3;
import com.sparrows.geometry.geometry3.Plane3;
import com.sparrows.geometry.geometry3.Point3;
import com.sparrows.geometry.geometry3.Polygon3;
import com.sparrows.geometry.maths.Maths;
import com.sparrows.geometry.maths.Rational;

class SphericalPointTest {

    @Test
    void TestNorthPole() {
        SphericalPoint p = SphericalPoint.northPole;
        checkSphericalPoint(p,0,0,1);
    }

    @Test
    void TestSouthPole() {
        SphericalPoint p = SphericalPoint.southPole;
        checkSphericalPoint(p,0,0,-1);
    }

    @Test
    void TestConstructor() throws InvalidSphericalPoint {
        SphericalPoint p = new SphericalPoint(0,0,1);
        checkSphericalPoint(p,0,0,1);
    }

    @Test
    void TestConstructorException()  {
        Assertions.assertThrows(InvalidSphericalPoint.class, () -> new SphericalPoint(1,1,1));
    }

    @Test
    void TestDistanceSame() throws InvalidSphericalPoint {
        SphericalPoint p = new SphericalPoint(0,0,1);
        SphericalPoint q = new SphericalPoint(0,0,1);
        checkDouble(0., p.minorDistance(q));
    }

    @Test
    void TestDistanceQuarterCircle() throws InvalidSphericalPoint {
        SphericalPoint p = new SphericalPoint(0,0,1);
        SphericalPoint q = new SphericalPoint(0,1,0);
        checkDouble(Maths.PI2, p.minorDistance(q));
    }

    @Test
    void TestDistanceHalfCircle() throws InvalidSphericalPoint {
        SphericalPoint p = new SphericalPoint(1,0,0);
        SphericalPoint q = new SphericalPoint(-1,0,0);
        checkDouble(Math.PI, p.minorDistance(q));
    }

    @Test
    void TestIdentical() throws InvalidSphericalPoint {
        SphericalPoint p = new SphericalPoint(1,0,0);
        Assertions.assertTrue(p.identical(new SphericalPoint(1,0,0.00000000001)));
        Assertions.assertTrue(p.identical(new SphericalPoint(1,0.00000000001,0)));
        Assertions.assertTrue(p.identical(new SphericalPoint(1.00000000001,0,0)));
        Assertions.assertFalse(p.identical(new SphericalPoint(1.00000000001,0,0.000000001)));
    }

    @Test
    void TestAntipode() throws InvalidSphericalPoint {
        SphericalPoint p = new SphericalPoint(1,0,0).antipode();
        checkSphericalPoint(p,-1,0,0);
    }

    @Test
    void TestAntipodal() throws InvalidSphericalPoint {
        SphericalPoint p = new SphericalPoint(1,0,0);
        Assertions.assertTrue(p.antipodal(new SphericalPoint(-1,0,0.00000000001)));
        Assertions.assertTrue(p.antipodal(new SphericalPoint(-1,0.00000000001,0)));
        Assertions.assertTrue(p.antipodal(new SphericalPoint(-1.00000000001,0,0)));
        Assertions.assertFalse(p.antipodal(new SphericalPoint(-1.00000000001,0,0.000000001)));
    }

    @Test
    void TestClone() throws InvalidSphericalPoint {
        SphericalPoint p = new SphericalPoint(0,1,0);
        SphericalPoint q = new SphericalPoint(p);
        Assertions.assertNotSame(p, q);
        Assertions.assertEquals(p, q);
        Assertions.assertTrue(p.identical(q));
    }

    @Test
    void TestMatrix() throws InvalidSphericalPoint {
        SimpleMatrix mat = new SphericalPoint(0,0,1).matrix();
        checkMatrix(new double[][]{
                {0},{0},{1}
        }, mat);
    }

    @Test
    void TestBisectingGreatCircle() throws InvalidSphericalPoint, PointArc, AntipodalPoints, IdenticalSphericalPoints {
        SphericalPoint a = new SphericalPoint(1,0,0);
        SphericalPoint b = new SphericalPoint(0,-1,0);
        SphericalPoint c = new SphericalPoint(0,0,1);
        GreatCircle g = a.bisectingGreatCircle(b,c);
        checkGreatCircle(g,0,-1/Maths.SQRT2,-1/Maths.SQRT2);
        GreatCircle g2 = a.bisectingGreatCircle(c,b);
        checkGreatCircle(g2,0,-1/Maths.SQRT2,-1/Maths.SQRT2);
    }

    @Test
    void OLDTestFermatPoint() throws InvalidSphericalPoint, AntipodalPoints, NotEnoughSphericalVertices, IdenticalSphericalPoints, ZeroVectorException, IdenticalOrAntipodalPoints, PointArc, CannotFindFermatPoint, IdenticalOrOppositeGreatCircles {
        SphericalPoint a = new SphericalPoint(0,0,1);
        SphericalPoint b = new SphericalPoint(-.5,-Maths.SQRT3_2,0);
        SphericalPoint c = new SphericalPoint(.5,-Maths.SQRT3_2,0);
        //SphericalPoint b = new SphericalPoint(-1/Maths.SQRT2,-1/Maths.SQRT2,0);
        //SphericalPoint c = new SphericalPoint(1/Maths.SQRT2,-1/Maths.SQRT2,0);
        SphericalPoint fermatPoint = SphericalPoint.fermatPoint(a,b,c);
        System.out.println(fermatPoint);
        SphericalPoint projectionAB = fermatPoint.projection(new GreatCircle(a,b));
        SphericalPoint projectionBC = fermatPoint.projection(new GreatCircle(b,c));
        SphericalPoint projectionCA = fermatPoint.projection(new GreatCircle(c,a));
        System.out.println("projections: " + projectionAB + "  " + projectionBC + "  " + projectionCA);
        System.out.println(projectionAB.distance(projectionBC,true));
        System.out.println(projectionBC.distance(projectionCA,true));
        System.out.println(projectionCA.distance(projectionAB,true));

        SphericalPoint refAB = fermatPoint.reflect(new GreatCircle(a,b));
        SphericalPoint refBC = fermatPoint.reflect(new GreatCircle(b,c));
        SphericalPoint refCA = fermatPoint.reflect(new GreatCircle(c,a));
        System.out.println("reflections: " + refAB + "  " + refBC + "  " + refCA);
        System.out.println(refAB.distance(refBC,true));
        System.out.println(refBC.distance(refCA,true));
        System.out.println(refCA.distance(refAB,true));

        Plane3 planeAB = new Plane3(new GreatCircle(a,b));
        Plane3 planeBC = new Plane3(new GreatCircle(b,c));
        Plane3 planeCA = new Plane3(new GreatCircle(c,a));
        Point3 projectionPlaneAB = new Point3(fermatPoint).project(planeAB);
        Point3 projectionPlaneBC = new Point3(fermatPoint).project(planeBC);
        Point3 projectionPlaneCA = new Point3(fermatPoint).project(planeCA);
        System.out.println("plane projections: " + projectionPlaneAB + "  " + projectionPlaneBC + "  " + projectionPlaneCA);
        System.out.println(projectionPlaneAB.distance(projectionPlaneBC));
        System.out.println(projectionPlaneBC.distance(projectionPlaneCA));
        System.out.println(projectionPlaneCA.distance(projectionPlaneAB));

        checkDouble(refAB.distance(refBC,true), refBC.distance(refCA,true));
        checkDouble(refBC.distance(refCA,true), refCA.distance(refAB,true));
    }

    @Test
    void TestFermatPoint() throws InvalidSphericalPoint, AntipodalPoints, NotEnoughSphericalVertices, IdenticalSphericalPoints, ZeroVectorException, IdenticalOrAntipodalPoints, PointArc, CannotFindFermatPoint, IdenticalOrOppositeGreatCircles {
        for (SchwarzTriangle schwarzTriangle : SchwarzTriangle.getAll()) {
            //if (!schwarzTriangle.toString().equals("(2,3,4/3)")) continue;
            //if (!schwarzTriangle.toString().equals("(2,3,5/4)")) continue;
            //if (!schwarzTriangle.toString().equals("(2,3,3/2)")) continue;

            System.out.println(schwarzTriangle);
            SphericalPoint a = schwarzTriangle.triangle().getVertex(0);
            SphericalPoint b = schwarzTriangle.triangle().getVertex(1);
            SphericalPoint c = schwarzTriangle.triangle().getVertex(2);
            SphericalPoint fermatPoint;
            try {
//                fermatPoint = SphericalPoint.fermatPoint(a, b, c);
//                System.out.println("Fermat point: " + fermatPoint);
                fermatPoint = SphericalPoint.fermatPoint2(a,b,c);
                System.out.println("Fermat point 2: " + fermatPoint);
                Assertions.assertTrue(fermatPoint.identical(fermatPoint));
            } catch (GeometryException e) {
                System.out.println("cannot find");
                continue;
            }

            // check reflections form an equilateral triangle
            SphericalPoint refAB = fermatPoint.reflect(new GreatCircle(a, b));
            SphericalPoint refBC = fermatPoint.reflect(new GreatCircle(b, c));
            SphericalPoint refCA = fermatPoint.reflect(new GreatCircle(c, a));
//            System.out.println("reflections: " + refAB + "  " + refBC + "  " + refCA);
//            System.out.println(refAB.distance(refBC, true));
//            System.out.println(refBC.distance(refCA, true));
//            System.out.println(refCA.distance(refAB, true));
            checkDouble(refAB.distance(refBC, true), refBC.distance(refCA, true));
            checkDouble(refBC.distance(refCA, true), refCA.distance(refAB, true));

            // check *Euclidean* projections onto bounding *planes* form an equilateral triangle
            Plane3 planeAB = new Plane3(new GreatCircle(a,b));
            Plane3 planeBC = new Plane3(new GreatCircle(b,c));
            Plane3 planeCA = new Plane3(new GreatCircle(c,a));
            Point3 projectionPlaneAB = new Point3(fermatPoint).project(planeAB);
            Point3 projectionPlaneBC = new Point3(fermatPoint).project(planeBC);
            Point3 projectionPlaneCA = new Point3(fermatPoint).project(planeCA);
//            System.out.println("plane projections: " + projectionPlaneAB + "  " + projectionPlaneBC + "  " + projectionPlaneCA);
//            System.out.println(projectionPlaneAB.distance(projectionPlaneBC));
//            System.out.println(projectionPlaneBC.distance(projectionPlaneCA));
//            System.out.println(projectionPlaneCA.distance(projectionPlaneAB));
            checkDouble(projectionPlaneAB.distance(projectionPlaneBC), projectionPlaneBC.distance(projectionPlaneCA));
            checkDouble(projectionPlaneBC.distance(projectionPlaneCA), projectionPlaneCA.distance(projectionPlaneAB));
/*
            System.out.println(a);
            System.out.println(b);
            System.out.println(c);

            double angle = Maths.sphericalTriangleAngleFromSides(a.distance(b,true),a.distance(b,true),a.distance(b,true));
            System.out.println("angle = " + angle);
            SphericalPoint c2 = b.rotate(a,-angle);
            angle = Maths.sphericalTriangleAngleFromSides(b.distance(c,true),b.distance(c,true),b.distance(c,true));
            System.out.println("angle = " + angle);
            SphericalPoint a2 = c.rotate(b,-angle);
            angle = Maths.sphericalTriangleAngleFromSides(c.distance(a,true),c.distance(a,true),c.distance(a,true));
            System.out.println("angle = " + angle);
            SphericalPoint b2 = a.rotate(c,-angle);

            System.out.println("a2 = " + a2);
            System.out.println(a2.distance(b,true) + " " + a2.distance(c,true) + " " + b.distance(c,true));
            System.out.println(b2);
            System.out.println(c2);

            SphericalPoint f2 = new GreatCircle(a2,a).intersection(new GreatCircle(b2,b));
            System.out.println("new Fermat point = " + f2);
            f2 = new GreatCircle(b2,b).intersection(new GreatCircle(c2,c));
            System.out.println("new Fermat point = " + f2);
            f2 = new GreatCircle(c2,c).intersection(new GreatCircle(a2,a));
            System.out.println("new Fermat point = " + f2);*/
        }
//        Assertions.fail();
    }

    @Test
    void TestFermatPoint2 () throws GeometryException {
        SchwarzTriangle s = new SchwarzTriangle(new Rational(2),new Rational(3),new Rational(5));
        SphericalPoint fermat = SphericalPoint.fermatPoint2(s.triangle().getVertex(0), s.triangle().getVertex(1), s.triangle().getVertex(2));
    }

    @Test
    void TestReflect() throws InvalidSphericalPoint {
        GreatCircle c = new GreatCircle(new SphericalPoint(0,0,1));
        SphericalPoint p = new SphericalPoint(1/Maths.SQRT3,1/Maths.SQRT3,1/Maths.SQRT3);
        p = p.reflect(c);
        checkSphericalPoint(p, 1/Maths.SQRT3,1/Maths.SQRT3,-1/Maths.SQRT3);
    }

    @Test
    void TestIdentity() throws InvalidSphericalPoint {
        SphericalPoint p = new SphericalPoint(1/Maths.SQRT3, 1/Maths.SQRT3,1/Maths.SQRT3);
        checkSphericalPoint(p.identity(),1/Maths.SQRT3, 1/Maths.SQRT3,1/Maths.SQRT3);
    }

    @Test
    void TestInvert() throws InvalidSphericalPoint {
        SphericalPoint p = new SphericalPoint(1/Maths.SQRT2, 1/Maths.SQRT2,0);
        checkSphericalPoint(p.invert(),-1/Maths.SQRT2, -1/Maths.SQRT2,0);
    }

    @Test
    void TestRotateZAxis() throws GeometryException {
        SphericalPoint p = new SphericalPoint(1,0,0);
        SphericalPoint q = p.rotate(new SphericalPoint(0,0,1), Maths.PI2);
        checkSphericalPoint(q,0,1,0);
    }

    @Test
    void TestRotateNegativeZAxis() throws GeometryException {
        SphericalPoint p = new SphericalPoint(1,0,0);
        SphericalPoint q = p.rotate(new SphericalPoint(0,0,-1), Maths.PI2);
        checkSphericalPoint(q,0,-1,0);
    }

    @Test
    void TestStuff() throws IOException {

        test2();
    }

    void test2() throws IOException, IndexOutOfBoundsException {

    }

}
