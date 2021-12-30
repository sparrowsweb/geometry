package com.sparrows.geometry.transformation;

import com.sparrows.geometry.geometry2.Line2;
import com.sparrows.geometry.geometry2.Vector2;
import org.ejml.simple.SimpleMatrix;

// reflection in a line
public class Reflection2 extends AffineTransformation2 {
    private Line2 mirror;

    public Reflection2(Line2 mirror) {
        super(matrix(mirror));
        this.mirror = mirror;
    }

    public Line2 getMirror() {
        return mirror;
    }

    public static SimpleMatrix matrix(Line2 mirror) {
        var shift1 = new Translation2(new Vector2(mirror.getPoint()).negate());
        LinearTransformation2 reflectOrigin = new ReflectionOrigin2(mirror);
        var shift2 = new Translation2(new Vector2(mirror.getPoint()));
        return new AffineTransformation2(shift1).compose(new AffineTransformation2(reflectOrigin)).compose(new AffineTransformation2(shift2)).getMatrix();
    }

}
