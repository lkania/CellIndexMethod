package itba.edu.ar.test;

import java.io.IOException;
import java.util.List;

import itba.edu.ar.cellIndexMethod.CellIndexMethod;
import itba.edu.ar.cellIndexMethod.IndexMatrix;
import itba.edu.ar.cellIndexMethod.IndexMatrixBuilder;
import itba.edu.ar.cellIndexMethod.route.Route;
import itba.edu.ar.cellIndexMethod.route.routeImpl.BruteForceRoute;
import itba.edu.ar.cellIndexMethod.route.routeImpl.OptimizedRoute;
import itba.edu.ar.output.FileOutputNeightbours;
import itba.edu.ar.output.FileOutputStressTest;

public class CellIndexMethodStressTest {

	private List<Integer> particleQuantities;
	private List<Integer> cellQuantities;
	private String neightboursFilePath;
	private String stressFilePath;
	private List<String> staticFilePaths;
	private List<String> dynamicFilePaths;
	private int timeStep;
	private float interactionRadio;
	private int timesPerSimulation;

	public CellIndexMethodStressTest(List<Integer> particleQuantities, List<Integer> cellQuantities,
			String neightboursFilePath, String stressFilePath, List<String> staticFilePaths,
			List<String> dynamicFilePaths, int timeStep, float interactionRadio, int timesPerSimulation) {
		super();
		this.particleQuantities = particleQuantities;
		this.cellQuantities = cellQuantities;
		this.neightboursFilePath = neightboursFilePath;
		this.stressFilePath = stressFilePath;
		this.staticFilePaths = staticFilePaths;
		this.dynamicFilePaths = dynamicFilePaths;
		this.timeStep = timeStep;
		this.interactionRadio = interactionRadio;
		this.timesPerSimulation = timesPerSimulation;
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
				CellIndexMethod cellIndexMethod = new CellIndexMethod(indexMatrix, getRoute(cellQuantity,timeStep), interactionRadio);

				cellIndexMethod.subscribe(st);
				
				for(int i=0;i<timesPerSimulation;i++)
					cellIndexMethod.execute();
				
				System.out.println("\tSimulation Time: "+st.getSimulationTime());
				st.endSimulation();

			}
		}

		st.writeToFile();

	}

	private static Route getRoute(int cellQuantity, int timeStep) {
		return (cellQuantity == 1) ? new BruteForceRoute(timeStep)
				: new OptimizedRoute(cellQuantity, true, timeStep);

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
