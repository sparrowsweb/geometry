package com.sparrows.geometry;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.sparrows.geometry.exception.DoesNotTileSphere;
import com.sparrows.geometry.exception.GeometryException;
import com.sparrows.geometry.exception.InvalidSchwarzTriangle;
import com.sparrows.geometry.exception.InvalidWythoffSymbol;
import com.sparrows.geometry.exception.NotAPointSymmetry;
import com.sparrows.geometry.geometry2.Point2;
import com.sparrows.geometry.geometry3.*;
import com.sparrows.geometry.graphics.GeoImage;
import com.sparrows.geometry.maths.Maths;
import com.sparrows.geometry.maths.Rational;
import com.sparrows.geometry.maths.exceptions.InvalidRational;
import com.sparrows.geometry.maths.exceptions.ZeroDenominator;
import com.sparrows.geometry.spherical.SchwarzTriangle;
import com.sparrows.geometry.spherical.SphericalPolyhedron;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;

class WythoffSymbolTest {
    WythoffSymbolTest() throws InvalidSchwarzTriangle, InvalidRational, ZeroDenominator, InvalidWythoffSymbol, DoesNotTileSphere, NotAPointSymmetry {
    }

    class UniformData {
        int index;
        String name;
        List<WythoffSymbol> wythoffSymbols;
        int faces;
        int vertices;
        int edges;
        boolean hemiPolyhedron;
        boolean orientable;
        PointSymmetryGroup3 symmetry;

        public UniformData(int index, String name, List<WythoffSymbol> wythoffSymbols, int faces, int vertices, int edges, boolean hemiPolyhedron, boolean orientable, String symmetry) throws NotAPointSymmetry {
            this.index = index;
            this.name = name;
            this.wythoffSymbols = wythoffSymbols;
            this.faces = faces;
            this.vertices = vertices;
            this.edges = edges;
            this.hemiPolyhedron = hemiPolyhedron;
            this.orientable = orientable;
            this.symmetry = new PointSymmetryGroup3(symmetry);
        }
    }

