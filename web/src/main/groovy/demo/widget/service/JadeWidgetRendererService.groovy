package demo.widget.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.jade4j.JadeHelper

/**
 * Created by domix on 1/4/15.
 */
@JadeHelper
class JadeWidgetRendererService {
  @Autowired
  WidgetRendererService widgetRendererService

  String render() {
    "widgetRendererService.render()"
  }
}
