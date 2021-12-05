package pl.bpiatek.marinemonitoring.ais.api.openposition;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bartosz Piatek on 30/11/2021
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VesselApiResponse {

  @JsonProperty("timeStamp")
  String timeStamp;
  @JsonProperty("mmsi")
  Integer maritimeMobileServiceIdentity;
  @JsonProperty("geometry")
  Geometry geometry;
  @JsonProperty("shipType")
  String shipType;
  @JsonProperty("name")
  String name;
  @JsonProperty("imo")
  Integer internationalMaritimeOrganization;
  @JsonProperty("callsign")
  String callsign;
  @JsonProperty("country")
  String country;
  @JsonProperty("eta")
  String estimatedShipArrival;
  @JsonProperty("destination")
  String destination;
  @JsonProperty("isSurvey")
  Boolean isSurvey;
  @JsonProperty("heading")
  Integer heading;
  @JsonProperty("draught")
  Double draught;

  @NoArgsConstructor
  @AllArgsConstructor
  public static class Geometry {
    String type;
    @JsonProperty("coordinates")
    List<Double> coordinates = new ArrayList<>();

    public String getType() {
      return type;
    }

    public Double getLongitude() {
      if(!coordinates.isEmpty()) {
        return coordinates.get(0);
      }
      return 0.0;
    }

    public Double getLatitude() {
      if (coordinates.size() > 1) {
        return coordinates.get(1);
      }
      return 0.0;
    }
  }
}
