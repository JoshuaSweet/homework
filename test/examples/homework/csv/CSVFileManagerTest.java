package examples.homework.csv;

import static org.junit.Assert.*;
import org.junit.Test;

import examples.homework.csv.CSVFile;
import examples.homework.csv.CSVFileManager;
import examples.homework.csv.Enrollee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Tests CSVFileManager
 * @author Sweet
 *
 */
public class CSVFileManagerTest {

	private final String EXISTING_FILE_CORRECT_FORMAT = "C:/test/correctFormat.csv";
	private final String EXISTING_FILE_BAD_FORMAT = "C:/test/commaInCompanyName.csv";
	
	private List<CSVFile> getCorrectProcessedFiles1() {
		
		List<CSVFile> files = new ArrayList<CSVFile>(); 
		
		List<Enrollee> list1 = new ArrayList<Enrollee>();		
		list1.add( new Enrollee( "9f0368c1-7e1c-4193-a6c0-d9b2a087c16a", "Elly Dethloff", 		2, "B Co.") );
		list1.add( new Enrollee( "f1aed37d-0d81-44c1-8506-817a032b865a", "Gertrude Jangles", 	1, "B Co.") );
		list1.add( new Enrollee( "c46ea599-98fa-40ea-be4a-8e9eb53e5e31", "Fairchild Phillips", 	4, "B Co.") );
		CSVFile file1 = new CSVFile( "" );
		file1.setEnrollees( list1 );
		files.add( file1 );
		
		List<Enrollee> list2 = new ArrayList<Enrollee>();		
		list2.add( new Enrollee( "b3e4bfe1-8715-4551-983a-d095a0f86f46", "Bob Dillan", 		3, "Company A") );		
		list2.add( new Enrollee( "b9b0ba68-3222-434a-8a8e-3728a288d5c9", "Daren Hopefield", 0, "Company A") );
		list2.add( new Enrollee( "619c0cdf-9904-4da0-9698-cd8d33e3ebcc", "Cramer Jones", 	5, "Company A") );	
		CSVFile file2 = new CSVFile( "" );
		file2.setEnrollees( list2 );
		files.add( file2 );
		
		List<Enrollee> list3 = new ArrayList<Enrollee>();		
		list3.add( new Enrollee( "b41b0cf5-d066-47a8-950a-1051e0bd51cf", "Hector Highlander", 	3, "Company C LLC") );
		list3.add( new Enrollee( "c340c557-027a-499a-8845-dcb8a49ca564", "Ingrid Highlander", 	3, "Company C LLC") );
		list3.add( new Enrollee( "c5ed7f88-8d79-4d4d-ab74-f4a0ce1b698b", "Jerry Smokefield", 	3, "Company C LLC") );
		CSVFile file3 = new CSVFile( "" );
		file3.setEnrollees( list3 );
		files.add( file3 );
		
		return files;
		
	}
	
	@Test
	public void test_upload_with_valid_files() {
		
		CSVFile file = new CSVFile( this.EXISTING_FILE_CORRECT_FORMAT );
		CSVFileManager fileManager = new CSVFileManager();
		fileManager.stage( Arrays.asList( file ) );		
		fileManager.upload();		
		CSVFile expected = fileManager.getUploaded().get( 0 );
		assertEquals( expected, file );
		
	}
	
	@Test
	public void test_process_with_valid_files() {
		
		CSVFile file = new CSVFile( this.EXISTING_FILE_CORRECT_FORMAT );
		CSVFileManager fileManager = new CSVFileManager();
		fileManager.stage( Arrays.asList( file ) );
		fileManager.upload();		
		fileManager.process();
		List<CSVFile> expected = this.getCorrectProcessedFiles1();
		List<CSVFile> actual = fileManager.getProcessed();
		
		int expectedSize = expected.size();
		int actualSize = actual.size();
		
		assertEquals( expectedSize, actualSize );
		
		for ( int i = 0; i < actualSize; i++ ) {
			
			List<Enrollee> actualEnrollees = actual.get( i ).getEnrollees();
			List<Enrollee> expectedEnrollees = expected.get( i ).getEnrollees();
			
			int actualEnrolleesSize = actualEnrollees.size();
			int exprectedEnrolleesSize = expectedEnrollees.size();
			
			assertEquals( exprectedEnrolleesSize, actualEnrolleesSize );
			
			for ( int j = 0; j < actualEnrolleesSize; j++ ) {
				
				String actualEnrollee = actualEnrollees.get( j ).toString();
				String expectedEnrollee = expectedEnrollees.get( j ).toString();
				
				assertEquals( expectedEnrollee, actualEnrollee );
				
			}
			
		}
		
	}
	
	@Test
	public void test_upload_with_invalid_files() {
		
		CSVFile file = new CSVFile( this.EXISTING_FILE_BAD_FORMAT );
		CSVFileManager fileManager = new CSVFileManager();
		fileManager.stage( Arrays.asList( file ) );		
		fileManager.upload();		
		assertEquals( 1, fileManager.getFailed().size() );
		
	}
	
	@Test
	public void test_process_with_invalid_files() {
		
		CSVFile file = new CSVFile( this.EXISTING_FILE_BAD_FORMAT );
		CSVFileManager fileManager = new CSVFileManager();
		fileManager.stage( Arrays.asList( file ) );
		fileManager.upload();
		
		assertEquals( 1, fileManager.getFailed().size() );
		
		fileManager.process();
		
		assertEquals( 0, fileManager.getProcessed().size() );

	}
	
	@Test
	public void test_process_with_mixed_valid_invalid_files() {
		
		CSVFile goodFile = new CSVFile( this.EXISTING_FILE_CORRECT_FORMAT );
		CSVFile badFile = new CSVFile( this.EXISTING_FILE_BAD_FORMAT );
		CSVFileManager fileManager = new CSVFileManager();
		fileManager.stage( Arrays.asList( goodFile, badFile ) );
		fileManager.upload();
		
		assertEquals( 1, fileManager.getFailed().size() );
		assertEquals( 1, fileManager.getUploaded().size() );
		
		fileManager.process();
		
		assertEquals( 3, fileManager.getProcessed().size() );
		
	}
	
	/*TEST WILL WRITE TO FILE SYSTEM*/
	/* 
	@Test
	public void test_export_with_mixed_valid_invalid_files() {
		
		CSVFile goodFile = new CSVFile( this.EXISTING_FILE_CORRECT_FORMAT );
		CSVFile badFile = new CSVFile( this.EXISTING_FILE_BAD_FORMAT );
		CSVFileManager fileManager = new CSVFileManager();
		fileManager.stage( Arrays.asList( goodFile, badFile ) );
		fileManager.upload();
		
		assertEquals( 1, fileManager.getFailed().size() );
		assertEquals( 1, fileManager.getUploaded().size() );
		
		fileManager.process();
		
		assertEquals( 3, fileManager.getProcessed().size() );
		
		fileManager.export();
		
		assertEquals(  3, fileManager.getSaved().size() );
		
	}
	*/

}
