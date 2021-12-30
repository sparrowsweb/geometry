package com.sparrows.geometry.spherical;

import static com.sparrows.geometry.utils.TestUtils.checkArc;
import static com.sparrows.geometry.utils.TestUtils.checkDouble;
import static com.sparrows.geometry.utils.TestUtils.checkSphericalPoint;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.sparrows.geometry.exception.AntipodalPoints;
import com.sparrows.geometry.exception.GeometryException;
import com.sparrows.geometry.exception.GreatCircleContainsArc;
import com.sparrows.geometry.exception.IdenticalSphericalPoints;
import com.sparrows.geometry.exception.InvalidSphericalArc;
import com.sparrows.geometry.exception.InvalidSphericalPoint;
import com.sparrows.geometry.exception.OverlappingArcs;
import com.sparrows.geometry.exception.PointArc;
import com.sparrows.geometry.maths.Maths;

class ArcTest {

    @Test
    void TestTypeConstructorIdenticalMinor() throws InvalidSphericalPoint, AntipodalPoints, PointArc {
        Arc a = new Arc(new SphericalPoint(0,0,1),new SphericalPoint(0,0,1),true);
        checkArc(a,0,0,1,0,0,1,Arc.ArcType.MINOR,0,0,1);
    }

    @Test
    void TestTypeConstructorIdenticalMajor() {
        Assertions.assertThrows(PointArc.class,
                () -> new Arc(new SphericalPoint(0,0,1),new SphericalPoint(0,0,1),false));
    }

    @Test
    void TestTypeConstructorAntipodalMinor() {
        Assertions.assertThrows(AntipodalPoints.class,
                () -> new Arc(new SphericalPoint(0,0,1),new SphericalPoint(0,0,-1),true));
    }

    @Test
    void TestTypeConstructorAntipodalMajor() {
        Assertions.assertThrows(AntipodalPoints.class,
                () -> new Arc(new SphericalPoint(0,0,1),new SphericalPoint(0,0,-1),false));
    }

    @Test
    void TestTypeConstructorMinor() throws InvalidSphericalPoint, AntipodalPoints, PointArc {
        Arc a = new Arc(new SphericalPoint(0,0,1),new SphericalPoint(0,1,0),true);
        checkArc(a,0,0,1,0,1,0,Arc.ArcType.MINOR,0,1/Maths.SQRT2,1/Maths.SQRT2);
    }

    @Test
    void TestTypeConstructorMajor() throws InvalidSphericalPoint, AntipodalPoints, PointArc {
        Arc a = new Arc(new SphericalPoint(0,0,1),new SphericalPoint(0,1,0),false);
        checkArc(a,0,0,1,0,1,0,Arc.ArcType.MAJOR,0,-1/Maths.SQRT2,-1/Maths.SQRT2);
    }

    @Test
    void TestMidpointConstructorIdentical() throws InvalidSphericalPoint, InvalidSphericalArc {
        Arc a = new Arc(new SphericalPoint(0,1,0),new SphericalPoint(0,1,0),new SphericalPoint(0,1,0));
        checkArc(a,0,1,0,0,1,0,Arc.ArcType.MINOR,0,1,0);
    }

    @Test
    void TestMidpointConstructorIdenticalFail() {
        Assertions.assertThrows(InvalidSphericalArc.class, () ->
            new Arc(new SphericalPoint(0,1,0),new SphericalPoint(0,1,0),new SphericalPoint(0,0,1)));
    }

    @Test
    void TestMidpointConstructorAntipodal() throws InvalidSphericalPoint, InvalidSphericalArc {
        Arc a = new Arc(new SphericalPoint(-1,0,0),new SphericalPoint(1,0,0),new SphericalPoint(0,1,0));
        checkArc(a,-1,0,0,1,0,0,Arc.ArcType.MERIDIAN,0,1,0);
    }

    @Test
    void TestMidpointConstructorAntipodalFail() {
        Assertions.assertThrows(InvalidSphericalArc.class, () ->
                new Arc(new SphericalPoint(-1,0,0),new SphericalPoint(1,0,0),new SphericalPoint(1/Maths.SQRT2,1/Maths.SQRT2,0)));
    }

    @Test
    void TestMidpointConstructorMinor() throws InvalidSphericalPoint, InvalidSphericalArc {
        Arc a = new Arc(new SphericalPoint(-1,0,0),new SphericalPoint(0,-1,0),new SphericalPoint(-1/Maths.SQRT2,-1/Maths.SQRT2,0));
        checkArc(a,-1,0,0,0,-1,0,Arc.ArcType.MINOR,-1/Maths.SQRT2,-1/Maths.SQRT2,0);
    }

