package org.ygaros.predicter.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ygaros.predicter.domain.Domain;


@RestController
@RequestMapping("/")
public class FrontPageController {

    private final Domain domain;

    @Autowired
    public FrontPageController(Domain domain){
        this.domain = domain;
    }

    @GetMapping
    public ResponseEntity<String> frontPage(){
        return ResponseEntity.ok(
                String.format(
                        "hello world result = %b", this.domain.getPredictionForTomorrow()
                )
        );
    }

}
