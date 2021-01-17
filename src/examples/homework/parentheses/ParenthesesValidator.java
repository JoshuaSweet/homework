package examples.homework.parentheses;

import java.util.logging.*;

/**
 * Provides various static validation methods for determining if 
 * string expressions containing parentheses are correctly formatted
 * in part or in whole.  This was created to validate LISP expression
 * but can be generalized.  
 * @author Sweet
 *
 */
public class ParenthesesValidator {

	private final static Logger log = Logger.getLogger( ParenthesesValidator.class.toString() );
	private static final char OPEN_PARENTHESIS = '(';
	private static final char CLOSED_PARENTHESIS = ')';
	private static final String ILLEGAL_ARGUMENT_MESSAGE = "Expression must be non null String containing at least 2 characters.";
	
	/**
	 * Determines if expression String is non null and at least 2 chars in length.
	 * Throws illegal argument exception if String is null or less than 2 chars in
	 * length to facilitate fail fast.
	 * @param expression
	 * @return
	 * @throws IllegalArgumentException
	 */
	protected static boolean isLegalArgument( String expression ) 
		throws IllegalArgumentException {
		
		if ( expression == null || expression.length() < 2 ) {
			
			throw new IllegalArgumentException( ILLEGAL_ARGUMENT_MESSAGE );
			
		} else {
			
			return true;
			
		}	
		
	}
	
	/**
	 * Determines if opening parenthesis is present in correct location.
	 * Ignores any other expression inaccuracies.
	 * For full testing, combine with other validators.
	 * @param expression
	 * @return true if opening parenthesis present at begging of string param.
	 */
	protected static boolean openingParenthesis( String expression )
		throws IllegalArgumentException {
		
		isLegalArgument( expression );
		
		boolean valid = false;
		char firstChar = expression.charAt( 0 );
		
		if ( firstChar == ParenthesesValidator.OPEN_PARENTHESIS ) {
			
			valid = true;
			
		}
		
		return valid;
		
	}
	
	/**
	 * Determines if closing parenthesis is present in correct location.
	 * Ignores any other expression inaccuracies.
	 * For full testing, combine with other validators.
	 * @param expression
	 * @return true if closing parenthesis present at the end of string param.
	 * * @throws IllegalArgumentException
	 */
	protected static boolean closingParenthesis( String expression )	
		throws IllegalArgumentException {
			
		isLegalArgument( expression );
		
		boolean valid = false;
		char lastChar = expression.charAt( expression.length() - 1 );
		
		if ( lastChar == ParenthesesValidator.CLOSED_PARENTHESIS ) {
			
			valid = true;
			
		}
		
		return valid;
		
	}	
	
	/**
	 * Determines if String expression contains open and close parentheses.
	 * @param expression
	 * @return true if contains open and close parentheses.
	 * @throws IllegalArgumentException
	 */
	protected static boolean hasParentheses( String expression )	
		throws IllegalArgumentException {
			
		isLegalArgument( expression );
		
		boolean valid = true;
		boolean hasOpenParenthesis = expression.contains( "(" );
		boolean hasClosedParenthesis = expression.contains( ")" );
		
		if ( !hasOpenParenthesis ) {
			
			log.log( Level.INFO, "Nesting validation failed for missing open parentheses." );	
			valid = false;
			
		}
		
		if ( !hasClosedParenthesis ) {
			
			log.log( Level.INFO, "Nesting validation failed for missing close parentheses." );
			valid = false;
			
		}
		
		return valid;
		
	}	
	
	/**
	 * Determines if parentheses are properly opened and closed and nested;
	 * @param expression
	 * @return true if each open parenthesis has a corresponding closing parenthesis.
	 * @throws IllegalArgumentException
	 */
	protected static boolean nesting( String expression ) 
	throws IllegalArgumentException {
		
		boolean hasParentheses = ParenthesesValidator.hasParentheses( expression );		
		boolean valid = false;		
		
		if ( hasParentheses ) {	
			
			int expressionLength = expression.length();
			int openCloseMatch = 0;
			
			for ( int i = 0; i < expressionLength; i++ ) {
				
				char currentChar = expression.charAt( i );
				
				if ( currentChar == ParenthesesValidator.OPEN_PARENTHESIS ) {
					
					openCloseMatch++;
					
				} 
				else if ( currentChar == ParenthesesValidator.CLOSED_PARENTHESIS ) {
					
					openCloseMatch--;
					
				}
				
				if ( openCloseMatch < 0 ) {
					
					log.log( Level.INFO, "Nesting validation failed at index " + i + " for too many closing parentheses." );
					break;
					
				}
				
			}
			
			if ( openCloseMatch == 0 ) {
				
				valid = true;
				
			} 
			else if ( openCloseMatch > 0 ) {
				
				log.log( Level.INFO, "Nesting validation failed too many open parentheses." );
				
			}
			
		}
		
		return valid;
		
	}
	
	/**
	 * Determines if parentheses are properly closed and nested. 
	 * @param expression
	 * @return true if parentheses are properly closed and nested.
	 * @throws IllegalArgumentException
	 */
	public static boolean closedAndNested( String expression )
		throws IllegalArgumentException {
		
		isLegalArgument( expression );
		
		boolean valid = ParenthesesValidator.hasParentheses( expression ) &&
				ParenthesesValidator.openingParenthesis( expression ) &&
				ParenthesesValidator.closingParenthesis( expression ) &&
				ParenthesesValidator.nesting( expression );
		
		return valid;
		
	}
	
}
