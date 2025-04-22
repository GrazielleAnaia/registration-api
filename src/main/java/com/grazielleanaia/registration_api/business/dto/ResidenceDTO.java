package com.grazielleanaia.registration_api.business.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ResidenceDTO {

    private String street;

    private String complement;

    private String city;

    private String state;

    private Long zipCode;

    private Long customer_id;
}
