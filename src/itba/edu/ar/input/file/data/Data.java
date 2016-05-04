package itba.edu.ar.input.file.data;

import itba.edu.ar.cellIndexMethod.data.particle.FloatPoint;

public class Data {

	private int particleQuantity;
	private double mass;
	private double radio;
	private FloatPoint velocity;
	private FloatPoint position; 
	
	public Data(int particleQuantity, double mass, double radio) {
		this(particleQuantity,mass,radio,0,0);
	}
	
	public Data(int particleQuantity, double mass, double radio, double velocityX, double velocityY) {
		this.particleQuantity = particleQuantity;
		this.mass = mass;
		this.radio = radio;
		this.velocity=new FloatPoint(velocityX, velocityY);
	}

	public Data(int particleQuantity, double mass, double radio, FloatPoint velocity,FloatPoint position) {
		this.particleQuantity = particleQuantity;
		this.mass = mass;
		this.radio = radio;
		this.velocity=velocity;
		this.position=position;
	}
	
	public int getParticleQuantity() {
		return particleQuantity;
	}

	public double getMass() {
		return mass;
	}

	public double getRadio() {
		return radio;
	}

	public FloatPoint getVelocity(double positionX, double positionY) {
		return velocity;
	}
	
	public boolean hasPredeterminePosition(){
		return position!=null;
	}
	
	public FloatPoint getPosition(){
		return position;
	}

	public FloatPoint getVelocity(FloatPoint position) {
		return getVelocity(position.getX(), position.getY());
	}
	

}
