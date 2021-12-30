package com.sparrows.geometry.transformation;
import com.sparrows.geometry.geometry3.Point3;
import com.sparrows.geometry.geometry3.Vector3;
import com.sparrows.geometry.transformation.d3.AffineTransformation3;
import com.sparrows.geometry.transformation.d3.LinearTransformation3;

import org.ejml.simple.SimpleMatrix;

// reflection in a line
public class Inversion3 extends AffineTransformation3 {
    private Point3 centre;

    public Inversion3(Point3 centre) {
        super(matrix(centre));
        this.centre = centre;
    }

    public Point3 getCentre() {
        return centre;
    }

    public static SimpleMatrix matrix(Point3 centre) {
        var shift1 = new Translation3(new Vector3(centre).negate());
        LinearTransformation3 inversionOrigin = new InversionOrigin3();
        var shift2 = new Translation3(new Vector3(centre));
        return new AffineTransformation3(shift1).compose(new AffineTransformation3(inversionOrigin)).compose(new AffineTransformation3(shift2)).getMatrix();
    }

    public String toString() {
        return "inversion in " + centre.toString();
    }

}
