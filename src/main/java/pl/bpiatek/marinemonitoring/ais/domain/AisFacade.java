package pl.bpiatek.marinemonitoring.ais.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import pl.bpiatek.marinemonitoring.ais.api.openposition.VesselApiResponse;
import pl.bpiatek.marinemonitoring.ais.api.openposition.VesselGuiObject;
import pl.bpiatek.marinemonitoring.ais.api.token.AisTokenResponse;
import pl.bpiatek.marinemonitoring.positionstack.api.PositionstackCityResponse;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Bartosz Piatek on 30/11/2021
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AisFacade {

  @Value("${extend.area.by.n.degrees}")
  private double extendArea;
  private final AisTokenProvider tokenProvider;
  private final AisTokenRepository aisTokenRepository;
  private final AisApiFeignClient aisApiFeignClient;
  private final VesselApiToGuiObjectMapper vesselApiToGuiObjectMapper;

  public void obtainAndSaveToken() {
    AisTokenResponse aisTokenResponse = tokenProvider.provideToken();
    aisTokenRepository.save(AisToken.formResponse(aisTokenResponse));
    log.info("Access token for AIS obtained and saved.");
  }

  public String getValidToken() {
    AisTokenResponse tokenResponse = aisTokenRepository.findTopByOrderByCreatedAtDesc().toResponse();
    return tokenResponse.getTokenType() + " " + tokenResponse.getAccessToken();
  }

  public List<VesselGuiObject> getVesselsInArea(PositionstackCityResponse city) {
    ResponseEntity<List<VesselApiResponse>> vesselsInArea = aisApiFeignClient.getVesselsInArea(
        city.getLongitude() - extendArea,
        city.getLongitude() + extendArea,
        city.getLatitude() - extendArea,
        city.getLatitude() + extendArea,
          getValidToken()
      );

    return vesselsInArea.getBody().stream()
        .map(vesselApiToGuiObjectMapper::map)
        .collect(Collectors.toList());
  }
}
