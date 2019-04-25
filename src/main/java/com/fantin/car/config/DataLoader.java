package com.fantin.car.config;

import com.fantin.car.config.inits.InitCars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private InitCars initCars;

    @Autowired
    private EnvironmentReader environmentReader;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (!environmentReader.isAmbienteDeTeste()) {
            initCars.init();
        }
    }
}
