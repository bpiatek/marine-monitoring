package pl.bpiatek.marinemonitoring.ais.api.openposition;

import lombok.*;

/**
 * Created by Bartosz Piatek on 01/12/2021
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class VesselGuiObject {
  Integer maritimeMobileServiceIdentity;
  Double longitude;
  Double latitude;
  String country;
  String name;
  String shipType;
  String destination;
}
