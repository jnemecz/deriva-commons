package cz.deriva.commons.utils;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * <p>Utility class pro praci s datem a casem vcetne jejich prevodu mezi casovymi zonami.</p>
 *
 * <p>Datum a cas je vracen ve formatu ISO 8601.</p>
 *
 * @author Jiri Nemec
 */
public final class DateTimeUtils {

  /**
   * Casova zona UTC.
   */
  private final static ZoneId UTC_TIME_ZONE = ZoneId.of("UTC");

  /**
   * <p>Clock pro casove udaje (behem testovani se meni na fixed).</p>
   */
  private final static Clock CLOCK = Clock.systemDefaultZone();

  /**
   * <p>Mistni casova zona.</p>
   */
  private final static ZoneId LOCAL_TIME_ZONE = ZoneId.systemDefault();

  /**
   * <p>Vraci aktualni datum a cas bez casove zony.</p>
   *
   * @return aktualni datum a cas
   */
  public static LocalDateTime actualDateTime() {
    return LocalDateTime.now(CLOCK);
  }

  /**
   * Vraci pocet minut zaokrouhleno nahoru na pulhodiny.
   *
   * @param minutes
   * @return minuty zaokrouhlene na pulhodinu nahoru
   */
  public static double ceilMinutesToHalfHour(double minutes) {

    final double diff = minutes % (double) 30;

    if (diff != 0) {
      return (double) (((int) minutes / 30) + 1) * 30;
    } else {
      return minutes;
    }

  }

  /**
   * <p>Vraci casovou zonu podle mistniho prostredi.</p>
   *
   * @return defaultni casova zona podle mistniho prostredi
   */
  public static ZoneId localTimeZone() {
    return DateTimeUtils.LOCAL_TIME_ZONE;
  }

  /**
   * <p>Parsuje string reprezentujici datum a cas s casovou zonou.</p>
   *
   * @param dateTimeAsString reprezentace data a casu s casovou zonou
   * @return rozparsovany datum a cas podle stringove reprezentace
   */
  public static ZonedDateTime parse(final String dateTimeAsString) {
    AssertUtils.notBlank(dateTimeAsString, "dateTimeAsString");
    ZonedDateTime foo = ZonedDateTime.parse(dateTimeAsString, DateTimeFormatter.ISO_ZONED_DATE_TIME);
    return foo;
  }

  /**
   * <p>Vraci aktualni datum a cas v casove zone podle mistniho prostredi.</p>
   *
   * @return aktualni datum a cas podle mistnost prostredi
   */
  public static ZonedDateTime localDateWithTimeZone() {
    return ZonedDateTime.of(
        DateTimeUtils.actualDateTime(),
        DateTimeUtils.localTimeZone()
    );
  }

  /**
   * <p>Vraci aktualni datum a cas v UTC.</p>
   *
   * @return aktualni cas v casove zone UTC
   */
  public static ZonedDateTime localTimeInUtc() {
    final ZonedDateTime local = DateTimeUtils.localDateWithTimeZone();
    return local.withZoneSameInstant(UTC_TIME_ZONE);
  }

  /**
   * <p>Vraci lokalni cas v mistni casove zone jako string.</p>
   *
   * @return
   */
  public static String localTimeAsString() {
    return DateTimeUtils.toString(DateTimeUtils.localDateWithTimeZone());
  }

  /**
   * <p>Vraci lokalni cas v UTC casove zone jako string.</p>
   *
   * @return
   */
  public static String localTimeInUtcAsString() {
    return DateTimeUtils.toString(DateTimeUtils.localTimeInUtc());
  }

  /**
   * <p>Prevede datum a cas do {@code String}</p>
   *
   * @param dateTime prevadeny datum a cas
   * @return prevedeny datum a cas
   */
  public static String toString(final ZonedDateTime dateTime) {
    AssertUtils.notNull(dateTime, "dateTime");
    LocalDateTime lcd = dateTime.withNano(0).toLocalDateTime();
    ZonedDateTime zdt = ZonedDateTime.of(lcd, dateTime.getZone());
    return zdt.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
  }

  public static java.time.ZonedDateTime toZoneDateTime(final java.sql.Timestamp timestamp, final String timeZone) {
    AssertUtils.notNull(timestamp, "timestamp");
    AssertUtils.notBlank(timeZone, "timeZone");
    return timestamp.toLocalDateTime().atZone(ZoneId.of(timeZone));
  }

