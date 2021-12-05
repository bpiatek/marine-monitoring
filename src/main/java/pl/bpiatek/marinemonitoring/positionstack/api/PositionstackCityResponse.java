package pl.bpiatek.marinemonitoring.positionstack.api;

import lombok.Value;

/**
 * Created by Bartosz Piatek on 01/12/2021
 */
@Value
public class PositionstackCityResponse {
  Double latitude;
  Double longitude;
  String name;
  String country;
  String continent;
  String error;

  public static PositionstackCityResponse withError(String error) {
    return new PositionstackCityResponse(0.0, 0.0, "", "", "", error);
  }
}
