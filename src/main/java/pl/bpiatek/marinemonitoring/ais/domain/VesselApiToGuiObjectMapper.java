package pl.bpiatek.marinemonitoring.ais.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.bpiatek.marinemonitoring.ais.api.openposition.VesselApiResponse;
import pl.bpiatek.marinemonitoring.ais.api.openposition.VesselGuiObject;

/**
 * Created by Bartosz Piatek on 01/12/2021
 */
@Component
@RequiredArgsConstructor
class VesselApiToGuiObjectMapper {

  private final ShipTypeProvider shipTypeProvider;

  VesselGuiObject map(VesselApiResponse apiResponse) {
    return VesselGuiObject.builder()
        .maritimeMobileServiceIdentity(apiResponse.getMaritimeMobileServiceIdentity())
        .longitude(apiResponse.getGeometry().getLongitude())
        .latitude(apiResponse.getGeometry().getLatitude())
        .country(apiResponse.getCountry())
        .name(apiResponse.getName())
        .shipType(shipTypeProvider.getShipTypeName(apiResponse.getShipType()))
        .destination(apiResponse.getDestination() == null ? "" : apiResponse.getDestination())
        .build();
  }
}
