package cz.deriva.commons;

import cz.deriva.commons.utils.DateTimeUtils;
import cz.deriva.commons.utils.ReflectionUtils;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Created by jirka on 20.06.18.
 *
 * @author Jiri Nemec
 */
@RunWith(JUnitPlatform.class)
class DateTimeUtilsTest {

  private final static Long birthDate = 375903357L;
  private final static ZoneId zone = ZoneId.of("Europe/Prague");

  @BeforeEach
  void setUp() throws Exception {
    Instant ins = Instant.ofEpochSecond(birthDate);
    Clock clock = Clock.fixed(ins, zone);
    ReflectionUtils.setFinalStaticField(DateTimeUtils.class, "CLOCK", clock);
    ReflectionUtils.setFinalStaticField(DateTimeUtils.class, "LOCAL_TIME_ZONE", zone);
  }

  @BeforeEach
  public void correctTimeZone() {
    Assert.assertEquals(DateTimeUtils.localTimeZone().getId(), "Europe/Prague");
  }

  @BeforeEach
  public void correctDateTime() {
    LocalDateTime localDateTime = DateTimeUtils.actualDateTime();

    assertAll(
        () -> assertNotNull(localDateTime),
        () -> assertEquals(1981, localDateTime.getYear()),
        () -> assertEquals(11, localDateTime.getMonth().getValue()),
        () -> assertEquals(29, localDateTime.getDayOfMonth()),
        () -> assertEquals(18, localDateTime.getHour()),
        () -> assertEquals(35, localDateTime.getMinute())
    );

  }

  private void checkZonedDateTime(ZonedDateTime ps, int year, int month, int day, int hour, int minute, int second, String timeZone) {
    assertAll(
        () -> assertNotNull(ps),
        () -> assertEquals(year, ps.getYear()),
        () -> assertEquals(month, ps.getMonth().getValue()),
        () -> assertEquals(day, ps.getDayOfMonth()),
        () -> assertEquals(hour, ps.getHour()),
        () -> assertEquals(minute, ps.getMinute()),
        () -> assertEquals(second, ps.getSecond()),
        () -> assertEquals(timeZone, ps.getZone().toString())
    );
  }

  @Nested
  class clock {

    @Test
    public void standard() {

      final Clock clock = DateTimeUtils.getUtcClock(2020, 3, 25, 10, 10, 10);

      assertAll(
          () -> assertEquals(1585131010000L, clock.millis())
      );

    }

  }

  @Nested
  class fromUnixtimestampMilis {

    @Test
    public void standard() {

      long ts = 1585251215000L;
      final LocalDateTime dts = DateTimeUtils.fromUnixtimestampMilis(ts);

      assertAll(
          () -> assertEquals(LocalDateTime.of(2020, 3, 26, 19, 33, 35), dts)
      );

    }

  }

  @Nested
  class toUnixtimestamp {

    @Test
    public void prague() {

      final Clock clock = DateTimeUtils.getUtcClock(2020, 3, 25, 10, 10, 10);

      final LocalDateTime ldt = LocalDateTime.now(clock);
      final ZoneId p = ZoneId.of("Europe/Prague");

      assertAll(
          () -> assertEquals("Europe/Prague", p.getId()),
          () -> assertEquals(1585127410, DateTimeUtils.toUnixtimestampSeconds(ldt, p))
      );

    }

    @Test
    public void losAngeles() {

      final Clock clock = DateTimeUtils.getUtcClock(2020, 3, 25, 10, 10, 10);

      final LocalDateTime ldt = LocalDateTime.now(clock);
      final ZoneId p = ZoneId.of("America/Los_Angeles");

      assertAll(
          () -> assertEquals("America/Los_Angeles", p.getId()),
          () -> assertEquals(1585156210, DateTimeUtils.toUnixtimestampSeconds(ldt, p))
      );

    }

    @Test
    public void systemTimeZone() {

      final Clock clock = DateTimeUtils.getUtcClock(2020, 3, 25, 10, 10, 10);

      final LocalDateTime ldt = LocalDateTime.now(clock);
      final ZoneId p = ZoneId.systemDefault();

      assertAll(
          () -> assertEquals(DateTimeUtils.toUnixtimestampSeconds(ldt, p), DateTimeUtils.toUnixtimestampSeconds(ldt))
      );

    }

  }

  @Nested
  class toUnixtimestampMilis {

