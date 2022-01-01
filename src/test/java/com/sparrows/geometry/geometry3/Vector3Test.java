package com.sparrows.geometry.geometry3;

import com.sparrows.geometry.exception.GeometryException;
import com.sparrows.geometry.exception.ZeroVectorException;
import com.sparrows.geometry.transformation.d3.AffineTransformation3;
import com.sparrows.geometry.transformation.Stretch3;
import com.sparrows.geometry.transformation.Translation3;
import com.sparrows.geometry.maths.Maths;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.sparrows.geometry.utils.TestUtils.checkDouble;
import static com.sparrows.geometry.utils.TestUtils.checkVector3;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Vector3Test {

    Vector3 V1 = new Vector3(0,0,0);
    Vector3 V2 = new Vector3(2,3,4);
    Vector3 V3 = new Vector3(-2,-3,-4);
    Vector3 V4 = new Vector3(4,6,8);
    Vector3 V5 = new Vector3(-3,2,0);

    @Test
    void TestZero() {
        checkVector3(Vector3.zero,0,0, 0);
    }

    @Test
    void TestXUnit() {
        checkVector3(Vector3.X_UNIT,1,0, 0);
    }

    @Test
    void TestYUnit() {
        checkVector3(Vector3.Y_UNIT,0,1, 0);
    }

    @Test
    void TestZUnit() {
        checkVector3(Vector3.Z_UNIT,0,0, 1);
    }

    @Test
    void TestDefaultConstructor() {
        Vector3 v = new Vector3();
        checkVector3(v,0,0, 0);
    }

    @Test
    void TestConstructor() {
        Vector3 v = new Vector3(1, 2, 3);
        checkVector3(v,1,2, 3);
    }

    @Test
    void TestPointPointConstructor() {
        Vector3 v = new Vector3(new Point3(1,2,3),new Point3(3,5,7));
        checkVector3(v, 2, 3,4);
    }

    @Test
    void TestLengthSquared() {
        Vector3 v = new Vector3(1, 2,3);
        checkDouble(14., v.lengthSquared());
    }

    @Test
    void TestLength() {
        Vector3 v = new Vector3(1, 3, 5);
        checkDouble(Math.sqrt(35), v.length());
    }

    @Test
    void TestUnit() throws ZeroVectorException {
        Vector3 v = new Vector3(1, 2, 3);
        Vector3 u = v.unit();
        checkVector3(u,1 / Math.sqrt(14), 2 / Math.sqrt(14), 3 / Math.sqrt(14));
    }

    @Test
    void TestUnit0() {
        Vector3 v = new Vector3(0, 0,0);
        Exception e = assertThrows(IllegalArgumentException.class, v::unit);
        assertEquals("Zero vector.", e.getMessage());
    }

    @Test
    void TestReverse() {
        checkVector3(new Vector3(1,2,3).reverse(), -1, -2,-3);
    }

    @Test
    void TestIdentical() {
        Vector3 v = new Vector3(1, 2,3);
        Assertions.assertTrue(v.identical(new Vector3(1, 2,3)));
        Assertions.assertTrue(v.identical(new Vector3(1, 2.00000000001,3)));
        Assertions.assertTrue(v.identical(new Vector3(1.00000000001, 2,3)));
        Assertions.assertTrue(v.identical(new Vector3(1, 2,3.00000000001)));
        Assertions.assertFalse(v.identical(new Vector3(1, 2.000000001,3)));
        Assertions.assertFalse(v.identical(new Vector3(1.000000001, 2,3)));
        Assertions.assertFalse(v.identical(new Vector3(1, 2,3.000000001)));
    }

    @Test
    void TestOpposite() {
        Vector3 v = new Vector3(1, 2,3);
        Assertions.assertTrue(v.opposite(new Vector3(-1, -2,-3)));
        Assertions.assertTrue(v.opposite(new Vector3(-1, -2.00000000001,-3)));
        Assertions.assertTrue(v.opposite(new Vector3(-1.00000000001, -2,-3)));
        Assertions.assertTrue(v.opposite(new Vector3(-1, -2,-3.00000000001)));
        Assertions.assertFalse(v.opposite(new Vector3(-1, -2.000000001,-3)));
        Assertions.assertFalse(v.opposite(new Vector3(-1.000000001, -2,-3)));
        Assertions.assertFalse(v.opposite(new Vector3(-1, -2,-3.000000001)));
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
        Assertions.assertThrows(IllegalArgumentException.class, () -> V1.sameDirection(V2));
        Assertions.assertThrows(IllegalArgumentException.class, () -> V1.sameDirection(V2));
        Assertions.assertThrows(IllegalArgumentException.class, () -> V1.sameDirection(V3));
        Assertions.assertThrows(IllegalArgumentException.class, () -> V1.sameDirection(V4));
        Assertions.assertThrows(IllegalArgumentException.class, () -> V1.sameDirection(V5));
        Assertions.assertThrows(IllegalArgumentException.class, () -> V2.sameDirection(V1));
        Assertions.assertTrue(V2.sameDirection(V2));
        Assertions.assertFalse(V2.sameDirection(V3));
        Assertions.assertTrue(V2.sameDirection(V4));
        Assertions.assertFalse(V2.sameDirection(V5));
    }

    @Test
    void TestOppositeDirection() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> V1.oppositeDirection(V1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> V1.oppositeDirection(V2));
        Assertions.assertThrows(IllegalArgumentException.class, () -> V1.oppositeDirection(V3));
        Assertions.assertThrows(IllegalArgumentException.class, () -> V1.oppositeDirection(V4));
        Assertions.assertThrows(IllegalArgumentException.class, () -> V1.oppositeDirection(V5));
        Assertions.assertThrows(IllegalArgumentException.class, () -> V2.oppositeDirection(V1));
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
        Vector3 v = new Vector3(1, 2,3);
        Vector3 u = new Vector3(v);
        Assertions.assertNotSame(v, u);
        assertEquals(v, u);
        Assertions.assertTrue(v.identical(u));
    }

    @Test
    void TestMultiply() {
        Vector3 v = new Vector3(1, 2,3);
        Vector3 u = v.multiply(5);
        checkVector3(u,5,10,15);
    }

    @Test
    void TestDivide() {
        Vector3 v = new Vector3(1, 2,3);
        Vector3 u = v.divide(5);
        checkVector3(u,.2,.4,.6);
    }

    @Test
    void TestAdd() {
        Vector3 v = new Vector3(1, 2,3).add(new Vector3(3,5,7));
        checkVector3(v,4,7,10);
    }

    @Test
    void TestSubtract() {
        Vector3 v = new Vector3(1, 2,3).subtract(new Vector3(3,5,7));
        checkVector3(v,-2,-3,-4);
    }

    @Test
    void TestDot() {
        Vector3 v = new Vector3(1,2,3);
        Vector3 u = new Vector3(-4,-5,6);
        checkDouble(4.,v.dot(u));
    }

    @Test
    void TestCross() {
        checkVector3(Vector3.X_UNIT.cross(Vector3.Y_UNIT),0,0,1);
        checkVector3(Vector3.Y_UNIT.cross(Vector3.Z_UNIT),1,0,0);
        checkVector3(Vector3.Z_UNIT.cross(Vector3.X_UNIT),0,1,0);
        checkVector3(Vector3.X_UNIT.cross(Vector3.Z_UNIT),0,-1,0);
        checkVector3(Vector3.Y_UNIT.cross(Vector3.X_UNIT),0,0,-1);
        checkVector3(Vector3.Z_UNIT.cross(Vector3.Y_UNIT),-1,0,0);
        checkVector3(Vector3.X_UNIT.cross(Vector3.X_UNIT),0,0,0);
        checkVector3(Vector3.Y_UNIT.cross(Vector3.Y_UNIT),0,0,0);
        checkVector3(Vector3.Z_UNIT.cross(Vector3.Z_UNIT),0,0,0);
    }

    @Test
    void TestAngle() throws ZeroVectorException {
        checkDouble(Math.PI, V2.angle(V3));
        checkDouble(0., V2.angle(V4));
        checkDouble(Maths.PI2, V2.angle(V5));
    }

    @Test
    void TestAngleZero() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            V1.angle(V2);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            V2.angle(V1);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            V1.angle(V1);
        });
    }

    @Test
    void TestRotateOriginZero() {
        Vector3 v = new Vector3(1,2,3);
        Assertions.assertThrows(IllegalArgumentException.class,() -> {
            v.rotateOrigin(Vector3.zero, Maths.PI2);
        });
    }

    @Test
    void TestRotateOriginXAxis() throws GeometryException {
        Vector3 v = new Vector3(1,2,3);
        Vector3 u = v.rotateOrigin(Vector3.X_UNIT, Maths.PI2);
        checkVector3(u,1,-3,2);
    }

    @Test
    void TestRotateOriginYAxis() throws GeometryException {
        Vector3 v = new Vector3(1,2,3);
        Vector3 u = v.rotateOrigin(Vector3.Y_UNIT, Maths.PI2);
        checkVector3(u,3,2,-1);
    }

    @Test
    void TestRotateOriginZAxis() throws GeometryException {
        Vector3 v = new Vector3(1,2,3);
        Vector3 u = v.rotateOrigin(Vector3.Z_UNIT, Maths.PI2);
        checkVector3(u,-2,1,3);
    }

    @Test
    void TestInvert() throws GeometryException {
        Vector3 v = new Vector3(1,2,3);
        checkVector3(v.invertOrigin(),-1,-2,-3);
    }

    @Test
    void TestScale() throws GeometryException {
        Vector3 v = new Vector3(1, 2,3);
        Vector3 u = v.scale(-2);
        checkVector3(u,-2,-4,-6);
    }

    @Test
    void TestStretch() throws GeometryException {
        Vector3 v = new Vector3(1,2,3);
        Vector3 u = v.stretch(4, 6,8);
        checkVector3(u,4,12,24);
    }

    @Test
    void TestTranslate() {
        Vector3 v = new Vector3(1,2,3);
        Vector3 u = v.translate(new Translation3(-1,-3,-5));
        checkVector3(u,1,2,3);
    }

    @Test
    void TestAffine() {
        AffineTransformation3 a = new AffineTransformation3(new Stretch3(2,3,4),new Translation3(-7,-8,-9));
        Vector3 v = new Vector3(1,2,3);
        Vector3 u = v.affineTransform(a);
        checkVector3(u,2,6,12);
    }
}