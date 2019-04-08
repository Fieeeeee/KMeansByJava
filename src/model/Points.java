package model;

public class Points {
	public double x;
	public double y;
	public double z;
	public double n;
 // 获取距离
    public double getDistance(Points p) {
 
        return Math.sqrt(Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2)+ Math.pow(z - p.z, 2)+Math.pow(n - p.n, 2));
 
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
	public double getZ() {
		return z;
	}
	public void setZ(double z) {
		this.z = z;
	}
	public double getN() {
		return n;
	}
	public void setN(double n) {
		this.n = n;
	}
	public Points(double x, double y, double z, double n) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.n = n;
	}
	public Points() {
		super();
	}
    
}
