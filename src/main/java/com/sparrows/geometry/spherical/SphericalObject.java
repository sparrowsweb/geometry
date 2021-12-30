package com.sparrows.geometry.spherical;

import com.sparrows.geometry.exception.InvalidSphericalTransformation;
import com.sparrows.geometry.transformation.SphericalIdentity;
import com.sparrows.geometry.transformation.SphericalInversion;
import com.sparrows.geometry.transformation.SphericalReflection;
import com.sparrows.geometry.transformation.SphericalRotation;
import com.sparrows.geometry.transformation.SphericalTransformation;

public interface SphericalObject<T extends SphericalObject<T>> {

    boolean identical(T o);

    T sphericalTransform(SphericalTransformation t);

    default T identity() {
        SphericalTransformation t = new SphericalIdentity();
        return sphericalTransform(t);
    }
/*
    default T scale(double factor) throws GeometryException {
        LinearTransformation3 t = new Scale3(factor);
        return linearTransform(t);
    }

    default T stretch(double xFactor, double yFactor, double zFactor) throws GeometryException {
        LinearTransformation3 t = new Stretch3(xFactor, yFactor, zFactor);
        return linearTransform(t);
    }
*/
    default T reflect(GreatCircle mirror) {
        SphericalTransformation t = new SphericalReflection(mirror);
        return sphericalTransform(t);
    }

    default T rotate(SphericalPoint centre, double angle) {
        SphericalTransformation t = null;
        try {
            t = new SphericalRotation(centre,angle);
        } catch (InvalidSphericalTransformation invalidSphericalTransformation) {
            invalidSphericalTransformation.printStackTrace();
        }
        return sphericalTransform(t);
    }
/*
    default T rotoreflectOrigin(Vector3 axis, double angle) throws GeometryException {
        LinearTransformation3 t = new RotoreflectionOrigin3(axis,angle);
        return linearTransform(t);
    }

    default T rotoreflect(Line3 axis, double angle, Point3 mirrorPoint) throws GeometryException {
        AffineTransformation3 t = new Rotoreflection3(axis,angle,mirrorPoint);
        return affineTransform(t);
    }
*/
    default T invert() {
        return sphericalTransform(new SphericalInversion());
    }
}
