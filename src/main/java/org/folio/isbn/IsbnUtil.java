package org.folio.isbn;

import org.apache.commons.validator.routines.checkdigit.CheckDigitException;
import org.apache.commons.validator.routines.checkdigit.ISBN10CheckDigit;

/**
 * Class with utility methods for working with ISBN number
 */
public final class IsbnUtil {

  private IsbnUtil() {
  }

  /**
   * Convert an ISBN-13 code to an ISBN-10 code if possible.
   * <p>
   * This method requires a valid ISBN-13 with NO formatting
   * characters.
   *
   * @param isbn13 The ISBN-13 code to convert
   * @return A converted ISBN-10 code or <code>null</code>
   * if the ISBN-13 code is not valid or does not have an ISBN-10 code.
   * @throws IllegalArgumentException if the input is not 0 or 13 characters long
   * or contains a character that is not a digit.
   * @see org.apache.commons.validator.routines.ISBNValidator#convertToISBN13(String)
   */
  static public String convertToISBN10(String isbn13) {
    if (isbn13 == null) {
      return null;
    }

    String input = isbn13.trim();
    if (input.length() != 13) {
      throw new IllegalArgumentException("Invalid length " + input.length() + " for '" + input + "'");
    }

    if (! input.startsWith("978")) {
      return null;
    }

    // drop "978" and the original check digit
    String isbn10 = input.substring(3, 12);
    try {
      return isbn10 + ISBN10CheckDigit.ISBN10_CHECK_DIGIT.calculate(isbn10);
    } catch (CheckDigitException e) {
      throw new IllegalArgumentException("Check digit error for '" + input + "' - " + e.getMessage());
    }
  }
}
