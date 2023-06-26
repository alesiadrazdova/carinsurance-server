package com.bootcamp.carinsurance.repository;

import com.bootcamp.carinsurance.models.VehicleCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleConditionRepository extends JpaRepository<VehicleCondition,Integer> {

}