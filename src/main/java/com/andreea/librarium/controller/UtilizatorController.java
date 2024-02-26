package com.andreea.librarium.controller;

import com.andreea.librarium.config.UserSession;
import com.andreea.librarium.model.*;
import com.andreea.librarium.repositories.RolRepository;
import com.andreea.librarium.repositories.RoluriUtilizatoriRepository;
import com.andreea.librarium.repositories.UtilizatoriRepository;
import com.andreea.librarium.service.*;
import com.andreea.librarium.config.UserSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.*;

@Controller
//@ComponentScan(basePackages = {"C:\\Librarium\\src\\main\\java\\com\\andreea\\librarium\\service"})
public class UtilizatorController {
    //    @GetMapping("/")
//    public String showIndex() {
//        return "index";
//    }
//

    private UsersService usersService;
    private RolService rolService;
    private UtilizatoriRepository utilizatoriRepository;
    private RoluriUtilizatori roluriUtilizatori;
    private CartiService cartiService;

    private RolRepository rolRepository;
    private Utilizatori utilizatori;
    @Autowired
    private UserSession userSession;
    private RoluriUtilizatoriRepository roluriUtilizatoriRepository;
    private RezervareCarteService rezervareCarteService;
    private ImprumuturiService imprumuturiService;

    //    @Autowired
//    RoluriUtilizatori roluriUtilizatori;
    @Autowired
    public UtilizatorController(UsersService usersService, RoluriUtilizatoriRepository roluriUtilizatoriRepository, RolRepository rolRepository,
                                RolService rolService,
                                RezervareCarteService rezervareCarteService,
                                ImprumuturiService imprumuturiService,
                                UserSession userSession) {

        this.usersService = usersService;
        this.roluriUtilizatoriRepository=roluriUtilizatoriRepository;
        this.rolRepository = rolRepository;
        this.rolService=rolService;
        this.rezervareCarteService=rezervareCarteService;
        this.imprumuturiService=imprumuturiService;
        this.userSession=userSession;
    }

//    public UtilizatorController( {
//
//    }


