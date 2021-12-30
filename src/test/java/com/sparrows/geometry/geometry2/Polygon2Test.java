package com.sparrows.geometry.geometry2;

import com.sparrows.geometry.exception.GeometryException;
import com.sparrows.geometry.exception.IdenticalVertices;
import com.sparrows.geometry.exception.InvalidSidesDensity;
import com.sparrows.geometry.exception.NotEnoughVertices;
import com.sparrows.geometry.exception.ParallelLinesException;
import com.sparrows.geometry.exception.ZeroExternalAngle;
import com.sparrows.geometry.exception.ZeroVectorException;
import com.sparrows.geometry.transformation.Translation2;
import com.sparrows.geometry.maths.Maths;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static com.sparrows.geometry.utils.TestUtils.checkDouble;
import static com.sparrows.geometry.utils.TestUtils.checkPolygon2;

class Polygon2Test {

    @Test
    void TestConstructor() throws NotEnoughVertices {
        Polygon2 g = new Polygon2(new Point2(0,0), new Point2(1,0), new Point2(1,1));
        checkPolygon2(g,0,0,1,0,1,1);
    }

    @Test
    void TestConstructorNoSides() {
        Assertions.assertThrows(NotEnoughVertices.class, Polygon2::new);
    }

    @Test
    void TestConstructorTwoSides() {
        Assertions.assertThrows(NotEnoughVertices.class, () -> new Polygon2(new Point2(0, 0), new Point2(1, 0)));
    }

    @Test
    void TestConstructorList() throws NotEnoughVertices {
        Polygon2 g = new Polygon2(new ArrayList<>(Arrays.asList(new Point2(0, 0), new Point2(1, 0), new Point2(1, 1), new Point2(0, 1))));
        checkPolygon2(g,0,0,1,0,1,1,0,1);
    }

    @Test
    void TestRegularDigon() {
        Exception e = Assertions.assertThrows(NotEnoughVertices.class, () -> Polygon2.regular(2,1));
        Assertions.assertEquals("A polygon must have at least three vertices.",e.getMessage());
    }

    @Test
    void TestRegularDoubleTriangle() {
        Exception e = Assertions.assertThrows(InvalidSidesDensity.class, () -> Polygon2.regular(6,2));
        Assertions.assertEquals("Inconsistent sides and density (6/2).",e.getMessage());
    }

    @Test
    void TestRegularHeptagon() throws GeometryException {
        Polygon2 g = Polygon2.regular(7,1);
        checkPolygon2(g,-.5,-1.0382606982861684,.5,-1.0382606982861684,1.1234898018587336,-0.2564292158181385,0.9009688679024193,0.718498696363685,0,1.1523824354812433,-0.9009688679024193,0.718498696363685,-1.1234898018587336,-0.2564292158181385);
        checkRegular(g,7,1);
    }

    @Test
    void TestRegularNonagram() throws GeometryException {
        Polygon2 g = Polygon2.regular(9,2);
     //   checkPolygon2(g,-.5,-1.0382606982861684,.5,-1.0382606982861684,1.1234898018587336,-0.2564292158181385,0.9009688679024193,0.718498696363685,0,1.1523824354812433,-0.9009688679024193,0.718498696363685,-1.1234898018587336,-0.2564292158181385);
        checkRegular(g,9,2);
    }

    @Test
    void TestInradius() {
        checkDouble(Maths.SQRT3_2,Polygon2.inradius(6,1));
    }

    @Test
    void TestCircumradius() {
        checkDouble(1.,Polygon2.circumradius(6,1));
    }

    @Test
    void TestEdgeAngleSubtended() {
        checkDouble(Maths.PI3,Polygon2.edgeAngleSubtended(6,1));
    }

    @Test
    void TestEdgeInternalAngle() {
        checkDouble(2*Maths.PI3,Polygon2.edgeInternalAngle(6,1));
    }

    @Test
    void TestEdgeExternalAngle() {
        checkDouble(Maths.PI3,Polygon2.edgeExternalAngle(6,1));
    }

    @Test
    void TestClone() throws NotEnoughVertices {
        Polygon2 g = new Polygon2(new Point2(0, 0), new Point2(1, 0), new Point2(1,1));
        Polygon2 h = new Polygon2(g);
        checkPolygon2(h,0,0,1,0,1,1);
        Assertions.assertNotSame(g, h);
        for (var i = 0; i < 3; i++) {
            Assertions.assertNotSame(g.getVertex(i), h.getVertex(i));
        }
    }

    @Test
    void TestIdentical() throws NotEnoughVertices {
        ArrayList<Point2> points1 = new ArrayList<>(Arrays.asList(new Point2(0, 0), new Point2(1, 0), new Point2(1, 1), new Point2(0, 1)));
        ArrayList<Point2> points2 = new ArrayList<>(points1);
        Polygon2 g1 = new Polygon2(points1);
        for (var i = 0; i < g1.vertexCount(); i++) {
            Polygon2 g2 = new Polygon2(points2);
            Assertions.assertTrue(g1.identical(g2));
            points2.add(points2.get(0));
            points2.remove(0);
        }
    }

    @Test
    void TestIdenticalDifferentSize() throws NotEnoughVertices {
        ArrayList<Point2> points1 = new ArrayList<>(Arrays.asList(new Point2(0, 0), new Point2(1, 0), new Point2(1, 1), new Point2(0, 1)));
        Polygon2 g1 = new Polygon2(points1);
        ArrayList<Point2> points2 = new ArrayList<>(Arrays.asList(new Point2(0, 0), new Point2(1, 0), new Point2(1, 1)));
        Polygon2 g2 = new Polygon2(points2);
        Assertions.assertFalse(g1.identical(g2));
    }

