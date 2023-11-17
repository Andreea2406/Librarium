package com.andreea.librarium.controller;

import com.andreea.librarium.model.Rol;
import com.andreea.librarium.model.RoluriUtilizatori;
import com.andreea.librarium.model.Utilizatori;
import com.andreea.librarium.repositories.RolRepository;
import com.andreea.librarium.repositories.RoluriUtilizatoriRepository;
import com.andreea.librarium.repositories.UtilizatoriRepository;
import com.andreea.librarium.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    @GetMapping("/admin_adauga_cititor")
    public String returnAdminAdaugaCititor(Model model) {
        model.addAttribute("user", new Utilizatori());
        return "admin_adauga_cititor"; // Returnați pagina cu formularul de adăugare a utilizatorului
    }
    @RequestMapping(value="admin_cititori/{id}", method = RequestMethod.GET)
    public String findById(@PathVariable Long id) {
        System.out.println("ID"+id);
        return "ID found"+id;
    }


//    @RequestMapping("/admin_editeaza_cititor/{id}")
//    public String afiseazaFormularEditare(@PathVariable Long id, Model model) {
////      Utilizatori utilizator = usersService.getUtilizatorById(Math.toIntExact(id));
//        Optional<Utilizatori> utilizator = utilizatoriRepository.findById(id);
//        System.out.println("aici");
//        model.addAttribute("utilizator", utilizator);
//        return "admin_editeaza_cititor";
//    }
//




//    @PostMapping("/update/{id}")
//    @PostMapping("/edit/{id}")
////    public String actualizeazaUtilizator(@PathVariable("id") Integer id, @Valid Utilizatori utilizator , Model model) {
//
//    public String actualizeazaUtilizator(@PathVariable Integer id, @ModelAttribute Utilizatori utilizator) {
//
//        Utilizatori existingUtilizator = usersService.getUtilizatorById(utilizator.getId());
//
//        if (existingUtilizator != null) {
//            System.out.println("aici");
//            existingUtilizator.setNume(utilizator.getNume());
//            existingUtilizator.setPrenume(utilizator.getPrenume());
//            existingUtilizator.setVarsta(utilizator.getVarsta());
//            existingUtilizator.setTelefon(utilizator.getTelefon());
//            existingUtilizator.setEmail(utilizator.getEmail());
//            existingUtilizator.setStrada(utilizator.getStrada());
//            existingUtilizator.setOras(utilizator.getOras());
//            existingUtilizator.setCodPostal(utilizator.getCodPostal());
//            existingUtilizator.setJudet(utilizator.getJudet());
//            existingUtilizator.setApartament(utilizator.getApartament());
//            existingUtilizator.setNumar(utilizator.getNumar());
//            existingUtilizator.setScara(utilizator.getScara());
//            existingUtilizator.setOcupatie(utilizator.getOcupatie());
//            existingUtilizator.setParola(utilizator.getParola());
//            // ... și actualizați celelalte câmpuri
//            utilizatoriRepository.save(existingUtilizator);
//            System.out.println("aici");
//            Utilizatori updatedUtilizator = usersService.saveUser(existingUtilizator);
//
//            if (updatedUtilizator != null) {
//                return "admin_cititori";
//            }
//        }
//        System.out.println("aici");
//        return "error_page";
//    }
//
////    @GetMapping("/admin_cititori/edit/{id}")
////    public String editCustomer(@PathVariable Integer id, Model model){
////        Utilizatori utilizator=usersService.getUtilizatorById(id);
//////        Customer thisCustomer=customerRepository.getById(id);
////        model.addAttribute("utilizator",utilizator);
////        return "admin_editeaza_cititor";
////    }
//@GetMapping("/admin_editeaza_cititor/{id}")
//public String afiseazaFormularEditare(@PathVariable Integer id, Model model) {
//    // Obțineți utilizatorul cu ID-ul specificat din serviciu sau din baza de date
//    Utilizatori utilizator = usersService.getUtilizatorById(id);
//
//    // Verificați dacă utilizatorul există
//    if (utilizator != null) {
//        // Adăugați utilizatorul în model pentru a-l afișa în formular
//        model.addAttribute("utilizator", utilizator);
//
//        // Returnați numele paginii Thymeleaf unde veți afișa formularul de editare
//        return "admin_editeaza_cititor";
//    } else {
//        // Dacă utilizatorul nu există, puteți să tratați acest caz aici sau să redirecționați către o pagină de eroare
//        return "error_page"; // Înlocuiți "pagina_de_eroare" cu pagina de eroare reală
//    }
//}














//    @GetMapping("/editare_cititor")
//    public String returnEditareCititor(Model model) {
//        model.addAttribute("user", new Utilizatori());
//        return "editare_cititor";
//    }




//    @PostMapping("/adaugare")
//    public String addUser(@ModelAttribute Utilizatori utilizatori) {
//        Utilizatori newUser = usersService.saveUser(utilizatori); // Apelați serviciul pentru a adăuga utilizatorul
//        System.out.println("Utilizatorul a fost adăugat cu succes: " + newUser.getId());
//
//        if (newUser != null) {
//            // Utilizatorul a fost adăugat cu succes
//            return "admin_cititori"; // Redirecționați la pagina de administrare a cititorilor
//        } else {
//            System.out.println("Adăugarea utilizatorului a eșuat.");
//            // Tratați cazul în care adăugarea a eșuat
//            return "error_page"; // Puteți redirecționa către o pagină de eroare sau face altceva
//        }
//    }
//    @GetMapping("/adaiugare")
//    public String getAdaugare(Model model) {
//        model.addAttribute("user",new Utilizatori());
//        return "admin_cititori";
//
//    }

//    @GetMapping("/admin_cititori")
//    public String afiseazaUtilizatori(Model model) {
//        List<Utilizatori> utilizatori = usersService.getAllUsers();
//        model.addAttribute("utilizatori", utilizatori);
//        return "admin_cititori";
//    }
//    @GetMapping("/admin_cititori")
//    public String returnAdminCititori() {
//        return "admin_cititori";
//    }
//








//    @GetMapping("/admin_adauga_cititor")
//    public String returnAdminAdaugaCititor() {
//        return "admin_adauga_cititor";
//    }
//    @GetMapping("/admin_cititori/new"){
//        public String showNewForm(Model model){
//            return j;
//        }
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
