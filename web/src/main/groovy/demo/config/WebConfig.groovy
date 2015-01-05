package demo.config

import demo.infraestructure.RenderContextFilter
import org.springframework.boot.autoconfigure.AutoConfigureAfter
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.OncePerRequestFilter

/**
 * Created by domix on 1/5/15.
 */
@Configuration
@AutoConfigureAfter(WebMvcAutoConfiguration)
class WebConfig {
  @Bean
  OncePerRequestFilter renderContextFilter() {
    new RenderContextFilter()
  }
}
