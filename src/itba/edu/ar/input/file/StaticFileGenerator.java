package itba.edu.ar.input.file;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import itba.edu.ar.input.file.data.Data;

public class StaticFileGenerator extends FileGenerator{

	private static final double COLOR = 1;
	private static final String SEPARATOR = " ";

	public static void generate(List<String> staticPaths, double length, int particleQuantity, double radio,
			String path) {
		generate(staticPaths, length, particleQuantity, radio, 0, path);
	}

	public static void generate(List<String> staticPaths, double length, int particleQuantity, double radio,
			double mass, String path) {

		List<Data> staticFileDatas = new LinkedList<>();
		staticFileDatas.add(new Data(particleQuantity, mass, radio));

		staticPaths.add(generate(length, staticFileDatas, path));

	}

	public static String generate(double length, List<Data> staticFileDatas, String path) {
		List<String> file = new LinkedList<String>();
		int particleQuantity = getTotalParticleQuantity(staticFileDatas);
		file.add(particleQuantity + "");
		file.add(length + "");

		StringBuilder sb = new StringBuilder();

		for (Data data : staticFileDatas) {
			for (int i = 0; i < data.getParticleQuantity(); i++) {
				sb.append(data.getRadio()).append(SEPARATOR).append(COLOR).append(SEPARATOR).append(data.getMass());
				file.add(sb.toString());
				sb = new StringBuilder();
			}
		}
		String finalPath = path + "Static" + particleQuantity;
		try {
			Files.write(Paths.get(finalPath), file, Charset.forName("UTF-8"));
		} catch (IOException e) {
			throw new IllegalAccessError();
		}

		return finalPath;
	}

	

}
