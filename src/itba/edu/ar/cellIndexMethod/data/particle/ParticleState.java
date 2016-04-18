package itba.edu.ar.cellIndexMethod.data.particle;

public class ParticleState {

	private FloatPoint position;
	private double angle;
	private double velocityAbs;

	public ParticleState(float x, float y, float angle, double velocityAbs) {
		super();
		position = new FloatPoint(x, y);
		this.velocityAbs=velocityAbs;
		this.angle=angle;
	}

	public ParticleState() {
		this(0,0,0,0);
	}

	public FloatPoint getPosition() {
		return position;
	}

	public double getVelocityAbs() {
		return velocityAbs;
	}
	
	public double getAngle(){
		return angle;
	}

	public void setPosition(double x, double y) {
		position = new FloatPoint(x,y);
	}
	
	public void setVelocityAbs(double velocityAbs){
		this.velocityAbs=velocityAbs;
	}
	
	public void setAngle(double angle){
		this.angle=angle;
	}

	public void setPosition(FloatPoint newPosition) {
		this.position=newPosition;
	}

	public FloatPoint getVelocity() {
		return new FloatPoint(Math.cos(angle)*velocityAbs,Math.sin(angle)*velocityAbs);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(angle);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		temp = Double.doubleToLongBits(velocityAbs);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParticleState other = (ParticleState) obj;
		if (Double.doubleToLongBits(angle) != Double.doubleToLongBits(other.angle))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (Double.doubleToLongBits(velocityAbs) != Double.doubleToLongBits(other.velocityAbs))
			return false;
		return true;
	}

	

}
