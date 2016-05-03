package itba.edu.ar.input.file;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import itba.edu.ar.cellIndexMethod.data.particle.FloatPoint;
import itba.edu.ar.input.file.data.Data;

public class DynamicFileGenerator extends FileGenerator {

	private static final String SEPARATOR = " ";
	private Set<DynamicFileGeneratorParticle> positions = new HashSet<DynamicFileGeneratorParticle>();

	public static void generate(List<String> dynamicPaths, String path, int times, double length, int particleQuantity,
			double velocityAbs) {
		List<String> file = new LinkedList<String>();
		times++;

		for (int i = 0; i < times; i++) {
			file.add("" + i);
			for (int pq = 0; pq < particleQuantity; pq++) {
				double angle = getRandomAngle();
				double vx = velocityAbs * Math.cos(angle);
				double vy = velocityAbs * Math.sin(angle);

				file.add(getRandom(length) + SEPARATOR + getRandom(length) + SEPARATOR + vx + SEPARATOR + vy);
			}
		}

		try {
			String finalPath = path + "Dynamic" + particleQuantity;
			dynamicPaths.add(finalPath);
			Files.write(Paths.get(finalPath), file, Charset.forName("UTF-8"));
		} catch (IOException e) {
			throw new IllegalAccessError();
		}

	}

	public String generate(double length, List<Data> staticFileDatas, String path) {
		return generate(length, staticFileDatas, path, 0);

	}

	public String generate(double length, List<Data> staticFileDatas, String path, int times) {
		List<String> file = new LinkedList<String>();
		times++;

		int particleQuantity = getTotalParticleQuantity(staticFileDatas);
		for (int i = 0; i < times; i++) {
			file.add("" + i);

			for (Data data : staticFileDatas) {
				for (int pq = 0; pq < data.getParticleQuantity(); pq++) {

					double positionX = 0;
					double positionY = 0;
					DynamicFileGeneratorParticle preParticle = null;

					while (preParticle==null||positions.contains(preParticle)) {
						positionX = data.getPosition(Math.random());
						positionY = data.getPosition(Math.random());
						preParticle = new DynamicFileGeneratorParticle(new FloatPoint(positionX, positionY),data.getRadio());
					}
					positions.add(preParticle);
					
					FloatPoint velocity = data.getVelocity(positionX,positionY);

					file.add(positionX + SEPARATOR + positionY + SEPARATOR + velocity.getX() + SEPARATOR + velocity.getY());
				}
			}
		}

		String finalPath = path + "Dynamic" + particleQuantity;
		try {
			Files.write(Paths.get(finalPath), file, Charset.forName("UTF-8"));
		} catch (IOException e) {
			throw new IllegalAccessError();
		}
		return finalPath;

	}

	private static double getRandomAngle() {
		return Math.random() * Math.PI * 2;
	}

	private static double getRandom(double length) {
		return Math.random() * length;
	}
	

}
