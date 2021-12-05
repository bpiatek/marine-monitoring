package pl.bpiatek.marinemonitoring.positionstack.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.bpiatek.marinemonitoring.positionstack.api.CitiesResponse;
import pl.bpiatek.marinemonitoring.positionstack.api.PositionstackCityResponse;

/**
 * Created by Bartosz Piatek on 01/12/2021
 */
@Component
@RequiredArgsConstructor
public class PositionstackFacade {

  @Value("${positionstack.accesskey}")
  private String accessKey;
  private final PositionstackFeignClient feignClient;

  public PositionstackCityResponse getCityCoordinates(String city) {
    CitiesResponse cities = feignClient.getCityCoordinates(accessKey, city).getBody();
    if(cityExist(cities)) {
      return cities.getData().get(0);
    }

    return PositionstackCityResponse.empty();
  }

  private boolean cityExist(CitiesResponse cities) {
    return cities != null && !cities.getData().isEmpty();
  }
}
