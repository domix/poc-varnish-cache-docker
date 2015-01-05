package demo.widget.render

import groovy.util.logging.Slf4j
import org.springframework.core.NamedInheritableThreadLocal
import org.springframework.core.NamedThreadLocal

import static org.springframework.util.Assert.notNull

/**
 * Created by domix on 1/5/15.
 */
@Slf4j
class RenderContextHolder {
  private static final ThreadLocal<RenderContext> renderContextHolder =
    new NamedThreadLocal<RenderContext>("RenderContext")

  private static final ThreadLocal<RenderContext> inheritableRenderContextHolder =
    new NamedInheritableThreadLocal<RenderContext>("RenderContext")

  /**
   * Reset the RenderContext for the current thread.
   */
  static void resetRenderContext() {
    renderContextHolder.remove()
    inheritableRenderContextHolder.remove()
  }

  /**
   * Associate the given RenderContext with the current thread,
   * <i>not</i> exposing it as inheritable for child threads.
   * @param renderContext the current RenderContext,
   * or {@code null} to reset the thread-bound context
   * @see demo.widget.render.SimpleRenderContext
   */
  static void setRenderContext(RenderContext renderContext) {
    setRenderContext(renderContext, false)
  }

  /**
   * Associate the given RenderContext with the current thread.
   * @param renderContext the current RenderContext,
   * or {@code null} to reset the thread-bound context
   * @param inheritable whether to expose the RenderContext as inheritable
   * for child threads (using an {@link InheritableThreadLocal})
   * @see demo.widget.render.SimpleRenderContext
   */
  static void setRenderContext(RenderContext renderContext, boolean inheritable) {
    if (log.isDebugEnabled()) {
      log.debug("Setting RenderContext '${renderContext.toString()}', using inheritable: '${inheritable}'")
    }
    if (renderContext) {
      if (inheritable) {
        inheritableRenderContextHolder.set(renderContext)
        renderContextHolder.remove()
      } else {
        renderContextHolder.set(renderContext)
        inheritableRenderContextHolder.remove()
      }
    } else {
      resetRenderContext()
    }
  }

  /**
   * Return the RenderContext associated with the current thread, if any.
   * @return the current RenderContext, or {@code null} if none
   */
  static RenderContext getRenderContext() {
    renderContextHolder.get() ?: inheritableRenderContextHolder.get()
  }

  /**
   * Associate the given RenderEnvironment with the current thread.
   * <p>Will implicitly create a RenderContext for the given RenderEnvironment,
   * <i>not</i> exposing it as inheritable for child threads.
   * @param renderEnvironment the current RenderEnvironment, or {@code null} to reset
   * the renderEnvironment part of thread-bound context
   * @see demo.widget.render.SimpleRenderContext#SimpleRenderContext(demo.widget.render.RenderEnvironment)
   */
  static void setRenderEnvironment(RenderEnvironment renderEnvironment) {
    setRenderEnvironment(renderEnvironment, false)
  }

  /**
   * Associate the given RenderEnvironment with the current thread.
   * <p>Will implicitly create a RenderContext for the given RenderEnvironment.
   * @param renderEnvironment the current RenderEnvironment, or {@code null} to reset
   * the renderEnvironment part of thread-bound context
   * @param inheritable whether to expose the RenderContext as inheritable
   * for child threads (using an {@link InheritableThreadLocal})
   * @see demo.widget.render.SimpleRenderContext#SimpleRenderContext(RenderEnvironment)
   */
  static void setRenderEnvironment(RenderEnvironment renderEnvironment, boolean inheritable) {
    notNull(renderEnvironment, 'The RenderEnvironment must be provided in order to setup the RenderContext')

    if (log.isDebugEnabled()) {
      log.debug("Setting RenderEnvironment '${renderEnvironment.toString()}'")
    }
    SimpleRenderContext simpleRenderContext = new SimpleRenderContext(renderEnvironment)
    setRenderContext(simpleRenderContext, inheritable);
  }

  /**
   * Return the RenderEnvironment associated with the current thread, if any.
   *
   * @return the current RenderEnvironment.
   * @see RenderContext#getRenderEnvironment()
   */
  static RenderEnvironment getRenderEnvironment() {
    getRenderContext()?.getRenderEnvironment()
  }
}
