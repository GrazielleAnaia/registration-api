package com.grazielleanaia.registration_api.infrastructure.repository;


import com.grazielleanaia.registration_api.infrastructure.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

boolean existsByEmail(String email);

Optional<Customer> findByEmail(String email);

@Transactional
    void deleteByEmail(String email);
}
