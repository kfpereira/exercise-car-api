package com.fantin.car.domain.type;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

public enum Status {

    AVAILABLE("A"),
    UNAVAILABLE("U");

    private String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Converter(autoApply = true)
    public static class Mapeador implements AttributeConverter<Status, String> {

        @Override
        public String convertToDatabaseColumn(Status x) {
            return String.valueOf(x.getValue());
        }

        @Override
        public Status convertToEntityAttribute(String y) {
            if (y == null) return null;
            if ("A".equals(y)) return AVAILABLE;
            if ("U".equals(y)) return UNAVAILABLE;
            throw new IllegalStateException("Valor inválido: " + y);
        }
    }
}
