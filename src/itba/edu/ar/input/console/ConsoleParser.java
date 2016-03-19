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
		String path = args[5];
		
		System.out.println(path);

		Test test = new Test(length, radio, timeStep, interactionRadio, timesPerSimulation,path);
		test.executeTest();
	}

}
