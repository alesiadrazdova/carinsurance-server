package com.bootcamp.carinsurance.repository;

import com.bootcamp.carinsurance.models.VehicleInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleInformationRepository extends JpaRepository<VehicleInformation,Integer> {

}
