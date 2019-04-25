package com.fantin.car.config.inits;

import com.fantin.car.domain.services.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class InitCars {

    @Autowired
    private CarroService service;

    public void init() {
        service.save("ford", "focus", 2014, "aaa7678");
        service.save("ford", "ka", 2014, "aaa1234");
        service.save("ford", "fiesta", 2014, "aaa0987");
    }

}
