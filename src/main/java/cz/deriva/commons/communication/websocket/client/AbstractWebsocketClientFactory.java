package cz.deriva.commons.communication.websocket.client;

public abstract class AbstractWebsocketClientFactory {

  /**
   * Vytvari websocket klienta daneho typu.
   *
   * @return inicializovany objekt websocket clienta
   */
  public abstract AbstractSpringWebsocketClient getClient();

}
