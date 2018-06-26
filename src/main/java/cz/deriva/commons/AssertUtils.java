package cz.deriva.commons;

/**
 * <p>Utility class pro validaci argumentu.</p>
 *
 * @author Jiri Nemec
 */
public final class AssertUtils {

    private AssertUtils() {
    }

    public static <T> T notNull(final T reference, final String parameterName) {
        if (reference == null) {
            throw new IllegalArgumentException(String.format("[Assertion failed] - argument %s is required; it must not be null", parameterName));
        }
        return reference;
    }

    public static void isTrue(final boolean expression, final String paramName) {
        if (!expression) {
            throw new IllegalArgumentException(String.format("[Assertion failed] - argument %s must be true", paramName));
        }
    }

}
