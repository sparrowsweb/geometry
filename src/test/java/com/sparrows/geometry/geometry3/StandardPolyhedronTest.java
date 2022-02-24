package com.sparrows.geometry.geometry3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.sparrows.geometry.exception.GeometryException;

class StandardPolyhedronTest {
    @Test
    void TestTetrahedron() throws GeometryException {
        Polyhedron h = StandardPolyhedra.TETRAHEDRON;
        Assertions.assertEquals(4,h.faceCount());
        h.validate();
        Assertions.assertTrue(h.oriented());
    }

    @Test
    void TestCube() throws GeometryException {
        Polyhedron h = StandardPolyhedra.CUBE;
        Assertions.assertEquals(6,h.faceCount());
        h.validate();
        Assertions.assertTrue(h.oriented());
    }

    @Test
    void TestOctahedron() throws GeometryException {
        Polyhedron h = StandardPolyhedra.OCTAHEDRON;
        Assertions.assertEquals(8,h.faceCount());
        h.validate();
        Assertions.assertTrue(h.oriented());
    }

    @Test
    void TestDodecahedron() throws GeometryException {
        Polyhedron h = StandardPolyhedra.DODECAHEDRON;
        Assertions.assertEquals(12,h.faceCount());
        h.validate();
        Assertions.assertTrue(h.oriented());
    }

    @Test
    void TestIcosahedron() throws GeometryException {
        Polyhedron h = StandardPolyhedra.ICOSAHEDRON;
        Assertions.assertEquals(20,h.faceCount());
        h.validate();
        Assertions.assertTrue(h.oriented());
    }

    @Test
    void TestSmallStellatedDodecahedron() throws GeometryException {
        Polyhedron h = StandardPolyhedra.SMALL_STELLATED_DODECAHEDRON;
        Assertions.assertEquals(12,h.faceCount());
        h.validate();
        Assertions.assertTrue(h.oriented());
    }

    @Test
    void TestGreatStellatedDodecahedron() throws GeometryException {
        Polyhedron h = StandardPolyhedra.GREAT_STELLATED_DODECAHEDRON;
        Assertions.assertEquals(12,h.faceCount());
        h.validate();
        Assertions.assertTrue(h.oriented());
    }

    @Test
    void TestGreatDodecahedron() throws GeometryException {
        Polyhedron h = StandardPolyhedra.GREAT_DODECAHEDRON;
        Assertions.assertEquals(12,h.faceCount());
        h.validate();
        Assertions.assertTrue(h.oriented());
    }

    @Test
    void TestGreatIcosahedron() throws GeometryException {
        Polyhedron h = StandardPolyhedra.GREAT_ICOSAHEDRON;
        Assertions.assertEquals(20,h.faceCount());
        h.validate();
        Assertions.assertTrue(h.oriented());
    }

    @Test
    void TestUniformTriangularPrism() throws GeometryException {
        Polyhedron h = StandardPolyhedra.uniformPrism(3);
        Assertions.assertEquals(5,h.faceCount());
        h.validate();
        Assertions.assertTrue(h.oriented());
    }

    @Test
    void TestUniformDecagrammicPrism() throws GeometryException {
        Polyhedron h = StandardPolyhedra.uniformPrism(10,3);
        Assertions.assertEquals(12,h.faceCount());
        h.validate();
        Assertions.assertTrue(h.oriented());
    }

    @Test
    void TestUniformSquareAntiprism() throws GeometryException {
        Polyhedron h = StandardPolyhedra.uniformAntiprism(4);
        Assertions.assertEquals(10,h.faceCount());
        h.validate();
        Assertions.assertTrue(h.oriented());
    }

    @Test
    void TestUniformDecagrammicAntiprism() throws GeometryException {
        Polyhedron h = StandardPolyhedra.uniformAntiprism(10,3);
        Assertions.assertEquals(22,h.faceCount());
        h.validate();
        Assertions.assertTrue(h.oriented());
    }
}