    @GetMapping("/creare_cont")
    public String returnCreareCont() {
        return "creare_cont";
    }
    @GetMapping("/cititor_pagina_principala.html")
    public String returnCititorPaginaPrincipala() {
        return "cititor_pagina_principala";
    }
//    @GetMapping("/cititor_evenimente.html")
//    public String returnCittitorEvenimente() {
//        return "cititor_evenimente";
//    }
//    @GetMapping("/cititor_catalog.html")
//    public String returnCititorCatalog(Model model) {
//
//        //String titluCarte = cartiService.obtineTitluCarte();
//
//        // adaugă titlul în model pentru a fi accesibil în Thymeleaf
//       // model.addAttribute("titluCarte", titluCarte);
//        List<Carti> carti = cartiService.getAllBooks();
//        model.addAttribute("carti", carti);
//        return "cititor_catalog";
//    }
//    @GetMapping("/cititor_rezervari_sali.html")
//    public String returnCititorRezervariSali() {
//        return "cititor_rezervari_sali";
//    }
    @GetMapping("/cititor_mesaje.html")
    public String returnCititorMesaje() {
        return "cititor_mesaje";
    }
    @GetMapping("/carte.html")
    public String returnCarte() {
        return "carte";
    }
    @GetMapping("/cititor_carte.html")
    public String returnCititorCarte() {
        return "cititor_carte";
    }

//    @GetMapping("/cititor_setari_profil")
//    public String returnCisddtitorCarte(Model model) {
//        Integer idUtilizatorLogat = userSession.getUserId();
//        System.out.println("utii: " + idUtilizatorLogat);
//        Utilizatori utilizatori = usersService.getUserById(idUtilizatorLogat);
//        System.out.println("utii"+utilizatori);
//
//
//        // Presupunând că ai o clasă UserSession care ține minte utilizatorul logat
////        Utilizatori utilizator = utilizatoriRepository.findById(idUtilizatorLogat.longValue()).orElse(null); // Asigură-te că metoda findById acceptă un Long și că tipul ID-ului în UserSession este compatibil
////
////        Utilizatori utilizatori=usersService.getStudentById(idUtilizatorLogat);
//
//        model.addAttribute("utilizator", utilizatori);
//        return "cititor_setari_profil";}
//    @GetMapping("/cititor_setari_profil/{id}")
//    public String returnCititorSetariProfil(@PathVariable("id") Integer id, Model model) {
//        Integer idUtilizatorLogat = userSession.getUserId();
//        System.out.println("utii");
//        System.out.println(idUtilizatorLogat);
//        //        Utilizatori utilizator = utilizatoriRepository.findById(idUtilizatorLogat.longValue()).orElse(null); // Asigură-te că metoda findById acceptă un Long și că tipul ID-ului în UserSession este compatibil
//
//        Utilizatori utilizatori=usersService.getStudentById(id);
//       model.addAttribute("utilizatori",utilizatori);
//        return "cititor_setari_profil";
//    }
//    @GetMapping("/cititor_rezervari_imprumuturi.html")
//    public String returnCititorImprumuturiRez() {
//        return "cititor_rezervari_imprumuturi";
//    }


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
                utilizatori.getCNP(),utilizatori.getTelefon(),utilizatori.getEmail(),utilizatori.getStrada(),utilizatori.getOras()
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
//    @PostMapping("/login")
//    public String login(@ModelAttribute Utilizatori utilizatori,Model model) {
//        System.out.println("login request: "+utilizatori);
//        Utilizatori authenticated= usersService.authenticate(utilizatori.getEmail(),
//                utilizatori.getParola());
//        if(authenticated!=null){
//            model.addAttribute("userLogin",authenticated.getEmail());
////            return "cititor_pagina_principala";
//            return "admin_pagina_principala";
//
//            // return "admin_cititori";
//        }else {
//
//            return "error_page";
//        }
//
//    }
@PostMapping("/login")
public String login(@ModelAttribute Utilizatori utilizatori, Model model) {
    System.out.println("login request: " + utilizatori);
    Utilizatori authenticated = usersService.authenticate(utilizatori.getEmail(), utilizatori.getParola());

    if (authenticated != null) {
        System.out.println("UserId during login: " + authenticated.getId());
        userSession.setUserId(authenticated.getId());

        Set<Rol> roles = usersService.getRolesForUser(authenticated.getId());

        if (roles.stream().anyMatch(rol -> "admin".equals(rol.getNumeRol()))) {
            model.addAttribute("userLogin", authenticated.getEmail());
            return "admin_pagina_principala";
        } else if (roles.stream().anyMatch(rol -> "user".equals(rol.getNumeRol()))) {
            model.addAttribute("userLogin", authenticated.getEmail());
            return "cititor_pagina_principala";
        }
        else if (roles.stream().anyMatch(rol -> "bibliotecar".equals(rol.getNumeRol()))) {
            model.addAttribute("userLogin", authenticated.getEmail());
            return "redirect:/bibliotecar_pagina_principala";

//            return "bibliotecar_pagina_principala";
        }else {
            // Handle other roles or no roles
            return "error_page";
        }
    } else {
        return "error_page";
    }
}

    @GetMapping("/admin_adauga_cititor")
    public String returnAdminAdaugaCititor(Model model) {
        model.addAttribute("user", new Utilizatori());
        return "admin_adauga_cititor"; // Returnați pagina cu formularul de adăugare a utilizatorului
    }





//    @GetMapping("/admin_cititori")
//    public String afiseazaUtilizatori(Model model) {
//        List<Utilizatori> utilizatori = usersService.getAllUsers();
//        model.addAttribute("utilizatori", utilizatori);
//        return "admin_cititori";
//    }
//    @GetMapping("admin_cititori/")
//    public String read(@RequestParam("id") int id, Model model)
//    {
//        return "admin_cititori";
//    }

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
            model.addAttribute("currentRole", ""); // or handle the case when no roles are present
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
//      Utilizatori utilizator = usersService.getUtilizatorById(Math.toIntExact(id));
//        Optional<Utilizatori> utilizator = utilizatoriRepository.findById(id);
        System.out.println("aici");
//        model.addAttribute("utilizator", utilizator);
        return "admin_editeaza_cititor";
    }
