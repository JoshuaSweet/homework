package examples.homework.csv;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import examples.homework.csv.CSVFile;
import examples.homework.csv.Enrollee;

/**
 * Tests CSVFile
 * @author Sweet
 *
 */
public class CSVFileTest {

	private final String EXISTING_FILE_CORRECT_FORMAT = "C:/test/correctFormat.csv";
	private final String EXISTING_FILE_WRONG_HEADER = "C:/test/wrongHeader.csv";
	private final String EXISTING_FILE_NO_HEADER = "C:/test/noHeader.csv";
	private final String EXISTING_FILE_BAD_FORMAT = "C:/test/commaInCompanyName.csv";
	private final String EXISTING_FILE_BAD_EXTENSION = "C:/test/badExtension.txt";
	private final String DNE_FILE_PATH = "C:/DNE_FILE_PATH.csv";
	private final String PATH_TOO_SHORT = "n.n";
	
	private List<Enrollee> correctFormattedSortedByInsuranceCompany;
	private List<Enrollee> correctSortLastNameFirstNameAscending;
	private List<Enrollee> correctSortInsuranceCompanyLastNameFirstNameAscending;
	
	private List<Enrollee> getCorrectSortLastNameFirstNameAscending() {
		
		correctSortLastNameFirstNameAscending = new ArrayList<Enrollee>();
		
		correctSortLastNameFirstNameAscending.add( new Enrollee( "9f0368c1-7e1c-4193-a6c0-d9b2a087c16a", "Elaina Dethloff", 	1, "B Co.") );
		correctSortLastNameFirstNameAscending.add( new Enrollee( "9f0368c1-7e1c-4193-a6c0-d9b2a087c16a", "Elly Dethloff", 		2, "B Co.") );		
		correctSortLastNameFirstNameAscending.add( new Enrollee( "b3e4bfe1-8715-4551-983a-d095a0f86f46", "Bob Dillan", 			1, "Company A") );
		correctSortLastNameFirstNameAscending.add( new Enrollee( "b3e4bfe1-8715-4551-983a-d095a0f86f46", "Bob Dillan", 			2, "Company A") );
		correctSortLastNameFirstNameAscending.add( new Enrollee( "b3e4bfe1-8715-4551-983a-d095a0f86f46", "Bob Dillan", 			3, "Company A") );		
		correctSortLastNameFirstNameAscending.add( new Enrollee( "b41b0cf5-d066-47a8-950a-1051e0bd51cf", "Hector Highlander", 	3, "Company C LLC") );
		correctSortLastNameFirstNameAscending.add( new Enrollee( "c340c557-027a-499a-8845-dcb8a49ca564", "Ingrid Highlander", 	3, "Company C LLC") );		
		correctSortLastNameFirstNameAscending.add( new Enrollee( "b9b0ba68-3222-434a-8a8e-3728a288d5c9", "Daren Hopefield", 	0, "Company A") );		
		correctSortLastNameFirstNameAscending.add( new Enrollee( "f1aed37d-0d81-44c1-8506-817a032b865a", "Gertrude Jangles", 	1, "B Co.") );		
		correctSortLastNameFirstNameAscending.add( new Enrollee( "619c0cdf-9904-4da0-9698-cd8d33e3ebcc", "Cramer Jones", 		2, "Company A") );
		correctSortLastNameFirstNameAscending.add( new Enrollee( "619c0cdf-9904-4da0-9698-cd8d33e3ebcc", "Cramer Jones", 		5, "Company A") );
		correctSortLastNameFirstNameAscending.add( new Enrollee( "c46ea599-98fa-40ea-be4a-8e9eb53e5e31", "Fairchild Phillips", 	4, "B Co.") );
		correctSortLastNameFirstNameAscending.add( new Enrollee( "c5ed7f88-8d79-4d4d-ab74-f4a0ce1b698b", "Jerry Smokefield", 	3, "Company C LLC") );
		
		return correctSortLastNameFirstNameAscending;
		
	}
	
