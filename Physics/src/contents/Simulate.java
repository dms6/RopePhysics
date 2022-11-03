package contents;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Simulate {
	public static ArrayList<Point> points = new ArrayList<>();
	public static ArrayList<Stick> sticks = new ArrayList<>();
	public static int radius = 10;
	private final float deltaTime = 0.016f; 
	private final float gravity = 10f;
	private final int numIterations = 10;

	public void update() {
		for(Point p : points) {
			if(!p.locked) {
				float beforeX = p.x;
				float beforeY = p.y;
				p.x += p.x - p.prevX;
				p.y += p.y - p.prevY;
				p.y += gravity * deltaTime * deltaTime;
				p.prevX = beforeX;
				p.prevY = beforeY;
			}
		}
		for(int i = 0;i<numIterations; i++) {
			for(Stick s : sticks) {
				float stickCenterX = (s.pointA.x+s.pointB.x)/2;
				float stickCenterY = (s.pointA.y+s.pointB.y)/2;
				//NORMALIZE
				float xLen = s.pointA.x - s.pointB.x;
				float yLen = s.pointA.y - s.pointB.y;
				float magnitude = dist(xLen, yLen);
				float stickDirX = xLen/magnitude;
				float stickDirY = yLen/magnitude;
				if(!s.pointA.locked) {
					s.pointA.x = stickCenterX+stickDirX*s.length/2;
					s.pointA.y = stickCenterY+stickDirY*s.length/2;
				}
				if(!s.pointB.locked) {
					s.pointB.x = stickCenterX-stickDirX*s.length/2;
					s.pointB.y = stickCenterY-stickDirY*s.length/2;
				}
			}
		}
	}
	public void render(Graphics g) {

		g.setColor(Color.WHITE);
		try {
			for(Stick s : sticks) {
				g.drawLine((int)s.pointA.x, (int)s.pointA.y, (int)s.pointB.x, (int)s.pointB.y);
			}
		}
		catch(Exception E) {};
		
		try {
			for(Point p : points) {
				g.setColor(p.locked?Color.RED:Color.WHITE);
				g.fillOval((int)p.x-radius/2, (int)p.y-radius/2, radius, radius);
			}
		}
		catch(Exception E) { };
		
	}
	public static Point findNearest(int X, int Y) {
		Point closest = null;
		int minDist = Integer.MAX_VALUE;
		for(Point p : points) {
			int dist = (int) ((X-p.x)*(X-p.x) + (Y-p.y)*(Y-p.y));
			if(dist < minDist) {
				minDist = dist;
				closest = p;
			}
		}
		return closest;
	}
	public static float dist(float x, float y) {
		return (float)Math.sqrt(x*x+y*y);
	}

}
