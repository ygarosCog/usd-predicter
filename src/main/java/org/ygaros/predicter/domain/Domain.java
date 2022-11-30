package org.ygaros.predicter.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ygaros.predicter.data.NBPResponse;
import org.ygaros.predicter.data.Rate;

import java.time.LocalDate;

@Service
public class Domain {

    private final USDCaller caller;

    private final Predicter predicter;

    @Autowired
    public Domain(USDCaller caller, Predicter predicter) {
        this.caller = caller;
        this.predicter = predicter;
    }

    public boolean getPredictionForTomorrow(){
        return this.predicter.predictForTomorrow();
    }
    public Rate getRateForDate(LocalDate date){
        Rate[] rates;
        try {
            rates = this.caller.getRateForDate(date).getRates();
        }catch (Exception e){
            return this.getRateForDate(date.minusDays(1));
        }
        if(rates.length == 0){
            return this.getRateForDate(date.minusDays(1));
        }
        return rates[0];
    }
    public Rate getRateForToday() {
        return this.getRateForDate(LocalDate.now());
    }
}