    private final List<UniformData> uniformData = Arrays.asList(
            // p|q r
            // regular p|q 2
            new UniformData(1,"tetrahedron",
                    Arrays.asList(new WythoffSymbol("3|2 3"),new WythoffSymbol("|2 2 2")), 4,4,6,false, true, "Td"),
            new UniformData(5,"octahedron",
                    Arrays.asList(new WythoffSymbol("4|2 3"),new WythoffSymbol("|2 2 3")), 8,6,12,false, true, "Oh"),
            new UniformData(6,"cube",
                    Collections.singletonList(new WythoffSymbol("3|2 4")), 6,8,12,false, true, "Oh"),
            new UniformData(22,"icosahedron",
                    Arrays.asList(new WythoffSymbol("5|2 3"),new WythoffSymbol("|2 3 3")), 20,12,30,false, true, "Ih"),
            new UniformData(23,"dodecahedron",
                    Collections.singletonList(new WythoffSymbol("3|2 5")), 12,20,30,false, true, "Ih"),
            new UniformData(53,"great icosahedron",
                    Collections.singletonList(new WythoffSymbol("5/2|2 3")), 20,12,30,false, true, "Ih"),
            new UniformData(35,"great dodecahedron",
                    Collections.singletonList(new WythoffSymbol("5/2|2 5")), 12,12,30,false, true, "Ih"),
            new UniformData(34,"small stellated dodecahedron",
                    Collections.singletonList(new WythoffSymbol("5|2 5/2")), 12,12,30,false, true, "Ih"),
            new UniformData(52,"great stellated dodecahedron",
                    Collections.singletonList(new WythoffSymbol("3|2 5/2")), 12,20,30,false, true, "Ih"),
            // quasi-regular
            // semi-regular 2|p q
            new UniformData(7,"cuboctahedron",
                    Arrays.asList(new WythoffSymbol("2|3 4"),new WythoffSymbol("3 3|2")), 14,12,24,false, true, "Oh"),
            new UniformData(24,"icosidodecahedron",
                    Collections.singletonList(new WythoffSymbol("2|3 5")), 32,30,60,false, true, "Ih"),
            new UniformData(54,"great icosidodecahedron",
                    Arrays.asList(new WythoffSymbol("2|3 5/2"),new WythoffSymbol("2|3 5/3"),new WythoffSymbol("2|3/2 5/2"),new WythoffSymbol("2|3/2 5/3")), 32,30,60,false, true, "Ih"),
            new UniformData(36,"dodecadodecahedron",
                    Arrays.asList(new WythoffSymbol("2|5 5/2"),new WythoffSymbol("2|5 5/3"),new WythoffSymbol("2|5/2 5/4"),new WythoffSymbol("2|5/3 5/4")), 24,30,60,false, true, "Ih"),
            // ditrigonal 3|p q or 3/2|p q
            new UniformData(30,"small ditrigonal icosidodecahedron",
                    Collections.singletonList(new WythoffSymbol("3|5/2 3")), 32,20,60,false, true, "Ih"),
            new UniformData(41,"ditrigonal dodecadodecahedron",
                    Arrays.asList(new WythoffSymbol("3|5/3 5"),new WythoffSymbol("3/2|5 5/2"),new WythoffSymbol("3/2|5/3 5/4"),new WythoffSymbol("3|5/2 5/4")), 24,20,60,false, true, "Ih"),
            new UniformData(47,"great ditrigonal icosidodecahedron",
                    Arrays.asList(new WythoffSymbol("3/2|3 5"),new WythoffSymbol("3|3/2 5"),new WythoffSymbol("3|3 5/4"),new WythoffSymbol("3/2|3/2 5/4")), 32,20,60,false, true, "Ih"),

            // p q|r
            // truncated regular 2 p|q
            new UniformData(2,"truncated tetrahedron",
                    Collections.singletonList(new WythoffSymbol("2 3|3")), 8,12,18,false, true, "Td"),
            new UniformData(8,"truncated octahedron",
                    Arrays.asList(new WythoffSymbol("2 4|3"),new WythoffSymbol("3 3 2|")), 14,24,36,false, true, "Oh"),
            new UniformData(9,"truncated cube",
                    Collections.singletonList(new WythoffSymbol("2 3|4")), 14,24,36,false, true, "Oh"),
            new UniformData(25,"truncated icosahedron",
                    Collections.singletonList(new WythoffSymbol("2 5|3")), 32,60,90,false, true, "Ih"),
            new UniformData(26,"truncated dodecahedron",
                    Collections.singletonList(new WythoffSymbol("2 3|5")), 32,60,90,false, true, "Ih"),
            new UniformData(37,"truncated great dodecahedron",
                    Arrays.asList(new WythoffSymbol("2 5/2|5"),new WythoffSymbol("2 5/3|5")), 24,60,90,false, true, "Ih"),
            new UniformData(55,"truncated great icosahedron",
                    Arrays.asList(new WythoffSymbol("2 5/2|3"),new WythoffSymbol("2 5/3|3")), 32,60,90,false, true, "Ih"),
            new UniformData(19,"stellated truncated hexahedron",
                    Arrays.asList(new WythoffSymbol("2 3|4/3"),new WythoffSymbol("2 3/2|4/3")), 14,24,36,false, true, "Oh"),
            new UniformData(58,"small stellated truncated dodecahedron",
                    Arrays.asList(new WythoffSymbol("2 5|5/3"),new WythoffSymbol("2 5/4|5/3")), 24,60,90,false, true, "Ih"),
            new UniformData(66,"great stellated truncated dodecahedron",
                    Collections.singletonList(new WythoffSymbol("2 3|5/3")), 32,60,90,false, true, "Ih"),
            // hemipolyhedra p p'|q
            new UniformData(4,"tetrahemihexahedron",
                    Collections.singletonList(new WythoffSymbol("3/2 3|2")), 7,6,12,true, false,"Td"),
            new UniformData(3,"octahemioctahedron",
                    Collections.singletonList(new WythoffSymbol("3/2 3|3")), 12,12,24,true, true,"Oh"),
            new UniformData(15,"cubohemioctahedron",
                    Collections.singletonList(new WythoffSymbol("4/3 4|3")), 10,12,24,true, false,"Oh"),
            new UniformData(49,"small icosihemidodecahedron",
                    Collections.singletonList(new WythoffSymbol("3/2 3|5")), 26,30,60,true, false, "Ih"),
            new UniformData(51,"small dodecahemidodecahedron",
                    Collections.singletonList(new WythoffSymbol("5/4 5|5")), 18,30,60,true, false, "Ih"),
            new UniformData(71,"great icosihemidodecahedron",
                    Collections.singletonList(new WythoffSymbol("3/2 3|5/3")), 26,30,60,true, false, "Ih"),
            new UniformData(70,"great dodecahemidodecahedron",
                    Collections.singletonList(new WythoffSymbol("5/3 5/2|5/3")), 18,30,60,true, false, "Ih"),
            new UniformData(65,"great dodecahemicosahedron",
                    Collections.singletonList(new WythoffSymbol("5/4 5|3")), 22,30,60,true, false, "Ih"),
            new UniformData(62,"small dodecahemicosahedron",
                    Collections.singletonList(new WythoffSymbol("5/3 5/2|3")), 22,30,60,true, false, "Ih"),
            // rhombic quasi-regular p q|r
            new UniformData(10,"rhombicuboctahedron",
                    Collections.singletonList(new WythoffSymbol("3 4|2")), 26,24,48,false, true, "Oh"),
            new UniformData(13,"small cubicuboctahedron",
                    Arrays.asList(new WythoffSymbol("3/2 4|4"),new WythoffSymbol("3 4/3|4")), 20,24,48,false, true, "Oh"),
            new UniformData(14,"great cubicuboctahedron",
                    Arrays.asList(new WythoffSymbol("3 4|4/3"),new WythoffSymbol("4 3/2|4")), 20,24,48,false, true, "Oh"),
            new UniformData(17,"nonconvex great rhombicuboctahedron",
                    Arrays.asList(new WythoffSymbol("3/2 4|2"),new WythoffSymbol("3 4/3|2")), 26,24,48,false, true, "Oh"),
            new UniformData(27,"rhombicosidodecahedron",
                    Collections.singletonList(new WythoffSymbol("3 5|2")), 62,60,120,false, true, "Ih"),
            new UniformData(33,"small dodecicosidodecahedron",
                    Arrays.asList(new WythoffSymbol("3/2 5|5"),new WythoffSymbol("3 5/4|5")), 44,60,120,false, true, "Ih"),
            new UniformData(61,"great dodecicosidodecahedron",
                    Arrays.asList(new WythoffSymbol("5/2 3|5/3"),new WythoffSymbol("5/3 3/2|5/3")), 44,60,120,false, true, "Ih"),
            new UniformData(67,"nonconvex great rhombicosidodecahedron",
                    Arrays.asList(new WythoffSymbol("5/3 3|2"),new WythoffSymbol("5/2 3/2|2")), 62,60,120,false, true, "Ih"),
            new UniformData(31,"small icosicosidodecahedron",
                    Collections.singletonList(new WythoffSymbol("5/2 3|3")), 52,60,120,false, true, "Ih"),
            new UniformData(43,"small ditrigonal dodecicosidodecahedron",
                    Arrays.asList(new WythoffSymbol("5/3 3|5"),new WythoffSymbol("5/2 3/2|5")), 44,60,120,false, true, "Ih"),
            new UniformData(38,"rhombidodecadodecahedron",
                    Collections.singletonList(new WythoffSymbol("5/2 5|2")), 54,60,120,false, true, "Ih"),
            new UniformData(44,"icosidodecadodecahedron",
                    Arrays.asList(new WythoffSymbol("5/3 5|3"),new WythoffSymbol("5/2 5/4|3")), 44,60,120,false, true, "Ih"),
            new UniformData(42,"great ditrigonal dodecicosidodecahedron",
                    Arrays.asList(new WythoffSymbol("3 5|5/3"),new WythoffSymbol("5/4 3/2|5/3")), 44,60,120,false, true, "Ih"),
            new UniformData(48,"great icosicosidodecahedron",
                    Arrays.asList(new WythoffSymbol("3/2 5|3"),new WythoffSymbol("3 5/4|3")), 52,60,120,false, true, "Ih"),

            // p q r|
            new UniformData(11,"truncated cuboctahedron",
                    Collections.singletonList(new WythoffSymbol("2 3 4|")), 26,48,72,false, true, "Oh"),
            new UniformData(20,"great truncated cuboctahedron",
                    Collections.singletonList(new WythoffSymbol("2 3 4/3|")), 26,48,72,false, true, "Oh"),
            new UniformData(16,"cubitruncated cuboctahedron",
                    Collections.singletonList(new WythoffSymbol("3 4 4/3|")), 20,48,72,false, true, "Oh"),
            new UniformData(28,"truncated icosidodecahedron",
                    Collections.singletonList(new WythoffSymbol("2 3 5|")), 62,120,180,false, true, "Ih"),
            new UniformData(68,"great truncated icosidodecahedron",
                    Collections.singletonList(new WythoffSymbol("2 3 5/3|")), 62,120,180,false, true, "Ih"),
            new UniformData(45,"icositruncated dodecadodecahedron",
                    Collections.singletonList(new WythoffSymbol("3 5 5/3|")), 44,120,180,false, true, "Ih"),
            new UniformData(59,"truncated dodecadodecahedron",
                    Collections.singletonList(new WythoffSymbol("2 5 5/3|")), 54,120,180,false, true, "Ih"),

            // p q (r s)|
            new UniformData(18,"small rhombihexahedron",
                    Arrays.asList(new WythoffSymbol("2 4 3/2|")/*,new WythoffSymbol("2 4 4/2|")*/), 18,24,48,false, false, "Oh"),
            new UniformData(21,"great rhombihexahedron",
                    Arrays.asList(new WythoffSymbol("2 4/3 3/2|")/*,new WythoffSymbol("2 4/3 4/2|")*/), 18,24,48,false, false, "Oh"),
            new UniformData(56,"rhombicosahedron",
                    Arrays.asList(new WythoffSymbol("2 3 5/4|"),new WythoffSymbol("2 3 5/2|")), 50,60,120,false, false, "Ih"),
            new UniformData(73,"great rhombidodecahedron",
                    Arrays.asList(new WythoffSymbol("2 5/3 3/2|"),new WythoffSymbol("2 5/3 5/4|")), 42,60,120,false, false, "Ih"),
            new UniformData(63,"great dodecicosahedron",
                    Arrays.asList(new WythoffSymbol("3 5/3 3/2|"),new WythoffSymbol("3 5/3 5/2|")), 32,60,120,false, false, "Ih"),
            new UniformData(39,"small rhombidodecahedron",
                    Arrays.asList(new WythoffSymbol("2 5 3/2|"),new WythoffSymbol("2 5 5/2|")), 42,60,120,false, false, "Ih"),
            new UniformData(50,"small dodecicosahedron",
                    Arrays.asList(new WythoffSymbol("3 5 3/2|"),new WythoffSymbol("3 5 5/4|")), 32,60,120,false, false, "Ih"),

            // |p q r snubs
            new UniformData(12,"snub cube",
                    Collections.singletonList(new WythoffSymbol("|2 3 4")), 38,24,60,false, true, "O"),
            new UniformData(32,"small snub icosicosidodecahedron",
                    Collections.singletonList(new WythoffSymbol("|5/2 3 3")), 112,60,180,false, true,"Ih"),
            new UniformData(72,"small retrosnub icosicosidodecahedron",
                    Collections.singletonList(new WythoffSymbol("|3/2 3/2 5/2")), 112,60,180,false, true,"Ih"),
            new UniformData(29,"snub dodecahedron",
                    Collections.singletonList(new WythoffSymbol("|2 3 5")), 92,60,150,false, true, "I"),
            new UniformData(40,"snub dodecadodecahedron",
                    Collections.singletonList(new WythoffSymbol("|2 5/2 5")), 84,60,150,false, true,"I"),
            new UniformData(60,"inverted snub dodecadodecahedron",
                    Collections.singletonList(new WythoffSymbol("|5/3 2 5")), 84,60,150,false, true,"I"),
            new UniformData(57,"great snub icosidodecahedron",
                    Collections.singletonList(new WythoffSymbol("|2 5/2 3")), 92,60,150,false, true,"I"),
            new UniformData(69,"great inverted snub icosidodecahedron",
                    Collections.singletonList(new WythoffSymbol("|5/3 2 3")), 92,60,150,false, true,"I"),
            new UniformData(74,"great retrosnub icosidodecahedron",
                    Collections.singletonList(new WythoffSymbol("|2 3/2 5/3")), 92,60,150,false, true,"I"),
            new UniformData(46,"snub icosidodecadodecahedron",
                    Collections.singletonList(new WythoffSymbol("|5/3 3 5")), 104,60,180,false, true,"I"),
            new UniformData(64,"great snub dodecicosidodecahedron",
                    Collections.singletonList(new WythoffSymbol("|5/3 5/2 3")), 104,60,180,false, true,"I"),

            // dihedral
            new UniformData(-1,"pentagonal prism",
                    Collections.singletonList(new WythoffSymbol("2 5|2")), 7,10,15,false, true, "D5h"),
            new UniformData(-2,"pentagonal antiprism",
                    Collections.singletonList(new WythoffSymbol("|2 2 5")), 12,10,20,false, true, "D5d"),
            new UniformData(-3,"pentagrammic prism",
                    Collections.singletonList(new WythoffSymbol("2 5/2|2")), 7,10,15,false, true, "D5h"),
            new UniformData(-4,"pentagrammic antiprism",
                    Collections.singletonList(new WythoffSymbol("|2 2 5/2")), 12,10,20,false, true, "D5h"),
            new UniformData(-5,"pentagrammic crossed antiprism",
                    Collections.singletonList(new WythoffSymbol("|2 2 5/3")), 12,10,20,false, true, "D5d")


    );

