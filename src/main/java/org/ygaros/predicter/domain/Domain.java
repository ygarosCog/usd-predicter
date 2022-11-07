package org.ygaros.predicter.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class Domain {

    final private USDCaller caller;

    final private Predicter predicter;

    @Autowired
    public Domain(USDCaller caller, Predicter predicter) {
        this.caller = caller;
        this.predicter = predicter;
    }

    public boolean getPredictionForTomorrow(){
        return this.predicter.predictForTomorrow();
    }
}
