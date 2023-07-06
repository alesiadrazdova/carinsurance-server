package com.bootcamp.carinsurance.services;

import com.bootcamp.carinsurance.models.ImpactDirection;
import com.bootcamp.carinsurance.repository.ImpactDirectionRepository;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ImpactDirectionService {
    private final ImpactDirectionRepository impactDirectionRepository;

    public ImpactDirectionService(ImpactDirectionRepository impactDirectionRepository) {
        this.impactDirectionRepository = impactDirectionRepository;
    }

    public ImpactDirection findById(int id) {
        Optional<ImpactDirection> foundDirection = impactDirectionRepository.findByImpactDirectionId(id);
        return foundDirection.orElse(null);
    }

    @Transactional
    public void save(ImpactDirection impactDirection){impactDirectionRepository.save(impactDirection);}
}
