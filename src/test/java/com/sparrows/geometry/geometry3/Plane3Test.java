package com.sparrows.geometry.geometry3;

import com.sparrows.geometry.exception.GeometryException;
import com.sparrows.geometry.exception.ZeroVectorException;
import com.sparrows.geometry.transformation.d3.AffineTransformation3;
import com.sparrows.geometry.transformation.Stretch3;
import com.sparrows.geometry.transformation.Translation3;
import com.sparrows.geometry.maths.Maths;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.sparrows.geometry.utils.TestUtils.checkPlane3;

class Plane3Test {

    @Test
    void TestConstructor() {
        Plane3 a = new Plane3(new Vector3(1,2,3),4);
        checkPlane3(a, 1/Math.sqrt(14),2/Math.sqrt(14),3/Math.sqrt(14),4);
    }

    @Test
    void TestConstructorZero() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Plane3 a = new Plane3(new Vector3(0, 0, 0), -4);
        });
    }

    @Test
    void TestConstructorThreePoints() {
        Plane3 a = new Plane3(new Point3(3,0,0), new Point3(0,3,0),new Point3(0,0,3));
        checkPlane3(a, 1/Maths.SQRT3,1/Maths.SQRT3,1/Maths.SQRT3,Maths.SQRT3);
    }

    @Test
    void TestConstructorThreePointsCoincident() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Plane3(new Point3(1, 0, 0), new Point3(1, 0, 0), new Point3(0, 0, 1));
        });
    }

    @Test
    void TestConstructorThreePointsCollinear() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Plane3(new Point3(1, 0, 0), new Point3(2, 0, 0), new Point3(3, 0, 0));
        });
    }

    @Test
    void TestX0() {
        checkPlane3(Plane3.X_EQUALS_0, 1, 0, 0, 0);
    }

    @Test
    void TestY0() {
        checkPlane3(Plane3.Y_EQUALS_0, 0, 1, 0, 0);
    }

    @Test
    void TestZ0() {
        checkPlane3(Plane3.Z_EQUALS_0, 0, 0, 1, 0);
    }

    @Test
    void TestIdentical() {
        Plane3 a = new Plane3(Vector3.X_UNIT, 3);
        Plane3 b = new Plane3(Vector3.X_UNIT.reverse(), 3);
        Plane3 c = new Plane3(Vector3.X_UNIT, 2);
        Assertions.assertTrue(a.identical(a));
        Assertions.assertFalse(a.identical(b));
        Assertions.assertFalse(a.identical(c));
        Assertions.assertFalse(b.identical(a));
        Assertions.assertTrue(b.identical(b));
        Assertions.assertFalse(b.identical(c));
        Assertions.assertFalse(c.identical(a));
        Assertions.assertFalse(c.identical(b));
        Assertions.assertTrue(c.identical(c));
    }

    @Test
    void TestOpposite() {
        Plane3 a = new Plane3(Vector3.X_UNIT, 3);
        Plane3 b = new Plane3(Vector3.X_UNIT.reverse(), -3);
        Plane3 c = new Plane3(Vector3.X_UNIT, 2);
        Assertions.assertFalse(a.opposite(a));
        Assertions.assertTrue(a.opposite(b));
        Assertions.assertFalse(a.opposite(c));
        Assertions.assertTrue(b.opposite(a));
        Assertions.assertFalse(b.opposite(b));
        Assertions.assertFalse(b.opposite(c));
        Assertions.assertFalse(c.opposite(a));
        Assertions.assertFalse(c.opposite(b));
        Assertions.assertFalse(c.opposite(c));
    }

    @Test
    void TestIdenticalOrOpposite() {
        Plane3 a = new Plane3(Vector3.X_UNIT, 3);
        Plane3 b = new Plane3(Vector3.X_UNIT.reverse(), -3);
        Plane3 c = new Plane3(Vector3.X_UNIT, 2);
        Assertions.assertTrue(a.identicalOrOpposite(a));
        Assertions.assertTrue(a.identicalOrOpposite(b));
        Assertions.assertFalse(a.identicalOrOpposite(c));
        Assertions.assertTrue(b.identicalOrOpposite(a));
        Assertions.assertTrue(b.identicalOrOpposite(b));
        Assertions.assertFalse(b.identicalOrOpposite(c));
        Assertions.assertFalse(c.identicalOrOpposite(a));
        Assertions.assertFalse(c.identicalOrOpposite(b));
        Assertions.assertTrue(c.identicalOrOpposite(c));
    }

    @Test
    void TestClone() {
        Plane3 a = new Plane3(Vector3.X_UNIT,2);
        Plane3 b = new Plane3(a);
        Assertions.assertNotSame(a,b);
        Assertions.assertEquals(a,b);
        Assertions.assertTrue(a.identical(b));
    }

    @Test
    void TestContainsPoint() {
        Plane3 a = new Plane3(new Vector3(1,1,1),3);
        Point3 p = new Point3(Maths.SQRT3,Maths.SQRT3,Maths.SQRT3);
        Assertions.assertTrue(a.contains(p));

        a = new Plane3(Vector3.X_UNIT,2);
        p = new Point3(2,3,4);
        Assertions.assertTrue(a.contains(p));

        a = new Plane3(Vector3.X_UNIT,2);
        p = new Point3(2.1,3,4);
        Assertions.assertFalse(a.contains(p));
    }

    @Test
    void TestIdentity() throws GeometryException {
        Plane3 a = new Plane3(Vector3.X_UNIT,3);
        Plane3 b = a.identity();
        checkPlane3(b,1,0,0,3);
    }

    @Test
    void TestReflectOriginX0() throws GeometryException {
        Plane3 a = new Plane3(Vector3.X_UNIT,2);
        Plane3 b = a.reflectOrigin(Vector3.X_UNIT);
        checkPlane3(b,-1, 0, 0, 2);
    }

    @Test
    void TestReflectOriginY0() throws GeometryException {
        Plane3 a = new Plane3(Vector3.X_UNIT,2);
        Plane3 b = a.reflectOrigin(Vector3.Y_UNIT);
        checkPlane3(b,1, 0, 0, 2);
    }

    @Test
    void TestReflectOriginZ0() throws GeometryException {
        Plane3 a = new Plane3(Vector3.Z_UNIT,2);
        Plane3 b = a.reflectOrigin(Vector3.Z_UNIT);
        checkPlane3(b,0, 0, -1, 2);
    }

    @Test
    void TestScale() throws GeometryException {
        Plane3 a = new Plane3(Vector3.X_UNIT, 3);
        Plane3 b = a.scale(2);
        checkPlane3(b,1,0,0,6);
    }

    @Test
    void TestStretch() throws GeometryException {
        Plane3 a = new Plane3(Vector3.X_UNIT.reverse(), 5);
        Plane3 b = a.stretch(2,3,4);
        checkPlane3(b,-1,0,0,10);
    }

    @Test
    void TestRotateOriginZero() throws ZeroVectorException {
        Plane3 a = new Plane3(Vector3.Y_UNIT, 2);
        Assertions.assertThrows(IllegalArgumentException.class,() -> {
            a.rotateOrigin(Vector3.zero, Maths.PI2);
        });
    }

    @Test
    void TestRotateOriginXAxis() throws GeometryException {
        Plane3 a = new Plane3(Vector3.Y_UNIT, 2);
        Plane3 b = a.rotateOrigin(Vector3.X_UNIT,Maths.PI2);
        checkPlane3(b,0,0,1,2);
    }

    @Test
    void TestRotateOriginYAxis() throws GeometryException {
        Plane3 a = new Plane3(Vector3.Z_UNIT, 3);
        Plane3 b = a.rotateOrigin(Vector3.Y_UNIT,Math.PI);
        checkPlane3(b,0,0,-1,3);
    }

    @Test
    void TestRotateOriginZAxis() throws GeometryException {
        Plane3 a = new Plane3(Vector3.X_UNIT, -4);
        Plane3 b = a.rotateOrigin(Vector3.Z_UNIT,Maths.PI4);
        checkPlane3(b,1/Maths.SQRT2,1/Maths.SQRT2,0,-4);
    }

    @Test
    void TestInvert() throws GeometryException {
        Plane3 a = new Plane3(Vector3.Z_UNIT, -2);
        Plane3 b = a.invertOrigin();
        checkPlane3(b,0,0,-1,-2);
    }

    @Test
    void TestTranslate() throws ZeroVectorException {
        Plane3 a = new Plane3(Vector3.Y_UNIT, 1);
        Plane3 b = a.translate(new Vector3(1,2,3));
        checkPlane3(b,0,1,0,3);
    }

    @Test
    void TestAffine() throws GeometryException {
        AffineTransformation3 a = new AffineTransformation3(new Stretch3(2,3,4),new Translation3(-7,-8,-9));
        Plane3 b = new Plane3(Vector3.X_UNIT, 1);
        Plane3 c = b.affineTransform(a);
        checkPlane3(c,1,0,0,-5);
    }

}
