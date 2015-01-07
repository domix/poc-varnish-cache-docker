package demo.widget.components

import demo.service.PropertyService
import demo.widget.Widget
import demo.widget.WidgetComponent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.MultiValueMap

/**
 * Created by domix on 1/4/15.
 */
@Service
class PropertyDetailWidget implements WidgetComponent {
  @Autowired
  PropertyService propertyService

  @Override
  Widget build(String widgetId, String contentId, MultiValueMap params) {
    def model = [property: propertyService.getById(contentId)]

    new Widget(contentId: contentId, model: model, widgetId: widgetId, params: params).addContentIdReference(contentId)
  }

  @Override
  String id() {
    'propertyDetail'
  }
}
