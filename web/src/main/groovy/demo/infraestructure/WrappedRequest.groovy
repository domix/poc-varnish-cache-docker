package demo.infraestructure

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletRequestWrapper
import java.util.stream.Collector
import java.util.stream.Collectors

/**
 * Created by domix on 1/5/15.
 */
class WrappedRequest extends HttpServletRequestWrapper {

  def unusedParameters = ['__render_mode', '__device']
  /**
   * Constructs a request object wrapping the given request.
   *
   * @param request The request to wrap
   *
   * @throws java.lang.IllegalArgumentException
   *             if the request is null
   */
  WrappedRequest(HttpServletRequest request) {
    super(request)
  }

  @Override
  Map<String, String[]> getParameterMap() {
    null
  }
}
