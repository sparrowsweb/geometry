package com.sparrows.geometry.geometry2;

import com.sparrows.geometry.exception.ZeroVectorException;
import com.sparrows.geometry.transformation.AffineTransformation2;
import com.sparrows.geometry.transformation.Stretch2;
import com.sparrows.geometry.transformation.Translation2;
import com.sparrows.geometry.maths.Maths;
import org.ejml.simple.SimpleMatrix;
import org.junit.jupiter.api.Assertions;

import static com.sparrows.geometry.utils.TestUtils.checkDouble;
import static com.sparrows.geometry.utils.TestUtils.checkMatrix;
import static com.sparrows.geometry.utils.TestUtils.checkPoint2;

import org.junit.jupiter.api.Test;

class Point2Test {

    @Test
    void TestDefaultConstructor() {
        Point2 p = new Point2();
        checkPoint2(p, 0,0);
    }

    @Test
    void TestConstructor() {
        Point2 p = new Point2(1,2);
        checkPoint2(p,1,2);
    }

    @Test
    void TestDistanceSquared() {
        Point2 p = new Point2(1,1);
        Point2 q = new Point2(2,3);
        checkDouble(5., p.distanceSquared(q));
    }

    @Test
    void TestDistance() {
        Point2 p = new Point2(1,1);
        Point2 q = new Point2(2,5);
        checkDouble(Math.sqrt(17), p.distance(q));
    }

    @Test
    void TestIdentical() {
        Point2 p = new Point2(1,2);
        Assertions.assertTrue(p.identical(new Point2(1,2)));
        Assertions.assertTrue(p.identical(new Point2(1,2.00000000001)));
        Assertions.assertTrue(p.identical(new Point2(1.00000000001,2)));
        Assertions.assertFalse(p.identical(new Point2(1,2.000001)));
        Assertions.assertFalse(p.identical(new Point2(1.00001,2)));
    }

    @Test
    void TestClone() {
        Point2 p = new Point2(1,2);
        Point2 q = new Point2(p);
        Assertions.assertNotSame(p, q);
        Assertions.assertEquals(p, q);
        Assertions.assertTrue(p.identical(q));
    }

    @Test
    void TestBisect() {
        Point2 p = new Point2(1,2);
        Point2 q = new Point2(3, 5);
        Point2 r = p.bisect(q);
        checkPoint2(r, 2, 3.5);
    }

    @Test
    void TestMatrix() {
        SimpleMatrix mat = new Point2(3,5).matrix();
        checkMatrix(new double[][]{
                {3},{5}
        }, mat);
    }

    @Test
    void TestAugmentedMatrix() {
        SimpleMatrix mat = new Point2(3,5).augmentedMatrix();
        checkMatrix(new double[][]{
                {3},{5},{1}
        }, mat);
    }

    @Test
    void TestExpand() {
        Point2 p = new Point2(1,2);
        Point2 q = p.scale(4);
        checkPoint2(q,4,8);
    }

    @Test
    void TestIdentity() {
        Point2 p = new Point2(1,2);
        Point2 q = p.identity();
        checkPoint2(q,1,2);
    }

    @Test
    void TestInvert() {
        Point2 p = new Point2(1,2);
        Point2 q = p.invert();
        checkPoint2(q,-1,-2);
    }

    @Test
    void TestReflectOriginAngle() {
        Point2 p = new Point2(1,2);
        Point2 q = p.reflectOrigin(Maths.PI4);
        checkPoint2(q,2,1);
    }

    @Test
    void TestReflectOriginLine() throws ZeroVectorException {
        Point2 p = new Point2(1,2);
        Point2 q = p.reflectOrigin(new Line2(Point2.origin, new Vector2(1,-1)));
        checkPoint2(q,-2,-1);
    }

    @Test
    void TestReflect() throws ZeroVectorException {
        Point2 p = new Point2(1,2);
        p = p.reflect(new Line2(new Point2(3,3),new Vector2(1,-1)));
        checkPoint2(p, 4,5);
    }

    @Test
    void TestRotate() {
        Point2 p = new Point2(3,5);
        Point2 q = p.rotate(new Point2(1,2),Maths.PI2);
        checkPoint2(q,-2,4);
    }

    @Test
    void TestRotateOrigin() {
        Point2 p = new Point2(1,2);
        Point2 q = p.rotateOrigin(Math.PI);
        checkPoint2(q,-1,-2);
    }

    @Test
    void TestStretch() {
        Point2 p = new Point2(1,2);
        Point2 q = p.stretch(4, 6);
        checkPoint2(q,4,12);
    }
    @Test
    void TestTranslate() {
        Point2 p = new Point2(1,2);
        Point2 q = p.translate(new Translation2(new Vector2(-1,-3)));
        checkPoint2(q,0,-1);
    }

    @Test
    void TestAffine() {
        AffineTransformation2 a = new AffineTransformation2(new Stretch2(2,3),new Translation2(-7,-8));
        Point2 p = new Point2(1,2);
        Point2 q = p.affineTransform(a);
        checkPoint2(q,-5,-2);
    }
}
