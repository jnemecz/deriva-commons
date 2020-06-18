package cz.deriva.commons;

import cz.deriva.commons.utils.StringUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by jirka on 12.07.18.
 *
 * @author Jiri Nemec
 */
class StringUtilsTest {

  @Nested
  class isQuoted {

    @Test
    public void QuotedTrue() {
      assertAll(
          () -> assertTrue(StringUtils.isQuoted("\"hodnota\"")),
          () -> assertTrue(StringUtils.isQuoted("\"hodn\"ota\"")),
          () -> assertTrue(StringUtils.isQuoted("\"hodn\"ota\"")),
          () -> assertTrue(StringUtils.isQuoted("\"hodn    \" ota\"")),
          () -> assertTrue(StringUtils.isQuoted("\"\"\"\"\"\"\""))
      );
    }

    @Test
    public void QuotedFalse() {
      assertAll(
          () -> assertFalse(StringUtils.isQuoted("hodnota")),
          () -> assertFalse(StringUtils.isQuoted("\"hodnota")),
          () -> assertFalse(StringUtils.isQuoted("\"\"\"hodnota")),
          () -> assertFalse(StringUtils.isQuoted("hodnota\"")),
          () -> assertFalse(StringUtils.isQuoted("hodnota\"\"\"\""))
      );
    }

    @Test
    public void nullEmpty() {
      assertAll(
          () -> assertFalse(StringUtils.isQuoted(null)),
          () -> assertFalse(StringUtils.isQuoted("")),
          () -> assertFalse(StringUtils.isQuoted("    "))
      );
    }

  }

  @Nested
  class removeFirstQuote {

    @Test
    public void test() {

      assertAll(

          // Null a prazdne hodnoty
          () -> assertEquals(null, StringUtils.removeFirstQuote(null, "[")),
          () -> assertEquals("", StringUtils.removeFirstQuote("", "[")),
          () -> assertEquals("    ", StringUtils.removeFirstQuote("    ", "[")),

          // Neexistujici quote
          () -> assertEquals("[abc]", StringUtils.removeFirstQuote("[abc]", null)),
          () -> assertEquals("[abc]", StringUtils.removeFirstQuote("[abc]", "")),
          () -> assertEquals("[abc]", StringUtils.removeFirstQuote("[abc]", "]")),

          // Existujici quote
          () -> assertEquals("abc]", StringUtils.removeFirstQuote("[abc]", "[")),
          () -> assertEquals("[abc", StringUtils.removeLastQuote("[abc]", "]")),
          () -> assertEquals("[abc]", StringUtils.removeLastQuote("[abc]]", "]" )),
          () -> assertEquals("[abc]]", StringUtils.removeLastQuote("[abc]]]", "]" )),
          () -> assertEquals("[abc]]]", StringUtils.removeLastQuote("[abc]]]", "")),
          () -> assertEquals("abc", StringUtils.removeFirstQuote("abc", ""))
      );
    }


  }

  @Nested
  class removeLastQuote {

    @Test
    public void test() {

      assertAll(

          // Null a prazdne hodnoty
          () -> assertEquals(null, StringUtils.removeLastQuote(null, "[")),
          () -> assertEquals("", StringUtils.removeLastQuote("", "[")),
          () -> assertEquals("    ", StringUtils.removeLastQuote("    ", "[")),

          // Neexistujici quote
          () -> assertEquals("[abc]", StringUtils.removeLastQuote("[abc]", null)),
          () -> assertEquals("[abc]", StringUtils.removeLastQuote("[abc]", "")),
          () -> assertEquals("[abc]", StringUtils.removeLastQuote("[abc]", "[")),

          // Existujici quote - kratka
          () -> assertEquals("[abc", StringUtils.removeLastQuote("[abc]", "]")),
          () -> assertEquals("  [abc", StringUtils.removeLastQuote("  [abc]", "]")),
          () -> assertEquals("  [abc]", StringUtils.removeLastQuote("  [abc]]", "]" )),
          () -> assertEquals("  [abc]]", StringUtils.removeLastQuote("  [abc]]]", "]" )),
          () -> assertEquals("abc", StringUtils.removeLastQuote("abc", "")),

          // Existujici quote - dlouha
          () -> assertEquals("[abc", StringUtils.removeLastQuote("[abc])", "])")),

          () -> assertEquals("  [abc", StringUtils.removeLastQuote("  [abc])", "])")),
          () -> assertEquals("  [abc]", StringUtils.removeLastQuote("  [abc]])", "])" )),
          () -> assertEquals("  [abc]]", StringUtils.removeLastQuote("  [abc]]])", "])" )),
          () -> assertEquals("abc", StringUtils.removeLastQuote("abc", ""))
      );
    }


  }

  @Nested
  class isQuotedBy {

    final String quote = "\"";