    @Test
    void testWythoffSymbol() throws DoesNotTileSphere, InvalidSchwarzTriangle, ZeroDenominator {
        WythoffSymbol w = new WythoffSymbol(new SchwarzTriangle(new Rational(2),new Rational(3),new Rational(5)), WythoffSymbol.Type.ONE);
        Assertions.assertEquals(30,w.vertexCount());
    }

    @Test
    void testConstructorStringTypeOne() throws InvalidRational, ZeroDenominator, InvalidWythoffSymbol, InvalidSchwarzTriangle, DoesNotTileSphere {
        WythoffSymbol w = new WythoffSymbol("|2 3 4");
        Assertions.assertEquals(WythoffSymbol.Type.SNUB, w.getType());
        Assertions.assertEquals(new Rational(2),w.getSchwarzTriangle().getP());
        Assertions.assertEquals(new Rational(3),w.getSchwarzTriangle().getQ());
        Assertions.assertEquals(new Rational(4),w.getSchwarzTriangle().getR());
    }

    @Test
    void testConstructorStringTypeTwo() throws InvalidRational, ZeroDenominator, InvalidWythoffSymbol, InvalidSchwarzTriangle, DoesNotTileSphere {
        WythoffSymbol w = new WythoffSymbol("2|3 5/2");
        Assertions.assertEquals(WythoffSymbol.Type.ONE, w.getType());
        Assertions.assertEquals(new Rational(2),w.getSchwarzTriangle().getP());
        Assertions.assertEquals(new Rational(3),w.getSchwarzTriangle().getQ());
        Assertions.assertEquals(new Rational(5,2),w.getSchwarzTriangle().getR());
    }

