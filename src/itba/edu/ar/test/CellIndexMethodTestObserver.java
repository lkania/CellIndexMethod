package itba.edu.ar.test;

import java.util.List;

import itba.edu.ar.cellIndexMethod.CellIndexMethodObserver;
import itba.edu.ar.cellIndexMethod.data.particle.Particle;

public interface CellIndexMethodTestObserver extends CellIndexMethodObserver {

	
	void cellQuantityStepFinished();

	void state(Integer cellQuantity, List<Particle> particles);
	
	

}
