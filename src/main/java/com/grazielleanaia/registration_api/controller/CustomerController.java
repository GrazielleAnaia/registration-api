package com.grazielleanaia.registration_api.controller;


import com.grazielleanaia.registration_api.business.CustomerService;
import com.grazielleanaia.registration_api.business.dto.CustomerDTO;
import com.grazielleanaia.registration_api.business.dto.PhoneDTO;
import com.grazielleanaia.registration_api.business.dto.ResidenceDTO;
import com.grazielleanaia.registration_api.infrastructure.entity.Customer;
import com.grazielleanaia.registration_api.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor

public class CustomerController {

    private final CustomerService customerService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerService.createCustomer(customerDTO));
    }

    @PostMapping("/login")
    public String login(@RequestBody CustomerDTO customerDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (customerDTO.getEmail(), customerDTO.getPassword()));
        return "Bearer " + jwtUtil.generateToken(authentication.getName());
    }

    @GetMapping
    public ResponseEntity<CustomerDTO> findCustomerByEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok(customerService.getCustomerByEmail(email));
    }

    @GetMapping("/all")
    public List<CustomerDTO> findAllCustomer() {
        return customerService.getAllCustomers();
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String email) {
        customerService.deleteCustomer(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO customerDTO,
                                                      @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(customerService.updateCustomer(customerDTO, token));
    }

    @PutMapping("/residence")
    public ResponseEntity<ResidenceDTO> updateResidence(@RequestBody ResidenceDTO residenceDTO,
                                                        @RequestParam("id") Long id) {
        return ResponseEntity.ok(customerService.updateResidence(residenceDTO, id));
    }

    @PutMapping("/phone")
    public ResponseEntity<PhoneDTO> updatePhone(@RequestBody PhoneDTO phoneDTO,
                                                @RequestParam("id") Long id) {
        return ResponseEntity.ok(customerService.updatePhone(phoneDTO, id));
    }

    @PostMapping("/residence")
    public ResponseEntity<ResidenceDTO> addResidence(@RequestBody ResidenceDTO residenceDTO,
                                                     @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(customerService.addResidence(residenceDTO, token));
    }

    @PostMapping("/phone")
    public ResponseEntity<PhoneDTO> addPhone(@RequestBody PhoneDTO phoneDTO,
                                             @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(customerService.addPhone(phoneDTO, token));
    }


}
