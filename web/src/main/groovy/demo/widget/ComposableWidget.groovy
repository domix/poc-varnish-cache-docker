package demo.widget
/**
 * Created by domix on 1/6/15.
 */
class ComposableWidget extends Widget {
  Map<String, Widget> widgets = [:]

  void addWidget(String id, Widget widget) {
    widgets.put(id, widget)
  }
}
