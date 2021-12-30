package com.sparrows.geometry.spherical;

import com.sparrows.geometry.exception.DoesNotTileSphere;
import com.sparrows.geometry.exception.InvalidSchwarzTriangle;
import com.sparrows.geometry.maths.Rational;
import com.sparrows.geometry.maths.exceptions.ZeroDenominator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SchwarzTriangleTest {
    @Test
    void testSchwarzTriangles() throws DoesNotTileSphere, InvalidSchwarzTriangle, ZeroDenominator {
        // density 1
        SchwarzTriangle s = new SchwarzTriangle(new Rational(2),new Rational(2),new Rational(3));
        Assertions.assertEquals(SchwarzTriangle.SymmetryGroup.DIHEDRAL,s.getSymmetryGroup());
        Assertions.assertEquals(12,s.getSymmetryGroupOrder());
        Assertions.assertEquals(1,s.getTilingDensity());

        s = new SchwarzTriangle(new Rational(2),new Rational(2),new Rational(99));
        Assertions.assertEquals(SchwarzTriangle.SymmetryGroup.DIHEDRAL,s.getSymmetryGroup());
        Assertions.assertEquals(396,s.getSymmetryGroupOrder());
        Assertions.assertEquals(1,s.getTilingDensity());

        s = new SchwarzTriangle(new Rational(2),new Rational(2),new Rational(99,7));
        Assertions.assertEquals(SchwarzTriangle.SymmetryGroup.DIHEDRAL,s.getSymmetryGroup());
        Assertions.assertEquals(396,s.getSymmetryGroupOrder());
        Assertions.assertEquals(7,s.getTilingDensity());

        s = new SchwarzTriangle(new Rational(2),new Rational(3),new Rational(3));
        Assertions.assertEquals(SchwarzTriangle.SymmetryGroup.TETRAHEDRAL,s.getSymmetryGroup());
        Assertions.assertEquals(24,s.getSymmetryGroupOrder());
        Assertions.assertEquals(1,s.getTilingDensity());

        s = new SchwarzTriangle(new Rational(2),new Rational(3),new Rational(4));
        Assertions.assertEquals(SchwarzTriangle.SymmetryGroup.OCTAHEDRAL,s.getSymmetryGroup());
        Assertions.assertEquals(48,s.getSymmetryGroupOrder());
        Assertions.assertEquals(1,s.getTilingDensity());

        s = new SchwarzTriangle(new Rational(2),new Rational(3),new Rational(5));
        Assertions.assertEquals(SchwarzTriangle.SymmetryGroup.ICOSAHEDRAL,s.getSymmetryGroup());
        Assertions.assertEquals(120,s.getSymmetryGroupOrder());
        Assertions.assertEquals(1,s.getTilingDensity());

        // density 2
        s = new SchwarzTriangle(new Rational(3,2),new Rational(3),new Rational(3));
        Assertions.assertEquals(SchwarzTriangle.SymmetryGroup.TETRAHEDRAL,s.getSymmetryGroup());
        Assertions.assertEquals(24,s.getSymmetryGroupOrder());
        Assertions.assertEquals(2,s.getTilingDensity());

        s = new SchwarzTriangle(new Rational(3,2),new Rational(4),new Rational(4));
        Assertions.assertEquals(SchwarzTriangle.SymmetryGroup.OCTAHEDRAL,s.getSymmetryGroup());
        Assertions.assertEquals(48,s.getSymmetryGroupOrder());
        Assertions.assertEquals(2,s.getTilingDensity());

        s = new SchwarzTriangle(new Rational(3,2),new Rational(5),new Rational(5));
        Assertions.assertEquals(SchwarzTriangle.SymmetryGroup.ICOSAHEDRAL,s.getSymmetryGroup());
        Assertions.assertEquals(120,s.getSymmetryGroupOrder());
        Assertions.assertEquals(2,s.getTilingDensity());

        s = new SchwarzTriangle(new Rational(5,2),new Rational(3),new Rational(3));
        Assertions.assertEquals(SchwarzTriangle.SymmetryGroup.ICOSAHEDRAL,s.getSymmetryGroup());
        Assertions.assertEquals(120,s.getSymmetryGroupOrder());
        Assertions.assertEquals(2,s.getTilingDensity());

        // density 3
        s = new SchwarzTriangle(new Rational(2),new Rational(3,2),new Rational(3));
        Assertions.assertEquals(SchwarzTriangle.SymmetryGroup.TETRAHEDRAL,s.getSymmetryGroup());
        Assertions.assertEquals(24,s.getSymmetryGroupOrder());
        Assertions.assertEquals(3,s.getTilingDensity());

        s = new SchwarzTriangle(new Rational(2),new Rational(5,2),new Rational(5));
        Assertions.assertEquals(SchwarzTriangle.SymmetryGroup.ICOSAHEDRAL,s.getSymmetryGroup());
        Assertions.assertEquals(120,s.getSymmetryGroupOrder());
        Assertions.assertEquals(3,s.getTilingDensity());

        // density 42
        s = new SchwarzTriangle(new Rational(5,4),new Rational(5,4),new Rational(5,4));
        Assertions.assertEquals(SchwarzTriangle.SymmetryGroup.ICOSAHEDRAL,s.getSymmetryGroup());
        Assertions.assertEquals(120,s.getSymmetryGroupOrder());
        Assertions.assertEquals(42,s.getTilingDensity());

        // density 99
        s = new SchwarzTriangle(new Rational(2),new Rational(2),new Rational(200,99));
        Assertions.assertEquals(SchwarzTriangle.SymmetryGroup.DIHEDRAL,s.getSymmetryGroup());
        Assertions.assertEquals(800,s.getSymmetryGroupOrder());
        Assertions.assertEquals(99,s.getTilingDensity());
    }

    @Test
    void TestFindAll() {
        var all = SchwarzTriangle.getAll();
        Assertions.assertEquals(44,all.size());
        Assertions.assertEquals(5, all.stream().filter(s -> s.getSymmetryGroup() == SchwarzTriangle.SymmetryGroup.TETRAHEDRAL).count());
        Assertions.assertEquals(7, all.stream().filter(s -> s.getSymmetryGroup() == SchwarzTriangle.SymmetryGroup.OCTAHEDRAL).count());
        Assertions.assertEquals(32, all.stream().filter(s -> s.getSymmetryGroup() == SchwarzTriangle.SymmetryGroup.ICOSAHEDRAL).count());
        System.out.println(all.size() + " found");
        for (var a : all) {
            System.out.println(a + ": tiles=" + a.getTilingSize() + " density=" + a.getTilingDensity());
        }
    }
}
