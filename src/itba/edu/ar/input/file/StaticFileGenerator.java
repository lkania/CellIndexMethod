package itba.edu.ar.input.file;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class StaticFileGenerator {



	public static void generate(List<String> staticPaths, Float length, int particleQuantity, float radio, String path) {
		List<String> file = new LinkedList<String>();
		file.add(particleQuantity+"");
		file.add(length.toString());

		for (int i = 0; i < particleQuantity; i++) {
			file.add(radio + " " + 1);
		}

		try {
			String finalPath = path+"Static"+particleQuantity;
			staticPaths.add(finalPath);
			Files.write(Paths.get(finalPath), file, Charset.forName("UTF-8"));
		} catch (IOException e) {
			throw new IllegalAccessError();
		}

	}

}
