package com.sparrows.geometry.geometry2;

import com.sparrows.geometry.exception.GeometryException;
import com.sparrows.geometry.transformation.AffineTransformation2;
import com.sparrows.geometry.transformation.Reflection2;
import com.sparrows.geometry.transformation.Rotation2;
import com.sparrows.geometry.transformation.Scale2;
import com.sparrows.geometry.transformation.Identity2;
import com.sparrows.geometry.transformation.InversionOrigin2;
import com.sparrows.geometry.transformation.LinearTransformation2;
import com.sparrows.geometry.transformation.ReflectionOrigin2;
import com.sparrows.geometry.transformation.RotationOrigin2;
import com.sparrows.geometry.transformation.Stretch2;
import com.sparrows.geometry.transformation.Translation2;

public interface GeometryObject2<T extends GeometryObject2<T>> {

    boolean identical(T o);

    T linearTransform(LinearTransformation2 t);
    T affineTransform(AffineTransformation2 t) throws GeometryException;

    default T identity() {
        LinearTransformation2 t = new Identity2();
        return linearTransform(t);
    }

    default T reflectOrigin(double angle) {
        LinearTransformation2 t = new ReflectionOrigin2(angle);
        return linearTransform(t);
    }

    default T reflectOrigin(Line2 l) {
        LinearTransformation2 t = new ReflectionOrigin2(l);
        return linearTransform(t);
    }

    default T reflect(Line2 l) {
        AffineTransformation2 t = new Reflection2(l);
        try {
            return affineTransform(t);
        } catch (GeometryException e) {
            return null; // can't happen
        }
    }

    default T rotate(Point2 centre,double angle) {
        AffineTransformation2 a = new Rotation2(centre, angle);
        try {
            return affineTransform(a);
        } catch (GeometryException e) {
            return null; // can't happen
        }
    }

    default T scale(double factor) {
        LinearTransformation2 t = new Scale2(factor);
        return linearTransform(t);
    }

    default T stretch(double xFactor, double yFactor) {
        LinearTransformation2 t = new Stretch2(xFactor, yFactor);
        return linearTransform(t);
    }

    default T rotateOrigin(double angle) {
        LinearTransformation2 t = new RotationOrigin2(angle);
        return linearTransform(t);
    }

    default T invert() {
        LinearTransformation2 t = new InversionOrigin2();
        return linearTransform(t);
    }

    T translate(Translation2 t);
    default T translate(Vector2 v) {
        return translate(new Translation2(v));
    }
}
