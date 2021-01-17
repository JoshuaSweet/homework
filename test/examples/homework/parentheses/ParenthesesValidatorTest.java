package examples.homework.parentheses;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import examples.homework.parentheses.ParenthesesValidator;

/**
 * Tests example.homework.ParenthesesValidator.
 * @author Sweet
 *
 */
public class ParenthesesValidatorTest {
	
	/**
	 * Using modifications of common lisp string I found online.
	 */
	private final String CORRECT_EXPRESSION = "(defun negate (X)\r\n"
			+ "  \"Negate the value of X.\"  ; This is a documentation string.\r\n"
			+ "  (- X))";
	
	private final String MISSING_OPENING_PARENTHESIS = "defun negate (X)\r\n"
			+ "  \"Negate the value of X.\"  ; This is a documentation string.\r\n"
			+ "  (- X))";
	
	private final String MISSING_CLOSING_PARENTHESIS = "(defun negate (X)\r\n"
			+ "  \"Negate the value of X.\"  ; This is a documentation string.\r\n"
			+ "  (- X";
	
	private final String MISSING_NESTED_OPEN_PARENTHESIS = "(defun negate X)\r\n"
			+ "  \"Negate the value of X.\"  ; This is a documentation string.\r\n"
			+ "  (- X))";
	
	private final String MISSING_NESTED_CLOSED_PARENTHESIS = "(defun negate (X\r\n"
			+ "  \"Negate the value of X.\"  ; This is a documentation string.\r\n"
			+ "  (- X))";
	
	private final String MISSING_ALL_PARENTHESIS = "defun negate X\r\n"
			+ "  \"Negate the value of X.\"  ; This is a documentation string.\r\n"
			+ "  - X";
	
	private final String STARTS_WITH_CLOSING_PARENTHESIS = ")defun negate (X)\r\n"
			+ "  \"Negate the value of X.\"  ; This is a documentation string.\r\n"
			+ "  (- X))";
	
	private final String ENDS_WITH_OPENING_PARENTHESIS = "(defun negate (X)\r\n"
			+ "  \"Negate the value of X.\"  ; This is a documentation string.\r\n"
			+ "  (- X)(";
	
	private final String OPENING_CLOSING_MISMATCH = ")defun negate (X)\r\n"
			+ "  \"Negate the value of X.\"  ; This is a documentation string.\r\n"
			+ "  (- X)(";
	
	private final String OPEN_CLOSE_MISMATCH = ")defun negate )X(\r\n"
			+ "  \"Negate the value of X.\"  ; This is a documentation string.\r\n"
			+ "  )- X((";
	
	private final String LEGAL_ARGUMENT = "()";
	
	private final String ILLEGAL_ARGUMENT = "(";

	@Before
	public void setUp() throws Exception {
	}
	
	//ARGUMENT LEGALITY
	
