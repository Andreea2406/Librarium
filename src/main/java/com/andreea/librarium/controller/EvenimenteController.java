package com.andreea.librarium.controller;


import com.andreea.librarium.model.Evenimente;
import com.andreea.librarium.repositories.EvenimenteRepository;
import com.andreea.librarium.service.EvenimenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Service;
import java.beans.PropertyEditorSupport;
import java.time.Instant;
import java.util.List;

@Controller
public class EvenimenteController {

    private EvenimenteRepository evenimenteRepository;
    private EvenimenteService evenimenteService;


    @Autowired
    public EvenimenteController(EvenimenteService evenimenteService,EvenimenteRepository evenimenteRepository){
        this.evenimenteRepository=evenimenteRepository;
        this.evenimenteService=evenimenteService;
    }
    @GetMapping("/admin_evenimente.html")
    public String afiseazaEvenimente(Model model) {
    List<Evenimente> evenimente=evenimenteService.getAllEvenimente();
    model.addAttribute("evenimente",evenimente);
//        List<Carti> carti = cartiService.getAllBooks();
//        model.addAttribute("carti", carti);
        return "admin_evenimente";
    }


    @GetMapping("/admin_adauga_eveniment")
    public String returnAdminAdaugaEvenimente(Model model) {
        model.addAttribute("eveniment", new Evenimente());
        return "admin_adauga_eveniment"; // Returnați pagina cu formularul de adăugare a cărții
    }
    @PostMapping("/adaugareEveniment")
    public String addEveniment(@ModelAttribute Evenimente evenimente){
        Evenimente newEveniment=evenimenteService.saveEveniment(evenimente);
        if (newEveniment != null) {
            return "admin_evenimente";
        } else {
            return "error_page";
        }
    }
    @GetMapping("admin_evenimente/edit/{id}")
    public String read(@PathVariable("id") Integer id, Model model){
        Evenimente evenimente=evenimenteService.getEvenimentById(id);
        model.addAttribute("evenimente",evenimente);
        return "admin_editeaza_eveniment";
    }
    @GetMapping("/admin_editeaza_evenimente/{id}")
    public String afiseazaFormularEditare(@PathVariable("id") Long id,Model model){
        return "admin_editeaza_eveniment";
    }
    @PostMapping("/admin_evenimente/{id}")
    public String actualizeazaEveniment(@PathVariable Integer id,@ModelAttribute("evenimente") Evenimente evenimente, Model model){
        Evenimente existingEveniment=evenimenteService.getEvenimentById(id);
        if(existingEveniment!=null){
            existingEveniment.setNume(evenimente.getNume());
            existingEveniment.setDataInceput(evenimente.getDataInceput());
            existingEveniment.setOraInceput(evenimente.getOraInceput());
            existingEveniment.setDataFinal(evenimente.getDataFinal());
            existingEveniment.setOraFinal(evenimente.getOraFinal());
            existingEveniment.setDescriere(evenimente.getDescriere());

            Evenimente updatedEveniment=evenimenteService.updateEveniment(existingEveniment);
            if(updatedEveniment!=null){
                return "redirect:/admin_evenimente.html";

//                return "admin_evenimente";
            }
        }
        return "error_page";
    }

    @GetMapping("/admin_evenimente/{id}")
    public String deleteEveniment(@PathVariable Integer id){
        evenimenteService.deleteEvenimentById(id);
        return "redirect:/admin_evenimente.html";
    }
}
