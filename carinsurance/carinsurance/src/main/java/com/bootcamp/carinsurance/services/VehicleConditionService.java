package com.bootcamp.carinsurance.services;

import com.bootcamp.carinsurance.models.VehicleCondition;
import com.bootcamp.carinsurance.repository.VehicleConditionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class VehicleConditionService {
    private final VehicleConditionRepository vehicleConditionRepository;

    public VehicleConditionService(VehicleConditionRepository vehicleConditionRepository) {
        this.vehicleConditionRepository = vehicleConditionRepository;
    }

    @Transactional
    public void save(VehicleCondition vehicleCondition){vehicleConditionRepository.save(vehicleCondition);}
}
