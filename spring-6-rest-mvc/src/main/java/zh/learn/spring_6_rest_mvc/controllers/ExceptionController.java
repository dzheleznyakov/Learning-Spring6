package zh.learn.spring_6_rest_mvc.controllers;

import org.springframework.http.ResponseEntity;

//@ControllerAdvice
public class ExceptionController {
//    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNOtFoundException() {
        return ResponseEntity.notFound().build();
    }
}
