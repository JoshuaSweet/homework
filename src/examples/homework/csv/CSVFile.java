package examples.homework.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Handles CSV file processing.
 * @author Sweet
 *
 */
public class CSVFile implements FileInterface {

	private static final Logger log = Logger.getLogger( CSVFile.class.toString() );
	private String path;
	private String fileString;
	private String[] fileArray;
	private List<Enrollee> enrollees = new ArrayList<Enrollee>();
	private static final String ERROR_FILE_LENGTH = "File does not appear to contain data or is missing header. File must be composed of header row and at least one data row.";
	private static final String ERROR_HEADER_FORMAT = "Bad header. Expected \"User Id,First and Last Name,Version,Insurance Company\"";
	private static final String ERROR_FILE_FORMAT = "File format invalid.";
	public static final String REQUIRED_HEADER = "User Id,First and Last Name,Version,Insurance Company";
	private static final String ERROR_NULL_FILE_OBJECTS = "Null fileString and fileArray references detected.";
	private static final String ERROR_NULL_FILE_PATH = "Null path detected.";
	
	public CSVFile( String path ) {
		
		this.path = path;
		
	}
	
	private CSVFile() {/*need to use constructor with args*/}
	
	@Override
	public void upload()
		throws IOException {
		
		/**
		 * I couldn't recall exactly how to read files.  I don't do this on a daily basis.
		 * I had to do some refresher to get this to work properly.  
		 */
		
		if ( this.validatePath() ) {
			
			Path pathForFile = Paths.get( this.path );
			Stream<String> lines;
			lines = Files.lines( pathForFile );
			this.fileString = lines.collect( Collectors.joining( "\n" ) );
			lines.close();
			this.fileArray = this.fileString.split( "\n" );
			
			if ( this.validate() ) {
				
				this.process();
				
			} else {
				
				throw new IOException( ERROR_FILE_FORMAT );
				
			}
			
		} else {
			
			throw new IOException( ERROR_FILE_FORMAT );
			
		} 
		
	}
	
	@Override
	public boolean validate() {

		if ( this.uploaded() ) {
			
			boolean valid = this.validCharacters() && this.validLength() && this.validHeader();
			return valid;
			
		} else {
			
			throw new NullPointerException( ERROR_NULL_FILE_OBJECTS );
			
		}
		
	}

	@Override
	public boolean validatePath() {

		boolean valid = false;
		String extension = "";
		
		if ( this.path == null ) {
			
			throw new NullPointerException( ERROR_NULL_FILE_PATH );
			
		}		
		else if ( this.path.length() >= 5 ) {
			
			extension = this.path.substring( this.path.length() - 4 );
			valid = extension.equalsIgnoreCase( ".csv" );
			
			if ( !valid ) {
				
				log.log( Level.SEVERE, "Expected file extension \".csv\", recieved \"" + extension + "\"." );
				
			}
			
		} else {
			
			log.log( Level.SEVERE, "Unexpected file path specified." );
			
		}
		
		return valid;
		
	}
	
	@Override
	public void process() {

		if ( this.uploaded() ) {
		
			this.setEnrollees(this.toEnrollees( this.fileArray ));
			//sort by insurance company
			
		} else {
			
			throw new NullPointerException( ERROR_NULL_FILE_OBJECTS );
			
		}		
		
	}
	
	@Override
	public boolean uploaded() {
		
		boolean uploaded = false;
		uploaded = this.fileString != null && this.fileArray != null;
		return uploaded;
		
	}
	
	@Override
	public void saveAs() throws IOException {
		
		/**
		 * Had to reference.  I don't do much file processing these days.
		 */
		
		this.fileString = this.generateFileStringFromCurrentEnrollees();
		FileWriter writer = new FileWriter( this.path );
		writer.write( this.fileString );
		writer.close();
		
	}
	
	/**
	 * Sort enrollees list by insurance company ascending.
	 * Results are stored in enrollees list.
	 */
	public void sortByInsuranceCompany() {
		
		List<Enrollee> result = enrollees.stream()
				.sorted( Comparator.comparing( Enrollee::getInsuranceCompany ))
				.collect( Collectors.toList() );
		
		this.setEnrollees( result );
		
	}
	
