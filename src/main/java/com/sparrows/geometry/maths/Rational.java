package com.sparrows.geometry.maths;

import com.sparrows.geometry.maths.exceptions.InvalidRational;
import com.sparrows.geometry.maths.exceptions.ZeroDenominator;

import java.util.Objects;

public class Rational {
    public static final Rational ZERO = new Rational(0L);
    public static final Rational ONE = new Rational(1L);
    public static final Rational TWO = new Rational(2L);

    private long numerator;
    private long denominator;

    public Rational(long numerator, long denominator) throws ZeroDenominator {
        if (denominator == 0) {
            throw new ZeroDenominator();
        }
        this.numerator = numerator;
        this.denominator = denominator;
        simplify();
    }

    public Rational(long numerator) {
        this.numerator = numerator;
        this.denominator = 1L;
    }

    public long getNumerator() {
        return numerator;
    }

    public long getDenominator() {
        return denominator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var rational = (Rational) o;
        return numerator == rational.numerator && denominator == rational.denominator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    private void simplify() {
        long h = Maths.hcf(numerator,denominator);
        if (denominator < 0) {
            h = -h;
        }
        numerator /= h;
        denominator /= h;
    }

    public int sign() {
        return Long.signum(numerator);
    }

    public Rational reciprocal() throws ZeroDenominator {
        return new Rational(denominator,numerator);
    }

    public Rational add(Rational r) {
        try {
            return new Rational(numerator*r.denominator + r.numerator*denominator, denominator*r.denominator);
        } catch (ZeroDenominator zeroDenominator) {
            // can't happen
            return null;
        }
    }

    public Rational subtract(Rational r) {
        try {
            return new Rational(numerator*r.denominator - r.numerator*denominator, denominator*r.denominator);
        } catch (ZeroDenominator zeroDenominator) {
            // can't happen
            return null;
        }
    }

    public Rational multiply(Rational r)  {
        try {
            return new Rational(numerator*r.numerator,denominator*r.denominator);
        } catch (ZeroDenominator zeroDenominator) {
            // cannot happen
            return null;
        }
    }

    public Rational multiply(int n)  {
        try {
            return new Rational(numerator*n,denominator);
        } catch (ZeroDenominator zeroDenominator) {
            // cannot happen
            return null;
        }
    }

    public Rational divide(Rational r) throws ZeroDenominator {
        return new Rational(numerator*r.denominator,denominator*r.numerator);
    }

    public Rational divide(int n) throws ZeroDenominator {
        return new Rational(numerator,denominator*n);
    }

    public int compare(Rational r) {
        return subtract(r).sign();
    }

    public boolean lessThan(Rational r) {
        return compare(r) < 0;
    }

    public boolean lessThanOrEqual(Rational r) {
        return compare(r) <= 0;
    }

    public boolean greaterThan(Rational r) {
        return compare(r) > 0;
    }

    public boolean greaterThanOrEqual(Rational r) {
        return compare(r) >= 0;
    }

    public double toDouble() {
        return (double) numerator/denominator;
    }

    public static Rational parseRational(String s) throws ZeroDenominator, InvalidRational {
        try {
            int slash = s.indexOf('/');
            if (slash >= 0) {
                return new Rational(Long.parseLong(s.substring(0, slash)), Long.parseLong(s.substring(slash + 1)));
            } else {
                return new Rational(Long.parseLong(s));
            }
        } catch (NumberFormatException e) {
            throw new InvalidRational(s);
        }
    }

    @Override
    public String toString() {
        if (denominator == 1) {
            return Long.toString(numerator);
        } else {
            return numerator + "/" + denominator;
        }
    }
}
