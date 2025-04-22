package com.grazielleanaia.registration_api.infrastructure.repository;


import com.grazielleanaia.registration_api.infrastructure.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
