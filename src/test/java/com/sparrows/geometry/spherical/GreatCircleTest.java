package com.sparrows.geometry.spherical;

import static com.sparrows.geometry.utils.TestUtils.checkDouble;
import static com.sparrows.geometry.utils.TestUtils.checkGreatCircle;
import static com.sparrows.geometry.utils.TestUtils.checkSphericalPoint;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.sparrows.geometry.exception.AntipodalPoints;
import com.sparrows.geometry.exception.GeometryException;
import com.sparrows.geometry.exception.GreatCircleContainsArc;
import com.sparrows.geometry.exception.IdenticalOrOppositeGreatCircles;
import com.sparrows.geometry.exception.IdenticalSphericalPoints;
import com.sparrows.geometry.exception.InvalidSphericalPoint;
import com.sparrows.geometry.exception.PointArc;
import com.sparrows.geometry.maths.Maths;

class GreatCircleTest {

    @Test
    void TestConstructor() {
        GreatCircle c = new GreatCircle(SphericalPoint.northPole);
        checkGreatCircle(c,0,0,1);
    }

    @Test
    void TestPointsConstructor() throws InvalidSphericalPoint, AntipodalPoints, IdenticalSphericalPoints {
        GreatCircle c = new GreatCircle(SphericalPoint.northPole, new SphericalPoint(0,-1,0));
        checkGreatCircle(c,1,0,0);
    }

    @Test
    void TestPointsConstructorIdentical() {
        Assertions.assertThrows(IdenticalSphericalPoints.class,() -> new GreatCircle(SphericalPoint.northPole, SphericalPoint.northPole));
    }

    @Test
    void TestPointsConstructorAntipodal() {
        Assertions.assertThrows(AntipodalPoints.class,() -> new GreatCircle(SphericalPoint.northPole, SphericalPoint.southPole));
    }


    @Test
    void TestArcConstructor() throws InvalidSphericalPoint, AntipodalPoints, PointArc {
        GreatCircle c = new GreatCircle(new Arc(SphericalPoint.northPole, new SphericalPoint(0,-1,0),true));
        checkGreatCircle(c,1,0,0);
    }

    @Test
    void TestArcConstructorIdentical() throws PointArc, AntipodalPoints {
        Arc a = new Arc(SphericalPoint.northPole, SphericalPoint.northPole,true);
        Assertions.assertThrows(PointArc.class,() -> new GreatCircle(a));
    }

    @Test
    void TestArcConstructorAntipodal() {
        Assertions.assertThrows(AntipodalPoints.class,() -> new GreatCircle(SphericalPoint.northPole, SphericalPoint.southPole));
    }

    @Test
    void TestReverse() {
        GreatCircle c = new GreatCircle(SphericalPoint.northPole).reverse();
        checkGreatCircle(c,0,0,-1);
    }

    @Test
    void TestIdentical() throws InvalidSphericalPoint {
        GreatCircle c1 = new GreatCircle(new SphericalPoint(1,0,0));
        GreatCircle c2 = new GreatCircle(new SphericalPoint(1,0,0));
        Assertions.assertTrue(c1.identical(c2));
        Assertions.assertFalse(c1.opposite(c2));
        Assertions.assertTrue(c1.identicalOrOpposite(c2));
    }

    @Test
    void TestOpposite() throws InvalidSphericalPoint {
        GreatCircle c1 = new GreatCircle(new SphericalPoint(1,0,0));
        GreatCircle c2 = new GreatCircle(new SphericalPoint(-1,0,0));
        Assertions.assertFalse(c1.identical(c2));
        Assertions.assertTrue(c1.opposite(c2));
        Assertions.assertTrue(c1.identicalOrOpposite(c2));
    }

    @Test
    void TestNotIdentical() throws InvalidSphericalPoint {
        GreatCircle c1 = new GreatCircle(new SphericalPoint(1,0,0));
        GreatCircle c2 = new GreatCircle(new SphericalPoint(0,1,0));
        Assertions.assertFalse(c1.identical(c2));
        Assertions.assertFalse(c1.opposite(c2));
        Assertions.assertFalse(c1.identicalOrOpposite(c2));
    }

    @Test
    void TestClone() throws InvalidSphericalPoint {
        GreatCircle c1 = new GreatCircle(new SphericalPoint(1,0,0));
        GreatCircle c2 = new GreatCircle(c1);
        Assertions.assertNotSame(c1,c2);
        Assertions.assertEquals(c1,c2);
        Assertions.assertTrue(c1.identical(c2));
    }

