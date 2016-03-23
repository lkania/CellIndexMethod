package itba.edu.ar.cellIndexMethod;

import java.util.LinkedList;
import java.util.List;

import itba.edu.ar.cellIndexMethod.data.Cell;
import itba.edu.ar.cellIndexMethod.data.particle.Particle;
import itba.edu.ar.cellIndexMethod.route.Route;

public class CellIndexMethod {

	private IndexMatrix matrix;
	private Route route;
	private float interactionRadio;
	private float radio;
	private List<CellIndexMethodObserver> subscribers = new LinkedList<CellIndexMethodObserver>();

	public CellIndexMethod(IndexMatrix matrix, Route route, float interactionRadio,float radio) {
		this.matrix = matrix;
		this.route = route;
		this.interactionRadio = interactionRadio;
		this.radio=radio;
		if (!satisfyConstraint())
			throw new IllegalStateException();

	}

	private boolean satisfyConstraint() {
		return (matrix.getLength() / matrix.getCellQuantity()-2*radio) > interactionRadio;
	}

	public void execute() {
		notifyTimeStepStarted();

		for (int x = 0; x < matrix.getCellQuantity(); x++) {
			for (int y = 0; y < matrix.getCellQuantity(); y++) {
				Cell cell = matrix.getCell(x, y);
				for (Particle particle : cell.getParticles()) {
					route.fillNeightbours(x, y, matrix, particle, interactionRadio);
				}

			}
		}

		notifyTimeStepEnded();
	}

	private void notifyTimeStepStarted() {
		for (CellIndexMethodObserver subscriber : subscribers)
			subscriber.stepStarted();
	}

	private void notifyTimeStepEnded() {
		for (CellIndexMethodObserver subscriber : subscribers)
			subscriber.stepEnded();
	}

	public void subscribe(CellIndexMethodObserver subscriber) {
		subscribers.add(subscriber);
	}

	public void unsubscribe(CellIndexMethodObserver subscriber) {
		subscribers.remove(subscriber);
	}

	public void reset() {
		matrix.reset();
	}

}
