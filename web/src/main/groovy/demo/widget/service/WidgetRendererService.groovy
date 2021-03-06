package demo.widget.service

import de.neuland.jade4j.JadeConfiguration
import de.neuland.jade4j.template.JadeTemplate
import demo.widget.ComposableWidget
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

    def template = jadeConfiguration.getTemplate("widgets/$widget.widgetId/$device")

    renderEnvironment.usedIds.addAll(widget.contentIdReferences)
    renderEnvironment.usedIds << widget.contentId

    RenderContextHolder.setRenderEnvironment(renderEnvironment)

    if (RenderContextHolder.isESI()) {
      if (widget instanceof ComposableWidget) {
        renderComposableWidget(widget, params, model, request, response, template)
      } else {

        params.remove("__render_mode")
        params.remove("__device")

        def uriComponents = MvcUriComponentsBuilder.fromMethodName(WidgetController, "widget", widget.widgetId, widget.contentId, model, request, response, params).host(hostname).queryParams(params).build()
        def uriString = uriComponents.toUriString()

        "<esi:include src=\"${uriString}\"/>"
      }

    } else {
      if (widget instanceof ComposableWidget) {
        renderComposableWidget(widget, params, model, request, response, template)
      } else {
        jadeConfiguration.renderTemplate(template, widget.model)
      }
    }
  }

  private String renderComposableWidget(ComposableWidget widget, MultiValueMap params, Model model, HttpServletRequest request, HttpServletResponse response, JadeTemplate template) {
    def modelWidget = [:]
    widget.widgets.each { id, value ->
      modelWidget.put(id, render(value, params, model, request, response))
    }

    jadeConfiguration.renderTemplate(template, [properties: modelWidget])
  }
}
