package com.fantin.car.domain.repositories;

import com.fantin.car.domain.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {

    Carro findByPlaca(String placa);
}
