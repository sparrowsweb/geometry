package com.sparrows.geometry.geometry3;

import com.sparrows.geometry.exception.GeometryException;
import com.sparrows.geometry.exception.InvalidEdgeException;
import com.sparrows.geometry.exception.InvalidVertexException;
import com.sparrows.geometry.exception.NotEnoughFaces;
import com.sparrows.geometry.exception.NotEnoughVertices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class PolyhedronTest {
    @Test
    void TestConstructorNotEnoughFaces() throws NotEnoughVertices {
        List<Polygon3> faces = Arrays.asList(
                new Polygon3(new Point3(-1,-1,-1),new Point3(-1,-1,1),new Point3(-1,1,1),new Point3(-1,-1,1)),
                new Polygon3(new Point3(1,-1,-1),new Point3(1,-1,1),new Point3(1,1,1),new Point3(1,-1,1))
        );
        Assertions.assertThrows(NotEnoughFaces.class, () -> {
            Polyhedron h = new Polyhedron(faces);
        });
    }

    @Test
    void TestConstructor() throws NotEnoughVertices, NotEnoughFaces {
        List<Polygon3> faces = Arrays.asList(
                new Polygon3(new Point3(-1,-1,-1),new Point3(-1,-1,1),new Point3(-1,1,1),new Point3(-1,-1,1)),
                new Polygon3(new Point3(1,-1,-1),new Point3(1,-1,1),new Point3(1,1,1),new Point3(1,-1,1)),
                new Polygon3(new Point3(-1,-1,-1),new Point3(-1,-1,1),new Point3(1,-1,1),new Point3(1,-1,-1)),
                new Polygon3(new Point3(-1,1,-1),new Point3(-1,1,1),new Point3(1,1,1),new Point3(1,1,-1)),
                new Polygon3(new Point3(-1,-1,-1),new Point3(-1,-1,1),new Point3(-1,-1,-1),new Point3(-1,-1,1)),
                new Polygon3(new Point3(1,-1,-1),new Point3(1,-1,1),new Point3(1,-1,-1),new Point3(1,-1,1))
        );
        Polyhedron h = new Polyhedron(faces);
    }
/*
    @Test
    void TestValidateThreeFaces() throws GeometryException {
        List<Polygon3> faces = Arrays.asList(
                new Polygon3(new Point3(0,0,0),new Point3(1,0,0),new Point3(0,1,0)),
                new Polygon3(new Point3(0,0,0),new Point3(1,0,0),new Point3(1,1,1)),
                new Polygon3(new Point3(1,0,0),new Point3(0,1,0),new Point3(1,1,1))
        );
        Polyhedron h = new Polyhedron(faces);
        Assertions.assertThrows(NotEnoughFaces.class, h::validate);
    }
*/
    @Test
    void TestValidateTwoFacesAtVertex() throws GeometryException {
        List<Polygon3> faces = Arrays.asList(
                new Polygon3(new Point3(0,0,0),new Point3(1,0,0),new Point3(0,1,0)),
                new Polygon3(new Point3(0,0,0),new Point3(1,0,0),new Point3(1,1,1)),
                new Polygon3(new Point3(1,0,0),new Point3(0,1,0),new Point3(1,1,1)),
                new Polygon3(new Point3(-1,0,0),new Point3(0,1,0),new Point3(1,1,1))
        );
        Polyhedron h = new Polyhedron(faces);
        Assertions.assertThrows(InvalidVertexException.class, h::validate);
    }

    @Test
    void TestValidateThreeFacesAtEdge() throws GeometryException {
        List<Polygon3> faces = Arrays.asList(
                new Polygon3(new Point3(0,0,0),new Point3(1,0,0),new Point3(0,1,0)),
                new Polygon3(new Point3(0,0,0),new Point3(1,0,0),new Point3(1,1,1)),
                new Polygon3(new Point3(1,0,0),new Point3(0,1,0),new Point3(1,1,1)),
                new Polygon3(new Point3(0,0,0),new Point3(0,1,0),new Point3(1,1,1)),
                new Polygon3(new Point3(0,0,0),new Point3(0,1,0),new Point3(1,1,1))
        );
        Polyhedron h = new Polyhedron(faces);
        Assertions.assertThrows(InvalidEdgeException.class, h::validate);
    }

    @Test
    void TestValidateOK() throws GeometryException {
        List<Polygon3> faces = Arrays.asList(
                new Polygon3(new Point3(0,0,0),new Point3(1,0,0),new Point3(0,1,0)),
                new Polygon3(new Point3(0,0,0),new Point3(1,0,0),new Point3(1,1,1)),
                new Polygon3(new Point3(1,0,0),new Point3(0,1,0),new Point3(1,1,1)),
                new Polygon3(new Point3(0,0,0),new Point3(0,1,0),new Point3(1,1,1))
        );
        Polyhedron h = new Polyhedron(faces);
        h.validate();
    }

    @Test
    void TestOriented() throws GeometryException {
        List<Polygon3> faces = Arrays.asList(
                new Polygon3(new Point3(0,0,0),new Point3(1,0,0),new Point3(0,1,0)),
                new Polygon3(new Point3(1,0,0),new Point3(0,0,0),new Point3(1,1,1)),
                new Polygon3(new Point3(0,1,0),new Point3(1,0,0),new Point3(1,1,1)),
                new Polygon3(new Point3(0,0,0),new Point3(0,1,0),new Point3(1,1,1))
        );
        Polyhedron h = new Polyhedron(faces);
        Assertions.assertTrue(h.oriented());
    }

    @Test
    void TestOrientedFalse() throws GeometryException {
        List<Polygon3> faces = Arrays.asList(
                new Polygon3(new Point3(0,0,0),new Point3(1,0,0),new Point3(0,1,0)),
                new Polygon3(new Point3(1,0,0),new Point3(0,0,0),new Point3(1,1,1)),
                new Polygon3(new Point3(0,1,0),new Point3(1,0,0),new Point3(1,1,1)),
                new Polygon3(new Point3(0,0,0),new Point3(0,1,0),new Point3(1,1,1)).reverse()
        );
        Polyhedron h = new Polyhedron(faces);
        Assertions.assertFalse(h.oriented());
    }

    @Test
    void TestIdenticalOK() throws NotEnoughFaces {
        Polyhedron h1 = StandardPolyhedra.cube;
        Polyhedron h2 = new Polyhedron(h1);
        Assertions.assertTrue(h1.identical(h2));
    }

    @Test
    void TestValidateShuffledFaces() throws GeometryException {
        List<Polygon3> faces = Arrays.asList(
                new Polygon3(new Point3(0,0,0),new Point3(1,0,0),new Point3(0,1,0)),
                new Polygon3(new Point3(1,0,0),new Point3(0,1,0),new Point3(1,1,1)),
                new Polygon3(new Point3(0,0,0),new Point3(1,0,0),new Point3(1,1,1)),
                new Polygon3(new Point3(0,0,0),new Point3(0,1,0),new Point3(1,1,1))
        );
        Polyhedron h1 = new Polyhedron(faces);
        List<Polygon3> faces2 = Arrays.asList(
                new Polygon3(new Point3(0,0,0),new Point3(1,0,0),new Point3(0,1,0)),
                new Polygon3(new Point3(0,0,0),new Point3(1,0,0),new Point3(1,1,1)),
                new Polygon3(new Point3(1,0,0),new Point3(0,1,0),new Point3(1,1,1)),
                new Polygon3(new Point3(0,0,0),new Point3(0,1,0),new Point3(1,1,1))
        );
        Polyhedron h2 = new Polyhedron(faces2);
        Assertions.assertTrue(h1.identical(h2));
    }

    @Test
    void TestValidateFalse() throws GeometryException {
        List<Polygon3> faces = Arrays.asList(
                new Polygon3(new Point3(0,0,0),new Point3(1,0,0),new Point3(0,1,0)),
                new Polygon3(new Point3(1,0,0),new Point3(0,1,0),new Point3(1,1,1)),
                new Polygon3(new Point3(0,0,0),new Point3(1,0,0),new Point3(1,1,1)),
                new Polygon3(new Point3(0,0,0),new Point3(0,1,0),new Point3(1,1,1))
        );
        Polyhedron h1 = new Polyhedron(faces);
        List<Polygon3> faces2 = Arrays.asList(
                new Polygon3(new Point3(0,0,0),new Point3(1,0,0),new Point3(0,1,0)),
                new Polygon3(new Point3(1,0,0),new Point3(0,1,0),new Point3(1,1,1)),
                new Polygon3(new Point3(0,0,0),new Point3(1,0,0),new Point3(1,1,1)),
                new Polygon3(new Point3(0,0,0),new Point3(0,1,0),new Point3(1,1,1.1))
        );
        Polyhedron h2 = new Polyhedron(faces2);
        Assertions.assertFalse(h1.identical(h2));
    }

    @Test
    void TestSymmetriesTetrahedron() {
        Polyhedron h = StandardPolyhedra.tetrahedron;
        Assertions.assertFalse(h.inversionSymmetry());
        var reflectionSymmetries = h.reflectionSymmetries();
        Assertions.assertEquals(6,reflectionSymmetries.size());
        var rotationalSymmetryAxes = h.rotationAxes();
        Assertions.assertEquals(7,rotationalSymmetryAxes.size());
        var rotationSymmetries = h.rotationSymmetries();
        Assertions.assertEquals(11,rotationSymmetries.size());
        var rotoreflectionAxes = h.rotoreflectionAxes();
        Assertions.assertEquals(3,rotoreflectionAxes.size());
        var rotoreflectionSymmetries = h.rotoreflectionSymmetries();
        Assertions.assertEquals(6,rotoreflectionSymmetries.size());
        var symmetries = h.symmetries();
        Assertions.assertEquals(24,symmetries.size());
    }

    @Test
    void TestSymmetriesCube() {
        Polyhedron h = StandardPolyhedra.cube;
        Assertions.assertTrue(h.inversionSymmetry());
        var reflectionSymmetries = h.reflectionSymmetries();
        Assertions.assertEquals(9,reflectionSymmetries.size());
        var rotationalSymmetryAxes = h.rotationAxes();
        Assertions.assertEquals(13,rotationalSymmetryAxes.size());
        var rotationSymmetries = h.rotationSymmetries();
        Assertions.assertEquals(23,rotationSymmetries.size());
        var rotoreflectionAxes = h.rotoreflectionAxes();
        Assertions.assertEquals(4,rotoreflectionAxes.size());
        var rotoreflectionSymmetries = h.rotoreflectionSymmetries();
        Assertions.assertEquals(12,rotoreflectionSymmetries.size());
        var symmetries = h.symmetries();
        Assertions.assertEquals(48,symmetries.size());
    }

    @Test
    void TestSymmetriesOctahedron() {
        Polyhedron h = StandardPolyhedra.octahedron;
        Assertions.assertTrue(h.inversionSymmetry());
        var reflectionSymmetries = h.reflectionSymmetries();
        Assertions.assertEquals(9,reflectionSymmetries.size());
        var rotationalSymmetryAxes = h.rotationAxes();
        Assertions.assertEquals(13,rotationalSymmetryAxes.size());
        var rotationSymmetries = h.rotationSymmetries();
        Assertions.assertEquals(23,rotationSymmetries.size());
        var rotoreflectionAxes = h.rotoreflectionAxes();
        Assertions.assertEquals(4,rotoreflectionAxes.size());
        var rotoreflectionSymmetries = h.rotoreflectionSymmetries();
        Assertions.assertEquals(12,rotoreflectionSymmetries.size());
        var symmetries = h.symmetries();
        Assertions.assertEquals(48,symmetries.size());
    }

    @Test
    void TestSymmetriesIcosahedron() {
        Polyhedron h = StandardPolyhedra.icosahedron;
        Assertions.assertTrue(h.inversionSymmetry());
        var reflectionSymmetries = h.reflectionSymmetries();
        Assertions.assertEquals(15,reflectionSymmetries.size());
        var rotationalSymmetryAxes = h.rotationAxes();
        Assertions.assertEquals(31,rotationalSymmetryAxes.size());
        var rotationSymmetries = h.rotationSymmetries();
        Assertions.assertEquals(59,rotationSymmetries.size());
        var rotoreflectionAxes = h.rotoreflectionAxes();
        Assertions.assertEquals(16,rotoreflectionAxes.size());
        var rotoreflectionSymmetries = h.rotoreflectionSymmetries();
        Assertions.assertEquals(60,rotoreflectionSymmetries.size());
        var symmetries = h.symmetries();
        Assertions.assertEquals(120,symmetries.size());
    }

    @Test
    void TestSymmetriesDodecahedron() {
        Polyhedron h = StandardPolyhedra.icosahedron;
        Assertions.assertTrue(h.inversionSymmetry());
        var reflectionSymmetries = h.reflectionSymmetries();
        Assertions.assertEquals(15,reflectionSymmetries.size());
        var rotationalSymmetryAxes = h.rotationAxes();
        Assertions.assertEquals(31,rotationalSymmetryAxes.size());
        var rotationSymmetries = h.rotationSymmetries();
        Assertions.assertEquals(59,rotationSymmetries.size());
        var rotoreflectionAxes = h.rotoreflectionAxes();
        Assertions.assertEquals(16,rotoreflectionAxes.size());
        var rotoreflectionSymmetries = h.rotoreflectionSymmetries();
        Assertions.assertEquals(60,rotoreflectionSymmetries.size());
        var symmetries = h.symmetries();
        Assertions.assertEquals(120,symmetries.size());
    }

    @Test
    void TestSymmetriesPentagonalPrism() throws GeometryException {
        Polyhedron h = StandardPolyhedra.uniformPrism(5);
        Assertions.assertFalse(h.inversionSymmetry());
        var reflectionSymmetries = h.reflectionSymmetries();
        Assertions.assertEquals(6,reflectionSymmetries.size());
        var rotationalSymmetryAxes = h.rotationAxes();
        Assertions.assertEquals(6,rotationalSymmetryAxes.size());
        var rotationSymmetries = h.rotationSymmetries();
        Assertions.assertEquals(9,rotationSymmetries.size());
        var rotoreflectionAxes = h.rotoreflectionAxes();
        Assertions.assertEquals(0,rotoreflectionAxes.size());
        var rotoreflectionSymmetries = h.rotoreflectionSymmetries();
        Assertions.assertEquals(0,rotoreflectionSymmetries.size());
        var symmetries = h.symmetries();
        Assertions.assertEquals(20,symmetries.size());
    }

    @Test
    void TestSymmetriesPentagrammicPrism() throws GeometryException {
        Polyhedron h = StandardPolyhedra.uniformPrism(5,2);
        Assertions.assertFalse(h.inversionSymmetry());
        var reflectionSymmetries = h.reflectionSymmetries();
        Assertions.assertEquals(6,reflectionSymmetries.size());
        var rotationalSymmetryAxes = h.rotationAxes();
        Assertions.assertEquals(6,rotationalSymmetryAxes.size());
        var rotationSymmetries = h.rotationSymmetries();
        Assertions.assertEquals(9,rotationSymmetries.size());
        var rotoreflectionAxes = h.rotoreflectionAxes();
        Assertions.assertEquals(0,rotoreflectionAxes.size());
        var rotoreflectionSymmetries = h.rotoreflectionSymmetries();
        Assertions.assertEquals(0,rotoreflectionSymmetries.size());
        var symmetries = h.symmetries();
        Assertions.assertEquals(20,symmetries.size());
    }

    @Test
    void TestSymmetriesDecagrammicPrism() throws GeometryException {
        Polyhedron h = StandardPolyhedra.uniformPrism(10,3);
        Assertions.assertTrue(h.inversionSymmetry());
        var reflectionSymmetries = h.reflectionSymmetries();
        Assertions.assertEquals(11,reflectionSymmetries.size());
        var rotationalSymmetryAxes = h.rotationAxes();
        Assertions.assertEquals(11,rotationalSymmetryAxes.size());
        var rotationSymmetries = h.rotationSymmetries();
        Assertions.assertEquals(19,rotationSymmetries.size());
        var rotoreflectionAxes = h.rotoreflectionAxes();
        Assertions.assertEquals(0,rotoreflectionAxes.size());
        var rotoreflectionSymmetries = h.rotoreflectionSymmetries();
        Assertions.assertEquals(0,rotoreflectionSymmetries.size());
        var symmetries = h.symmetries();
        Assertions.assertEquals(40,symmetries.size());
    }

    @Test
    void TestSymmetries20grammicPrism() throws GeometryException {
        Polyhedron h = StandardPolyhedra.uniformPrism(20,7);
        Assertions.assertTrue(h.inversionSymmetry());
        var reflectionSymmetries = h.reflectionSymmetries();
        Assertions.assertEquals(21,reflectionSymmetries.size());
        var rotationalSymmetryAxes = h.rotationAxes();
        Assertions.assertEquals(21,rotationalSymmetryAxes.size());
        var rotationSymmetries = h.rotationSymmetries();
        Assertions.assertEquals(39,rotationSymmetries.size());
        var rotoreflectionAxes = h.rotoreflectionAxes();
        Assertions.assertEquals(0,rotoreflectionAxes.size());
        var rotoreflectionSymmetries = h.rotoreflectionSymmetries();
        Assertions.assertEquals(0,rotoreflectionSymmetries.size());
        var symmetries = h.symmetries();
        Assertions.assertEquals(80,symmetries.size());
    }

    @Test
    void TestSymmetries21grammicPrism() throws GeometryException {
        Polyhedron h = StandardPolyhedra.uniformPrism(21,10);
        Assertions.assertFalse(h.inversionSymmetry());
        var reflectionSymmetries = h.reflectionSymmetries();
        Assertions.assertEquals(22,reflectionSymmetries.size());
        var rotationalSymmetryAxes = h.rotationAxes();
        Assertions.assertEquals(22,rotationalSymmetryAxes.size());
        var rotationSymmetries = h.rotationSymmetries();
        Assertions.assertEquals(41,rotationSymmetries.size());
        var rotoreflectionAxes = h.rotoreflectionAxes();
        Assertions.assertEquals(0,rotoreflectionAxes.size());
        var rotoreflectionSymmetries = h.rotoreflectionSymmetries();
        Assertions.assertEquals(0,rotoreflectionSymmetries.size());
        var symmetries = h.symmetries();
        Assertions.assertEquals(84,symmetries.size());
    }

    @Test
    void TestSymmetriesPentagonalAntiprism() throws GeometryException {
        Polyhedron h = StandardPolyhedra.uniformAntiprism(5);
        Assertions.assertTrue(h.inversionSymmetry());
        var reflectionSymmetries = h.reflectionSymmetries();
        Assertions.assertEquals(5,reflectionSymmetries.size());
        var rotationAxes = h.rotationAxes();
        Assertions.assertEquals(6,rotationAxes.size());
        var rotationSymmetries = h.rotationSymmetries();
        Assertions.assertEquals(9,rotationSymmetries.size());
        var rotoreflectionAxes = h.rotoreflectionAxes();
        Assertions.assertEquals(1,rotoreflectionAxes.size());
        var rotoreflectionSymmetries = h.rotoreflectionSymmetries();
        Assertions.assertEquals(5,rotoreflectionSymmetries.size());
        var symmetries = h.symmetries();
        Assertions.assertEquals(20,symmetries.size());
    }

    @Test
    void TestSymmetriesPentagrammicAntiprism() throws GeometryException {
        Polyhedron h = StandardPolyhedra.uniformAntiprism(5,2);
        Assertions.assertFalse(h.inversionSymmetry());
        var reflectionSymmetries = h.reflectionSymmetries();
        Assertions.assertEquals(6,reflectionSymmetries.size());
        var rotationalSymmetryAxes = h.rotationAxes();
        Assertions.assertEquals(6,rotationalSymmetryAxes.size());
        var rotationSymmetries = h.rotationSymmetries();
        Assertions.assertEquals(9,rotationSymmetries.size());
        var rotoreflectionAxes = h.rotoreflectionAxes();
        Assertions.assertEquals(0,rotoreflectionAxes.size());
        var rotoreflectionSymmetries = h.rotoreflectionSymmetries();
        Assertions.assertEquals(0,rotoreflectionSymmetries.size());
        var symmetries = h.symmetries();
        Assertions.assertEquals(20,symmetries.size());
    }

    @Test
    void TestSymmetriesPentagrammicCrossedAntiprism() throws GeometryException {
        Polyhedron h = StandardPolyhedra.uniformAntiprism(5,3);
        Assertions.assertTrue(h.inversionSymmetry());
        var reflectionSymmetries = h.reflectionSymmetries();
        Assertions.assertEquals(5,reflectionSymmetries.size());
        var rotationalSymmetryAxes = h.rotationAxes();
        Assertions.assertEquals(6,rotationalSymmetryAxes.size());
        var rotationSymmetries = h.rotationSymmetries();
        Assertions.assertEquals(9,rotationSymmetries.size());
        var rotoreflectionAxes = h.rotoreflectionAxes();
        Assertions.assertEquals(1,rotoreflectionAxes.size());
        var rotoreflectionSymmetries = h.rotoreflectionSymmetries();
        Assertions.assertEquals(5,rotoreflectionSymmetries.size());
        var symmetries = h.symmetries();
        Assertions.assertEquals(20,symmetries.size());
    }

}
