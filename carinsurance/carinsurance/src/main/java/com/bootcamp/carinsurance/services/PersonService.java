package com.bootcamp.carinsurance.services;

import com.bootcamp.carinsurance.models.Person;
import com.bootcamp.carinsurance.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = personRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Person person) {
        personRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setPersonId(id);
        personRepository.save(updatedPerson);
    }

    public Optional<Person> findUserByLogin(String login) throws UsernameNotFoundException {
        Optional<Person> person = personRepository.findByLogin(login);
        return person;
    }

    @Transactional
    public void delete(int id) {
        personRepository.deleteById(id);
    }
}
