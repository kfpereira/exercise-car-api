package com.fantin.car.environments;

import com.fantin.car.config.inits.InitCars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnvCars {

    @Autowired
    private InitCars initCars;

    public void init() {
        initCars.init();
    }
}
