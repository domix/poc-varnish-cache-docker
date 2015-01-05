package demo.widget.service

import de.neuland.jade4j.JadeConfiguration
import demo.widget.Widget
import demo.widget.controller.WidgetController
import demo.widget.render.RenderContextHolder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.ui.Model
import org.springframework.util.MultiValueMap
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import static WidgetController.MARKUP

/**
 * Created by domix on 1/4/15.
 */
@Service
class WidgetRendererService {
  @Autowired
  JadeConfiguration jadeConfiguration

  @Value('${app.host}')
  String hostname

  String render(Widget widget, MultiValueMap params, Model model, HttpServletRequest request, HttpServletResponse response) {

    def renderEnvironment = RenderContextHolder.renderEnvironment
    String device = renderEnvironment.device
    String renderMode = renderEnvironment.renderMode

    if (renderMode == MARKUP) {
      def template = jadeConfiguration.getTemplate("widgets/$widget.widgetId/$device")
      jadeConfiguration.renderTemplate(template, widget.model)
    } else {
      def uriComponents = MvcUriComponentsBuilder.fromMethodName(WidgetController, "widget", widget.widgetId, widget.contentId, model, request, response, params).host(hostname).queryParams(params).build()
      def uriString = uriComponents.toUriString()

      "<esi:include src=\"${uriString}\"/>"
    }
  }
}
