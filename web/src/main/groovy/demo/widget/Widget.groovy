package demo.widget

import groovy.transform.ToString
import org.springframework.util.MultiValueMap

/**
 * Created by domix on 1/4/15.
 */
@ToString
class Widget {
  String widgetId
  String contentId
  Map<String, Object> model
  MultiValueMap params
  List<String> contentIdReferences = []


  Widget addContentIdReference(String id) {
    contentIdReferences << id
    this
  }

  List<String> getContentIdReferences() {
    contentIdReferences.sort().unique()
  }
}
