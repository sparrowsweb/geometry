package com.sparrows.geometry.geometry3;

import com.sparrows.geometry.exception.GeometryException;
import com.sparrows.geometry.transformation.*;
import com.sparrows.geometry.transformation.d3.AffineTransformation3;
import com.sparrows.geometry.transformation.d3.LinearTransformation3;

public interface GeometryObject3<T extends GeometryObject3<T>> {

    boolean identical(T o);

    T linearTransform(LinearTransformation3 t) throws GeometryException;
    T affineTransform(AffineTransformation3 t) throws GeometryException;

    default T identity() throws GeometryException {
        LinearTransformation3 t = new Identity3();
        return linearTransform(t);
    }

    default T scaleOrigin(double factor) throws GeometryException {
        LinearTransformation3 t = new ScaleOrigin3(factor);
        return linearTransform(t);
    }

    default T scale(Point3 centre, double factor) throws GeometryException {
        AffineTransformation3 t = new Scale3(centre,factor);
        return affineTransform(t);
    }

    default T stretch(double xFactor, double yFactor, double zFactor) throws GeometryException {
        LinearTransformation3 t = new Stretch3(xFactor, yFactor, zFactor);
        return linearTransform(t);
    }

    default T reflect(Plane3 mirror) {
        AffineTransformation3 t = new Reflection3(mirror);
        try {
            return affineTransform(t);
        } catch (GeometryException e) {
            return null; // can't happen
        }
    }

    default T reflectOrigin(Vector3 mirrorNormal) {
        LinearTransformation3 t = new ReflectionOrigin3(mirrorNormal);
        try {
            return linearTransform(t);
        } catch (GeometryException e) {
            return null; // can't happen
        }
    }

    default T reflectOrigin(Plane3 a) {
        LinearTransformation3 t = new ReflectionOrigin3(a);
        try {
            return linearTransform(t);
        } catch (GeometryException e) {
            return null; // can't happen
        }
    }

    default T rotate(Line3 axis, double angle) {
        AffineTransformation3 t = new Rotation3(axis,angle);
        try {
            return affineTransform(t);
        } catch (GeometryException e) {
            return null; // can't happen as anything can be rotated
        }
    }

    default T rotateOrigin(Vector3 axis, double angle) throws GeometryException {
        LinearTransformation3 t = new RotationOrigin3(axis,angle);
        return linearTransform(t);
    }

    default T rotoreflectOrigin(Vector3 axis, double angle) throws GeometryException {
        LinearTransformation3 t = new RotoreflectionOrigin3(axis,angle);
        return linearTransform(t);
    }

    default T rotoreflect(Line3 axis, double angle, Point3 mirrorPoint) throws GeometryException {
        AffineTransformation3 t = new Rotoreflection3(axis,angle,mirrorPoint);
        return affineTransform(t);
    }

    default T invertOrigin() {
        LinearTransformation3 t = new InversionOrigin3();
        try {
            return linearTransform(t);
        } catch (GeometryException e) {
            return null; // can't happen
        }
    }

    default T invert(Point3 centre) {
        AffineTransformation3 t = new Inversion3(centre);
        try {
            return affineTransform(t);
        } catch (GeometryException e) {
            return null; // can't happen
        }
    }

    T translate(Translation3 t);

    default T translate(Vector3 v) {
        return translate(new Translation3(v));
    }
}
