package com.andreea.librarium.security;

//import com.example.registrationlogindemo.entity.Role;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//
//import java.util.Collections;
//
//public abstract class CustomUserDetailsService implements UserDetails {
//    private String username;
//    private String password;
//    private Collection<? extends GrantedAuthority> authorities;
//
//    public void CustomUserDetails(String username, String password, String role) {
//        this.username = username;
//        this.password = password;
//        // Create a collection of authorities (roles and permissions)
//        this.authorities = Collections.singleton(new SimpleGrantedAuthority(role));
//    }
//
//    // Implement UserDetails methods...
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    // Implement other UserDetails methods...
//
//    // ...
//}