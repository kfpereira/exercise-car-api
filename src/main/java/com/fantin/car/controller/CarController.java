package com.fantin.car.controller;

import com.fantin.car.config.exceptions.InvalidateDataException;
import com.fantin.car.domain.RentCarThread;
import com.fantin.car.domain.model.Carro;
import com.fantin.car.domain.repositories.CarroRepository;
import com.fantin.car.domain.services.CarroService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = { "carsController" })
@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarroRepository repository;

    @Autowired
    private CarroService service;

    @PostMapping
    public String rentCar(@RequestBody Entrada entrada) throws InvalidateDataException {
        Carro carro = repository.findByPlaca(entrada.getPlaca());
        service.rent(carro, entrada.getCliente());
        return "Carro alugado com sucesso";
    }

    @PostMapping(value = "/thread")
    public String rentCarByThread(@RequestBody Entrada entrada) throws InvalidateDataException {
        Carro carro = repository.findByPlaca(entrada.getPlaca());
        RentCarThread thread = new RentCarThread(carro, entrada.getCliente());
        thread.run();
        return "Carro alugado com sucesso";
    }

}
