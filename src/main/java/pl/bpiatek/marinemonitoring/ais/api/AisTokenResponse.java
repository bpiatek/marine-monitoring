package pl.bpiatek.marinemonitoring.ais.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Created by Bartosz Piatek on 29/11/2021
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AisTokenResponse {
  @JsonProperty("access_token")
  String accessToken;
  @JsonProperty("token_type")
  String tokenType;
  @JsonProperty("expires_in")
  String expiresIn;
}
