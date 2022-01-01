package com.sparrows.geometry.geometry3;

import com.sparrows.geometry.exception.GeometryException;
import com.sparrows.geometry.exception.IdenticalVertices;
import com.sparrows.geometry.exception.NotEnoughVertices;
import com.sparrows.geometry.exception.PolygonNotPlanar;
import com.sparrows.geometry.exception.ZeroExternalAngle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static com.sparrows.geometry.utils.TestUtils.checkDouble;
import static com.sparrows.geometry.utils.TestUtils.checkPlane3;
import static com.sparrows.geometry.utils.TestUtils.checkPolygon3;

class Polygon3Test {

    @Test
    void TestConstructor() throws NotEnoughVertices {
        Polygon3 g = new Polygon3(new Point3(0,0,0), new Point3(1,0,0), new Point3(1,1,0));
        checkPolygon3(g,0,0,0,1,0,0,1,1,0);
    }

    @Test
    void TestConstructorNoSides() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Polygon3 g = new Polygon3();
        });
    }

    @Test
    void TestConstructorTwoSides() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Polygon3 g = new Polygon3(new Point3(0, 0,0), new Point3(1, 0,1));
        });
    }

    @Test
    void TestConstructorList() throws NotEnoughVertices {
        Polygon3 g = new Polygon3(new ArrayList<Point3>(Arrays.asList(new Point3(0, 0,0), new Point3(1, 0,0), new Point3(1, 1,0), new Point3(0, 1,0))));
        checkPolygon3(g,0,0,0,1,0,0,1,1,0,0,1,0);
    }

    @Test
    void TestRegularDigon() {
        Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Polygon3.regular(2,1);
        });
        Assertions.assertEquals("A polygon must have at least three vertices.",e.getMessage());
    }

    @Test
    void TestRegularDoubleTriangle() {
        Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Polygon3.regular(6,2);
        });
        Assertions.assertEquals("Inconsistent sides and density (6/2).",e.getMessage());
    }

    @Test
    void TestRegularHeptagon() throws GeometryException {
        Polygon3 g = Polygon3.regular(7,1);
        checkPolygon3(g,-.5,-1.0382606982861684,0,.5,-1.0382606982861684,0,1.1234898018587336,-0.2564292158181385,0,0.9009688679024193,0.718498696363685,0,0,1.1523824354812433,0,-0.9009688679024193,0.718498696363685,0,-1.1234898018587336,-0.2564292158181385,0);
        checkRegular(g,7,1);
    }

    @Test
    void TestRegularNonagram() throws GeometryException {
        Polygon3 g = Polygon3.regular(9,2);
        //   checkPolygon2(g,-.5,-1.0382606982861684,.5,-1.0382606982861684,1.1234898018587336,-0.2564292158181385,0.9009688679024193,0.718498696363685,0,1.1523824354812433,-0.9009688679024193,0.718498696363685,-1.1234898018587336,-0.2564292158181385);
        checkRegular(g,9,2);
    }

    @Test
    void TestIdentical() throws NotEnoughVertices {
        ArrayList<Point3> points1 = new ArrayList<>(Arrays.asList(new Point3(0, 0,0), new Point3(1, 0,0), new Point3(1, 1,0), new Point3(0, 1,0)));
        ArrayList<Point3> points2 = new ArrayList<>(points1);
        Polygon3 g1 = new Polygon3(points1);
        for (var i = 0; i < g1.vertexCount(); i++) {
            Polygon3 g2 = new Polygon3(points2);
            Assertions.assertTrue(g1.identical(g2));
            points2.add(points2.get(0));
            points2.remove(0);
        }
    }

    @Test
    void TestIdenticalDifferentSize() throws NotEnoughVertices {
        ArrayList<Point3> points1 = new ArrayList<>(Arrays.asList(new Point3(0, 0,0), new Point3(1, 0,0), new Point3(1, 1,0), new Point3(0, 1,0)));
        Polygon3 g1 = new Polygon3(points1);
        ArrayList<Point3> points2 = new ArrayList<>(Arrays.asList(new Point3(0, 0,0), new Point3(1, 0,0), new Point3(1, 1,0)));
        Polygon3 g2 = new Polygon3(points2);
        Assertions.assertFalse(g1.identical(g2));
    }

    @Test
    void TestIdenticalDifferentVertices() throws NotEnoughVertices {
        ArrayList<Point3> points1 = new ArrayList<>(Arrays.asList(new Point3(0, 0, 0), new Point3(1, 0, 0), new Point3(1, 1, 0), new Point3(0, 1, 0)));
        Polygon3 g1 = new Polygon3(points1);
        ArrayList<Point3> points2 = new ArrayList<>(Arrays.asList(new Point3(0, 0, 0), new Point3(1, 0, 0), new Point3(1, 1, 0),new Point3(0,2, 0)));
        Polygon3 g2 = new Polygon3(points2);
        Assertions.assertFalse(g1.identical(g2));
    }

    @Test
    void TestValidateNotEnoughVertices() throws NotEnoughVertices {
        Polygon3 g = new Polygon3(new Point3(0, 0, 0), new Point3(1, 0, 0), new Point3(1, 0, 0));
        Assertions.assertThrows(IdenticalVertices.class, g::validate);
    }

    @Test
    void TestValidateZeroExternalAngle() throws NotEnoughVertices {
        Polygon3 g = new Polygon3(new Point3(0, 0, 0), new Point3(1, 0, 0), new Point3(2,0, 0));
        Assertions.assertThrows(ZeroExternalAngle.class, g::validate);
    }

    @Test
    void TestValidateNotPlanar() throws NotEnoughVertices {
        Polygon3 g = new Polygon3(new Point3(0, 0, 0), new Point3(1, 0, 0), new Point3(0,1, 0), new Point3(2,0, 1));
        Assertions.assertThrows(PolygonNotPlanar.class, g::validate);
    }

    @Test
    void TestValidateOK() throws NotEnoughVertices, IdenticalVertices, ZeroExternalAngle, PolygonNotPlanar {
        Polygon3 g = new Polygon3(new Point3(0, 0, 0), new Point3(1, 0, 0), new Point3(2,1, 0));
        g.validate();
    }

    @Test
    void TestClone() throws NotEnoughVertices {
        Polygon3 g = new Polygon3(new Point3(0, 0, 0), new Point3(1, 0, 0), new Point3(2,1, 0));
        Polygon3 h = new Polygon3(g);
        checkPolygon3(h,0,0,0,1,0,0,2,1,0);
        Assertions.assertNotSame(g, h);
        for (var i = 0; i < 3; i++) {
            Assertions.assertNotSame(g.getVertex(i), h.getVertex(i));
        }
    }

    @Test
    void TestPlane() throws NotEnoughVertices, PolygonNotPlanar {
        Polygon3 g = new Polygon3(new Point3(0, 0, 0), new Point3(1, 0, 0), new Point3(2,1, 0));
        checkPlane3(g.plane(),0,0,1,0);
    }
