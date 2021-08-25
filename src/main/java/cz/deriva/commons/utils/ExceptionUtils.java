package cz.deriva.commons.utils;

import java.util.StringJoiner;

public final class ExceptionUtils {

  public static String dumpErrorInfo(final Exception e) {

    if (e == null) {
      return "";
    }

    final String msg = e.getMessage();

    final StringJoiner joiner = new StringJoiner(", ");
    joiner.add("type=" + e.getClass().getCanonicalName());
    joiner.add("msg=" + (StringUtils.isBlank(msg) ? "NA" : msg));

    return "[" + joiner.toString() + "]";

  }

}