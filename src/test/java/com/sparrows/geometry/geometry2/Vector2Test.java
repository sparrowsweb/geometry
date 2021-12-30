package com.sparrows.geometry.geometry2;

import com.sparrows.geometry.exception.ZeroVectorException;
import com.sparrows.geometry.transformation.AffineTransformation2;
import com.sparrows.geometry.transformation.Stretch2;
import com.sparrows.geometry.transformation.Translation2;
import com.sparrows.geometry.maths.Maths;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.sparrows.geometry.utils.TestUtils.checkVector2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Vector2Test {

    private final double ERROR_MARGIN = 0.00000000001;
    Vector2 V1 = new Vector2(0,0);
    Vector2 V2 = new Vector2(2,3);
    Vector2 V3 = new Vector2(-2,-3);
    Vector2 V4 = new Vector2(4,6);
    Vector2 V5 = new Vector2(-3,2);

    @Test
    void TestZero() {
        checkVector2(Vector2.zero,0,0);
    }

    @Test
    void TestXUnit() {
        checkVector2(Vector2.xUnit,1,0);
    }

    @Test
    void TestYUnit() {
        checkVector2(Vector2.yUnit,0,1);
    }

    @Test
    void TestDefaultConstructor() {
        Vector2 v = new Vector2();
        checkVector2(v,0,0);
    }

    @Test
    void TestConstructor() {
        Vector2 v = new Vector2(1, 2);
        checkVector2(v,1,2);
    }

    @Test
    void TestPointPointConstructor() {
        Vector2 v = new Vector2(new Point2(1,2),new Point2(3,5));
        checkVector2(v, 2, 3);
    }

    @Test
    void TestLengthSquared() {
        Vector2 v = new Vector2(1, 2);
        assertEquals(5, v.lengthSquared(), ERROR_MARGIN);
    }

    @Test
    void TestLength() {
        Vector2 v = new Vector2(1, 3);
        assertEquals(Math.sqrt(10), v.length(), ERROR_MARGIN);
    }

    @Test
    void TestUnit() throws ZeroVectorException {
        Vector2 v = new Vector2(1, 2);
        Vector2 u = v.unit();
        checkVector2(u,1 / Math.sqrt(5), 2 / Math.sqrt(5));
    }

    @Test
    void TestUnit0() {
        Vector2 v = new Vector2(0, 0);
        Exception e = assertThrows(ZeroVectorException.class, v::unit);
        assertEquals("Vector is zero.", e.getMessage());
    }

    @Test
    void TestReverse() {
        checkVector2(new Vector2(1,2).reverse(), -1, -2);
    }

    @Test
    void TestIdentical() {
        Vector2 v = new Vector2(1, 2);
        Assertions.assertTrue(v.identical(new Vector2(1, 2)));
        Assertions.assertTrue(v.identical(new Vector2(1, 2.00000000001)));
        Assertions.assertTrue(v.identical(new Vector2(1.00000000001, 2)));
        Assertions.assertFalse(v.identical(new Vector2(1, 2.000000001)));
        Assertions.assertFalse(v.identical(new Vector2(1.000000001, 2)));
    }

    @Test
    void TestOpposite() {
        Vector2 v = new Vector2(1, 2);
        Assertions.assertTrue(v.opposite(new Vector2(-1, -2)));
        Assertions.assertTrue(v.opposite(new Vector2(-1, -2.00000000001)));
        Assertions.assertTrue(v.opposite(new Vector2(-1.00000000001, -2)));
        Assertions.assertFalse(v.opposite(new Vector2(-1, -2.000000001)));
        Assertions.assertFalse(v.opposite(new Vector2(-1.000000001, -2)));
    }

    @Test
    void TestParallel() {
        Assertions.assertTrue(V1.parallel(V1));
        Assertions.assertTrue(V1.parallel(V2));
        Assertions.assertTrue(V1.parallel(V3));
        Assertions.assertTrue(V1.parallel(V4));
        Assertions.assertTrue(V1.parallel(V5));
        Assertions.assertTrue(V2.parallel(V1));
        Assertions.assertTrue(V2.parallel(V2));
        Assertions.assertTrue(V2.parallel(V3));
        Assertions.assertTrue(V2.parallel(V4));
        Assertions.assertFalse(V2.parallel(V5));
    }

    @Test
    void TestSameDirection() {
        Assertions.assertTrue(V1.sameDirection(V1));
        Assertions.assertTrue(V1.sameDirection(V2));
        Assertions.assertTrue(V1.sameDirection(V3));
        Assertions.assertTrue(V1.sameDirection(V4));
        Assertions.assertTrue(V1.sameDirection(V5));
        Assertions.assertTrue(V2.sameDirection(V1));
        Assertions.assertTrue(V2.sameDirection(V2));
        Assertions.assertFalse(V2.sameDirection(V3));
        Assertions.assertTrue(V2.sameDirection(V4));
        Assertions.assertFalse(V2.sameDirection(V5));
    }

    @Test
    void TestOppositeDirection() {
        Assertions.assertTrue(V1.oppositeDirection(V1));
        Assertions.assertTrue(V1.oppositeDirection(V2));
        Assertions.assertTrue(V1.oppositeDirection(V3));
        Assertions.assertTrue(V1.oppositeDirection(V4));
        Assertions.assertTrue(V1.oppositeDirection(V5));
        Assertions.assertTrue(V2.oppositeDirection(V1));
        Assertions.assertFalse(V2.oppositeDirection(V2));
        Assertions.assertTrue(V2.oppositeDirection(V3));
        Assertions.assertFalse(V2.oppositeDirection(V4));
        Assertions.assertFalse(V2.oppositeDirection(V5));
    }
    @Test
    void TestPerpendicular() {
        Assertions.assertTrue(V1.perpendicular(V1));
        Assertions.assertTrue(V1.perpendicular(V2));
        Assertions.assertTrue(V1.perpendicular(V3));
        Assertions.assertTrue(V1.perpendicular(V4));
        Assertions.assertTrue(V1.perpendicular(V5));
        Assertions.assertTrue(V2.perpendicular(V1));
        Assertions.assertFalse(V2.perpendicular(V2));
        Assertions.assertFalse(V2.perpendicular(V3));
        Assertions.assertFalse(V2.perpendicular(V4));
        Assertions.assertTrue(V2.perpendicular(V5));
    }

    @Test
    void TestClone() {
        Vector2 v = new Vector2(1, 2);
        Vector2 u = new Vector2(v);
        Assertions.assertNotSame(v, u);
        assertEquals(v, u);
        Assertions.assertTrue(v.identical(u));
    }

    @Test
    void TestMultiply() {
        Vector2 v = new Vector2(1, 2);
        Vector2 u = v.multiply(5);
        checkVector2(u,5,10);
    }

    @Test
    void TestDivide() {
        Vector2 v = new Vector2(1, 2);
        Vector2 u = v.divide(5);
        checkVector2(u,.2,.4);
    }

    @Test
    void TestAdd() {
        Vector2 v = new Vector2(1, 2).add(new Vector2(3,5));
        checkVector2(v,4,7);
    }

    @Test
    void TestSubtract() {
        Vector2 v = new Vector2(1, 2).subtract(new Vector2(3,5));
        checkVector2(v,-2,-3);
    }

    @Test
    void TestAngle() throws ZeroVectorException {
        Assertions.assertEquals(Math.PI, V2.angle(V3), ERROR_MARGIN);
        Assertions.assertEquals(0, V2.angle(V4), ERROR_MARGIN);
        Assertions.assertEquals(Maths.PI2, V2.angle(V5), ERROR_MARGIN);
    }

    @Test
    void TestAngleZero() {
        Assertions.assertThrows(ZeroVectorException.class, () -> {
            V1.angle(V2);
        });
        Assertions.assertThrows(ZeroVectorException.class, () -> {
            V2.angle(V1);
        });
        Assertions.assertThrows(ZeroVectorException.class, () -> {
            V1.angle(V1);
        });
    }

    @Test
    void TestPositiveAngle() throws ZeroVectorException {
        Assertions.assertEquals(0, Vector2.xUnit.positiveAngle(Vector2.xUnit));
        Assertions.assertEquals(0, Vector2.yUnit.positiveAngle(Vector2.yUnit));
        Assertions.assertEquals(0, Vector2.xUnit.negate().positiveAngle(Vector2.xUnit.negate()));
        Assertions.assertEquals(0, Vector2.yUnit.negate().positiveAngle(Vector2.yUnit.negate()));

        Assertions.assertEquals(Maths.PI2, Vector2.xUnit.positiveAngle(Vector2.yUnit));
        Assertions.assertEquals(Maths.PI2, Vector2.yUnit.positiveAngle(Vector2.xUnit.negate()));
        Assertions.assertEquals(Maths.PI2, Vector2.xUnit.negate().positiveAngle(Vector2.yUnit.negate()));
        Assertions.assertEquals(Maths.PI2, Vector2.yUnit.negate().positiveAngle(Vector2.xUnit));

        Assertions.assertEquals(Math.PI, Vector2.xUnit.positiveAngle(Vector2.xUnit.negate()));
        Assertions.assertEquals(Math.PI, Vector2.yUnit.positiveAngle(Vector2.yUnit.negate()));
        Assertions.assertEquals(Math.PI, Vector2.xUnit.negate().positiveAngle(Vector2.xUnit));
        Assertions.assertEquals(Math.PI, Vector2.yUnit.negate().positiveAngle(Vector2.yUnit));

        Assertions.assertEquals(-Maths.PI2, Vector2.xUnit.positiveAngle(Vector2.yUnit.negate()));
        Assertions.assertEquals(-Maths.PI2, Vector2.yUnit.negate().positiveAngle(Vector2.xUnit.negate()));
        Assertions.assertEquals(-Maths.PI2, Vector2.xUnit.negate().positiveAngle(Vector2.yUnit));
        Assertions.assertEquals(-Maths.PI2, Vector2.yUnit.positiveAngle(Vector2.xUnit));
    }
    @Test
    void TestPositiveAngleZero() {
        Assertions.assertThrows(ZeroVectorException.class, () -> {
            Vector2.zero.positiveAngle(Vector2.xUnit);
        });
        Assertions.assertThrows(ZeroVectorException.class, () -> {
            Vector2.yUnit.positiveAngle(Vector2.zero);
        });
        Assertions.assertThrows(ZeroVectorException.class, () -> {
            Vector2.zero.positiveAngle(Vector2.zero);
        });
    }

    @Test
    void TestExpand() {
        Vector2 v = new Vector2(1, 2);
        Vector2 u = v.scale(-2);
        checkVector2(u,-2,-4);
    }

    @Test
    void TestRotate() {
        Vector2 v = new Vector2(3,5);
        Vector2 u = v.rotate(new Point2(1,2),Maths.PI2);
        checkVector2(u,-5,3);
    }

    @Test
    void TestStretch() {
        Vector2 v = new Vector2(1,2);
        Vector2 u = v.stretch(4, 6);
        checkVector2(u,4,12);
    }

    @Test
    void TestRotateOrigin() {
        Vector2 v = new Vector2(1,2);
        Vector2 u = v.rotateOrigin(Math.PI);
        checkVector2(u,-1,-2);
    }

    @Test
    void TestTranslate() {
        Vector2 v = new Vector2(1,2);
        Vector2 u = v.translate(new Translation2(-1,-3));
        checkVector2(u,1,2);
    }

    @Test
    void TestAffine() {
        AffineTransformation2 a = new AffineTransformation2(new Stretch2(2,3),new Translation2(-7,-8));
        Vector2 v = new Vector2(1,2);
        Vector2 u = v.affineTransform(a);
        checkVector2(u,2,6);
    }
}