package cz.deriva.commons.utils;

/**
 * Created by jirka on 12.07.18.
 *
 * @author Jiri Nemec
 */
public final class StringUtils {

  private static final String QUOTES = "\"";

  private StringUtils() {
  }

  public static boolean isNotBlank(final CharSequence cs) {
    return !isBlank(cs);
  }

  public static boolean equals(final String value1, final String value2) {

    AssertUtils.notNull(value1, "Value 1 should not be null");
    AssertUtils.notNull(value2, "Value 2 should not be null");

    int length1 = value1.length();

    if (length1 != value2.length()) {
      return false;
    }

    int status = 0;

    for (int i = 0; i < length1; i++) {
      status |= value1.charAt(i) ^ value2.charAt(i);
    }

    return status == 0;

  }

  /**
   * Odebere uvozovky (") na zacatku a konci hodnoty. Pokud hodnota neni v uvozovkach, vraci hodnotu beze zmeny.
   *
   * @param element
   * @return
   */
  public static String removeQuotes(final String element) {
    // Musi byt o-quotovana na obou stranach
    if (isQuotedBy(element, QUOTES, QUOTES)) {
      return removeQuotes(element, QUOTES, QUOTES);
    } else {
      return element;
    }
  }

  /**
   * <p>Odebere ze stringu prvni znak, pokud je to znak {@code quote}.</p>
   *
   * <pre>
   * StringUtils.removeFirstQuote(null, "[")                 = null
   * StringUtils.removeFirstQuote("", "[")                   = null
   * StringUtils.removeFirstQuote("[abc]", null)             = [abc]
   * StringUtils.removeFirstQuote("[abc]", "")               = [abc]
   * StringUtils.removeFirstQuote("[abc]", "[")               = abc]
   * StringUtils.removeFirstQuote("abc", "[")               = abc
   * </pre>
   *
   * @param element hodnota ke zpacovani, muze byt prazdna i null, v tom pripade se vraci puvodni hodnota
   * @param quote   hodnota, ktera se ma odebrat, pokud null / prazndy, tak se vraci puvodni hodnota
   * @return upraveny string bez uvodni hodnoty
   */

  public static String removeFirstQuote(final String element, final String quote) {
    if (StringUtils.isBlank(element) || StringUtils.isBlank(quote)) {
      return element;
    }
    if (quote.equals(element.substring(0, 1))) {
      return element.substring(1);
    } else {
      return element;
    }
  }

  /**
   * <p>Odebere ze stringu posledni znak, pokud je to znak {@code quote}.</p>
   *
   * <pre>
   * StringUtils.removeLastQuote(null, "[")                 = null
   * StringUtils.removeLastQuote("", "[")                   = null
   * StringUtils.removeLastQuote("[abc]", null)             = [abc]
   * StringUtils.removeLastQuote("[abc]", "")               = [abc]
   * StringUtils.removeLastQuote("[abc]", "[")               = abc]
   * StringUtils.removeLastQuote("abc", "[")               = abc
   * </pre>
   *
   * @param element hodnota ke zpacovani, muze byt prazdna i null, v tom pripade se vraci puvodni hodnota
   * @param quote   hodnota, ktera se ma odebrat, pokud null / prazndy, tak se vraci puvodni hodnota
   * @return upraveny string bez uvodni hodnoty
   */
  public static String removeLastQuote(final String element, final String quote) {

    if (StringUtils.isBlank(element) || StringUtils.isBlank(quote)) {
      return element;
    }

    if (quote.equals(element.substring(element.length() - quote.length()))) {
      return element.substring(0, element.length() - quote.length());
    } else {
      return element;
    }

  }

  public static String removeQuotes(final String element, final String firstQuote, final String lastQuote) {
    return StringUtils.removeLastQuote(StringUtils.removeFirstQuote(element, firstQuote), lastQuote);
  }

  public static boolean isQuotedBy(final String element, final String firstQuote, final String lastQuote) {

    if (StringUtils.isBlank(element) || StringUtils.isBlank(firstQuote) || StringUtils.isBlank(lastQuote)) {
      return false;
    }

    final String first = element.substring(0, firstQuote.length());

    if (firstQuote.equals(first)) {
      final String last = element.substring(element.length() - lastQuote.length());
      if (lastQuote.equals(last)) {
        return true;
      }
    }

    return false;

  }

  /**
   * Kontroluje, jestli je hodnota uvozena v uvozovkach.
   *
   * @param element
   * @return
   */

  public static boolean isQuoted(final String element) {

    if (StringUtils.isBlank(element)) {
      return false;
    }

    final String first = element.substring(0, QUOTES.length());

    if (QUOTES.equals(first)) {
      final String last = element.substring(element.length() - QUOTES.length());
      if (QUOTES.equals(last)) {
        return true;
      }
    }

    return false;

  }

  public static boolean isBlank(final CharSequence cs) {

    if (StringUtils.isEmpty(cs)) {
      return true;
    }

    final String testValue = cs.toString().trim();

    return testValue.length() == 0;

  }

  public static boolean isNotEmpty(final CharSequence cs) {
    return !isEmpty(cs);
  }

  public static boolean isEmpty(final CharSequence cs) {
    return cs == null || cs.length() == 0;
  }


}
