//package com.andreea.librarium.controller;
//
//import com.andreea.librarium.model.Utilizatori;
//
//import com.andreea.librarium.model.Rol;
//import com.andreea.librarium.model.RoluriUtilizatori;
//import com.andreea.librarium.repositories.RolRepository;
//import com.andreea.librarium.repositories.RoluriUtilizatoriRepository;
//import com.andreea.librarium.repositories.UtilizatoriRepository;
//import com.andreea.librarium.service.UsersService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//
//import java.util.List;
//import java.util.Optional;
//
//@Controller
//@RequestMapping("/admin_cititori")
//public class AdminCititoriController {
//
//    private  UsersService usersService;
//    private UtilizatoriRepository utilizatoriRepository;
//    private RolRepository rolRepository;
//    private RoluriUtilizatoriRepository roluriUtilizatoriRepository;
//
//    @Autowired
//    public AdminCititoriController(UsersService usersService,RoluriUtilizatoriRepository roluriUtilizatoriRepository,RolRepository rolRepository) {
//        this.usersService = usersService;
//        this.roluriUtilizatoriRepository=roluriUtilizatoriRepository;
//        this.rolRepository = rolRepository;
//    }
//
////    @GetMapping("/admin_cititori")
////    public String afiseazaUtilizatori(Model model) {
////        List<Utilizatori> utilizatori = usersService.getAllUsers();
////        System.out.println("Number of users: " + utilizatori.size());
////
////        model.addAttribute("utilizatori", utilizatori);
////        return "admin_cititori";
////    }
//
//    @GetMapping("/admin_adauga_cititor")
//    public String returnAdminAdaugaCititor(Model model) {
//        model.addAttribute("user", new Utilizatori());
//        return "admin_adauga_cititor";
//    }
//
//    @PostMapping("/adaugare")
//    public String addUser(@ModelAttribute Utilizatori utilizator) {
//        Utilizatori newUser = usersService.saveUser(utilizator);
//
//        if (newUser != null) {
//            return "admin_cititori";
//        } else {
//            return "error_page";
//        }
//    }
////    @GetMapping("admin_cititori/{id}")
////    public String read(@PathVariable("id") Integer id, Model model)
////    {
////        return "admin_cititori";
////    }
//
//
////    @GetMapping("/edit/{id}")
////    public String afiseazaFormularEditare(@PathVariable Long id, Model model) {
////        Utilizatori utilizator = usersService.getUtilizatorById(id);
////        model.addAttribute("utilizator",  utilizator);
////        return "admin_editeaza_cititor";
////    }
////    @GetMapping("/admin_editeaza_cititor")
////    public String afiseazaFormular() {
////        return "admin_editeaza_cititor";
////    }
//
//    ////////////////
//
////    @GetMapping("/admin_editeaza_cititor")
////    public String afiseazaFormularEditare(Model model) {
////        model.addAttribute("user", new Utilizatori());
////      //  Utilizatori utilizator = usersService.getUtilizatorById(id);
////       // model.addAttribute("utilizator", utilizator);
////        return "admin_editeaza_cititor";
////    }
//
//
//
//
//
//    ////////////////////////////////
////@GetMapping("/admin_editeaza_cititor/{id}")
////public String afiseazaFormularEditare(@PathVariable Long id, Model model) {
////    // Obțineți utilizatorul cu ID-ul specificat din serviciu sau din baza de date
////    Utilizatori utilizator = usersService.getUtilizatorById(Math.toIntExact(id));
////
////    // Verificați dacă utilizatorul există
////    if (utilizator != null) {
////        // Adăugați utilizatorul în model pentru a-l afișa în formular
////        model.addAttribute("utilizator", utilizator);
////
////        // Returnați numele paginii Thymeleaf unde veți afișa formularul de editare
////        return "admin_editeaza_cititor";
////    } else {
////        // Dacă utilizatorul nu există, puteți să tratați acest caz aici sau să redirecționați către o pagină de eroare
////        return "error_page"; // Înlocuiți "pagina_de_eroare" cu pagina de eroare reală
////    }
////}
//
//    @PostMapping("/edit/{id}")
//
//
//    public String actualizeazaUtilizator(@PathVariable Integer id, @ModelAttribute Utilizatori utilizator) {
//
//
////        Utilizatori existingUtilizator = usersService.getUtilizatorById(id);
//        Utilizatori existingUtilizator = utilizatoriRepository.getById(Long.valueOf(id));
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
//
//            Utilizatori updatedUtilizator = usersService.saveUser(existingUtilizator);
//
//            if (updatedUtilizator != null) {
//                return "admin_cititori";
//            }
//        }
//
//        return "error_page";
//    }
//
//
//
//
//
//    //////////////////
////@GetMapping("/admin_cititori/edit/{id}")
////public String afiseazaFormularEditare(@PathVariable Long id, Model model) {
////    // Obțineți utilizatorul din serviciu pe baza ID-ului
////    Utilizatori utilizator = usersService.getUtilizatorById(id);
////
////    // Verificați dacă utilizatorul există
////    if (utilizator != null) {
////        // Adăugați utilizatorul în model pentru a-l afișa în șablonul Thymeleaf
////        model.addAttribute("utilizator", utilizator);
////
////        // Returnați numele șablonului pentru pagina de editare
////        return "admin_editeaza_cititor";
////    } else {
////        // Dacă utilizatorul nu a fost găsit, puteți trata acest caz sau redirecționa către o pagină de eroare
////        return "pagina_de_eroare";
////    }
////}
////
////    @PostMapping("/edit/{id}")
////    public String actualizeazaUtilizator(@PathVariable Long id, @ModelAttribute Utilizatori utilizatori) {
////        Utilizatori existingUtilizator = usersService.getUtilizatorById(id);
////
////        if (existingUtilizator != null) {
////            existingUtilizator.setNume(utilizatori.getNume());
////            existingUtilizator.setPrenume(utilizatori.getPrenume());
////            existingUtilizator.setVarsta(utilizatori.getVarsta());
////            // ... și actualizați celelalte câmpuri
////
////            Utilizatori updatedUtilizator = usersService.saveUser(existingUtilizator);
////
////            if (updatedUtilizator != null) {
////                return "redirect:/admin_cititori";
////            }
////        }
////
////        return "error_page";
////    }
//}
