package itba.edu.ar.cellIndexMethod.route.routeImpl;

import java.awt.Point;
import java.util.Map;
import java.util.Set;

import itba.edu.ar.cellIndexMethod.IndexMatrix;
import itba.edu.ar.cellIndexMethod.data.Cell;
import itba.edu.ar.cellIndexMethod.data.particle.Particle;

public class BruteForceRoute extends AbstractRoute{

	private float timeStep;

	public BruteForceRoute(float timeStep) {
		this.timeStep = timeStep;
	}

	@Override
	public void fillNeightbours(int x, int y, IndexMatrix matrix, Particle particle, float interactionRadio) {
			Point position = getPosition(x, y);
			Cell cell = matrix.getCell(position);
			particle.subscribe(this);
			particle.getNeightbours(cell.getParticles(), interactionRadio,timeStep);
			particle.unsubscribe(this);
	}

	private Point getPosition(int x, int y) {
		int newX = x;
		int newY = y;

		return new Point(newX, newY);
	}

	@Override
	public void neighbour(Particle particle1, Particle particle2) {
		maybeAddEntry(particle1);
		allNeightbours.get(particle1).add(particle2);
	}

	

}
