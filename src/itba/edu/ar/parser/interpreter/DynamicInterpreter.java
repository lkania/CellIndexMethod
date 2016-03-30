package itba.edu.ar.parser.interpreter;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

import itba.edu.ar.cellIndexMethod.data.particle.Particle;

public class DynamicInterpreter extends FileInterpreter implements Interpreter {

	private List<Particle> particles;
	private float timeStep;

	public DynamicInterpreter(List<Particle> particles) {
		super();
		this.particles = particles;
	}

	@Override
	public void parse(int i, String line) {
		if (isTimeStep(i))
			timeStep(i,line);
		else
			particlesProperties(i,line);
	}

	private void timeStep(int i, String line) {
		Scanner scanner = getScanner(line);
		timeStep = scanner.nextFloat();
	}

	private void particlesProperties(int i, String line) {
		Scanner scanner = getScanner(line);
		Particle particle = particles.get(particleIndex(i));
		particle.setPosition(scientificNotationToFloat(scanner.next()),scientificNotationToFloat(scanner.next()));
		
		if(scanner.hasNext())
			particle.setVelocityAbs(scanner.nextFloat(),scanner.nextFloat());
		
	}
	
	private double scientificNotationToFloat(String number){
		return Double.valueOf(number);
	}

	private int particleIndex(int i) {
		return (i%(particles.size()+1))-1;
	}

	private boolean isTimeStep(int i) {
		return (i % (particles.size() + 1)) == 0;
	}

}