    @Test
    void TestMidpointConstructorMajor() throws InvalidSphericalPoint, InvalidSphericalArc {
        Arc a = new Arc(new SphericalPoint(-1,0,0),new SphericalPoint(0,-1,0),new SphericalPoint(1/Maths.SQRT2,1/Maths.SQRT2,0));
        checkArc(a,-1,0,0,0,-1,0,Arc.ArcType.MAJOR,1/Maths.SQRT2,1/Maths.SQRT2,0);
    }

    @Test
    void TestMidpointConstructorFail() {
        Assertions.assertThrows(InvalidSphericalArc.class, () ->
                new Arc(new SphericalPoint(-1,0,0),new SphericalPoint(0,-1,0),new SphericalPoint(0,0,1)));
    }

    @Test
    void TestCopyConstructor() throws InvalidSphericalPoint, InvalidSphericalArc {
        Arc a = new Arc(new SphericalPoint(-1,0,0),new SphericalPoint(0,-1,0),new SphericalPoint(1/Maths.SQRT2,1/Maths.SQRT2,0));
        Arc b = new Arc(a);
        checkArc(b,-1,0,0,0,-1,0,Arc.ArcType.MAJOR,1/Maths.SQRT2,1/Maths.SQRT2,0);
        Assertions.assertNotSame(a,b);
        Assertions.assertEquals(a,b);
        Assertions.assertTrue(a.identical(b));
    }

    @Test
    void TestIdentical() throws InvalidSphericalPoint, AntipodalPoints, PointArc {
        Arc a = new Arc(new SphericalPoint(0,0,1),new SphericalPoint(0,1,0),true);
        Arc b = new Arc(new SphericalPoint(0,0,1),new SphericalPoint(0,1,0),true);
        Assertions.assertTrue(a.identical(b));
        Assertions.assertFalse(a.opposite(b));
        Assertions.assertTrue(a.identicalOrOpposite(b));
    }

    @Test
    void TestOpposite() throws InvalidSphericalPoint, AntipodalPoints, PointArc {
        Arc a = new Arc(new SphericalPoint(0,0,1),new SphericalPoint(0,1,0),true);
        Arc b = new Arc(new SphericalPoint(0,1,0),new SphericalPoint(0,0,1),true);
        Assertions.assertFalse(a.identical(b));
        Assertions.assertTrue(a.opposite(b));
        Assertions.assertTrue(a.identicalOrOpposite(b));
    }

    @Test
    void TestNotIdentical() throws InvalidSphericalPoint, AntipodalPoints, PointArc {
        Arc a = new Arc(new SphericalPoint(0,0,1),new SphericalPoint(0,1,0),true);
        Arc b = new Arc(new SphericalPoint(0,0,1),new SphericalPoint(1,0,0),true);
        Assertions.assertFalse(a.identical(b));
        Assertions.assertFalse(a.opposite(b));
        Assertions.assertFalse(a.identicalOrOpposite(b));
    }

    @Test
    void TestLengthZero() throws InvalidSphericalPoint, AntipodalPoints, PointArc {
        Arc a = new Arc(new SphericalPoint(0,0,1),new SphericalPoint(0,0,1),true);
        checkDouble(0., a.length());
    }

    @Test
    void TestLengthMinor() throws InvalidSphericalPoint, AntipodalPoints, PointArc {
        Arc a = new Arc(new SphericalPoint(0,0,1),new SphericalPoint(0,1,0),true);
        checkDouble(Maths.PI2, a.length());
    }

    @Test
    void TestLengthMajor() throws InvalidSphericalPoint, AntipodalPoints, PointArc {
        Arc a = new Arc(new SphericalPoint(0,0,1),new SphericalPoint(0,1,0),false);
        checkDouble(3*Maths.PI2, a.length());
    }

    @Test
    void TestLengthMeridian() throws InvalidSphericalPoint, InvalidSphericalArc {
        Arc a = new Arc(new SphericalPoint(0,0,1),new SphericalPoint(0,0,-1),new SphericalPoint(0,1,0));
        checkDouble(Math.PI, a.length());
    }

    @Test
    void TestReverse() throws InvalidSphericalPoint, AntipodalPoints, PointArc {
        Arc a = new Arc(new SphericalPoint(0,0,1),new SphericalPoint(0,1,0),true);
        Arc b = a.reverse();
        checkArc(b,0,1,0,0,0,1, Arc.ArcType.MINOR,0,1/Maths.SQRT2,1/Maths.SQRT2);
    }

