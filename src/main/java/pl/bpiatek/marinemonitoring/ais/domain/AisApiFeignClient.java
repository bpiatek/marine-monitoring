package pl.bpiatek.marinemonitoring.ais.domain;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import pl.bpiatek.marinemonitoring.ais.api.openposition.VesselApiResponse;

import java.util.List;

/**
 * Created by Bartosz Piatek on 30/11/2021
 */
@FeignClient(name = "aisapiclient", url = "${ais.api.url}")
interface AisApiFeignClient {

  @GetMapping(value = "v2/geodata/ais/openpositions")
  ResponseEntity<List<VesselApiResponse>> getVesselsInArea(@RequestParam(name = "Xmin") Double minLongitude,
                                                           @RequestParam(name = "Xmax") Double maxLongitude,
                                                           @RequestParam(name = "Ymin") Double minLatitude,
                                                           @RequestParam(name = "Ymax") Double maxLatitude,
                                                           @RequestHeader(value = "Authorization") String authorizationHeaderValue);
}
