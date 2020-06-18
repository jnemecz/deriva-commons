package cz.deriva.commons.communication.websocket.client;

import cz.deriva.commons.communication.websocket.dto.WebsocketMessage;
import cz.deriva.commons.communication.websocket.dto.WebsocketMessageReceivedEvent;
import cz.deriva.commons.utils.AssertUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationEventPublisher;

/**
 * Implementace message managera, ktera prijate zpravy z websocketu posila jako systemovou eventu naslouchajicim listenerum.
 */
public class SystemEventMessageManager<T extends WebsocketMessage> implements MessageManager<T> {

  private static Logger LOGGER = LogManager.getLogger(SystemEventMessageManager.class);

  private final ApplicationEventPublisher eventPublisher;

  public SystemEventMessageManager(final ApplicationEventPublisher eventPublisher) {
    AssertUtils.notNull(eventPublisher, "eventPublisher");
    this.eventPublisher = eventPublisher;
  }

  @Override
  public void receive(final T message) {
    AssertUtils.notNull(message, "message");
    eventPublisher.publishEvent(new WebsocketMessageReceivedEvent(message));
  }

}
