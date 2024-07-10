package com.andreea.librarium.controller;

import com.andreea.librarium.config.UserSession;
import com.andreea.librarium.model.CartiFavorite;
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

import java.util.List;

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
    private CartiFavoriteService cartiFavoriteService;
    @Autowired
    public CititorEditareProfil(UsersService usersService, RoluriUtilizatoriRepository roluriUtilizatoriRepository, RolRepository rolRepository,
                                RolService rolService,
                                RezervareCarteService rezervareCarteService,
                                ImprumuturiService imprumuturiService,
                                UserSession userSession,CartiFavoriteService cartiFavoriteService, CartiService cartiService){



    this.usersService = usersService;
    this.roluriUtilizatoriRepository=roluriUtilizatoriRepository;
    this.rolRepository = rolRepository;
    this.rolService=rolService;
    this.rezervareCarteService=rezervareCarteService;
    this.imprumuturiService=imprumuturiService;
    this.userSession=userSession;
    this.cartiFavoriteService=cartiFavoriteService;
    this.cartiService=cartiService;

}
    @GetMapping("/cititor_setari_profil")
    public String vizualizeazaSetariProfil(Model model) {
        Integer idUtilizatorLogat = userSession.getUserId();
        if (idUtilizatorLogat != null) {
            Utilizatori utilizator = usersService.getStudentById(idUtilizatorLogat);
            if (utilizator != null) {
                model.addAttribute("utilizator", utilizator);

                List<CartiFavorite> cartiFavorite = cartiFavoriteService.findFavoriteCartiIdsByUserId(idUtilizatorLogat);
                model.addAttribute("cartiFavorite", cartiFavorite);

                return "cititor_setari_profil";
            } else {
                return "pagina_eroare";
            }
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/cititor_editare_profil")
    public String afiseazaFormularEditareProfil(Model model) {
        Integer idUtilizatorLogat = userSession.getUserId();
        if (idUtilizatorLogat == null) {
            return "redirect:/login";
        }

        Utilizatori utilizator = usersService.getStudentById(idUtilizatorLogat);
        if (utilizator == null) {
            return "pagina_eroare";
        }

        model.addAttribute("utilizatorForm", utilizator);
        return "cititor_editare_profil";
    }
    @PostMapping("/cititor_editare_profil")
    public String proceseazaEditareProfil(@ModelAttribute("utilizatorForm") Utilizatori utilizatorForm,
                                         Model model) {
        Integer idUtilizatorLogat = userSession.getUserId();
        if (idUtilizatorLogat == null) {
            return "redirect:/login";
        }

        Utilizatori existingUtilizator = usersService.getStudentById(idUtilizatorLogat);
        if (existingUtilizator == null) {
            return "pagina_eroare";
        }
        if (existingUtilizator != null) {
            existingUtilizator.setNume(utilizatorForm.getNume());
            existingUtilizator.setPrenume(utilizatorForm.getPrenume());
            existingUtilizator.setVarsta(utilizatorForm.getVarsta());
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
        return "redirect:/cititor_setari_profil";
    }


}
