package itba.edu.ar.input.file;

import java.io.IOException;
import java.nio.channels.AcceptPendingException;
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
	private static final int MAX_FAILS = 100;
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

	public String generate(List<Data> staticFileDatas, String path) {
		return generate(staticFileDatas, path, 0);

	}

	public String generate(List<Data> staticFileDatas, String path, int times) {
		List<String> file = new LinkedList<String>();
		times++;

		for (int i = 0; i < times; i++) {
			file.add("" + i);

			for (Data data : staticFileDatas) {
				int fails=0;
				for (int pq = 0; pq < data.getParticleQuantity() && fails<MAX_FAILS; pq++) {

					FloatPoint position = null;
					DynamicFileGeneratorParticle preParticle = null;

					boolean isNewParticle=true;
					fails=0;
					while (preParticle==null||!isNewParticle && fails<MAX_FAILS) {
						position = data.getPosition();

						preParticle = new DynamicFileGeneratorParticle(position,data.getRadio());
						isNewParticle=!positions.contains(preParticle);
						if(!isNewParticle)
							fails++;
					}
					if(fails==MAX_FAILS)
						break;
					
					positions.add(preParticle);
					
					FloatPoint velocity = data.getVelocity(position);

					file.add(position.getX() + SEPARATOR + position.getY() + SEPARATOR + velocity.getX() + SEPARATOR + velocity.getY());
				}
				data.setParticleQuantity(positions.size());
			}
			
			
		}
		int particleQuantity = getTotalParticleQuantity(staticFileDatas);
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
