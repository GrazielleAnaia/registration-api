package com.grazielleanaia.registration_api.business;


import com.grazielleanaia.registration_api.business.converter.CustomerConverter;
import com.grazielleanaia.registration_api.business.dto.CustomerDTO;
import com.grazielleanaia.registration_api.infrastructure.entity.Customer;
import com.grazielleanaia.registration_api.infrastructure.exception.ConflictException;
import com.grazielleanaia.registration_api.infrastructure.exception.ResourceNotFoundException;
import com.grazielleanaia.registration_api.infrastructure.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;


    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        emailExist(customerDTO.getEmail());
        Customer customer = customerConverter.convertToCustomer(customerDTO);
        return customerConverter.convertToCustomerDTO(customerRepository.save(customer));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public CustomerDTO getCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Email not found"));
        return customerConverter.convertToCustomerDTO(customer);
    }

    public void deleteCustomer(String email) {
        try{
            customerRepository.deleteByEmail(email);
        } catch (ResourceNotFoundException e) {
            throw  new ResourceNotFoundException("Email not found", e.getCause());
        }
    }

    public void emailExist(String email) {
        boolean emailExist = verifyEmail(email);
        try {
            if (emailExist) {
                throw new ConflictException("Email already registered" + email);
            }
        } catch (ConflictException e) {
            throw new ConflictException("Email already registered", e.getCause());
        }
    }

    public boolean verifyEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

}
