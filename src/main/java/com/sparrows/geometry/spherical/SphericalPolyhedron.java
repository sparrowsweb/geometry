package com.sparrows.geometry.spherical;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.sparrows.geometry.exception.GeometryException;
import com.sparrows.geometry.exception.InvalidEdgeException;
import com.sparrows.geometry.exception.InvalidSphericalVertex;
import com.sparrows.geometry.exception.NotEnoughSphericalFaces;
import com.sparrows.geometry.geometry3.Point3;
import com.sparrows.geometry.transformation.d3.AffineTransformation3;
import com.sparrows.geometry.transformation.Reflection3;
import com.sparrows.geometry.transformation.Rotation3;
import com.sparrows.geometry.transformation.RotationalSymmetryAxis;
import com.sparrows.geometry.transformation.Rotoreflection3;
import com.sparrows.geometry.transformation.RotoreflectionalSymmetryAxis;
import com.sparrows.geometry.transformation.SphericalTransformation;

public class SphericalPolyhedron implements SphericalObject<SphericalPolyhedron> {

    private Point3 centroid = null;
    private List<SphericalPoint> vertices = null;
    private List<List<Integer>> vertexFaces = null;
    private List<List<Integer>> vertexVertices = null;
    private List<Arc> edges = null;
    private List<List<Integer>> edgeFaces = null;
    private List<List<Integer>> edgeSides = null;
    private Boolean oriented = null;
    private List<AffineTransformation3> symmetries = null;
    private Boolean inversionSymmetry = null;
    private List<Reflection3> reflectionSymmetries = null;
    List<RotationalSymmetryAxis> rotationAxes = null;
    List<Rotation3> rotationSymmetries = null;
    List<RotoreflectionalSymmetryAxis> rotoreflectionAxes = null;
    List<Rotoreflection3> rotoreflectionSymmetries = null;

    private final List<SphericalPolygon> faces;

    // Constructors
    public SphericalPolyhedron(List<SphericalPolygon> faces) throws NotEnoughSphericalFaces {
        if (faces.size() < 2) {
            throw new NotEnoughSphericalFaces();
        }
        this.faces = faces;
    }
    public SphericalPolyhedron(SphericalPolyhedron h) throws NotEnoughSphericalFaces {
        this(h.faces.stream().map(SphericalPolygon::new).collect(Collectors.toList()));
    }

    // Getters
    public List<SphericalPolygon> getFaces() {
        return faces;
    }
    public SphericalPolygon getFace(int f) {
        return faces.get(f);
    }
    public int faceCount() {
        return faces.size();
    }

    public int vertexCount() {
        return vertices().size();
    }

