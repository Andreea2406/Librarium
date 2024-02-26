package com.andreea.librarium.controller;

import com.andreea.librarium.config.UserSession;
import com.andreea.librarium.model.Evenimente;
import com.andreea.librarium.service.EvenimenteService;
import com.andreea.librarium.service.InregistrareEvenimentService;
import com.andreea.librarium.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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
    @Autowired
    public InregistrareEvenimenteController(EvenimenteService evenimenteService) {
        this.evenimenteService = evenimenteService;
    }

    @GetMapping("/cititor_evenimente.html")
    public String afiseazaEvenimentele(Model model) {
        List<Evenimente> evenimente = evenimenteService.getAllEvenimente(); // Presupunem că ai o metodă care returnează toate evenimentele
        model.addAttribute("evenimente", evenimente);
        return "cititor_evenimente"; // Numele fișierului template Thymeleaf (fără extensie .html)
    }
    @PostMapping("/inscrie-te")
    public String inscrieLaEveniment(@RequestParam("idEveniment") Integer idEveniment, RedirectAttributes redirectAttributes) {
        // Aici ar trebui să obții ID-ul utilizatorului curent. Exemplu:
        Integer idUtilizator =userSession.getUserId();  /* Metoda ta de a obține ID-ul utilizatorului curent */;

        // Logica de creare a unei noi înregistrări de eveniment
        boolean rezultat = inregistrareEvenimentService.inscrieUtilizatorLaEveniment(idEveniment, idUtilizator);

        if (rezultat) {
            // Adaugă un mesaj de succes
            redirectAttributes.addFlashAttribute("mesajSucces", "Te-ai însris cu succes la eveniment!");
        } else {
            // Adaugă un mesaj de eroare
            redirectAttributes.addFlashAttribute("mesajEroare", "Nu te-ai putut înscrie la eveniment.");
        }

        return "redirect:/cititor_evenimente.html";
    }

}
