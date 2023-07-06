package com.bootcamp.carinsurance.services;

import com.bootcamp.carinsurance.models.TypeOfContact;
import com.bootcamp.carinsurance.repository.TypeOfContactRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TypeOfContactService {
    private final TypeOfContactRepository typeOfContactRepository;

    public TypeOfContactService(TypeOfContactRepository typeOfContactRepository) {
        this.typeOfContactRepository = typeOfContactRepository;
    }

    @Transactional
    public void save(TypeOfContact typeOfContact) {
        typeOfContactRepository.save(typeOfContact);
    }

    public TypeOfContact findByName(String name) {
        Optional<TypeOfContact> typeOfContact = typeOfContactRepository.findByName(name);
        return typeOfContact.orElse(null);
    }

    public List<TypeOfContact> findAll(){return typeOfContactRepository.findAll();}
}