    public int edgeCount() {
        return edges().size();
    }
    public List<SphericalPoint> vertices() {
        findVertices();
        return vertices;
    }
    public List<List<Integer>> vertexFaces() {
        findVertices();
        return vertexFaces;
    }
    public List<List<Integer>> vertexVertices() {
        findVertices();
        return vertexVertices;
    }
    private void findVertices() {
        if (vertices == null) {
            vertices = new ArrayList<>();
            vertexFaces = new ArrayList<>();
            vertexVertices = new ArrayList<>();
            for (var f = 0; f < faceCount(); f++) {
                SphericalPolygon face = getFace(f);
                for (var v = 0; v < face.vertexCount(); v++) {
                    SphericalPoint vertex = face.getVertex(v);
                    var found = false;
                    for (var i = 0; i < vertices.size(); i++) {
                        if (vertex.identical(vertices.get(i))) {
                            vertexFaces.get(i).add(f);
                            vertexVertices.get(i).add(v);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        vertices.add(face.getVertex(v));
                        ArrayList<Integer> x = new ArrayList<>();
                        x.add(f);
                        vertexFaces.add(x);
                        ArrayList<Integer> y = new ArrayList<>();
                        y.add(v);
                        vertexVertices.add(y);
                    }
                }
            }
        }
    }

    public List<Arc> edges() {
        findEdges();
        return edges;
    }
    public List<List<Integer>> edgeFaces() {
        findEdges();
        return edgeFaces;
    }
    public List<List<Integer>> edgeSides() {
        findEdges();
        return edgeSides;
    }
    public boolean oriented() {
        findEdges();
        return oriented;
    }
    private void findEdges() {
        if (edges == null) {
            oriented = true;
            edges = new ArrayList<>();
            edgeFaces = new ArrayList<>();
            edgeSides = new ArrayList<>();
            for (var f = 0; f < faceCount(); f++) {
                SphericalPolygon face = getFace(f);
                for (var v = 0; v < face.vertexCount(); v++) {
                    Arc side = face.side(v);
                    var found = false;
                    for (var i = 0; i < edges.size(); i++) {
                        if (side.identicalOrOpposite(edges.get(i))) {
                            edgeFaces.get(i).add(f);
                            edgeSides.get(i).add(v);
                            if (side.identical(edges.get(i))) {
                                oriented = false;
                            }
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        edges.add(side);
                        ArrayList<Integer> x = new ArrayList<>();
                        x.add(f);
                        edgeFaces.add(x);
                        ArrayList<Integer> y = new ArrayList<>();
                        y.add(v);
                        edgeSides.add(y);
                    }
                }
            }
        }
    }

    List<Integer>[][] faceFaces;
    List<Integer>[][] faceSides;
    public void findFaceData() {
        if (faceFaces == null) {
            findEdges();
            faceFaces = new ArrayList[faces.size()][];
            faceSides = new ArrayList[faces.size()][];

            for (var f = 0; f < faceCount(); f++) {
                faceFaces[f] = new ArrayList[faces.get(f).vertexCount()];
                faceSides[f] = new ArrayList[faces.get(f).vertexCount()];
                for (var s = 0; s < faces.get(f).vertexCount(); s++) {
                    faceFaces[f][s] = new ArrayList<>();
                    faceSides[f][s] = new ArrayList<>();
                }
            }

            for (var e = 0; e < edgeFaces.size(); e++) {
                for (var i = 0; i < edgeFaces.get(e).size(); i++) {
                    // edge e borders face edgeFaces[e][i]
                    //                edge edgeSides[e][i]
                    // so face edgeFaces[e][i] edge edgeSides[e][i] borders the rest
                    for (var j = 0; j < edgeFaces.get(e).size(); j++) {
                        if (i != j) {
                            // face edgeFaces[e][i] edge edgeSides[e][i] borders face edgeFaces[e][j] edge edgeSides[e][j]
                            //System.out.println("face " + edgeFaces.get(e).get(i) + " edge " + edgeSides.get(e).get(i) + " adjoins " +
                              //      "face " + edgeFaces.get(e).get(j) + " edge " + edgeSides.get(e).get(j));
                            faceFaces[edgeFaces.get(e).get(i)][edgeSides.get(e).get(i)].add(edgeFaces.get(e).get(j));
                            faceSides[edgeFaces.get(e).get(i)][edgeSides.get(e).get(i)].add(edgeSides.get(e).get(j));
                        }
                    }
                }
            }
        }
    }

    public List<SphericalPolyhedron> split() throws NotEnoughSphericalFaces {
        findFaceData();

        List<SphericalPolyhedron> splitList = new ArrayList<>();
        List<SphericalPolygon> faceList;
        boolean[] processed = new boolean[getFaces().size()];
        processed[0] = true;

        for (var start = 0; start < faceCount(); start++) {
            if (!processed[start]) {

                faceList = new ArrayList<>();
                faceList.add(faces.get(start));
                for (var f = 0; f < faceList.size(); f++) {
                    for (var s = 0; s < faces.get(f).vertexCount(); s++) {
                        for (var i = 0; i < faceFaces[f][s].size(); i++) {
                            if (!processed[faceFaces[f][s].get(i)]) {
                                faceList.add(faces.get(faceFaces[f][s].get(i)));
                                processed[faceFaces[f][s].get(i)] = true;
                            }
                        }
                    }
                }
                splitList.add(new SphericalPolyhedron(faceList));

            }
        }
        return splitList;
    }

    // Validate
    public void validate() throws GeometryException {
        // at least 2 faces
        // validate each face
        for (var face : faces) {
            face.validate();
        }
        // each vertex has at least 2 faces
        findVertices();
        for (var v = 0; v < vertices.size(); v++) {
            if (vertexFaces.get(v).size() < 2) {
                throw new InvalidSphericalVertex(vertices.get(v), vertexFaces.get(v).size());
            }
        }
        // each side matches precisely one other on another face
        findEdges();
        for (var v = 0; v < edges.size(); v++) {
            if (edgeFaces.get(v).size() != 2) {
                throw new InvalidEdgeException(edges.get(v), edgeFaces.get(v).size());
            }
        }
    }

    @Override
    public boolean identical(SphericalPolyhedron o) {
        if (faceCount() != o.faceCount()) {
            return false;
        }
        var matched = new boolean[faceCount()];
        for (var f = 0; f < faceCount(); f++) {
            SphericalPolygon face = getFace(f);
            var match = false;
            for (var f2 = 0; f2 < faceCount(); f2++) {
                if (!matched[f2]) {
                    if (face.identicalOrOpposite(o.getFace(f2))) {
                        match = true;
                        matched[f2] = true;
                        break;
                    }
                }
            }
            if (!match) {
                return false;
            }
        }
        return true;
    }

/*
    // Symmetry
    public List<AffineTransformation3> symmetries() {
        if (symmetries == null) {
            symmetries = new ArrayList<>();
            symmetries.add(new AffineTransformation3(new Identity3()));
            symmetries.addAll(reflectionSymmetries());
            symmetries.addAll(rotationSymmetries());
            symmetries.addAll(rotationPlusReflectionSymmetries());
        }
        return symmetries;
    }

    public boolean inversionSymmetry() {
        if (inversionSymmetry == null) {
            inversionSymmetry = identical(invert(centroid()));
        }
        return inversionSymmetry;
    }

    public List<Reflection3> reflectionSymmetries() {
        if (reflectionSymmetries == null) {
            reflectionSymmetries = new ArrayList<>();
            // plane bisecting two vertices
            findVertices();
            List<Plane3> candidatePlanes = new ArrayList<>();
            for (var v1 = 0; v1 < vertices.size(); v1++) {
                for (var v2 = v1 + 1; v2 < vertices.size(); v2++) {
                    try {
                        Plane3 bisector = vertices.get(v1).bisectingPlane(vertices.get(v2));
                        var match = false;
                        for (var candidatePlane : candidatePlanes) {
                            if (bisector.identicalOrOpposite(candidatePlane)) {
                                match = true;
                                break;
                            }
                        }
                        if (!match) {
                            candidatePlanes.add(bisector);
                        }
                    } catch (ZeroVectorException e) {
                        // shouldn't happen
                    }
                }
            }

            for (var candidatePlane : candidatePlanes) {
                SphericalPolyhedron reflection = reflect(candidatePlane);
                if (identical(reflection)) {
                    reflectionSymmetries.add(new Reflection3(candidatePlane));
                }
            }
        }
        return reflectionSymmetries;
    }

    // axes with 2-fold symmetry (order may actually be a multiple of 2)
    private List<RotationalSymmetryAxis> twoFoldSymmetryAxes() {
        findVertices();
        // look for possible axes of 2-fold symmetry
        List<RotationalSymmetryAxis> candidateAxes = new ArrayList<>();
        var centre = Point3.centroid(vertices);
        for (var p1 = 0; p1 < vertices.size(); p1++) {
            Point3 point1 = vertices.get(p1);
            double point1DistanceSquaredCentre = point1.distanceSquared(centre);
            for (int p2 = p1 + 1; p2 < vertices.size(); p2++) {
                Point3 point2 = vertices.get(p2);
                double point2DistanceSquaredCentre = point2.distanceSquared(centre);
                if (Maths.equal(point2DistanceSquaredCentre, point1DistanceSquaredCentre)) {
                    try {
                        var axis = new Line3(centre,point1.midpoint(point2));
                        var match = false;
                        for (var candidateAxis : candidateAxes) {
                            if (axis.identicalOrOpposite(candidateAxis.getAxis())) {
                                match = true;
                                break;
                            }
                        }
                        if (!match) {
                            candidateAxes.add(new RotationalSymmetryAxis(axis,2));
                        }
                    } catch (ZeroVectorException e) {
                        // ignore
                    }
                }
            }
        }

        return candidateAxes.stream().filter(r -> identical(rotate(r.getAxis(), Math.PI))).collect(Collectors.toList());
    }

    private List<RotationalSymmetryAxis> candidateThreeFoldSymmetryAxes() {
        findVertices();
        List<RotationalSymmetryAxis> candidateAxes;
        // look for axes of symmetry of order 3 or more
        // look for triplets of points
        candidateAxes = new ArrayList<>();
        var centre = Point3.centroid(vertices);
        for (var p1 = 0; p1 < vertices.size(); p1++) {
            Point3 point1 = vertices.get(p1);
            double point1DistanceSquaredCentre = point1.distanceSquared(centre);
            for (int p2 = p1+1; p2 < vertices.size(); p2++) {
                Point3 point2 = vertices.get(p2);
                double point2DistanceSquaredCentre = point2.distanceSquared(centre);
                if (Maths.equal(point2DistanceSquaredCentre, point1DistanceSquaredCentre)) {
                    for (var p3 = p2+1; p3 < vertices.size(); p3++) {
                        var point3 = vertices.get(p3);
                        if (Maths.equal(point3.distanceSquared(centre), point1DistanceSquaredCentre)) {
                            try {
                                var normal = new Vector3(point1,point2).cross(new Vector3(point2,point3));
                                var axis = new Line3(centre,normal);

                                var plane = new Plane3(axis.getVector(),point1);
                                int maxOrder = (int)vertices.stream().filter(plane::contains).count();

                                var match = false;
                                for (var candidateAxis : candidateAxes) {
                                    if (axis.identicalOrOpposite(candidateAxis.getAxis())) {
                                        if (maxOrder != candidateAxis.getOrder()) {
                                            //System.out.println("max "+maxOrder+"->update "+candidateAxis.getOrder()+" to " + Maths.hcf(candidateAxis.getOrder(), maxOrder));
                                            int hcf = Maths.hcf(candidateAxis.getOrder(), maxOrder);
                                            candidateAxis.setOrder(hcf);
                                        }
                                        match = true;
                                        break;
                                    }
                                }
                                if (!match) {
                                    candidateAxes.add(new RotationalSymmetryAxis(axis,maxOrder));
                                }
                            } catch (ZeroVectorException e) {
                                // means centre is the average of point1, point2 and point3
                            }
                        }
                    }
                }
            }
        }

        return candidateAxes.stream().filter(a -> a.getOrder() != 1).collect(Collectors.toList());
    }

    public List<RotationalSymmetryAxis> rotationAxes() {
        if (rotationAxes == null) {
            findVertices();

            rotationAxes = new ArrayList<>();

            List<RotationalSymmetryAxis> candidateThreeFoldAxes = candidateThreeFoldSymmetryAxes();
            // check candidates for actual axes of symmetry
            for (var r : candidateThreeFoldAxes) {
                    if (identical(rotate(r.getAxis(), Math.PI * 2 / r.getOrder()))) {
                        rotationAxes.add(r);
                        // TBD also try divisors of r.getOrder()
                    }
            }

            // add 2-fold axes
            List<RotationalSymmetryAxis> twoFoldAxes = twoFoldSymmetryAxes();
            for (var r : twoFoldAxes) {
                var match = false;
                for (var s : rotationAxes) {
                    if (s.getAxis().identicalOrOpposite(r.getAxis())) {
                        match = true;
                        break;
                    }
                }
                if (!match) {
                    rotationAxes.add(r);
                }
            }
        }
        return rotationAxes;
    }

    public List<Rotation3> rotationSymmetries() {
        if (rotationSymmetries == null) {
            rotationSymmetries = new ArrayList<>();
            for (var r : rotationAxes()) {
                for (var i = 1; i < r.getOrder(); i++) {
                    rotationSymmetries.add(new Rotation3(r.getAxis(), 2 * Math.PI * i / r.getOrder()));
                }
            }
        }
        return rotationSymmetries;
    }

    public List<RotoreflectionalSymmetryAxis> rotoreflectionAxes() {
        if (rotoreflectionAxes == null) {
            rotoreflectionAxes = new ArrayList<>();
            var rotationalSymmetryAxes = rotationAxes();
            for (var r : rotationalSymmetryAxes) {
                try {
                    if (identical(rotoreflect(r.getAxis(), Math.PI / r.getOrder(), centroid()))) {
                        rotoreflectionAxes.add(new RotoreflectionalSymmetryAxis(r.getAxis(), 2 * r.getOrder(), centroid()));
                    }
                } catch (GeometryException e) {
                    // can't happen
                }
            }
        }
        return rotoreflectionAxes;
    }

    public List<Rotoreflection3> rotoreflectionSymmetries() {
        if (rotoreflectionSymmetries == null) {
            rotoreflectionSymmetries = new ArrayList<>();
            for (var r : rotoreflectionAxes()) {
                for (var i = 1; i < r.getOrder(); i += 2) {
                    try {
                        rotoreflectionSymmetries.add(new Rotoreflection3(r.getAxis(), Math.PI * i / r.getOrder(), centroid()));
                    } catch (GeometryException e) {
                        // can't happen
                    }
                }
            }
        }
        return rotoreflectionSymmetries;
    }

    private List<AffineTransformation3> rotationPlusReflectionSymmetries() {
        List<AffineTransformation3> rotationPlusReflectionSymmetries = new ArrayList<>();

        for (var rot : rotationSymmetries()) {
            for (var ref : reflectionSymmetries()) {
                AffineTransformation3 rotRef = rot.compose(ref);
                var found = false;
                for (var rr : rotationPlusReflectionSymmetries) {
                    if (rotRef.identical(rr)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    for (var r : reflectionSymmetries()) {
                        if (rotRef.identical(r)) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        rotationPlusReflectionSymmetries.add(rotRef);
                    }
                }
            }
        }
        return rotationPlusReflectionSymmetries;
    }
*/
    @Override
    public SphericalPolyhedron sphericalTransform(SphericalTransformation t) {
        try {
            return new SphericalPolyhedron(faces.stream().map(f -> f.sphericalTransform(t)).collect(Collectors.toList()));
        } catch (NotEnoughSphericalFaces notEnoughSphericalFaces) {
            notEnoughSphericalFaces.printStackTrace();
            return null;
        }
    }

    private Boolean isFaceEquilateral;
    public boolean isFaceEquilateral() {
        if (isFaceEquilateral == null) {
            isFaceEquilateral = faces.stream().allMatch(SphericalPolygon::isEquilateral);
        }
        return isFaceEquilateral;
    }

    private Boolean isFaceEquiangular;
    public boolean isFaceEquiangular() {
        if (isFaceEquiangular == null) {
            isFaceEquiangular = faces.stream().allMatch(SphericalPolygon::isEquiangular);
        }
        return isFaceEquiangular;
    }

    public boolean isFaceRegular() {
        return isFaceEquilateral() && isFaceEquiangular();
    }
/*
    private static SphericalPolyhedron regular(int index) throws InvalidRegularPolyhedronIndex {
        switch (index) {
            case 1:
                return regular(3,1, Math.acos(1./3));
            case 2:
                return regular(4,1, Maths.PI2);
            case 3:
                return regular(3,1, Math.acos(-1./3));
            case 4:
                return regular(5,1, Math.acos(-Maths.SQRT5/5));
            case 5:
                return regular(3,1, Math.acos(-Maths.SQRT5/3));
            case 6:
                return regular(5,2, Math.acos(-Maths.SQRT5/5));
            case 7:
                return regular(5,2, Math.acos(Maths.SQRT5/5));
            case 8:
                return regular(5,1, Math.acos(Maths.SQRT5/5));
            case 9:
                return regular(3,1, Math.acos(Maths.SQRT5/3));
            default:
                throw new InvalidRegularPolyhedronIndex();
        }
    }

    private static SphericalPolyhedron regular(int faceSides, int faceDensity, double dihedralAngle) {
        List<Polygon3> faces = new ArrayList<>();
        try {
            faces.add(Polygon3.regular(faceSides, faceDensity));
            for (var f = 0; f < faces.size(); f++) {
                for (var side = 0; side < faceSides; side++) {
                    Line3 axis = faces.get(f).sideLine(side);
                    Polygon3 nextFace = faces.get(f).rotate(axis, -dihedralAngle).reverse();
                    var skip = false;
                    for (Polygon3 face : faces) {
                        if (nextFace.identical(face)) {
                            skip = true;
                            break;
                        }
                    }
                    if (!skip) {
                        faces.add(nextFace);
                    }
                }
            }
            return new SphericalPolyhedron(faces);
        } catch (GeometryException e) {
            return null;
        }
    }

    public static SphericalPolyhedron uniformPrism(int sides, int density) throws GeometryException {
        GeometryUtils.validateSidesDensity(sides, density);
        var face1 = Polygon3.regular(sides, density);
        Polygon3 face2 = face1.translate(Vector3.zUnit);
        List<Polygon3> faces = new ArrayList<>();
        faces.add(face1);
        for (var i = 0; i < sides; i++) {
            faces.add(new Polygon3(
                    face1.getVertex((i+1)%sides),
                    face1.getVertex(i),
                    face2.getVertex(i),
                    face2.getVertex((i+1)%sides)));
        }
        faces.add(face2.reverse());
        return new SphericalPolyhedron(faces);
    }

    public static SphericalPolyhedron uniformAntiprism(int sides, int density) throws GeometryException {
        GeometryUtils.validateSidesDensity(sides, density);
        var face1 = Polygon3.regular(sides, density);
        double height = Math.sqrt(1-1./4/Maths.square(Math.cos(Maths.PI2*density/sides)));
        Polygon3 face2 = face1.translate(Vector3.zUnit.multiply(height)).rotate(Line3.zAxis,Math.PI*density/sides);
        List<Polygon3> faces = new ArrayList<>();
        faces.add(face1);
        for (var i = 0; i < sides; i++) {
            faces.add(new Polygon3(
                    face1.getVertex((i+1)%sides),
                    face1.getVertex(i),
                    face2.getVertex(i)));
            faces.add(new Polygon3(
                    face1.getVertex(i),
                    face2.getVertex((i+sides-1)%sides),
                    face2.getVertex(i)));
        }
        faces.add(face2.reverse());
        return new SphericalPolyhedron(faces);
    }
    */
}