    @Test
    void TestAnglePerpendicular() throws InvalidSphericalPoint {
        GreatCircle c1 = new GreatCircle(new SphericalPoint(1,0,0));
        GreatCircle c2 = new GreatCircle(new SphericalPoint(0,-1,0));
        checkDouble(Maths.PI2,c1.angle(c2));
        checkDouble(Maths.PI2,c2.angle(c1));
    }

    @Test
    void TestAngleZero() throws InvalidSphericalPoint {
        GreatCircle c1 = new GreatCircle(new SphericalPoint(1,0,0));
        GreatCircle c2 = new GreatCircle(c1);
        checkDouble(0.,c1.angle(c2));
        checkDouble(0.,c2.angle(c1));
    }

    @Test
    void TestAnglePi() throws InvalidSphericalPoint {
        GreatCircle c1 = new GreatCircle(new SphericalPoint(1,0,0));
        GreatCircle c2 = c1.reverse();
        checkDouble(Math.PI,c1.angle(c2));
        checkDouble(Math.PI,c2.angle(c1));
    }

    @Test
    void TestIntersectionIdentical() throws InvalidSphericalPoint {
        GreatCircle c1 = new GreatCircle(new SphericalPoint(1,0,0));
        GreatCircle c2 = new GreatCircle(new SphericalPoint(1,0,0));
        Assertions.assertThrows(IdenticalOrOppositeGreatCircles.class, () -> c1.intersection(c2));
    }

    @Test
    void TestIntersectionOpposite() {
        GreatCircle c1 = new GreatCircle(new SphericalPoint(1,0,0));
        GreatCircle c2 = new GreatCircle(new SphericalPoint(-1,0,0));
        Assertions.assertThrows(IdenticalOrOppositeGreatCircles.class, () -> c1.intersection(c2));
    }

    @Test
    void TestIntersection() throws InvalidSphericalPoint, IdenticalOrOppositeGreatCircles {
        GreatCircle c1 = new GreatCircle(new SphericalPoint(1,0,0));
        GreatCircle c2 = new GreatCircle(new SphericalPoint(0,1,0));
        checkSphericalPoint(c1.intersection(c2),0,0,1);
    }

    @Test
    void TestIntersection2() throws InvalidSphericalPoint, IdenticalOrOppositeGreatCircles {
        GreatCircle c1 = new GreatCircle(new SphericalPoint(1,0,0));
        GreatCircle c2 = new GreatCircle(new SphericalPoint(0,1,0));
        checkSphericalPoint(c2.intersection(c1),0,0,-1);
    }

    @Test
    void TestContainsTrue() throws InvalidSphericalPoint {
        GreatCircle c1 = new GreatCircle(new SphericalPoint(0,0,1));
        Assertions.assertTrue(c1.contains(new SphericalPoint(1,0,0)));
    }

    @Test
    void TestContainsFalse() throws InvalidSphericalPoint {
        GreatCircle c1 = new GreatCircle(new SphericalPoint(0,0,1));
        Assertions.assertFalse(c1.contains(new SphericalPoint(0,0,1)));
    }

    @Test
    void TestIntersectionArcZeroOnCircle() throws InvalidSphericalPoint, AntipodalPoints, PointArc, GreatCircleContainsArc {
        GreatCircle c = new GreatCircle(new SphericalPoint(1,0,0));
        Arc a = new Arc(new SphericalPoint(0,-1,0), new SphericalPoint(0,-1,0), true);
        List<SphericalPoint> intersections = c.intersections(a);
        Assertions.assertEquals(1,intersections.size());
        checkSphericalPoint(intersections.get(0),0,-1,0);
    }

    @Test
    void TestIntersectionArcZeroOffCircle() throws InvalidSphericalPoint, AntipodalPoints, PointArc, GreatCircleContainsArc {
        GreatCircle c = new GreatCircle(new SphericalPoint(1,0,0));
        Arc a = new Arc(new SphericalPoint(1,0,0), new SphericalPoint(1,0,0), true);
        List<SphericalPoint> intersections = c.intersections(a);
        Assertions.assertEquals(0,intersections.size());
    }

    @Test
    void TestIntersectionArcStartsCircle() throws InvalidSphericalPoint, AntipodalPoints, PointArc, GreatCircleContainsArc {
        GreatCircle c = new GreatCircle(new SphericalPoint(1,0,0));
        Arc a = new Arc(new SphericalPoint(0,-1,0), new SphericalPoint(1,0,0), true);
        List<SphericalPoint> intersections = c.intersections(a);
        Assertions.assertEquals(1,intersections.size());
        checkSphericalPoint(intersections.get(0),0,-1,0);
    }