    @Test
    void TestComplement() throws InvalidSphericalPoint, AntipodalPoints, PointArc, InvalidSphericalArc {
        Arc a = new Arc(new SphericalPoint(0,0,1),new SphericalPoint(0,1,0),true);
        Arc b = a.complement();
        checkArc(b,0,0,1,0,1,0, Arc.ArcType.MAJOR,0,-1/Maths.SQRT2,-1/Maths.SQRT2);
    }

    @Test
    void TestComplementZero() throws InvalidSphericalPoint, AntipodalPoints, PointArc {
        Arc a = new Arc(new SphericalPoint(0,0,1),new SphericalPoint(0,0,1),true);
        Assertions.assertThrows(InvalidSphericalArc.class, a::complement);
    }

    @Test
    void TestComplementMeridian() throws InvalidSphericalPoint, InvalidSphericalArc {
        Arc a = new Arc(new SphericalPoint(0,0,1),new SphericalPoint(0,0,-1),new SphericalPoint(1,0,0));
        Arc b = a.complement();
        checkArc(b,0,0,1,0,0,-1, Arc.ArcType.MERIDIAN,-1,0,0);
    }

    @Test
    void TestAngleIdentical() throws InvalidSphericalPoint, AntipodalPoints, PointArc, PointArc {
        Arc a = new Arc(new SphericalPoint(0,0,1),new SphericalPoint(0,1,0),true);
        Arc b = new Arc(a);
        checkDouble(0.,a.angle(b));
    }

    @Test
    void TestAngleReverse() throws InvalidSphericalPoint, AntipodalPoints, PointArc, PointArc {
        Arc a = new Arc(new SphericalPoint(0,0,1),new SphericalPoint(0,1,0),true);
        Arc b = a.reverse();
        checkDouble(Math.PI,a.angle(b));
    }

    @Test
    void TestAnglePoint() throws InvalidSphericalPoint, AntipodalPoints, PointArc {
        Arc a = new Arc(new SphericalPoint(0,0,1),new SphericalPoint(0,0,1),true);
        Arc b = new Arc(new SphericalPoint(0,0,1),new SphericalPoint(0,1,0),true);
        Assertions.assertThrows(PointArc.class, () -> a.angle(b));
    }

    @Test
    void TestContainsMinor() throws InvalidSphericalPoint, AntipodalPoints, PointArc {
        Arc a = new Arc(new SphericalPoint(1,0,0),new SphericalPoint(0,1,0),true);
        Assertions.assertTrue(a.contains(new SphericalPoint(1,0,0)));
        Assertions.assertTrue(a.contains(new SphericalPoint(1/Maths.SQRT2,1/Maths.SQRT2,0)));
        Assertions.assertTrue(a.contains(new SphericalPoint(0,1,0)));
        Assertions.assertFalse(a.contains(new SphericalPoint(-1/Maths.SQRT2,1/Maths.SQRT2,0)));
        Assertions.assertFalse(a.contains(new SphericalPoint(-1,0,0)));
        Assertions.assertFalse(a.contains(new SphericalPoint(-1/Maths.SQRT2,-1/Maths.SQRT2,0)));
        Assertions.assertFalse(a.contains(new SphericalPoint(0,-1,0)));
        Assertions.assertFalse(a.contains(new SphericalPoint(1/Maths.SQRT2,-1/Maths.SQRT2,0)));

        Assertions.assertFalse(a.contains(new SphericalPoint(0,0,1)));
        Assertions.assertFalse(a.contains(new SphericalPoint(0,0,-1)));
    }

    @Test
    void TestContainsMajor() throws InvalidSphericalPoint, AntipodalPoints, PointArc {
        Arc a = new Arc(new SphericalPoint(1,0,0),new SphericalPoint(0,1,0),false);
        Assertions.assertTrue(a.contains(new SphericalPoint(1,0,0)));
        Assertions.assertFalse(a.contains(new SphericalPoint(1/Maths.SQRT2,1/Maths.SQRT2,0)));
        Assertions.assertTrue(a.contains(new SphericalPoint(0,1,0)));
        Assertions.assertTrue(a.contains(new SphericalPoint(-1/Maths.SQRT2,1/Maths.SQRT2,0)));
        Assertions.assertTrue(a.contains(new SphericalPoint(-1,0,0)));
        Assertions.assertTrue(a.contains(new SphericalPoint(-1/Maths.SQRT2,-1/Maths.SQRT2,0)));
        Assertions.assertTrue(a.contains(new SphericalPoint(0,-1,0)));
        Assertions.assertTrue(a.contains(new SphericalPoint(1/Maths.SQRT2,-1/Maths.SQRT2,0)));

        Assertions.assertFalse(a.contains(new SphericalPoint(0,0,1)));
        Assertions.assertFalse(a.contains(new SphericalPoint(0,0,-1)));
    }

