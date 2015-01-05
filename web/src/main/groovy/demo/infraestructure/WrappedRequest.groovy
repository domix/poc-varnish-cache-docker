package demo.infraestructure

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletRequestWrapper

/**
 * Created by domix on 1/5/15.
 */
class WrappedRequest extends HttpServletRequestWrapper {
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
  public Map<String, String[]> getParameterMap()
  {
    //return local param map
  }
}
