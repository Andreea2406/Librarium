package com.andreea.librarium.controller;

import com.andreea.librarium.config.UserSession;
import com.andreea.librarium.model.Evenimente;
import com.andreea.librarium.model.InregistrareEveniment;
import com.andreea.librarium.repositories.UtilizatoriRepository;
import com.andreea.librarium.service.EvenimenteService;
import com.andreea.librarium.service.InregistrareEvenimentService;
import com.andreea.librarium.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class InregistrareEvenimenteController {

    @Autowired
private InregistrareEvenimentService inregistrareEvenimentService;
    @Autowired

    private final EvenimenteService evenimenteService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private UserSession userSession;
    private final UtilizatoriRepository utilizatoriRepository;
    @Autowired
    public InregistrareEvenimenteController(EvenimenteService evenimenteService, UserSession userSession, UtilizatoriRepository utilizatoriRepository) {
        this.evenimenteService = evenimenteService;
        this.userSession = userSession;
        this.utilizatoriRepository = utilizatoriRepository;
    }

    @GetMapping("/cititor_evenimente.html")
    public String afiseazaEvenimentele(Model model) {
        Integer idUtilizatorLogat = userSession.getUserId();

        model.addAttribute("userId",idUtilizatorLogat);
        List<Evenimente> evenimente = evenimenteService.getAllEvenimente();
        model.addAttribute("evenimente", evenimente);
        return "cititor_evenimente";
    }
    @GetMapping("/cititor_evenimentele_mele.html")
    public String afiseazaEvenimenteInregistratePentruCititori(Model model) {
        Integer idUtilizatorLogat = userSession.getUserId();

        model.addAttribute("userId",idUtilizatorLogat);
        List<InregistrareEveniment> evenimente = inregistrareEvenimentService.getRegistrationsForCurrentUser(idUtilizatorLogat);
        model.addAttribute("evenimente", evenimente);
        return "cititor_evenimentele_mele";
    }

    @PostMapping("/inscrie-te")
    public String inscrieLaEveniment(@RequestParam("idEveniment") Integer idEveniment, RedirectAttributes redirectAttributes) {
        Integer idUtilizator =userSession.getUserId();

        boolean rezultat = inregistrareEvenimentService.inscrieUtilizatorLaEveniment(idEveniment, idUtilizator);

        if (rezultat) {
            redirectAttributes.addFlashAttribute("mesajSucces", "Te-ai însris cu succes la eveniment!");
        } else {
            redirectAttributes.addFlashAttribute("mesajEroare", "Nu te-ai putut înscrie la eveniment.");
        }

        return "redirect:/cititor_evenimente.html";
    }
    @PostMapping("/anuleaza-inscriere")
    public String anuleazaInscriere(@RequestParam("idInregistrare") Integer idInregistrare, RedirectAttributes redirectAttributes) {
        boolean rezultat = inregistrareEvenimentService.anuleazaInscriere(idInregistrare);

        if (rezultat) {
            redirectAttributes.addFlashAttribute("mesajSucces", "Înscrierea ta a fost anulată cu succes!");
        } else {
            redirectAttributes.addFlashAttribute("mesajEroare", "Nu s-a putut anula înscrierea.");
        }

        return "redirect:/cititor_evenimentele_mele.html";
    }

}
