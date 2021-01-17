package examples.homework.csv;

import java.io.IOException;

/**
 * Could be generalized api for processing other file types.
 * @author Sweet
 *
 */
public interface FileInterface {
	
	/**
	 * Upload file.
	 * @throws IOException
	 */
	public void upload() throws IOException;
	
	/**
	 * Determine if a file has been uploaded.
	 * @return
	 */
	public boolean uploaded();
	
	/**
	 * Validate uploaded file.
	 * @return
	 */
	public boolean validate() throws NullPointerException;
	
	/**
	 * Pre-upload validation of path.
	 * @return
	 */
	public boolean validatePath() throws NullPointerException;
	
	/**
	 * Standard file processing.
	 */
	public void process() throws NullPointerException;
	
	/**
	 * Save file.
	 * @throws IOException
	 */
	public void saveAs() throws IOException;

}
