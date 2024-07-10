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

    private ImprumuturiService imprumuturiService;
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
        Integer userId = userSession.getUserId();

        if (userId != null ) {


            Utilizatori utilizatori = usersService.getUserById(userId);

            if (utilizatori != null) {
                Carti carti = cartiService.getBookById(idCarte);

                Imprumuturi imprumut = new Imprumuturi();
                imprumut.setIdCarte(carti);
                imprumut.setIdUtilizator(utilizatori);

                Instant now = Instant.now();
                imprumut.setDataOraImprumut(now);
                imprumut.setDataOraReturn(now.plus(30, ChronoUnit.DAYS));
                imprumut.setIsFinalizat(false);

                imprumuturiService.saveImprumut(imprumut);

                return "redirect:/cititor_rezervari_imprumuturi.html";
            } else {

                return "error_page";
            }
        } else {

            return "error_page";
        }
    }


}
