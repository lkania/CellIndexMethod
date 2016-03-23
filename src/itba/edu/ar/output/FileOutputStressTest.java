package itba.edu.ar.output;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import itba.edu.ar.cellIndexMethod.data.particle.Particle;
import itba.edu.ar.test.CellIndexMethodTestObserver;

public class FileOutputStressTest implements CellIndexMethodTestObserver {

	private String tag = "";
	private Path path;
	private int cellQuantity;
	private int particleQuantity;
	private List<SimulationTime> simulationTimes = new LinkedList<SimulationTime>();
	private long startSimulationTime;
	private float averageSimulationTime = 0;
	private int simulationsQuantity;
	private static float toMilliseconds = (float) Math.pow(10, 6);

	public FileOutputStressTest(String path, int cellQuantity, int particleQuantity, String tag) {
		super();
		this.path = Paths.get(path);
		this.cellQuantity = cellQuantity;
		this.particleQuantity = particleQuantity;
		this.tag = tag;
	}

	public FileOutputStressTest(String stressFilePath) {
		this(stressFilePath, 0, 0, "");
	}

	@Override
	public void stepEnded(Map<Particle, Set<Particle>> allNeightbours) {
		averageSimulationTime += System.nanoTime() - startSimulationTime;
		simulationsQuantity++;
	}

	@Override
	public void stepStarted() {
		startSimulationTime = System.nanoTime();
	}

	private float getSimulationTime() {
		return averageSimulationTime / (simulationsQuantity * toMilliseconds);
	}

	@Override
	public void cellQuantityStepFinished() {
		float simulationTime = getSimulationTime();
		simulationTimes.add(new SimulationTime(simulationTime, tag, cellQuantity, particleQuantity));
		System.out.println("\tSimulation Time: " + simulationTime);
	}

	@Override
	public void state(Integer cellQuantity, List<Particle> particles) {
		System.out.print("Particle quantity "+particles.size()+"\tCell quantity " + cellQuantity);
		this.cellQuantity = cellQuantity;
		this.particleQuantity = particles.size();
		averageSimulationTime = 0;
		simulationsQuantity = 0;
	}

	@Override
	public void endOfSimulation() {
		List<String> file = new LinkedList<String>();

		for (SimulationTime st : simulationTimes) {
			file.add(st.toString());
		}

		try {
			Files.write(path, file, Charset.forName("UTF-8"));
		} catch (IOException e) {
			throw new IllegalAccessError();
		}
	}

	private class SimulationTime {
		private float averageSimulationTime;
		private String tag;
		private int cellQuantity;
		private int particleQuantity;

		public SimulationTime(float averageSimulationTime, String tag, int cellQuantity, int particleQuantity) {
			super();
			this.averageSimulationTime = averageSimulationTime;
			this.tag = tag;
			this.cellQuantity = cellQuantity;
			this.particleQuantity = particleQuantity;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			return sb.append(cellQuantity).append(" ").append(particleQuantity).append(" ")
					.append(averageSimulationTime).append(" ").append(tag).toString();
		}

	}

}
