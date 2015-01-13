package demo.widget.render

/**
 * Created by domix on 1/5/15.
 */
class RenderEnvironment {
  String device = ''
  String renderMode = ''
  List<String> usedIds = []

  String toString() {
    "device: '$device' renderMode: '$renderMode'"
  }
}
