package examples.homework;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import examples.homework.csv.CSVFile;
import examples.homework.csv.CSVFileManager;
import examples.homework.parentheses.ParenthesesValidator;

/**
 * You can run this application for a couple of quick examples.
 * Execute unit test for more thorough examination.
 * @author Sweet
 *
 */
public class Application {

	private final static Logger log = Logger.getLogger( Application.class.toString() );
	
	public static void main(String[] args) {
				
		parenthesesValidationExample();
		csvProcessingExample();

	}
	
	private static void parenthesesValidationExample() {
		
		final String CORRECT_EXPRESSION = "(defun negate (X)\r\n"
				+ "  \"Negate the value of X.\"  ; This is a documentation string.\r\n"
				+ "  (- X))";
		
		boolean valid = ParenthesesValidator.closedAndNested( CORRECT_EXPRESSION );
		log.log( Level.INFO, "Parenthesis validation returned " + valid + " for the follwing expression." );
		log.log( Level.INFO, CORRECT_EXPRESSION );
		
	}
	
	private static void csvProcessingExample() {
		
		String EXISTING_FILE_CORRECT_FORMAT = "C:/test/correctFormat.csv";//this and other test files are in the repository
		CSVFile goodFile = new CSVFile( EXISTING_FILE_CORRECT_FORMAT );
		CSVFileManager fileManager = new CSVFileManager();
		fileManager.stage( Arrays.asList( goodFile ) );
		fileManager.upload();//get and validate file data
		fileManager.process();//sort etc
		
		/**
		 * Uncomment the line below to save processed files to disk.
		 */
		
		//fileManager.export();//uncomment to save processed files to disk
		
	}

}
