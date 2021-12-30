package com.sparrows.geometry.geometry2;

import com.sparrows.geometry.exception.GeometryException;
import com.sparrows.geometry.exception.ParallelLinesException;
import com.sparrows.geometry.exception.ZeroVectorException;
import com.sparrows.geometry.transformation.AffineTransformation2;
import com.sparrows.geometry.transformation.Stretch2;
import com.sparrows.geometry.transformation.Translation2;
import com.sparrows.geometry.maths.Maths;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.sparrows.geometry.utils.TestUtils.checkDouble;
import static com.sparrows.geometry.utils.TestUtils.checkPoint2;
import static com.sparrows.geometry.utils.TestUtils.checkLine2;

class Line2Test {

    private static final double ERROR_MARGIN = 0.0000000001;

    @Test
    void TestConstructorZeroVector() {
        Assertions.assertThrows(ZeroVectorException.class, () -> {
            var l = new Line2(new Point2(0,0), new Vector2(0,0));
        });
        Assertions.assertThrows(ZeroVectorException.class, () -> {
            var l = new Line2(new Point2(1,2), new Vector2(0,0));
        });
    }

    @Test
    void TestConstructorYAxis() throws ZeroVectorException {
        Line2 l = new Line2(new Point2(0,3), new Vector2(0,5));
        checkLine2(l, 0, 0, 0, 1);
    }

    @Test
    void TestConstructorNegativeYAxis() throws ZeroVectorException {
        Line2 l = new Line2(new Point2(0,3), new Vector2(0,-5));
        checkLine2(l, 0, 0, 0, -1);
    }

    @Test
    void TestConstructorX1() throws ZeroVectorException {
        Line2 l = new Line2(new Point2(1,2), new Vector2(0,10));
        checkLine2(l, 1, 0, 0, 1);
    }

    @Test
    void TestConstructorXAxis() throws ZeroVectorException {
        Line2 l = new Line2(new Point2(-7,0), new Vector2(9,0));
        checkLine2(l, 0, 0, 1, 0);
    }

    @Test
    void TestConstructorNegativeXAxis() throws ZeroVectorException {
        Line2 l = new Line2(new Point2(0,.009), new Vector2(0,-0.008));
        checkLine2(l, 0, 0, 0, -1);
    }

    @Test
    void TestConstructorNegativeY3() throws ZeroVectorException {
        Line2 l = new Line2(new Point2(7, 3), new Vector2(-.8,0));
        checkLine2(l, 0, 3, -1, 0);
    }

    @Test
    void TestConstructor() throws ZeroVectorException {
        Line2 l = new Line2(new Point2(2, 0), new Vector2(-1,1));
        checkLine2(l, 1, 1, -1/Maths.SQRT2, 1/Maths.SQRT2);
    }

    @Test
    void TestConstructorPointPoint() throws ZeroVectorException {
        Line2 l = new Line2(new Point2(6, 3), new Point2(3,6));
        checkLine2(l, 4.5, 4.5, -1/Maths.SQRT2, 1/Maths.SQRT2);
    }

    @Test
    void TestIdentical() throws ZeroVectorException {
        Line2 l = new Line2(new Point2(0,0),new Vector2(1,0));
        Line2 m = new Line2(new Point2(0,0),new Vector2(2,0));
        Assertions.assertTrue(l.identical(m));
        Assertions.assertFalse(l.opposite(m));
        Assertions.assertTrue(l.identicalOrOpposite(m));
        Assertions.assertTrue(l.parallel(m));
        Assertions.assertTrue(l.sameDirection(m));
        Assertions.assertFalse(l.oppositeDirection(m));
        Assertions.assertFalse(l.perpendicular(m));
        Assertions.assertTrue(l.intersect(m));
        Assertions.assertFalse(l.intersectPoint(m));
    }

