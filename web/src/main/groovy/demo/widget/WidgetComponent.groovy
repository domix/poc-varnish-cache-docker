package demo.widget

import org.springframework.util.MultiValueMap

/**
 * Created by domix on 1/4/15.
 */
interface WidgetComponent {
  Widget build(String widgetId, String contentId, MultiValueMap params)

  String id()
}