	/**
	 * Tests ParenthesesValidator.isLegalArgument when parameter is legal.
	 */
	@Test
	public void test_argument_validation_when_legal_argument() {
				
		boolean actual = ParenthesesValidator.isLegalArgument( this.LEGAL_ARGUMENT );	
		assertTrue( actual );
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_argument_validation_when_illegal_argument() {
				
		ParenthesesValidator.isLegalArgument( this.ILLEGAL_ARGUMENT );		
		
	}

	//OPENING PARENTHESIS TESTING
	
	@Test
	public void test_opening_parenthesis_validation_when_correct_expression() {
				
		boolean actual = ParenthesesValidator.openingParenthesis( this.CORRECT_EXPRESSION );
		assertTrue( actual );
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_opening_parenthesis_validation_when_illegal_arg() {
				
		ParenthesesValidator.openingParenthesis( this.ILLEGAL_ARGUMENT );
				
	}
	
	@Test
	public void test_opening_parenthesis_validation_when_missing_opening() {
				
		boolean actual = ParenthesesValidator.openingParenthesis( this.MISSING_OPENING_PARENTHESIS );
		assertFalse( actual );
		
	}
	
	@Test
	public void test_opening_parenthesis_validation_when_start_with_closing() {
				
		boolean actual = ParenthesesValidator.openingParenthesis( this.STARTS_WITH_CLOSING_PARENTHESIS );
		assertFalse( actual );
		
	}
	
	@Test
	public void test_opening_parenthesis_validation_when_opening_closing_mismatch() {
				
		boolean actual = ParenthesesValidator.openingParenthesis( this.OPENING_CLOSING_MISMATCH );
		assertFalse( actual );
		
	}
	
	@Test
	public void test_opening_parenthesis_validation_when_open_close_mismatch() {
				
		boolean actual = ParenthesesValidator.openingParenthesis( this.OPEN_CLOSE_MISMATCH );
		assertFalse( actual );
		
	}
	
	/*
	 * Ensure this method only cares about the opening parenthesis.
	 * */
	@Test
	public void test_opening_parenthesis_validation_when_missing_closing() {
				
		boolean actual = ParenthesesValidator.openingParenthesis( this.MISSING_CLOSING_PARENTHESIS );
		assertTrue( actual );
		
	}
	
	//CLOSING PARENTHESIS TESTING
	
	@Test
	public void test_closing_parenthesis_validation_when_correct_expression() {
				
		boolean actual = ParenthesesValidator.closingParenthesis( this.CORRECT_EXPRESSION );
		assertTrue( actual );
		
	}
	
	@Test
	public void test_closing_parenthesis_validation_when_ends_with_open() {
				
		boolean actual = ParenthesesValidator.closingParenthesis( this.ENDS_WITH_OPENING_PARENTHESIS );
		assertFalse( actual );
		
	}
	
	@Test
	public void test_closing_parenthesis_validation_when_missing_all() {
				
		boolean actual = ParenthesesValidator.closingParenthesis( this.MISSING_ALL_PARENTHESIS );
		assertFalse( actual );
		
	}
	
	@Test
	public void test_closing_parenthesis_validation_when_missing_closing() {
				
		boolean actual = ParenthesesValidator.closingParenthesis( this.MISSING_CLOSING_PARENTHESIS );
		assertFalse( actual );
		
	}
	
	@Test
	public void test_closing_parenthesis_validation_when_open_close_mismatch() {
				
		boolean actual = ParenthesesValidator.closingParenthesis( this.OPEN_CLOSE_MISMATCH );
		assertFalse( actual );
		
	}
	
	@Test
	public void test_closing_parenthesis_validation_when_opening_closing_mismatch() {
				
		boolean actual = ParenthesesValidator.closingParenthesis( this.OPENING_CLOSING_MISMATCH );
		assertFalse( actual );
		
	}
	
	/*
	 * Ensure this method only cares about closing parenthesis.
	 * */
	@Test
	public void test_closing_parenthesis_validation_when_missing_opening() {
				
		boolean actual = ParenthesesValidator.closingParenthesis( this.MISSING_OPENING_PARENTHESIS );
		assertTrue( actual );
		
	}
	
	//HAS PARENTHESES
	
	@Test
	public void test_hasParentheses_validation_when_correct_expression() {
				
		boolean actual = ParenthesesValidator.hasParentheses( this.CORRECT_EXPRESSION );
		assertTrue( actual );
		
	}
	
	@Test( expected = IllegalArgumentException.class )
	public void test_hasParentheses_validation_when_illegal_argument() {
				
		ParenthesesValidator.hasParentheses( this.ILLEGAL_ARGUMENT );
				
	}
	
	@Test
	public void test_hasParentheses_validation_when_missing_all_parentheses() {
				
		boolean actual = ParenthesesValidator.hasParentheses( this.MISSING_ALL_PARENTHESIS );
		assertFalse( actual );
				
	}
	
	@Test
	public void test_hasParentheses_validation_when_missing_some_parentheses() {
				
		boolean actual = ParenthesesValidator.hasParentheses( this.MISSING_CLOSING_PARENTHESIS );
		assertTrue( actual );
				
	}
	
	@Test
	public void test_hasParentheses_validation_when_open_close_mismatch() {
				
		boolean actual = ParenthesesValidator.hasParentheses( this.OPEN_CLOSE_MISMATCH );
		assertTrue( actual );
				
	}
	
	@Test
	public void test_hasParentheses_validation_when_opening_closing_mismatch() {
				
		boolean actual = ParenthesesValidator.hasParentheses( this.OPENING_CLOSING_MISMATCH );
		assertTrue( actual );
				
	}
	
	//NESTING PARENTHESES TESTING
	
	@Test
	public void test_nesting_validation_when_correct_expression() {
				
		boolean actual = ParenthesesValidator.nesting( this.CORRECT_EXPRESSION );
		assertTrue( actual );
		
	}
	
	@Test( expected = IllegalArgumentException.class )
	public void test_nesting_validation_when_illegal_argument() {
				
		ParenthesesValidator.nesting( this.ILLEGAL_ARGUMENT );
				
	}
	
	@Test
	public void test_nesting_validation_when_missing_all() {
				
		boolean actual = ParenthesesValidator.nesting( this.MISSING_ALL_PARENTHESIS );
		assertFalse( actual );
		
	}
	
	@Test
	public void test_nesting_validation_when_missing_closing() {
				
		boolean actual = ParenthesesValidator.nesting( this.MISSING_CLOSING_PARENTHESIS );
		assertFalse( actual );
		
	}
	
	@Test
	public void test_nesting_validation_when_missing_nested_closed() {
				
		boolean actual = ParenthesesValidator.nesting( this.MISSING_NESTED_CLOSED_PARENTHESIS );
		assertFalse( actual );
		
	}
	
	@Test
	public void test_nesting_validation_when_missing_nested_open() {
				
		boolean actual = ParenthesesValidator.nesting( this.MISSING_NESTED_OPEN_PARENTHESIS );
		assertFalse( actual );
		
	}
	
	@Test
	public void test_nesting_validation_when_missing_opening() {
				
		boolean actual = ParenthesesValidator.nesting( this.MISSING_OPENING_PARENTHESIS );
		assertFalse( actual );
		
	}
	
	@Test
	public void test_nesting_validation_when_open_close_mismatch() {
				
		boolean actual = ParenthesesValidator.nesting( this.OPEN_CLOSE_MISMATCH );
		assertFalse( actual );
		
	}
	
	@Test
	public void test_nesting_validation_when_opening_closing_mismatch() {
				
		boolean actual = ParenthesesValidator.nesting( this.OPENING_CLOSING_MISMATCH );
		assertFalse( actual );
		
	}
	
	@Test
	public void test_nesting_validation_when_start_with_closing() {
				
		boolean actual = ParenthesesValidator.nesting( this.STARTS_WITH_CLOSING_PARENTHESIS );
		assertFalse( actual );
		
	}
	
	//CLOSED AND NESTED
	
	@Test
	public void test_closedAndNested_validation_when_correct_expression() {
				
		boolean actual = ParenthesesValidator.closedAndNested( this.CORRECT_EXPRESSION );
		assertTrue( actual );
		
	}
	
	@Test( expected = IllegalArgumentException.class )
	public void test_closedAndNested_validation_when_illegal_argument() {
				
		ParenthesesValidator.closedAndNested( this.ILLEGAL_ARGUMENT );
				
	}
	
	@Test
	public void test_closedAndNested_validation_when_end_with_opening() {
				
		boolean actual = ParenthesesValidator.closedAndNested( this.ENDS_WITH_OPENING_PARENTHESIS );
		assertFalse( actual );
		
	}
	
	@Test
	public void test_closedAndNested_validation_when_missing_all() {
				
		boolean actual = ParenthesesValidator.closedAndNested( this.MISSING_ALL_PARENTHESIS );
		assertFalse( actual );
		
	}
	
	@Test
	public void test_closedAndNested_validation_when_missing_closing() {
				
		boolean actual = ParenthesesValidator.closedAndNested( this.MISSING_CLOSING_PARENTHESIS );
		assertFalse( actual );
		
	}
	
	@Test
	public void test_closedAndNested_validation_when_missing_nested_closed() {
				
		boolean actual = ParenthesesValidator.closedAndNested( this.MISSING_NESTED_CLOSED_PARENTHESIS );
		assertFalse( actual );
		
	}
	
	@Test
	public void test_closedAndNested_validation_when_missing_nested_open() {
				
		boolean actual = ParenthesesValidator.closedAndNested( this.MISSING_NESTED_OPEN_PARENTHESIS );
		assertFalse( actual );
		
	}
	
	@Test
	public void test_closedAndNested_validation_when_missing_opening() {
				
		boolean actual = ParenthesesValidator.closedAndNested( this.MISSING_OPENING_PARENTHESIS );
		assertFalse( actual );
		
	}
	
	@Test
	public void test_closedAndNested_validation_when_open_close_mismatch() {
				
		boolean actual = ParenthesesValidator.closedAndNested( this.OPEN_CLOSE_MISMATCH );
		assertFalse( actual );
		
	}
	
	@Test
	public void test_closedAndNested_validation_when_opening_closing_mismatch() {
				
		boolean actual = ParenthesesValidator.closedAndNested( this.OPENING_CLOSING_MISMATCH );
		assertFalse( actual );
		
	}
	
	@Test
	public void test_closedAndNested_validation_when_starts_with_closing() {
				
		boolean actual = ParenthesesValidator.closedAndNested( this.STARTS_WITH_CLOSING_PARENTHESIS );
		assertFalse( actual );
		
	}

}
