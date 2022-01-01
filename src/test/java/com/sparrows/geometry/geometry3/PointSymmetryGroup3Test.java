package com.sparrows.geometry.geometry3;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.sparrows.geometry.WythoffSymbol;
import com.sparrows.geometry.exception.GeometryException;
import com.sparrows.geometry.exception.NotAPointSymmetry;
import com.sparrows.geometry.maths.exceptions.InvalidRational;
import com.sparrows.geometry.maths.exceptions.ZeroDenominator;
import com.sparrows.geometry.spherical.SphericalPolyhedron;
import com.sparrows.geometry.transformation.Translation3;

class PointSymmetryGroup3Test {

    List<String> validGroups = Arrays.asList(
            "T","Td","Th",
            "O","Oh",
            "I","Ih",
            "C1", "C2", "C5", "C123",
            "C1v", "C2v", "C7v", "C98v",
            "C1h", "C2h", "C8h", "C101h",
            "D1", "D2", "D6", "D103",
            "D1d", "D2d", "D5d", "D102d",
            "D1h", "D2h", "D9h", "D109h",
            "S2", "S4", "S8", "S100",
            "Ci",
            "Cs");
    List<String> validGroupsResult = Arrays.asList(
            "T","Td","Th",
            "O","Oh",
            "I","Ih",
            "C1", "C2", "C5", "C123",
            "C1h", "C2v", "C7v", "C98v",
            "C1h", "C2h", "C8h", "C101h",
            "C2", "D2", "D6", "D103",
            "C2h", "D2d", "D5d", "D102d",
            "C2v", "D2h", "D9h", "D109h",
            "S2", "S4", "S8", "S100",
            "S2",
            "C1h");

    @Test
    void TestConstructorOK() throws NotAPointSymmetry {
        for (int i = 0; i < validGroups.size(); i++) {
            PointSymmetryGroup3 g = new PointSymmetryGroup3(validGroups.get(i));
            Assertions.assertEquals(validGroupsResult.get(i), g.toString());
        }
    }

    @Test
    void TestPolyhedronC1() throws GeometryException {
        Polyhedron h = StandardPolyhedra.tetrahedron(new Point3(0,0,0),new Point3(1,0,0),new Point3(0,2,0),new Point3(0,0,3));
        PointSymmetryGroup3 g = new PointSymmetryGroup3(h);
        Assertions.assertEquals("C1",g.toString());
    }

    @Test
    void TestPolyhedronC2() throws GeometryException {
        Polygon3 parallelogram = new Polygon3(new Point3(0,0,0), new Point3(1,0,0), new Point3(3,2,0),new Point3(2,2,0));
        Polyhedron h = StandardPolyhedra.pyramid(parallelogram,new Point3(1.5,1,1));
        PointSymmetryGroup3 g = new PointSymmetryGroup3(h);
        Assertions.assertEquals("C2",g.toString());
    }

    @Test
    void TestPolyhedronC5() throws GeometryException {
        Polyhedron h = StandardPolyhedra.frustum(Polygon3.pentagon, Polygon3.pentagon.translate(Vector3.Z_UNIT).scale(0.5).rotate(Line3.zAxis,Math.PI/20));
        PointSymmetryGroup3 g = new PointSymmetryGroup3(h);
        Assertions.assertEquals("C5",g.toString());
    }

    @Test
    void TestPolyhedronC49() throws GeometryException {
        Polyhedron h = StandardPolyhedra.frustum(Polygon3.regular(49), Polygon3.regular(49).translate(Vector3.Z_UNIT).scale(0.5).rotate(Line3.zAxis,Math.PI/300));
        PointSymmetryGroup3 g = new PointSymmetryGroup3(h);
        Assertions.assertEquals("C49",g.toString());
    }

    @Test
    void TestPolyhedronC1h() throws GeometryException {
        Polyhedron h = StandardPolyhedra.prism(new Polygon3(new Point3(0,0,0),new Point3(1,0,0),new Point3(0,2,0)), new Translation3(Vector3.Z_UNIT));
        PointSymmetryGroup3 g = new PointSymmetryGroup3(h);
        Assertions.assertEquals("C1h",g.toString());
    }

    @Test
    void TestPolyhedronC2h() throws GeometryException {
        Polygon3 parallelogram = new Polygon3(new Point3(0,0,0), new Point3(1,0,0), new Point3(3,2,0),new Point3(2,2,0));
        Polyhedron h = StandardPolyhedra.prism(parallelogram,new Translation3(Vector3.Z_UNIT));
        PointSymmetryGroup3 g = new PointSymmetryGroup3(h);
        Assertions.assertEquals("C2h",g.toString());
    }

