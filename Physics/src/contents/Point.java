package contents;

public class Point {
	public float x, y;
	public float prevX, prevY;
	public boolean locked;
	
	public Point(float x, float y, boolean locked) {
		this.x = x;
		this.y = y;
		prevX = x;
		prevY = y;
		this.locked = locked;
	}

}
