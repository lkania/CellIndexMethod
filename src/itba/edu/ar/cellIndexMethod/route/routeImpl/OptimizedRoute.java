package itba.edu.ar.cellIndexMethod.route.routeImpl;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import itba.edu.ar.cellIndexMethod.IndexMatrix;
import itba.edu.ar.cellIndexMethod.data.Cell;
import itba.edu.ar.cellIndexMethod.data.particle.Particle;
import itba.edu.ar.cellIndexMethod.route.Route;

public class OptimizedRoute implements Route {

	private static List<Point> cellNeighbours;
	private int cellQuantity;
	private boolean periodicBoundaries;
	private double length;

	static {
		cellNeighbours = new LinkedList<Point>();
		cellNeighbours.add(new Point(0, 0));
		cellNeighbours.add(new Point(0, 1));
		cellNeighbours.add(new Point(1, 1));
		cellNeighbours.add(new Point(1, 0));
		cellNeighbours.add(new Point(1, -1));
	}

	public OptimizedRoute(int cellQuantity, boolean periodicBoundaries, double length) {
		super();
		this.cellQuantity = cellQuantity;
		this.periodicBoundaries = periodicBoundaries;
		this.length = length;
	}

	@Override
	public void fillNeightbours(int x, int y, IndexMatrix matrix, Particle particle, double interactionRadio) {
		for (Point increment : cellNeighbours) {
			Point position = getPosition(x, y, increment, matrix);

			if (position != null) {
				Cell cell = matrix.getCell(position);
				particle.fillNeightbours(cell.getParticles(), interactionRadio, periodicBoundaries, length,
						cellQuantity);
			}
		}
	}

	private Point getPosition(int x, int y, Point increment, IndexMatrix matrix) {
		int newX = x + (int) increment.getX();
		int newY = y + (int) increment.getY();

		if (periodicBoundaries) {
			newX = (newX + cellQuantity) % cellQuantity;
			newY = (newY + cellQuantity) % cellQuantity;
		}

		return (matrix.insideBoundaries(newX, newY)) ? new Point(newX, newY) : null;
	}

}
