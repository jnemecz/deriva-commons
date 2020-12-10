package cz.deriva.commons.utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by jirka on 20.06.18.
 *
 * @author Jiri Nemec
 */
public class HttpUtils {

  private static final String[] HEADERS_TO_TRY = {
      "X-Forwarded-For",
      "Proxy-Client-IP",
      "WL-Proxy-Client-IP",
      "HTTP_X_FORWARDED_FOR",
      "HTTP_X_FORWARDED",
      "HTTP_X_CLUSTER_CLIENT_IP",
      "HTTP_CLIENT_IP",
      "HTTP_FORWARDED_FOR",
      "HTTP_FORWARDED",
      "HTTP_VIA",
      "REMOTE_ADDR"};

  private HttpUtils() {
  }

  public static String getClientIpAddress(final HttpServletRequest request) {

    for (String header : HEADERS_TO_TRY) {
      String ip = request.getHeader(header);
      if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
        return ip;
      }
    }

    return request.getRemoteAddr();
  }

  public static String getRequestUri(final HttpServletRequest request) {

    final String uri = (String) request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI);

    if (!StringUtils.isBlank(uri)) {
      return uri;
    } else {
      return request.getRequestURI();
    }

  }

}
