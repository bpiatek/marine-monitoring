package pl.bpiatek.marinemonitoring.ais.api.token;

import lombok.Value;

/**
 * Created by Bartosz Piatek on 29/11/2021
 */
@Value
public class AisTokenRequest {
  String grantType;
  String clientId;
  String clientSecret;
  String scope = "api";
}
