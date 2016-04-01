package itba.edu.ar.cellIndexMethod.route.routeImpl;

import java.awt.Point;

import itba.edu.ar.cellIndexMethod.IndexMatrix;
import itba.edu.ar.cellIndexMethod.data.Cell;
import itba.edu.ar.cellIndexMethod.data.particle.Particle;
import itba.edu.ar.cellIndexMethod.route.Route;

public class BruteForceRoute implements Route{

	private float timeStep;
	private float length;
	private int cellQuantity;
		
	public BruteForceRoute(float timeStep, float length, int cellQuantity) {
		super();
		this.timeStep = timeStep;
		this.length = length;
		this.cellQuantity = cellQuantity;
	}

	@Override
	public void fillNeightbours(int x, int y, IndexMatrix matrix, Particle particle, double interactionRadio) {
			Point position = getPosition(x, y);
			Cell cell = matrix.getCell(position);
			particle.fillNeightbours(cell.getParticles(), interactionRadio, false, length, cellQuantity);
	}

	private Point getPosition(int x, int y) {
		int newX = x;
		int newY = y;

		return new Point(newX, newY);
	}



}
