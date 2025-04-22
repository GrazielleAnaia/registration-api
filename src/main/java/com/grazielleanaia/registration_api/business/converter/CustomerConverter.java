package com.grazielleanaia.registration_api.business.converter;



import com.grazielleanaia.registration_api.business.dto.CustomerDTO;
import com.grazielleanaia.registration_api.business.dto.PhoneDTO;
import com.grazielleanaia.registration_api.business.dto.ResidenceDTO;
import com.grazielleanaia.registration_api.infrastructure.entity.Customer;
import com.grazielleanaia.registration_api.infrastructure.entity.Phone;
import com.grazielleanaia.registration_api.infrastructure.entity.Residence;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerConverter {

    /* Convert to Entities */
    public Customer convertToCustomer(CustomerDTO customerDTO) {
        return Customer.builder()
                .name(customerDTO.getName())
                .email(customerDTO.getEmail())
                .password(customerDTO.getPassword())
                .phoneList(convertToPhoneList(customerDTO.getPhoneList()))
                .residenceList(convertToResidenceList(customerDTO.getResidenceList()))
                .build();
    }

    public List<Phone> convertToPhoneList(List<PhoneDTO> phoneDTOList) {
        return phoneDTOList.stream()
                .map(this::convertToPhone).toList();
    }

    public Phone convertToPhone(PhoneDTO phoneDTO) {
        return Phone.builder()
                .number(phoneDTO.getNumber())
                .build();
    }

    public List<Residence> convertToResidenceList(List<ResidenceDTO> residenceDTOList) {
        return residenceDTOList.stream()
                .map(this::convertToResidence).toList();
    }

    public Residence convertToResidence(ResidenceDTO residenceDTO) {
        return Residence.builder()
                .street(residenceDTO.getStreet())
                .complement(residenceDTO.getComplement())
                .city(residenceDTO.getCity())
                .state(residenceDTO.getState())
                .zipCode(residenceDTO.getZipCode())
                .build();
    }

    /* Convert to DTO */
    public CustomerDTO convertToCustomerDTO(Customer customer) {
        return CustomerDTO.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .residenceList(convertToListResidenceDTO(customer.getResidenceList()))
                .phoneList(convertToListPhoneDTO(customer.getPhoneList()))
                .build();
    }

    public List<ResidenceDTO> convertToListResidenceDTO(List<Residence> residences) {
        return residences.stream().map(this::convertToResidenceDTO).toList();
    }

    public ResidenceDTO convertToResidenceDTO(Residence residence) {
        return ResidenceDTO.builder()
                .street(residence.getStreet())
                .complement(residence.getComplement())
                .city(residence.getCity())
                .state(residence.getState())
                .zipCode(residence.getZipCode())
                .build();
    }

    public List<PhoneDTO> convertToListPhoneDTO(List<Phone> phones) {
        return phones.stream().map(this::convertToPhoneDTO).toList();
    }

    public PhoneDTO convertToPhoneDTO(Phone phone) {
        return PhoneDTO.builder()
                .number(phone.getNumber())
                .build();
    }
}
