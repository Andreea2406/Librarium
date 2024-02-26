//package com.andreea.librarium.security;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import com.andreea.librarium.model.RoluriUtilizatori;
//import com.andreea.librarium.model.Utilizatori;
//import com.andreea.librarium.repositories.UtilizatoriRepository;
//
//import java.util.Collections;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UtilizatoriRepository userRepository; // Asume you have a User repository
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Utilizatori user = userRepository.findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
//
//        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getParola(),
//                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole()))); // Adjust according to your User entity
//    }
//}
//
//
//
////import com.andreea.librarium.model.Rol;
////import com.andreea.librarium.model.RoluriUtilizatori;
////import com.andreea.librarium.model.Utilizatori;
////import com.andreea.librarium.repositories.UtilizatoriRepository;
////import org.springframework.security.core.GrantedAuthority;
////import org.springframework.security.core.authority.SimpleGrantedAuthority;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.security.core.userdetails.UsernameNotFoundException;
////import org.springframework.stereotype.Service;
////import org.springframework.security.core.userdetails.UserDetailsService;
////
////import java.util.Collection;
////import java.util.stream.Collectors;
////
////@Service
////public class CustomUserDetailsService implements UserDetailsService {
////
////    private UtilizatoriRepository userRepository;
////
////    public CustomUserDetailsService(UtilizatoriRepository userRepository) {
////        this.userRepository = userRepository;
////    }
////
////    @Override
////    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
////        Utilizatori utilizatori = userRepository.findByEmail(email);
////
////        if (utilizatori != null) {
////            return new org.springframework.security.core.userdetails.User(utilizatori.getEmail(),
////                    utilizatori.getParola(),
////                    mapRolesToAuthorities(utilizatori.getRoluri()));
////        }else{
////            throw new UsernameNotFoundException("Invalid username or password.");
////        }
////    }
////
////    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Collection <RoluriUtilizatori> roles) {
////        Collection < ? extends GrantedAuthority> mapRoles = roles.stream()
////                .map(role -> new SimpleGrantedAuthority(role.getNumeRol()))
////                .collect(Collectors.toList());
////        return mapRoles;
////    }
////}