package com.grazielleanaia.registration_api.infrastructure.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "residence")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Residence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "complement")
    private String complement;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zipcode")
    private Long zipCode;

    @Column(name = "customer_id")
    private Long customer_id;
}
