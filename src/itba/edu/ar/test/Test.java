package itba.edu.ar.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import itba.edu.ar.input.file.CellIndexMethodFileGenerator;

public class Test {

	private String path;
	private List<String> staticPaths = new LinkedList<String>();
	private List<String> dynamicPaths = new LinkedList<String>();
	private Float length;
	private float radio;
	private int timeStep;
	private float interactionRadio;
	private int timesPerSimulation;

	private static List<Integer> particleQuantities;
	private static List<Integer> cellQuantities;

	static {
		particleQuantities = getIntegerList(100, 200, 100);
		cellQuantities = getIntegerList(1, 19, 1);
	}

	public Test(Float length, float radio, int timeStep, float interactionRadio, int timesPerSimulation, String path) {
		super();
		this.length = length;
		this.path = path;
		this.radio = radio;
		this.timeStep = timeStep;
		this.interactionRadio = interactionRadio;
		this.timesPerSimulation = timesPerSimulation;
	}

	public void executeTest() throws IOException, InstantiationException, IllegalAccessException {

		for (Integer particleQuantity : particleQuantities) {
			CellIndexMethodFileGenerator cmfg = new CellIndexMethodFileGenerator(length, particleQuantity, radio, path,
					timeStep);
			cmfg.generate(staticPaths, dynamicPaths);

		}

		CellIndexMethodStressTest st = new CellIndexMethodStressTest(particleQuantities, cellQuantities,
				path+"neightbours", path+"stressTest", staticPaths, dynamicPaths, timeStep, interactionRadio,
				timesPerSimulation);

		st.start();
	}

	private static List<Integer> getIntegerList(int from, int to, int step) {
		List<Integer> ans = new LinkedList<Integer>();
		for (int i = from; i <= to; i = i + step) {
			ans.add(i);
		}
		return ans;
	}

}
