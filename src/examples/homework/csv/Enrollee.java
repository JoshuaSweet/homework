package examples.homework.csv;

import java.util.logging.Logger;

/**
 * Enrollee records from CSV files.
 * @author Sweet
 *
 */
public class Enrollee {

	private final static Logger log = Logger.getLogger( Enrollee.class.toString() );
	private String userId;
	private String firstAndLastName;
	private String firstName;
	private String lastName;
	private int version;
	private String insuranceCompany;
	private String fileLine;
	
	private Enrollee() {/*need to use constructor with args*/}
	
	public Enrollee( String fileLine ) {
		
		this.fileLine = fileLine;
		String[] dataArray = fileLine.split( "," );
		this.userId = dataArray[0];
		this.firstAndLastName = dataArray[1];
		
		String[] name = (dataArray[1]).split( " " );
		this.firstName = name[0];
		this.lastName = name[1];
		
		this.version = Integer.parseInt( dataArray[2] );
		this.insuranceCompany = dataArray[3];
		
	}
	
	public Enrollee( String userId, String firstAndLastName, int version, String insuranceCompany ) {
		
		this.userId = userId;
		this.firstAndLastName = firstAndLastName;
		
		String[] name = firstAndLastName.split( " " );
		this.firstName = name[0];
		this.lastName = name[1];
		
		this.version = version;
		this.insuranceCompany = insuranceCompany;
		
	}
	
	@Override
	public String toString() {
		return "{'UserId':'" + this.userId + 
				"','FirstAndLastName':'" + this.firstAndLastName + 
				"','FirstName':'" + this.firstName + 
				"','LastName':'" + this.lastName + 
				"','Version':'" + this.version + 
				"','InsuranceCompany':'" + this.insuranceCompany + "'}";
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getInsuranceCompany() {
		return insuranceCompany;
	}
	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}
	public String getFirstAndLastName() {
		return firstAndLastName;
	}
	public void setFirstAndLastName(String firstAndLastName) {
		this.firstAndLastName = firstAndLastName;
	}

	public String getFileLine() {
		return fileLine;
	}
	
}
