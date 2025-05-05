package com.grazielleanaia.registration_api.business.mapper;


import com.grazielleanaia.registration_api.business.dto.CustomerDTO;
import com.grazielleanaia.registration_api.infrastructure.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapperConverter {

    @Mapping(source = "customer_id", target = "customer_id")
    List<CustomerDTO> toCustomerDTOList(List<Customer> customerList);
}
