package com.grazielleanaia.registration_api.business;


import com.grazielleanaia.registration_api.business.converter.CustomerConverter;
import com.grazielleanaia.registration_api.business.dto.CustomerDTO;
import com.grazielleanaia.registration_api.business.dto.PhoneDTO;
import com.grazielleanaia.registration_api.business.dto.ResidenceDTO;
import com.grazielleanaia.registration_api.infrastructure.entity.Customer;
import com.grazielleanaia.registration_api.infrastructure.entity.Phone;
import com.grazielleanaia.registration_api.infrastructure.entity.Residence;
import com.grazielleanaia.registration_api.infrastructure.exception.ConflictException;
import com.grazielleanaia.registration_api.infrastructure.exception.ResourceNotFoundException;
import com.grazielleanaia.registration_api.infrastructure.repository.CustomerRepository;
import com.grazielleanaia.registration_api.infrastructure.repository.PhoneRepository;
import com.grazielleanaia.registration_api.infrastructure.repository.ResidenceRepository;
import com.grazielleanaia.registration_api.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final ResidenceRepository residenceRepository;
    private final PhoneRepository phoneRepository;


    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        emailExist(customerDTO.getEmail());
        customerDTO.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
        Customer customer = customerConverter.convertToCustomer(customerDTO);
        return customerConverter.convertToCustomerDTO(customerRepository.save(customer));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public CustomerDTO getCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Email not found" + email));
        return customerConverter.convertToCustomerDTO(customer);
    }

    public void deleteCustomer(String email) {
        try {
            customerRepository.deleteByEmail(email);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Email not found", e.getCause());
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

    public CustomerDTO updateCustomer(CustomerDTO customerDTO, String token) {
        String email = jwtUtil.extractUsername(token.substring(7));
        Customer customer = customerRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Email not found"));
        customerDTO.setPassword(customerDTO.getPassword() != null ? passwordEncoder.encode(customerDTO.getPassword()) : null);
        Customer customer1 = customerConverter.updateCustomer(customerDTO, customer);
        return customerConverter.convertToCustomerDTO(customerRepository.save(customer1));
    }

    public ResidenceDTO updateResidence(ResidenceDTO residenceDTO, Long id) {
        Residence residence = residenceRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Id not found" + id));

        Residence residence1 = customerConverter.updateResidence(residenceDTO, residence);
        return customerConverter.convertToResidenceDTO(residenceRepository.save(residence1));
    }

    public PhoneDTO updatePhone(PhoneDTO phoneDTO, Long id) {
        Phone phone = phoneRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Id not found" + id));
        Phone phone1 = customerConverter.updatePhone(phoneDTO, phone);
        return customerConverter.convertToPhoneDTO(phoneRepository.save(phone1));
    }

    public ResidenceDTO addResidence(ResidenceDTO residenceDTO, String token) {
        String email = jwtUtil.extractUsername(token.substring(7));
        Customer customer = customerRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Customer not found" + email));
        Residence residence = customerConverter.addResidence(residenceDTO, customer.getId());
        return customerConverter.convertToResidenceDTO(residenceRepository.save(residence));
    }

    public PhoneDTO addPhone(PhoneDTO phoneDTO, String token) {
        String email = jwtUtil.extractUsername(token.substring(7));
        Customer customer = customerRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Customer not found" + email));
        Phone phone = customerConverter.addPhone(phoneDTO, customer.getId());
        return customerConverter.convertToPhoneDTO(phoneRepository.save(phone));
    }

}
