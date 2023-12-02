package com.example.demo.service;

import com.example.demo.models.Person;
import com.example.demo.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {
    private final PersonService personService;
    @Autowired
    public PersonDetailsService(PersonService personService) {
        this.personService = personService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person=personService.findbyName(username);
        if(person.isEmpty()){
            throw  new UsernameNotFoundException("user not found");
        }
        return new PersonDetails(person.get());
    }
}
