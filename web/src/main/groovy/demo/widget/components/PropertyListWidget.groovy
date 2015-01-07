package demo.widget.components

import demo.service.PropertyService
import demo.widget.ComposableWidget
import demo.widget.Widget
import demo.widget.WidgetComponent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.MultiValueMap

/**
 * Created by domix on 1/4/15.
 */
@Service
class PropertyListWidget implements WidgetComponent {
  @Autowired
  PropertyService propertyService

  @Autowired
  PropertyDetailWidget propertyDetailWidget

  @Override
  Widget build(String widgetId, String contentId, MultiValueMap params) {

    def properties = propertyService.findProperties()
    def model = [contentId: contentId]

    ComposableWidget propertyList = new ComposableWidget(widgetId: widgetId, contentId: contentId, model: model, params: params)

    properties.each {
      def widget = propertyDetailWidget.build('propertyDetail', it.id, params)
      propertyList.addWidget("property_${it.id}", widget)
    }

    propertyList.addContentIdReference(contentId)
  }

  @Override
  String id() {
    'propertyList'
  }
}
