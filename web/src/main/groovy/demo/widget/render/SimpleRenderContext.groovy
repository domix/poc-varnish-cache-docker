package demo.widget.render

/**
 * Created by domix on 1/5/15.
 */
class SimpleRenderContext implements RenderContext {
  RenderEnvironment renderEnvironment
  /**
   * Create a new SimpleRenderContext that exposes the specified RenderEnvironment.
   * Every {@link #getRenderEnvironment()} call will return this RenderEnvironment.
   * @param renderEnvironment the RenderEnvironment to expose
   */
  SimpleRenderContext(RenderEnvironment renderEnvironment) {
    this.renderEnvironment = renderEnvironment;
  }

  @Override
  RenderEnvironment getRenderEnvironment() {
    renderEnvironment
  }
}
