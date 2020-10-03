package GRAPHICSENGINE.SHAPES;

import GRAPHICSENGINE.point.PIXELPOINT;
import GRAPHICSENGINE.point.PointConverter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class PIXELPOLYGON {
 private PIXELPOINT[] points;
 public PIXELPOLYGON(PIXELPOINT...points) {
	 this.points = new PIXELPOINT[points.length];
	 for(int i = 0; i < points.length; i++) {
		 PIXELPOINT p = points[i];
		 this.points[i] = new PIXELPOINT(p.x, p.y, p.z);
	 }
 }
 public void render(Graphics g) {
	 Polygon poly = new Polygon();
	 for(int i = 0; i < points.length; i++) {
		 Point p = PointConverter.convertPoint(points[i]);
		 poly.addPoint(p.x, p.y);
	 }
	 g.setColor(Color.WHITE);
	 g.fillPolygon(poly);
 }
}
