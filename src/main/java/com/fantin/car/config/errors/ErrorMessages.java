package com.fantin.car.config.errors;

import lombok.Getter;

@Getter
public enum ErrorMessages {

    ALUGADO("Carro com Status Alugado");

    private String message;

    ErrorMessages(String message) {
        this.message = message;
    }

}