/*
    @Test
    void TestExpand() throws NotEnoughVertices {
        Polygon3 g = new Polygon3(new Point3(0, 0), new Point3(1, 0), new Point3(2,1));
        Polygon3 h = g.scale(4);
        checkPolygon3(h,0,0,4,0,8,4);
    }

    @Test
    void TestExpandSelf() throws NotEnoughVertices {
        Polygon3 g = new Polygon3(new Point3(0, 0), new Point3(1, 0), new Point3(2,1));
        g.scaleSelf(4);
        checkPolygon3(g,0,0,4,0,8,4);
    }

    @Test
    void TestStretch() throws NotEnoughVertices {
        Polygon3 g = new Polygon3(new Point3(0, 0), new Point3(1, 0), new Point3(2,1));
        Polygon3 h = g.stretch(4,5);
        checkPolygon3(h,0,0,4,0,8,5);
    }

    @Test
    void TestStretchSelf() throws NotEnoughVertices {
        Polygon3 g = new Polygon3(new Point3(0, 0), new Point3(1, 0), new Point3(2,1));
        g.stretchSelf(4,5);
        checkPolygon3(g,0,0,4,0,8,5);
    }*/
/*
    @Test
    void TestRotateOrigin() {
        Point3 p = new Point3(1,2);
        Point3 q = p.rotateOrigin(Math.PI);
        checkPoint3(q,-1,-2);
    }

    @Test
    void TestRotateOriginSelf() {
        Point3 p = new Point3(1,2);
        p.rotateOriginSelf(-Math.PI/2);
        checkPoint3(p,2,-1);
    }


    @Test
    void TestRotate() throws GeometryException {
        Polygon3 g = new Polygon3(new Point3(0, 0), new Point3(1, 0), new Point3(2,1));
        Polygon3 h = g.rotate(new Point3(-1,-1),-Maths.PI2);
        checkPolygon3(h,0,-2,0,-3,1,-4);
    }

    @Test
    void TestTranslate() throws NotEnoughVertices {
        Polygon3 g = new Polygon3(new Point3(0, 0), new Point3(1, 0), new Point3(2,1));
        Polygon3 h = g.translate(new Translation2(new Vector2(-1,-3)));
        checkPolygon3(h,-1,-3,0,-3,1,-2);
    }

    @Test
    void TestTranslateSelf() throws NotEnoughVertices {
        Polygon3 g = new Polygon3(new Point3(0, 0), new Point3(1, 0), new Point3(2,1));
        g.translateSelf(new Translation2(new Vector2(-1,-3)));
        checkPolygon3(g,-1,-3,0,-3,1,-2);
    }*/

    void checkRegular(Polygon3 g, int sides, int density) throws GeometryException {
        Assertions.assertEquals(sides,g.getVertices().size());
        Assertions.assertTrue(g.sideVector(0).sameDirection(Vector3.X_UNIT));
        double circumradius = g.getVertex(0).distance(Point3.origin);
        for (var v = 0; v < sides; v++) {
            checkDouble(1.,g.getVertex(v).distance(g.getVertex((v+1)%g.vertexCount())), "side " + v + " unexpected length");
            checkDouble(circumradius,g.getVertex(v).distance(Point3.origin), "side " + v + " unexpected distance from origin");
        }
    }
}
