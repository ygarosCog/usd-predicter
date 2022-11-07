package org.ygaros.predicter.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
public class ErrorMapper {


    @ExceptionHandler({
            Exception.class
    })
    public ResponseEntity<String> noData(){
        return ResponseEntity.notFound().build();
    }
}
