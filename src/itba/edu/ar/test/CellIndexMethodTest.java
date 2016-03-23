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
	private String stressFilePath;
	private List<String> staticFilePaths;
	private List<String> dynamicFilePaths;
	private boolean periodicBoundaries;
	private int timeStep;
	private float interactionRadio;
	private int timesPerSimulation;
	private List<CellIndexMethodTestObserver> subscribers = new LinkedList<CellIndexMethodTestObserver>();

	
	public CellIndexMethodTest(List<Integer> particleQuantities, List<Integer> cellQuantities, String stressFilePath, List<String> staticFilePaths,
			List<String> dynamicFilePaths, int timeStep, float interactionRadio, int timesPerSimulation, boolean periodicBoundaries) {
		super();
		this.particleQuantities = particleQuantities;
		this.cellQuantities = cellQuantities;
		this.stressFilePath = stressFilePath;
		this.staticFilePaths = staticFilePaths;
		this.dynamicFilePaths = dynamicFilePaths;
		this.timeStep = timeStep;
		this.interactionRadio = interactionRadio;
		this.timesPerSimulation = timesPerSimulation;
		this.periodicBoundaries=periodicBoundaries;
	}
	
	public void start() throws InstantiationException, IllegalAccessException, IOException {

		FileOutputStressTest st = new FileOutputStressTest(stressFilePath);

		for (Integer particleQuantity : particleQuantities) {
			
			
			System.out.println("Particle quantity: "+particleQuantity);
			
			String staticPath = getStatic();
			String dynamicPath = getDynamic();
			
			for (Integer cellQuantity : cellQuantities) {
				System.out.print("\tCell quantity "+cellQuantity);
				
				st.reset(cellQuantity, particleQuantity);
				IndexMatrix indexMatrix = IndexMatrixBuilder.getIndexMatrix(staticPath, dynamicPath, cellQuantity,
						timeStep);
				
				notifyState(cellQuantity,indexMatrix.getParticles());
				
				CellIndexMethod cellIndexMethod = new CellIndexMethod(indexMatrix, getRoute(cellQuantity,periodicBoundaries,timeStep), interactionRadio);

				subscribeSubscribers(cellIndexMethod);
				cellIndexMethod.subscribe(st);
				
				for(int i=0;i<timesPerSimulation;i++)
					cellIndexMethod.execute();
				
				System.out.println("\tSimulation Time: "+st.getSimulationTime());
				st.endSimulation();
				
				notifyCellQuantityStepFinished();

			}
		}

		st.writeToFile();

	}
	
	public void subscribe(CellIndexMethodTestObserver subscriber){
		subscribers.add(subscriber);
	}

	private void notifyCellQuantityStepFinished() {
		for(CellIndexMethodTestObserver subscriber : subscribers)
			subscriber.cellQuantityStepFinished();
	}

	private void subscribeSubscribers(CellIndexMethod cellIndexMethod) {
		for(CellIndexMethodTestObserver subscriber : subscribers)
			cellIndexMethod.subscribe(subscriber);
	}

	private void notifyState(Integer cellQuantity, List<Particle> particles) {
		for(CellIndexMethodTestObserver subscriber : subscribers)
			subscriber.state(cellQuantity,particles);
	}

	private static Route getRoute(int cellQuantity, boolean periodicBoundaries, int timeStep) {
		return (cellQuantity == 1) ? new BruteForceRoute(timeStep)
				: new OptimizedRoute(cellQuantity, periodicBoundaries, timeStep);

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
