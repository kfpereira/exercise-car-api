package com.fantin.car;

import com.fantin.car.config.core.FunctionalTest;
import com.fantin.car.config.exceptions.InvalidateDataException;
import com.fantin.car.domain.RentCarThread;
import com.fantin.car.domain.model.Carro;
import com.fantin.car.domain.repositories.CarroRepository;
import com.fantin.car.domain.services.CarroService;
import com.fantin.car.environments.EnvCars;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@FunctionalTest
class CarroTest {

    @Autowired
    private EnvCars envCars;

    @Autowired
    private CarroRepository repository;

    @Autowired
    private CarroService service;

    @BeforeEach
    void setup() {
        envCars.init();
    }

    @Test
    void happyDay() {
        assertEquals(3, repository.findAll().size());
    }

    @Test
    void findCarByPlaca() {
        Carro carro = repository.findByPlaca("AAA1234");
        assertEquals("KA", carro.getModelo());
    }

    @Test
    void rentACar() throws InvalidateDataException {
        Carro carro = repository.findByPlaca("AAA1234");
        service.rent(carro, "Customer 01");

        assertEquals("Customer 01", repository.findByPlaca("AAA1234").getCustomer());
    }

    @Test
    void rentACarTwoCustomers() throws InvalidateDataException {
        Carro carro = repository.findByPlaca("AAA1234");
        service.rent(carro, "Customer 01");
        assertThrows(InvalidateDataException.class,
                () -> service.rent(carro, "Customer 02"),
                "Carro com Status Alugado"
        );

        assertEquals("Customer 01", repository.findByPlaca("AAA1234").getCustomer());
    }

    @Test
    void rentACarTwoCustomersConcurrently() throws InvalidateDataException {
        Carro carro = repository.findByPlaca("AAA1234");
        RentCarThread customer01 = new RentCarThread(carro, "Customer 01");
        RentCarThread customer02 = new RentCarThread(carro, "Customer 02");

        customer01.run();
        customer02.run();

        assertEquals("Customer 01", repository.findByPlaca("AAA1234").getCustomer());
    }

}
