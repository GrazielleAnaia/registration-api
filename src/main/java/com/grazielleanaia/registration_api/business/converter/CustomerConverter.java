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
                .phoneList(customerDTO.getPhoneList() != null ? convertToPhoneList(customerDTO.getPhoneList()) : null)
                .residenceList(customerDTO.getResidenceList() != null ? convertToResidenceList(customerDTO.getResidenceList()) : null)
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
                .residenceList(customer.getResidenceList() != null ? convertToListResidenceDTO(customer.getResidenceList()) : null)
                .phoneList(customer.getPhoneList() != null ? convertToListPhoneDTO(customer.getPhoneList()) : null)
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
                .id(residence.getId())
                .build();
    }

    public List<PhoneDTO> convertToListPhoneDTO(List<Phone> phones) {
        return phones.stream().map(this::convertToPhoneDTO).toList();
    }

    public PhoneDTO convertToPhoneDTO(Phone phone) {
        return PhoneDTO.builder()
                .number(phone.getNumber())
                .id(phone.getId())
                .build();
    }

    //Converter for update methods

    public Customer updateCustomer(CustomerDTO customerDTO, Customer customer) {
        return Customer.builder()
                .name(customerDTO.getName() != null ? customerDTO.getName() : customer.getName())
                .email(customerDTO.getEmail() != null ? customerDTO.getEmail() : customer.getEmail())
                .password(customerDTO.getPassword() != null ? customerDTO.getPassword() : customer.getPassword())
                .id(customer.getId())
                .residenceList(customer.getResidenceList())
                .phoneList(customer.getPhoneList())
                .build();
    }

    public Residence updateResidence(ResidenceDTO residenceDTO, Residence residence) {
        return Residence.builder()
                .street(residenceDTO.getStreet() != null ? residenceDTO.getStreet() : residence.getStreet())
                .complement(residenceDTO.getComplement() != null ? residenceDTO.getComplement() : residence.getComplement())
                .city(residenceDTO.getCity() != null ? residenceDTO.getCity() : residence.getCity())
                .state(residenceDTO.getState() != null ? residenceDTO.getState() : residence.getState())
                .zipCode(residenceDTO.getZipCode() != null ? residenceDTO.getZipCode() : residence.getZipCode())
                .id(residence.getId())
                .build();
    }

    public Phone updatePhone(PhoneDTO phoneDTO, Phone phone) {
        return Phone.builder()
                .number(phoneDTO.getNumber() != null ? phoneDTO.getNumber() : phone.getNumber())
                .id(phone.getId())
                .build();
    }

    //Converter for Add methods

    public Residence addResidence(ResidenceDTO residenceDTO, Long customerID) {
        return Residence.builder()
                .street(residenceDTO.getStreet())
                .complement(residenceDTO.getComplement())
                .city(residenceDTO.getCity())
                .state(residenceDTO.getState())
                .zipCode(residenceDTO.getZipCode())
                .customer_id(customerID)
                .build();
    }

    public Phone addPhone(PhoneDTO phoneDTO, Long customerID) {
        return Phone.builder()
                .number(phoneDTO.getNumber())
                .customer_id(customerID)
                .build();
    }
}
