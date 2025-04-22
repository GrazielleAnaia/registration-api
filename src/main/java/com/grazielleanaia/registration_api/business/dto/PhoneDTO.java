package com.grazielleanaia.registration_api.business.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PhoneDTO {
    private Long id;

    private String number;
}
