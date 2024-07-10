package com.andreea.librarium.controller;

import com.andreea.librarium.config.UserSession;
import com.andreea.librarium.model.*;
import com.andreea.librarium.repositories.CartiRepository;
import com.andreea.librarium.repositories.RolRepository;
import com.andreea.librarium.repositories.RoluriUtilizatoriRepository;
import com.andreea.librarium.repositories.UtilizatoriRepository;
import com.andreea.librarium.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.websocket.server.PathParam;
import java.util.*;
import java.util.stream.Collectors;

import static net.minidev.asm.DefaultConverter.convertToLong;

@Controller
public class UtilizatorController {

    private UsersService usersService;
    private RolService rolService;
    private UtilizatoriRepository utilizatoriRepository;
    private RoluriUtilizatori roluriUtilizatori;
    private EvenimenteService evenimenteService;
    @Autowired
    private CartiService cartiService;

    private RolRepository rolRepository;
    private Utilizatori utilizatori;
    @Autowired
    private UserSession userSession;
    private RoluriUtilizatoriRepository roluriUtilizatoriRepository;
    private RezervareCarteService rezervareCarteService;
    private ImprumuturiService imprumuturiService;
    private final CartiRepository cartiRepository;
    private final ParticipantConversatieService participantConversatieService;


    @Autowired
    public UtilizatorController(UsersService usersService, RoluriUtilizatoriRepository roluriUtilizatoriRepository, RolRepository rolRepository,
                                RolService rolService,
                                RezervareCarteService rezervareCarteService,
                                ImprumuturiService imprumuturiService,
                                UserSession userSession,
                                CartiService cartiService,

                                EvenimenteService evenimenteService, CartiRepository cartiRepository, ParticipantConversatieService participantConversatieService) {

        this.usersService = usersService;
        this.roluriUtilizatoriRepository=roluriUtilizatoriRepository;
        this.rolRepository = rolRepository;
        this.rolService=rolService;
        this.rezervareCarteService=rezervareCarteService;
        this.imprumuturiService=imprumuturiService;
        this.userSession=userSession;
        this.cartiService=cartiService;
        this.evenimenteService=evenimenteService;
        this.cartiRepository = cartiRepository;
        this.participantConversatieService = participantConversatieService;
    }




    @GetMapping("/creare_cont")
    public String returnCreareCont() {
        return "creare_cont";
    }
    @GetMapping("/cauta")
    public String searchBooks(@RequestParam("query") String query, Model model) {
        List<Carti> carti = cartiService.searchBooks(query);
        if (carti.isEmpty()) {
            model.addAttribute("errorMessage", "Nu există cărți care să corespundă interogării tale.");
            return "error_page";

        }
        model.addAttribute("carti", carti);
        model.addAttribute("isCatalogPage", false);

        return "catalog";
    }
    @GetMapping("/filtrare")
    public String filterBooks(@RequestParam(value = "query", required = false) String query,
                              @RequestParam(value = "categorie", required = false) String categorie,
                              @RequestParam(value = "autor", required = false) String autor,
                              @RequestParam(value = "editura", required = false) String editura,
                              @RequestParam(value = "limba", required = false) String limba,
                              Model model) {

        List<Carti> cartiList = cartiService.filterBooks(query, categorie, autor, editura, limba);


        model.addAttribute("carti", cartiList);
        model.addAttribute("query", query);
        model.addAttribute("categorie", categorie);
        model.addAttribute("autor", autor);
        model.addAttribute("editura", editura);
        model.addAttribute("limba", limba);

        return "catalog";
    }
    @GetMapping("/cititor_pagina_principala.html")
    public String returnCititorPaginaPrincipala(Model model) {
        Integer idUtilizatorLogat = userSession.getUserId();
        model.addAttribute("userId",idUtilizatorLogat);
        Integer userId = userSession.getUserId();
        List<Evenimente> evenimente = evenimenteService.getAllEvenimente();
        List<Carti> carti = cartiService.findFirst9();
        List<Evenimente> firstFourEvenimente = evenimente.stream().limit(3).collect(Collectors.toList());
        model.addAttribute("carti1", carti.subList(0, 3));
        model.addAttribute("carti2", carti.subList(3, 6));
        model.addAttribute("carti3", carti.subList(6, 9));
        model.addAttribute("evenimente", evenimente);



        if (userId != null) {
            model.addAttribute("userSession", userSession);
        } else {
            System.out.println("EROARE");

        }
        return "cititor_pagina_principala";
    }