    @Test
    void TestIdenticalDifferentVertices() throws NotEnoughVertices {
        ArrayList<Point2> points1 = new ArrayList<>(Arrays.asList(new Point2(0, 0), new Point2(1, 0), new Point2(1, 1), new Point2(0, 1)));
        Polygon2 g1 = new Polygon2(points1);
        ArrayList<Point2> points2 = new ArrayList<>(Arrays.asList(new Point2(0, 0), new Point2(1, 0), new Point2(1, 1),new Point2(0,2)));
        Polygon2 g2 = new Polygon2(points2);
        Assertions.assertFalse(g1.identical(g2));
    }

    @Test
    void TestValidateNotEnoughVertices() throws NotEnoughVertices {
        Polygon2 g = new Polygon2(new Point2(0, 0), new Point2(1, 0), new Point2(1, 0));
        Assertions.assertThrows(IdenticalVertices.class, g::validate);
    }

    @Test
    void TestValidateZeroExternalAngle() throws NotEnoughVertices {
        Polygon2 g = new Polygon2(new Point2(0, 0), new Point2(1, 0), new Point2(2,0));
        Assertions.assertThrows(ZeroExternalAngle.class, g::validate);
    }

    @Test
    void TestValidateOK() throws NotEnoughVertices, IdenticalVertices, ZeroExternalAngle {
        Polygon2 g = new Polygon2(new Point2(0, 0), new Point2(1, 0), new Point2(2,1));
        g.validate();
    }

    @Test
    void TestIntersectionLineNone() throws NotEnoughVertices, ZeroVectorException, ParallelLinesException {
        Polygon2 g = new Polygon2(new Point2(0, 0), new Point2(2, 0), new Point2(2,2), new Point2(0,2));
        Line2 l = new Line2(new Point2(-1,0), Vector2.yUnit);
        var ranges = g.intersection(l);

        for (var r : ranges) {
            System.out.println("range " + r.getStart() + " to " + r.getEnd());
        }

        Assertions.assertEquals(0.,ranges.size());
    }

    @Test
    void TestIntersectionLineOneRange() throws NotEnoughVertices, ZeroVectorException, ParallelLinesException {
        Polygon2 g = new Polygon2(new Point2(0, 0), new Point2(2, 0), new Point2(2,2), new Point2(0,2));
        Line2 l = new Line2(new Point2(1,0), Vector2.yUnit);
        var ranges = g.intersection(l);

        for (var r : ranges) {
            System.out.println("range " + r.getStart() + " to " + r.getEnd());
        }

        Assertions.assertEquals(1.,ranges.size());
        checkDouble(0.,ranges.get(0).getStart());
        checkDouble(2.,ranges.get(0).getEnd());
    }

    @Test
    void TestIntersectionLineTwoRanges() throws NotEnoughVertices, ZeroVectorException, ParallelLinesException {
        Polygon2 g = new Polygon2(new Point2(-1, 0), new Point2(2, 0), new Point2(0,2), new Point2(2,4), new Point2(-1,4));
        Line2 l = new Line2(new Point2(1,0), Vector2.yUnit);
        var ranges = g.intersection(l);

        for (var r : ranges) {
            System.out.println("range " + r.getStart() + " to " + r.getEnd());
        }

        Assertions.assertEquals(2.,ranges.size());
        checkDouble(0.,ranges.get(0).getStart());
        checkDouble(1.,ranges.get(0).getEnd());
        checkDouble(3.,ranges.get(1).getStart());
        checkDouble(4.,ranges.get(1).getEnd());
    }

    @Test
    void TestExpand() throws NotEnoughVertices {
        Polygon2 g = new Polygon2(new Point2(0, 0), new Point2(1, 0), new Point2(2,1));
        Polygon2 h = g.scale(4);
        checkPolygon2(h,0,0,4,0,8,4);
    }

    @Test
    void TestStretch() throws NotEnoughVertices {
        Polygon2 g = new Polygon2(new Point2(0, 0), new Point2(1, 0), new Point2(2,1));
        Polygon2 h = g.stretch(4,5);
        checkPolygon2(h,0,0,4,0,8,5);
    }

    @Test
    void TestRotate() throws GeometryException {
        Polygon2 g = new Polygon2(new Point2(0, 0), new Point2(1, 0), new Point2(2,1));
        Polygon2 h = g.rotate(new Point2(-1,-1),-Maths.PI2);
        checkPolygon2(h,0,-2,0,-3,1,-4);
    }

    @Test
    void TestTranslate() throws NotEnoughVertices {
        Polygon2 g = new Polygon2(new Point2(0, 0), new Point2(1, 0), new Point2(2,1));
        Polygon2 h = g.translate(new Translation2(new Vector2(-1,-3)));
        checkPolygon2(h,-1,-3,0,-3,1,-2);
    }

    void checkRegular(Polygon2 g, int sides, int density)  {
        Assertions.assertEquals(sides,g.getVertices().size());
        Assertions.assertTrue(g.sideVector(0).sameDirection(Vector2.xUnit));
        double circumradius = g.getVertex(0).distance(Point2.origin);
        for (var v = 0; v < sides; v++) {
            checkDouble(1.,g.getVertex(v).distance(g.getVertex((v+1)%g.vertexCount())), "side " + v + " unexpected length");
            checkDouble(circumradius,g.getVertex(v).distance(Point2.origin), "side " + v + " unexpected distance from origin");
        }
    }

}
