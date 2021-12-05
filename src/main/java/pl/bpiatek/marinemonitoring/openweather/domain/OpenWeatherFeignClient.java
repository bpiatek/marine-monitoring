package pl.bpiatek.marinemonitoring.openweather.domain;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.bpiatek.marinemonitoring.openweather.api.OpenWeatherResponse;

/**
 * Created by Bartosz Piatek on 04/12/2021
 */
@FeignClient(name = "openweatherclient", url = "${openweather.url}")
interface OpenWeatherFeignClient {

  @GetMapping("weather")
  ResponseEntity<OpenWeatherResponse> getWeatherByCoordinates(@RequestParam(name = "units", defaultValue = "metric") String units,
                                                             @RequestParam("lon") Double longitude,
                                                             @RequestParam("lat") Double latitude,
                                                             @RequestParam("appid") String accessToken);
}
