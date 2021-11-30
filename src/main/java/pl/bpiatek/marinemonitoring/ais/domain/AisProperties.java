package pl.bpiatek.marinemonitoring.ais.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Bartosz Piatek on 29/11/2021
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "ais")
class AisProperties {
  private String grantType;
  private String clientId;
  private String clientSecret;
}
