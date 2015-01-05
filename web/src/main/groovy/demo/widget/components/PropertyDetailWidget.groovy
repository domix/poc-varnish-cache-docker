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
class PropertyDetailWidget implements WidgetComponent {
  @Autowired
  PropertyService propertyService

  @Override
  Map<String, Map> model(String widgetId, String contentId, MultiValueMap params) {
    [property: propertyService.getById(contentId)]
  }

  @Override
  String id() {
    'propertyDetail'
  }
}
