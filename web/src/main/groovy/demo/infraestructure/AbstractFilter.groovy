package demo.infraestructure

import org.springframework.web.filter.OncePerRequestFilter

/**
 * Created by domix on 1/5/15.
 */
abstract class AbstractFilter extends OncePerRequestFilter {

  boolean threadContextInheritable = false

  /**
   * Set whether to expose the desired Context and RequestAttributes as inheritable
   * for child threads (using an {@link java.lang.InheritableThreadLocal}).
   * <p>Default is "false", to avoid side effects on spawned background threads.
   * Switch this to "true" to enable inheritance for custom child threads which
   * are spawned during request processing and only used for this request
   * (that is, ending after their initial task, without reuse of the thread).
   * <p><b>WARNING:</b> Do not use inheritance for child threads if you are
   * accessing a thread pool which is configured to potentially add new threads
   * on demand (e.g. a JDK {@link java.util.concurrent.ThreadPoolExecutor}),
   * since this will expose the inherited context to such a pooled thread.
   */
  void setThreadContextInheritable(boolean threadContextInheritable) {
    this.threadContextInheritable = threadContextInheritable
  }

  /**
   * Returns "false" so that the filter may set up the request context in each
   * asynchronously dispatched thread.
   */
  @Override
  protected boolean shouldNotFilterAsyncDispatch() {
    false
  }

  /**
   * Returns "false" so that the filter may set up the request context in an
   * error dispatch.
   */
  @Override
  protected boolean shouldNotFilterErrorDispatch() {
    false
  }
}
