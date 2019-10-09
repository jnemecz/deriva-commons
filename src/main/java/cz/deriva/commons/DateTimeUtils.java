package cz.deriva.commons;

import java.time.Clock;
import java.time.Instant;
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

}
