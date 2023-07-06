package com.bootcamp.carinsurance.services;

import com.bootcamp.carinsurance.models.Contact;
import com.bootcamp.carinsurance.repository.ContactRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ContactService {
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Transactional
    public void save(Contact contact){contactRepository.saveAndFlush(contact);}
}
