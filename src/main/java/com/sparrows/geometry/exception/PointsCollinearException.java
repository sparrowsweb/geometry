package com.sparrows.geometry.exception;

public class PointsCollinearException extends GeometryException {
    public PointsCollinearException() {
        super("Points are collinear.");
    }
}
