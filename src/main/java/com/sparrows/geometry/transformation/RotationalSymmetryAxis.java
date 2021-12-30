package com.sparrows.geometry.transformation;

import com.sparrows.geometry.geometry3.Line3;

public class RotationalSymmetryAxis {
    private final Line3 axis;
    private int order;

    public RotationalSymmetryAxis(Line3 axis, int order) {
        this.axis = axis;
        this.order = order;
    }

    public Line3 getAxis() {
        return axis;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return order + "-fold about " + axis.toString();
    }

}
