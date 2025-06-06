package com.example.POS.System.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user from database or any custom source
        if (username.equals("admin")) {
            return new User("admin", new BCryptPasswordEncoder().encode("admin123"), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}