    @Test
    void TestPolyhedronC5h() throws GeometryException {
        Polyhedron h = StandardPolyhedra.frustum(Polygon3.pentagon, Polygon3.pentagon.translate(Vector3.Z_UNIT).scale(0.5).rotate(Line3.zAxis,Math.PI/20));
        Polyhedron h2 = h.reflect(Polygon3.pentagon.plane());
        Polyhedron compound = new Polyhedron(h,h2);
        PointSymmetryGroup3 g = new PointSymmetryGroup3(compound);
        Assertions.assertEquals("C5h",g.toString());
    }

    @Test
    void TestPolyhedronC30h() throws GeometryException {
        Polyhedron h = StandardPolyhedra.frustum(Polygon3.regular(30), Polygon3.regular(30).translate(Vector3.Z_UNIT).scale(0.5).rotate(Line3.zAxis,Math.PI/20));
        Polyhedron h2 = h.reflect(Polygon3.regular(30).plane());
        Polyhedron compound = new Polyhedron(h,h2);
        PointSymmetryGroup3 g = new PointSymmetryGroup3(compound);
        Assertions.assertEquals("C30h",g.toString());
    }

    @Test
    void TestPolyhedronC2v() throws GeometryException {
        Polygon3 base = new Polygon3(new Point3(-2,-1,0), new Point3(2,-1,0), new Point3(2,1,0), new Point3(-2,1,0));
        Polyhedron h = StandardPolyhedra.frustum(base, base.translate(Vector3.Z_UNIT).scale(0.5));
        PointSymmetryGroup3 g = new PointSymmetryGroup3(h);
        Assertions.assertEquals("C2v",g.toString());
    }

    @Test
    void TestPolyhedronC5v() throws GeometryException {
        Polyhedron h = StandardPolyhedra.pyramid(Polygon3.pentagon,new Point3(0,0,1));
        PointSymmetryGroup3 g = new PointSymmetryGroup3(h);
        Assertions.assertEquals("C5v",g.toString());
    }

    @Test
    void TestPolyhedronC49v() throws GeometryException {
        Polyhedron h = StandardPolyhedra.pyramid(Polygon3.regular(49),new Point3(0,0,1));
        PointSymmetryGroup3 g = new PointSymmetryGroup3(h);
        Assertions.assertEquals("C49v",g.toString());
    }

    @Test
    void TestPolyhedronD2() throws GeometryException {
        Polygon3 parallelogram = new Polygon3(new Point3(-2,-1,5), new Point3(-1,-2,5), new Point3(2,1,5),new Point3(1,2,5));
        Polygon3 parallelogram2 = parallelogram.rotate(Line3.xAxis,Math.PI);
        Polyhedron h = StandardPolyhedra.frustum(parallelogram,parallelogram2);
        PointSymmetryGroup3 g = new PointSymmetryGroup3(h);
        Assertions.assertEquals("D2",g.toString());
    }

    @Test
    void TestPolyhedronD5() throws GeometryException {
        Polyhedron h = StandardPolyhedra.twistedPrism(5, Math.PI/20);
        PointSymmetryGroup3 g = new PointSymmetryGroup3(h);
        Assertions.assertEquals("D5",g.toString());
    }

    @Test
    void TestPolyhedronD20() throws GeometryException {
        Polyhedron h = StandardPolyhedra.twistedPrism(20,3, Math.PI/100);
        PointSymmetryGroup3 g = new PointSymmetryGroup3(h);
        Assertions.assertEquals("D20",g.toString());
    }

    @Test
    void TestPolyhedronD2d() throws GeometryException {
        Polygon3 base = new Polygon3(new Point3(-2,-1,0),new Point3(2,-1,0),new Point3(2,1,0),new Point3(-2,1,0));
        Polygon3 top = new Polygon3(new Point3(-1,-2,5),new Point3(1,-2,5),new Point3(1,2,5),new Point3(-1,2,5));
        Polyhedron h = StandardPolyhedra.frustum(base,top);
        PointSymmetryGroup3 g = new PointSymmetryGroup3(h);
        Assertions.assertEquals("D2d",g.toString());
    }

    @Test
    void TestPolyhedronD5d() throws GeometryException {
        Polyhedron h = StandardPolyhedra.uniformAntiprism(5);
        PointSymmetryGroup3 g = new PointSymmetryGroup3(h);
        Assertions.assertEquals("D5d",g.toString());
    }

    @Test
    void TestPolyhedronD20d() throws GeometryException {
        Polyhedron h = StandardPolyhedra.uniformAntiprism(20,7);
        PointSymmetryGroup3 g = new PointSymmetryGroup3(h);
        Assertions.assertEquals("D20d",g.toString());
    }

    @Test
    void TestPolyhedronD2h() throws GeometryException {
        Polygon3 rectangle = new Polygon3(new Point3(0,0,0),new Point3(2,0,0),new Point3(2,1,0),new Point3(0,1,0));
        Polyhedron cuboid = StandardPolyhedra.prism(rectangle,new Translation3(new Vector3(0,0,3)));
        PointSymmetryGroup3 g = new PointSymmetryGroup3(cuboid);
        Assertions.assertEquals("D2h",g.toString());
    }

