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

import itba.edu.ar.cellIndexMethod.CellIndexMethodObserver;
import itba.edu.ar.cellIndexMethod.data.particle.Particle;
import itba.edu.ar.test.CellIndexMethodTestObserver;

public class FileOutputNeightbours implements CellIndexMethodTestObserver {

	private String pathFolder;
	private List<Particle> particles;
	private List<String> fileContent = new LinkedList<String>();

	public FileOutputNeightbours(String path) {
		this.pathFolder = path;
	}

	@Override
	public void stepEnded() {

		if(!fileContent.isEmpty())
			return;

		StringBuilder sb = new StringBuilder();
		for (Particle particle : particles) {
			sb.append(particle.getId());
			Set<Particle> neightbours = particle.getNeightbours();
			if (!neightbours.isEmpty()) {
				for (Particle neightbour : neightbours)
					sb.append("," + neightbour.getId());
			}
			fileContent.add(sb.toString());
			sb = new StringBuilder();
		}
	}

	@Override
	public void stepStarted() {
		// TODO Auto-generated method stub

	}

	@Override
	public void cellQuantityStepFinished() {
		try {
			Files.write(Paths.get(pathFolder+"neightbours_"+particles.size()), fileContent, Charset.forName("UTF-8"));
		} catch (IOException e) {
			throw new IllegalAccessError();
		}
		fileContent.clear();
	}

	@Override
	public void state(Integer cellQuantity, List<Particle> particles) {
		this.particles = particles;
	}

	@Override
	public void endOfSimulation() {
		// TODO Auto-generated method stub
		
	}

}
