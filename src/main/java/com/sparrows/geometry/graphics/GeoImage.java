package com.sparrows.geometry.graphics;

import com.sparrows.geometry.geometry2.Point2;
import com.sparrows.geometry.geometry2.Polygon2;
import com.sparrows.geometry.geometry3.Point3;
import com.sparrows.geometry.geometry3.Polygon3;
import com.sparrows.geometry.geometry3.Polyhedron;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

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

    public void drawPolyhedron(Polyhedron polyhedron) {
        for (Polygon3 polygon : polyhedron.getFaces()) {
            drawPolygon(polygon);
        }
    }

    public void setColour(Color colour) {
        graphics.setColor(colour);
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

    private Polygon convertPolygon2ToPolygon(Polygon2 polygon2) {
        Polygon polygon = new Polygon();
        for (Point2 vertex : polygon2.getVertices()) {
            Point p = convertCoordinatesToPixels(vertex);
            polygon.addPoint(p.x,p.y);
        }
        return polygon;
    }
}