    @Test
    void TestOpposite() throws ZeroVectorException {
        Line2 l = new Line2(new Point2(100,0),new Vector2(3,0));
        Line2 m = new Line2(new Point2(0,0),new Vector2(-1,0));
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
        Line2 l = new Line2(new Point2(0,0),new Vector2(1,0));
        Line2 m = new Line2(new Point2(0,0),new Vector2(1,1));
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
        Line2 l = new Line2(new Point2(2,0),new Vector2(-1,2));
        Line2 m = new Line2(new Point2(1,0),new Vector2(-1,2));
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
        Line2 l = new Line2(new Point2(2,0),new Vector2(-1,2));
        Line2 m = new Line2(new Point2(1,0),new Vector2(1,-2));
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
        Line2 l = new Line2(new Point2(0,0),new Vector2(1,0));
        Line2 m = new Line2(new Point2(0,0),new Vector2(0,1));
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
        Line2 l = new Line2(new Point2(1,2),new Vector2(1,0));
        Line2 m = new Line2(new Point2(4,3),new Vector2(1,1));
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
    void TestAngle() throws ZeroVectorException {
        Line2 l = new Line2(new Point2(1,2),new Vector2(1,0));
        Line2 m = new Line2(new Point2(4,3),new Vector2(1,1));
        checkDouble(Maths.PI4, l.angle(m));
        checkDouble(Maths.PI4, m.angle(l));
        checkDouble(0., l.angle(l));
        checkDouble(0., m.angle(m));
    }

    @Test
    void TestPositiveAngle() throws ZeroVectorException {
        Line2 l = new Line2(new Point2(1,2),new Vector2(1,0));
        Line2 m = new Line2(new Point2(4,3),new Vector2(1,1));
        checkDouble(Maths.PI4, l.positiveAngle(m));
        checkDouble(-Maths.PI4, m.positiveAngle(l));
        checkDouble(0., l.positiveAngle(l));
        checkDouble(0., m.positiveAngle(m));
    }

    @Test
    void TestIntersection () throws ParallelLinesException, ZeroVectorException {
 /*       Assertions.assertThrows(ParallelLinesException.class, () -> {
            Line2.xAxis.intersection(Line2.xAxis);
        });

        Assertions.assertThrows(ParallelLinesException.class, () -> {
            Line2.xAxis.intersection(Line2.xAxis.reverse());
        });

        Assertions.assertThrows(ParallelLinesException.class, () -> {
            Line2.yAxis.intersection(Line2.yAxis);
        });

        Assertions.assertThrows(ParallelLinesException.class, () -> {
            Line2.yAxis.intersection(Line2.yAxis.reverse());
        });

        checkPoint2(Line2.xAxis.intersection(Line2.yAxis), 0, 0);

        checkPoint2(Line2.xAxis.intersection(Line2.yAxis.reverse()), 0, 0);

        checkPoint2(Line2.xAxis.reverse().intersection(Line2.yAxis), 0, 0);

        checkPoint2(Line2.xAxis.reverse().intersection(Line2.yAxis.reverse()), 0, 0);

        checkPoint2(new Line2(new Point2(-4,0),new Vector2(1,1)).intersection(new Line2(new Point2(4,0),new Vector2(-1,1))), 0, 4);
*/
        checkPoint2(new Line2(new Point2(2,0),new Point2(0,2)).intersection(new Line2(new Point2(1,0),Vector2.yUnit)), 1, 1);
    }

    @Test
    void TestReverse() throws ZeroVectorException {
        checkLine2(new Line2(new Point2(0,3), new Vector2(1/Maths.SQRT2,-1/Maths.SQRT2)).reverse(), 1.5, 1.5, -1/Maths.SQRT2, 1/Maths.SQRT2);
    }

    @Test
    void TestScale() throws ZeroVectorException {
        Line2 l = new Line2(new Point2(1,2),new Vector2(1,0));
        Line2 m = l.scale(4);
        checkLine2(m, 0, 8, 1, 0);
    }

    @Test
    void TestRotate() throws GeometryException {
        Line2 l = new Line2(new Point2(3,0),new Vector2(1,1));
        Line2 m = l.rotate(new Point2(1,2),Maths.PI2);
        checkLine2(m, 3.5, 3.5, -1/Maths.SQRT2, 1/Maths.SQRT2);
    }

    @Test
    void TestAffine() throws GeometryException {
        AffineTransformation2 a = new AffineTransformation2(new Stretch2(2,3),new Translation2(-7,-8));
        Line2 l = new Line2(new Point2(1,2),Vector2.xUnit);
        Line2 m = l.affineTransform(a);
        checkLine2(m, 0,-2, 1,0);
    }
}
