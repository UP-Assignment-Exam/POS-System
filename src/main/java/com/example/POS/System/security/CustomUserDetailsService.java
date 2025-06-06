package com.example.POS.System.security;

import com.example.POS.System.exceptions.Custom.BadRequestException;
import com.example.POS.System.models.Role;
import com.example.POS.System.models.User;
import com.example.POS.System.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(Integer.parseInt(username)).orElseThrow(() -> new BadRequestException("User not Found!"));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), roleToAuthorities(user.getRole()));
    }

    private Collection<GrantedAuthority> roleToAuthorities(Role role) {
        return List.of(new SimpleGrantedAuthority(role.getName()));
    }
}
