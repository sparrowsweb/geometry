package com.sparrows.geometry.utils;

import com.sparrows.geometry.geometry2.Point2;
import com.sparrows.geometry.geometry2.Polygon2;
import com.sparrows.geometry.geometry2.Line2;
import com.sparrows.geometry.geometry2.Vector2;
import com.sparrows.geometry.geometry3.Line3;
import com.sparrows.geometry.geometry3.Plane3;
import com.sparrows.geometry.geometry3.Point3;
import com.sparrows.geometry.geometry3.Polygon3;
import com.sparrows.geometry.geometry3.Vector3;
import com.sparrows.geometry.spherical.Arc;
import com.sparrows.geometry.spherical.GreatCircle;
import com.sparrows.geometry.spherical.SphericalPoint;
import com.sparrows.geometry.spherical.SphericalPolygon;

import org.ejml.simple.SimpleMatrix;
import org.junit.jupiter.api.Assertions;

public class TestUtils {

    public static final double ERROR_MARGIN = 0.00000000001;

    public static void checkArc(Arc a, double x1, double y1, double z1, double x2, double y2, double z2, Arc.ArcType arcType, double xm, double ym, double zm) {
        Assertions.assertEquals(x1, a.getStartPoint().getX(), ERROR_MARGIN, "Point 1 unexpected x coordinate");
        Assertions.assertEquals(y1, a.getStartPoint().getY(), ERROR_MARGIN, "Point 1 unexpected y coordinate");
        Assertions.assertEquals(z1, a.getStartPoint().getZ(), ERROR_MARGIN, "Point 1 unexpected z coordinate");
        Assertions.assertEquals(x2, a.getEndPoint().getX(), ERROR_MARGIN, "Point 2 unexpected x coordinate");
        Assertions.assertEquals(y2, a.getEndPoint().getY(), ERROR_MARGIN, "Point 2 unexpected y coordinate");
        Assertions.assertEquals(z2, a.getEndPoint().getZ(), ERROR_MARGIN, "Point 2 unexpected z coordinate");
        Assertions.assertEquals(arcType, a.getArcType(), "Unexpected arc type");
        Assertions.assertEquals(xm, a.getMidpoint().getX(), ERROR_MARGIN, "Midpoint unexpected x coordinate");
        Assertions.assertEquals(ym, a.getMidpoint().getY(), ERROR_MARGIN, "Midpoint unexpected y coordinate");
        Assertions.assertEquals(zm, a.getMidpoint().getZ(), ERROR_MARGIN, "Midpoint unexpected z coordinate");
    }

    public static void checkDouble(Double expected, Double actual) {
        checkDouble(expected, actual, "");
    }

    public static void checkDouble(Double expected, Double actual, String message) {
        if (expected == null) {
            Assertions.assertNull(actual, (message.equals("") ? "" : message + ": ") + "expected null");
        } else {
            Assertions.assertNotNull(actual, (message.equals("") ? "" : message + ": ") + "expected not null");
            Assertions.assertEquals(expected, actual, ERROR_MARGIN, message);
        }
    }

    public static void checkGreatCircle(GreatCircle c, double x, double y, double z) {
        Assertions.assertEquals(x, c.getCentre().getX(), ERROR_MARGIN, "Unexpected x coordinate");
        Assertions.assertEquals(y, c.getCentre().getY(), ERROR_MARGIN, "Unexpected y coordinate");
        Assertions.assertEquals(z, c.getCentre().getZ(), ERROR_MARGIN, "Unexpected z coordinate");
    }

    public static void checkLine2(Line2 l, double x, double y, double h, double j) {
        Assertions.assertEquals(x, l.getPoint().getX(),ERROR_MARGIN,"Unexpected point x coordinate.");
        Assertions.assertEquals(y, l.getPoint().getY(),ERROR_MARGIN,"Unexpected point y coordinate.");
        Assertions.assertEquals(h, l.getVector().getX(),ERROR_MARGIN,"Unexpected vector x coordinate.");
        Assertions.assertEquals(j, l.getVector().getY(),ERROR_MARGIN,"Unexpected vector y coordinate.");
    }

    public static void checkLine3(Line3 l, double x, double y, double z, double h, double j, double k) {
        Assertions.assertEquals(x, l.getPoint().getX(),ERROR_MARGIN,"Unexpected point x coordinate.");
        Assertions.assertEquals(y, l.getPoint().getY(),ERROR_MARGIN,"Unexpected point y coordinate.");
        Assertions.assertEquals(z, l.getPoint().getZ(),ERROR_MARGIN,"Unexpected point z coordinate.");
        Assertions.assertEquals(h, l.getVector().getX(),ERROR_MARGIN,"Unexpected vector x coordinate.");
        Assertions.assertEquals(j, l.getVector().getY(),ERROR_MARGIN,"Unexpected vector y coordinate.");
        Assertions.assertEquals(k, l.getVector().getZ(),ERROR_MARGIN,"Unexpected vector z coordinate.");
    }

