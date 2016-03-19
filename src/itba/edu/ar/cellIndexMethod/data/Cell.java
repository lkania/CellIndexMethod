package itba.edu.ar.cellIndexMethod.data;
import java.util.LinkedList;
import java.util.List;

import itba.edu.ar.cellIndexMethod.data.particle.Particle;

public class Cell {

	private List<Particle> particles;
	
	public Cell(){
		particles = new LinkedList<Particle>();
	}

	public void add(Particle particle) {
		particles.add(particle);
	}

	public List<Particle> getParticles() {
		return particles;
	}
	
}