	private List<Enrollee> getCorrectFormattedSortedByInsuranceCompany() {
		
		correctFormattedSortedByInsuranceCompany = new ArrayList<Enrollee>();
		
		correctFormattedSortedByInsuranceCompany.add( new Enrollee( "9f0368c1-7e1c-4193-a6c0-d9b2a087c16a", "Elaina Dethloff", 		1, "B Co.") );
		correctFormattedSortedByInsuranceCompany.add( new Enrollee( "9f0368c1-7e1c-4193-a6c0-d9b2a087c16a", "Elly Dethloff", 		2, "B Co.") );
		correctFormattedSortedByInsuranceCompany.add( new Enrollee( "c46ea599-98fa-40ea-be4a-8e9eb53e5e31", "Fairchild Phillips", 	4, "B Co.") );
		correctFormattedSortedByInsuranceCompany.add( new Enrollee( "f1aed37d-0d81-44c1-8506-817a032b865a", "Gertrude Jangles", 	1, "B Co.") );
		
		correctFormattedSortedByInsuranceCompany.add( new Enrollee( "b3e4bfe1-8715-4551-983a-d095a0f86f46", "Bob Dillan", 		1, "Company A") );
		correctFormattedSortedByInsuranceCompany.add( new Enrollee( "b3e4bfe1-8715-4551-983a-d095a0f86f46", "Bob Dillan", 		2, "Company A") );
		correctFormattedSortedByInsuranceCompany.add( new Enrollee( "b3e4bfe1-8715-4551-983a-d095a0f86f46", "Bob Dillan", 		3, "Company A") );		
		correctFormattedSortedByInsuranceCompany.add( new Enrollee( "619c0cdf-9904-4da0-9698-cd8d33e3ebcc", "Cramer Jones", 	2, "Company A") );
		correctFormattedSortedByInsuranceCompany.add( new Enrollee( "619c0cdf-9904-4da0-9698-cd8d33e3ebcc", "Cramer Jones", 	5, "Company A") );		
		correctFormattedSortedByInsuranceCompany.add( new Enrollee( "b9b0ba68-3222-434a-8a8e-3728a288d5c9", "Daren Hopefield", 	0, "Company A") );
		
		correctFormattedSortedByInsuranceCompany.add( new Enrollee( "b41b0cf5-d066-47a8-950a-1051e0bd51cf", "Hector Highlander", 	3, "Company C LLC") );
		correctFormattedSortedByInsuranceCompany.add( new Enrollee( "c340c557-027a-499a-8845-dcb8a49ca564", "Ingrid Highlander", 	3, "Company C LLC") );
		correctFormattedSortedByInsuranceCompany.add( new Enrollee( "c5ed7f88-8d79-4d4d-ab74-f4a0ce1b698b", "Jerry Smokefield", 	3, "Company C LLC") );
		
		return correctFormattedSortedByInsuranceCompany;
		
	}
	
	private List<Enrollee> getCorrectSortInsuranceCompanyLastNameFirstNameAscending() {
		
		correctSortInsuranceCompanyLastNameFirstNameAscending = new ArrayList<Enrollee>();
		
		correctSortInsuranceCompanyLastNameFirstNameAscending.add( new Enrollee( "9f0368c1-7e1c-4193-a6c0-d9b2a087c16a", "Elaina Dethloff", 	1, "B Co.") );
		correctSortInsuranceCompanyLastNameFirstNameAscending.add( new Enrollee( "9f0368c1-7e1c-4193-a6c0-d9b2a087c16a", "Elly Dethloff", 		2, "B Co.") );
		correctSortInsuranceCompanyLastNameFirstNameAscending.add( new Enrollee( "c46ea599-98fa-40ea-be4a-8e9eb53e5e31", "Fairchild Phillips", 	4, "B Co.") );
		correctSortInsuranceCompanyLastNameFirstNameAscending.add( new Enrollee( "f1aed37d-0d81-44c1-8506-817a032b865a", "Gertrude Jangles", 	1, "B Co.") );
		
		correctSortInsuranceCompanyLastNameFirstNameAscending.add( new Enrollee( "b3e4bfe1-8715-4551-983a-d095a0f86f46", "Bob Dillan", 		1, "Company A") );
		correctSortInsuranceCompanyLastNameFirstNameAscending.add( new Enrollee( "b3e4bfe1-8715-4551-983a-d095a0f86f46", "Bob Dillan", 		2, "Company A") );
		correctSortInsuranceCompanyLastNameFirstNameAscending.add( new Enrollee( "b3e4bfe1-8715-4551-983a-d095a0f86f46", "Bob Dillan", 		3, "Company A") );		
		correctSortInsuranceCompanyLastNameFirstNameAscending.add( new Enrollee( "b9b0ba68-3222-434a-8a8e-3728a288d5c9", "Daren Hopefield", 0, "Company A") );
		correctSortInsuranceCompanyLastNameFirstNameAscending.add( new Enrollee( "619c0cdf-9904-4da0-9698-cd8d33e3ebcc", "Cramer Jones", 	2, "Company A") );		
		correctSortInsuranceCompanyLastNameFirstNameAscending.add( new Enrollee( "619c0cdf-9904-4da0-9698-cd8d33e3ebcc", "Cramer Jones", 	5, "Company A") );
		
		correctSortInsuranceCompanyLastNameFirstNameAscending.add( new Enrollee( "b41b0cf5-d066-47a8-950a-1051e0bd51cf", "Hector Highlander", 	3, "Company C LLC") );
		correctSortInsuranceCompanyLastNameFirstNameAscending.add( new Enrollee( "c340c557-027a-499a-8845-dcb8a49ca564", "Ingrid Highlander", 	3, "Company C LLC") );
		correctSortInsuranceCompanyLastNameFirstNameAscending.add( new Enrollee( "c5ed7f88-8d79-4d4d-ab74-f4a0ce1b698b", "Jerry Smokefield", 	3, "Company C LLC") );
		
		return correctSortInsuranceCompanyLastNameFirstNameAscending;
		
	}
	