    @Test
    void testConstructorStringTypeThree() throws InvalidRational, ZeroDenominator, InvalidWythoffSymbol, InvalidSchwarzTriangle, DoesNotTileSphere {
        WythoffSymbol w = new WythoffSymbol("2  3/2 |   5/3");
        Assertions.assertEquals(WythoffSymbol.Type.TWO, w.getType());
        Assertions.assertEquals(new Rational(2),w.getSchwarzTriangle().getP());
        Assertions.assertEquals(new Rational(3,2),w.getSchwarzTriangle().getQ());
        Assertions.assertEquals(new Rational(5,3),w.getSchwarzTriangle().getR());
    }

    @Test
    void testConstructorStringTypeFour() throws InvalidRational, ZeroDenominator, InvalidWythoffSymbol, InvalidSchwarzTriangle, DoesNotTileSphere {
        WythoffSymbol w = new WythoffSymbol("2 3/2 5/3|");
        Assertions.assertEquals(WythoffSymbol.Type.THREE, w.getType());
        Assertions.assertEquals(new Rational(2),w.getSchwarzTriangle().getP());
        Assertions.assertEquals(new Rational(3,2),w.getSchwarzTriangle().getQ());
        Assertions.assertEquals(new Rational(5,3),w.getSchwarzTriangle().getR());
    }

