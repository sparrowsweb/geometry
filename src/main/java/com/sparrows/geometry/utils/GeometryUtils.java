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
}
