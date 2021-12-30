package com.sparrows.geometry.transformation;
import com.sparrows.geometry.geometry2.Point2;
import com.sparrows.geometry.geometry2.Vector2;
import org.ejml.simple.SimpleMatrix;

public class Inversion2 extends AffineTransformation2 {
    private Point2 centre;

    public Inversion2(Point2 centre) {
        super(matrix(centre));
        this.centre = centre;
    }

    public Point2 getCentre() {
        return centre;
    }

    public static SimpleMatrix matrix(Point2 centre) {
        var shift1 = new Translation2(new Vector2(centre).negate());
        LinearTransformation2 inversionOrigin = new InversionOrigin2();
        var shift2 = new Translation2(new Vector2(centre));
        return new AffineTransformation2(shift1).compose(new AffineTransformation2(inversionOrigin)).compose(new AffineTransformation2(shift2)).getMatrix();
    }

    public String toString() {
        return "inversion in " + centre.toString();
    }

}
