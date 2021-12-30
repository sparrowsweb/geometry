package com.sparrows.geometry.spherical;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sparrows.geometry.exception.AntipodalPoints;
import com.sparrows.geometry.exception.DoesNotTileSphere;
import com.sparrows.geometry.exception.IdenticalSphericalPoints;
import com.sparrows.geometry.exception.InvalidSchwarzTriangle;
import com.sparrows.geometry.exception.InvalidVertexAngle;
import com.sparrows.geometry.exception.InvalidVertexAngles;
import com.sparrows.geometry.maths.exceptions.ZeroDenominator;
import com.sparrows.geometry.maths.Maths;
import com.sparrows.geometry.maths.Rational;

public class SchwarzTriangle {
    public enum SymmetryGroup {
        DIHEDRAL(0),
        TETRAHEDRAL(24),
        OCTAHEDRAL(48),
        ICOSAHEDRAL(120);

        private final int order;

        SymmetryGroup(int order) {
            this.order = order;
        }

        public int getOrder() {
            return order;
        }
    }

    private static final List<SchwarzTriangle> all;
    static {List<SchwarzTriangle> temp = null;
        try {
            temp = findAll();
        } catch (ZeroDenominator | InvalidSchwarzTriangle e) {
            e.printStackTrace();
        }
        all = temp;
    }

    private final Rational p;
    private final Rational q;
    private final Rational r;

    private final SymmetryGroup symmetryGroup;
    private final int symmetryGroupOrder;
    private final int tilingDensity;

    private int tilingSize;

    /**
     * <p>A Schwarz triangle. This is defined by three rational numbers p, q and r which define the
     * vertex angles π/p, π/q and π/r. If two of p, q and r are equal to 2, the Schwarz triangle is
     * dihedral. The following restrictions apply to p, q and r:
     * (1) p, q, r > 1 (otherwise a vertex angle would be >= π).
     * (2) 1/p + 1/q + 1/r > 1 (otherwise the angle sum would be <= π).
     * (3) The numerator of p, q, r must be between 2 and 5, unless it is a dihedral Schwarz triangle.
     * (4) Numerators 4 and 5 cannot both appear among p, q and r (otherwise, with an assumed group order of
     * 120, the covering density would not be an integer).</p>
     * @param p first angle
     * @param q second angle
     * @param r third angle
     * @throws InvalidSchwarzTriangle If the restrictions on p, q and r are not met.
     */
    public SchwarzTriangle(Rational p, Rational q, Rational r) throws InvalidSchwarzTriangle, DoesNotTileSphere {
        this.p = p;
        this.q = q;
        this.r = r;
        validate();
        if (Maths.twoOrMoreTrue(p.equals(Rational.TWO), q.equals(Rational.TWO), r.equals(Rational.TWO))) {
            this.symmetryGroup = SymmetryGroup.DIHEDRAL;
            Rational orderVertex;
            if (!p.equals(Rational.TWO)) {
                orderVertex = p;
            } else if (!q.equals(Rational.TWO)) {
                orderVertex = q;
            } else {
                orderVertex = r;
            }
            this.symmetryGroupOrder = 4*(int)orderVertex.getNumerator();
            this.tilingDensity = (int)orderVertex.getDenominator();
        } else {
            long maxNumerator = Maths.max(p.getNumerator(),q.getNumerator(),r.getNumerator());
            if (maxNumerator == 3) {
                this.symmetryGroup = SymmetryGroup.TETRAHEDRAL;
            } else if (maxNumerator == 4) {
                this.symmetryGroup = SymmetryGroup.OCTAHEDRAL;
            } else {
                this.symmetryGroup = SymmetryGroup.ICOSAHEDRAL;
            }
            this.symmetryGroupOrder = this.symmetryGroup.getOrder();
            Rational densityRational;
            try {
                densityRational = angularExcess().multiply(this.symmetryGroupOrder).divide(4);
            } catch (ZeroDenominator zeroDenominator) {
                // cannot happen
                throw new InvalidSchwarzTriangle("Unexpected error, zero denominator.");
            }
            if (densityRational.getDenominator() == 1L) {
                this.tilingDensity = (int)densityRational.getNumerator();
            } else {
                throw new InvalidSchwarzTriangle("Unexpected error, density is not an integer.");
            }
        }
        findTiling();
    }

