package com.sparrows.geometry.utils;

import com.sparrows.geometry.exception.triangle.TriangleInconsistentAngleSideSideException;
import com.sparrows.geometry.exception.triangle.TriangleInsufficientDataException;
import com.sparrows.geometry.exception.triangle.TriangleInvalidAngleSumException;
import com.sparrows.geometry.exception.triangle.TriangleInvalidSidesException;
import com.sparrows.geometry.exception.triangle.TriangleException;
import com.sparrows.geometry.exception.triangle.TriangleAngleNotANumberException;
import com.sparrows.geometry.exception.triangle.TriangleInvalidAngleException;
import com.sparrows.geometry.exception.triangle.TriangleSideNotANumberException;
import com.sparrows.geometry.exception.triangle.TriangleInvalidSideException;

public class Triangle {
    private Double[] side;
    private Double[] angle;

    private double ERROR_MARGIN = .00000000001;

    public Triangle(Double sideA, Double sideB, Double sideC, Double angleA, Double angleB, Double angleC) throws TriangleException {
        side = new Double[] {sideA, sideB, sideC};
        angle = new Double[] {angleA, angleB, angleC};

        validate();

        resolve();
    }

    public void validate() throws TriangleException {
        for (int i = 0; i < 3; i++) {
            if (side[i] != null && (side[i].isInfinite() || side[i].isNaN())) {
                throw new TriangleSideNotANumberException(i+1, side[i]);
            }
        }

        for (int i = 0; i < 3; i++) {
            if (side[i] != null && side[i] <= 0) {
                throw new TriangleInvalidSideException(i+1, side[i]);
            }
        }

        for (short i = 0; i < 3; i++) {
            if (angle[i] != null && (angle[i].isInfinite() || angle[i].isNaN())) {
                throw new TriangleAngleNotANumberException(i+1, angle[i]);
            }
        }

        for (short i = 0; i < 3; i++) {
            if (angle[i] != null && (angle[i] <= 0 || angle[i] >= Math.PI)) {
                throw new TriangleInvalidAngleException(i+1, angle[i]);
            }
        }

        if (side[0] != null && side[1] != null && side[2] != null) {
            for (short i = 0; i < 3; i++) {
                if (side[i] >= side[(i+1)%3] + side[(i+2)%3]) {
                    throw new TriangleInvalidSidesException(i+1, side[i]);
                }
            }
        }

        double angleSum = (angle[0] != null ? angle[0] : 0) + (angle[1] != null ? angle[1] : 0) + (angle[2] != null ? angle[2] : 0);
        if (angle[0] != null && angle[1] != null && angle[2] != null) {
            if (Math.abs(angleSum - Math.PI) > ERROR_MARGIN) {
                throw new TriangleInvalidAngleSumException(angleSum);
            }
        } else {
            if (angleSum > Math.PI) {
                throw new TriangleInvalidAngleSumException(angleSum);
            }
        }
    }

    public void resolve() throws TriangleException {
        int anglesKnown = (angle[0] != null ? 1 : 0) + (angle[1] != null ? 1 : 0) + (angle[2] != null ? 1 : 0);

        // if we have two angles we can calculate the third
        if (anglesKnown == 2) {
            calculateThirdAngle();
            anglesKnown = 3;
        }

        int sidesKnown = (side[0] != null ? 1 : 0) + (side[1] != null ? 1 : 0) + (side[2] != null ? 1 : 0);

        if (sidesKnown == 3 && anglesKnown == 3) {
            return;
        }

        // if we have all the sides we can calculate all the angles
        if (sidesKnown == 3 && anglesKnown < 3) {
            calculateAllAnglesFromAllSides();
            return;
        }

        // if we have two sides and the angle between we can calculate the third side
        if (sidesKnown >= 2 && anglesKnown >= 1) {
            for (short i = 0; i < 3; i++) {
                if (side[i] == null && side[(i + 1) % 3] != null && side[(i + 2) % 3] != null && angle[i] != null) {
                    side[i] = cosineRule(side[(i + 1) % 3], side[(i + 2) % 3], angle[i % 3]);
                    if (anglesKnown < 3) {
                        calculateAllAnglesFromAllSides();
                    }
                    return;
                }
            }
        }

        // if we have three angles and a side we can calculate the other sides
        if (anglesKnown == 3 && sidesKnown >= 1 && sidesKnown < 3) {
            for (short i = 0; i < 3; i++) {
                if (side[i] == null) {
                    if (side[(i + 1) % 3] != null) {
                        side[i] = sineRuleForSide(angle[i], side[(i + 1) % 3], angle[(i + 1) % 3]);
                    } else if (side[(i + 2) % 3] != null) {
                        side[i] = sineRuleForSide(angle[i], side[(i + 2) % 3], angle[(i + 2) % 3]);
                    }
                }
            }
            return;
        }

        // if we have two sides and one opposite angle we can (sort of) calculate the other opposite angle
        if (sidesKnown >= 2 && anglesKnown >= 1) {
            for (short i = 0; i < 3; i++) {
                if (angle[i] == null && side[i] != null) {
                    for (short j = 1; j <= 2; j++) {
                        if (side[(i + j) % 3] != null && angle[(i + j) % 3] != null) {
                            angle[i] = sineRuleForAngle(side[i], angle[(i + j) % 3], side[(i + j) % 3]);
                            calculateThirdAngle();
                            if (side[(i + 3 - j) % 3] == null) {
                                side[(i + 3 - j) % 3] = sineRuleForSide(angle[(i + 3 - j) % 3], side[(i + j) % 3], angle[(i + j) % 3]);
                            }
                            return;
                        }
                    }
                }
            }
        }

        throw new TriangleInsufficientDataException();
    }

    private void calculateThirdAngle() {
        for (short i = 0; i < 3; i++) {
            if (angle[i] == null) {
                angle[i] = Math.PI - angle[(i + 1) % 3] - angle[(i + 2) % 3];
                break;
            }
        }
    }
    private void calculateAllAnglesFromAllSides() {
        for (short i = 0; i < 3; i++) {
            if (angle[i] == null) {
                angle[i] = reverseCosineRule(side[i], side[(i+1)%3], side[(i+2)%3]);
            }
        }
    }

    public static double sineRuleForSide (double angleA, double b, double angleB) {
        return Math.sin(angleA) * b / Math.sin(angleB);
    }

    public static double sineRuleForAngle (double a, double angleB, double b) throws TriangleInconsistentAngleSideSideException {
        double angleA = Math.asin(a * Math.sin(angleB) / b);
        if (Double.isNaN(angleA)) {
            throw new TriangleInconsistentAngleSideSideException();
        }
        return angleA;
    }

    public static double cosineRule (double b, double c, double theta) {
        return Math.sqrt(b*b + c*c - 2*b*c*Math.cos(theta));
    }

    public static double reverseCosineRule (double a, double b, double c) {
        return Math.acos((b*b + c*c - a*a) / (2*b*c));
    }

    public String toString() {
        return "a = " + side[0] + "\nb = " + side[1] + "\nc = " + side[2] + "\nA = " + angle[0] + "\nB = " + angle[1] + "\nC = " + angle[2];
    }

    public Double getSideA() {
        return side[0];
    }

    public Double getSideB() {
        return side[1];
    }

    public Double getSideC() {
        return side[2];
    }

    public Double getAngleA() {
        return angle[0];
    }

    public Double getAngleB() {
        return angle[1];
    }

    public Double getAngleC() {
        return angle[2];
    }
}
