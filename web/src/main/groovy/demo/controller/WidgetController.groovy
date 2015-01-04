package demo.controller

import demo.PropertyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

import javax.servlet.http.HttpServletResponse

/**
 * Created by domix on 1/4/15.
 */
@Controller
class WidgetController {
  public static final String DEVICE = '__device'
  @Autowired
  PropertyService propertyService

  @RequestMapping('/widget/{widgetId}/{id}')
  String widget(
    @PathVariable String widgetId,
    @PathVariable String id,
    @RequestParam(value = '__device', defaultValue = 'desktop') String device,
    @RequestParam(value = '__render_mode', defaultValue = 'markup') String renderMode, Model model, HttpServletResponse response) {

    model.addAttribute('property', propertyService.getById(id))
    model.addAttribute(DEVICE, device)
    model.addAttribute('__render_mode', renderMode)

    response.addHeader('X-Article-id', id)

    "widgets/$widgetId/$device"
  }
}
