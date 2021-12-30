package com.sparrows.geometry.geometry3;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.sparrows.geometry.exception.GeometryException;
import com.sparrows.geometry.exception.NotAPointSymmetry;
import com.sparrows.geometry.transformation.Reflection3;
import com.sparrows.geometry.transformation.ReflectionOrigin3;
import com.sparrows.geometry.transformation.RotationOrigin3;
import com.sparrows.geometry.transformation.RotationalSymmetryAxis;
import com.sparrows.geometry.transformation.RotoreflectionOrigin3;
import com.sparrows.geometry.transformation.d3.LinearTransformation3;

/**
 * <p>A point symmetry group is a group of origin-preserving isometries.
 * In Schönflies notation there are fourteen types: seven infinite axial series
 * and seven polyhedral groups.</p>
 *
 * <p>Axial families (i.e. having at most one axis of order >= 3):
 * <ul>
 * <li>Cn  - cyclic order n - chiral - C1 is the identity</li>
 * <li>Cnv - pyramidal order 2n</li>
 * <li>Cnh - order 2n</li>
 * <li>Dn  -        - chiral - order 2n - D2 has no special rotation axis</li>
 * <li>Dnd - anti prismatic - order 4n</li>
 * <li>Dnh - prismatic - order 4n - D2h has no special rotation axis</li>
 * <li>Sn  -       - n even - order 2n</li>
 * </ul>
 * </p>
 * <p>Polyhedral groups (i.e. having more than one axis of order >= 3):
 * <ul>
 * <li>T</li>
 * <li>Td</li>
 * <li>Th</li>
 * <li>O</li>
 * <li>Oh</li>
 * <li>I</li>
 * <li>Ih</li>
 * </ul>
 * </p>
 * <p>The following pairs are identical, the former will be saved as the latter
 * <ul>
 * <li>C1v -> C1h</li>
 * <li>D1 -> C2</li>
 * <li>D1h = C2v</li>
 * <li>D1d = C2h</li>
 * </ul>
 * </p>
 * <p>The following are alternative names, the former will be saved as the latter:
 * <ul>
 * <li>Ci -> S2</li>
 * <li>Cs -> C1h</li>
 * </ul>
 * </p>
 */
public class PointSymmetryGroup3 {
    public enum Type {
        C, D, S, T, O, I
    }
    public enum Subtype {
        h, v, c, d
    }
    public static class Data {
        private String name;
        private Type type;
        private boolean family;
        private Subtype subtype;
        private int order;
        List<LinearTransformation3> generators;

        public Data(String name, Type type, boolean family, Subtype subtype, int order, List<LinearTransformation3> generators) {
            this.name = name;
            this.type = type;
            this.family = family;
            this.subtype = subtype;
            this.order = order;
            this.generators = generators;
        }
    }

    public static Data Cn = new Data("Cn",Type.C,true,null, 1, null);
    public static Data Cnv = new Data("Cnv",Type.C,true,Subtype.v, 2,null);
    public static Data Cnh = new Data("Cnh",Type.C,true,Subtype.h, 2,null);

    private final Type type;
    private final Integer n;
    private final Subtype subtype;

    public PointSymmetryGroup3 (String string) throws NotAPointSymmetry {
        if (string.equals("Ci")) {
            string = "S2";
        } else if (string.equals("Cs") || string.equals("C1v")) {
            string = "C1h";
        } else if (string.equals("D1")) {
            string = "C2";
        } else if (string.equals("D1h")) {
            string = "C2v";
        } else if (string.equals("D1d")) {
            string = "C2h";
        }
        var patternString = "^T|Td|Th|O|Oh|I|Ih|(C[1-9][0-9]*[vh]?)|(D[1-9][0-9]*[hd]?)|(S([2468]|[1-9][0-9]*[02468]))|Ci|Cs$";
        var pattern = Pattern.compile(patternString);

        var matcher = pattern.matcher(string);
        if (!matcher.find()) {
            throw new NotAPointSymmetry();
        }

        var pat = Pattern.compile("^(.)([0-9]*)(.?)$");
        matcher = pat.matcher(string);
        if (!matcher.find()) {
            throw new NotAPointSymmetry();
        }
        this.type = Type.valueOf(matcher.group(1));
        this.n = matcher.group(2).isEmpty() ? null : Integer.parseInt(matcher.group(2));
        this.subtype = matcher.group(3).isEmpty() ? null : Subtype.valueOf(matcher.group(3));
    }