    @GetMapping("/cititor_mesaje.html")
    public String returnCititorMesaje() {

        return "cititor_mesaje";
    }



    @GetMapping("/carte.html")
    public String returnCarte() {
        return "carte";
    }

    @GetMapping("carte/{id}")
    public String afisCarte(@PathVariable("id") Integer id, Model model){
        Carti carti= cartiService.getBookById(id);
        model.addAttribute("carti", carti);
        List<Carti> randomBooks = cartiService.getRandomBooks(3);
        model.addAttribute("randomBooks", randomBooks);
        return "carte";

    }




    @GetMapping("/cititor_carte.html")
    public String returnCititorCarte() {
        return "cititor_carte";
    }


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
        Utilizatori registeredUser= usersService.registrationUser(utilizatori.getNume(), utilizatori.getPrenume(),
                utilizatori.getVarsta(),utilizatori.getTelefon(),utilizatori.getEmail(),utilizatori.getStrada(),utilizatori.getOras()
                , utilizatori.getCodPostal(),
                utilizatori.getJudet(),utilizatori.getApartament(),utilizatori.getNumar(),
                utilizatori.getScara(),utilizatori.getOcupatie(),
                utilizatori.getParola()

        );
        if (registeredUser != null) {
            RoluriUtilizatori userRole = new RoluriUtilizatori();
            userRole.setIdUtilizator(registeredUser);
            Optional<Rol> optionalRole = rolRepository.findByNumeRol("user");
            Rol userRoleEntity = optionalRole.orElse(null);

            userRole.setIdRol(userRoleEntity);

            usersService.saveUserRole(userRole);
            participantConversatieService.addUser(1, registeredUser.getId());

            return "redirect:/login";
        } else {
            return "error_page";
        }
    }

