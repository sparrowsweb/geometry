package com.sparrows.geometry.geometry3;

import com.sparrows.geometry.exception.GeometryException;
import com.sparrows.geometry.exception.ParallelLinesException;
import com.sparrows.geometry.exception.SkewLines;
import com.sparrows.geometry.exception.ZeroVectorException;
import com.sparrows.geometry.maths.Maths;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.sparrows.geometry.utils.TestUtils.checkDouble;
import static com.sparrows.geometry.utils.TestUtils.checkLine3;
import static com.sparrows.geometry.utils.TestUtils.checkPoint3;

class Line3Test {

    @Test
    void TestConstructorZeroVector() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            var l = new Line3(new Point3(0,0,0), new Vector3(0,0,0));
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            var l = new Line3(new Point3(1,2,3), new Vector3(0,0,0));
        });
    }

    @Test
    void TestConstructorYAxis() {
        Line3 l = new Line3(new Point3(0,3,0), new Vector3(0,5,0));
        checkLine3(l, 0, 0, 0, 0, 1, 0);
    }

    @Test
    void TestConstructorNegativeYAxis() throws ZeroVectorException {
        Line3 l = new Line3(new Point3(0,3,0), new Vector3(0,-5,0));
        checkLine3(l, 0, 0,0, 0, -1,0);
    }

    @Test
    void TestConstructorX1Y2() throws ZeroVectorException {
        Line3 l = new Line3(new Point3(1,2,7), new Vector3(0,0,10));
        checkLine3(l, 1, 2,0, 0, 0,1);
    }

    @Test
    void TestConstructorXAxis() throws ZeroVectorException {
        Line3 l = new Line3(new Point3(-7,0,0), new Vector3(9,0,0));
        checkLine3(l, 0, 0,0, 1, 0,0);
    }

    @Test
    void TestConstructorNegativeXAxis() throws ZeroVectorException {
        Line3 l = new Line3(new Point3(.009,0,0), new Vector3(-0.008,0,0));
        checkLine3(l, 0, 0, 0,-1,0, 0);
    }

    @Test
    void TestConstructorNegativeY3Z4() throws ZeroVectorException {
        Line3 l = new Line3(new Point3(7, 3,4), new Vector3(-.8,0,0));
        checkLine3(l, 0, 3,4, -1, 0,0);
    }

    @Test
    void TestConstructorPointPoint() throws ZeroVectorException {
        Line3 l = new Line3(new Point3(1,1,1), new Point3(2,2,2));
        checkLine3(l, 0,0,0, 1/Maths.SQRT3, 1/Maths.SQRT3, 1/Maths.SQRT3);
    }

    @Test
    void TestIdentical() throws ZeroVectorException {
        Line3 l = new Line3(new Point3(0,0,0),new Vector3(1,0,0));
        Line3 m = new Line3(new Point3(0,0,0),new Vector3(2,0,0));
        Assertions.assertTrue(l.identical(m));
        Assertions.assertFalse(l.opposite(m));
        Assertions.assertTrue(l.identicalOrOpposite(m));
        Assertions.assertTrue(l.parallel(m));
        Assertions.assertTrue(l.sameDirection(m));
        Assertions.assertFalse(l.oppositeDirection(m));
        Assertions.assertFalse(l.perpendicular(m));
        Assertions.assertTrue(l.intersect(m));
        Assertions.assertFalse(l.intersectPoint(m));
        Assertions.assertFalse(l.skew(m));
    }
