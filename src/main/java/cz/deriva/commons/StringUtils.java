package cz.deriva.commons;

/**
 * Created by jirka on 12.07.18.
 *
 * @author Jiri Nemec
 */
public final class StringUtils {

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

    public static boolean isBlank(final CharSequence cs) {

        if (StringUtils.isEmpty(cs)) {
            return true;
        }

        final String testValue = new String(cs.toString()).trim();

        return testValue.length() == 0;

    }

    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }


}
