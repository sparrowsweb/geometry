package com.sparrows.geometry.spherical;

import static com.sparrows.geometry.utils.TestUtils.checkSphericalPolygon;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.sparrows.geometry.exception.IdenticalVertices;
import com.sparrows.geometry.exception.InvalidSphericalPoint;
import com.sparrows.geometry.exception.InvalidVertexAngle;
import com.sparrows.geometry.exception.InvalidVertexAngles;
import com.sparrows.geometry.exception.NotEnoughSphericalVertices;
import com.sparrows.geometry.exception.NotEnoughVertices;
import com.sparrows.geometry.maths.Maths;


class SphericalPolygonTest {

    @Test
    void TestConstructorNoSides() {
        Assertions.assertThrows(NotEnoughSphericalVertices.class, SphericalPolygon::new);
    }

    @Test
    void TestConstructorOneSide() {
        Assertions.assertThrows(NotEnoughSphericalVertices.class, () -> new SphericalPolygon(new SphericalPoint(1,0,0)));
    }

    @Test
    void TestConstructorTwoSides() throws InvalidSphericalPoint, NotEnoughSphericalVertices {
        SphericalPolygon g = new SphericalPolygon(new SphericalPoint(1,0,0),new SphericalPoint(-1,0,0));
        checkSphericalPolygon(g,1,0,0,-1,0,0);
    }

    @Test
    void TestConstructorList() throws InvalidSphericalPoint, NotEnoughSphericalVertices {
        SphericalPolygon g = new SphericalPolygon(new ArrayList<SphericalPoint>(Arrays.asList(new SphericalPoint(1, 0,0), new SphericalPoint(0, 1,0), new SphericalPoint(0, 0,1))));
        checkSphericalPolygon(g,1,0,0,0,1,0,0,0,1);
    }

    @Test
    void TestIdentical() throws InvalidSphericalPoint, NotEnoughSphericalVertices {
        ArrayList<SphericalPoint> points1 = new ArrayList<>(Arrays.asList(new SphericalPoint(1, 0,0), new SphericalPoint(0, 1,0), new SphericalPoint(0, 0,1)));
        ArrayList<SphericalPoint> points2 = new ArrayList<>(points1);
        SphericalPolygon g1 = new SphericalPolygon(points1);
        for (var i = 0; i < g1.vertexCount(); i++) {
            SphericalPolygon g2 = new SphericalPolygon(points2);
            Assertions.assertTrue(g1.identical(g2));
            points2.add(points2.get(0));
            points2.remove(0);
        }
    }

    @Test
    void TestIdenticalDifferentSize() throws NotEnoughSphericalVertices, InvalidSphericalPoint {
        ArrayList<SphericalPoint> points1 = new ArrayList<>(Arrays.asList(new SphericalPoint(1, 0,0), new SphericalPoint(0, 1,0), new SphericalPoint(0, 0,1)));
        SphericalPolygon g1 = new SphericalPolygon(points1);
        ArrayList<SphericalPoint> points2 = new ArrayList<>(Arrays.asList(new SphericalPoint(0, 1,0), new SphericalPoint(0, 0,1)));
        SphericalPolygon g2 = new SphericalPolygon(points2);
        Assertions.assertFalse(g1.identical(g2));
    }

    @Test
    void TestIdenticalDifferentVertices() throws NotEnoughSphericalVertices, InvalidSphericalPoint {
        ArrayList<SphericalPoint> points1 = new ArrayList<>(Arrays.asList(new SphericalPoint(1, 0,0), new SphericalPoint(0, 1,0), new SphericalPoint(0, 0,1)));
        SphericalPolygon g1 = new SphericalPolygon(points1);
        ArrayList<SphericalPoint> points2 = new ArrayList<>(Arrays.asList(new SphericalPoint(1, 0,0), new SphericalPoint(0, 1,0), new SphericalPoint(0, 0,-1)));
        SphericalPolygon g2 = new SphericalPolygon(points2);
        Assertions.assertFalse(g1.identical(g2));
    }

