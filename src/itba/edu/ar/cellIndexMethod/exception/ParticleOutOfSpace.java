package itba.edu.ar.cellIndexMethod.exception;

import itba.edu.ar.cellIndexMethod.data.particle.Particle;

public class ParticleOutOfSpace extends RuntimeException {

	Particle particle;
	
	public ParticleOutOfSpace(Particle particle) {
		this.particle=particle;
	}
	
	@Override
	public String getMessage() {
		return particle.toString();
	}
	
}
