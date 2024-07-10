package com.andreea.librarium.controller;

import com.andreea.librarium.config.UserSession;
import com.andreea.librarium.model.Carti;
import com.andreea.librarium.model.RezervariSali;
import com.andreea.librarium.model.SaliBiblioteca;
import com.andreea.librarium.repositories.RezervareSaliRepository;
import com.andreea.librarium.repositories.SaliRepository;
import com.andreea.librarium.service.RezervareSaliService;
import com.andreea.librarium.service.SaliService;
import com.andreea.librarium.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.websocket.server.PathParam;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Controller
public class SaliController {
    @Autowired
    private SaliRepository saliRepository;
    @Autowired
    private SaliService saliService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private UserSession userSession;
    @Autowired
    private RezervareSaliService rezervareSaliService;
    @Autowired
    private RezervareSaliRepository rezervareSaliRepository;

    @Autowired
    public SaliController(SaliService saliService,SaliRepository saliRepository, RezervareSaliService rezervareSaliService
    , UserSession userSession
    ){
        this.saliRepository=saliRepository;
        this.saliService=saliService;
        this.rezervareSaliService=rezervareSaliService;
        this.userSession=userSession;
    }


    @GetMapping("/admin_sali_biblioteca.html")
    public String afiseaza(Model model) {

        List<SaliBiblioteca> sali = saliService.getAll();
        model.addAttribute("sali", sali);
        return "admin_sali_biblioteca";
    }
    @GetMapping("/cititor_rezervari_sali.html")
    public String afiseazaSaliPentruCititori(Model model) {
        List<SaliBiblioteca> sali = saliService.getAll();
        model.addAttribute("sali", sali);
        return "cititor_rezervari_sali";
    }
    @GetMapping("/cititor_sali_rezervate.html")
    public String afiseazaSaliRezervatePentruCititori(Model model) {
        Integer idUtilizatorLogat = userSession.getUserId();

        List<RezervariSali> sali = rezervareSaliService.getRezervariByUserId(idUtilizatorLogat);
        model.addAttribute("sali", sali);
        return "cititor_sali_rezervate";
    }


    @PostMapping("/anuleaza-rezervare-sala")
    public String anuleazaRezervareSala(@RequestParam("idRezervareSala") Integer idRezervareSala, RedirectAttributes redirectAttributes) {
        boolean rezultat = rezervareSaliService.anuleazaRezervareSala(idRezervareSala);

        if (rezultat) {
            redirectAttributes.addFlashAttribute("mesajSucces", "Rezervarea sălii a fost anulată cu succes!");
        } else {
            redirectAttributes.addFlashAttribute("mesajEroare", "Nu s-a putut anula rezervarea sălii.");
        }

        return "redirect:/cititor_sali_rezervate.html";
    }
    @GetMapping("/admin_adauga_sala")
    public String returnAdminAdaugaSala(Model model) {
        model.addAttribute("sala", new SaliBiblioteca());
        return "admin_adauga_sala";
    }
    @PostMapping("/adaugareSala")
    public String addCarte(@ModelAttribute SaliBiblioteca saliBiblioteca) {
        SaliBiblioteca newSala = saliService.saveSala(saliBiblioteca);

        if (newSala != null) {
            return "redirect:/admin_sali_biblioteca.html";
        } else {
            return "error_page";
        }
    }
    @GetMapping("admin_sali_biblioteca/edit/{id}")
    public String read(@PathVariable("id") Integer id, Model model)
    {
        SaliBiblioteca saliBiblioteca=saliService.getSalaById(id);

        model.addAttribute("sali", saliBiblioteca);
        return "admin_editeaza_sala";
    }

    @GetMapping("/admin_editeaza_sala/{id}")
    public String afiseazaFormularEditare(@PathParam("id") Long id, Model model) {
        System.out.println("aici");
        return "admin_editeaza_sala";
    }
    @PostMapping("/admin_sali_biblioteca/{id}")

    public String actualizeazaSala(@PathVariable Integer id, @ModelAttribute("sali") SaliBiblioteca saliBiblioteca, Model model) {


        SaliBiblioteca existingSala=saliService.getSalaById(id);


        if (existingSala != null) {
            existingSala.setNume(saliBiblioteca.getNume());
            existingSala.setLocuriDisponibile(saliBiblioteca.getLocuriDisponibile());
            SaliBiblioteca updateedSala=saliService.updateSala(existingSala);


            if (updateedSala != null) {
                return "redirect:/admin_sali_biblioteca.html";

            }
        }

        return "error_page";
    }
    @GetMapping("/admin_sali_biblioteca/{id}")
    public String deleteSala(@PathVariable Integer id){
        saliService.deleteSalaById(id);
        return "redirect:/admin_sali_biblioteca.html";
    }
    @GetMapping("/rezerva_sala/{salaId}")
    public String rezervaSala(@PathVariable Integer salaId) {
        Integer idUtilizator = userSession.getUserId();

        if (idUtilizator == null) {
            return "redirect:/login";
        }


        rezervareSaliService.adaugaRezervare(salaId, idUtilizator, Instant.now(), Instant.now().plus(Duration.ofHours(1)));

        return "redirect:/cititor_rezervari_sali.html";
    }


}
