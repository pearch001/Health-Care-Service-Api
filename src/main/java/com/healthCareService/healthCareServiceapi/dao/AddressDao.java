package com.healthCareService.healthCareServiceapi.dao;

import com.healthCareService.healthCareServiceapi.model.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressDao extends CrudRepository<Address, Long> {
    
}