	/**
	 * Sort enrollees list by last name and then first name ascending.
	 * Results are stored in enrollees list.
	 */
	public void sortByLastNameFirstNameAscending() {
		
		List<Enrollee> lastNameResult = enrollees.stream()
				.sorted( Comparator.comparing( Enrollee::getLastName ))
				.collect( Collectors.toList() );
		
		List<Enrollee> firstNameResult = lastNameResult.stream()
				.sorted( Comparator.comparing( Enrollee::getLastName ))
				.collect( Collectors.toList() );
		
		this.setEnrollees( firstNameResult );
		
	}
	
	public void removeDuplicatesByUserId() {
		
		Map<String,Enrollee> versionIndex = new HashMap<String,Enrollee>();
		
		for ( Enrollee enrollee : this.enrollees ) {
			
			String userId =	enrollee.getUserId();			
			Enrollee highestVersion = versionIndex.get( userId );
			
			if ( highestVersion == null || enrollee.getVersion() > highestVersion.getVersion() ) {
				
				versionIndex.put( userId, enrollee );
				
			}
			
		}
		
		this.enrollees = new ArrayList<Enrollee>( versionIndex.values() );
		
	}
	
	/**
	 * Determines if file contains only valid characters.
	 * Currently checks for double quotes which can indicate embedded commas were used which would disrupt parsing.
	 * Double quotes are not allowed.
	 * This would be more maintainable keeping black listed characters in a list and stepping through list for validation.
	 * @return true if file contains only valid characters.
	 */
	private boolean validCharacters() {
		
		boolean valid = !this.fileString.contains( "\"" );
		return valid;
		
	}
	
	/**
	 * Determines if file length is appropriate.
	 * @return true if length indicates header with data
	 */
	private boolean validLength() {
		
		boolean valid = this.fileArray.length > 1;
		
		if ( !valid ) {
			
			log.log( Level.SEVERE, ERROR_FILE_LENGTH );
			
		}
		
		return valid;
		
	}
	
	/**
	 * Parses a single line from csv file to an enrollee.
	 * @param fileLine
	 * @return Enrollee
	 */
	private Enrollee toEnrollee( String fileLine ) {
		
		Enrollee enrollee = new Enrollee( fileLine );
		return enrollee;
		
	}
	
	/**
	 * Convert array of String file lines to List<Enrollee>.
	 * @param fileData
	 * @return List<Enrollee>
	 */
	private List<Enrollee> toEnrollees( String[] file ) {

		List<Enrollee> enrolleesList = new ArrayList<Enrollee>();
				
		for ( int i = 1; i < file.length; i++  ) {//skip header
			
			String fileLine = file[i];
			Enrollee enrollee = this.toEnrollee( fileLine );					
			enrolleesList.add( enrollee );
			
		}
		
		return enrolleesList;
		
	}
	
	/**
	 * Determines if header is in valid format.
	 * @param header
	 * @return true if header format is valid
	 */
	private boolean validHeader() {
		
		boolean valid = false;		
		String header = this.fileArray[0];
		
		if ( header.equalsIgnoreCase( REQUIRED_HEADER ) ) {
			
			valid = true;
			
		} else {
			
			log.log( Level.SEVERE, ERROR_HEADER_FORMAT );
			
		}
		
		return valid;
		
	}
	
	/**
	 * Generate a new csv file string for file writing.
	 * This updates CSVFile.fileString with the new string.
	 * This string can be used to directly to write to csv. 
	 * @return new file string
	 */
	public String generateFileStringFromCurrentEnrollees() {
		
		String newFileString = REQUIRED_HEADER + "\n";
		
		for ( Enrollee enrollee : enrollees ) {
			
			newFileString = newFileString + enrollee.getFileLine() + "\n";
			
		}
		
		this.fileString = newFileString;		
		return newFileString;
		
	}
	
	public String getFileString() {
		return fileString;
	}
	public String[] getFileArray() {
		return fileArray;
	}
	public String getPath() {
		return path;
	}

	public List<Enrollee> getEnrollees() {
		return enrollees;
	}
	
	public void setEnrollees(List<Enrollee> enrollees) {
		this.enrollees = enrollees;
	}

}
