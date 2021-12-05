package pl.bpiatek.marinemonitoring.positionstack.api;

import lombok.*;

import java.util.List;

/**
 * Created by Bartosz Piatek on 01/12/2021
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CitiesResponse {
  private List<PositionstackCityResponse> data;
}
