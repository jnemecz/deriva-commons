package cz.deriva.commons.communication.websocket.client;


import cz.deriva.commons.communication.websocket.config.AbstractWebsocketConnectionConfig;
import cz.deriva.commons.utils.AssertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;


/**
 * Zakladni trida pro vytvoreni konkretni implementace Spring websocket klienta.
 *
 * @param <T> urcuje typ konfigurace websocket klienta
 */
public abstract class AbstractSpringWebsocketClient<T extends AbstractWebsocketConnectionConfig> {

  final static Logger LOGGER = LoggerFactory.getLogger(AbstractSpringWebsocketClient.class);

  private final T config;
  private WebSocketSession webSocketSession;
  private final AbstractSpringWebsocketHandler handler;

  public AbstractSpringWebsocketClient(final T config, final AbstractSpringWebsocketHandler handler) {

    AssertUtils.notNull(config, "config");
    this.config = config;

    AssertUtils.notNull(handler, "handler");
    this.handler = handler;

  }

  /**
   * Obsah message, ktera je poslana pri prihlaseni do channelu - primarne pro prihlaseni do channelu.
   *
   * @return
   */
  public abstract TextMessage createSubscriptionMessage();

  /**
   * URI na ktere je pristupny WS server, na ktery se klient bude pripojovat.
   *
   * @return
   */
  public abstract URI getUri();

  /**
   * Provadi pripojeni klienta na websocket.
   */
  public void connect() {

    try {

      final WebSocketClient webSocketClient = webSocketClient();
      setWebSocketSession(webSocketClient.doHandshake(handler, new WebSocketHttpHeaders(), getUri()).get());
      this.getWebSocketSession().sendMessage(createSubscriptionMessage());

    } catch (Exception e) {
      LOGGER.error("Chyba pri pristupu k websocketu: {}", e.getMessage(), e);
    }

  }

  /**
   * Vraci konfiguraci tohoto websocket klienta.
   *
   * @return konfigurace klienta
   */
  public T getConfig() {
    return config;
  }

  /**
   * Kontroluje, jestli je websocket spojeni otevrene a aktivni.
   *
   * @return true pokud je spojeni aktivni a je mozen ho pouzit, jinak false (potom provest obnovu spojeni)
   */
  public boolean isOpen() {

    if (webSocketSession == null) {
      return false;
    }

    if (!webSocketSession.isOpen()) {
      try {
        webSocketSession.close();
      } catch (IOException e) {
        // silently, please...
      }
      return false;
    }

    return true;

  }

  private WebSocketClient webSocketClient() {
    return new StandardWebSocketClient(createContainer());
  }

  private void setWebSocketSession(WebSocketSession webSocketSession) {
    this.webSocketSession = webSocketSession;
  }

  private WebSocketSession getWebSocketSession() {
    return webSocketSession;
  }

  private WebSocketContainer createContainer() {
    final WebSocketContainer container = ContainerProvider.getWebSocketContainer();
    container.setDefaultMaxTextMessageBufferSize(20971520);
    return container;
  }

}






















