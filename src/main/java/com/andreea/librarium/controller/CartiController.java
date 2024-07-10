package com.andreea.librarium.controller;

import com.andreea.librarium.config.UserSession;
import com.andreea.librarium.model.CartiFavorite;
import com.andreea.librarium.model.Utilizatori;
import com.andreea.librarium.model.Carti;

import com.andreea.librarium.repositories.CartiRepository;
import com.andreea.librarium.repositories.RolRepository;

import com.andreea.librarium.service.CartiFavoriteService;
import com.andreea.librarium.service.CartiService;
import com.andreea.librarium.service.RecommendationService;
import com.andreea.librarium.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
public class CartiController {
    private RolRepository rolRepository;
    private CartiService cartiService;
    @Autowired
    private UserSession userSession;
    private CartiFavoriteService cartiFavoriteService;
    private UsersService usersService;
    private  CartiRepository cartiRepository;


    @Autowired
    private RecommendationService recommendationService;
    @Autowired
    public CartiController(CartiService cartiService, CartiRepository cartiRepository,UserSession userSession,
                           CartiFavoriteService cartiFavoriteService,RecommendationService recommendationService,
                           UsersService usersService) {
        this.cartiService=cartiService;
        this.cartiRepository=cartiRepository;
        this.userSession=userSession;
        this.cartiFavoriteService=cartiFavoriteService;
        this.usersService=usersService;
        this.recommendationService = recommendationService;

    }


    @GetMapping("/admin_adauga_carte")
    public String returnAdminAdaugaCarte(Model model) {
        model.addAttribute("carte", new Carti());
        return "admin_adauga_carte";
    }
    @PostMapping("/adaugareCarte")
    public String addCarte(@ModelAttribute Carti carte) {
        Carti newCarte = cartiService.saveCarte(carte);

        if (newCarte != null) {
            return "redirect:/admin_carti.html";
        } else {
            return "error_page";
        }
    }
    @GetMapping("/admin_carti.html")
    public String afiseazaCarti(Model model, @RequestParam(value = "searchTerm", required = false) String searchTerm) {
        List<Carti> carti;
        if (searchTerm != null && !searchTerm.isEmpty()) {
            carti = cartiService.searchBooksByTerm(searchTerm);
            if (carti.isEmpty()) {
                model.addAttribute("errorMessage", "Nu există cărți care să corespundă interogării tale.");
            }
        } else {
            carti = cartiService.getAllBooks();
        }
        model.addAttribute("carti", carti);
        model.addAttribute("searchTerm", searchTerm);
        return "admin_carti";
    }



    @GetMapping("/search")
    public String searchBooks(@RequestParam("query") String query, Model model) {
    List<Carti> carti = cartiService.searchBooks(query);
        if (carti.isEmpty()) {
            model.addAttribute("errorMessage", "Nu există cărți care să corespundă interogării tale.");
            return "error_search";

        }
        model.addAttribute("carti", carti);
        model.addAttribute("isCatalogPage", false);

        return "cititor_catalog";
}


