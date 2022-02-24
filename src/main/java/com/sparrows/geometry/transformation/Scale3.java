package com.sparrows.geometry.transformation;

import com.sparrows.geometry.geometry3.Point3;
import com.sparrows.geometry.geometry3.Vector3;
import com.sparrows.geometry.transformation.d3.AffineTransformation3;
import com.sparrows.geometry.transformation.d3.LinearTransformation3;
import org.ejml.simple.SimpleMatrix;

public class Scale3 extends AffineTransformation3 {
    private final Point3 centre;
    private final double factor;

    public Scale3(Point3 centre, double factor) {
        super(matrix(centre, factor));
        this.centre = centre;
        this.factor = factor;
    }

    public static SimpleMatrix matrix(Point3 centre, double factor) {
        var shift1 = new Translation3(new Vector3(centre).negate());
        LinearTransformation3 scaleOrigin = null;
        scaleOrigin = new ScaleOrigin3(factor);
        var shift2 = new Translation3(new Vector3(centre));
        return new AffineTransformation3(shift1).compose(new AffineTransformation3(scaleOrigin)).compose(new AffineTransformation3(shift2)).getMatrix();
    }
}
