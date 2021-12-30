package com.sparrows.geometry.utils;

import com.sparrows.geometry.exception.GeometryException;
import com.sparrows.geometry.exception.InvalidSidesDensity;
import com.sparrows.geometry.exception.NotEnoughVertices;
import com.sparrows.geometry.maths.Maths;

public abstract class GeometryUtils {

    public static void validateSidesDensity(int sides, int density) throws GeometryException {
        if (sides < 3) {
            throw new NotEnoughVertices();
        }
        if (Maths.hcf(sides, density) != 1) {
            throw new InvalidSidesDensity(sides, density);
        }
    }
}
