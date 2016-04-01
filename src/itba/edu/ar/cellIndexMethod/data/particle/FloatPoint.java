package itba.edu.ar.cellIndexMethod.data.particle;

public class FloatPoint {

	private double x;
	private double y;

	public FloatPoint(double x, double y) {
		super();
		
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	@Override
	public String toString() {
		return "("+getX()+","+getY()+")";
	}

	public FloatPoint multiply(double velocityAbs) {
		return new FloatPoint(x*velocityAbs,y*velocityAbs);
	}

	public FloatPoint plus(FloatPoint deltaFloatPoint) {
		return new FloatPoint(x+deltaFloatPoint.getX(),y+deltaFloatPoint.getY());
	}

	public FloatPoint mod(double length) {
		return new FloatPoint(x%length,y%length);
	}

	public FloatPoint plus(double length) {
		return this.plus(new FloatPoint(length,length));
	}

	public double abs() {
		return Math.hypot(x, y);
	}
	

}
