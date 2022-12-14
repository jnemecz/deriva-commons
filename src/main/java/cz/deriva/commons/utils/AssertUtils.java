package cz.deriva.commons.utils;

import java.math.BigDecimal;

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
            throw new IllegalArgumentException(
                    String.format("[Assertion failed] - argument %s is required; it must be valid e-mail address",
                            parameterName));
        }
        return reference;
    }

    public static String notBlank(final String reference, final String parameterName) {
        AssertUtils.notNull(reference, parameterName);
        if (StringUtils.isBlank(reference)) {
            raiseNotBlankError(parameterName);
        }
        return reference;
    }

    public static void raiseError(final String message) {
        throw new IllegalArgumentException(message);
    }

    public static <T extends CharSequence> T raiseNullError(final T fieldName) {
        if (StringUtils.isNotBlank(fieldName)) {
            raiseError(String.format("[Assertion failed] - argument %s must be null", fieldName));
        } else {
            raiseError("[Assertion failed] - argument must be null");
        }
        return fieldName;
    }

    public static void raiseNotNullError(final String fieldName) {
        if (StringUtils.isNotBlank(fieldName)) {
            raiseError(String.format("[Assertion failed] - argument %s is required; it must not be null", fieldName));
        } else {
            raiseError("[Assertion failed] - argument is required; it must not be null");
        }
    }

    public static void raiseNotBlankError(final String fieldName) {
        if (StringUtils.isNotBlank(fieldName)) {
            raiseError(String.format("[Assertion failed] - argument %s is required; it must not be blank", fieldName));
        } else {
            raiseError("[Assertion failed] - argument is required; it must not be blank");
        }
    }

    public static Double isGteZero(final Double value, final String parameterName) {
        if (!ValidationUtils.isGteZero(value)) {
            throw new IllegalArgumentException(
                    String.format("[Assertion failed] - argument %s is required; it must be greater or equal than 0",
                            parameterName));
        }
        return value;
    }

    public static Integer isGteZero(final Integer value, final String parameterName) {
        if (!ValidationUtils.isGteZero(value)) {
            throw new IllegalArgumentException(
                    String.format("[Assertion failed] - argument %s is required; it must be greater or equal than 0",
                            parameterName));
        }
        return value;
    }

    public static Long isGteZero(final Long value, final String parameterName) {
        if (!ValidationUtils.isGteZero(value)) {
            throw new IllegalArgumentException(
                    String.format("[Assertion failed] - argument %s is required; it must be greater or equal than 0",
                            parameterName));
        }
        return value;
    }

    public static BigDecimal isGteZero(final BigDecimal value, final String parameterName) {
        if (!ValidationUtils.isGteZero(value)) {
            throw new IllegalArgumentException(
                    String.format("[Assertion failed] - argument %s is required; it must be greater or equal than 0",
                            parameterName));
        }
        return value;
    }

    public static BigDecimal isGteOne(final BigDecimal value, final String parameterName) {
        if (!ValidationUtils.isGte(value, BigDecimal.ONE)) {
            throw new IllegalArgumentException(
                    String.format("[Assertion failed] - argument %s is required; it must be greater or equal than 1",
                            parameterName));
        }
        return value;
    }

    public static BigDecimal isGte(final BigDecimal checkValue,
                                   final BigDecimal intervalValue,
                                   final String parameterName) {
        if (!ValidationUtils.isGte(checkValue, intervalValue)) {
            throw new IllegalArgumentException(
                    String.format("[Assertion failed] - argument %s is required; it must be greater or equal than %s",
                            parameterName, intervalValue));
        }
        return checkValue;
    }

    public static void isSame(final BigDecimal value1, final BigDecimal value2, final String parameterName) {
        if (!ValidationUtils.isSame(value1, value2)) {
            throw new IllegalArgumentException(
                    String.format("[Assertion failed] - argument %s is required; it must be same to %s", parameterName,
                            value2));
        }
    }

    public static BigDecimal isLte(final BigDecimal checkValue,
                                   final BigDecimal intervalValue,
                                   final String parameterName) {
        if (!ValidationUtils.isLte(checkValue, intervalValue)) {
            throw new IllegalArgumentException(
                    String.format("[Assertion failed] - argument %s is required; it must be lower or equal than %s",
                            parameterName, intervalValue));
        }
        return checkValue;
    }

    public static BigDecimal isGt(final BigDecimal checkValue,
                                  final BigDecimal intervalValue,
                                  final String parameterName) {
        if (!ValidationUtils.isGt(checkValue, intervalValue)) {
            throw new IllegalArgumentException(
                    String.format("[Assertion failed] - argument %s is required; it must be greater or equal than %s",
                            parameterName, intervalValue));
        }
        return checkValue;
    }

    public static BigDecimal isLt(final BigDecimal checkValue,
                                  final BigDecimal intervalValue,
                                  final String parameterName) {
        if (!ValidationUtils.isLt(checkValue, intervalValue)) {
            throw new IllegalArgumentException(
                    String.format("[Assertion failed] - argument %s is required; it must be lower or equal than %s",
                            parameterName, intervalValue));
        }
        return checkValue;
    }

    public static BigDecimal isLteOne(final BigDecimal value, final String parameterName) {
        if (!ValidationUtils.isLte(value, BigDecimal.ONE)) {
            throw new IllegalArgumentException(
                    String.format("[Assertion failed] - argument %s is required; it must be lower or equal than 1",
                            parameterName));
        }
        return value;
    }

    public static Integer isGtZero(final Integer value, final String parameterName) {
        if (!ValidationUtils.isGtZero(value)) {
            throw new IllegalArgumentException(
                    String.format("[Assertion failed] - argument %s is required; it must be greater or equal than 0",
                            parameterName));
        }
        return value;
    }

    public static Long isGtZero(final Long value, final String parameterName) {
        if (!ValidationUtils.isGtZero(value)) {
            throw new IllegalArgumentException(
                    String.format("[Assertion failed] - argument %s is required; it must be greater or equal than 0",
                            parameterName));
        }
        return value;
    }

    public static BigDecimal between(final BigDecimal value,
                                     final BigDecimal fromInclusive,
                                     final BigDecimal toInclusive,
                                     final String parameterName) {

        if (!ValidationUtils.between(value, fromInclusive, toInclusive)) {
            throw new IllegalArgumentException(
                    String.format("[Assertion failed] - argument %s is not within the range", parameterName));
        }
        return value;

    }

    public static BigDecimal isGtZero(final BigDecimal value, final String parameterName) {
        if (!ValidationUtils.isGtZero(value)) {
            throw new IllegalArgumentException(
                    String.format("[Assertion failed] - argument %s is required; it must be greater than 0",
                            parameterName));
        }
        return value;
    }

    public static Double isGtZero(final Double value, final String parameterName) {
        if (!ValidationUtils.isGtZero(value)) {
            throw new IllegalArgumentException(
                    String.format("[Assertion failed] - argument %s is required; it must be greater than 0",
                            parameterName));
        }
        return value;
    }

    public static boolean validState(final boolean expression, final String message) {
        if (!expression) {
            throw new IllegalArgumentException(
                    String.format("[Assertion failed] - the validated state is false; %s", message));
        }
        return expression;
    }

    public static <T> T forceNull(final T reference) {
        if (reference != null) {
            raiseNullError(null);
        }
        return reference;
    }

    public static <T> T forceNull(final T reference, final String parameterName) {
        if (reference != null) {
            raiseNullError(parameterName);
        }
        return reference;
    }

    public static <T> T notNull(final T reference) {
        if (reference == null) {
            raiseNotNullError(null);
        }
        return reference;
    }

    public static <T> T notNull(final T reference, final String parameterName) {
        if (reference == null) {
            raiseNotNullError(parameterName);
        }
        return reference;
    }

    public static Long isGreaterThanZero(final Long number, final String paramName) {
        AssertUtils.notNull(number, "number");
        if (number <= 0) {
            throw new IllegalArgumentException(
                    String.format("[Assertion failed] - argument %s must be greater than zero", paramName));
        }
        return number;
    }

    public static boolean isTrue(final boolean expression, final String paramName) {
        if (!expression) {
            throw new IllegalArgumentException(
                    String.format("[Assertion failed] - argument %s must be true", paramName));
        }
        return expression;
    }

}
