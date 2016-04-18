package itba.edu.ar.parser.interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import itba.edu.ar.cellIndexMethod.data.particle.Particle;

public class StaticInterpreter extends FileInterpreter implements Interpreter{

	
	private List<Particle> particles = new ArrayList<Particle>();
	private float length;
	private int totalParticles;
	
	@Override
	public void parse(int i, String line) {
		switch(i){
		case 0:
			numberOfParticles(line);
			break;
		case 1:
			lenght(line);
			break;
		default:
			particleProperties(i,line);
		}
	}

	private void particleProperties(int i, String line) {
		Scanner scanner = getScanner(line);
		particles.add(new Particle(i-1,scanner.nextDouble(),scanner.nextDouble(),scanner.nextDouble()));
	}

	private void lenght(String line) {
		Scanner scanner = getScanner(line);
		length = scanner.nextFloat();
	}

	private void numberOfParticles(String line) {
		Scanner scanner = getScanner(line);
		totalParticles = scanner.nextInt();
	}
	
	

	public List<Particle> getParticles() {
		return particles;
	}

	public float getLength() {
		return length;
	}

	public int getTotalParticles() {
		return totalParticles;
	}

	
	
}
