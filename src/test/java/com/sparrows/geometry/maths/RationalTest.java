package com.sparrows.geometry.maths;

import com.sparrows.geometry.maths.exceptions.InvalidRational;
import com.sparrows.geometry.maths.exceptions.ZeroDenominator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RationalTest {
    @Test
    void TestConstructor3_7() throws Exception {
        Assertions.assertEquals(3,new Rational(3,7).getNumerator());
        Assertions.assertEquals(7,new Rational(3,7).getDenominator());
    }
    @Test
    void TestConstructor1_0() {
        Assertions.assertThrows(ZeroDenominator.class, () -> new Rational(1, 0));
    }
    @Test
    void TestConstructor3_6() throws Exception {
        Assertions.assertEquals(1,new Rational(3,6).getNumerator());
        Assertions.assertEquals(2,new Rational(3,6).getDenominator());
    }
    @Test
    void TestConstructorMinus3_6() throws Exception {
        Assertions.assertEquals(-1,new Rational(-3,6).getNumerator());
        Assertions.assertEquals(2,new Rational(-3,6).getDenominator());
    }
    @Test
    void TestConstructor3_Minus6() throws Exception {
        Assertions.assertEquals(-1,new Rational(3,-6).getNumerator());
        Assertions.assertEquals(2,new Rational(3,-6).getDenominator());
    }
    @Test
    void TestConstructorMinus3_Minus6() throws Exception {
        Assertions.assertEquals(1,new Rational(3,6).getNumerator());
        Assertions.assertEquals(2,new Rational(3,6).getDenominator());
    }

    @Test
    void TestToDouble() throws ZeroDenominator {
        Assertions.assertEquals(.5,new Rational(1,2).toDouble());
    }

    @Test
    void TestParseRational() throws ZeroDenominator, InvalidRational {
        Assertions.assertEquals(new Rational(1,2),Rational.parseRational("1/2"));
    }

    @Test
    void TestParseRationalSimplify() throws ZeroDenominator, InvalidRational {
        Assertions.assertEquals(new Rational(1,2),Rational.parseRational("-2/-4"));
    }

    @Test
    void TestParseRationalInteger() throws ZeroDenominator, InvalidRational {
        Assertions.assertEquals(new Rational(-7,1),Rational.parseRational("-7"));
    }

    @Test
    void TestParseRationalError() {
        Assertions.assertThrows(InvalidRational.class, () -> Rational.parseRational("1//2"));
    }

    @Test
    void TestParseRationalZeroDivide() {
        Assertions.assertThrows(ZeroDenominator.class, () -> Rational.parseRational("1/0"));
    }
}