    @Test
    void testUniforms() throws GeometryException {
        for (var u : uniformData) {
            System.out.println(u.index+ " " + u.name);
            for (var w : u.wythoffSymbols) {
                System.out.println("  " + w.toString());
                SphericalPolyhedron h = w.uniformPolyhedron();
                Polyhedron h2 = new Polyhedron(h);
                h2.validate();
                Assertions.assertEquals(u.faces, h2.faceCount());
                Assertions.assertEquals(u.vertices, h2.vertexCount());
                Assertions.assertEquals(u.edges, h2.edgeCount());
                Assertions.assertTrue(h2.isFaceRegular());
                if (u.orientable) {
                    Assertions.assertTrue(h2.oriented());
                }

                PointSymmetryGroup3 g = new PointSymmetryGroup3(h2);
                Assertions.assertEquals(u.symmetry.toString(),g.toString());
                System.out.println(g);
            }
        }
    }

    @Test
    void testStuff() throws GeometryException {
        for (var sp : SchwarzTriangle.getAll()) {
            SchwarzTriangle sq = new SchwarzTriangle(sp.getQ(), sp.getR(), sp.getP());
            SchwarzTriangle sr = new SchwarzTriangle(sp.getR(), sp.getP(), sp.getQ());

            tryWythoff(sp, WythoffSymbol.Type.ONE);
            if (sp.getR() != sp.getP()) {
                tryWythoff(sr, WythoffSymbol.Type.ONE);
            }
            if (sp.getQ() != sp.getP() && sp.getQ() != sp.getR()) {
                tryWythoff(sq, WythoffSymbol.Type.ONE);
            }

            tryWythoff(sp, WythoffSymbol.Type.TWO);
            if (sp.getP() != sp.getR()) {
                tryWythoff(sq, WythoffSymbol.Type.TWO);
            }
            if (sp.getQ() != sp.getR() && sp.getQ() != sp.getP()) {
                tryWythoff(sr, WythoffSymbol.Type.TWO);
            }

            tryWythoff(sp, WythoffSymbol.Type.THREE);
            tryWythoff(sp, WythoffSymbol.Type.SNUB);

        }
        Assertions.fail();
    }

