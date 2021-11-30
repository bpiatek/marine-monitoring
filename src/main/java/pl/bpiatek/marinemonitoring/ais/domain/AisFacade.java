package pl.bpiatek.marinemonitoring.ais.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.bpiatek.marinemonitoring.ais.api.AisTokenResponse;

/**
 * Created by Bartosz Piatek on 30/11/2021
 */
@Component
@RequiredArgsConstructor
public class AisFacade {

  private final AisTokenProvider tokenProvider;
  private final AisTokenRepository aisTokenRepository;

  public void generateAndSaveToken() {
    AisTokenResponse aisTokenResponse = tokenProvider.provideToken();
    aisTokenRepository.save(AisToken.formResponse(aisTokenResponse));
  }

  public AisTokenResponse getValidToken() {
    return aisTokenRepository.findTopByOrderByCreatedAtDesc().toResponse();
  }
}