  /**
   * Vraci mistni datum a cas v UTC time zone.
   *
   * @return datum a cas v UTC
   */
  public static LocalDateTime utcNow() {
    return LocalDateTime.now(Clock.systemUTC());
  }

  public static LocalDateTime fromUnixtimestampMilis(final Long utcUnixTimeStampMilis) {
    AssertUtils.isGtZero(utcUnixTimeStampMilis, "utcUnixTimeStampMilis");
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(utcUnixTimeStampMilis), ZoneId.of("UTC"));
  }

  public static long toUnixtimestampSeconds(final LocalDateTime localDateTime, final ZoneId zoneId) {
    AssertUtils.notNull(localDateTime, "localDateTime");
    AssertUtils.notNull(zoneId, "zoneId");
    return localDateTime.atZone(zoneId).toEpochSecond();
  }

  public static long toUnixtimestampMillis(final LocalDateTime localDateTime, final ZoneId zoneId) {
    return DateTimeUtils.toUnixtimestampSeconds(localDateTime, zoneId) * 1000;
  }

  public static long toUnixtimestampMillis(final LocalDateTime localDateTime) {
    return DateTimeUtils.toUnixtimestampMillis(localDateTime, ZoneId.systemDefault());
  }

  public static long toUnixtimestampSeconds(final LocalDateTime localDateTime) {
    return DateTimeUtils.toUnixtimestampSeconds(localDateTime, ZoneId.systemDefault());
  }

  public static java.sql.Timestamp toSqlTimeStamp(final ZonedDateTime zonedDateTime) {
    AssertUtils.notNull(zonedDateTime, "zonedDateTime");
    return java.sql.Timestamp.valueOf(zonedDateTime.toLocalDateTime());
  }

  public static java.time.ZonedDateTime toUtcZonedDateTime(final java.util.Date date) {
    AssertUtils.notNull(date, "date");
    Instant instant = Instant.ofEpochMilli(date.getTime());
    return ZonedDateTime.ofInstant(instant, ZoneOffset.UTC);
  }

  public static java.time.LocalDateTime toUtcLocalDateTime(final java.util.Date date) {
    AssertUtils.notNull(date, "date");
    Instant instant = Instant.ofEpochMilli(date.getTime());
    LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
    return ldt;
  }

  public static Clock getClock(int year, int month, int day, int hour, int minute, int second, final ZoneOffset zone) {
    final Instant ins = LocalDate.of(year, month, day).atTime(hour, minute, second).toInstant(zone);
    return Clock.fixed(ins, zone);
  }

  public static Clock getUtcClock(int year, int month, int day, int hour, int minute, int second) {
    final Instant ins = LocalDate.of(year, month, day).atTime(hour, minute, second).toInstant(ZoneOffset.UTC);
    return Clock.fixed(ins, ZoneOffset.UTC);
  }

  public static java.util.Date toDate(final ZonedDateTime localDateTime) {
    AssertUtils.notNull(localDateTime, "localDateTime");
    return DateTimeUtils.dateFromInstant(localDateTime.toInstant());
  }

  public static java.util.Date toDate(final LocalDateTime localDateTime) {
    AssertUtils.notNull(localDateTime, "localDateTime");
    return dateFromInstant(localDateTime.toInstant(ZoneOffset.UTC));
  }

  private static final java.util.Date dateFromInstant(final Instant instant) {
    AssertUtils.notNull(instant, "instant");
    return Date.from(instant);
  }

  public static Double minutesToHours(Double minutes) {
    AssertUtils.notNull(minutes, "minutes");
    AssertUtils.validState(minutes >= 0, "Pocet minut musi byt > 0");
    return MathUtils.round(minutes / (double) 60, 2);
  }

  public static boolean between(LocalDate checkDate, LocalDate from, LocalDate to) {

    AssertUtils.notNull(checkDate, "checkDate");
    AssertUtils.notNull(from, "from");
    AssertUtils.notNull(to, "to");

    if (checkDate.isBefore(from)) {
      return false;
    }

    if (checkDate.isAfter(to)) {
      return false;
    }

    return true;

  }

}