    @Test
    void TestValidateIdenticalVertices() throws InvalidSphericalPoint, NotEnoughSphericalVertices {
        ArrayList<SphericalPoint> points = new ArrayList<>(Arrays.asList(new SphericalPoint(1, 0,0), new SphericalPoint(1, 0,0), new SphericalPoint(0, 0,1)));
        SphericalPolygon g = new SphericalPolygon(points);
        Assertions.assertThrows(IdenticalVertices.class, g::validate);
    }
/*
    @Test
    void TestValidateZeroExternalAngle() throws NotEnoughVertices {
        Polygon3 g = new Polygon3(new Point3(0, 0, 0), new Point3(1, 0, 0), new Point3(2,0, 0));
        Assertions.assertThrows(ZeroExternalAngle.class, g::validate);
    }
*/
    @Test
    void TestValidateOK() throws IdenticalVertices, InvalidSphericalPoint, NotEnoughSphericalVertices {
        ArrayList<SphericalPoint> points = new ArrayList<>(Arrays.asList(new SphericalPoint(1, 0,0), new SphericalPoint(0, 1,0), new SphericalPoint(0, 0,1)));
        SphericalPolygon g = new SphericalPolygon(points);
        g.validate();
    }

    @Test
    void TestClone() throws InvalidSphericalPoint, NotEnoughSphericalVertices {
        ArrayList<SphericalPoint> points = new ArrayList<>(Arrays.asList(new SphericalPoint(1, 0,0), new SphericalPoint(0, 1,0), new SphericalPoint(0, 0,1)));
        SphericalPolygon g = new SphericalPolygon(points);
        SphericalPolygon h = new SphericalPolygon(g);
        checkSphericalPolygon(h,1,0,0,0,1,0,0,0,1);
        Assertions.assertTrue(g.identical(h));
        Assertions.assertNotSame(g, h);
        for (var i = 0; i < 3; i++) {
            Assertions.assertNotSame(g.getVertex(i), h.getVertex(i));
        }
    }

    @Test
    void TestTriangleZeroAngle() {
        Assertions.assertThrows(InvalidVertexAngle.class, () -> SphericalPolygon.triangle(0,2,2));
        Assertions.assertThrows(InvalidVertexAngle.class, () -> SphericalPolygon.triangle(2,0,2));
        Assertions.assertThrows(InvalidVertexAngle.class, () -> SphericalPolygon.triangle(2,2,0));
    }

    @Test
    void TestTriangleSmallAngles() {
        Assertions.assertThrows(InvalidVertexAngles.class, () -> SphericalPolygon.triangle(1,1,1));
    }

    @Test
    void TestTriangleBigAngle() {
        Assertions.assertThrows(InvalidVertexAngle.class, () -> SphericalPolygon.triangle(6.29,1,1));
        Assertions.assertThrows(InvalidVertexAngle.class, () -> SphericalPolygon.triangle(1,6.29,1));
        Assertions.assertThrows(InvalidVertexAngle.class, () -> SphericalPolygon.triangle(1,1,6.29));
    }

    @Test
    void TestTriangleAnglePi() {
        Assertions.assertThrows(InvalidVertexAngle.class, () -> SphericalPolygon.triangle(Math.PI,1,1));
        Assertions.assertThrows(InvalidVertexAngle.class, () -> SphericalPolygon.triangle(1,Math.PI,1));
        Assertions.assertThrows(InvalidVertexAngle.class, () -> SphericalPolygon.triangle(1,1,Math.PI));
    }

    @Test
    void TestTriangleOctant() throws InvalidVertexAngle, InvalidVertexAngles {
        var g = SphericalPolygon.triangle(Maths.PI2,Maths.PI2,Maths.PI2);
        checkSphericalPolygon(g,0,0,1,0,-1,0,1,0,0);
    }

    /*
    void checkRegular(Polygon3 g, int sides, int density) throws GeometryException {
        Assertions.assertEquals(sides,g.getVertices().size());
        Assertions.assertTrue(g.sideVector(0).sameDirection(Vector3.xUnit));
        double circumradius = g.getVertex(0).distance(Point3.origin);
        for (var v = 0; v < sides; v++) {
            checkDouble(1.,g.getVertex(v).distance(g.getVertex((v+1)%g.vertexCount())), "side " + v + " unexpected length");
            checkDouble(circumradius,g.getVertex(v).distance(Point3.origin), "side " + v + " unexpected distance from origin");
        }
    }*/
}
