package itba.edu.ar.input.file;

import java.util.List;

public class CellIndexMethodFileGenerator {

	private Float length;
	private int particleQuantity;
	private float radio;
	private String path;
	private int times;

	public CellIndexMethodFileGenerator(Float length, int particleQuantity, float radio, String path, int times) {
		super();
		this.length = length;
		this.particleQuantity = particleQuantity;
		this.radio = radio;
		this.path = path;
		this.times = times;
	}

	public void generate(List<String> staticPaths,List<String> dynamicPaths) {
		StaticFileGenerator.generate(staticPaths,length, particleQuantity, radio, path);
		DynamicFileGenerator.generate(dynamicPaths,path, times, length, particleQuantity);
	}

}
