package pl.bpiatek.marinemonitoring.ais.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Bartosz Piatek on 04/12/2021
 */
@Slf4j
@Component
@RequiredArgsConstructor
class ShipTypeProvider {
  private final ConcurrentHashMap<String, String> shipTypes = new ConcurrentHashMap<>();

  private final ShipTypeRepository shipTypeRepository;

  String getShipTypeName(String apiNumber) {
    if(shipTypes.isEmpty()) {
      populateCache();
    }

    return shipTypes.getOrDefault(apiNumber, "");
  }

  private void populateCache() {
    shipTypeRepository.findAll()
        .forEach(shipType -> shipTypes.put(shipType.getNumber(), shipType.getValue()));
    log.info("Ship types stored in cache.");
  }
}
