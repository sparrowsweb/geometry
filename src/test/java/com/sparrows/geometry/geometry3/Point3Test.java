package com.sparrows.geometry.geometry3;

import com.sparrows.geometry.exception.GeometryException;
import com.sparrows.geometry.exception.ZeroVectorException;
import com.sparrows.geometry.transformation.d3.AffineTransformation3;
import com.sparrows.geometry.transformation.d3.LinearTransformation3;
import com.sparrows.geometry.transformation.Stretch3;
import com.sparrows.geometry.transformation.Translation3;
import com.sparrows.geometry.maths.Maths;
import org.ejml.simple.SimpleMatrix;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.sparrows.geometry.utils.TestUtils.checkDouble;
import static com.sparrows.geometry.utils.TestUtils.checkMatrix;
import static com.sparrows.geometry.utils.TestUtils.checkPoint3;

class Point3Test {

    @Test
    void TestDefaultConstructor() {
        Point3 p = new Point3();
        checkPoint3(p, 0,0,0);
    }

    @Test
    void TestConstructor() {
        Point3 p = new Point3(1,2,3);
        checkPoint3(p,1,2,3);
    }

    @Test
    void TestDistanceSquared() {
        Point3 p = new Point3(1,1,1);
        Point3 q = new Point3(2,3,4);
        checkDouble(14., p.distanceSquared(q));
    }

    @Test
    void TestDistance() {
        Point3 p = new Point3(1,1,1);
        Point3 q = new Point3(2,3,4);
        checkDouble(Math.sqrt(14), p.distance(q));
    }

    @Test
    void TestIdentical() {
        Point3 p = new Point3(1,2,3);
        Assertions.assertTrue(p.identical(new Point3(1,2,3)));
        Assertions.assertTrue(p.identical(new Point3(1,2.00000000001,3)));
        Assertions.assertTrue(p.identical(new Point3(1.00000000001,2,3)));
        Assertions.assertTrue(p.identical(new Point3(1,2,3.00000000001)));
        Assertions.assertFalse(p.identical(new Point3(1,2.000000001,3)));
        Assertions.assertFalse(p.identical(new Point3(1.000000001,2,3)));
        Assertions.assertFalse(p.identical(new Point3(1,2,3.000000001)));
    }

    @Test
    void TestClone() {
        Point3 p = new Point3(1,2,3);
        Point3 q = new Point3(p);
        Assertions.assertNotSame(p, q);
        Assertions.assertEquals(p, q);
        Assertions.assertTrue(p.identical(q));
    }

    @Test
    void TestBisect() {
        Point3 p = new Point3(1,2,3);
        Point3 q = new Point3(3, 5,7);
        Point3 r = p.midpoint(q);
        checkPoint3(r, 2, 3.5,5);
    }

    @Test
    void TestProjectYZ() throws ZeroVectorException {
        Point3 p = new Point3(1,2,3);
        Plane3 a = new Plane3(new Vector3(1,0,0),0);
        Point3 projection = p.project(a);
        checkPoint3(projection,0,2,3);
    }

    @Test
    void TestProjectZX() throws ZeroVectorException {
        Point3 p = new Point3(1,2,3);
        Plane3 a = new Plane3(new Vector3(0,1,0),0);
        Point3 projection = p.project(a);
        checkPoint3(projection,1,0,3);
    }

    @Test
    void TestProjectXY() throws ZeroVectorException {
        Point3 p = new Point3(1,2,3);
        Plane3 a = new Plane3(new Vector3(0,0,1),0);
        Point3 projection = p.project(a);
        checkPoint3(projection,1,2,0);
    }

    @Test
    void TestProject() throws ZeroVectorException {
        Point3 p = new Point3(0,0,0);
        Plane3 a = new Plane3(new Vector3(1,1,1),5);
        Point3 projection = p.project(a);
        checkPoint3(projection,5/Maths.SQRT3,5/Maths.SQRT3,5/Maths.SQRT3);
    }

    @Test
    void TestMatrix() {
        SimpleMatrix mat = new Point3(3,5,7).matrix();
        checkMatrix(new double[][]{
                {3},{5},{7}
        }, mat);
    }

