package com.sparrows.geometry.exception;

public class InvalidDihedralAngle extends GeometryException {
    public InvalidDihedralAngle(int face1, int face2) {
        super("Face " + face1 + " and face " + face2 + " are coplanar.");
    }
}