    @Test
    void TestIntersectionArcEndsCircle() throws InvalidSphericalPoint, AntipodalPoints, PointArc, GreatCircleContainsArc {
        GreatCircle c = new GreatCircle(new SphericalPoint(1,0,0));
        Arc a = new Arc(new SphericalPoint(1,0,0), new SphericalPoint(0,-1,0), true);
        List<SphericalPoint> intersections = c.intersections(a);
        Assertions.assertEquals(1,intersections.size());
        checkSphericalPoint(intersections.get(0),0,-1,0);
    }

    @Test
    void TestIntersectionArcCrossesCircleOnce() throws InvalidSphericalPoint, AntipodalPoints, PointArc, GreatCircleContainsArc {
        GreatCircle c = new GreatCircle(new SphericalPoint(1,0,0));
        Arc a = new Arc(new SphericalPoint(-1/Maths.SQRT2,1/Maths.SQRT2,0), new SphericalPoint(1/Maths.SQRT2,1/Maths.SQRT2,0), true);
        List<SphericalPoint> intersections = c.intersections(a);
        Assertions.assertEquals(1,intersections.size());
        checkSphericalPoint(intersections.get(0),0,1,0);
    }

    @Test
    void TestIntersectionArcCrossesCircleTwice() throws InvalidSphericalPoint, AntipodalPoints, PointArc, GreatCircleContainsArc {
        GreatCircle c = new GreatCircle(new SphericalPoint(1,0,0));
        Arc a = new Arc(new SphericalPoint(-1/Maths.SQRT2,1/Maths.SQRT2,0), new SphericalPoint(-1/Maths.SQRT2,-1/Maths.SQRT2,0), false);
        List<SphericalPoint> intersections = c.intersections(a);
        Assertions.assertEquals(2,intersections.size());
        SphericalPoint expected1 = new SphericalPoint(0,1,0);
        SphericalPoint expected2 = new SphericalPoint(0,-1,0);
        Assertions.assertTrue(intersections.get(0).identical(expected1) && intersections.get(1).identical(expected2) ||
                intersections.get(0).identical(expected2) && intersections.get(1).identical(expected1));
    }

    @Test
    void TestIntersectionArcOffCircle() throws InvalidSphericalPoint, AntipodalPoints, PointArc, GreatCircleContainsArc {
        GreatCircle c = new GreatCircle(new SphericalPoint(1,0,0));
        Arc a = new Arc(new SphericalPoint(-1/Maths.SQRT2,1/Maths.SQRT2,0), new SphericalPoint(-1/Maths.SQRT2,-1/Maths.SQRT2,0), true);
        List<SphericalPoint> intersections = c.intersections(a);
        Assertions.assertEquals(0,intersections.size());
    }

    @Test
    void TestIntersectionArcWithinCircle() throws InvalidSphericalPoint, AntipodalPoints, PointArc {
        GreatCircle c = new GreatCircle(new SphericalPoint(1,0,0));
        Arc a = new Arc(new SphericalPoint(0,1/Maths.SQRT2,1/Maths.SQRT2), new SphericalPoint(0,-1/Maths.SQRT2,1/Maths.SQRT2), true);
        Assertions.assertThrows(GreatCircleContainsArc.class, () -> c.intersections(a));
    }

    @Test
    void TestIntersectionPointArcWithinCircle() throws InvalidSphericalPoint, AntipodalPoints, PointArc, GreatCircleContainsArc {
        GreatCircle c = new GreatCircle(new SphericalPoint(1,0,0));
        Arc a = new Arc(new SphericalPoint(0,1/Maths.SQRT2,1/Maths.SQRT2), new SphericalPoint(0,1/Maths.SQRT2,1/Maths.SQRT2), true);
        List<SphericalPoint> intersections = c.intersections(a);
        Assertions.assertEquals(1,intersections.size());
        checkSphericalPoint(intersections.get(0),0,1/Maths.SQRT2,1/Maths.SQRT2);
    }

    @Test
    void TestInvert() throws InvalidSphericalPoint {
        GreatCircle c1 = new GreatCircle(new SphericalPoint(1,0,0));
        GreatCircle c2 = c1.invert();
        checkGreatCircle(c2, -1,0,0);
    }

    @Test
    void TestRotateZAxis() throws GeometryException {
        GreatCircle c1 = new GreatCircle(new SphericalPoint(0,1,0));
        GreatCircle c2 = c1.rotate(SphericalPoint.northPole,Maths.PI2);
        checkGreatCircle(c2, -1,0,0);
    }

    @Test
    void TestRotateNegativeZAxis() throws GeometryException {
        GreatCircle c1 = new GreatCircle(new SphericalPoint(0,1,0));
        GreatCircle c2 = c1.rotate(SphericalPoint.southPole,Maths.PI2);
        checkGreatCircle(c2, 1,0,0);
    }

}
