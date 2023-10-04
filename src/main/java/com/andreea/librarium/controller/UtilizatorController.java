package com.andreea.librarium.controller;


import com.andreea.librarium.model.Rol;
import com.andreea.librarium.model.RoluriUtilizatori;
import com.andreea.librarium.model.Utilizatori;
import com.andreea.librarium.repositories.RolRepository;
import com.andreea.librarium.repositories.RoluriUtilizatoriRepository;
import com.andreea.librarium.repositories.UtilizatoriRepository;
import com.andreea.librarium.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
//@ComponentScan(basePackages = {"C:\\Librarium\\src\\main\\java\\com\\andreea\\librarium\\service"})
public class UtilizatorController {
    //    @GetMapping("/")
//    public String showIndex() {
//        return "index";
//    }
//

    private UsersService usersService;
    private UtilizatoriRepository utilizatoriRepository;
    private RolRepository rolRepository;

    private RoluriUtilizatoriRepository roluriUtilizatoriRepository;
    @Autowired
    public UtilizatorController(UsersService usersService,RoluriUtilizatoriRepository roluriUtilizatoriRepository,RolRepository rolRepository) {

        this.usersService = usersService;
        this.roluriUtilizatoriRepository=roluriUtilizatoriRepository;
        this.rolRepository = rolRepository;
    }

//    public UtilizatorController( {
//
//    }


    @GetMapping("/creare_cont")
    public String returnCreareCont() {
        return "creare_cont";
    }

//    @Controller
//    public class RegistrationController {
//
//        }

    @GetMapping("/register")
    public String getCreareCont(Model model) {
        model.addAttribute("registerRequest",new Utilizatori());
        return "creare_cont";

    }

    @GetMapping("/utilizator/login")
    public String getLogare(Model model) {
        model.addAttribute("loginRequest",new Utilizatori());

        return "logare";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Utilizatori utilizatori) {
        System.out.println("register request: "+utilizatori);
        Utilizatori registeredUser= usersService.registrationUser(utilizatori.getNume(), utilizatori.getPrenume(),
       utilizatori.getVarsta(),utilizatori.getTelefon(),utilizatori.getEmail(),utilizatori.getStrada(),utilizatori.getOras()
                , utilizatori.getCodPostal(),
                utilizatori.getJudet(),utilizatori.getApartament(),utilizatori.getNumar(),
                utilizatori.getScara(),utilizatori.getOcupatie(),
                utilizatori.getParola()

        );
      //return registeredUser==null?"error_page":"redirect:/login";
        if (registeredUser != null) {
            // Create a UserRole entity and associate it with the registered user
            RoluriUtilizatori userRole = new RoluriUtilizatori();
            userRole.setIdUtilizator(registeredUser);
//            userRole.setIdRol(1); // Set the user role
            Optional<Rol> optionalRole = rolRepository.findByNumeRol("user");
            Rol userRoleEntity = optionalRole.orElse(null);

            //Rol userRoleEntity = rolRepository.findByName("user"); // Replace with actual repository method
            userRole.setIdRol(userRoleEntity); // Set the user role entity

            // Save the user role to the database
            usersService.saveUserRole(userRole);

            return "redirect:/login";
        } else {
            return "error_page";
        }
    }
    @PostMapping("/login")
    public String login(@ModelAttribute Utilizatori utilizatori,Model model) {
        System.out.println("login request: "+utilizatori);
        Utilizatori authenticated= usersService.authenticate(utilizatori.getEmail(),
                utilizatori.getParola());
        if(authenticated!=null){
            model.addAttribute("userLogin",authenticated.getEmail());
            return "cititor_pagina_principala";
        }else {

            return "error_page";
        }

    }
//    @GetMapping("/logare")
//    public String returnLogare() {
//        return "logare";
//    }

}


//    private UserService userService;
//
//    public UtilizatorController(UserService userService) {
//        super();
//        this.userService = userService;
//    }
//
//    @GetMapping("/")
//    public String getIndex() {
//        return "index";
//    }
//    @PostMapping("/register")
//    public String registerUserAccount(@ModelAttribute("user")UserRegistrationDTO registrationDTO){
//
//        userService.save(registrationDTO);
//        return "redirect:/register?success";
//    }
//    //@ModelAttribute("user")
////public  UserRegistrationDTO userRegistrationDTO(){
////        return new UserRegistrationDTO();
////}
//    @Autowired
//    private UtilizatoriRepository utilizatoriRepository;
//
//    @GetMapping("/register")
//    public String showRegistrationForm(Model model) {
//        model.addAttribute("user", new UserRegistrationDTO());
//        return "creare_cont";
//    }
////
////
////
////    @PostMapping("/register")
////    public String processRegistrationForm(@ModelAttribute("user") Utilizatori user) {
////
////        // Here you can add validation and processing logic
////        // Hash the password, set other fields, etc.
////        utilizatoriRepository.save(user);
////
////        return "redirect:/register:succes"; // Redirect to a success page
////    }
////    @GetMapping("/cititor_pagina_principala")
////    public String returnPaginaPrincipala(Model model) {
////        model.addAttribute("user", new Utilizatori());
////        return "cititor_pagina_principala";
////    }
////    @GetMapping("/creare_cont")
////    public String returnCreareCont(Model model) {
////        model.addAttribute("user", new Utilizatori());
////        return "creare_cont";
////    }
////    @GetMapping("/login")
////    public String returnLogare() {
////
////        return "logare";
////    }
//
//}