    @Test
    void TestAugmentedMatrix() {
        SimpleMatrix mat = new Point3(3,5,7).augmentedMatrix();
        checkMatrix(new double[][]{
                {3},{5},{7},{1}
        }, mat);
    }

    @Test
    void TestLinearTransform() {
        Point3 p = new Point3(1,2,3);
        LinearTransformation3 l = new LinearTransformation3(new SimpleMatrix(new double[][]{{1,2,3},{4,5,6},{7,8,10}}));
        checkPoint3(p.linearTransform(l), 14,32,53);
    }

    @Test
    void TestIdentity() throws GeometryException {
        Point3 p = new Point3(1,2,3);
        Point3 q = p.identity();
        checkPoint3(q,1,2,3);
    }

    @Test
    void TestReflectOriginX0() throws GeometryException {
        Point3 p = new Point3(1,2,3);
        Point3 q = p.reflectOrigin(Vector3.X_UNIT);
        checkPoint3(q,-1,2,3);
    }

    @Test
    void TestReflectOriginY0() throws GeometryException {
        Point3 p = new Point3(1,2,3);
        Point3 q = p.reflectOrigin(Vector3.Y_UNIT);
        checkPoint3(q,1,-2,3);
    }

    @Test
    void TestReflectOriginZ0() throws GeometryException {
        Point3 p = new Point3(1,2,3);
        Point3 q = p.reflectOrigin(Vector3.Z_UNIT);
        checkPoint3(q,1,2,-3);
    }

    @Test
    void TestReflectOriginPlane() throws GeometryException {
        Point3 p = new Point3(1,2,3);
        Point3 q = p.reflectOrigin(new Plane3(new Vector3(1,1,1),0));
        checkPoint3(q,-3,-2,-1);
    }

    @Test
    void TestScale() throws GeometryException {
        Point3 p = new Point3(1,2,3);
        Point3 q = p.scale(4);
        checkPoint3(q,4,8,12);
    }

    @Test
    void TestStretch() throws GeometryException {
        Point3 p = new Point3(1,2,3);
        Point3 q = p.stretch(4, 6,8);
        checkPoint3(q,4,12,24);
    }

    @Test
    void TestReflect() throws ZeroVectorException {
        Point3 p = new Point3(1,2,3);
        p = p.reflect(new Plane3(Vector3.X_UNIT, 4));
        checkPoint3(p, 7,2,3);
    }

    @Test
    void TestRotateOriginZero() {
        Point3 p = new Point3(1,2,3);
        Assertions.assertThrows(IllegalArgumentException.class,() -> {
            p.rotateOrigin(Vector3.zero, Maths.PI2);
        });
    }

    @Test
    void TestRotateOriginXAxis() throws GeometryException {
        Point3 p = new Point3(1,2,3);
        Point3 q = p.rotateOrigin(Vector3.X_UNIT, Maths.PI2);
        checkPoint3(q,1,-3,2);
    }

    @Test
    void TestRotateOriginYAxis() throws GeometryException {
        Point3 p = new Point3(1,2,3);
        Point3 q = p.rotateOrigin(Vector3.Y_UNIT, Maths.PI2);
        checkPoint3(q,3,2,-1);
    }

    @Test
    void TestRotateOriginZAxis() throws GeometryException {
        Point3 p = new Point3(1,2,3);
        Point3 q = p.rotateOrigin(Vector3.Z_UNIT, Maths.PI2);
        checkPoint3(q,-2,1,3);
    }

    @Test
    void TestInvert() {
        Point3 p = new Point3(1,2,3);
        checkPoint3(p.invert(new Point3(9,9,9)),17,16,15);
    }

    @Test
    void TestInvertOrigin() {
        Point3 p = new Point3(1,2,3);
        checkPoint3(p.invertOrigin(),-1,-2,-3);
    }

    @Test
    void TestTranslate() {
        Point3 p = new Point3(1,2,3);
        Point3 q = p.translate(new Translation3(new Vector3(-1,-3, -5)));
        checkPoint3(q,0,-1,-2);
    }

    @Test
    void TestAffine() {
        AffineTransformation3 a = new AffineTransformation3(new Stretch3(2,3,4),new Translation3(-7,-8,-9));
        Point3 p = new Point3(1,2,3);
        Point3 q = p.affineTransform(a);
        checkPoint3(q,-5,-2,3);
    }
}
