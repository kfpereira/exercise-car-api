package com.fantin.car.domain.services;

import com.fantin.car.config.errors.ErrorMessages;
import com.fantin.car.config.exceptions.InvalidateDataException;
import com.fantin.car.domain.model.Carro;
import com.fantin.car.domain.repositories.CarroRepository;
import com.fantin.car.domain.type.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class CarroService {

    @Autowired
    private CarroRepository repository;

    public Carro save(String marca, String modelo, Integer ano, String placa) {
        Carro carro = Carro.builder()
                .marca(marca.toUpperCase())
                .modelo(modelo.toUpperCase())
                .ano(ano)
                .placa(placa.toUpperCase())
                .status(Status.AVAILABLE)
                .build();

        return repository.saveAndFlush(carro);
    }

    @Transactional
    public void rent(Carro carro, String customer) throws InvalidateDataException {
        validateRent(carro);

        carro.setStatus(Status.UNAVAILABLE);
        carro.setRentDate(new Date());
        carro.setCustomer(customer);

        repository.saveAndFlush(carro);
    }

    private void validateRent(Carro carro) throws InvalidateDataException {
        Carro currentCarro = repository.findById(carro.getId()).get();
        if (currentCarro.getStatus().equals(Status.UNAVAILABLE))
            throw new InvalidateDataException(ErrorMessages.ALUGADO.getMessage());
    }
}
