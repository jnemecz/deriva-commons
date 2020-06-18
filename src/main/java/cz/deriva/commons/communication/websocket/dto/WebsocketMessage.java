package cz.deriva.commons.communication.websocket.dto;

import cz.deriva.commons.utils.AssertUtils;

/**
 * DTO urcene k prenaseni zprav prijatych z websocketu.
 */
public class WebsocketMessage<T extends WebsocketMessageType> {

  /**
   * Urcuje typ prijate zpravy.
   */
  private final T type;

  /**
   * Obsah zpravy.
   */
  private final String message;

  public WebsocketMessage(final T type, final String message) {
    AssertUtils.notNull(type, "type");
    this.type = type;
    this.message = message;
  }

  public T getType() {
    return type;
  }

  public String getMessage() {
    return message;
  }

}