    @Test
    public void prague() {

      final Clock clock = DateTimeUtils.getUtcClock(2020, 3, 25, 10, 10, 10);

      final LocalDateTime ldt = LocalDateTime.now(clock);
      final ZoneId p = ZoneId.of("Europe/Prague");

      assertAll(
          () -> assertEquals("Europe/Prague", p.getId()),
          () -> assertEquals(1585127410000L, DateTimeUtils.toUnixtimestampMillis(ldt, p))
      );

    }

    @Test
    public void losAngeles() {

      final Clock clock = DateTimeUtils.getUtcClock(2020, 3, 25, 10, 10, 10);

      final LocalDateTime ldt = LocalDateTime.now(clock);
      final ZoneId p = ZoneId.of("America/Los_Angeles");

      assertAll(
          () -> assertEquals("America/Los_Angeles", p.getId()),
          () -> assertEquals(1585156210000L, DateTimeUtils.toUnixtimestampMillis(ldt, p))
      );

    }

    @Test
    public void systemTimeZone() {

      final Clock clock = DateTimeUtils.getUtcClock(2020, 3, 25, 10, 10, 10);

      final LocalDateTime ldt = LocalDateTime.now(clock);
      final ZoneId p = ZoneId.systemDefault();

      assertAll(
          () -> assertEquals(DateTimeUtils.toUnixtimestampMillis(ldt, p), DateTimeUtils.toUnixtimestampMillis(ldt))
      );

    }

  }

  @Nested
  class CeilingMinutes {

    @Test
    public void standard() {
      assertAll(
          () -> assertEquals(0, DateTimeUtils.ceilMinutesToHalfHour(0)),
          () -> assertEquals(30, DateTimeUtils.ceilMinutesToHalfHour(1)),
          () -> assertEquals(30, DateTimeUtils.ceilMinutesToHalfHour(29)),
          () -> assertEquals(60, DateTimeUtils.ceilMinutesToHalfHour(31)),
          () -> assertEquals(240, DateTimeUtils.ceilMinutesToHalfHour(240)),
          () -> assertEquals(270, DateTimeUtils.ceilMinutesToHalfHour(270)),
          () -> assertEquals(300, DateTimeUtils.ceilMinutesToHalfHour(271)),
          () -> assertEquals(30, DateTimeUtils.ceilMinutesToHalfHour(Double.MIN_VALUE))
      );
    }

  }


  @Nested
  class Parse {

    @Test
    @DisplayName("Parse local time")
    public void localTimeUtc() {
      final String parseString = "1981-11-29T18:35:57Z";
      ZonedDateTime ps = DateTimeUtils.parse(parseString);
      checkZonedDateTime(ps, 1981, 11, 29, 18, 35, 57, "Z");
    }

    @Test
    @DisplayName("Parse local time")
    public void localTimeWinterTime() {
      final String parseString = "1981-11-29T18:35:57+01:00";
      ZonedDateTime ps = DateTimeUtils.parse(parseString);
      checkZonedDateTime(ps, 1981, 11, 29, 18, 35, 57, "+01:00");
    }

  }

  @Nested
  @DisplayName("LocalTimeInUtc")
  class LocalTimeInUtc {

    @Test
    public void test1() {
      final ZonedDateTime v = DateTimeUtils.localTimeInUtc();
      checkZonedDateTime(v, 1981, 11, 29, 17, 35, 57, "UTC");
    }

  }

  @Nested
  @DisplayName("LocalDateWithTimeZone")
  class LocalDateWithTimeZone {

    @Test
    public void test1() {
      ZonedDateTime v = DateTimeUtils.localDateWithTimeZone();
      checkZonedDateTime(v, 1981, 11, 29, 18, 35, 57, "Europe/Prague");
    }

  }

  @Nested
  class Range {

    @Test
    public void testStartRange() {

      LocalDate from = LocalDate.of(2020, 7, 1);
      LocalDate to = LocalDate.of(2020, 7, 31);

      final LocalDate checkDate = from;

      assertAll(
          () -> assertTrue(DateTimeUtils.between(checkDate, from, to))
      );

    }

    @Test
    public void testEndRange() {

      LocalDate from = LocalDate.of(2020, 7, 1);
      LocalDate to = LocalDate.of(2020, 7, 31);

      final LocalDate checkDate = to;

      assertAll(
          () -> assertTrue(DateTimeUtils.between(checkDate, from, to))
      );

    }

