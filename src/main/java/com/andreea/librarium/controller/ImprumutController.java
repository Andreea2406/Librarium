package com.andreea.librarium.controller;

import com.andreea.librarium.config.UserSession;
import com.andreea.librarium.model.Carti;
import com.andreea.librarium.model.Imprumuturi;
import com.andreea.librarium.model.RezervariCarti;
import com.andreea.librarium.model.Utilizatori;
import com.andreea.librarium.repositories.ImprumuturiRepository;
import com.andreea.librarium.service.CartiService;
import com.andreea.librarium.service.ImprumuturiService;
import com.andreea.librarium.service.RezervareCarteService;
import com.andreea.librarium.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

@Controller

public class ImprumutController {



    @Autowired

    private ImprumuturiService imprumuturiService; // Inject your service
private ImprumuturiRepository imprumuturiRepository;
private Carti carti;
private UsersService usersService;
private CartiService cartiService;
private RezervareCarteService rezervareCarteService;
@Autowired
private UserSession userSession;
    @Autowired
public ImprumutController(ImprumuturiService imprumuturiService, ImprumuturiRepository imprumuturiRepository, UsersService usersService,UserSession userSession
    ,CartiService cartiService,RezervareCarteService rezervareCarteService){

    this.imprumuturiService=imprumuturiService;
    this.imprumuturiRepository=imprumuturiRepository;
    this.usersService=usersService;
    this.userSession=userSession;
    this.cartiService=cartiService;
    this.rezervareCarteService=rezervareCarteService;
}
    @PostMapping("/imprumuta")
    public String imprumutaCarte(@RequestParam("id") Integer idCarte, Model model) {
        // Obține userId din userSession
        Integer userId = userSession.getUserId();
        System.out.println("userId: " + userSession.getUserId());
        System.out.println("idCarte: " + idCarte);
        // Verifică dacă userId este nenul și diferit de zero
        if (userId != null ) {
            System.out.println("hereee");
            // Obține întregul obiect Utilizatori pentru userId
            //            Utilizatori utilizator = usersService.getStudentById(idUtilizator);
            System.out.println("userIdd: " + userId);

            Utilizatori utilizatori = usersService.getUserById(userId); // Replace with your Utilizatori service method

            if (utilizatori != null) {
                Carti carti = cartiService.getBookById(idCarte); // Înlocuiește cu metoda ta de service pentru Carti

                // Creează un obiect Imprumuturi
                Imprumuturi imprumut = new Imprumuturi();
                imprumut.setIdCarte(carti);
                imprumut.setIdUtilizator(utilizatori);

                // Setează alte date precum dataOraImprumut și dataOraReturn
                Instant now = Instant.now();
                imprumut.setDataOraImprumut(now);
                imprumut.setDataOraReturn(now.plus(30, ChronoUnit.DAYS)); // Exemplu: Data de returnare după 30 de zile

                // Salvează înregistrarea Imprumuturi
                imprumuturiService.saveImprumut(imprumut);

                // Redirecționează sau întoarce un răspuns în consecință
                return "redirect:/catalog.html";
            } else {
                // Tratează cazul în care utilizatorul nu a fost găsit
                // Poate ar trebui să redirecționezi către o pagină de eroare sau să întorci alt răspuns
                return "error_page";
            }
        } else {
            // Tratează cazul în care userId nu este setat corect
            // Poate ar trebui să redirecționezi către o pagină de eroare sau să întorci alt răspuns
            return "error_page";
        }
    }


}
