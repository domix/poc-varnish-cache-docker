package demo.widget.service

import demo.widget.Widget
import demo.widget.WidgetComponent
import gex.commons.exception.ObjectNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Service
import org.springframework.ui.Model
import org.springframework.util.MultiValueMap

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by domix on 1/4/15.
 */
@Service
class WidgetService {
  @Autowired
  ApplicationContext applicationContext
  @Autowired
  WidgetRendererService widgetRendererService

  Widget getWidget(String id, String contentId, MultiValueMap params) {
    def widgetComponents = applicationContext.getBeansOfType(WidgetComponent)

    def widgetComponent = widgetComponents.entrySet().stream().filter({ it.value.id() == id }).map({
      it.value
    }).findFirst().orElseThrow({
      throw new ObjectNotFoundException(id, 'widget')
    })

    def model = widgetComponent.model(id, contentId, params)

    new Widget(widgetId: id, contentId: contentId, model: model)
  }

  String render(String widgetId, String contentId, MultiValueMap params, Model model, HttpServletRequest request, HttpServletResponse response) {
    Widget widget = getWidget(widgetId, contentId, params)
    widgetRendererService.render(widget, params, model, request, response)
  }
}
