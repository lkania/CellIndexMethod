package itba.edu.ar.cellIndexMethod.route;
import java.util.List;
import java.util.Map;
import java.util.Set;

import itba.edu.ar.cellIndexMethod.IndexMatrix;
import itba.edu.ar.cellIndexMethod.data.particle.Particle;

public interface Route {

	public void fillNeightbours(int x,int y,IndexMatrix matrix,Particle particle,float interactionRadio);

	
}

