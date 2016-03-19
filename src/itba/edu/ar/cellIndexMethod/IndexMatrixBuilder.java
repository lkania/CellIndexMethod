package itba.edu.ar.cellIndexMethod;

import java.io.IOException;

import itba.edu.ar.cellIndexMethod.data.particle.Particle;
import itba.edu.ar.parser.InfoParser;
import itba.edu.ar.parser.interpreter.DynamicInterpreter;
import itba.edu.ar.parser.interpreter.Interpreter;
import itba.edu.ar.parser.interpreter.StaticInterpreter;

public class IndexMatrixBuilder {

	public static IndexMatrix getIndexMatrix(String staticFilePath, String dynamicFilePath, int cellquantity,
			float timeStep) throws IOException, InstantiationException, IllegalAccessException {
		StaticInterpreter staticInterpreter = new StaticInterpreter();
		InfoParser.parse(staticFilePath, staticInterpreter);
		Interpreter dynamicInterpreter = new DynamicInterpreter(staticInterpreter.getParticles());
		InfoParser.parse(dynamicFilePath, dynamicInterpreter);
		IndexMatrix matrix = new IndexMatrix(cellquantity, timeStep, staticInterpreter.getLength());
		matrix.addParticles(staticInterpreter.getParticles());
		return matrix;
	}

}
