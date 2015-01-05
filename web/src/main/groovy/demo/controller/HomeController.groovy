package demo.controller

import demo.widget.service.WidgetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by domix on 08/10/14.
 */
@Controller
class HomeController {
  @Autowired
  WidgetService widgetService

  @RequestMapping('/')
  String index(
    Model model, HttpServletRequest request, HttpServletResponse response,
    @RequestParam MultiValueMap params) {

    def renderedWidget = widgetService.render('propertyList', '1', params, model, request, response)
    model.addAttribute('widget', renderedWidget)

    'index'
  }


}