    @Test
    void TestIntersectionPointArcAndPointArcSame() throws InvalidSphericalPoint, AntipodalPoints, PointArc, OverlappingArcs {
        Arc a1 = new Arc(new SphericalPoint(0,-1,0), new SphericalPoint(0,-1,0), true);
        Arc a2 = new Arc(new SphericalPoint(0,-1,0), new SphericalPoint(0,-1,0), true);
        List<SphericalPoint> intersections = a1.intersections(a2);
        Assertions.assertEquals(1,intersections.size());
        checkSphericalPoint(intersections.get(0),0,-1,0);
        intersections = a2.intersections(a1);
        Assertions.assertEquals(1,intersections.size());
        checkSphericalPoint(intersections.get(0),0,-1,0);
    }

    @Test
    void TestIntersectionPointArcAndPointArcDifferent() throws InvalidSphericalPoint, AntipodalPoints, PointArc, OverlappingArcs {
        Arc a1 = new Arc(new SphericalPoint(0,-1,0), new SphericalPoint(0,-1,0), true);
        Arc a2 = new Arc(new SphericalPoint(0,1,0), new SphericalPoint(0,1,0), true);
        List<SphericalPoint> intersections = a1.intersections(a2);
        Assertions.assertEquals(0,intersections.size());
        intersections = a2.intersections(a1);
        Assertions.assertEquals(0,intersections.size());
    }

    @Test
    void TestIntersectionPointArcOnCircleOnArc() throws InvalidSphericalPoint, AntipodalPoints, PointArc, OverlappingArcs {
        Arc a1 = new Arc(new SphericalPoint(0,-1,0), new SphericalPoint(1,0,0), true);
        Arc a2 = new Arc(new SphericalPoint(0,-1,0), new SphericalPoint(0,-1,0), true);
        List<SphericalPoint> intersections = a1.intersections(a2);
        Assertions.assertEquals(1,intersections.size());
        checkSphericalPoint(intersections.get(0),0,-1,0);
        intersections = a2.intersections(a1);
        Assertions.assertEquals(1,intersections.size());
        checkSphericalPoint(intersections.get(0),0,-1,0);
    }

    @Test
    void TestIntersectionPointArcOnCircleOffArc() throws InvalidSphericalPoint, AntipodalPoints, PointArc, OverlappingArcs {
        Arc a1 = new Arc(new SphericalPoint(0,-1,0), new SphericalPoint(1,0,0), true);
        Arc a2 = new Arc(new SphericalPoint(0,1,0), new SphericalPoint(0,1,0), true);
        List<SphericalPoint> intersections = a1.intersections(a2);
        Assertions.assertEquals(0,intersections.size());
        intersections = a2.intersections(a1);
        Assertions.assertEquals(0,intersections.size());
    }

    @Test
    void TestIntersectionPointArcOffCircle() throws InvalidSphericalPoint, AntipodalPoints, PointArc, OverlappingArcs {
        Arc a1 = new Arc(new SphericalPoint(0,-1,0), new SphericalPoint(1,0,0), true);
        Arc a2 = new Arc(new SphericalPoint(0,0,1), new SphericalPoint(0,0,1), true);
        List<SphericalPoint> intersections = a1.intersections(a2);
        Assertions.assertEquals(0,intersections.size());
        intersections = a2.intersections(a1);
        Assertions.assertEquals(0,intersections.size());
    }

    @Test
    void TestIntersectionArcEndsSame() throws InvalidSphericalPoint, AntipodalPoints, PointArc, OverlappingArcs {
        Arc a1 = new Arc(new SphericalPoint(0,-1,0), new SphericalPoint(1,0,0), true);
        Arc a2 = new Arc(new SphericalPoint(1,0,0), new SphericalPoint(0,0,1), true);
        List<SphericalPoint> intersections = a1.intersections(a2);
        Assertions.assertEquals(1,intersections.size());
        checkSphericalPoint(intersections.get(0),1,0,0);
        intersections = a2.intersections(a1);
        Assertions.assertEquals(1,intersections.size());
        checkSphericalPoint(intersections.get(0),1,0,0);
    }

