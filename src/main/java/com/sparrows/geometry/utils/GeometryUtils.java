package com.sparrows.geometry.utils;

import com.sparrows.geometry.maths.Maths;

public abstract class GeometryUtils {

    public static void validateSidesDensity(int sides, int density) {
        if (sides < 3) {
            throw new IllegalArgumentException("A polygon must have at least three vertices.");
        }
        if (Maths.hcf(sides, density) != 1) {
            throw new IllegalArgumentException("Inconsistent sides and density (" + sides + "/" + density + ").");
        }
    }

    // at a trivalent vertex of regular polygons, find the dihedral angle between a regular a-gon and a regular b-gon
    // when the third face is a regular c-gon
    public static double trivalentVertexAngle(int a, int b, int c)
    {
        return trivalentVertexAngle((a - 2) * Math.PI / a, (b - 2) * Math.PI / b, (c - 2) * Math.PI / c);
    }

    // at a trivalent vertex of polygons, find the dihedral angles between faces when the face angles are known
    public static double trivalentVertexAngle(double A, double B, double C) {
        if (Maths.greaterThanOrEqual(A, B + C) || Maths.greaterThanOrEqual(B, C + A) || Maths.greaterThanOrEqual(C, A + B))
            throw new IllegalArgumentException("Each angle must be less than the sum of the other two at a trivalent vertex.");
        if (Maths.greaterThanOrEqual(A + B + C, 2 * Math.PI))
            throw new IllegalArgumentException("The sum of angles at a trivalent vertex must be less than 2π.");

        return Maths.arcCosine((Math.cos(C) - Math.cos(A) * Math.cos(B)) / (Math.sin(A) * Math.sin(B)));
    }

    // the dihedral angle between triangular faces in a uniform n/d-gonal antiprism
    public static double uniformAntiprismTriangleTriangleDihedralAngle(int vertices, int density) {
        double t = Maths.square(Math.tan(Math.PI/2*density/vertices));
        double s270 = Maths.square(Math.sin(3*Math.PI/2*density/vertices));
        double s180 = Maths.square(Math.sin(Math.PI*density/vertices));
        double c = 1f - 2f/3*((3-t)/4 + s270/s180);
        return Math.acos(c);
    }

    // the dihedral angle between an end face and a triangle in a uniform n/d-gonal antiprism
    public static double uniformAntiprismTriangleEndDihedralAngle(int vertices, int density) {
        return Math.acos(-Math.tan(Math.PI/2*density/vertices) / Math.sqrt(3));
    }
}
