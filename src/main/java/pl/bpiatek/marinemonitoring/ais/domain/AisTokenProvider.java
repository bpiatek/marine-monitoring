package pl.bpiatek.marinemonitoring.ais.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.bpiatek.marinemonitoring.ais.api.token.AisTokenResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bartosz Piatek on 29/11/2021
 */
@Component
@RequiredArgsConstructor
class AisTokenProvider {

  private final AisProperties aisProperties;
  private final AisTokenFeignClient aisTokenFeignClient;

  AisTokenResponse provideToken() {
    Map<String, String> params = new HashMap<>();
    params.put("client_id", aisProperties.getClientId());
    params.put("scope", "api");
    params.put("client_secret", aisProperties.getClientSecret());
    params.put("grant_type", aisProperties.getGrantType());
    return aisTokenFeignClient.getToken(params).getBody();
  }
}
