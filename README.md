# folio-isbn-util

Copyright (C) 2018 The Open Library Foundation

This software is distributed under the terms of the Apache License,
Version 2.0. See the file "[LICENSE](LICENSE)" for more information.

## Introduction

ISBN number converter utilities.

Use
* [ISBNValidator](https://commons.apache.org/proper/commons-validator/apidocs/org/apache/commons/validator/routines/ISBNValidator.html)
* [ISBN10CheckDigit.ISBN10_CHECK_DIGIT](https://commons.apache.org/proper/commons-validator/apidocs/org/apache/commons/validator/routines/checkdigit/ISBN10CheckDigit.html)
* [EAN13CheckDigit.EAN13_CHECK_DIGIT](https://commons.apache.org/proper/commons-validator/apidocs/org/apache/commons/validator/routines/checkdigit/EAN13CheckDigit.html) (for ISBN13)
* [ISBNCheckDigit.ISBN_CHECK_DIGIT](https://commons.apache.org/proper/commons-validator/apidocs/org/apache/commons/validator/routines/checkdigit/ISBNCheckDigit.html) (for ISBN10 and ISBN10 combined)

from org.apache.commons.validator.routines of [Apache Commons Validator](https://commons.apache.org/proper/commons-validator/).

Many more examples are in the [unit tests](https://svn.apache.org/viewvc/commons/proper/validator/trunk/src/test/java/org/apache/commons/validator/routines/).

## Examples

```
    import org.apache.commons.validator.routines.ISBNValidator;
    import org.apache.commons.validator.routines.checkdigit.CheckDigitException;
    import org.apache.commons.validator.routines.checkdigit.EAN13CheckDigit;
    import org.apache.commons.validator.routines.checkdigit.ISBN10CheckDigit;
    import org.apache.commons.validator.routines.checkdigit.ISBNCheckDigit;
    import org.folio.isbn.IsbnUtil;

    ISBNValidator isbnValidator = ISBNValidator.getInstance();
    isbnValidator.isValidISBN10("1930110995")) = true
    isbnValidator.isValidISBN10("1930110994")) = false
    isbnValidator.isValidISBN13("9781930110991")) = true
    isbnValidator.isValidISBN13("9781930110992")) = false
    isbnValidator.isValid("1930110995")) = true
    isbnValidator.isValid("1930110994")) = false
    isbnValidator.isValid("9781930110991")) = true
    isbnValidator.isValid("9781930110992")) = false

    isbnValidator.convertToISBN13("1930110995")) = "9781930110991"
    IsbnUtil.convertToISBN10("9781930110991")) = "1930110995"

    isbnValidator.validateISBN10("020163385X")) = "020163385X"
    isbnValidator.validateISBN10("0-201-63385-X")) = "020163385X"
    isbnValidator.validateISBN10("0 201 63385 X")) = "020163385X"
    isbnValidator.validateISBN13("978-0-201-63385-6")) = "9780201633856"
    isbnValidator.validate("0-201-63385-X")) = "9780201633856"
    isbnValidator.validate("978-0-201-63385-6")) = "9780201633856"

    ISBNValidator isbnValidatorWithoutConvert = ISBNValidator.getInstance(false);
    isbnValidatorWithoutConvert.validate("0-201-63385-X")) = "020163385X"

    ISBN10CheckDigit.ISBN10_CHECK_DIGIT.calculate("193011099")) = "5"
    ISBN10CheckDigit.ISBN10_CHECK_DIGIT.calculate("020163385")) = "X"
    // ISBN13 is EAN13
    EAN13CheckDigit.EAN13_CHECK_DIGIT.calculate("978193011099")) = "1"
    ISBNCheckDigit.ISBN_CHECK_DIGIT.calculate("193011099")) = "5"
    ISBNCheckDigit.ISBN_CHECK_DIGIT.calculate("978193011099")) = "1"
```

## Deprecated classes

Note to use the classes from the `org.apache.commons.validator.routines` subdirectory
and not the deprecated classes from the `org.apache.commons.validator` directory.

## Additional information

* See project [ISBNUTIL](https://issues.folio.org/browse/ISBNUTIL)
at the [FOLIO issue tracker](https://dev.folio.org/guidelines/issue-tracker).

* Other FOLIO Developer documentation is at [dev.folio.org](https://dev.folio.org/)
