package itba.edu.ar.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import itba.edu.ar.input.file.CellIndexMethodFileGenerator;
import itba.edu.ar.output.FileOutputNeightbours;
import itba.edu.ar.output.FileOutputStressTest;

public class Test {

	private String path;
	private List<String> staticPaths = new LinkedList<String>();
	private List<String> dynamicPaths = new LinkedList<String>();
	private Float length;
	private float radio;
	private int timeStep;
	private float interactionRadio;
	private int timesPerSimulation;

	private List<Integer> particleQuantities;
	private List<Integer> cellQuantities;

	public Test(Float length, float radio, int timeStep, float interactionRadio, int timesPerSimulation,
			int fromParticleQuantity, int stepParticleQuantity, int toParticleQuantity, String path) {
		super();
		this.length = length;
		this.path = path;
		this.radio = radio;
		this.timeStep = timeStep;
		this.interactionRadio = interactionRadio;
		this.timesPerSimulation = timesPerSimulation;
		particleQuantities = getIntegerListPlusStep(fromParticleQuantity, toParticleQuantity, stepParticleQuantity);
		cellQuantities = getIntegerListPlusStep(getMaxCellQuantity(), getMaxCellQuantity(), 1);
	}

	private int getMaxCellQuantity() {
		return (int) Math.ceil(length / (interactionRadio + 2 * radio)) - 1;
	}

	public void executeTest() throws IOException, InstantiationException, IllegalAccessException {

		for (Integer particleQuantity : particleQuantities) {
			CellIndexMethodFileGenerator cmfg = new CellIndexMethodFileGenerator(length, particleQuantity, radio, path,
					timeStep);
			cmfg.generate(staticPaths, dynamicPaths);
		}

		CellIndexMethodTest st = new CellIndexMethodTest(particleQuantities, cellQuantities, staticPaths, dynamicPaths,
				timeStep, interactionRadio, timesPerSimulation, radio);

		st.subscribe(new FileOutputStressTest(path + "stressTest"));
		st.subscribe(new FileOutputNeightbours(path));
		st.start();
	}

	private static List<Integer> getIntegerListPlusStep(int from, int to, int step) {
		List<Integer> ans = new LinkedList<Integer>();
		for (int i = from; i <= to; i = i + step) {
			ans.add(i);
		}
		return ans;
	}

	private static List<Integer> getIntegerListMultiplyStep(int from, int to, int step) {
		List<Integer> ans = new LinkedList<Integer>();
		for (int i = from; i <= to; i = i * step) {
			ans.add(i);
		}
		return ans;
	}

}
