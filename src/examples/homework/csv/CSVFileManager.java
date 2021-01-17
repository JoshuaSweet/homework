package examples.homework.csv;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.UUID;

/**
 * Manages bulk processing of CSV files.
 * @author Sweet
 *
 */
public class CSVFileManager implements FileManagerInterface {

	private static final Logger log = Logger.getLogger( CSVFileManager.class.toString() );
	private List<CSVFile> staged = new ArrayList<CSVFile>();
	private List<CSVFile> uploaded = new ArrayList<CSVFile>();
	private List<CSVFile> failed = new ArrayList<CSVFile>();
	private List<CSVFile> processed = new ArrayList<CSVFile>();
	private List<CSVFile> saved = new ArrayList<CSVFile>(); 
		
	@Override
	public void upload() {

		log.log( Level.INFO, "Uploading files." );
		
		for ( CSVFile file : staged ) {
			
			try {
				
				file.upload();
				
			} catch ( IOException e ) {
				
				log.log( Level.SEVERE, "File at path " + file.getPath() + " could not be uploaded." );
				failed.add( file );
				break;
				
			}
			
			uploaded.add( file );
			
		}
		
		//staged = new ArrayList<CSVFile>();//clear staging area
		
	}

	@Override
	public void process() {

		log.log( Level.INFO, "Processing files." ); 
		
		for ( CSVFile file : uploaded ) {
			
			List<CSVFile> newFiles = new ArrayList<CSVFile>();
			file.process();
			file.sortByInsuranceCompany();
			String previousCompany = "";
			CSVFile currentFile = null;
			
			for ( Enrollee enrollee : file.getEnrollees() ) {//step through and create new file for each insurance company group

				String currentCompany = enrollee.getInsuranceCompany();
				boolean newCompanyDetected = !currentCompany.equalsIgnoreCase( previousCompany );
				
				if ( newCompanyDetected ) {
					
					currentFile = new CSVFile( "C:/test/" + currentCompany + "_" + UUID.randomUUID() + ".csv" );
					newFiles.add( currentFile );
					previousCompany = currentCompany;
					
				}
				
				currentFile.getEnrollees().add( enrollee );
				
			}
			
			for ( CSVFile newFile : newFiles ) {
				
				log.log( Level.INFO, "Removing duplicates and sorting generated file " + newFile.getPath() + "." );
				
				newFile.removeDuplicatesByUserId();
				newFile.sortByLastNameFirstNameAscending();
				processed.add( newFile );
				
			}
			
		}
		
	}

	@Override
	public void export() {

		log.log( Level.INFO, "Exporting processed files to disk." );
		
		for ( CSVFile file : processed ) {
			
			try {
				
				file.saveAs();
				log.log( Level.INFO, "Generated file " + file.getPath() +  " exported." );
				
			} catch ( IOException e ) {
				
				log.log( Level.SEVERE, "Export failed for file " + file.getPath() +  "." );
				failed.add( file );
				break;
				
			}
			
			saved.add( file );
			
		}
		
	}
	
	public void stage( List<CSVFile> files ) {
		
		log.log( Level.INFO, "Staging " + files.size() + " file(s) for upload." );
		this.staged = files;
		this.uploaded = new ArrayList<CSVFile>();
		this.processed = new ArrayList<CSVFile>();
		this.failed = new ArrayList<CSVFile>();
		this.saved = new ArrayList<CSVFile>();
		
	}
	
	public List<CSVFile> getUploaded(){
		return this.uploaded;
	}
	public List<CSVFile> getProcessed(){
		return this.processed;
	}
	public List<CSVFile> getFailed(){
		return this.failed;
	}
	public List<CSVFile> getSaved(){
		return this.saved;
	}
	
}
