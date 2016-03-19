package itba.edu.ar.cellIndexMethod;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import itba.edu.ar.cellIndexMethod.data.particle.Particle;

public interface CellIndexMethodObserver {

	public void stepEnded(Map<Particle,Set<Particle>> allNeightbours);
	public void stepStarted();
	
}
