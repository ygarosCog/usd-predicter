package org.ygaros.predicter.domain;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tensorflow.SavedModelBundle;
import org.tensorflow.Tensor;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.types.TFloat32;
import org.ygaros.predicter.data.NBPResponse;
import org.ygaros.predicter.data.Rate;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Component
public class Predicter implements InitializingBean {

    private static final String SEQUENTIAL_INPUT = "sequential_input";
    private SavedModelBundle model;
    private final USDCaller caller;

    @Autowired
    public Predicter(USDCaller caller) {
        this.caller = caller;
    }

    /**
     * Return True if USD value in PLN will go up,
     * False if USD to PLN value will go down
     * @return boolean
     */
    public boolean predictForTomorrow(){
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        NBPResponse rates = this.caller.getRatesFor(yesterday, today);
        Map<String, Tensor> output = this.model.call(this.prepareInput(today,rates));
        return this.parseOutput(output);

    }

    private boolean parseOutput(Map<String, Tensor> output) {
        Tensor tensor = output.get(output.keySet().toArray()[0]);
        float willGoDown = tensor.asRawTensor().data().asFloats().getFloat(0);
        float willGoUp = tensor.asRawTensor().data().asFloats().getFloat(1);
        return willGoUp > willGoDown;
    }

    private Map<String, Tensor> prepareInput(LocalDate today, NBPResponse rates){
        Map<String, Tensor> inputData = new HashMap<>(1);
        Rate[] ratesArray = rates.getRates();
        float rateYesterday = (float) ratesArray[0].getMid();
        float rateToday = ratesArray.length < 2 ? rateYesterday : (float) ratesArray[1].getMid();
        inputData.put(SEQUENTIAL_INPUT, this.prepareTensor(today, rateToday, rateYesterday));
        return inputData;
    }
    private Tensor prepareTensor(LocalDate today, float rateToday, float rateYesterday){
        return Tensor.of(TFloat32.class, Shape.of(1, 4),
                (tensor) -> tensor
                        .setFloat(today.getMonthValue(), 0L, 0L)
                        .setFloat(today.getDayOfMonth(), 0L, 1L)
                        .setFloat(rateYesterday, 0L, 2L)
                        .setFloat(rateToday, 0L, 3L)
                );
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.model = SavedModelBundle.load("modelTrained");

    }

}