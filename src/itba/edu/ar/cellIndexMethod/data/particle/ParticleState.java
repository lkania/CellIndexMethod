package itba.edu.ar.cellIndexMethod.data.particle;

public class ParticleState {

	private FloatPoint position;
	private FloatPoint velocity;

	public ParticleState(float x, float y, float velocityX, float velocityY) {
		super();
		position = new FloatPoint(x, y);
		velocity = new FloatPoint(velocityX, velocityY);
	}

	public FloatPoint getPosition() {
		return position;
	}

	public FloatPoint getVelocity() {
		return velocity;
	}

	public void setPosition(float x, float y) {
		position = new FloatPoint(x,y);
	}
	
	public void setVelocity(float velocityX,float velocityY){
		velocity = new FloatPoint(velocityX, velocityY);
	}

	

}
