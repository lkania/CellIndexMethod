package itba.edu.ar.test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import itba.edu.ar.cellIndexMethod.CellIndexMethod;
import itba.edu.ar.cellIndexMethod.IndexMatrix;
import itba.edu.ar.cellIndexMethod.IndexMatrixBuilder;
import itba.edu.ar.cellIndexMethod.data.particle.Particle;
import itba.edu.ar.cellIndexMethod.route.Route;
import itba.edu.ar.cellIndexMethod.route.routeImpl.BruteForceRoute;
import itba.edu.ar.cellIndexMethod.route.routeImpl.OptimizedRoute;
import itba.edu.ar.output.FileOutputNeightbours;
import itba.edu.ar.output.FileOutputStressTest;

public class CellIndexMethodTest {

	private List<Integer> particleQuantities;
	private List<Integer> cellQuantities;
	private List<String> staticFilePaths;
	private List<String> dynamicFilePaths;
	private int timeStep;
	private float interactionRadio;
	private int timesPerSimulation;
	private List<CellIndexMethodTestObserver> subscribers = new LinkedList<CellIndexMethodTestObserver>();
	private float radio;

	public CellIndexMethodTest(List<Integer> particleQuantities, List<Integer> cellQuantities,
			List<String> staticFilePaths, List<String> dynamicFilePaths, int timeStep, float interactionRadio,
			int timesPerSimulation,float radio) {
		super();
		this.particleQuantities = particleQuantities;
		this.cellQuantities = cellQuantities;
		this.staticFilePaths = staticFilePaths;
		this.dynamicFilePaths = dynamicFilePaths;
		this.timeStep = timeStep;
		this.interactionRadio = interactionRadio;
		this.timesPerSimulation = timesPerSimulation;
		this.radio=radio;
	}

	public void start() throws InstantiationException, IllegalAccessException, IOException {

		for (Integer particleQuantity : particleQuantities) {

			String staticPath = getStatic();
			String dynamicPath = getDynamic();

			for (Integer cellQuantity : cellQuantities) {

				IndexMatrix indexMatrix = IndexMatrixBuilder.getIndexMatrix(staticPath, dynamicPath, cellQuantity,
						timeStep);
				notifyState(cellQuantity, indexMatrix.getParticles());

				CellIndexMethod cellIndexMethod = new CellIndexMethod(indexMatrix, getRoute(cellQuantity,indexMatrix.getLength()),
						interactionRadio,radio);

				subscribeSubscribers(cellIndexMethod);

				for (int i = 0; i < timesPerSimulation; i++) {
					cellIndexMethod.execute();
					cellIndexMethod.reset();
				}

				unsubscribeSubscribers(cellIndexMethod);

				notifyCellQuantityStepFinished();

			}

		}

		notifyEndofSimulation();

	}

	private void notifyEndofSimulation() {
		for (CellIndexMethodTestObserver subscriber : subscribers)
			subscriber.endOfSimulation();
	}

	private void unsubscribeSubscribers(CellIndexMethod cellIndexMethod) {
		for (CellIndexMethodTestObserver subscriber : subscribers)
			cellIndexMethod.unsubscribe(subscriber);
	}

	public void subscribe(CellIndexMethodTestObserver subscriber) {
		subscribers.add(subscriber);
	}

	private void notifyCellQuantityStepFinished() {
		for (CellIndexMethodTestObserver subscriber : subscribers)
			subscriber.cellQuantityStepFinished();
	}

	private void subscribeSubscribers(CellIndexMethod cellIndexMethod) {
		for (CellIndexMethodTestObserver subscriber : subscribers)
			cellIndexMethod.subscribe(subscriber);
	}

	private void notifyState(Integer cellQuantity, List<Particle> particles) {
		for (CellIndexMethodTestObserver subscriber : subscribers)
			subscriber.state(cellQuantity, particles);
	}

	private static Route getRoute(int cellQuantity, double length) {
		return (cellQuantity == 1) ? new BruteForceRoute(length) : new OptimizedRoute(cellQuantity, true,length);

	}

	private String getStatic() {
		String path = staticFilePaths.get(0);
		staticFilePaths.remove(0);
		return path;
	}

	private String getDynamic() {
		String path = dynamicFilePaths.get(0);
		dynamicFilePaths.remove(0);
		return path;
	}

}
