//package com.andreea.librarium.service;
//
//import com.andreea.librarium.model.Rol;
//import com.andreea.librarium.model.RoluriUtilizatori;
//import com.andreea.librarium.model.Utilizatori;
//import com.andreea.librarium.repositories.RoleRepository;
//import com.andreea.librarium.repositories.UtilizatoriRepository;
//import com.andreea.librarium.web.dto.UserRegistrationDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import java.util.List;
//
//import java.util.Arrays;
//import java.util.stream.Collectors;
//
//@Service
//public  class UserServiceImpl implements UserService {
//    //injection:
//    @Autowired
//    private  UtilizatoriRepository utilizatoriRepository;
//    private RoleRepository roleRepository;
//    private PasswordEncoder passwordEncoder;
//
//    public UserServiceImpl(UtilizatoriRepository utilizatoriRepository, RoleRepository roleRepository,PasswordEncoder passwordEncoder) {
//        //  super();
//        this.utilizatoriRepository = utilizatoriRepository;
//        this.roleRepository=roleRepository;
//        this.passwordEncoder=passwordEncoder;
//    }
//
//    @Override
//
//    public void  save(UserRegistrationDTO userRegistrationDTO) {
//        //return null;
//        Utilizatori utilizatori=new Utilizatori();
//        utilizatori.setNume(userRegistrationDTO.getNume()+" "+userRegistrationDTO.getPrenume());
//        utilizatori.setVarsta(userRegistrationDTO.getVarsta());
//        utilizatori.setTelefon(userRegistrationDTO.getTelefon());
//        utilizatori.setEmail(userRegistrationDTO.getEmail());
//        utilizatori.setStrada(userRegistrationDTO.getStrada());
//        utilizatori.setOras(userRegistrationDTO.getOras());
//        utilizatori.setCodPostal(userRegistrationDTO.getCodPostal());
//        utilizatori.setJudet(userRegistrationDTO.getJudet());
//        utilizatori.setApartament(userRegistrationDTO.getApartament());
//        utilizatori.setNumar(userRegistrationDTO.getNumar());
//        utilizatori.setOcupatie(userRegistrationDTO.getOcupatie());
//        utilizatori.setParola(passwordEncoder.encode(userRegistrationDTO.getParola()));
//    RoluriUtilizatori rol=roleRepository.findByName("ROLE_ADMIN");
//        if(rol == null){
//            rol = checkRoleExist();
//        }
//        utilizatori.setRoluri(Arrays.asList(rol));
//        utilizatoriRepository.save(utilizatori);
//
//
//
//    }
//    @Override
//    public Utilizatori findUserByEmail(String email) {
//        return utilizatoriRepository.findByEmail(email);
//    }
//
//    @Override
//    public List<UserRegistrationDTO> findAllUsers() {
//        List<Utilizatori> utilizatori = utilizatoriRepository.findAll();
//        return utilizatori.stream()
//                .map((user) -> mapToUserDto(user))
//                .collect(Collectors.toList());
//    }
//
//
//    private UserRegistrationDTO mapToUserDto(Utilizatori utilizatori){
//        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
//        String[] str = utilizatori.getNume().split(" ");
//        userRegistrationDTO.setPrenume(str[0]);
//        userRegistrationDTO.setNume(str[1]);
//        userRegistrationDTO.setEmail(utilizatori.getEmail());
//        return userRegistrationDTO;
//    }
//
//    private RoluriUtilizatori checkRoleExist(){
//        RoluriUtilizatori rol = new RoluriUtilizatori();
//        rol.setNumeRol("ROLE_ADMIN");
//        return roleRepository.save(rol);
//    }
//
//
////
////    @Override
////    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
////        Utilizatori utilizatori=utilizatoriRepository.findByEmail(username);
////        if(utilizatori==null){
////            throw new UsernameNotFoundException("Parola sau email invalid!");
////
////
////        }
////
////
////        return new org.springframework.security.core.userdetails.User(utilizatori.getEmail(),utilizatori.getPassword(),mapRolesToAuthorities(utilizatori.getRoluri()));
////
////    }
////    private Collection<?extends GrantedAuthority >mapRolesToAuthorities(Collection<Rol>roluri){
////        return roluri.stream().map(rol->new SimpleGrantedAuthority(rol.getNumeRol())).collect(Collectors.toList());
////
////
////    }
//
//}
