package itba.edu.ar.input.file;

import java.util.List;

public class CellIndexMethodFileGenerator {

	private double length;
	private int particleQuantity;
	private double radio;
	private String path;
	private int times;
	private double velocityAbs;

	public CellIndexMethodFileGenerator(double length, int particleQuantity, double radio, String path, int times,double velocityAbs) {
		super();
		this.length = length;
		this.particleQuantity = particleQuantity;
		this.radio = radio;
		this.path = path;
		this.times = times;
		this.velocityAbs=velocityAbs;
	}

	public void generate(List<String> staticPaths,List<String> dynamicPaths) {
		StaticFileGenerator.generate(staticPaths,length, particleQuantity, radio, path);
		DynamicFileGenerator.generate(dynamicPaths,path, times, length, particleQuantity,velocityAbs);
	}
	
	

}
