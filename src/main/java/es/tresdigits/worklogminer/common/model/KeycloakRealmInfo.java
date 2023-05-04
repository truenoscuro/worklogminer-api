package es.tresdigits.worklogminer.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class KeycloakRealmInfo {

  private String realm;
  @JsonProperty("public_key")
  private String publicKey;
  @JsonProperty("token-service")
  private String tokenService;
  @JsonProperty("account-service")
  private String accountService;
  @JsonProperty("tokens-not-before")
  private Integer tokensNotBefore;
}