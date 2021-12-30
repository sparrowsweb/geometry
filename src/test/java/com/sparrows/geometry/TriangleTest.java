package com.sparrows.geometry;

import com.sparrows.geometry.exception.triangle.*;
import com.sparrows.geometry.maths.Maths;
import com.sparrows.geometry.utils.Triangle;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static com.sparrows.geometry.utils.TestUtils.checkDouble;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TriangleTest {

    private final double ERROR_MARGIN = 0.00000000001;

    @Test
    void TestNaNSide () {
        Exception exception = assertThrows(TriangleSideNotANumberException.class, () -> {
            Triangle t = new Triangle(Double.NaN, 3., 4., Math.PI / 2, null, null);
        });
        assertEquals("Invalid triangle: Side 1 length is not a number (NaN).", exception.getMessage());
        exception = assertThrows(TriangleSideNotANumberException.class, () -> {
            Triangle t = new Triangle(2., Double.NaN, 4., Math.PI / 2, null, null);
        });
        assertEquals("Invalid triangle: Side 2 length is not a number (NaN).", exception.getMessage());
        exception = assertThrows(TriangleSideNotANumberException.class, () -> {
            Triangle t = new Triangle(2., 3., Double.NaN, Math.PI / 2, null, null);
        });
        assertEquals("Invalid triangle: Side 3 length is not a number (NaN).", exception.getMessage());
    }

    @Test
    void TestNegativeInfinitySide () {
        Exception exception = assertThrows(TriangleSideNotANumberException.class, () -> {
            Triangle t = new Triangle(Double.NEGATIVE_INFINITY, 3., 4., Math.PI / 2, null, null);
        });
        assertEquals("Invalid triangle: Side 1 length is not a number (-Infinity).", exception.getMessage());
        exception = assertThrows(TriangleSideNotANumberException.class, () -> {
            Triangle t = new Triangle(2., Double.NEGATIVE_INFINITY, 4., Math.PI / 2, null, null);
        });
        assertEquals("Invalid triangle: Side 2 length is not a number (-Infinity).", exception.getMessage());
        exception = assertThrows(TriangleSideNotANumberException.class, () -> {
            Triangle t = new Triangle(2., 3., Double.NEGATIVE_INFINITY, Math.PI / 2, null, null);
        });
        assertEquals("Invalid triangle: Side 3 length is not a number (-Infinity).", exception.getMessage());
    }


    @Test
    void TestPositiveInfinitySide () {
        Exception exception = assertThrows(TriangleSideNotANumberException.class, () -> {
            Triangle t = new Triangle(Double.POSITIVE_INFINITY, 3., 4., Math.PI / 2, null, null);
        });
        assertEquals("Invalid triangle: Side 1 length is not a number (Infinity).", exception.getMessage());
        exception = assertThrows(TriangleSideNotANumberException.class, () -> {
            Triangle t = new Triangle(2., Double.POSITIVE_INFINITY, 4., Math.PI / 2, null, null);
        });
        assertEquals("Invalid triangle: Side 2 length is not a number (Infinity).", exception.getMessage());
        exception = assertThrows(TriangleSideNotANumberException.class, () -> {
            Triangle t = new Triangle(4., 3., Double.POSITIVE_INFINITY, Math.PI / 2, null, null);
        });
        assertEquals("Invalid triangle: Side 3 length is not a number (Infinity).", exception.getMessage());
    }

    @Test
    void TestZeroSide () {
        Exception exception = assertThrows(TriangleInvalidSideException.class, () -> {
            Triangle t = new Triangle(0., 3., 4., Math.PI / 2, null, null);
        });
        assertEquals("Invalid triangle: Side 1 length (0.0) must be greater than zero.", exception.getMessage());
        exception = assertThrows(TriangleInvalidSideException.class, () -> {
            Triangle t = new Triangle(2., 0., 4., Math.PI / 2, null, null);
        });
        assertEquals("Invalid triangle: Side 2 length (0.0) must be greater than zero.", exception.getMessage());
        exception = assertThrows(TriangleInvalidSideException.class, () -> {
            Triangle t = new Triangle(2., 3., 0., Math.PI / 2, null, null);
        });
        assertEquals("Invalid triangle: Side 3 length (0.0) must be greater than zero.", exception.getMessage());
    }

    @Test
    void TestNegativeSide () {
        Exception exception = assertThrows(TriangleInvalidSideException.class, () -> {
            Triangle t = new Triangle(-1., 3., 4., Math.PI / 2, null, null);
        });
        assertEquals("Invalid triangle: Side 1 length (-1.0) must be greater than zero.", exception.getMessage());
        exception = assertThrows(TriangleInvalidSideException.class, () -> {
            Triangle t = new Triangle(2., -1., 4., Math.PI / 2, null, null);
        });
        assertEquals("Invalid triangle: Side 2 length (-1.0) must be greater than zero.", exception.getMessage());
        exception = assertThrows(TriangleInvalidSideException.class, () -> {
            Triangle t = new Triangle(2., 3., -1., Math.PI / 2, null, null);
        });
        assertEquals("Invalid triangle: Side 3 length (-1.0) must be greater than zero.", exception.getMessage());
    }

    @Test
    void TestSideTooLong () {
        Exception exception = assertThrows(TriangleInvalidSidesException.class, () -> {
            Triangle t = new Triangle(7., 3., 4., Math.PI / 2, null, null);
        });
        assertEquals("Invalid triangle: Side 1 length (7.0) must be less than the sum of the other two sides.", exception.getMessage());
        exception = assertThrows(TriangleInvalidSidesException.class, () -> {
            Triangle t = new Triangle(1., 6., 4., Math.PI / 2, null, null);
        });
        assertEquals("Invalid triangle: Side 2 length (6.0) must be less than the sum of the other two sides.", exception.getMessage());
        exception = assertThrows(TriangleInvalidSidesException.class, () -> {
            Triangle t = new Triangle(1., 3., 4., Math.PI / 2, null, null);
        });
        assertEquals("Invalid triangle: Side 3 length (4.0) must be less than the sum of the other two sides.", exception.getMessage());
    }

    @Test
    void TestNaNAngle () {
        Exception exception = assertThrows(TriangleAngleNotANumberException.class, () -> {
            Triangle t = new Triangle(2., 3., 4., Double.NaN, null, null);
        });
        assertEquals("Invalid triangle: Angle 1 value is not a number (NaN).", exception.getMessage());
        exception = assertThrows(TriangleAngleNotANumberException.class, () -> {
            Triangle t = new Triangle(2., 3., 4., null, Double.NaN, null);
        });
        assertEquals("Invalid triangle: Angle 2 value is not a number (NaN).", exception.getMessage());
        exception = assertThrows(TriangleAngleNotANumberException.class, () -> {
            Triangle t = new Triangle(2., 3., 4., null, null, Double.NaN);
        });
        assertEquals("Invalid triangle: Angle 3 value is not a number (NaN).", exception.getMessage());
    }

    @Test
    void TestNegativeInfinityAngle () {
        Exception exception = assertThrows(TriangleAngleNotANumberException.class, () -> {
            new Triangle(2., 3., 4., Double.NEGATIVE_INFINITY, null, null);
        });
        assertEquals("Invalid triangle: Angle 1 value is not a number (-Infinity).", exception.getMessage());
        exception = assertThrows(TriangleAngleNotANumberException.class, () -> {
            new Triangle(2., 3., 4., null, Double.NEGATIVE_INFINITY, null);
        });
        assertEquals("Invalid triangle: Angle 2 value is not a number (-Infinity).", exception.getMessage());
        exception = assertThrows(TriangleAngleNotANumberException.class, () -> {
            new Triangle(2., 3., 4., null, null, Double.NEGATIVE_INFINITY);
        });
        assertEquals("Invalid triangle: Angle 3 value is not a number (-Infinity).", exception.getMessage());
    }

    @Test
    void TestPositiveInfinityAngle () {
        Exception exception = assertThrows(TriangleAngleNotANumberException.class, () -> {
            new Triangle(2., 3., 4., Double.POSITIVE_INFINITY, null, null);
        });
        assertEquals("Invalid triangle: Angle 1 value is not a number (Infinity).", exception.getMessage());
        exception = assertThrows(TriangleAngleNotANumberException.class, () -> {
            new Triangle(2., 3., 4., null, Double.POSITIVE_INFINITY, null);
        });
        assertEquals("Invalid triangle: Angle 2 value is not a number (Infinity).", exception.getMessage());
        exception = assertThrows(TriangleAngleNotANumberException.class, () -> {
            new Triangle(2., 3., 4., null, null, Double.POSITIVE_INFINITY);
        });
        assertEquals("Invalid triangle: Angle 3 value is not a number (Infinity).", exception.getMessage());
    }

    @Test
    void TestZeroAngle () {
        Exception exception = assertThrows(TriangleInvalidAngleException.class, () -> {
            new Triangle(2., 3., 4., 0., null, null);
        });
        assertEquals("Invalid triangle: Angle 1 (0.0) must be greater than zero and less than π.", exception.getMessage());
        exception = assertThrows(TriangleInvalidAngleException.class, () -> {
            new Triangle(2., 3., 4., null, 0., null);
        });
        assertEquals("Invalid triangle: Angle 2 (0.0) must be greater than zero and less than π.", exception.getMessage());
        exception = assertThrows(TriangleInvalidAngleException.class, () -> {
            new Triangle(2., 3., 4., null, null, 0.);
        });
        assertEquals("Invalid triangle: Angle 3 (0.0) must be greater than zero and less than π.", exception.getMessage());
    }

    @Test
    void TestNegativeAngle () {
        Exception exception = assertThrows(TriangleInvalidAngleException.class, () -> {
            new Triangle(2., 3., 4., -0.1, null, null);
        });
        assertEquals("Invalid triangle: Angle 1 (-0.1) must be greater than zero and less than π.", exception.getMessage());
        exception = assertThrows(TriangleInvalidAngleException.class, () -> {
            new Triangle(2., 3., 4., null, -0.01, null);
        });
        assertEquals("Invalid triangle: Angle 2 (-0.01) must be greater than zero and less than π.", exception.getMessage());
        exception = assertThrows(TriangleInvalidAngleException.class, () -> {
            new Triangle(2., 3., 4., null, null, -0.0001);
        });
        assertEquals("Invalid triangle: Angle 3 (-1.0E-4) must be greater than zero and less than π.", exception.getMessage());
    }

    @Test
    void TestAngleTooLarge () {
        Exception exception = assertThrows(TriangleInvalidAngleException.class, () -> {
            new Triangle(2., 3., 4., Math.PI, null, null);
        });
        assertEquals("Invalid triangle: Angle 1 (3.141592653589793) must be greater than zero and less than π.", exception.getMessage());
        exception = assertThrows(TriangleInvalidAngleException.class, () -> {
            new Triangle(2., 3., 4., null, 3.1416, null);
        });
        assertEquals("Invalid triangle: Angle 2 (3.1416) must be greater than zero and less than π.", exception.getMessage());
        exception = assertThrows(TriangleInvalidAngleException.class, () -> {
            new Triangle(2., 3., 4., null, null, 99.);
        });
        assertEquals("Invalid triangle: Angle 3 (99.0) must be greater than zero and less than π.", exception.getMessage());
    }

    @Test
    void TestAngleSum () {
        Exception exception = assertThrows(TriangleInvalidAngleSumException.class, () -> {
            new Triangle(2., 3., 4., 1., 1., 1.1416);
        });
        assertEquals("Invalid triangle: Angles must sum to π (actual value 3.1416).", exception.getMessage());
        exception = assertThrows(TriangleInvalidAngleSumException.class, () -> {
            new Triangle(2., 3., 4., 1., 3., null);
        });
        assertEquals("Invalid triangle: Angles must sum to π (actual value 4.0).", exception.getMessage());
        exception = assertThrows(TriangleInvalidAngleSumException.class, () -> {
            new Triangle(2., 3., 4., 1., null, 3.);
        });
        assertEquals("Invalid triangle: Angles must sum to π (actual value 4.0).", exception.getMessage());
        exception = assertThrows(TriangleInvalidAngleSumException.class, () -> {
            new Triangle(2., 3., 4., null, 3., 1.);
        });
        assertEquals("Invalid triangle: Angles must sum to π (actual value 4.0).", exception.getMessage());
    }

    @Test
    void TestCosineRule () {
        double a = Triangle.cosineRule(3, 4, Maths.PI2);
        Assert.assertEquals(5., a, ERROR_MARGIN);
    }

    @Test
    void TestReverseCosineRule () {
        double angle = Triangle.reverseCosineRule(5, 4, 3);
        Assert.assertEquals(Maths.PI2, angle, ERROR_MARGIN);
    }

    @Test
    void TestSineRuleForSide () {
        double a = Triangle.sineRuleForSide(Maths.PI2, 1.0, Maths.PI4);
        Assert.assertEquals(Math.sqrt(2), a, ERROR_MARGIN);
    }

    @Test
    void TestSineRuleForAngle () throws TriangleInconsistentAngleSideSideException {
        double angleA = Triangle.sineRuleForAngle(Math.sqrt(3), Maths.PI6, 1);
        Assert.assertEquals(Maths.PI3, angleA, ERROR_MARGIN);
    }

    @Test
    void TestSineRuleForAngleError () {
        assertThrows(TriangleInconsistentAngleSideSideException.class, () -> {
            double angleA = Triangle.sineRuleForAngle(2, Maths.PI4, 1);
        });
    }

    @Test
    void TestNoData () {
        assertThrows(TriangleInsufficientDataException.class, () -> {
            new Triangle(null, null, null, null, null, null);
        });
    }

    @Test
    void TestOneSide () {
        assertThrows(TriangleInsufficientDataException.class, () -> {
            new Triangle(1., null, null, null, null, null);
        });
        assertThrows(TriangleInsufficientDataException.class, () -> {
            new Triangle(null, 2., null, null, null, null);
        });
        assertThrows(TriangleInsufficientDataException.class, () -> {
            new Triangle(null, null, 3., null, null, null);
        });
    }

    @Test
    void TestOneAngle () {
        assertThrows(TriangleInsufficientDataException.class, () -> {
            new Triangle(null, null, null, Maths.PI2, null, null);
        });
        assertThrows(TriangleInsufficientDataException.class, () -> {
            new Triangle(null, null, null, null, Maths.PI2, null);
        });
        assertThrows(TriangleInsufficientDataException.class, () -> {
            new Triangle(null, null, null, null, null, Maths.PI2);
        });
    }

    @Test
    void TestTwoSides () {
        assertThrows(TriangleInsufficientDataException.class, () -> {
            new Triangle(1., 2., null, null, null, null);
        });
        assertThrows(TriangleInsufficientDataException.class, () -> {
            new Triangle(null, 2., 3., null, null, null);
        });
        assertThrows(TriangleInsufficientDataException.class, () -> {
            new Triangle(1., null, 3., null, null, null);
        });
    }

    @Test
    void TestTwoAngles () {
        assertThrows(TriangleInsufficientDataException.class, () -> {
            new Triangle(null, null, null, Maths.PI2, Maths.PI4, null);
        });
        assertThrows(TriangleInsufficientDataException.class, () -> {
            new Triangle(null, null, null, null, Maths.PI2, Maths.PI4);
        });
        assertThrows(TriangleInsufficientDataException.class, () -> {
            new Triangle(null, null, null, Maths.PI4, null, Maths.PI2);
        });
    }

    @Test
    void TestSideAngle () {
        assertThrows(TriangleInsufficientDataException.class, () -> {
            new Triangle(1., null, null, Maths.PI4, null, null);
        });
        assertThrows(TriangleInsufficientDataException.class, () -> {
            new Triangle(1., null, null, null, Maths.PI4, null);
        });
        assertThrows(TriangleInsufficientDataException.class, () -> {
            new Triangle(1., null, null, null, null, Maths.PI2);
        });
        assertThrows(TriangleInsufficientDataException.class, () -> {
            new Triangle(null, 2., null, Maths.PI4, null, null);
        });
        assertThrows(TriangleInsufficientDataException.class, () -> {
            new Triangle(null, 2., null, null, Maths.PI4, null);
        });
        assertThrows(TriangleInsufficientDataException.class, () -> {
            new Triangle(null, 2., null, null, null, Maths.PI2);
        });
        assertThrows(TriangleInsufficientDataException.class, () -> {
            new Triangle(null, null, 3., Maths.PI4, null, null);
        });
        assertThrows(TriangleInsufficientDataException.class, () -> {
            new Triangle(null, null, 3., null, Maths.PI4, null);
        });
        assertThrows(TriangleInsufficientDataException.class, () -> {
            new Triangle(null, null, 3., null, null, Maths.PI2);
        });
    }

    @Test
    void TestThreeSides () throws TriangleException {
        Triangle t = new Triangle(2., Maths.SQRT3, 1., null, null, null);
        Triangle expected = new Triangle(2., Maths.SQRT3, 1., Maths.PI2, Maths.PI3, Maths.PI6);
        checkTriangle(expected, t);
    }

    @Test
    void TestSideAngleSide () throws TriangleException {
        Triangle triangle = new Triangle(null, 3., 4., Maths.PI2, null, null);
        Triangle expected = new Triangle(5., 3., 4., Maths.PI2, Math.asin(.6), Math.asin(.8));
        checkTriangle(expected, triangle);

        triangle = new Triangle(4., null, 3., null, Maths.PI2, null);
        expected = new Triangle(4., 5., 3., Math.asin(.8), Maths.PI2, Math.asin(.6));
        checkTriangle(expected, triangle);

        triangle = new Triangle(3., 4., null, null, null, Maths.PI2);
        expected = new Triangle(3., 4., 5., Math.asin(.6), Math.asin(.8), Maths.PI2);
        checkTriangle(expected, triangle);
    }

    @Test
    void TestAngleSideSide () throws TriangleException {
        Triangle t = new Triangle(2., 2., null, null, Maths.PI4, null);
        Triangle expected = new Triangle(2., 2., 2*Maths.SQRT2, Maths.PI4, Maths.PI4, Maths.PI2);
        checkTriangle(expected, t);

        t = new Triangle(null, 2., 2., null, null, Maths.PI4);
        expected = new Triangle(2*Maths.SQRT2, 2., 2., Maths.PI2, Maths.PI4, Maths.PI4);
        checkTriangle(expected, t);

        t = new Triangle(2., null, 2., Maths.PI4, null, null);
        expected = new Triangle(2., 2*Maths.SQRT2, 2., Maths.PI4, Maths.PI2, Maths.PI4);
        checkTriangle(expected, t);
    }

    @Test
    void TestAngleSideSideError () {
        assertThrows(TriangleInconsistentAngleSideSideException.class, () -> {
            new Triangle(3., 2., null, null, Maths.PI4, null);
        });
    }

    @Test
    void TestTwoAnglesOneSide () throws TriangleException {
        Triangle triangle = new Triangle(1., null, null, null, Maths.PI2, Maths.PI4);
        Triangle expected = new Triangle(1., Maths.SQRT2, 1., Maths.PI4, Maths.PI2, Maths.PI4);
        checkTriangle(expected, triangle);

        triangle = new Triangle(null, 1., null, Maths.PI4, null, Maths.PI2);
        expected = new Triangle(1., 1., Maths.SQRT2, Maths.PI4, Maths.PI4, Maths.PI2);
        checkTriangle(expected, triangle);

        triangle = new Triangle(null, null, 1., Maths.PI2, Maths.PI4, null);
        expected = new Triangle(Maths.SQRT2, 1., 1., Maths.PI2, Maths.PI4, Maths.PI4);
        checkTriangle(expected, triangle);

        triangle = new Triangle(1., null, null, Maths.PI4, Maths.PI2, null);
        expected = new Triangle(1., Maths.SQRT2, 1., Maths.PI4, Maths.PI2, Maths.PI4);
        checkTriangle(expected, triangle);

        triangle = new Triangle(null, 1., null, null, Maths.PI4, Maths.PI2);
        expected = new Triangle(1., 1., Maths.SQRT2, Maths.PI4, Maths.PI4, Maths.PI2);
        checkTriangle(expected, triangle);

        triangle = new Triangle(null, null, 1., Maths.PI2, null, Maths.PI4);
        expected = new Triangle(Maths.SQRT2, 1., 1., Maths.PI2, Maths.PI4, Maths.PI4);
        checkTriangle(expected, triangle);

        triangle = new Triangle(1., null, null, Maths.PI4, null, Maths.PI4);
        expected = new Triangle(1., Maths.SQRT2, 1., Maths.PI4, Maths.PI2, Maths.PI4);
        checkTriangle(expected, triangle);

        triangle = new Triangle(null, 1., null, Maths.PI4, Maths.PI4, null);
        expected = new Triangle(1., 1., Maths.SQRT2, Maths.PI4, Maths.PI4, Maths.PI2);
        checkTriangle(expected, triangle);

        triangle = new Triangle(null, 1., null, Maths.PI4, Maths.PI4, null);
        expected = new Triangle(1., 1., Maths.SQRT2, Maths.PI4, Maths.PI4, Maths.PI2);
        checkTriangle(expected, triangle);
    }

    @Test
    void TestThreeAngles () {
        assertThrows(TriangleInsufficientDataException.class, () -> new Triangle(null, null, null, Maths.PI2, Maths.PI3, Maths.PI6));
    }

    @Test
    void TestThreeSidesOneAngle () throws TriangleException {
        Triangle t = new Triangle(2., Maths.SQRT3, 1., Maths.PI2, null, null);
        Triangle expected = new Triangle(2., Maths.SQRT3, 1., Maths.PI2, Maths.PI3, Maths.PI6);
        checkTriangle(expected, t);

        t = new Triangle(2., Maths.SQRT3, 1., null, Maths.PI3, null);
        expected = new Triangle(2., Maths.SQRT3, 1., Maths.PI2, Maths.PI3, Maths.PI6);
        checkTriangle(expected, t);

        t = new Triangle(2., Maths.SQRT3, 1., null, null, Maths.PI6);
        expected = new Triangle(2., Maths.SQRT3, 1., Maths.PI2, Maths.PI3, Maths.PI6);
        checkTriangle(expected, t);
    }

    @Test
    void TestTwoSidesTwoAngles () throws TriangleException {
        Triangle t = new Triangle(2., Maths.SQRT3, null, Maths.PI2, Maths.PI3, null);
        Triangle expected = new Triangle(2., Maths.SQRT3, 1., Maths.PI2, Maths.PI3, Maths.PI6);
        checkTriangle(expected, t);

        t = new Triangle(2., Maths.SQRT3, null, null, Maths.PI3, Maths.PI6);
        expected = new Triangle(2., Maths.SQRT3, 1., Maths.PI2, Maths.PI3, Maths.PI6);
        checkTriangle(expected, t);

        t = new Triangle(2., Maths.SQRT3, null, Maths.PI2, null, Maths.PI6);
        expected = new Triangle(2., Maths.SQRT3, 1., Maths.PI2, Maths.PI3, Maths.PI6);
        checkTriangle(expected, t);

        t = new Triangle(null, Maths.SQRT3, 1., Maths.PI2, Maths.PI3, null);
        expected = new Triangle(2., Maths.SQRT3, 1., Maths.PI2, Maths.PI3, Maths.PI6);
        checkTriangle(expected, t);

        t = new Triangle(null, Maths.SQRT3, 1., null, Maths.PI3, Maths.PI6);
        expected = new Triangle(2., Maths.SQRT3, 1., Maths.PI2, Maths.PI3, Maths.PI6);
        checkTriangle(expected, t);

        t = new Triangle(null, Maths.SQRT3, 1., Maths.PI2, null, Maths.PI6);
        expected = new Triangle(2., Maths.SQRT3, 1., Maths.PI2, Maths.PI3, Maths.PI6);
        checkTriangle(expected, t);

        t = new Triangle(2., null, 1., Maths.PI2, Maths.PI3, null);
        expected = new Triangle(2., Maths.SQRT3, 1., Maths.PI2, Maths.PI3, Maths.PI6);
        checkTriangle(expected, t);

        t = new Triangle(2., null, 1., null, Maths.PI3, Maths.PI6);
        expected = new Triangle(2., Maths.SQRT3, 1., Maths.PI2, Maths.PI3, Maths.PI6);
        checkTriangle(expected, t);

        t = new Triangle(2., null, 1., Maths.PI2, null, Maths.PI6);
        expected = new Triangle(2., Maths.SQRT3, 1., Maths.PI2, Maths.PI3, Maths.PI6);
        checkTriangle(expected, t);
    }

    @Test
    void TestOneSideThreeAngles () throws TriangleException {
        Triangle t = new Triangle(2., null, null, Maths.PI2, Maths.PI3, Maths.PI6);
        Triangle expected = new Triangle(2., Maths.SQRT3, 1., Maths.PI2, Maths.PI3, Maths.PI6);
        checkTriangle(expected, t);

        t = new Triangle(null, Maths.SQRT3, null, Maths.PI2, Maths.PI3, Maths.PI6);
        expected = new Triangle(2., Maths.SQRT3, 1., Maths.PI2, Maths.PI3, Maths.PI6);
        checkTriangle(expected, t);

        t = new Triangle(null, null, 1., Maths.PI2, Maths.PI3, Maths.PI6);
        expected = new Triangle(2., Maths.SQRT3, 1., Maths.PI2, Maths.PI3, Maths.PI6);
        checkTriangle(expected, t);
    }

    @Test
    void TestThreeSidesTwoAngles () throws TriangleException {
        Triangle t = new Triangle(2., Maths.SQRT3, 1., Maths.PI2, Maths.PI3, null);
        Triangle expected = new Triangle(2., Maths.SQRT3, 1., Maths.PI2, Maths.PI3, Maths.PI6);
        checkTriangle(expected, t);

        t = new Triangle(2., Maths.SQRT3, 1., null, Maths.PI3, Maths.PI6);
        expected = new Triangle(2., Maths.SQRT3, 1., Maths.PI2, Maths.PI3, Maths.PI6);
        checkTriangle(expected, t);

        t = new Triangle(2., Maths.SQRT3, 1., Maths.PI2, null, Maths.PI6);
        expected = new Triangle(2., Maths.SQRT3, 1., Maths.PI2, Maths.PI3, Maths.PI6);
        checkTriangle(expected, t);
    }

    @Test
    void TestTwoSidesThreeAngles () throws TriangleException {
        Triangle t = new Triangle(2., Maths.SQRT3, null, Maths.PI2, Maths.PI3, Maths.PI6);
        Triangle expected = new Triangle(2., Maths.SQRT3, 1., Maths.PI2, Maths.PI3, Maths.PI6);
        checkTriangle(expected, t);

        t = new Triangle(2., null, 1., Maths.PI2, Maths.PI3, Maths.PI6);
        expected = new Triangle(2., Maths.SQRT3, 1., Maths.PI2, Maths.PI3, Maths.PI6);
        checkTriangle(expected, t);

        t = new Triangle(null, Maths.SQRT3, 1., Maths.PI2, Maths.PI3, Maths.PI6);
        expected = new Triangle(2., Maths.SQRT3, 1., Maths.PI2, Maths.PI3, Maths.PI6);
        checkTriangle(expected, t);
    }

    @Test
    void TestThreeSidesThreeAngles () throws TriangleException {
        Triangle t = new Triangle(2., Maths.SQRT3, 1., Maths.PI2, Maths.PI3, Maths.PI6);
        Triangle expected = new Triangle(2., Maths.SQRT3, 1., Maths.PI2, Maths.PI3, Maths.PI6);
        checkTriangle(expected, t);
    }

    private void checkTriangle (Triangle expected, Triangle actual) {
        checkDouble(expected.getSideA(), actual.getSideA());
        checkDouble(expected.getSideB(), actual.getSideB());
        checkDouble(expected.getSideC(), actual.getSideC());
        checkDouble(expected.getAngleA(), actual.getAngleA());
        checkDouble(expected.getAngleB(), actual.getAngleB());
        checkDouble(expected.getAngleC(), actual.getAngleC());
    }
}