    public PointSymmetryGroup3(Polyhedron h) throws GeometryException {
        List<RotationalSymmetryAxis> rotationAxes = h.rotationAxes();
        List<Reflection3> reflections = h.reflectionSymmetries();


        long polyhedralAxisCount = rotationAxes.stream().filter(r -> r.getOrder() >= 3).count();
        if (polyhedralAxisCount >= 2) {
            this.n = null;
            // polyhedral
            if (polyhedralAxisCount == 4) {
                // tetrahedral
                this.type = Type.T;
                if (reflections.isEmpty()) {
                    this.subtype = null;
                } else if (reflections.size() == 3) {
                    this.subtype = Subtype.h;
                } else {
                    this.subtype = Subtype.d;
                }
            } else {
                if (polyhedralAxisCount == 7) {
                    // octahedral
                    this.type = Type.O;
                } else if (polyhedralAxisCount == 16) {
                    // icosahedral
                    this.type = Type.I;
                } else {
                    throw new GeometryException("Cannot determine symmetry group.");
                }
                if (reflections.isEmpty()) {
                    this.subtype = null;
                } else {
                    this.subtype = Subtype.h;
                }
            }
        } else {
            // axial
            var rotoreflections = h.rotoreflectionAxes();
            if (!rotoreflections.isEmpty()) {
                // Sn or Dnd
                if (reflections.isEmpty()) {
                    // Sn
                    this.type = Type.S;
                    this.n = rotoreflections.get(0).getOrder();
                    this.subtype = null;
                } else {
                    // Dnd
                    this.type = Type.D;
                    this.n = rotoreflections.get(0).getOrder() / 2;
                    this.subtype = Subtype.d;
                }
            } else {
                if (rotationAxes.isEmpty()) {
                    if (h.inversionSymmetry()) {
                        // S2
                        this.type = Type.S;
                        this.n = 2;
                        this.subtype = null;
                    } else {
                        this.type = Type.C;
                        this.n = 1;
                        if (reflections.isEmpty()) {
                            // C1: no symmetries
                            this.subtype = null;
                        } else {
                            // C1h: one reflection only (same as C1v)
                            this.subtype = Subtype.h;
                        }
                    }
                } else {
                    var mainAxis = rotationAxes.stream().filter(r -> r.getOrder() > 2).findFirst();
                    if (mainAxis.isPresent()) {
                        this.n = mainAxis.get().getOrder();
                        if (rotationAxes.size() == 1) {
                            this.type = Type.C;
                            if (reflections.isEmpty()) {
                                this.subtype = null;
                            } else if (reflections.size() == 1) {
                                this.subtype = Subtype.h;
                            } else if (reflections.size() == mainAxis.get().getOrder()) {
                                this.subtype = Subtype.v;
                            } else {
                                throw new GeometryException("Cannot determine symmetry group.");
                            }
                        } else if (rotationAxes.size() == mainAxis.get().getOrder() + 1) {
                            this.type = Type.D;
                            if (reflections.isEmpty()) {
                                this.subtype = null;
                            } else if (reflections.size() == mainAxis.get().getOrder() + 1) {
                                this.subtype = Subtype.h;
                            } else {
                                throw new GeometryException("Cannot determine symmetry group.");
                            }
                        } else {
                            throw new GeometryException("Cannot determine symmetry group.");
                        }
                    } else {
                        // dihedral where the main axis is order 2
                        this.n = 2;
                        if (rotationAxes.size() == 1) {
                            this.type = Type.C;
                            if (reflections.isEmpty()) {
                                // C2: one rotation order 2 only (same as D1)
                                this.subtype = null;
                            } else if (reflections.size() == 1) {
                                // C2h: one rotation order 2 and one reflection (same as D1d)
                                this.subtype = Subtype.h;
                            } else if (reflections.size() == 2) {
                                // C2v: one rotation order 2 and two reflections (same as D1h)
                                this.subtype = Subtype.v;
                            } else {
                                throw new GeometryException("Cannot determine symmetry group");
                            }
                        } else {
                            this.type = Type.D;
                            if (reflections.isEmpty()) {
                                // D2: three rotations order 2 and no reflections
                                this.subtype = null;
                            } else if (reflections.size() == 3) {
                                // D2h: three rotations order 2 and three reflections
                                this.subtype = Subtype.h;
                            } else {
                                throw new GeometryException("Cannot determine symmetry group");
                            }
                        }
                    }
                }
            }
        }
    }

    public List<LinearTransformation3> generators() {
        List<LinearTransformation3> generators = new ArrayList<>();

        try {
            if (this.type == Type.D && this.subtype == Subtype.d) {
                // DnD
                generators.add(new RotoreflectionOrigin3(Vector3.zUnit,Math.PI/this.n));
            } else if (this.type == Type.C || this.type == Type.D) {
                // Cn, Cnh, Cnv, D, Dnh
                generators.add(new RotationOrigin3(Vector3.zUnit, 2*Math.PI/this.n));
                if (this.subtype == Subtype.h) {
                    // Cnh, Dnh
                    generators.add(new ReflectionOrigin3(Plane3.z0));
                }
                if (this.subtype == Subtype.v || this.type == Type.D && this.subtype == Subtype.h) {
                    // Cnv, Dnh
                    generators.add(new ReflectionOrigin3(Plane3.y0));
                }
                if (this.type == Type.D && this.subtype == null) {
                    // Dn
                    generators.add(new RotationOrigin3(Vector3.yUnit,Math.PI));
                }
            } else if (this.type == Type.S) {
                // Sn
                generators.add(new RotoreflectionOrigin3(Vector3.zUnit,2*Math.PI/this.n));
            }
        } catch (GeometryException e) {
            e.printStackTrace();
        }
        return generators;
    }

    @Override
    public String toString() {
        return this.type.toString() + (this.n == null ? "" : this.n) + (this.subtype == null ? "" : this.subtype.toString());
    }
}
