package demo.widget.components

import demo.service.PropertyService
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

  @Override
  Map<String, Object> model(String widgetId, String contentId, MultiValueMap params) {
    [properties: propertyService.findProperties()]
  }

  @Override
  String id() {
    'propertyList'
  }
}
