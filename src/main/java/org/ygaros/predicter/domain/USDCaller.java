package org.ygaros.predicter.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.ygaros.predicter.data.NBPResponse;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
public class USDCaller {

    private final RestTemplate restTemplate;
    private final String uri = "http://api.nbp.pl/api/exchangerates/rates/a/usd/%s/?format=json";

    private final String uriFromTo = "http://api.nbp.pl/api/exchangerates/rates/a/usd/%s/%s/?format=json";

    @Autowired
    public USDCaller(RestTemplateBuilder builder){
        this.restTemplate = builder.build();
    }

    private NBPResponse callApi(LocalDate date)throws HttpClientErrorException {
        String uriWithDate = String.format(uri, date.toString());
        return this.restTemplate.getForObject(uriWithDate,
                NBPResponse.class);
    }

    public BigDecimal getMidValue(LocalDate date){
        return BigDecimal.valueOf(
                this.callApi(date)
                        .getRates()[0].getMid()
        ).setScale(2, RoundingMode.HALF_UP);
    }
    public NBPResponse getRatesFor(LocalDate startDate, LocalDate endDate){
        String uriWithDate = String.format(uriFromTo, startDate.toString(), endDate.toString());
        return this.restTemplate.getForObject(uriWithDate, NBPResponse.class);
    }
}
