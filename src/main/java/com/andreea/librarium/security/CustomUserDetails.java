//package com.andreea.librarium.security;
//
//import com.andreea.librarium.model.Utilizatori;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.Collections;
//
//public class CustomUserDetails implements UserDetails {
//
//    private final Utilizatori utilizatori;
//
//    public CustomUserDetails(Utilizatori utilizatori) {
//        this.utilizatori = utilizatori;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        // Define the user's authorities/roles here if needed
//        // For example, return a list of SimpleGrantedAuthority objects
//        // based on the user's roles in your application
//        // You can also return an empty list if you don't have roles
//        return Collections.emptyList();
//    }
//
//    @Override
//    public String getPassword() {
//        return utilizatori.getParola();
//    }
//
//    @Override
//    public String getUsername() {
//        return utilizatori.getEmail();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true; // You can implement account expiration logic here
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true; // You can implement account locking logic here
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true; // You can implement credentials expiration logic here
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true; // You can implement account enable/disable logic here
//    }
//}
