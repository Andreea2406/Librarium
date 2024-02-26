package com.andreea.librarium.controller;


import com.andreea.librarium.config.UserSession;
import com.andreea.librarium.model.Carti;
import com.andreea.librarium.model.RezervariCarti;
import com.andreea.librarium.model.Utilizatori;
import com.andreea.librarium.repositories.RezervareCarteRepository;
import com.andreea.librarium.service.CartiService;
import com.andreea.librarium.service.RezervareCarteService;
import com.andreea.librarium.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Controller
public class RezervareCarteController {

    @Autowired
    private RezervareCarteRepository rezervareCarteRepository;
    private RezervareCarteService rezervareCarteService;
    private Carti carti;
    private UsersService usersService;
    private CartiService cartiService;
    @Autowired
    private UserSession userSession;

    @Autowired
    public RezervareCarteController(RezervareCarteService rezervareCarteService, RezervareCarteRepository rezervareCarteRepository,
                                    UserSession userSession, UsersService usersService, CartiService cartiService) {
        this.rezervareCarteService = rezervareCarteService;
        this.rezervareCarteRepository = rezervareCarteRepository;
        this.userSession = userSession;
        this.usersService = usersService;
        this.cartiService = cartiService;
    }

    @PostMapping("/rezerva")
    public String rezervaCarte(@RequestParam("id") Integer idCarte, Model model, HttpSession session) {
        Integer userId = userSession.getUserId();
        Utilizatori utilizatori = usersService.getUserById(userId);
        if (userId != null && userId != 0) {
            // Verifică disponibilitatea cărții sau alte condiții specifice pentru rezervare
            Carti carti = cartiService.getBookById(idCarte);
            // Creează un obiect Rezervare
            RezervariCarti rezervare = new RezervariCarti();
            rezervare.setIdCarte(carti);
            rezervare.setIdUtilizator(utilizatori);
            Instant now = Instant.now();

            rezervare.setDataRezervare(now);
            rezervare.setDataExpirarii( now.plus(3, ChronoUnit.DAYS)); // Exemplu: Starea de așteptare
            rezervareCarteService.saveRezervareCarte(rezervare);
            // Salvează înregistrarea Rezervari

            // Poți adăuga un mesaj de succes sau alte acțiuni necesare

            // Redirecționează sau întoarce un răspuns în consecință
            return "redirect:/cititor_carte.html";
        } else {
            // Tratează cazul în care userId nu este setat corect
            // Poate ar trebui să redirecționezi către o pagină de eroare sau să întorci alt răspuns
            return "error_page";
        }
    }

}

