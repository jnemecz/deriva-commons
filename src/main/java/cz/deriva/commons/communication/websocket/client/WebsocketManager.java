package cz.deriva.commons.communication.websocket.client;

import cz.deriva.commons.utils.AssertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;
import java.util.Map;

/**
 * Sdruzuje sockety a udrzuje je aktivni, vsechny sockety, ktere majit byt aktivni, musi byt zde registrovany.
 *
 * @author Jiri Nemec
 */
public final class WebsocketManager {

  final static Logger LOGGER = LoggerFactory.getLogger(WebsocketManager.class);

  private final Map<AbstractWebsocketClientFactory, AbstractSpringWebsocketClient> clients;

  public WebsocketManager() {
    this.clients = new HashMap<>();
  }

  /**
   * Registruje factory pro vytvoreni websocket spojeni (klienta).
   *
   * @param factory
   */
  public void register(final AbstractWebsocketClientFactory factory) {
    AssertUtils.notNull(factory, "factory");
    if (clients.keySet().contains(factory)) {
      throw new IllegalStateException(String.format("Factory je jiz registrovana: %s", factory.getClass().getCanonicalName()));
    }
    this.clients.put(factory, null);
    LOGGER.info("Registrovana factory: {}", factory.getClass().getName());
  }

  /**
   * Periodicky kontroluje vsechny klienty, a v pripade potreby obnovuje spojeni.
   */
  @Scheduled(fixedRate = 1000)
  public void check() {

    for (Map.Entry<AbstractWebsocketClientFactory, AbstractSpringWebsocketClient> entry : clients.entrySet()) {

      // Websocket zatim neni vytvoreni nebo spojeni neni aktivni
      if (!this.isWebsocketOpen(entry.getValue())) {

        LOGGER.warn("Client neni aktivni, zapinam: {}", entry.getKey().getClass().getName());

        final AbstractSpringWebsocketClient client = entry.getKey().getClient();
        client.connect();
        entry.setValue(client);

        LOGGER.info("Client zapnut: {}", entry.getKey().getClass().getName());

      }

    }

  }

  /**
   * Kontroluje, jestli je dane websocket spojeni aktivni.
   *
   * @param websocketClient
   * @return
   */
  private boolean isWebsocketOpen(final AbstractSpringWebsocketClient websocketClient) {
    return (websocketClient != null && websocketClient.isOpen());
  }

}