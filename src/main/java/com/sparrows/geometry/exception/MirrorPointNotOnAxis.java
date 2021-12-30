package com.sparrows.geometry.exception;

public class MirrorPointNotOnAxis extends GeometryException {
    public MirrorPointNotOnAxis() {
        super("Rotoflection mirror point is not on the axis.");
    }
}
