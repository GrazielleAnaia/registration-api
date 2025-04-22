package com.grazielleanaia.registration_api.controller;


import com.grazielleanaia.registration_api.business.CustomerService;
import com.grazielleanaia.registration_api.business.dto.CustomerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor

public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerService.createCustomer(customerDTO));
    }

    @GetMapping
    public ResponseEntity<CustomerDTO> findCustomerByEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok(customerService.getCustomerByEmail(email));
    }

    @GetMapping("/all")
    public void findAllCustomer() {
        customerService.getAllCustomers();
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String email) {
        customerService.deleteCustomer(email);
        return ResponseEntity.ok().build();
    }


}
