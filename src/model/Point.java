package model;

public class Point {

	public double x;
    public double y;
    public int type;
 
    // 获取距离
    public double getDistance(Point p) {
 
    	double dise=Math.sqrt(Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2));
        return dise;
    }

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Point(double x, double y) {
		super();
		this.x = x;
		this.y = y;
		this.type=0;
	}

	public Point() {
		super();
	}
    
}