    public static void checkMatrix(double[][] expectedValues, SimpleMatrix actual) {
        SimpleMatrix expected = new SimpleMatrix(expectedValues);
        Assertions.assertEquals(expected.numRows(), actual.numRows(), "Unexpected number of rows.");
        Assertions.assertEquals(expected.numCols(), actual.numCols(), "Unexpected number of columns.");
        for (var r = 0; r < expected.numRows(); r++) {
            for (var c = 0; c < expected.numCols(); c++) {
                checkDouble(expected.get(r, c), actual.get(r, c), "Unexpected value at row " + r + " column " + c);
            }
        }
    }

    public static void checkPlane3(Plane3 a, double x, double y, double z, double d) {
        Assertions.assertEquals(x, a.getNormal().getX(), ERROR_MARGIN, "Unexpected vector x coordinate");
        Assertions.assertEquals(y, a.getNormal().getY(), ERROR_MARGIN, "Unexpected vector y coordinate");
        Assertions.assertEquals(z, a.getNormal().getZ(), ERROR_MARGIN, "Unexpected vector z coordinate");
        Assertions.assertEquals(d, a.getDistanceOrigin(), ERROR_MARGIN, "Unexpected d value");
    }

    public static void checkPoint2(Point2 p, double x, double y) {
        Assertions.assertEquals(x, p.getX(), ERROR_MARGIN, "Unexpected x coordinate");
        Assertions.assertEquals(y, p.getY(), ERROR_MARGIN, "Unexpected y coordinate");
    }

    public static void checkPoint3(Point3 p, double x, double y, double z) {
        Assertions.assertEquals(x, p.getX(), ERROR_MARGIN, "Unexpected x coordinate");
        Assertions.assertEquals(y, p.getY(), ERROR_MARGIN, "Unexpected y coordinate");
        Assertions.assertEquals(z, p.getZ(), ERROR_MARGIN, "Unexpected z coordinate");
    }

    public static void checkPolygon2(Polygon2 g, double...x) {
        Assertions.assertEquals(x.length/2, g.vertexCount(), "Unexpected number of sides");
        for (var v = 0; v < g.vertexCount(); v++) {
            Assertions.assertEquals(x[2*v], g.getVertex(v).getX(), ERROR_MARGIN, "Unexpected x coordinate for vertex " + v);
            Assertions.assertEquals(x[2*v+1], g.getVertex(v).getY(), ERROR_MARGIN, "Unexpected y coordinate for vertex " + v);
        }
    }

    public static void checkPolygon3(Polygon3 g, double...x) {
        Assertions.assertEquals(x.length/3, g.vertexCount(), "Unexpected number of sides");
        for (var v = 0; v < g.vertexCount(); v++) {
            Assertions.assertEquals(x[3*v], g.getVertex(v).getX(), ERROR_MARGIN, "Unexpected x coordinate for vertex " + v);
            Assertions.assertEquals(x[3*v+1], g.getVertex(v).getY(), ERROR_MARGIN, "Unexpected y coordinate for vertex " + v);
            Assertions.assertEquals(x[3*v+2], g.getVertex(v).getZ(), ERROR_MARGIN, "Unexpected z coordinate for vertex " + v);
        }
    }

    public static void checkSphericalPoint(SphericalPoint p, double x, double y, double z) {
        Assertions.assertEquals(x, p.getX(), ERROR_MARGIN, "Unexpected x coordinate");
        Assertions.assertEquals(y, p.getY(), ERROR_MARGIN, "Unexpected y coordinate");
        Assertions.assertEquals(z, p.getZ(), ERROR_MARGIN, "Unexpected z coordinate");
    }

    public static void checkSphericalPolygon(SphericalPolygon g, double...x) {
        Assertions.assertEquals(x.length/3, g.vertexCount(), "Unexpected number of sides");
        for (var v = 0; v < g.vertexCount(); v++) {
            Assertions.assertEquals(x[3*v], g.getVertex(v).getX(), ERROR_MARGIN, "Unexpected x coordinate for vertex " + v);
            Assertions.assertEquals(x[3*v+1], g.getVertex(v).getY(), ERROR_MARGIN, "Unexpected y coordinate for vertex " + v);
            Assertions.assertEquals(x[3*v+2], g.getVertex(v).getZ(), ERROR_MARGIN, "Unexpected z coordinate for vertex " + v);
        }
    }

    public static void checkVector2(Vector2 v, double x, double y) {
        Assertions.assertEquals(x, v.getX(), ERROR_MARGIN, "Unexpected x coordinate");
        Assertions.assertEquals(y, v.getY(), ERROR_MARGIN, "Unexpected y coordinate");
    }

    public static void checkVector3(Vector3 v, double x, double y, double z) {
        Assertions.assertEquals(x, v.getX(), ERROR_MARGIN, "Unexpected x coordinate");
        Assertions.assertEquals(y, v.getY(), ERROR_MARGIN, "Unexpected y coordinate");
        Assertions.assertEquals(z, v.getZ(), ERROR_MARGIN, "Unexpected z coordinate");
    }
}
