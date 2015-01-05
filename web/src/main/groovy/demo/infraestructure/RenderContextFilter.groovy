package demo.infraestructure

import demo.widget.render.RenderContextHolder
import demo.widget.render.RenderEnvironment
import org.springframework.web.context.request.ServletRequestAttributes

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by domix on 1/5/15.
 */
class RenderContextFilter extends AbstractFilter {
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    def attributes = new ServletRequestAttributes(request, response)
    initContextHolders(request, attributes)

    try {
      filterChain.doFilter(request, response)
    }
    finally {
      resetContextHolders()
    }
  }

  private void initContextHolders(HttpServletRequest request, ServletRequestAttributes requestAttributes) {
    def device = getDevice(request)
    def renderMode = getRenderMode(request)
    def renderEnvironment = new RenderEnvironment(device: device, renderMode: renderMode)

    RenderContextHolder.setRenderEnvironment(renderEnvironment)
  }

  private String getRenderMode(HttpServletRequest request) {
    if (request.getHeader('X-Esi') || request.getParameter('__render_mode') == 'esi') {
      'esi'
    } else {
      'markup'
    }
  }

  private String getDevice(HttpServletRequest request) {
    (request.getHeader('X-device') ?: request.getParameter('__device')) ?: 'desktop'
  }

  private void resetContextHolders() {
    RenderContextHolder.resetRenderContext()
  }

}
