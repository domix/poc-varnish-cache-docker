package demo.controller

import demo.PropertyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

/**
 * Created by domix on 08/10/14.
 */
@Controller
class HomeController {
  @Autowired
  PropertyService propertyService

  @RequestMapping('/')
  String index(Model model, @RequestParam(value = '__device', defaultValue = "desktop") String device) {
    model.addAttribute 'properties', propertyService.findProperties()
    model.addAttribute '__device', device
    //println model.dump()
    'index'
  }


}