/*
    @Test
    void TestOpposite() throws ZeroVectorException {
        Line3 l = new Line3(new Point3(100,0),new Vector3(3,0));
        Line3 m = new Line3(new Point3(0,0),new Vector3(-1,0));
        Assertions.assertFalse(l.identical(m));
        Assertions.assertTrue(l.opposite(m));
        Assertions.assertTrue(l.identicalOrOpposite(m));
        Assertions.assertTrue(l.parallel(m));
        Assertions.assertFalse(l.sameDirection(m));
        Assertions.assertTrue(l.oppositeDirection(m));
        Assertions.assertFalse(l.perpendicular(m));
        Assertions.assertTrue(l.intersect(m));
        Assertions.assertFalse(l.intersectPoint(m));
    }

    @Test
    void TestNotIdenticalOrOpposite() throws ZeroVectorException {
        Line3 l = new Line3(new Point3(0,0),new Vector3(1,0));
        Line3 m = new Line3(new Point3(0,0),new Vector3(1,1));
        Assertions.assertFalse(l.identical(m));
        Assertions.assertFalse(l.opposite(m));
        Assertions.assertFalse(l.identicalOrOpposite(m));
        Assertions.assertFalse(l.parallel(m));
        Assertions.assertFalse(l.sameDirection(m));
        Assertions.assertFalse(l.oppositeDirection(m));
        Assertions.assertFalse(l.perpendicular(m));
        Assertions.assertTrue(l.intersect(m));
        Assertions.assertTrue(l.intersectPoint(m));
    }

    @Test
    void TestSameDirection() throws ZeroVectorException {
        Line3 l = new Line3(new Point3(2,0),new Vector3(-1,2));
        Line3 m = new Line3(new Point3(1,0),new Vector3(-1,2));
        Assertions.assertFalse(l.identical(m));
        Assertions.assertFalse(l.opposite(m));
        Assertions.assertFalse(l.identicalOrOpposite(m));
        Assertions.assertTrue(l.parallel(m));
        Assertions.assertTrue(l.sameDirection(m));
        Assertions.assertFalse(l.oppositeDirection(m));
        Assertions.assertFalse(l.perpendicular(m));
        Assertions.assertFalse(l.intersect(m));
        Assertions.assertFalse(l.intersectPoint(m));
    }

    @Test
    void TestOppositeDirection() throws ZeroVectorException {
        Line3 l = new Line3(new Point3(2,0),new Vector3(-1,2));
        Line3 m = new Line3(new Point3(1,0),new Vector3(1,-2));
        Assertions.assertFalse(l.identical(m));
        Assertions.assertFalse(l.opposite(m));
        Assertions.assertFalse(l.identicalOrOpposite(m));
        Assertions.assertTrue(l.parallel(m));
        Assertions.assertFalse(l.sameDirection(m));
        Assertions.assertTrue(l.oppositeDirection(m));
        Assertions.assertFalse(l.perpendicular(m));
        Assertions.assertFalse(l.intersect(m));
        Assertions.assertFalse(l.intersectPoint(m));
    }

    @Test
    void TestPerpendicular() throws ZeroVectorException {
        Line3 l = new Line3(new Point3(0,0),new Vector3(1,0));
        Line3 m = new Line3(new Point3(0,0),new Vector3(0,1));
        Assertions.assertFalse(l.identical(m));
        Assertions.assertFalse(l.opposite(m));
        Assertions.assertFalse(l.identicalOrOpposite(m));
        Assertions.assertFalse(l.parallel(m));
        Assertions.assertFalse(l.sameDirection(m));
        Assertions.assertFalse(l.oppositeDirection(m));
        Assertions.assertTrue(l.perpendicular(m));
        Assertions.assertTrue(l.intersect(m));
        Assertions.assertTrue(l.intersectPoint(m));
    }

    @Test
    void TestIntersectPoint() throws ZeroVectorException {
        Line3 l = new Line3(new Point3(1,2),new Vector3(1,0));
        Line3 m = new Line3(new Point3(4,3),new Vector3(1,1));
        Assertions.assertFalse(l.identical(m));
        Assertions.assertFalse(l.opposite(m));
        Assertions.assertFalse(l.identicalOrOpposite(m));
        Assertions.assertFalse(l.parallel(m));
        Assertions.assertFalse(l.sameDirection(m));
        Assertions.assertFalse(l.oppositeDirection(m));
        Assertions.assertFalse(l.perpendicular(m));
        Assertions.assertTrue(l.intersect(m));
        Assertions.assertTrue(l.intersectPoint(m));
    }
*/
    @Test
    void TestAngle() throws ZeroVectorException {
        Line3 l = new Line3(new Point3(1,2,3),new Vector3(1,0,0));
        Line3 m = new Line3(new Point3(4,3,2),new Vector3(1,1,0));
        checkDouble(Maths.PI4, l.angle(m));
        checkDouble(Maths.PI4, m.angle(l));
        checkDouble(0., l.angle(l));
        checkDouble(0., m.angle(m));
    }

    @Test
    void TestIntersectionParallel () {
        Assertions.assertThrows(ParallelLinesException.class, () -> {
            Line3.xAxis.intersection(Line3.xAxis);
        });

        Assertions.assertThrows(ParallelLinesException.class, () -> {
            Line3.xAxis.intersection(Line3.xAxis.reverse());
        });

        Assertions.assertThrows(ParallelLinesException.class, () -> {
            Line3.yAxis.intersection(Line3.yAxis);
        });

        Assertions.assertThrows(ParallelLinesException.class, () -> {
            Line3.yAxis.intersection(Line3.yAxis.reverse());
        });

        Assertions.assertThrows(ParallelLinesException.class, () -> {
            Line3.zAxis.intersection(Line3.zAxis);
        });

        Assertions.assertThrows(ParallelLinesException.class, () -> {
            Line3.zAxis.intersection(Line3.zAxis.reverse());
        });
    }
    @Test
    void TestIntersection () throws ParallelLinesException, SkewLines {
        checkPoint3(Line3.xAxis.intersection(Line3.yAxis), 0, 0, 0);

        checkPoint3(Line3.xAxis.intersection(Line3.yAxis.reverse()), 0, 0, 0);

        checkPoint3(Line3.xAxis.reverse().intersection(Line3.yAxis), 0, 0, 0);

        checkPoint3(Line3.xAxis.reverse().intersection(Line3.yAxis.reverse()), 0, 0, 0);

        checkPoint3(Line3.xAxis.intersection(Line3.zAxis), 0, 0, 0);

        checkPoint3(Line3.xAxis.intersection(Line3.zAxis.reverse()), 0, 0, 0);

        checkPoint3(Line3.xAxis.reverse().intersection(Line3.zAxis), 0, 0, 0);

        checkPoint3(Line3.xAxis.reverse().intersection(Line3.zAxis.reverse()), 0, 0, 0);
/*
        checkPoint3(new Line3(new Point3(-4,0),new Vector3(1,1)).intersection(new Line3(new Point3(4,0),new Vector3(-1,1))), 0, 4);*/

    }

    @Test
    void TestIntersectionSkew () {
        Assertions.assertThrows(SkewLines.class, () -> {
            Line3 l = Line3.xAxis;
            Line3 m = new Line3(new Point3(0,0,1),Vector3.Y_UNIT);
            l.intersection(m);
        });
        Assertions.assertThrows(SkewLines.class, () -> {
            Line3 l = Line3.xAxis.reverse();
            Line3 m = new Line3(new Point3(0,0,1),Vector3.Y_UNIT);
            l.intersection(m);
        });
        Assertions.assertThrows(SkewLines.class, () -> {
            Line3 l = Line3.xAxis;
            Line3 m = new Line3(new Point3(0,0,1),Vector3.Y_UNIT.reverse());
            l.intersection(m);
        });
        Assertions.assertThrows(SkewLines.class, () -> {
            Line3 l = Line3.xAxis.reverse();
            Line3 m = new Line3(new Point3(0,0,1),Vector3.Y_UNIT.reverse());
            l.intersection(m);
        });

        Assertions.assertThrows(SkewLines.class, () -> {
            Line3 l = Line3.yAxis;
            Line3 m = new Line3(new Point3(-1,0,0),Vector3.Z_UNIT);
            l.intersection(m);
        });
        Assertions.assertThrows(SkewLines.class, () -> {
            Line3 l = Line3.yAxis.reverse();
            Line3 m = new Line3(new Point3(-1,0,0),Vector3.Z_UNIT);
            l.intersection(m);
        });
        Assertions.assertThrows(SkewLines.class, () -> {
            Line3 l = Line3.yAxis;
            Line3 m = new Line3(new Point3(-1,0,0),Vector3.Z_UNIT.reverse());
            l.intersection(m);
        });
        Assertions.assertThrows(SkewLines.class, () -> {
            Line3 l = Line3.yAxis.reverse();
            Line3 m = new Line3(new Point3(-1,0,0),Vector3.Z_UNIT.reverse());
            l.intersection(m);
        });

        Assertions.assertThrows(SkewLines.class, () -> {
            Line3 l = Line3.zAxis;
            Line3 m = new Line3(new Point3(0,0.02,0),Vector3.X_UNIT);
            l.intersection(m);
        });
        Assertions.assertThrows(SkewLines.class, () -> {
            Line3 l = Line3.zAxis.reverse();
            Line3 m = new Line3(new Point3(0,0.02,0),Vector3.X_UNIT);
            l.intersection(m);
        });
        Assertions.assertThrows(SkewLines.class, () -> {
            Line3 l = Line3.zAxis;
            Line3 m = new Line3(new Point3(0,0.02,0),Vector3.X_UNIT.reverse());
            l.intersection(m);
        });
        Assertions.assertThrows(SkewLines.class, () -> {
            Line3 l = Line3.zAxis.reverse();
            Line3 m = new Line3(new Point3(0,0.02,0),Vector3.X_UNIT).reverse();
            l.intersection(m);
        });
    }

    @Test
    void TestReverse() throws ZeroVectorException {
        checkLine3(new Line3(new Point3(1,-1,1), new Vector3(1/Maths.SQRT3,-1/Maths.SQRT3,1/Maths.SQRT3)).reverse(), 0, 0, 0, -1/Maths.SQRT3, 1/Maths.SQRT3, -1/Maths.SQRT3);
    }

    @Test
    void TestScale() throws GeometryException {
        Line3 l = new Line3(new Point3(1,2,3),new Vector3(1,0,0));
        Line3 m = l.scale(4);
        checkLine3(m, 0, 8,12, 1, 0,0);
    }
/*
    @Test
    void TestRotate() throws GeometryException {
        Line3 l = new Line3(new Point3(3,0),new Vector3(1,1));
        Line3 m = l.rotate(new Point3(1,2),Maths.PI2);
        checkLine3(m, 0, 7, -1/Maths.SQRT2, 1/Maths.SQRT2);
    }

    @Test
    void TestAffine() throws GeometryException {
        AffineTransformation2 a = new AffineTransformation2(new Stretch2(2,3),new Translation2(-7,-8));
        Line3 l = new Line3(new Point3(1,2),Vector3.xUnit);
        Line3 m = l.affineTransform(a);
        checkLine3(m, 0,-2, 1,0);
    }

    @Test
    void TestAffineSelf() throws GeometryException {
        AffineTransformation2 a = new AffineTransformation2(new Stretch2(2,3),new Translation2(-7,-8));
        Line3 l = new Line3(new Point3(1,2),Vector3.xUnit);
        l.affineTransformSelf(a);
        checkLine3(l, 0,-2, 1,0);
    }*/
}