    @Test
    void stuff2() throws IOException, GeometryException {
        GeoImage geoImage= new GeoImage(600,400,-3,-2,6);
/*
        geoImage.setColour(Color.BLUE);
        geoImage.setStroke(new BasicStroke(5,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
        geoImage.drawLine(new Point3(-1,-.5,9),new Point3(1,.5,999));

        geoImage.setColour(Color.GREEN);
        geoImage.setStroke(new BasicStroke(10,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
        geoImage.drawPoint(new Point3(0,-.5,9));

        Polygon3 poly = new Polygon3(new Point3(-1,-1,0),new Point3(-1,1,0),new Point3(1,1,0),new Point3(1,-1,0));
        geoImage.fillPolygon(poly);
        geoImage.setColour(Color.RED);
        geoImage.setStroke(new BasicStroke(1,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
        geoImage.drawPolygon(poly);*/

        Polyhedron h = StandardPolyhedra.uniformPrism(5).rotate(Line3.xAxis, Maths.PI4);
        geoImage.drawPolyhedron(h);

        geoImage.write("c:\\Users\\tmhoo\\Documents\\test.png");
    }

    void tryWythoff(SchwarzTriangle s, WythoffSymbol.Type t) {
        WythoffSymbol w = new WythoffSymbol(s,t);
        if (!w.toString().equals("|2 3 3/2")) return;//////////////////////////
        System.out.print(w + "   ");
        SphericalPolyhedron h = null;
        try {
            h = w.uniformPolyhedron();
            h.findFaceData();
            var x = h.split();
            if (x.size() > 1) {
                int zzz = 0;
            }
        } catch (GeometryException e) {
            System.out.println(e.getMessage());
            return;
        }
        try {
            h.validate();
        } catch (GeometryException e) {
            System.out.println("FAIL: " + e.getMessage());
            return;
        }
        Assertions.assertTrue(h.isFaceRegular());
        System.out.println("OK");
    }

}