@PostMapping("/login")
public String login(@ModelAttribute Utilizatori utilizatori, Model model) {
    Utilizatori authenticated = usersService.authenticate(utilizatori.getEmail(), utilizatori.getParola());

    if (authenticated != null) {
        userSession.setUserId(authenticated.getId());

        Set<Rol> roles = usersService.getRolesForUser(authenticated.getId());
        model.addAttribute("userId", authenticated.getId());

        if (roles.stream().anyMatch(rol -> "admin".equals(rol.getNumeRol()))) {
            model.addAttribute("userLogin", authenticated.getEmail());
            return "admin_pagina_principala";
        } else if (roles.stream().anyMatch(rol -> "user".equals(rol.getNumeRol()))) {
            model.addAttribute("userLogin", authenticated.getEmail());
            model.addAttribute("userSession", authenticated.getId());

            return "redirect:/cititor_pagina_principala.html";
        }
        else if (roles.stream().anyMatch(rol -> "bibliotecar".equals(rol.getNumeRol()))) {
            model.addAttribute("userLogin", authenticated.getEmail());
            return "redirect:/bibliotecar_pagina_principala";

        }else {
            return "error_page";
        }
    } else {
        return "error_page";
    }
}

    @GetMapping("/admin_adauga_cititor")
    public String returnAdminAdaugaCititor(Model model) {
        model.addAttribute("user", new Utilizatori());
        return "admin_adauga_cititor";
    }






    @GetMapping("admin_cititori/edit/{id}")
    public String read(@PathVariable("id") Integer id, Model model) {
        Utilizatori utilizatori = usersService.getStudentById(id);
        Set<RoluriUtilizatori> roluriUtilizatoris = utilizatori.getRoluriUtilizatoris();

        if (!roluriUtilizatoris.isEmpty()) {
            Iterator<RoluriUtilizatori> iterator = roluriUtilizatoris.iterator();
            RoluriUtilizatori firstRol = iterator.next();
            String currentRole = firstRol.getIdRol().getNumeRol();
            utilizatori.setSelectedRole(currentRole);
            model.addAttribute("currentRole", currentRole);
        } else {
            model.addAttribute("currentRole", "");
        }

        model.addAttribute("utilizatori", utilizatori);

        String otherRole;
        if ("bibliotecar".equals(model.getAttribute("currentRole"))) {
            otherRole = "user";
        } else {
            otherRole = "bibliotecar";
        }
        model.addAttribute("otherRole", otherRole);

        List<Rol> listaRoluri = usersService.obtineRoluri();
        model.addAttribute("listaRoluri", listaRoluri);

        return "admin_editeaza_cititor";
    }

    @GetMapping("/admin_editeaza_cititor/{id}")
    public String afiseazaFormularEditare(@PathParam("id") Long id, Model model) {

        return "admin_editeaza_cititor";
    }



    @PostMapping("/adaugare")
    public String addUser(@ModelAttribute Utilizatori utilizator) {
        Utilizatori newUser = usersService.saveUser(utilizator);
        RoluriUtilizatori userRole = new RoluriUtilizatori();
        userRole.setIdUtilizator(newUser);
        Optional<Rol> optionalRole = rolRepository.findByNumeRol("user");
        Rol userRoleEntity = optionalRole.orElse(null);

        userRole.setIdRol(userRoleEntity);

        usersService.saveUserRole(userRole);
        if (newUser != null) {
            return "redirect:/admin_cititori";
        } else {
            return "error_page";
        }
    }



    @GetMapping("/admin_cititori")
    public String afiseazaUtilizatori(Model model) {

        List<Utilizatori> utilizatori = usersService.getAllUsers();
        model.addAttribute("utilizatori", utilizatori);
        return "admin_cititori";
    }


    @PostMapping("/admin_cititori/{id}")
    public String actualizeazaUtilizator(@PathVariable Integer id,
                                         @ModelAttribute("utilizatori") Utilizatori utilizator,
                                         @RequestParam(name = "role", required = false) List<Integer> selectedRoles,
                                         Model model) {

        Utilizatori existingUtilizator = usersService.getStudentById(id);

        if (existingUtilizator != null) {
            existingUtilizator.setNume(utilizator.getNume());
            existingUtilizator.setPrenume(utilizator.getPrenume());
            existingUtilizator.setVarsta(utilizator.getVarsta());
            existingUtilizator.setTelefon(utilizator.getTelefon());
            existingUtilizator.setEmail(utilizator.getEmail());
            existingUtilizator.setStrada(utilizator.getStrada());
            existingUtilizator.setOras(utilizator.getOras());
            existingUtilizator.setCodPostal(utilizator.getCodPostal());
            existingUtilizator.setJudet(utilizator.getJudet());
            existingUtilizator.setApartament(utilizator.getApartament());
            existingUtilizator.setNumar(utilizator.getNumar());
            existingUtilizator.setScara(utilizator.getScara());
            existingUtilizator.setOcupatie(utilizator.getOcupatie());
            existingUtilizator.setParola(utilizator.getParola());

            if (selectedRoles != null) {
                Set<Rol> rolesToAdd = new HashSet<>();

                for (Integer roleId : selectedRoles) {
                    rolRepository.findById(roleId).ifPresent(rolesToAdd::add);
                }

                existingUtilizator.setRole(rolesToAdd);
            }
            Utilizatori updatedUtilizator = usersService.updateUtilizator(existingUtilizator);

            if (updatedUtilizator != null) {
                return "redirect:/admin_cititori";
            }
        }

        return "error_page";
    }


    @GetMapping("/admin_cititori/{id}")
