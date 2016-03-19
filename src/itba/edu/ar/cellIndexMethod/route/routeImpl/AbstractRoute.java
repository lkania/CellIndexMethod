package itba.edu.ar.cellIndexMethod.route.routeImpl;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import itba.edu.ar.cellIndexMethod.data.particle.Particle;
import itba.edu.ar.cellIndexMethod.data.particle.ParticleObserver;
import itba.edu.ar.cellIndexMethod.route.Route;

public abstract class AbstractRoute implements Route,ParticleObserver{

	protected Map<Particle, Set<Particle>> allNeightbours;
	
	protected void maybeAddEntry(Particle particle){
		if(!allNeightbours.containsKey(particle))
			allNeightbours.put(particle, new HashSet<Particle>());
	}

	@Override
	public void setMap(Map<Particle, Set<Particle>> allNeightbours) {
		this.allNeightbours = allNeightbours;
	}

	
}