    private void validate() throws InvalidSchwarzTriangle {
        validateParameter(p);
        validateParameter(q);
        validateParameter(r);
        if (angleSum().lessThanOrEqual(Rational.ONE)) {
            throw new InvalidSchwarzTriangle("Angles must sum to more than π.");
        }
        if ((p.getNumerator() == 4 || q.getNumerator() == 4 || r.getNumerator() == 4) &&
                (p.getNumerator() == 5 || q.getNumerator() == 5 || r.getNumerator() == 5)) {
            // if numerators 4 and 5 exist, the density would be fractional
            throw new InvalidSchwarzTriangle("Cannot have both numerator 4 and numerator 5 in the same Schwarz triangle.");
        }

        boolean dihedral = Maths.twoOrMoreTrue(p.equals(Rational.TWO), q.equals(Rational.TWO), r.equals(Rational.TWO));
        if (!dihedral &&
                (p.getNumerator() > 5L || q.getNumerator() > 5L || r.getNumerator() > 5L)) {
            throw new InvalidSchwarzTriangle("Each numerator must be at most 5.");
        }
    }
    private void validateParameter(Rational p) throws InvalidSchwarzTriangle {
        if (p.lessThanOrEqual(Rational.ONE)) {
            throw new InvalidSchwarzTriangle("Each angle must be less than π.");
        }
    }

    public Rational angleSum() {
        try {
            return p.reciprocal().add(q.reciprocal()).add(r.reciprocal());
        } catch (ZeroDenominator zeroDenominator) {
            // cannot happen
            return null;
        }
    }

    public Rational angularExcess() {
        return angleSum().subtract(Rational.ONE);
    }

    public static List<SchwarzTriangle> getAll() {
        return all;
    }
    public Rational getP() {
        return p;
    }
    public Rational getQ() {
        return q;
    }
    public Rational getR() {
        return r;
    }
    public SymmetryGroup getSymmetryGroup() {
        return symmetryGroup;
    }
    public int getSymmetryGroupOrder() {
        return symmetryGroupOrder;
    }
    public int getTilingDensity() {
        return tilingDensity;
    }
    public int getTilingSize() {
        return tilingSize;
    }

    /**
     * <p>A realisation of this Schwarz Triangle</p>
     * @return A SphericalPolygon with the angles of this Schwarz triangle.
     */
    public SphericalPolygon triangle() {
        try {
            return SphericalPolygon.triangle(
                    Math.PI / p.toDouble(),
                    Math.PI / q.toDouble(),
                    Math.PI / r.toDouble());
        } catch (InvalidVertexAngle | InvalidVertexAngles e) {
            // can't happen
            e.printStackTrace();
            return null;
        }
    }

