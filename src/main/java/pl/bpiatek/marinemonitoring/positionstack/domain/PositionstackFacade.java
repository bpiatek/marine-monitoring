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
  @Value("${city.not.exist.error}")
  private String cityNotExistError;
  @Value("${city.not.enough.letters}")
  private String notEnoughLettersError;
  private final PositionstackFeignClient feignClient;

  public PositionstackCityResponse getCityCoordinates(String city) {
    if(city.length() < 3) {
      return PositionstackCityResponse.withError(notEnoughLettersError);
    }

    CitiesResponse cities = feignClient.getCityCoordinates(accessKey, city).getBody();
    if(cityExist(cities)) {
      return cities.getData().get(0);
    }
    return PositionstackCityResponse.withError(cityNotExistError);
  }

  private boolean cityExist(CitiesResponse cities) {
    return cities != null && !cities.getData().isEmpty();
  }
}
