package GRAPHICSENGINE.point;

import java.awt.Point;

import GRAPHICSENGINE.PIXELENGINE;

public class PointConverter {
 public static Point convertPoint(PIXELPOINT PIXEL3D) {
	 int x2d= (int)(PIXELENGINE.WIDTH / 2 + PIXEL3D.y);
	 int y2d = (int)(PIXELENGINE.HEIGHT / 2 - PIXEL3D.z);
	 
	 Point point2d = new Point(x2d, y2d);
	 return point2d;
 }
}
