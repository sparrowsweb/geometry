package com.sparrows.geometry.maths;

import com.sparrows.geometry.maths.Maths;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MathsTest {
    @Test
    void TestHcf() {
        Assertions.assertEquals(77, Maths.hcf(77*8000,77*81));
    }
    @Test
    void TestHcfOneZero() {
        Assertions.assertEquals(1, Maths.hcf(1,0));
    }
    @Test
    void TestHcfSevenZero() {
        Assertions.assertEquals(7, Maths.hcf(7,0));
    }
    @Test
    void TestHcfNegativeNumerator() {
        Assertions.assertEquals(10, Maths.hcf(-20,30));
    }
    @Test
    void TestHcfNegativeDenominator() {
        Assertions.assertEquals(10, Maths.hcf(20,-30));
    }
    @Test
    void TestHcfNegativeNumeratorAndDenominator() {
        Assertions.assertEquals(10, Maths.hcf(-20,-30));
    }
    @Test
    void TestHcfLong() {
        Assertions.assertEquals(77L, Maths.hcf(77L*8000L,77L*81L));
    }
    @Test
    void TestHcfOneZeroLong() {
        Assertions.assertEquals(1L, Maths.hcf(1L,0L));
    }
    @Test
    void TestHcfSevenZeroLong() {
        Assertions.assertEquals(7L, Maths.hcf(7L,0L));
    }
    @Test
    void TestHcfNegativeNumeratorLong() {
        Assertions.assertEquals(10L, Maths.hcf(-20L,30L));
    }
    @Test
    void TestHcfNegativeDenominatorLong() {
        Assertions.assertEquals(10L, Maths.hcf(20L,-30L));
    }
    @Test
    void TestHcfNegativeNumeratorAndDenominatorLong() {
        Assertions.assertEquals(10L, Maths.hcf(-20L,-30L));
    }

    @Test
    void TestCoprimes() {
        var it = Maths.coprimes(10);
        Assertions.assertTrue(it.hasNext());
        Assertions.assertEquals(1,it.next());
        Assertions.assertTrue(it.hasNext());
        Assertions.assertEquals(3,it.next());
        Assertions.assertTrue(it.hasNext());
        Assertions.assertEquals(7,it.next());
        Assertions.assertTrue(it.hasNext());
        Assertions.assertEquals(9,it.next());
        Assertions.assertFalse(it.hasNext());
    }
}