	@Test
	public void test_upload_with_dne_file() {
	
		CSVFile file = new CSVFile( this.DNE_FILE_PATH );
		
		try {
			
			file.upload();
			fail("Expected IOException not thrown.");
			
		} catch (IOException e) {
			
			/*pass: Expected IOException thrown.*/
			
		}
		
	}
	
	@Test
	public void test_upload_with_existing_file_bad_format() {
	
		CSVFile file = new CSVFile( this.EXISTING_FILE_BAD_FORMAT );
		
		try {
			
			file.upload();
			fail( "Expected IOException not thrown." );
			
		} catch ( IOException e ) {
			
			/*pass: Expected IOException thrown.*/
			
		}
		
	}
	
	@Test
	public void test_upload_with_existing_file_correct_format() {
	
		CSVFile file = new CSVFile( this.EXISTING_FILE_CORRECT_FORMAT );
		
		try {
			
			file.upload();
						
		} catch ( IOException e ) {
			
			fail( "Unexpected IOException thrown." );
			
		}
		
	}
	
	@Test
	public void test_upload_with_existing_file_bad_extension() {
	
		CSVFile file = new CSVFile( this.EXISTING_FILE_BAD_EXTENSION );
		
		try {
			
			file.upload();
			fail( "Expected IOException not thrown." );
			
		} catch ( IOException e ) {
			
			/*pass: Expected IOException thrown.*/
			
		}
		
	}
	
	@Test
	public void test_validExtension_with_valid_extension() {
	
		CSVFile file = new CSVFile( this.EXISTING_FILE_CORRECT_FORMAT  );
		boolean valid = file.validatePath();
		assertTrue( valid );
		
	}
	
	@Test
	public void test_validExtension_with_invalid_extension() {
	
		CSVFile file = new CSVFile( this.EXISTING_FILE_BAD_EXTENSION );
		boolean valid = file.validatePath();
		assertFalse( valid );
				
	}
	
	@Test
	public void test_validExtension_with_extension_too_short() {
	
		CSVFile file = new CSVFile( this.PATH_TOO_SHORT );
		boolean valid = file.validatePath();
		assertFalse( valid );
				
	}
	
	@Test
	public void test_validFormat_with_valid_format() {
	
		CSVFile file = new CSVFile( this.EXISTING_FILE_CORRECT_FORMAT );

		try {
			
			file.upload();
			
		} catch ( IOException e ) {
			
			fail( "Unexpected IOException thrown." );
			
		}	
		
	}
	
	@Test
	public void test_validFormat_with_wrong_header() {
	
		CSVFile file = new CSVFile( this.EXISTING_FILE_WRONG_HEADER );

		try {
			
			file.upload();
			fail( "Expected IOException not thrown." );
			
		} catch ( IOException e ) {
			
			/*pass: Expected IOException thrown.*/
			
		}
		
	}
	
	@Test
	public void test_validFormat_with_no_header() {
	
		CSVFile file = new CSVFile( this.EXISTING_FILE_NO_HEADER );

		try {
			
			file.upload();
			fail( "Expected IOException not thrown." );
			
		} catch ( IOException e ) {
			
			/*pass: Expected IOException thrown.*/
			
		}	
		
	}
	
	@Test
	public void test_validFormat_with_double_quotes() {
	
		CSVFile file = new CSVFile( this.EXISTING_FILE_BAD_FORMAT );
		
		try {
			
			file.upload();
			fail( "Expected IOException not thrown." );
			
		} catch ( IOException e ) {
			
			/*pass: Expected IOException thrown.*/
			
		}	
		
	}
	
	@Test
	public void test_sortByInsuranceCompany_with_valid_file() {
	
		CSVFile file = new CSVFile( this.EXISTING_FILE_CORRECT_FORMAT );

		try {
			
			file.upload();
			file.sortByInsuranceCompany();
			List<Enrollee> actual = file.getEnrollees();
			List<Enrollee> expected = this.getCorrectFormattedSortedByInsuranceCompany();
			
			for ( int i = 0; i < actual.size(); i++ ) {
				
				assertEquals( expected.get( i ).getInsuranceCompany(), actual.get( i ).getInsuranceCompany() );
				
			}
			
		} catch ( IOException e ) {
			
			fail( "Unexpected IOException thrown." );
			
		}	
		
	}
	
	@Test
	public void test_sortByLastNameFirstNameAscending_with_valid_file() {
	
		CSVFile file = new CSVFile( this.EXISTING_FILE_CORRECT_FORMAT );

		try {
			
			file.upload();
			file.sortByLastNameFirstNameAscending();
			List<Enrollee> actual = file.getEnrollees();
			List<Enrollee> expected = this.getCorrectSortLastNameFirstNameAscending();
			
			for ( int i = 0; i < actual.size(); i++ ) {
				
				assertEquals( expected.get( i ).getLastName(), actual.get( i ).getLastName() );
				assertEquals( expected.get( i ).getFirstName(), actual.get( i ).getFirstName() );
				
			}
			
		} catch ( IOException e ) {
			
			fail( "Unexpected IOException thrown." );
			
		}	
		
	}

}
