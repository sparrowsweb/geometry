package com.sparrows.geometry.exception;

import com.sparrows.geometry.geometry3.LineSegment3;
import com.sparrows.geometry.spherical.Arc;

public class InvalidEdgeException extends IllegalArgumentException {
    public InvalidEdgeException(LineSegment3 s, int faces) {
        super("Edge " + s.toString() + " borders " + faces + " faces.");
    }
    public InvalidEdgeException(Arc a, int faces) {
        super("Edge " + a.toString() + " borders " + faces + " faces.");
    }
}
