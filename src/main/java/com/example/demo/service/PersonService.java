package com.example.demo.service;

import com.example.demo.models.Person;
import com.example.demo.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    public Optional<Person> findbyName(String name){
        return personRepository.findByUsername(name);
    }

    public List<Person> all() {
        return personRepository.findAll();
    }

    public Optional<Person> findById(Integer id) {
        return personRepository.findById(id);
    }
    @Transactional
    public void update(int id, Person person) {
        Person person1=personRepository.findByUsername(person.getUsername()).get();
        person1.setConfines(person.getConfines());
        person1.setUser_role(person.getUser_role());
    }

}
