package demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by domix on 1/4/15.
 */
@Controller

public class HomeController {
  @RequestMapping("/")
  public String index() {
    return "index";
  }
}
