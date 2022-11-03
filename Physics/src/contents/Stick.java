package contents;

public class Stick {
	public Point pointA, pointB;
	public float length;
	
	public Stick(Point A, Point B) {
		pointA = A;
		pointB = B;
		length = (float)Math.sqrt(Math.pow((pointA.x-pointB.x),2)+Math.pow(pointA.y-pointB.y, 2));
	}

}
