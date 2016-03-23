package itba.edu.ar.input.console;

import java.io.IOException;

import itba.edu.ar.test.Test;

public class ConsoleParser {

	public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException {

		float length = Float.valueOf(args[0]);
		float radio = Float.valueOf(args[1]);
		int timeStep = Integer.valueOf(args[2]);
		float interactionRadio = Float.valueOf(args[3]);
		int timesPerSimulation = Integer.valueOf(args[4]);
		int fromParticleQuantity = Integer.valueOf(args[5]);
		int toParticleQuantity = Integer.valueOf(args[6]);
		String path = args[7];
		boolean periodicBoundaries=Boolean.parseBoolean(args[8]);
		
		System.out.println(path);

		Test test = new Test(length, radio, timeStep, interactionRadio, timesPerSimulation,fromParticleQuantity,toParticleQuantity,periodicBoundaries,path);
		test.executeTest();
	}

}
