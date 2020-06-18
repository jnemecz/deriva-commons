package cz.deriva.commons.communication.websocket.config;

import cz.deriva.commons.utils.AssertUtils;

/**
 * Websocket config klienta, ktery se autorizuje pomoci API key / Secret;
 */
public abstract class ApiKeyWebsocketConnectionConfig extends AbstractWebsocketConnectionConfig {

  private final String apiKey;
  private final String secret;

  public ApiKeyWebsocketConnectionConfig(final String apiKey, final String secret, final String url) {

    super(url);

    AssertUtils.notBlank(apiKey, "apiKey");
    this.apiKey = apiKey;

    AssertUtils.notBlank(secret, "secret");
    this.secret = secret;

  }

  public String getApiKey() {
    return apiKey;
  }

  public String getSecret() {
    return secret;
  }

}
