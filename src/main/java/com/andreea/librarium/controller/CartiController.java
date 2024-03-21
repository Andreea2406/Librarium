package com.andreea.librarium.controller;

import com.andreea.librarium.config.UserSession;
import com.andreea.librarium.model.CartiFavorite;
import com.andreea.librarium.model.Utilizatori;
import com.andreea.librarium.model.Carti;

import com.andreea.librarium.repositories.CartiRepository;
import com.andreea.librarium.repositories.RolRepository;

import com.andreea.librarium.service.CartiFavoriteService;
import com.andreea.librarium.service.CartiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class CartiController {
    private RolRepository rolRepository;
    private CartiService cartiService;
    @Autowired
    private UserSession userSession;
    private CartiFavoriteService cartiFavoriteService;
    private  CartiRepository cartiRepository;
    @Autowired
    public CartiController(CartiService cartiService, CartiRepository cartiRepository,UserSession userSession,
                           CartiFavoriteService cartiFavoriteService) {
        this.cartiService=cartiService;
        this.cartiRepository=cartiRepository;
        this.userSession=userSession;
        this.cartiFavoriteService=cartiFavoriteService;

    }

//    @GetMapping("/admin_carti.html")
//    public String afisareAdminCarti() {
//        return "admin_carti";
//    }
    @GetMapping("/admin_adauga_carte")
    public String returnAdminAdaugaCarte(Model model) {
        model.addAttribute("carte", new Carti());
        return "admin_adauga_carte"; // Returnați pagina cu formularul de adăugare a cărții
    }
    @PostMapping("/adaugareCarte")
    public String addCarte(@ModelAttribute Carti carte) {
        Carti newCarte = cartiService.saveCarte(carte);

        if (newCarte != null) {
            return "admin_carti";
        } else {
            return "error_page";
        }
    }
    @GetMapping("/admin_carti.html")
    public String afiseazaCartiile(Model model) {

        List<Carti> carti = cartiService.getAllBooks();
        model.addAttribute("carti", carti);
        return "admin_carti";
    }
//    @GetMapping("/cititor_catalog.html")
//    public String returnCititorCatalog(Model model) {
//
//        //String titluCarte = cartiService.obtineTitluCarte();
//
//        // adaugă titlul în model pentru a fi accesibil în Thymeleaf
//        // model.addAttribute("titluCarte", titluCarte);
//        List<Carti> carti = cartiService.getAllBooks();
//        model.addAttribute("carti", carti);
//        return "cititor_catalog";
//    }



    @GetMapping("/cititor_catalog.html")
    public String returnCititorCatalog(Model model) {
        Integer userId = userSession.getUserId(); // Obține ID-ul din sesiune

        List<Carti> carti = cartiService.getAllBooks();
        List<CartiFavorite> cartiFavorite = cartiFavoriteService.findFavoriteCartiIdsByUserId(userId);

        // Transform the list of CartiFavorite entities into a list of book IDs
        List<Integer> cartiFavoriteIds = cartiFavorite.stream()
                .map(CartiFavorite::getCarteId) // Extract the book ID
                .collect(Collectors.toList());

        model.addAttribute("carti", carti);
        model.addAttribute("cartiFavoriteIds", cartiFavoriteIds);

        return "cititor_catalog";
    }

//    @GetMapping("/cititor_catalog.html")
//    public String returnCititorCatalog(Model model) {
//        // Presupunând că ai o metodă pentru a obține ID-ul utilizatorului curent
//        Integer userId = userSession.getUserId(); // Aceeași presupunere ca mai sus
//
//        List<Carti> carti = cartiService.getAllBooks();
//        List<Integer> cartiFavoriteIds = cartiFavoriteService.findFavoriteCartiIdsByUserId(userId);
//
//        // Opțional: Transformă lista de cărți într-o listă de DTO-uri dacă ai nevoie să adaugi informații suplimentare
//        // Pentru simplitate, vom presupune că lucrăm direct cu entități și adăugăm doar ID-urile cărților favorite în model
//        model.addAttribute("carti", carti);
//        model.addAttribute("cartiFavoriteIds", cartiFavoriteIds);
//
//        return "cititor_catalog";
//    }

    @GetMapping("cititor_carte/{id}")
public String afisCarteCititor(@PathVariable("id") Integer id, Model model){
        Carti carti= cartiService.getBookById(id);
    model.addAttribute("carti", carti);
    return "cititor_carte";

}
    @GetMapping("admin_carti/edit/{id}")
    public String read(@PathVariable("id") Integer id, Model model)
    {
//        Carti carti=cartiService.getBookById(id);
        Carti carti = cartiService.getBookById(id);

        model.addAttribute("carti", carti);
        return "admin_editeaza_carte";
    }
    @GetMapping("/admin_editeaza_carte/{id}")
    public String afiseazaFormularEditare(@PathParam("id") Long id, Model model) {
        System.out.println("aici");
        return "admin_editeaza_carte";
    }
    @PostMapping("/admin_carti/{id}")

    public String actualizeazaCarte(@PathVariable Integer id, @ModelAttribute("carti") Carti carti, Model model) {

        Carti existingCarte=cartiService.getBookById(id);
//        Utilizatori existingUtilizator = utilizatoriRepository.getById(Long.valueOf(id));


        if (existingCarte != null) {
            System.out.println("aici");
            existingCarte.setTitlu(carti.getTitlu());
            existingCarte.setAutor(carti.getAutor());
            existingCarte.setCategorie(carti.getCategorie());
            existingCarte.setLimba(carti.getLimba());
            existingCarte.setEditura(carti.getEditura());
            existingCarte.setTipCoperta(carti.getTipCoperta());
            existingCarte.setNrPagini(carti.getNrPagini());
            existingCarte.setColectie(carti.getColectie());
            existingCarte.setIsbn(carti.getIsbn());
            existingCarte.setLatime(carti.getLatime());
            existingCarte.setInaltime(carti.getInaltime());
            existingCarte.setDescriere(carti.getDescriere());
            existingCarte.setDataPublicarii(carti.getDataPublicarii());
            existingCarte.setDisponibilitate(carti.getDisponibilitate());

            Carti updatedCarte=cartiService.updateCarte(existingCarte);

            if (updatedCarte != null) {
//                return "admin_cititori";
                return "admin_carti";

            }
        }

        return "error_page";
    }


    @GetMapping("/admin_carti/{id}")
    public String deleteCarte(@PathVariable Integer id){
        cartiService.deleteCarteById(id);
        return "redirect:/admin_carti";
    }


    // Presupunând că ai o metodă care returnează o listă de cărți și informații despre favorite
// Metoda din controller care pregătește datele pentru pagina cu cărți








//    @GetMapping("/admin_adauga_carte")
//    public String returnAdminAdaugaCarte(Model model) {
//        model.addAttribute("user", new Utilizatori());
//        return "admin_adauga_carte"; // Returnați pagina cu formularul de adăugare a utilizatorului
//    }
}
