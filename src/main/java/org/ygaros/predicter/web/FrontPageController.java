package org.ygaros.predicter.web;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ygaros.predicter.data.Rate;
import org.ygaros.predicter.domain.Domain;

import java.time.LocalDate;


@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class FrontPageController {

    private final Domain domain;

    @Autowired
    public FrontPageController(Domain domain){
        this.domain = domain;
    }

    @GetMapping("/predict")
    public ResponseEntity<Boolean> frontPage(){
        return ResponseEntity.ok(this.domain.getPredictionForTomorrow());
    }
    @GetMapping("/ratesForToday")
    public ResponseEntity<Rate> getRateToday(){
        return ResponseEntity.ok(this.domain.getRateForToday());
    }
    @PostMapping("/ratesForDate")
    public ResponseEntity<Rate> getRateForDate(@RequestBody LocalDate date){
        return ResponseEntity.ok(this.domain.getRateForDate(date));
    }

}
