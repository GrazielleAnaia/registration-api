package com.grazielleanaia.registration_api.controller;


import com.grazielleanaia.registration_api.business.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor

public class CustomerController {

    private final CustomerService customerService;


}