    @Test
    void TestPolyhedronD5h() throws GeometryException {
        Polyhedron h = StandardPolyhedra.uniformPrism(5);
        PointSymmetryGroup3 g = new PointSymmetryGroup3(h);
        Assertions.assertEquals("D5h",g.toString());
    }

    @Test
    void TestPolyhedronD20h() throws GeometryException {
        Polyhedron h = StandardPolyhedra.uniformPrism(20,7);
        PointSymmetryGroup3 g = new PointSymmetryGroup3(h);
        Assertions.assertEquals("D20h",g.toString());
    }

    @Test
    void TestPolyhedronS2() throws GeometryException {
        Polyhedron h = StandardPolyhedra.tetrahedron(new Point3(0,0,0),new Point3(1,0,0),new Point3(0,2,0),new Point3(0,0,3));
        Polyhedron h2 = h.rotate(Line3.zAxis,Math.PI).reflect(Plane3.Z_EQUALS_0);
        Polyhedron compound = new Polyhedron(h,h2);
        PointSymmetryGroup3 g = new PointSymmetryGroup3(compound);
        Assertions.assertEquals("S2",g.toString());
    }

    @Test
    void TestPolyhedronS10() throws GeometryException {
        Polyhedron h = StandardPolyhedra.frustum(Polygon3.pentagon, Polygon3.pentagon.translate(Vector3.Z_UNIT).scale(0.5).rotate(Line3.zAxis,Math.PI/30));
        Polyhedron h2 = h.reflect(Plane3.Z_EQUALS_0).rotate(Line3.zAxis,Math.PI/5);
        Polyhedron compound = new Polyhedron(h,h2);
        PointSymmetryGroup3 g = new PointSymmetryGroup3(compound);
        Assertions.assertEquals("S10",g.toString());
    }

    @Test
    void TestPolyhedronS30() throws GeometryException {
        Polyhedron h = StandardPolyhedra.frustum(Polygon3.regular(15), Polygon3.regular(15).translate(Vector3.Z_UNIT).scale(0.5).rotate(Line3.zAxis,Math.PI/300));
        Polyhedron h2 = h.reflect(Plane3.Z_EQUALS_0).rotate(Line3.zAxis,Math.PI/15);
        Polyhedron compound = new Polyhedron(h,h2);
        PointSymmetryGroup3 g = new PointSymmetryGroup3(compound);
        Assertions.assertEquals("S30",g.toString());
    }

    @Test
    void TestPolyhedronT() throws GeometryException {
        Polyhedron h = StandardPolyhedra.tetrahedron;
        h = StandardPolyhedra.snub(h,2,Math.PI/20);
        h.validate();
        PointSymmetryGroup3 g = new PointSymmetryGroup3(h);
        Assertions.assertEquals("T",g.toString());
    }

    @Test
    void TestPolyhedronTd() throws GeometryException {
        Polyhedron h = StandardPolyhedra.tetrahedron;
        PointSymmetryGroup3 g = new PointSymmetryGroup3(h);
        Assertions.assertEquals("Td",g.toString());
    }

    @Test
    void TestPolyhedronTh() throws GeometryException {
        Polyhedron h = StandardPolyhedra.pyritohedron(.5);
        PointSymmetryGroup3 g = new PointSymmetryGroup3(h);
        Assertions.assertEquals("Th",g.toString());
    }

    @Test
    void TestPolyhedronO() throws GeometryException, InvalidRational, ZeroDenominator {
        SphericalPolyhedron s = new WythoffSymbol("|2 3 4").uniformPolyhedron();
        Polyhedron h = new Polyhedron(s);
        PointSymmetryGroup3 g = new PointSymmetryGroup3(h);
        Assertions.assertEquals("O",g.toString());
    }

    @Test
    void TestPolyhedronOh() throws GeometryException {
        Polyhedron h = StandardPolyhedra.octahedron;
        PointSymmetryGroup3 g = new PointSymmetryGroup3(h);
        Assertions.assertEquals("Oh",g.toString());
    }

    @Test
    void TestPolyhedronI() throws GeometryException, InvalidRational, ZeroDenominator {
        SphericalPolyhedron s = new WythoffSymbol("|2 3 5").uniformPolyhedron();
        Polyhedron h = new Polyhedron(s);
        PointSymmetryGroup3 g = new PointSymmetryGroup3(h);
        Assertions.assertEquals("I",g.toString());
    }

    @Test
    void TestPolyhedronIh() throws GeometryException {
        Polyhedron h = StandardPolyhedra.icosahedron;
        PointSymmetryGroup3 g = new PointSymmetryGroup3(h);
        Assertions.assertEquals("Ih",g.toString());
    }

}