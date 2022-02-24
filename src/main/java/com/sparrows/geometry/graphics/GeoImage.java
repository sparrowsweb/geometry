package com.sparrows.geometry.graphics;

import com.sparrows.geometry.geometry2.Point2;
import com.sparrows.geometry.geometry2.Polygon2;
import com.sparrows.geometry.geometry3.*;
import com.sparrows.geometry.maths.Range;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class GeoImage extends BufferedImage {
    int pixelWidth;
    int pixelHeight;
    double left;
    double right;
    double bottom;
    double top;
    double width;
    double height;
    Graphics2D graphics;
    public GeoImage(int pixelWidth, int pixelHeight, double left, double bottom, double width) {
        super(pixelWidth, pixelHeight, BufferedImage.TYPE_INT_RGB);
        this.pixelWidth = pixelWidth;
        this.pixelHeight = pixelHeight;
        this.left = left;
        this.width = width;
        this.right = this.left + this.width;
        this.bottom = bottom;
        this.height = (pixelHeight-1) * width / (pixelWidth-1);
        this.top = this.bottom + this.height;
        this.graphics = (Graphics2D) this.getGraphics();
    }

    public void drawLine(Point3 p1, Point3 p2) {
        drawLine(p1.project2D(),p2.project2D());
    }

    public void drawLine(Point2 p1, Point2 p2) {
        drawLine(p1.getX(),p1.getY(),p2.getX(),p2.getY());
    }

    public void drawLine(double x1, double y1, double x2, double y2) {
        Point p1 = convertCoordinatesToPixels(new Point2(x1,y1));
        Point p2 = convertCoordinatesToPixels(new Point2(x2,y2));
        graphics.drawLine(p1.x,p1.y,p2.x,p2.y);
    }

    public void drawPoint(Point3 p) {
        drawPoint(p.project2D());
    }

    public void drawPoint(Point2 p) {
        drawPoint(p.getX(),p.getY());
    }

    public void drawPoint(double x, double y) {
        drawLine(x,y,x,y);
    }

    public void drawPolygon(Polygon3 polygon) {
        drawPolygon(polygon.project2D());
    }

    public void drawPolygon(Polygon2 polygon) {
        graphics.drawPolygon(convertPolygon2ToPolygon(polygon));
    }

    public void fillPolygon(Polygon3 polygon) {
        fillPolygon(polygon.project2D());
    }

    public void fillPolygon(Polygon2 polygon) {
        graphics.fillPolygon(convertPolygon2ToPolygon(polygon));
    }

    public void fillPolygonB(Polygon3 polygon) {
        Polygon2 polygon2 = polygon.project2D();
        Range yRange = yRange(polygon2);

        for (int pixelY = 0; pixelY < getWidth(); pixelY++) {
            double y = convertPixelYToY(pixelY);
            if (y >= yRange.getStart() && y <= yRange.getEnd()) {
                Range xRange = convexIntersection(polygon2,y);
                graphics.drawLine(convertXToPixelX(xRange.getStart()),pixelY,convertXToPixelX(xRange.getEnd()),pixelY);
            }
        }
    }

    private Range yRange(Polygon2 polygon) {
        boolean first = true;
        double minY = 0, maxY = 0;
        for (var vertex : polygon.getVertices()) {
            if (first) {
                minY = vertex.getY();
                maxY = minY;
                first = false;
            } else {
                if (vertex.getY() < minY) {
                    minY = vertex.getY();
                } else if (vertex.getY() > maxY) {
                    maxY = vertex.getY();
                }
            }
        }
        return new Range(minY,maxY);
    }

    public Range convexIntersection(Polygon2 polygon, double y) {
        double lowX = 0, highX = 0;
        for (int v = 0; v < polygon.vertexCount(); v++) {
            Point2 p1 = polygon.getVertex(v);
            Point2 p2 = polygon.getVertex((v+1)%polygon.vertexCount());
            if (p1.getY() <= y && p2.getY() >= y || p1.getY() >= y && p2.getY() <= y) {
                double xCross = p1.getX() + (y - p1.getY()) / (p2.getY() - p1.getY()) * (p2.getX() - p1.getX());
                if (p1.getY() <= y && p2.getY() >= y) {
                    highX = xCross;
                } else {
                    lowX = xCross;
                }
            }
        }
        return new Range(lowX,highX);
    }

    public void drawConvexPolyhedron(Polyhedron polyhedron) {
        for (Polygon3 polygon : polyhedron.getFaces()) {
            Vector3 normal;
            normal = polygon.plane().getNormal();
            float dot = (float)Vector3.Z_UNIT.dot(normal);
            if (dot <= 0) {
                Color savedColour = graphics.getColor();
                graphics.setColor(dimColour(savedColour,-dot));
                fillPolygon(polygon);

                graphics.setColor(Color.BLACK);
                drawPolygon(polygon);

                graphics.setColor(savedColour);
            }
        }
    }

    public void drawConcavePolyhedron(Polyhedron polyhedron, java.util.List<Color> colours, boolean oriented) {
        var pixelPolygons = polyhedron.getFaces().stream().
                map(Polygon3::project2D).
                map(this::convertPolygon2ToPolygon).
                collect(Collectors.toList());
        var planes = polyhedron.getFaces().stream().map(Polygon3::plane).collect(Collectors.toList());
        var normals = planes.stream().map(Plane3::getNormal).collect(Collectors.toList());
        var dots = normals.stream().map(x -> x.dot(Vector3.Z_UNIT)).collect(Collectors.toList());
        var faceRGBs = new ArrayList<Integer>();
        for (int i = 0; i < polyhedron.faceCount(); i++) {
            faceRGBs.add(dimColour(colours.get(i),Math.abs(dots.get(i))).getRGB());
        }
        // hx + jy + kz = d
        // z = (d - hx - kz)/k
        for (int pixelX = 0; pixelX < getWidth(); pixelX++) {
            double x = convertPixelXToX(pixelX);
            double y = convertPixelYToY(0);
            for (int pixelY = 0; pixelY < getWidth(); pixelY++) {
                Point pixelPoint = new Point(pixelX,pixelY);
                boolean found = false;
                double maxZ = 0;
                int nearestFace = 0;
                for (int face = 0; face < pixelPolygons.size(); face++) {
                    if (!oriented || dots.get(face) > 0) {
                        if (pixelPolygons.get(face).contains(pixelPoint)) {
                            double z =
                                    (planes.get(face).getDistanceOrigin() -
                                            normals.get(face).getX() * x -
                                            normals.get(face).getY() * y) / normals.get(face).getZ();
                            if (!found) {
                                found = true;
                                maxZ = z;
                                nearestFace = face;
                            } else {
                                if (z > maxZ) {
                                    maxZ = z;
                                    nearestFace = face;
                                }
                            }
                        }
                    }
                }
                if (found) {
                    setRGB(pixelX,pixelY,faceRGBs.get(nearestFace));
                }
                y -= height / (pixelHeight-1);
            }
        }
    }

    public void setColour(Color colour) {
        graphics.setColor(colour);
    }

    public static Color dimColour(Color colour, double factor) {
        float[] c = colour.getRGBColorComponents(null);
        c[0] *= factor;
        c[1] *= factor;
        c[2] *= factor;
        return  new Color((int) (255 * c[0]), (int) (255 * c[1]), (int) (255 * c[2]));
    }

    public void setStroke(Stroke stroke) {
        graphics.setStroke(stroke);
    }

    public void write(String fileName) throws IOException {
        RenderedImage rendImage = this;
        ImageIO.write(rendImage, "png", new File(fileName));
    }

    private Point convertCoordinatesToPixels(Point2 p) {
        double pixelX = (p.getX() - left) * (pixelWidth-1) / width;
        double pixelY = (p.getY() - bottom) * (pixelHeight-1) / height;
        pixelY = pixelHeight-1-pixelY;
        return new Point((int)pixelX,(int)pixelY);
    }

    private int convertXToPixelX(double x) {
        return (int)((x - left) * (pixelWidth-1) / width);
    }

    private int convertYToPixelY(double y) {
        return pixelHeight-1-(int)((y - bottom) * (pixelHeight-1) / height);
    }

    private double convertPixelXToX(int pixelX) {
        return  (pixelX * width) / (pixelWidth-1) + left;
    }

    private double convertPixelYToY(int pixelY) {
        pixelY = pixelHeight-1-pixelY;
        return  (pixelY * height) / (pixelHeight-1) + bottom;
    }

    private Polygon convertPolygon2ToPolygon(Polygon2 polygon2) {
        Polygon polygon = new Polygon();
        for (Point2 vertex : polygon2.getVertices()) {
            Point p = convertCoordinatesToPixels(vertex);
            polygon.addPoint(p.x,p.y);
        }
        return polygon;
    }
}
