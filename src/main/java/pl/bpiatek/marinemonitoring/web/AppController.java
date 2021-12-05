package pl.bpiatek.marinemonitoring.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.bpiatek.marinemonitoring.ais.api.openposition.VesselGuiObject;
import pl.bpiatek.marinemonitoring.ais.domain.AisFacade;
import pl.bpiatek.marinemonitoring.openweather.api.OpenWeatherResponse;
import pl.bpiatek.marinemonitoring.openweather.domain.OpenWeatherFacade;
import pl.bpiatek.marinemonitoring.positionstack.api.PositionstackCityResponse;
import pl.bpiatek.marinemonitoring.positionstack.domain.PositionstackFacade;

import java.util.List;

/**
 * Created by Bartosz Piatek on 30/11/2021
 */
@Controller
@RequiredArgsConstructor
class AppController {

  private final AisFacade aisFacade;
  private final PositionstackFacade positionstackFacade;
  private final OpenWeatherFacade openWeatherFacade;

  @GetMapping
  public String index(Model model, @RequestParam(defaultValue = "Trondheim") String city) {
    PositionstackCityResponse cityCoordinates = positionstackFacade.getCityCoordinates(city);
    if (cityCoordinates.getError() != null) {
      prepareModelOnError(model, city, cityCoordinates.getError());
    } else {
      List<VesselGuiObject> vessels = aisFacade.getVesselsInArea(cityCoordinates);
      OpenWeatherResponse weather = openWeatherFacade.getWeatherForCoordinates(
          cityCoordinates.getLongitude(),
          cityCoordinates.getLatitude()
      );
      prepareModelOnSuccess(model, vessels, cityCoordinates, weather);
    }

    return "index";
  }

  private void prepareModelOnError(Model model, String city, String errorMessage) {
    model.addAttribute("errorMessage", errorMessage);
    model.addAttribute("error", "true");
    model.addAttribute("cityName", city);
  }

  private void prepareModelOnSuccess(
      Model model,
      List<VesselGuiObject> vessels,
      PositionstackCityResponse cityCoordinates,
      OpenWeatherResponse weather
  ) {
    model.addAttribute("error", "false");
    model.addAttribute("vessels", vessels);
    model.addAttribute("city", cityCoordinates);
    model.addAttribute("weather", weather);
  }
}
