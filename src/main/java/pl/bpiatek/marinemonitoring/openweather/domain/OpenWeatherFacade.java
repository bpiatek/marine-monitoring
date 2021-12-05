package pl.bpiatek.marinemonitoring.openweather.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import pl.bpiatek.marinemonitoring.openweather.api.OpenWeatherResponse;

/**
 * Created by Bartosz Piatek on 04/12/2021
 */
@Component
@RequiredArgsConstructor
public class OpenWeatherFacade {
  @Value("${openweather.units}")
  private String weatherUnits;

  @Value("${openweather.accesskey}")
  private String accessKey;

  private final OpenWeatherFeignClient openWeatherFeignClient;

  public OpenWeatherResponse getWeatherForCoordinates(Double longitude, Double latitude) {
    final ResponseEntity<OpenWeatherResponse> weatherByCoordinates = openWeatherFeignClient.getWeatherByCoordinates(
        weatherUnits,
        longitude,
        latitude,
        accessKey
    );

    return weatherByCoordinates.getBody();
  }

}
