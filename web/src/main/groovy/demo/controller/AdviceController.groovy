package demo.controller

import gex.commons.exception.ObjectNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

import static org.springframework.http.ResponseEntity.notFound

/**
 * Created by domix on 1/4/15.
 */
@ControllerAdvice
class AdviceController {
  @ExceptionHandler(ObjectNotFoundException)
  ResponseEntity<String> foo(ObjectNotFoundException exception) {
    notFound().build()
  }
}
