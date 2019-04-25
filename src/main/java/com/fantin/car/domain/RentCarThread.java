package com.fantin.car.domain;

import com.fantin.car.BeanUtil;
import com.fantin.car.config.exceptions.InvalidateDataException;
import com.fantin.car.domain.model.Carro;
import com.fantin.car.domain.services.CarroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class RentCarThread implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(RentCarThread.class);

    private String name;
    private Carro carro;

    public RentCarThread(Carro carro, String name) {
        this.carro = carro;
        this.name = name;
    }

    @Override
    public void run() {
        LOGGER.info(getName() + " is running");

        LOGGER.info("Called from thread");

        CarroService service = BeanUtil.getBean(CarroService.class);
        try {
            service.rent(this.carro, this.name);
            System.out.println(getName() + " is running");
        } catch (InvalidateDataException e) {
            System.out.println(getName() + " is running");
            e.printStackTrace();
        }

    }

    private String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
