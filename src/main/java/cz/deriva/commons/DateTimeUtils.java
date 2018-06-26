package cz.deriva.commons;

import java.time.*;
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
    public static ZonedDateTime parse(String dateTimeAsString) {
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
    public static String toString(ZonedDateTime dateTime) {
        LocalDateTime lcd = dateTime.withNano(0).toLocalDateTime();
        ZonedDateTime zdt = ZonedDateTime.of(lcd, dateTime.getZone());
        return zdt.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    public static LocalDateTime toUtcLocalDateTime(Date date) {
        Instant instant = Instant.ofEpochMilli(date.getTime());
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        return ldt;
    }

    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.toInstant(ZoneOffset.UTC));
    }

}
