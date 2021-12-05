package pl.bpiatek.marinemonitoring.positionstack.domain;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.bpiatek.marinemonitoring.positionstack.api.CitiesResponse;

/**
 * Created by Bartosz Piatek on 01/12/2021
 */
@FeignClient(name = "positionstackapiclient", url = "${positionstack.url}")
interface PositionstackFeignClient {

  @GetMapping("forward")
  ResponseEntity<CitiesResponse> getCityCoordinates(@RequestParam("access_key") String accessKey,
                                                     @RequestParam("query") String city);
}