    private void findTiling() throws DoesNotTileSphere {
        if (this.getSymmetryGroup() == SymmetryGroup.DIHEDRAL) {
            long numerator;
            if (!p.equals(Rational.TWO)) {
                numerator = p.getNumerator();
            } else if (!q.equals(Rational.TWO)) {
                numerator = q.getNumerator();
            } else {
                numerator = r.getNumerator();
            }
            this.tilingSize = 4*(int)numerator;
        } else {
            SphericalPolygon candidateTriangle = triangle();
            List<GreatCircle> mirrors = new ArrayList<>();
            try {
                mirrors.add(new GreatCircle(candidateTriangle.getVertex(0), candidateTriangle.getVertex(1)));
                mirrors.add(new GreatCircle(candidateTriangle.getVertex(1), candidateTriangle.getVertex(2)));
                mirrors.add(new GreatCircle(candidateTriangle.getVertex(2), candidateTriangle.getVertex(0)));
            } catch (AntipodalPoints | IdenticalSphericalPoints e) {
                // cannot happen
                e.printStackTrace();
            }
            List<SphericalPolygon> tileList = new ArrayList<>();
            tileList.add(candidateTriangle);
            for (var t = 0; t < tileList.size(); t++) {
                for (var mirror : mirrors) {
                    SphericalPolygon reflection = tileList.get(t).reflect(mirror);
                    var found = false;
                    for (var face : tileList) {
                        if (/*reflection.identical(face)*/reflection.getVertex(0).identical(face.getVertex(0)) && reflection.getVertex(1).identical(face.getVertex(1)) && reflection.getVertex(2).identical(face.getVertex(2))) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        tileList.add(reflection);
                    }
                }
                if (tileList.size() > 120) {
                    break;
                }
            }
            if (tileList.size() > 120) {
                throw new DoesNotTileSphere();
            } else {
                this.tilingSize = tileList.size();
                try {
                    var areaByPi = p.reciprocal().add(q.reciprocal()).add(r.reciprocal()).subtract(Rational.ONE);
                    var tilesPerCovering = new Rational(4).divide(areaByPi);
                    //this.tilingDensity = (int)(new Rational(tileList.size()).divide(tilesPerCovering).toDouble());
                } catch (ZeroDenominator e) {
                    // cannot happen
                    e.printStackTrace();
                }
            }
        }
    }

    private static List<SchwarzTriangle> findAll() throws ZeroDenominator, InvalidSchwarzTriangle {
        List<SchwarzTriangle> all = new ArrayList<>();
        List<Rational> validAngles = Arrays.asList(
                new Rational(2),
                new Rational(3),new Rational(3,2),
                new Rational(4),new Rational(4,3),
                new Rational(5),new Rational(5,2),new Rational(5,3),new Rational(5,4));
        for (var ip = 0; ip < validAngles.size(); ip++) {
            Rational p = validAngles.get(ip);
            for (var iq = ip; iq < validAngles.size(); iq++) {
                Rational q = validAngles.get(iq);
                for (var ir = iq; ir < validAngles.size(); ir++) {
                    Rational r = validAngles.get(ir);
                    if (p.reciprocal().add(q.reciprocal()).add(r.reciprocal()).lessThanOrEqual(Rational.ONE)) {
                        // ignore: angles too small
                    } else if ((p.getNumerator() == 4 || q.getNumerator() == 4 || r.getNumerator() == 4) &&
                            (p.getNumerator() == 5 || q.getNumerator() == 5 || r.getNumerator() == 5)) {
                        // ignore:numerators 4 and 5
                    } else if (p.reciprocal().add(q.reciprocal()).greaterThanOrEqual(r.reciprocal().add(Rational.ONE)) ||
                                    q.reciprocal().add(r.reciprocal()).greaterThanOrEqual(p.reciprocal().add(Rational.ONE)) ||
                                    r.reciprocal().add(p.reciprocal()).greaterThanOrEqual(q.reciprocal().add(Rational.ONE))) {
                        // ignore: invalid angles
                    } else if (p.equals(Rational.TWO) && (q.equals(Rational.TWO))) {
                        // ignore: dihedral symmetry
                    } else {
                        try {
                            var candidate = new SchwarzTriangle(p, q, r);
                            all.add(candidate);
                            //System.out.println(candidate + ": " + candidate.tilingSize + " - " + candidate.tilingDensity);
                        } catch (DoesNotTileSphere e) {
                            //System.out.println(e.getMessage());
                        }
                    }
                }
            }
        }
        return all;
    }

    public String toString() {
        return "(" + p.toString() + "," + q.toString() + "," + r.toString() + ")";
    }
}
