package com.andreea.librarium.controller;

import com.andreea.librarium.config.UserSession;
import com.andreea.librarium.model.RoluriUtilizatori;
import com.andreea.librarium.model.Utilizatori;
import com.andreea.librarium.repositories.RolRepository;
import com.andreea.librarium.repositories.RoluriUtilizatoriRepository;
import com.andreea.librarium.repositories.UtilizatoriRepository;
import com.andreea.librarium.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CititorEditareProfil {
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
    @Autowired
    public CititorEditareProfil(UsersService usersService, RoluriUtilizatoriRepository roluriUtilizatoriRepository, RolRepository rolRepository,
                                RolService rolService,
                                RezervareCarteService rezervareCarteService,
                                ImprumuturiService imprumuturiService,
                                UserSession userSession){



    this.usersService = usersService;
    this.roluriUtilizatoriRepository=roluriUtilizatoriRepository;
    this.rolRepository = rolRepository;
    this.rolService=rolService;
    this.rezervareCarteService=rezervareCarteService;
    this.imprumuturiService=imprumuturiService;
    this.userSession=userSession;
}
    @GetMapping("/cititor_setari_profil")
    public String vizualizeazaSetariProfil(Model model) {
        Integer idUtilizatorLogat = userSession.getUserId(); // Obține ID-ul din sesiune
        if (idUtilizatorLogat != null) {
            Utilizatori utilizator = usersService.getStudentById(idUtilizatorLogat);
            if (utilizator != null) {
                model.addAttribute("utilizator", utilizator);
                System.out.println("utii"+utilizator);

                return "cititor_setari_profil";
            } else {
                // Tratează cazul în care utilizatorul nu este găsit
                return "pagina_eroare"; // Sau o altă logică adecvată
            }
        } else {
            // Utilizatorul nu este logat sau sesiunea a expirat
            return "redirect:/login";
        }
    }
    @GetMapping("/cititor_editare_profil")
    public String afiseazaFormularEditareProfil(Model model) {
        Integer idUtilizatorLogat = userSession.getUserId();
        if (idUtilizatorLogat == null) {
            return "redirect:/login"; // Redirecționare la pagina de login dacă utilizatorul nu este logat
        }

        Utilizatori utilizator = usersService.getStudentById(idUtilizatorLogat);
        if (utilizator == null) {
            return "pagina_eroare"; // Pagina de eroare sau mesaj corespunzător dacă utilizatorul nu este găsit
        }

        model.addAttribute("utilizatorForm", utilizator);
        return "cititor_editare_profil"; // Numele paginii de editare
    }
    @PostMapping("/cititor_editare_profil")
    public String proceseazaEditareProfil(@ModelAttribute("utilizatorForm") Utilizatori utilizatorForm,
                                         Model model) {
        Integer idUtilizatorLogat = userSession.getUserId();
        if (idUtilizatorLogat == null) {
            return "redirect:/login";
        }

        // Asigurați-vă că actualizați doar utilizatorul curent logat
        Utilizatori existingUtilizator = usersService.getStudentById(idUtilizatorLogat);
        if (existingUtilizator == null) {
            return "pagina_eroare";
        }
        if (existingUtilizator != null) {
            existingUtilizator.setNume(utilizatorForm.getNume());
            existingUtilizator.setPrenume(utilizatorForm.getPrenume());
            existingUtilizator.setCNP(utilizatorForm.getCNP());
            existingUtilizator.setTelefon(utilizatorForm.getTelefon());
            existingUtilizator.setEmail(utilizatorForm.getEmail());
            existingUtilizator.setStrada(utilizatorForm.getStrada());
            existingUtilizator.setOras(utilizatorForm.getOras());
            existingUtilizator.setCodPostal(utilizatorForm.getCodPostal());
            existingUtilizator.setJudet(utilizatorForm.getJudet());
            existingUtilizator.setApartament(utilizatorForm.getApartament());
            existingUtilizator.setNumar(utilizatorForm.getNumar());
            existingUtilizator.setScara(utilizatorForm.getScara());
            existingUtilizator.setOcupatie(utilizatorForm.getOcupatie());
            existingUtilizator.setParola(utilizatorForm.getParola());
            Utilizatori updatedUtilizator = usersService.updateUtilizator(existingUtilizator);

        }
        // Actualizați detalii utilizator și salvați în baza de date
//        usersService.updateUtilizator(utilizatorForm);
        return "redirect:/cititor_setari_profil"; // Redirecționare la pagina de profil după actualizare
    }


}
