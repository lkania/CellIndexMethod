package itba.edu.ar.input.file;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class DynamicFileGenerator {

	public static void generate(List<String> dynamicPaths, String path, int times, Float length, int particleQuantity) {
		List<String> file = new LinkedList<String>();
		times++;

		for (int i = 0; i < times; i++) {
			file.add("" + i);
			for (int pq = 0; pq < particleQuantity; pq++) {
				file.add(getRandom(length) + " " + getRandom(length));
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

	private static float getRandom(Float length) {
		return (float) (Math.random() * length);
	}

}
