package itba.edu.ar.input.file;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class DynamicFileGenerator {

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

				file.add(getRandom(length) + " " + getRandom(length) + " " + vx + " " + vy);
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

	private static double getRandomAngle() {
		return Math.random() * Math.PI * 2;
	}

	private static double getRandom(double length) {
		return Math.random() * length;
	}

}
