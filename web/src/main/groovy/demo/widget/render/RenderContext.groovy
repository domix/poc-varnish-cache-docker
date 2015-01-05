package demo.widget.render

/**
 * Strategy interface for determining the current RenderEnvironment.
 *
 * <p>A RenderContext instance can be associated with a thread
 * via the RenderContextHolder class.
 *
 * @author Domingo Suarez Torres
 * @since 1/5/15
 *
 * @see RenderContextHolder#getRenderEnvironment()
 */
interface RenderContext {
  /**
   * Return the current RenderEnvironment, which can be fixed or determined dynamically,
   * depending on the implementation strategy.
   * @return the current RenderEnvironment, or {@code null} if no specific RenderEnvironment associated
   */
  RenderEnvironment getRenderEnvironment()
}
