//package com.andreea.librarium.security;
//
//import com.andreea.librarium.model.Rol;
//import com.andreea.librarium.model.RoluriUtilizatori;
//import com.andreea.librarium.model.Utilizatori;
//import com.andreea.librarium.repositories.UtilizatoriRepository;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.security.core.userdetails.UserDetailsService;
//
//import java.util.Collection;
//import java.util.stream.Collectors;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private UtilizatoriRepository userRepository;
//
//    public CustomUserDetailsService(UtilizatoriRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Utilizatori utilizatori = userRepository.findByEmail(email);
//
//        if (utilizatori != null) {
//            return new org.springframework.security.core.userdetails.User(utilizatori.getEmail(),
//                    utilizatori.getParola(),
//                    mapRolesToAuthorities(utilizatori.getRoluri()));
//        }else{
//            throw new UsernameNotFoundException("Invalid username or password.");
//        }
//    }
//
//    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Collection <RoluriUtilizatori> roles) {
//        Collection < ? extends GrantedAuthority> mapRoles = roles.stream()
//                .map(role -> new SimpleGrantedAuthority(role.getNumeRol()))
//                .collect(Collectors.toList());
//        return mapRoles;
//    }
//}