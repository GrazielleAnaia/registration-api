package com.grazielleanaia.registration_api.business.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CustomerDTO {


    private String name;

    private String email;

    private String password;

    private List<PhoneDTO> phoneList;

    private List<ResidenceDTO> residenceList;
}
