package pl.bpiatek.marinemonitoring.openweather.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Created by Bartosz Piatek on 04/12/2021
 */
@Getter
public class OpenWeatherResponse {

  @JsonProperty("main")
  Main main;
  @JsonProperty("clouds")
  Clouds clouds;

  @Getter
  public static class Clouds {
    String all;
  }

  @Getter
  public static class Main {
    Double temp;
    Integer pressure;
  }
}
