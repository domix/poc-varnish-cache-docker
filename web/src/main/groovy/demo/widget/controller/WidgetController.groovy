package demo.widget.controller

import demo.widget.service.WidgetRendererService
import demo.widget.service.WidgetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by domix on 1/4/15.
 */
@Controller
class WidgetController {
  public static final String DEVICE = '__device'
  public static final String MARKUP = 'markup'
  @Autowired
  WidgetService widgetService
  @Autowired
  WidgetRendererService widgetRendererService

  @RequestMapping('/widget/{widgetId}/{id}')
  void widget(
    @PathVariable String widgetId,
    @PathVariable String id,
    Model model, HttpServletRequest request, HttpServletResponse response,
    @RequestParam MultiValueMap params) {

    response.addHeader('X-Article-id', id)
    response.contentType = 'text/html; charset=UTF-8'

    def widgetContent = widgetService.render(widgetId, id, params, model, request, response)

    response.writer.write(widgetContent)
  }
}
