package itba.edu.ar.cellIndexMethod;

import java.awt.Point;
import java.util.List;

import itba.edu.ar.cellIndexMethod.data.Cell;
import itba.edu.ar.cellIndexMethod.data.Matrix;
import itba.edu.ar.cellIndexMethod.data.particle.FloatPoint;
import itba.edu.ar.cellIndexMethod.data.particle.Particle;

public class IndexMatrix {

	private Matrix<Cell> matrix;
	private int cellQuantity;
	private double timeStep;
	private double length;
	private List<Particle> particles;

	public IndexMatrix(int cellQuantity, double timeStep, double length)
			throws InstantiationException, IllegalAccessException {
		matrix = new Matrix<Cell>(cellQuantity, cellQuantity, Cell.class);
		this.timeStep = timeStep;
		this.length = length;
		this.cellQuantity = cellQuantity;
	}

	public void addParticles(List<Particle> particles) {
		for (Particle particle : particles) {

			Point position = round(particle.getPosition());
			Cell cell = getCell(position);
			cell.add(particle);
		}
		this.particles = particles;
	}

	public int getCellQuantity() {
		return cellQuantity;
	}

	private Point round(FloatPoint position) {
		int x = (int) (position.getX() / (length / cellQuantity));
		int y = (int) (position.getY() / (length / cellQuantity));
		return new Point(x, y);
	}

	public Cell getCell(Point position) {
		return matrix.get(position);
	}

	public Cell getCell(int x, int y) {
		return matrix.get(x, y);
	}

	public double getLength() {
		return length;
	}

	public List<Particle> getParticles() {
		return particles;
	}

	public boolean insideBoundaries(int x, int y) {
		return matrix.insideBoundaries(x, y);
	}

	public void reset() {
		for (Particle particle : particles)
			particle.resetNeightbours();
	}

	public void clear() throws InstantiationException, IllegalAccessException {
		matrix.clear();
	}

}
