package org.folio.isbn;

import org.apache.commons.validator.routines.ISBNValidator;
import org.apache.commons.validator.routines.checkdigit.CheckDigitException;
import org.apache.commons.validator.routines.checkdigit.EAN13CheckDigit;
import org.apache.commons.validator.routines.checkdigit.ISBN10CheckDigit;
import org.apache.commons.validator.routines.checkdigit.ISBNCheckDigit;
import org.folio.isbn.IsbnUtil;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for App.
 */
public class IsbnUtilTest extends TestCase {
  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public IsbnUtilTest(String testName) {
    super(testName);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() {
    return new TestSuite(IsbnUtilTest.class);
  }

  /**
   * Test the examples mentioned in the README.md.
   */
  public void testApp() throws CheckDigitException {
    ISBNValidator isbnValidator = ISBNValidator.getInstance();
    assertEquals(true, isbnValidator.isValidISBN10("1930110995"));
    assertEquals(false, isbnValidator.isValidISBN10("1930110994"));
    assertEquals(true, isbnValidator.isValidISBN13("9781930110991"));
    assertEquals(false, isbnValidator.isValidISBN13("9781930110992"));
    assertEquals(true, isbnValidator.isValid("1930110995"));
    assertEquals(false, isbnValidator.isValid("1930110994"));
    assertEquals(true, isbnValidator.isValid("9781930110991"));
    assertEquals(false, isbnValidator.isValid("9781930110992"));

    assertEquals("9781930110991", isbnValidator.convertToISBN13("1930110995"));
    assertEquals("1930110995", IsbnUtil.convertToISBN10("9781930110991"));

    assertEquals("020163385X", isbnValidator.validateISBN10("020163385X"));
    assertEquals("020163385X", isbnValidator.validateISBN10("0-201-63385-X"));
    assertEquals("020163385X", isbnValidator.validateISBN10("0 201 63385 X"));
    assertEquals("9780201633856", isbnValidator.validateISBN13("978-0-201-63385-6"));
    assertEquals("9780201633856", isbnValidator.validate("0-201-63385-X"));
    assertEquals("9780201633856", isbnValidator.validate("978-0-201-63385-6"));

    ISBNValidator isbnValidatorWithoutConvert = ISBNValidator.getInstance(false);
    assertEquals("020163385X", isbnValidatorWithoutConvert.validate("0-201-63385-X"));
    assertEquals("5", ISBN10CheckDigit.ISBN10_CHECK_DIGIT.calculate("193011099"));
    assertEquals("X", ISBN10CheckDigit.ISBN10_CHECK_DIGIT.calculate("020163385"));
    assertEquals("1", EAN13CheckDigit.EAN13_CHECK_DIGIT.calculate("978193011099"));
    assertEquals("5", ISBNCheckDigit.ISBN_CHECK_DIGIT.calculate("193011099"));
    assertEquals("1", ISBNCheckDigit.ISBN_CHECK_DIGIT.calculate("978193011099"));
  }

  public void testConvertToIsbn10() {
    assertEquals(null, IsbnUtil.convertToISBN10(null));
    assertEquals("1930110995", IsbnUtil.convertToISBN10("9781930110991"));
    assertEquals("020163385X", IsbnUtil.convertToISBN10("9780201633856"));
    assertEquals(null, IsbnUtil.convertToISBN10("9790201633856"));
    try {
      IsbnUtil.convertToISBN10("978");
      fail("3 digit length should throw IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // ignore expected exception
    }
    try {
      IsbnUtil.convertToISBN10("978193a110991");
      fail("Illegal character a should throw IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // ignore expected exception
    }
  }
}
