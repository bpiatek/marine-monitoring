package pl.bpiatek.marinemonitoring.ais.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import pl.bpiatek.marinemonitoring.ais.api.token.AisTokenResponse;

import java.time.LocalDateTime;

import javax.persistence.*;

/**
 * Created by Bartosz Piatek on 30/11/2021
 */
@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
class AisToken {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(columnDefinition = "serial")
  private Long id;
  private String accessToken;
  private String tokenType;
  private String expiresIn;
  @CreationTimestamp
  private LocalDateTime createdAt;

  AisTokenResponse toResponse() {
    return AisTokenResponse.builder()
        .accessToken(this.accessToken)
        .tokenType(this.tokenType)
        .build();
  }

  static AisToken formResponse(AisTokenResponse response) {
    return new AisToken(null,
                        response.getAccessToken(),
                        response.getTokenType(),
                        response.getExpiresIn(),
                        null
    );
  }
}
