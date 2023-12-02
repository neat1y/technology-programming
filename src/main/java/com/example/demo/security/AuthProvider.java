package com.example.demo.security;

import com.example.demo.service.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthProvider implements AuthenticationProvider {
    private final PersonDetailsService personDetailsService;
    @Autowired
    public AuthProvider(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username=authentication.getName();
        UserDetails userDetails=personDetailsService.loadUserByUsername(username);
        String password =authentication.getCredentials().toString();
        if(!password.equals(userDetails.getPassword()))
        {
            throw  new BadCredentialsException("Incorrent password");
        }
          return  new UsernamePasswordAuthenticationToken(userDetails,password, Collections.emptyList());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
