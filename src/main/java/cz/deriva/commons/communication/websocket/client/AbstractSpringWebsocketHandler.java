package cz.deriva.commons.communication.websocket.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Zaklad pro websocket handler.
 */
public abstract class AbstractSpringWebsocketHandler extends TextWebSocketHandler {

  private static Logger LOGGER = LogManager.getLogger(AbstractSpringWebsocketHandler.class);

  public abstract void handleTextMessage(final WebSocketSession session, final TextMessage message);

  @Override
  public void afterConnectionEstablished(final WebSocketSession session) {
    LOGGER.info("Established connection: {}", session);
  }

  @Override
  public void handleTransportError(final WebSocketSession session, final Throwable exception) {
    LOGGER.error(String.format("Transport error: %s", exception.getMessage()), exception);
  }

  @Override
  public void afterConnectionClosed(final WebSocketSession session, final CloseStatus closeStatus) {
    LOGGER.info("Connection closed with status: {}", closeStatus);
  }

  @Override
  public boolean supportsPartialMessages() {
    return false;
  }

}
