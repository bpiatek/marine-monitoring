package pl.bpiatek.marinemonitoring.ais;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Bartosz Piatek on 30/11/2021
 */
@Controller
class AisController {

  @GetMapping
  public String dupa(Model model) {

    model.addAttribute("test", "cos tu przekazuje");

    return "index";
  }
}
