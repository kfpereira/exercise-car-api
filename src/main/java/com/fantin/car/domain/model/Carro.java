package com.fantin.car.domain.model;

import com.fantin.car.domain.type.Status;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CARRO")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class Carro {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_TAM_ID")
    @SequenceGenerator(
            name = "SEQ_TAM_ID",
            allocationSize = 1,
            sequenceName = "SEQ_TAM_ID"
    )
    private Long id;

    @Column(name = "MARCA")
    private String marca;

    @Column(name = "MODELO")
    private String modelo;

    @Column(name = "ANO")
    private Integer ano;

    @Column(name = "PLACA", length = 8)
    private String placa;

    @Setter
    @Convert(converter = Status.Mapeador.class)
    @Column(name = "STATUS")
    private Status status;

    @Setter
    @Column(name = "RENT_DATE")
    private Date rentDate;

    @Setter
    @Column(name = "RENT_CUSTOMER")
    private String customer;
}
