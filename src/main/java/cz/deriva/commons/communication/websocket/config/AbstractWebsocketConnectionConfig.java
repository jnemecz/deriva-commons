package cz.deriva.commons.communication.websocket.config;

import cz.deriva.commons.utils.AssertUtils;

/**
 * Zaklad pro nastaveni websocket clienta.
 *
 * @author Jiri Nemec
 */
public abstract class AbstractWebsocketConnectionConfig {

  private final String url;

  public AbstractWebsocketConnectionConfig(final String url) {
    AssertUtils.notBlank(url,"url");
    this.url = url;
  }

  public String getUrl() {
    return url;
  }

}
