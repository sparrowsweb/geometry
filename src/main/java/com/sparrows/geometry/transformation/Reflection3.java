package com.sparrows.geometry.transformation;
import com.sparrows.geometry.geometry3.Plane3;
import com.sparrows.geometry.geometry3.Vector3;
import com.sparrows.geometry.transformation.d3.AffineTransformation3;
import com.sparrows.geometry.transformation.d3.LinearTransformation3;

import org.ejml.simple.SimpleMatrix;

// reflection in a line
public class Reflection3 extends AffineTransformation3 {
    private Plane3 mirror;

    public Reflection3(Plane3 mirror) {
        super(matrix(mirror));
        this.mirror = mirror;
    }

    public Plane3 getMirror() {
        return mirror;
    }

    public static SimpleMatrix matrix(Plane3 mirror) {
        var shift1 = new Translation3(new Vector3(mirror.pointOnPlane()).negate());
        LinearTransformation3 reflectOrigin = new ReflectionOrigin3(mirror);
        var shift2 = new Translation3(new Vector3(mirror.pointOnPlane()));
        return new AffineTransformation3(shift1).compose(new AffineTransformation3(reflectOrigin)).compose(new AffineTransformation3(shift2)).getMatrix();
    }

    public String toString() {
        return "reflection in " + mirror.toString();
    }

}
