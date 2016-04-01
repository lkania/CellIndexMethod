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

	

}
