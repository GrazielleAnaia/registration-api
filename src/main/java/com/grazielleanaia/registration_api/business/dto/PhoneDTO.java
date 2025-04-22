package com.grazielleanaia.registration_api.business.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PhoneDTO {


    private String number;
    private Long customer_id;
}
