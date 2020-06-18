package cz.deriva.commons.communication.websocket.dto;

import org.springframework.context.ApplicationEvent;

/**
 * Systemova obalky obsahujici zpravu z websocketu.
 *
 * @author Jiri Nemec
 */
public class WebsocketMessageReceivedEvent<T extends WebsocketMessage> extends ApplicationEvent
{

  public WebsocketMessageReceivedEvent(final T message) {
    super(message);
  }

}