//    @GetMapping("/makeBibliotecar/{id}")
//    public  String afiseazaRolBibliotecar(@PathParam("id") Long id, Model model){
//
//    return "rol_bibliotecar";
//    }
//@GetMapping("/makeBibliotecar/{id}")
//public String afiseazaRolBibliotecar(@PathVariable("id") Long id, Model model) {
//    Utilizatori updatedUser = usersService.makeBibliotecar(Math.toIntExact(id));
//
//    if (updatedUser != null) {
//        model.addAttribute("utilizator", updatedUser);
//        return "redirect:/rol_bibliotecar";
//    } else {
//        return "error_page";
//    }
//}
//
//


    @PostMapping("/adaugare")
    public String addUser(@ModelAttribute Utilizatori utilizator) {
        Utilizatori newUser = usersService.saveUser(utilizator);
        RoluriUtilizatori userRole = new RoluriUtilizatori();
        userRole.setIdUtilizator(newUser);
//            userRole.setIdRol(1); // Set the user role
        Optional<Rol> optionalRole = rolRepository.findByNumeRol("user");
        Rol userRoleEntity = optionalRole.orElse(null);

        //Rol userRoleEntity = rolRepository.findByName("user"); // Replace with actual repository method
        userRole.setIdRol(userRoleEntity); // Set the user role entity

        // Save the user role to the database
        usersService.saveUserRole(userRole);
        if (newUser != null) {
            return "admin_cititori";
        } else {
            return "error_page";
        }
    }



//    @PostMapping("/atribuire")
//    public String makingBibliotecar(@PathVariable Integer id,@ModelAttribute Utilizatori utilizatori) {
//        usersService.makeUserBibliotecar(id);
//
//        Utilizatori newUser = usersService.saveUser(utilizatori);
//
//        if (newUser != null) {
//            return "admin_cititori";
//        } else {
//            return "error_page";
//        }
//    }

    @GetMapping("/admin_cititori")
    public String afiseazaUtilizatori(Model model) {
        System.out.println("Se acceseaza /admin_cititori"); // Mesaj de debug

        List<Utilizatori> utilizatori = usersService.getAllUsers();
        model.addAttribute("utilizatori", utilizatori);
        return "admin_cititori";
    }

    //    @PostMapping("/edit/{id}")
//    @PostMapping("/edit/{id}")
    @PostMapping("/admin_cititori/{id}")
    public String actualizeazaUtilizator(@PathVariable Integer id,
                                         @ModelAttribute("utilizatori") Utilizatori utilizator,
                                         @RequestParam(name = "role", required = false) List<Integer> selectedRoles,
                                         Model model) {

        Utilizatori existingUtilizator = usersService.getStudentById(id);

        if (existingUtilizator != null) {
            existingUtilizator.setNume(utilizator.getNume());
            existingUtilizator.setPrenume(utilizator.getPrenume());
            existingUtilizator.setCNP(utilizator.getCNP());
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

            // Clear existing roles
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


    @GetMapping("/cititor_rezervari_imprumuturi.html")
    public String afiseazaRezervarileSiImprumuturileUtilizatorului(Model model) {
        Integer idUtilizatorLogat = userSession.getUserId(); // Obține ID-ul utilizatorului logat
        System.out.println("ID-ul utilizatorului logat: " + idUtilizatorLogat);

        // Inițializează listele ca liste goale în cazul în care serviciul returnează null
        List<Imprumuturi> imprumuturiList = imprumuturiService.getImprumuturiByUserId(idUtilizatorLogat);
        List<RezervariCarti> rezervariList = rezervareCarteService.getRezervariByUserId(idUtilizatorLogat);
        model.addAttribute("imprumuturi", imprumuturiList);
        model.addAttribute("rezervari", rezervariList);

        return "cititor_rezervari_imprumuturi"; // Numele paginii tale Thymeleaf
    }









//    @RequestMapping(value="admin_cititori/{id}", method = RequestMethod.GET)
//    public String findById(@PathVariable Long id) {
//        System.out.println("ID"+id);
//        return "ID found"+id;
//    }

    //@GetMapping("admin_cititori")
//public String afisAdminCititori(){
//        return "admin_cititori";
//}

//    @GetMapping("admin_cititori/edit/{id}")
//    public String read(@PathVariable("id") Integer id, Model model)
//    {
//
//        Utilizatori utilizatori = usersService.getStudentById(id);
//        model.addAttribute("utilizatori", utilizatori);
//        return "admin_editeaza_cititor";
//    }
    @GetMapping ("/admin_cititori/makeBibliotecar/{id}")
    public String makeBibliotecar(@PathVariable("id") Integer id, Model model) {
        Utilizatori utilizatori = usersService.getStudentById(id);
        model.addAttribute("utilizatori", utilizatori);
        //usersService.makeUserBibliotecar(id);

        return "rol_bibliotecar";
    }
    @GetMapping("/rol_bibliotecar")
    public String afiseazaPaginaRolBibliotecar(Model model) {
        // Logică pentru afișarea paginii rol_bibliotecar
        return "rol_bibliotecar";
    }


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
