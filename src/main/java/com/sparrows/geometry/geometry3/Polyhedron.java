package com.sparrows.geometry.geometry3;

import com.sparrows.geometry.exception.*;
import com.sparrows.geometry.spherical.SphericalPolygon;
import com.sparrows.geometry.spherical.SphericalPolyhedron;
import com.sparrows.geometry.transformation.d3.AffineTransformation3;
import com.sparrows.geometry.transformation.Identity3;
import com.sparrows.geometry.transformation.d3.LinearTransformation3;
import com.sparrows.geometry.transformation.Reflection3;
import com.sparrows.geometry.transformation.Rotation3;
import com.sparrows.geometry.transformation.RotationalSymmetryAxis;
import com.sparrows.geometry.transformation.Rotoreflection3;
import com.sparrows.geometry.transformation.RotoreflectionalSymmetryAxis;
import com.sparrows.geometry.transformation.Translation3;
import com.sparrows.geometry.maths.Maths;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Polyhedron implements GeometryObject3<Polyhedron> {

    private Point3 centroid = null;
    private List<Point3> vertices = null;
    private List<List<Integer>> vertexFaces = null;
    private List<List<Integer>> faceVertices = null;
    private List<List<Integer>> vertexVertices = null;
    private List<LineSegment3> edges = null;
    private List<List<Integer>> edgeFaces = null;
    private List<List<Integer>> edgeVertices = null;
    private List<List<Integer>> edgeSides = null;
    private Boolean oriented = null;
    private List<AffineTransformation3> symmetries = null;
    private Boolean inversionSymmetry = null;
    private List<Reflection3> reflectionSymmetries = null;
    List<RotationalSymmetryAxis> rotationAxes = null;
    List<Rotation3> rotationSymmetries = null;
    List<RotoreflectionalSymmetryAxis> rotoreflectionAxes = null;
    List<Rotoreflection3> rotoreflectionSymmetries = null;

    private final List<Polygon3> faces;

    // Constructors
    public Polyhedron(List<Polygon3> faces) {
        if (faces.size() < 4) {
            throw new IllegalArgumentException("Not enough faces.");
        }
        this.faces = faces;
    }
    public Polyhedron(Polygon3 ... faces) {
        if (faces.length < 4) {
            throw new IllegalArgumentException("Not enough faces.");
        }
        this.faces = Arrays.asList(faces);
    }
    public Polyhedron(Polyhedron h) {
        this(h.faces.stream().map(Polygon3::new).collect(Collectors.toList()));
    }
    public Polyhedron(SphericalPolyhedron s) {
        this(s.getFaces().stream().map(Polygon3::new).collect(Collectors.toList()));
    }
    public Polyhedron(Polyhedron ... poly) {
        List<Polygon3> faceList = new ArrayList<>();
        for (var p : poly) {
            faceList.addAll(p.faces);
        }
        if (faceList.size() < 4) {
            throw new IllegalArgumentException("Not enough faces.");
        }
        faces = faceList;
    }

    // Getters
    public List<Polygon3> getFaces() {
        return faces;
    }
    public Polygon3 getFace(int f) {
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
    public List<Point3> vertices() {
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
    public List<List<Integer>> faceVertices() {
        findVertices();
        return faceVertices;
    }

    private void findVertices() {
        if (vertices == null) {
            vertices = new ArrayList<>();
            vertexFaces = new ArrayList<>();
            vertexVertices = new ArrayList<>();
            faceVertices = new ArrayList<>();
            for (var f = 0; f < faceCount(); f++) {
                Polygon3 face = getFace(f);
                faceVertices.add(new ArrayList<>());
                for (var v = 0; v < face.vertexCount(); v++) {
                    Point3 vertex = face.getVertex(v);
                    var found = false;
                    for (var i = 0; i < vertices.size(); i++) {
                        if (vertex.identical(vertices.get(i))) {
                            vertexFaces.get(i).add(f);
                            vertexVertices.get(i).add(v);
                            faceVertices.get(f).add(i);
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
                        faceVertices.get(f).add(vertices.size()-1);
                    }
                }
            }
        }
    }

    public List<LineSegment3> edges() {
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
                Polygon3 face = getFace(f);
                for (var v = 0; v < face.vertexCount(); v++) {
                    LineSegment3 side = face.side(v);
                    var found = false;
                    for (var i = 0; i < edges.size(); i++) {
                        if (side.identicalOrOpposite(edges.get(i))) {
                            // side v of face f adjoins edge i
                            // for j in 0..edgeFaces.get(i) {
                            //   face f side v matches face edgeFaces.get(i).get(j) side edgeSides.get(i).get(j)
                            //   and vice versa
                            // }
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
                        // side v of face f adjoins this new edge
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

    public List<List<Integer>> edgeVertices() {
        if (edgeVertices == null) {
            findVertices();
            findEdges();
            edgeVertices = new ArrayList<>();
            for (int e = 0; e < edges.size(); e++) {
                LineSegment3 edge = edges.get(e);
                edgeVertices.add(new ArrayList<>());
                Point3 p1 = edge.getPoint1();
                for (int v = 0; v < vertices.size(); v++) {
                    if (vertices.get(v).identical(p1)) {
                        edgeVertices.get(e).add(v);
                        break;
                    }
                }
                Point3 p2 = edge.getPoint2();
                for (int v = 0; v < vertices.size(); v++) {
                    if (vertices.get(v).identical(p2)) {
                        edgeVertices.get(e).add(v);
                        break;
                    }
                }
            }
        }
        return edgeVertices;
    }

    // Validate
    public void validate() throws GeometryException {
        // at least 4 faces
        // validate each face
        for (var face : faces) {
            face.validate();
        }
        // each vertex has at least 3 faces
        findVertices();
        for (var v = 0; v < vertices.size(); v++) {
            if (vertexFaces.get(v).size() < 3) {
                throw new InvalidVertexException(vertices.get(v), vertexFaces.get(v).size());
            }
        }
        // each side matches precisely one other on another face
        findEdges();
        for (var v = 0; v < edges.size(); v++) {
            if (edgeFaces.get(v).size() != 2) {
                throw new InvalidEdgeException(edges.get(v), edgeFaces.get(v).size());
            }
        }
        // no flat dihedral angles
        for (List<Integer> edgeFace : edgeFaces) {
            Polygon3 face1 = faces.get(edgeFace.get(0));
            Polygon3 face2 = faces.get(edgeFace.get(1));
            if (Maths.equal(face1.angle(face2), Math.PI)) {
                throw new InvalidDihedralAngle(edgeFace.get(0), edgeFace.get(1));
            }
        }
    }

    @Override
    public boolean identical(Polyhedron o) {
        if (faceCount() != o.faceCount()) {
            return false;
        }
        var matched = new boolean[faceCount()];
        for (var f = 0; f < faceCount(); f++) {
            Polygon3 face = getFace(f);
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

    public Point3 centroid() {
        if (centroid == null) {
            findVertices();
            centroid = Point3.centroid(vertices);
        }
        return centroid;
    }

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
                Polyhedron reflection = reflect(candidatePlane);
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
                    } catch (IllegalArgumentException e) {
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
            if (vertices.size()>=80 && p1%10==0 && p1>0) System.out.println("..."+p1+"/"+vertices.size());
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
                            } catch (IllegalArgumentException e) {
                                // means centre is the average of point1, point2 and point3
                            }
                        }
                    }
                }
            }
        }

        return candidateAxes.stream().filter(a -> a.getOrder() >=3).collect(Collectors.toList());
    }

    public List<RotationalSymmetryAxis> rotationAxes() {
        if (rotationAxes == null) {
            findVertices();

            rotationAxes = new ArrayList<>();

            List<RotationalSymmetryAxis> candidateThreeFoldAxes = candidateThreeFoldSymmetryAxes();
            // check candidates for actual axes of symmetry
            for (var r : candidateThreeFoldAxes) {
                for (int divisor = r.getOrder(); divisor >= 3; divisor--) {
                    if (r.getOrder() / divisor * divisor == r.getOrder()) {
                        if (identical(rotate(r.getAxis(), Math.PI * 2 / divisor))) {
                            rotationAxes.add(new RotationalSymmetryAxis(r.getAxis(), divisor));
                            break;
                        }
                    }
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

    private class FaceEdge {
        private int face;
        private int edge;

        public FaceEdge(int face, int edge) {
            this.face = face;
            this.edge = edge;
        }
    }
    /**
     * <p>Orient this polyhedron.</p>
     * @return This polyhedron with its faces oriented.
     */
    public Polyhedron orient() {
        return this;
    }

    public void unityData() {
        System.out.println("Polyhedron XXX = new Polyhedron(");
        System.out.println("    new List<Polygon> {");
        for (int f = 0; f < faceCount(); f++) {
            System.out.print("        new Polygon (new List<Vector3> { ");
            Polygon3 face = faces.get(f);
            for (int v = 0; v < face.vertexCount(); v++) {
                Point3 vertex = face.getVertex(v);
                System.out.print("new Vector3" + "(" + vertex.getX() + "f," + vertex.getY() + "f," + vertex.getZ() + "f)");
                if (v < face.vertexCount() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.print(" })");
            if (f < faceCount()-1) {
                System.out.print(",");
            }
            System.out.println();
        }
        System.out.println("    }");
        System.out.println(");");
    }

    public String objString() {
        String s = "";
        for (Point3 vertex : this.vertices()) {
            s += "v " + vertex.getX() + " " + vertex.getY() + " " + vertex.getZ() + "\r\n";
        }
        for (Polygon3 face : this.faces) {
            s += "f";
            for (Point3 vertex : face.getVertices()) {
                for (int v = 0; v < this.vertices().size(); v++) {
                    if (vertex.identical(this.vertices.get(v))) {
                        s += " " + (v+1);
                        break;
                    }
                }
            }
            s += "\r\n";
        }
        return s;
    }

    public String objStringIndependentFaces() {
        String s = "";
        int totalVertices = 0;
        for (var face : getFaces()) {
            for (Point3 vertex : face.getVertices()) {
                s += "v " + vertex.getX() + " " + vertex.getY() + " " + vertex.getZ() + "\r\n";
            }
            s += "f ";
            for (int v = 0; v < face.getVertices().size(); v++) {
                s += " " + ((totalVertices++) + 1);
            }
            s += "\r\n";
        }
        return s;
    }

    public void writeObjFile(String fileName) throws IOException {
        String s = objString();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + ".obj"))) {
            writer.write(s);
        }
    }

    public void writeObjFileIndependentFaces(String fileName) throws IOException {
        String s = objStringIndependentFaces();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + ".obj"))) {
            writer.write(s);
        }
    }

    public void writeFaceObjFiles(String fileName) throws IOException {
        for (var v = 0; v < faceCount(); v++) {
            String faceFileName = fileName + (v+1) + ".obj";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(faceFileName))) {
                writer.write(getFace(v).objString());
            }
        }
    }

    public void writeFacePrismObjFiles(String fileName) throws IOException {
        Point3 centre = centroid();
        String s = objString();
        this.writeObjFile(fileName);
        for (var v = 0; v < faceCount(); v++) {
            Polygon3 face1 = getFace(v).reverse();
            Polygon3 face2 = null;
            try {
                face2 = face1.scale(centre,1.01);
            } catch (GeometryException e) {
                e.printStackTrace();
            }
            Polyhedron facePrism = StandardPolyhedra.prism(face1,face2);
            String faceFileName = fileName + (v+1);
            facePrism.writeObjFile(faceFileName);
        }
    }

    @Override
    public Polyhedron linearTransform(LinearTransformation3 t) throws GeometryException {
        return new Polyhedron(faces.stream().map(f -> f.linearTransform(t)).collect(Collectors.toList()));
    }

    @Override
    public Polyhedron affineTransform(AffineTransformation3 t) throws GeometryException {
        return new Polyhedron(faces.stream().map(f -> f.affineTransform(t)).collect(Collectors.toList()));
    }

    @Override
    public Polyhedron translate(Translation3 t) {
        return new Polyhedron(faces.stream().map(f -> f.translate(t)).collect(Collectors.toList()));
    }

    private Boolean isFaceEquilateral;
    public boolean isFaceEquilateral() {
        if (isFaceEquilateral == null) {
            isFaceEquilateral = faces.stream().allMatch(Polygon3::isEquilateral);
        }
        return isFaceEquilateral;
    }

    private Boolean isFaceEquiangular;
    public boolean isFaceEquiangular() {
        if (isFaceEquiangular == null) {
            isFaceEquiangular = faces.stream().allMatch(Polygon3::isEquiangular);
        }
        return isFaceEquiangular;
    }

    public boolean isFaceRegular() {
        return isFaceEquilateral() && isFaceEquiangular();
    }

}
