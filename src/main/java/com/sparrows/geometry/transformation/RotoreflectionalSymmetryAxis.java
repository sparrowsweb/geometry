package com.sparrows.geometry.transformation;

import com.sparrows.geometry.geometry3.Line3;
import com.sparrows.geometry.geometry3.Point3;

public class RotoreflectionalSymmetryAxis {
    private final Line3 axis;
    private int order;
    private Point3 mirrorPoint;

    public RotoreflectionalSymmetryAxis(Line3 axis, int order, Point3 mirrorPoint) {
        this.axis = axis;
        this.order = order;
        this.mirrorPoint = mirrorPoint;
    }

    public Line3 getAxis() {
        return axis;
    }

    public int getOrder() {
        return order;
    }

    public Point3 getMirrorPoint() {
        return mirrorPoint;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return order + "-fold about " + axis.toString() + " in point " + mirrorPoint.toString();
    }

}