    @Test
    public void testInRange() {

      LocalDate from = LocalDate.of(2020, 7, 1);
      LocalDate to = LocalDate.of(2020, 7, 31);

      final LocalDate checkDate = from.plusDays(2);

      assertAll(
          () -> assertTrue(DateTimeUtils.between(checkDate, from, to))
      );

    }

    @Test
    public void testNarrowRange() {

      LocalDate from = LocalDate.of(2020, 7, 1);

      assertAll(
          () -> assertTrue(DateTimeUtils.between(from, from, from))
      );

    }

    @Test
    public void testOutRange() {

      LocalDate from = LocalDate.of(2020, 7, 1);

      assertAll(
          () -> assertFalse(DateTimeUtils.between(from.plusYears(1), from, from))
      );

    }

  }

  class ToDate {

    @Test
    public void test1() {

      Date dd = DateTimeUtils.toDate(DateTimeUtils.actualDateTime());

      Calendar cal = Calendar.getInstance();
      cal.setTime(dd);

      // System.out.println(String.format("HODINA: %d", cal.get(Calendar.HOUR_OF_DAY)));

      assertAll(
          () -> assertNotNull(dd),
          () -> assertEquals(1981, cal.get(Calendar.YEAR)),
          () -> assertEquals(10, cal.get(Calendar.MONTH)),
          () -> assertEquals(29, cal.get(Calendar.DAY_OF_MONTH)),
          () -> assertEquals(19, cal.get(Calendar.HOUR_OF_DAY)),
          () -> assertEquals(35, cal.get(Calendar.MINUTE))
      );
    }

  }

  @Nested
  class ToUtcZonedDateTime {

    @Test
    public void test1() {

      final Date ddd = Date.from(DateTimeUtils.actualDateTime().toInstant(ZoneOffset.UTC));
      final ZonedDateTime zdt = DateTimeUtils.toUtcZonedDateTime(ddd);
      checkZonedDateTime(zdt, 1981, 11, 29, 18, 35, 57, "Z");

    }

  }

  @Nested
  class ToUtcLocalDateTime {

    @Test
    public void test1() {

      final Date ddd = Date.from(DateTimeUtils.actualDateTime().toInstant(ZoneOffset.UTC));
      final LocalDateTime dd = DateTimeUtils.toUtcLocalDateTime(ddd);

      checkLocalDateTime(dd, 1981, 11, 29, 18, 35, 57);

    }

  }

  private void checkLocalDateTime(LocalDateTime ps, int year, int month, int day, int hour, int minute, int second) {
    assertAll(
        () -> assertNotNull(ps),
        () -> assertEquals(year, ps.getYear()),
        () -> assertEquals(month, ps.getMonth().getValue()),
        () -> assertEquals(day, ps.getDayOfMonth()),
        () -> assertEquals(hour, ps.getHour()),
        () -> assertEquals(minute, ps.getMinute()),
        () -> assertEquals(second, ps.getSecond())
    );
  }

  @Nested
  class ToString {

    @Test
    @DisplayName("Prevod do UTC")
    public void localTimeUtc() {
      String s = DateTimeUtils.localTimeInUtcAsString();
      assertEquals("1981-11-29T17:35:57Z", s);
    }

    @Test
    @DisplayName("Prevod v mistni zone")
    public void test() {
      String s = DateTimeUtils.localTimeAsString();
      assertEquals("1981-11-29T18:35:57+01:00", s);
    }

    @Test
    @DisplayName("Prevod vlastniho casu v UTC")
    public void customDatetimeUtc() {

      LocalDate localDate = LocalDate.of(2018, 6, 20);
      LocalTime localDateTime = LocalTime.of(20, 10, 30, 99); // Note nanoseconds!
      ZonedDateTime zdt = ZonedDateTime.of(localDate, localDateTime, ZoneId.of("UTC"));

      assertEquals("2018-06-20T20:10:30Z", DateTimeUtils.toString(zdt));

    }

    @Test
    @DisplayName("Prevod vlastniho casu")
    public void customDatetime() {

      LocalDate localDate = LocalDate.of(2018, 6, 20);
      LocalTime localDateTime = LocalTime.of(20, 10, 30, 99); // Note nanoseconds!
      ZonedDateTime zdt = ZonedDateTime.of(localDate, localDateTime, ZoneId.of("Europe/Prague"));

      assertEquals("2018-06-20T20:10:30+02:00", DateTimeUtils.toString(zdt));

    }

  }

}