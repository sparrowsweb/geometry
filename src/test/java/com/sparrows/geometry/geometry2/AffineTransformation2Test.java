package com.sparrows.geometry.geometry2;

import com.sparrows.geometry.exception.TransformationNotInvertible;
import com.sparrows.geometry.transformation.AffineTransformation2;
import com.sparrows.geometry.transformation.LinearTransformation2;
import com.sparrows.geometry.transformation.Stretch2;
import com.sparrows.geometry.transformation.Translation2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.sparrows.geometry.utils.TestUtils.checkMatrix;

class AffineTransformation2Test {

    @Test
    void TestConstructor() {
        AffineTransformation2 t = new AffineTransformation2(new Stretch2(2,3), new Translation2(4,5));
        checkMatrix(new double[][]{
                {2,0,4},
                {0,3,5},
                {0,0,1}},
                t.getMatrix());
    }

    @Test
    void TestLinearTransformation() {
        AffineTransformation2 t = new AffineTransformation2(new Stretch2(2,3), new Translation2(4,5));
        LinearTransformation2 l = t.linearTransformation();
        checkMatrix(new double[][]{
                        {2,0},{0,3}},
                l.getMatrix());
    }

    @Test
    void TestTranslation() {
        AffineTransformation2 t = new AffineTransformation2(new Stretch2(2,3), new Translation2(4,5));
        Translation2 tr = t.translation();
        checkMatrix(new double[][]{
                        {4},{5}},
                tr.getMatrix());
    }

    @Test
    void TestInverse() throws TransformationNotInvertible {
        AffineTransformation2 a = new AffineTransformation2(new Stretch2(2,5), new Translation2(1,2));
        AffineTransformation2 i = a.inverse();
        checkMatrix(new double[][]{
                        {.5,0,-0.5},
                        {0,.2,-.4},
                        {0,0,1}},
                i.getMatrix());
    }

    @Test
    void TestInverseSingular() {
        AffineTransformation2 a = new AffineTransformation2(new Stretch2(2,0), new Translation2(1,2));
        Assertions.assertThrows(TransformationNotInvertible.class, a::inverse);
    }
}
