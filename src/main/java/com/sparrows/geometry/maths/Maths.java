package com.sparrows.geometry.maths;

import java.util.Iterator;

import com.sparrows.geometry.exception.InvalidVertexAngle;
import com.sparrows.geometry.exception.InvalidVertexAngles;

public abstract class Maths {

    public static double ERROR_MARGIN = 0.0000000001;

    public static final double SQRT2 = Math.sqrt(2);
    public static final double SQRT3 = Math.sqrt(3);
    public static final double SQRT5 = Math.sqrt(5);
    public static final double SQRT3_2 = Math.sqrt(3)/2;
    public static final double PI2 = Math.PI/2;
    public static final double PI3 = Math.PI/3;
    public static final double PI4 = Math.PI/4;
    public static final double PI6 = Math.PI/6;

    public static double square (double x) {
        return x*x;
    }

    public static boolean equal (double d1 ,double d2) {
        return Math.abs(d1-d2) <= ERROR_MARGIN;
    }
    public static boolean lessThan (double d1, double d2) {
        return (d1-d2 < -ERROR_MARGIN);
    }
    public static boolean greaterThan (double d1, double d2) {
        return (d1-d2 > ERROR_MARGIN);
    }
    public static boolean lessThanOrEqual (double d1, double d2) {
        return (d1-d2 <= ERROR_MARGIN);
    }
    public static boolean greaterThanOrEqual (double d1, double d2) {
        return (d1-d2 >= -ERROR_MARGIN);
    }

    public static int sign (double d) {
        if (equal(d,0)) {
            return 0;
        } else {
            return d < 0 ? -1 : 1;
        }
    }

    public static int hcf(int n1, int n2) {
        while (n2 != 0) {
            int temp = n1;
            n1 = n2;
            n2 = temp%n2;
        }
        return abs(n1);
    }

    public static long hcf(Long n1, Long n2) {
        while (n2 != 0) {
            Long temp = n1;
            n1 = n2;
            n2 = temp%n2;
        }
        return abs(n1);
    }

    public static int abs(int n) {
        return n >= 0 ? n : -n;
    }

    public static long abs(long n) {
        return n >= 0 ? n : -n;
    }

    public static long max(long n1,long n2,long n3) {
        return (n1 > n2) ? ((n1 > n3) ? n1 : n3) : ((n2 > n3) ? n2 : n3);
    }

    public static long max(long...n) {
        long nMax = n[0];
        for (var i = 1; i < n.length; i++) {
            if (n[i] > nMax) {
                nMax = n[i];
            }
        }
        return nMax;
    }

    public static double min(double...n) {
        double nMin = n[0];
        for (var i = 1; i < n.length; i++) {
            if (n[i] < nMin) {
                nMin = n[i];
            }
        }
        return nMin;
    }

    public static double max(double...n) {
        double nMax = n[0];
        for (var i = 1; i < n.length; i++) {
            if (n[i] > nMax) {
                nMax = n[i];
            }
        }
        return nMax;
    }

    /**
     * <p>Inverse cosine of a double variable, recognising that rounding errors may
     * have made the value slightly more than 1 or slightly less than -1.</p>
     * @param value The value of which the inverse cosine is required.
     * @return The inverse cosine.
     */
    public static double arcCosine(double value) {
        if (equal(value,1)) {
            return 0;
        } else if (equal(value,-1)) {
            return Math.PI;
        } else {
            return Math.acos(value);
        }
    }

    /**
     * <p>Calculates the side length of a spherical triangle, given the three angles.</p>
     * @param angleA angle opposite side a
     * @param angleB angle opposite side b
     * @param angleC angle opposite side c
     * @return the length of side a
     * @throws InvalidVertexAngle if a vertex angle is less than or equal to 0, equal to π or greater than or equal to 2π
     * @throws InvalidVertexAngles if sum of vertex angles is less than or equal to π or greater than or equal to 5π
     */
    public static double sphericalTriangleSideFromAngles(double angleA, double angleB, double angleC) throws InvalidVertexAngle, InvalidVertexAngles {
        if (Maths.lessThanOrEqual(angleA,0) || Maths.equal(angleA,Math.PI) || Maths.greaterThanOrEqual(angleA,2*Math.PI) ||
                Maths.lessThanOrEqual(angleB,0) || Maths.equal(angleB,Math.PI) || Maths.greaterThanOrEqual(angleB,2*Math.PI) ||
                Maths.lessThanOrEqual(angleC,0) || Maths.equal(angleC,Math.PI) || Maths.greaterThanOrEqual(angleC,2*Math.PI)) {
            throw new InvalidVertexAngle();
        }
        if (Maths.lessThanOrEqual(angleA+angleB+angleC,Math.PI) || Maths.greaterThanOrEqual(angleA+angleB+angleC,5*Math.PI)) {
            throw new InvalidVertexAngles();
        }
        double cosine = (Math.cos(angleA) + Math.cos(angleB) * Math.cos(angleC)) / (Math.sin(angleB) * Math.sin(angleC));
        double sideLength = Maths.arcCosine(cosine);
        if (angleA > Math.PI) {
            sideLength = 2*Math.PI - sideLength;
        }
        return sideLength;
    }

    /**
     * <p>Calculates the angle of a spherical triangle, given the three sides.</p>
     * @param sideA length of side a
     * @param sideB length of side b
     * @param sideC length of side c
     * @return the angle opposite side a
     * @throws InvalidVertexAngle if a vertex angle is less than or equal to 0, equal to π or greater than or equal to 2π
     * @throws InvalidVertexAngles if sum of vertex angles is less than or equal to π or greater than or equal to 5π
     */
    public static double sphericalTriangleAngleFromSides(double sideA, double sideB, double sideC) {

        if (Maths.lessThanOrEqual(sideA,0) || Maths.lessThanOrEqual(sideB,0) || Maths.lessThanOrEqual(sideC,0)) {
            throw new IllegalArgumentException("Side lengths must be greater than zero.");
        }
        if (Maths.greaterThan(sideA,sideB+sideC) || Maths.greaterThan(sideB,sideC+sideA) || Maths.greaterThan(sideC,sideA+sideB)) {
            throw new IllegalArgumentException("Not a triangle.");
        }
        return Maths.arcCosine(
                (Math.cos(sideA) - Math.cos(sideB) * Math.cos(sideC)) / (Math.sin(sideB) * Math.sin(sideC))
        );
    }

    public static boolean twoOrMoreTrue(boolean a, boolean b, boolean c) {
        return  (a && (b || c) || b && c);
    }

    public static boolean coprime(int a, int b) {
        return hcf(a,b) == 1;
    }

    public static Iterator<Integer> coprimes(int n) {
        return new Iterator<Integer>() {
            Integer last = 0;
            @Override
            public boolean hasNext() {
                Integer saveLast = last;
                Integer next = next();
                last = saveLast;
                return (next != null);
            }

            @Override
            public Integer next() {
                for (int d = last+1; d < n; d++) {
                    if (coprime(n,d)) {
                        last = d;
                        return d;
                    }
                }
                return null;
            }
        };
    }
}
