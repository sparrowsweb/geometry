package com.sparrows.geometry.transformation;

import com.sparrows.geometry.geometry3.Vector3;

public class Basis3 {
    Vector3 xUnit;
    Vector3 yUnit;
    Vector3 zUnit;
    public Basis3(Vector3 xUnit, Vector3 yUnit, Vector3 zUnit) {
        this.xUnit = xUnit;
        this.yUnit = yUnit;
        this.zUnit = zUnit;
    }

    public Vector3 getXUnit() {
        return xUnit;
    }

    public Vector3 getYUnit() {
        return yUnit;
    }

    public Vector3 getZUnit() {
        return zUnit;
    }
}