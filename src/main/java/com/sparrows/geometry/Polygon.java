package com.sparrows.geometry;

public abstract class Polygon {
    public static double inradius(int sides, int density) {
        return .5/Math.tan(Math.PI*density/sides);
    }

    public static double circumradius(int sides, int density) {
        return .5/Math.sin(Math.PI*density/sides);
    }

    public static double edgeInternalAngle(int sides, int density) {
        return Math.PI - Math.PI*2*density/sides;
    }

    public static double edgeExternalAngle(int sides, int density) {
        return Math.PI*2*density/sides;
    }

    public static double edgeAngleSubtended(int sides, int density) {
        return Math.PI*2*density/sides;
    }

}
