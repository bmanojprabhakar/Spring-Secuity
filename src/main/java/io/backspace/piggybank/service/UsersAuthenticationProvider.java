package io.backspace.piggybank.service;

import io.backspace.piggybank.model.Customer;
import io.backspace.piggybank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UsersAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<Customer> registeredUsers = customerRepository.findByEmail(userName);

        if(registeredUsers.size() > 0) {
            if(passwordEncoder.matches(password, registeredUsers.get(0).getPassword())) {
                return new UsernamePasswordAuthenticationToken(userName, password, Collections.singleton(new SimpleGrantedAuthority(registeredUsers.get(0).getRole())));
             } else {
                throw new BadCredentialsException("Password is incorrect");
            }
        } else {
            throw new UsernameNotFoundException("User name not registered");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
