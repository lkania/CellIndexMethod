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

public class FileOutputNeightbours implements CellIndexMethodObserver {

	private Path path;
	private List<Particle> particles;

	public FileOutputNeightbours(String path, List<Particle> particles) {
		this.path = Paths.get(path);
		this.particles = particles;
	}

	@Override
	public void stepEnded(Map<Particle, Set<Particle>> allNeightbours) {

		List<String> file = new LinkedList<String>();
		StringBuilder sb = new StringBuilder();
		for (Particle particle : particles) {
			sb.append(particle.getId());
			if (allNeightbours.containsKey(particle)) {
				for (Particle neightbour : allNeightbours.get(particle))
					sb.append("," + neightbour.getId());
			}
			file.add(sb.toString());
			sb = new StringBuilder();
		}

		try {
			Files.write(path, file, Charset.forName("UTF-8"));
		} catch (IOException e) {
			throw new IllegalAccessError();
		}

	}

	@Override
	public void stepStarted() {
		// TODO Auto-generated method stub

	}

}
