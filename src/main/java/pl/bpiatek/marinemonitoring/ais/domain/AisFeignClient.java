package pl.bpiatek.marinemonitoring.ais.domain;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.bpiatek.marinemonitoring.ais.api.AisTokenResponse;

import java.util.Map;

/**
 * Created by Bartosz Piatek on 29/11/2021
 */

@FeignClient(name = "aisclient", url = "https://id.barentswatch.no/")
interface AisFeignClient {

  @PostMapping(value = "connect/token", consumes = APPLICATION_FORM_URLENCODED_VALUE)
  ResponseEntity<AisTokenResponse> getToken(@RequestBody Map<String, ?> form);
}
