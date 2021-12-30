package com.sparrows.geometry.transformation;

import com.sparrows.geometry.geometry2.Point2;
import com.sparrows.geometry.geometry2.Vector2;
import org.ejml.simple.SimpleMatrix;

public class Rotation2 extends AffineTransformation2 {
    private final Point2 centre;
    private final double angle;

    public Rotation2(Point2 centre, double angle) {
        super(matrix(centre, angle));
        this.centre = centre;
        this.angle = angle;
    }

    public static SimpleMatrix matrix(Point2 centre, double angle) {
        var shift1 = new Translation2(new Vector2(centre).negate());
        LinearTransformation2 rotateOrigin = new RotationOrigin2(angle);
        var shift2 = new Translation2(new Vector2(centre));
        return new AffineTransformation2(shift1).compose(new AffineTransformation2(rotateOrigin)).compose(new AffineTransformation2(shift2)).getMatrix();
    }

    public Point2 getCentre() {
        return centre;
    }

    public double getAngle() {
        return angle;
    }
}
