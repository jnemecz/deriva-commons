package cz.deriva.commons;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import java.time.*;
import java.time.temporal.TemporalField;
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
    class ToDate {

        @Test
        public void test1() {

            Date dd = DateTimeUtils.toDate(DateTimeUtils.actualDateTime());

            Calendar cal = Calendar.getInstance();
            cal.setTime(dd);

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


    class ToUtcLocalDateTime {

        @Test
        public void test1() {

            Date ddd = Date.from(DateTimeUtils.actualDateTime().toInstant(ZoneOffset.UTC));
            LocalDateTime dd = DateTimeUtils.toUtcLocalDateTime(ddd);

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