    @Test
    public void QuotedNull() {
      assertAll(
          () -> assertFalse(StringUtils.isQuotedBy("\"hodnota\"", null, quote)),
          () -> assertFalse(StringUtils.isQuotedBy("\"hodnota\"", "", quote)),

          () -> assertFalse(StringUtils.isQuotedBy("\"hodn\"ota\"", quote, null)),
          () -> assertFalse(StringUtils.isQuotedBy("\"hodn\"ota\"", quote, "")),


          () -> assertFalse(StringUtils.isQuotedBy("\"hodn    \" ota\"", null, "")),
          () -> assertFalse(StringUtils.isQuotedBy("\"\"\"\"\"\"\"", "", null)),

          () -> assertFalse(StringUtils.isQuotedBy("\"hodn    \" ota\"", null, null)),
          () -> assertFalse(StringUtils.isQuotedBy("\"\"\"\"\"\"\"", "", ""))
      );
    }

    @Test
    public void QuotedTrue() {
      assertAll(
          () -> assertTrue(StringUtils.isQuotedBy("\"hodnota\"", quote, quote)),
          () -> assertTrue(StringUtils.isQuotedBy("\"hodn\"ota\"", quote, quote)),
          () -> assertTrue(StringUtils.isQuotedBy("\"hodn\"ota\"", quote, quote)),
          () -> assertTrue(StringUtils.isQuotedBy("\"hodn    \" ota\"", quote, quote)),
          () -> assertTrue(StringUtils.isQuotedBy("\"\"\"\"\"\"\"", quote, quote))
      );
    }

    @Test
    public void QuotedTrueLonger() {
      assertAll(
          () -> assertTrue(StringUtils.isQuotedBy("[[hodnota]]", "[[","]]")),
          () -> assertTrue(StringUtils.isQuotedBy("([[hodnota]])", "([[","]])"))
      );
    }

    @Test
    public void QuotedFalse() {
      assertAll(
          () -> assertFalse(StringUtils.isQuoted("hodnota")),
          () -> assertFalse(StringUtils.isQuoted("\"hodnota")),
          () -> assertFalse(StringUtils.isQuoted("\"\"\"hodnota")),
          () -> assertFalse(StringUtils.isQuoted("hodnota\"")),
          () -> assertFalse(StringUtils.isQuoted("hodnota\"\"\"\""))
      );
    }

    @Test
    public void nullEmpty() {
      assertAll(
          () -> assertFalse(StringUtils.isQuoted(null)),
          () -> assertFalse(StringUtils.isQuoted("")),
          () -> assertFalse(StringUtils.isQuoted("    "))
      );
    }

  }

  @Nested
  class RemoveQuotes {

    @Test
    public void remove() {
      assertAll(
          () -> assertEquals("hodnota", StringUtils.removeQuotes("hodnota")),
          () -> assertEquals("hodnota", StringUtils.removeQuotes("\"hodnota\"")),
          () -> assertEquals("\"hodnota", StringUtils.removeQuotes("\"hodnota")),
          () -> assertEquals("hodnota\"", StringUtils.removeQuotes("hodnota\"")),
          () -> assertEquals("\"\"\"\"\"", StringUtils.removeQuotes("\"\"\"\"\"\"\""))
      );
    }

    @Test
    public void nullEmpty() {
      assertAll(
          () -> assertEquals(null, StringUtils.removeQuotes(null)),
          () -> assertEquals("", StringUtils.removeQuotes("")),
          () -> assertEquals("   ", StringUtils.removeQuotes("   "))
      );
    }

  }

  @Nested
  class IsEmpty {

    @Test
    public void testNotEmpty() {
      assertAll(
          () -> assertFalse(StringUtils.isEmpty(" ")),
          () -> assertFalse(StringUtils.isEmpty("bob")),
          () -> assertFalse(StringUtils.isEmpty("  bob  "))
      );
    }

    @Test
    public void testEmpty() {
      assertAll(
          () -> assertTrue(StringUtils.isEmpty(null)),
          () -> assertTrue(StringUtils.isEmpty(""))
      );
    }

  }

  @Nested
  class IsBlank {

    @Test
    public void testNotBlank() {
      assertAll(
          () -> assertFalse(StringUtils.isBlank("bob")),
          () -> assertFalse(StringUtils.isBlank("  bob  "))
      );
    }

    @Test
    public void testBlank() {
      assertAll(
          () -> assertTrue(StringUtils.isBlank(null)),
          () -> assertTrue(StringUtils.isBlank("")),
          () -> assertTrue(StringUtils.isBlank(" ")),
          () -> assertTrue(StringUtils.isBlank("\n")),
          () -> assertTrue(StringUtils.isBlank("\t")),
          () -> assertTrue(StringUtils.isBlank("\r"))
      );
    }

  }


}