public String deleteUser(@PathVariable Integer id){
        usersService.deleteUserById(id);
        return "redirect:/admin_cititori";
}

    @GetMapping("/admin_cititori/cautare")
    public String cautaUtilizatori(@RequestParam("nume") String nume, Model model) {
        List<Utilizatori> utilizatori = usersService.searchUsersByName(nume);
        model.addAttribute("utilizatori", utilizatori);
        model.addAttribute("cautareActiva", true);

        return "admin_cititori";
    }
    @GetMapping("/cititor_rezervari_imprumuturi.html")
    public String afiseazaRezervarileSiImprumuturileUtilizatorului(Model model) {
        Integer idUtilizatorLogat = userSession.getUserId();

        List<Imprumuturi> imprumuturiList = imprumuturiService.getImprumuturiByUserId(idUtilizatorLogat);
        List<RezervariCarti> rezervariList = rezervareCarteService.getRezervariByUserId(idUtilizatorLogat);
        model.addAttribute("imprumuturi", imprumuturiList);
        model.addAttribute("rezervari", rezervariList);

        return "cititor_rezervari_imprumuturi";
    }
    @PostMapping("/anuleaza-imprumut")
    public String anuleazaImprumut(@RequestParam("idImprumut") Integer idImprumut, RedirectAttributes redirectAttributes) {
        boolean rezultat = imprumuturiService.anuleazaImprumut(idImprumut);

        if (rezultat) {
            redirectAttributes.addFlashAttribute("mesajSucces", "Împrumutul a fost anulat cu succes!");
        } else {
            redirectAttributes.addFlashAttribute("mesajEroare", "Nu s-a putut anula împrumutul.");
        }

        return "redirect:/cititor_rezervari_imprumuturi.html";
    }

    @PostMapping("/anuleaza-rezervare")
    public String anuleazaRezervare(@RequestParam("idRezervare") Integer idRezervare, RedirectAttributes redirectAttributes) {
        boolean rezultat = rezervareCarteService.anuleazaRezervare(idRezervare);

        if (rezultat) {
            redirectAttributes.addFlashAttribute("mesajSucces", "Rezervarea a fost anulată cu succes!");
        } else {
            redirectAttributes.addFlashAttribute("mesajEroare", "Nu s-a putut anula rezervarea.");
        }

        return "redirect:/cititor_rezervari_imprumuturi.html";
    }







    @GetMapping("/")
    public String indexPage(Model model) {
        List<Evenimente> evenimente = evenimenteService.getAllEvenimente();
        model.addAttribute("evenimente", evenimente);
        List<Carti> carti = cartiService.findFirst9();

        model.addAttribute("carti1", carti.subList(0, 3));
        model.addAttribute("carti2", carti.subList(3, 6));
        model.addAttribute("carti3", carti.subList(6, 9));
        return "index.html";
    }
    @GetMapping("/evenimente.html")
    public String returnEvenimente(Model model) {
        List<Evenimente> evenimente = evenimenteService.getAllEvenimente();
        model.addAttribute("evenimente", evenimente);
        return "evenimente.html";
    }
    @GetMapping("/catalog.html")
    public String afiseazaCatalog(Model model,@RequestParam(defaultValue = "0") int page){
        int size = 12;

        PageRequest pageRequest = PageRequest.of(page, size);
        List<Carti> allBooks = cartiRepository.findAll();
        List<Carti> uniqueBooks = allBooks.stream()
                .distinct()
                .collect(Collectors.toList());
        int totalBooks = uniqueBooks.size();
        int totalPages = (int) Math.ceil((double) totalBooks / size);

        int start = Math.min(page * size, totalBooks);
        int end = Math.min((start + size), totalBooks);
        List<Carti> paginatedBooks = uniqueBooks.subList(start, end);
        Page<Carti> cartiPage = new PageImpl<>(uniqueBooks.subList(start, end), pageRequest, uniqueBooks.size());

        model.addAttribute("carti", cartiPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("isCatalogPage", true);

        return "catalog";


    }





    @GetMapping ("/admin_cititori/makeBibliotecar/{id}")
    public String makeBibliotecar(@PathVariable("id") Integer id, Model model) {
        Utilizatori utilizatori = usersService.getStudentById(id);
        model.addAttribute("utilizatori", utilizatori);

        return "rol_bibliotecar";
    }
    @GetMapping("/rol_bibliotecar")
    public String afiseazaPaginaRolBibliotecar(Model model) {
        return "rol_bibliotecar";
    }
}






















