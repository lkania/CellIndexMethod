package itba.edu.ar.parser;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Pattern;

import itba.edu.ar.parser.interpreter.Interpreter;

public class InfoParser {

	private static Pattern newLinePattern = Pattern.compile("\\n");
	
	public static void parse(String pathString,Interpreter interpreter) throws IOException{
		
		Path path = Paths.get(pathString);
		Scanner scanner = new Scanner(path);
		
		scanner.useDelimiter(newLinePattern);
		
		int i = 0;
		while(scanner.hasNext()){
			interpreter.parse(i,scanner.next());
			i++;
		}
		
		scanner.close();
		
	}
	
}
