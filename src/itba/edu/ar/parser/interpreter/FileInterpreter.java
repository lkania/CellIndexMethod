package itba.edu.ar.parser.interpreter;

import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class FileInterpreter {

	private static Pattern spacesPattern = Pattern.compile("\\s+");
	
	protected Scanner getScanner(String line){
		Scanner scanner = new Scanner(line);
		scanner.useLocale(Locale.US);
		scanner.useDelimiter(spacesPattern);
		return scanner;
	}
	
}