    @GetMapping("/cititor_catalog.html")
    public String returnCititorCatalog(Model model,@RequestParam(defaultValue = "0") int page) {
        int size = 12;
        Integer userId = userSession.getUserId();
        List<Carti> userBooks = usersService.getUserInteractedBooks(userId);// Obține cărțile cu care utilizatorul a interacționat
        final List<Carti> recommendedBooks;
        Set<Integer> cartiFavoriteIds = new HashSet<>();
        //cartiFavoriteIds: Este un HashSet care va stoca ID-urile cărților favorite ale utilizatorului.
        // HashSet este folosit pentru acces rapid și eliminarea duplicatelor, asigurându-se că fiecare ID este unic.

        // Verifică dacă utilizatorul a interacționat cu cărți
        if (!userBooks.isEmpty()) {
            // Obține categoriile de interes ale utilizatorului pe baza cărților cu care a interacționat

            Set<String> categories = recommendationService.getUserBookCategories(userBooks);
            // Obține cărți recomandate pe baza categoriilor de interes și a cărților utilizatorului
            //Pe baza cărților cu care utilizatorul a interacționat, se obțin categoriile de interes.
            // Set<String> este folosit pentru a evita redundanța, astfel încât fiecare categorie să fie unică.

            recommendedBooks = recommendationService.getRecommendedBooks(categories, userBooks);
            // Obține cărțile favorite ale utilizatorului

            List<Carti> favoriteBooks = usersService.getUserInteractedBooks(userId);
            cartiFavoriteIds = favoriteBooks.stream().map(Carti::getId).collect(Collectors.toSet());
            //Se obțin din nou cărțile cu care utilizatorul a interacționat (pentru a extrage doar favoritele).
            // Se folosesc stream() și map() pentru a extrage ID-urile cărților și a le colecta într-un HashSet.
        } else {
            recommendedBooks = new ArrayList<>();// Dacă utilizatorul nu a interacționat cu nicio carte, nu există recomandări
        }
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Carti> allBooks = new ArrayList<>(recommendedBooks);
        allBooks.addAll(cartiRepository.findAll());
        List<Carti> uniqueBooks = allBooks.stream()
                .distinct()
                .collect(Collectors.toList());
        int totalBooks = uniqueBooks.size();
        int totalPages = (int) Math.ceil((double) totalBooks / size);
        // Determină indexul de start și de final pentru paginare

        int start = Math.min(page * size, totalBooks);
        int end = Math.min((start + size), totalBooks);
        List<Carti> paginatedBooks = uniqueBooks.subList(start, end);
        Page<Carti> cartiPage = new PageImpl<>(uniqueBooks.subList(start, end), pageRequest, uniqueBooks.size());
        // Adaugă datele necesare în model pentru a fi utilizate în vizualizarea Thymeleaf

        model.addAttribute("recommendedBooks", recommendedBooks);
        model.addAttribute("carti", cartiPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", (int) Math.ceil((double) uniqueBooks.size() / size));
        model.addAttribute("hasRecommendations", !recommendedBooks.isEmpty());
        model.addAttribute("cartiFavoriteIds", cartiFavoriteIds);
        model.addAttribute("isCatalogPage", true);

        return "cititor_catalog";
    }

    @GetMapping("/filter")
    public String filterBooks(@RequestParam(value = "query", required = false) String query,
                              @RequestParam(value = "categorie", required = false) String categorie,
                              @RequestParam(value = "autor", required = false) String autor,
                              @RequestParam(value = "editura", required = false) String editura,
                              @RequestParam(value = "limba", required = false) String limba,
                              Model model) {
        Integer userId = userSession.getUserId();

        List<Carti> cartiList = cartiService.filterBooks(query, categorie, autor, editura, limba);
        List<Carti> favoriteBooks = usersService.getUserInteractedBooks(userId);
        Set<Integer> cartiFavoriteIds = favoriteBooks.stream().map(Carti::getId).collect(Collectors.toSet());


        model.addAttribute("carti", cartiList);
        model.addAttribute("query", query);
        model.addAttribute("categorie", categorie);
        model.addAttribute("autor", autor);
        model.addAttribute("editura", editura);
        model.addAttribute("limba", limba);
        model.addAttribute("cartiFavoriteIds", cartiFavoriteIds);

        return "cititor_catalog";
    }
    @GetMapping("/resetFilters")
    public String resetFilters() {
        return "cititor_catalog";
    }

    @GetMapping("cititor_carte/{id}")
    public String afisCarteCititor(@PathVariable("id") Integer id, Model model){
        Carti carti= cartiService.getBookById(id);
    model.addAttribute("carti", carti);
        List<Carti> randomBooks = cartiService.getRandomBooks(3);
        model.addAttribute("randomBooks", randomBooks);
    return "cititor_carte";

}
    @GetMapping("admin_carti/edit/{id}")
    public String read(@PathVariable("id") Integer id, Model model)
    {
        Carti carti = cartiService.getBookById(id);

        model.addAttribute("carti", carti);
        return "admin_editeaza_carte";
    }
    @GetMapping("/admin_editeaza_carte/{id}")
    public String afiseazaFormularEditare(@PathParam("id") Long id, Model model) {
        return "admin_editeaza_carte";
    }

    @GetMapping("/admin_carti/search")
    public String adminSearchBooks(@RequestParam("searchTerm") String searchTerm, Model model) {
        List<Carti> carti = cartiService.searchBooksByTerm(searchTerm);
        if (carti.isEmpty()) {
            model.addAttribute("errorMessage", "Nu există cărți care să corespundă interogării tale.");
        }
        model.addAttribute("carti", carti);
        model.addAttribute("searchTerm", searchTerm);
        return "admin_carti";
    }

    @PostMapping("/admin_carti/{id}")

    public String actualizeazaCarte(@PathVariable Integer id, @ModelAttribute("carti") Carti carti, Model model) {

        Carti existingCarte=cartiService.getBookById(id);


        if (existingCarte != null) {
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
                return "redirect:/admin_carti.html";

            }
        }

        return "error_page";
    }


    @GetMapping("/admin_carti/{id}")
    public String deleteCarte(@PathVariable Integer id){
        cartiService.deleteCarteById(id);
        return "redirect:/admin_carti.html";

    }











}
