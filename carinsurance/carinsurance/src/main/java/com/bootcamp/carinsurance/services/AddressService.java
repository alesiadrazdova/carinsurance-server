package com.bootcamp.carinsurance.services;

import com.bootcamp.carinsurance.models.Address;
import com.bootcamp.carinsurance.repository.AddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Transactional
    public void save(Address address) {
        addressRepository.saveAndFlush(address);
    }
}
