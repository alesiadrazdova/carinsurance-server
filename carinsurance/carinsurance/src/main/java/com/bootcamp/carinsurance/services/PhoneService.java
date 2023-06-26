package com.bootcamp.carinsurance.services;

import com.bootcamp.carinsurance.models.Phone;
import com.bootcamp.carinsurance.repository.PhoneRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PhoneService {
    private final PhoneRepository phoneRepository;

    public PhoneService(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @Transactional
    public void save(Phone phone){phoneRepository.saveAndFlush(phone);}
}
