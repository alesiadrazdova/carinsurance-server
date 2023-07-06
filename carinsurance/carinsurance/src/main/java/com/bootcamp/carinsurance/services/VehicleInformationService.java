package com.bootcamp.carinsurance.services;

import com.bootcamp.carinsurance.models.VehicleInformation;
import com.bootcamp.carinsurance.repository.VehicleInformationRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class VehicleInformationService {
    private final VehicleInformationRepository vehicleInformationRepository;

    public VehicleInformationService(VehicleInformationRepository vehicleInformationRepository) {
        this.vehicleInformationRepository = vehicleInformationRepository;
    }

    @Transactional
    public void save(VehicleInformation vehicleInformation){vehicleInformationRepository.save(vehicleInformation);}
}
