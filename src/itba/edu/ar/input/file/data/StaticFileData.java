package itba.edu.ar.input.file.data;

import itba.edu.ar.cellIndexMethod.data.particle.FloatPoint;

public class StaticFileData {

	public int particleQuantity;
	public double mass;
	public double radio;
	public FloatPoint velocityAbs;


	public StaticFileData(int particleQuantity, double mass, double radio) {
		this(particleQuantity,mass,radio,0,0);
	}

	
	public StaticFileData(int particleQuantity, double mass, double radio, double velocityAbsX, double velocityAbsY) {
		this.particleQuantity = particleQuantity;
		this.mass = mass;
		this.radio = radio;
		this.velocityAbs=new FloatPoint(velocityAbsX, velocityAbsY);
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
	
	public FloatPoint getVelocityAbs(){
		return velocityAbs;
	}
	

}
