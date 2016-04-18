package itba.edu.ar.input.file;

import itba.edu.ar.cellIndexMethod.data.particle.FloatPoint;

public class DynamicFileGeneratorParticle {

	private FloatPoint position;
	private double radio;
	public DynamicFileGeneratorParticle(FloatPoint position, double radio) {
		super();
		this.position = position;
		this.radio = radio;
	}
	@Override
	public int hashCode() {
		return 1;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DynamicFileGeneratorParticle other = (DynamicFileGeneratorParticle) obj;
	
		return other.getPosition().distance(getPosition())<(getRadio()+other.getRadio());
	}
	
	
	public FloatPoint getPosition() {
		return position;
	}
	public double getRadio() {
		return radio;
	}
	
	
	
}
