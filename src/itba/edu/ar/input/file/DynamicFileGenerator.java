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
import itba.edu.ar.input.file.data.StaticFileData;

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

	public String generate(double length, List<StaticFileData> staticFileDatas, String path) {
		return generate(length, staticFileDatas, path, 0);

	}

	public String generate(double length, List<StaticFileData> staticFileDatas, String path, int times) {
		List<String> file = new LinkedList<String>();
		times++;

		int particleQuantity = getTotalParticleQuantity(staticFileDatas);
		for (int i = 0; i < times; i++) {
			file.add("" + i);

			for (StaticFileData data : staticFileDatas) {
				for (int pq = 0; pq < data.getParticleQuantity(); pq++) {

					double angle = getRandomAngle();
					double vx = data.getVelocityAbs().getX() * Math.cos(angle);
					double vy = data.getVelocityAbs().getY() * Math.sin(angle);

					double positionX = 0;
					double positionY = 0;
					DynamicFileGeneratorParticle preParticle = null;

					while (preParticle==null||positions.contains(preParticle)) {
						positionX = getRandom(length - 2 * data.getRadio()) + data.getRadio();
						positionY = getRandom(length - 2 * data.getRadio()) + data.getRadio();
						preParticle = new DynamicFileGeneratorParticle(new FloatPoint(positionX, positionY),data.getRadio());
					}
					positions.add(preParticle);

					file.add(positionX + SEPARATOR + positionY + SEPARATOR + vx + SEPARATOR + vy);
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
