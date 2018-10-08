package cz.deriva.commons;

/**
 * <p>Utility class pro validaci argumentu.</p>
 *
 * @author Jiri Nemec
 */
public final class AssertUtils {

  private AssertUtils() {
  }

  public static String emailAddress(final String reference, final String parameterName) {
    AssertUtils.notNull(reference, parameterName);
    if (!ValidationUtils.isValidEmail(reference)) {
      throw new IllegalArgumentException(String.format("[Assertion failed] - argument %s is required; it must be valid e-mail address", parameterName));
    }
    return reference;
  }

  public static String notBlank(final String reference, final String parameterName) {
    AssertUtils.notNull(reference, parameterName);
    if (StringUtils.isBlank(reference)) {
      throw new IllegalArgumentException(String.format("[Assertion failed] - argument %s is required; it must not be blank", parameterName));
    }
    return reference;
  }

  public static void validState(final boolean expression, final String message) {
    if (!expression) {
      throw new IllegalArgumentException(String.format("[Assertion failed] - the validated state is false; %s", message));
    }
  }

  public static <T> T notNull(final T reference) {
    if (reference == null) {
      throw new IllegalArgumentException("[Assertion failed] - argument is required; it must not be null");
    }
    return reference;
  }


  public static <T> T notNull(final T reference, final String parameterName) {
    if (reference == null) {
      throw new IllegalArgumentException(String.format("[Assertion failed] - argument %s is required; it must not be null", parameterName));
    }
    return reference;
  }

  public static void isGreaterThanZero(final Long number, final String paramName) {
    AssertUtils.notNull(number, "number");
    if (number <= 0) {
      throw new IllegalArgumentException(String.format("[Assertion failed] - argument %s must be greater than zero", paramName));
    }
  }

  public static void isTrue(final boolean expression, final String paramName) {
    if (!expression) {
      throw new IllegalArgumentException(String.format("[Assertion failed] - argument %s must be true", paramName));
    }
  }

}
