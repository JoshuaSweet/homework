package examples.homework.csv;

/**
 * Since there are actually a couple of different file types we would work with
 * it might make sense to start with an interface that basically defines our file 
 * processing API. Implement this interface for each of the different file types to
 * handle similar processes that would require different logic to complete. 
 * @author Sweet
 *
 */
public interface FileManagerInterface {
	
	/**
	 * Upload file from specified path.
	 * @throws IOException 
	 */
	public void upload();
	
	/**
	 * Process files.
	 */
	public void process();
	
	/**
	 * Export files.
	 */
	public void export();
		
}
