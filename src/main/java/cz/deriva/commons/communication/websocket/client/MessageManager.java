package cz.deriva.commons.communication.websocket.client;

import cz.deriva.commons.communication.websocket.dto.WebsocketMessage;

/**
 * Slouzi pro zpracovani prijatych message z burzy pro dalsi zpracovani v systemu.
 *
 * @author Jiri Nemec
 */
public interface MessageManager<T extends WebsocketMessage> {

  /**
   * Zpracovava zpravu, ktera byla prijata z websocketu.
   *
   * @param message zprava z websocketu
   */
  void receive(final T message);

}