    @Test
    void TestIntersectionArcCrossOnce() throws InvalidSphericalPoint, AntipodalPoints, PointArc, OverlappingArcs, InvalidSphericalArc {
        Arc a1 = new Arc(new SphericalPoint(1,0,0), new SphericalPoint(-1,0,0), new SphericalPoint(0,1,0));
        Arc a2 = new Arc(new SphericalPoint(0,0,1), new SphericalPoint(0,0,-1), new SphericalPoint(0,1,0));
        List<SphericalPoint> intersections = a1.intersections(a2);
        Assertions.assertEquals(1,intersections.size());
        checkSphericalPoint(intersections.get(0),0,1,0);
        intersections = a2.intersections(a1);
        Assertions.assertEquals(1,intersections.size());
        checkSphericalPoint(intersections.get(0),0,1,0);
    }

    @Test
    void TestIntersectionArcCrossTwice() throws InvalidSphericalPoint, AntipodalPoints, PointArc, OverlappingArcs, InvalidSphericalArc {
        Arc a1 = new Arc(new SphericalPoint(1,0,0), new SphericalPoint(-1,0,0), new SphericalPoint(0,1,0));
        Arc a2 = new Arc(new SphericalPoint(1,0,0), new SphericalPoint(-1,0,0), new SphericalPoint(0,0,1));
        SphericalPoint i1 = new SphericalPoint(1,0,0);
        SphericalPoint i2 = new SphericalPoint(-1,0,0);
        List<SphericalPoint> intersections = a1.intersections(a2);
        Assertions.assertEquals(2,intersections.size());
        Assertions.assertTrue(intersections.get(0).identical(i1) && intersections.get(1).identical(i2) ||
                intersections.get(0).identical(i2) && intersections.get(1).identical(i1));
        intersections = a2.intersections(a1);
        Assertions.assertEquals(2,intersections.size());
        Assertions.assertTrue(intersections.get(0).identical(i1) && intersections.get(1).identical(i2) ||
                intersections.get(0).identical(i2) && intersections.get(1).identical(i1));
    }

    @Test
    void TestIntersectionArcSameGreatCircle() throws InvalidSphericalPoint, AntipodalPoints, PointArc {
        Arc a1 = new Arc(new SphericalPoint(1,0,0), new SphericalPoint(0,1,0), true);
        Arc a2 = new Arc(new SphericalPoint(1,0,0), new SphericalPoint(0,-1,0), true);
        Assertions.assertThrows(OverlappingArcs.class, () -> a1.intersections(a2));
    }

    @Test
    void TestReflectMinor() throws InvalidSphericalPoint, AntipodalPoints, PointArc {
        GreatCircle c = new GreatCircle(new SphericalPoint(0,0,1));
        Arc a = new Arc(new SphericalPoint(0,0,1),new SphericalPoint(0,1,0),true);
        Arc b = a.reflect(c);
        checkArc(b, 0,0,-1,0,1,0, Arc.ArcType.MINOR,0,1/Maths.SQRT2,-1/Maths.SQRT2);
    }

    @Test
    void TestIdentityMinor() throws InvalidSphericalPoint, AntipodalPoints, PointArc {
        Arc a = new Arc(new SphericalPoint(0,0,1),new SphericalPoint(0,1,0),true);
        Arc b = a.identity();
        checkArc(b, 0,0,1,0,1,0, Arc.ArcType.MINOR,0,1/Maths.SQRT2,1/Maths.SQRT2);
    }

    @Test
    void TestInvertMinor() throws InvalidSphericalPoint, AntipodalPoints, PointArc {
        Arc a = new Arc(new SphericalPoint(0,0,1),new SphericalPoint(0,1,0),true);
        Arc b = a.invert();
        checkArc(b, 0,0,-1,0,-1,0, Arc.ArcType.MINOR,0,-1/Maths.SQRT2,-1/Maths.SQRT2);
    }

    @Test
    void TestRotateZAxisMinor() throws GeometryException {
        Arc a = new Arc(new SphericalPoint(0,0,1),new SphericalPoint(0,1,0),true);
        Arc b = a.rotate(SphericalPoint.northPole,Maths.PI2);
        checkArc(b, 0,0,1,-1,0,0, Arc.ArcType.MINOR,-1/Maths.SQRT2,0,1/Maths.SQRT2);
    }

    @Test
    void TestRotateNegativeZAxis() throws GeometryException {
        Arc a = new Arc(new SphericalPoint(0,0,1),new SphericalPoint(0,1,0),true);
        Arc b = a.rotate(SphericalPoint.southPole,Maths.PI2);
        checkArc(b, 0,0,1,1,0,0, Arc.ArcType.MINOR,1/Maths.SQRT2,0,1/Maths.SQRT2);
    }

}
