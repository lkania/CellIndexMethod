package itba.edu.ar.cellIndexMethod.data.particle;

public class FloatPoint {

	private float x;
	private float y;

	public FloatPoint(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	@Override
	public String toString() {
		return "("+getX()+","+getY()+")";
	}

}
