package com.andreea.librarium.controller;
import com.andreea.librarium.model.Utilizatori;


import com.andreea.librarium.config.UserSession;
import com.andreea.librarium.model.*;
import com.andreea.librarium.repositories.*;
import com.andreea.librarium.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
public class BibliotecariController {
    @Autowired
    private BibliotecariService bibliotecariService;
    @Autowired
    private BibliotecariRepository bibliotecariRepository;
    @Autowired
    private CartiService cartiService;
    @Autowired
    private CartiRepository cartiRepository;
    @Autowired
    private EvenimenteService evenimenteService;
    @Autowired
    private EvenimenteRepository evenimenteRepository;
    @Autowired
    private ImprumuturiService imprumuturiService;
    @Autowired
    private ImprumuturiRepository imprumuturiRepository;
    @Autowired
    private SaliService saliService;
    @Autowired
    private SaliRepository saliRepository;
    @Autowired
    private UserSession userSession;
    @Autowired
    private UsersService usersService;
    private Utilizatori utilizatori;
    @Autowired
    public  BibliotecariController(BibliotecariService bibliotecariService,BibliotecariRepository bibliotecariRepository,
                                   CartiService cartiService, CartiRepository cartiRepository,
                                   EvenimenteRepository evenimenteRepository, EvenimenteService evenimenteService,
                                   ImprumuturiRepository imprumuturiRepository, ImprumuturiService imprumuturiService,
                                   SaliRepository saliRepository,SaliService saliService,
                                   UsersService usersService,
                                   UserSession userSession){
        this.bibliotecariService=bibliotecariService;
        this.bibliotecariRepository=bibliotecariRepository;
        this.cartiRepository=cartiRepository;
        this.cartiService=cartiService;
        this.evenimenteRepository=evenimenteRepository;
        this.evenimenteService=evenimenteService;
        this.imprumuturiRepository=imprumuturiRepository;
        this.imprumuturiService=imprumuturiService;
        this.saliRepository=saliRepository;
        this.saliService=saliService;
        this.userSession=userSession;
        this.usersService=usersService;
    }

    @GetMapping("/bibliotecar_mesaje.html")
    public String returnBibliotecarMesaje(Model model) {
        Integer idUtilizatorLogat = userSession.getUserId();

        model.addAttribute("userId",idUtilizatorLogat);

        return "bibliotecar_mesaje";
    }

    @GetMapping("/bibliotecar_pagina_principala")
    public String afiseazaCarti(Model model,@RequestParam(value = "searchTerm", required = false) String searchTerm){
        List<Carti> carti = cartiService.getAllBooks();
        Integer idUtilizatorLogat = userSession.getUserId();
        if (searchTerm != null && !searchTerm.isEmpty()) {
            carti = cartiService.searchBooksByTerm(searchTerm);
            if (carti.isEmpty()) {
                model.addAttribute("errorMessage", "Nu există cărți care să corespundă interogării tale.");
            }
        } else {
            carti = cartiService.getAllBooks();
        }
        model.addAttribute("userId",idUtilizatorLogat);
        model.addAttribute("carti", carti);
        model.addAttribute("searchTerm", searchTerm);

        return "bibliotecar_pagina_principala";

    }
    @GetMapping("/bibliotecar_pagina_principala/search")
    public String adminSearchBooks(@RequestParam("searchTerm") String searchTerm, Model model) {
        List<Carti> carti = cartiService.searchBooksByTerm(searchTerm);
        if (carti.isEmpty()) {
            model.addAttribute("errorMessage", "Nu există cărți care să corespundă interogării tale.");
        }
        model.addAttribute("carti", carti);
        model.addAttribute("searchTerm", searchTerm);
        return "bibliotecar_pagina_principala";
    }
    @GetMapping("/bibliotecar_adauga_carte")
    public String returnBibliotecarAdaugaCarte(Model model) {
        model.addAttribute("carte", new Carti());
        return "bibliotecar_adauga_carte";
    }
    @PostMapping("/adaugaCarte")
    public String addCarte(@ModelAttribute Carti carte) {
        Carti newCarte = cartiService.saveCarte(carte);

        if (newCarte != null) {
            return "redirect:/bibliotecar_pagina_principala";
        } else {
            return "error_page";
        }
    }
    @GetMapping("bibliotecar_pagina_principala/edit/{id}")
    public String read(@PathVariable("id") Integer id, Model model)
    {
        Carti carti = cartiService.getBookById(id);

        model.addAttribute("carti", carti);
        return "bibliotecar_editeaza_carte";
    }
    @GetMapping("/bibliotecar_editeaza_carte/{id}")
    public String afiseazaFormularEditare(@PathParam("id") Long id, Model model) {
        return "bibliotecar_editeaza_carte";
    }
@PostMapping("/bibliotecar_editeaza_carte/{id}")

    public String actualizareCarte(@PathVariable Integer id, @ModelAttribute("carti") Carti carti, Model model) {

    Carti existingCarte=cartiService.getBookById(id);


        if (existingCarte != null) {
            existingCarte.setTitlu(carti.getTitlu());
            existingCarte.setAutor(carti.getAutor());
            existingCarte.setCategorie(carti.getCategorie());
            existingCarte.setLimba(carti.getLimba());
            existingCarte.setEditura(carti.getEditura());
            existingCarte.setTipCoperta(carti.getTipCoperta());
            existingCarte.setNrPagini(carti.getNrPagini());
            existingCarte.setColectie(carti.getColectie());
            existingCarte.setIsbn(carti.getIsbn());
            existingCarte.setLatime(carti.getLatime());
            existingCarte.setInaltime(carti.getInaltime());
            existingCarte.setDescriere(carti.getDescriere());
            existingCarte.setDataPublicarii(carti.getDataPublicarii());
            existingCarte.setDisponibilitate(carti.getDisponibilitate());

            Carti updatedCarte=cartiService.updateCarte(existingCarte);

            if (updatedCarte != null) {
                return "redirect:/bibliotecar_pagina_principala";


            }
        }

        return "error_page";
    }
    @GetMapping("/bibliotecar_pagina_principala/{id}")
    public String deleteCarte(@PathVariable Integer id){
        cartiService.deleteCarteById(id);
        return "redirect:/bibliotecar_pagina_principala";
    }

    @GetMapping("/bibliotecar_evenimente.html")
    public String afiseazaEvenimente(Model model) {
        List<Evenimente> evenimente=evenimenteService.getAllEvenimente();
        model.addAttribute("evenimente",evenimente);

        return "bibliotecar_evenimente";
    }
    @GetMapping("/bibliotecar_adauga_evenimente")
    public String returnAdaugaEvenimente(Model model) {
        model.addAttribute("eveniment", new Evenimente());
        return "bibliotecar_adauga_evenimente";
    }
    @PostMapping("/adaugaEveniment")
    public String addEveniment(@ModelAttribute Evenimente evenimente){
        Evenimente newEveniment=evenimenteService.saveEveniment(evenimente);
        if (newEveniment != null) {
            return "redirect:/bibliotecar_evenimente.html";
        } else {
            return "error_page";
        }
    }
    @GetMapping("bibliotecar_evenimente/edit/{id}")
    public String reead(@PathVariable("id") Integer id, Model model){
        Evenimente evenimente=evenimenteService.getEvenimentById(id);
        model.addAttribute("evenimente",evenimente);
        return "bibliotecar_editeaza_eveniment";
    }
    @GetMapping("/bibliotecar_editeaza_eveniment/{id}")
    public String afiseazaaFormularEditare(@PathVariable("id") Long id,Model model){
        return "bibliotecar_editeaza_eveniment";
    }
    @PostMapping("/bibliotecar_evenimente/{id}")
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
                return "redirect:/bibliotecar_evenimente.html";

            }
        }
        return "error_page";
    }
    @GetMapping("/bibliotecar_evenimente/{id}")
    public String deleteEveniment(@PathVariable Integer id){
        evenimenteService.deleteEvenimentById(id);
        return "redirect:/bibliotecar_evenimente.html";
    }








    @GetMapping("/bibliotecar_imprumuturi.html")
    public String afiseazaImprumuturi(Model model) {
        List<Imprumuturi> imprumuturi=imprumuturiService.getAllImprumuturi();
        model.addAttribute("imprumuturi",imprumuturi);


        return "bibliotecar_imprumuturi";
    }
    @PostMapping("/finalizeLoan/{id}")
    public String finalizeLoan(@PathVariable Integer id) {
        Imprumuturi imprumut = imprumuturiService.getImprumutById(id);
        if (imprumut != null) {
            imprumut.setIsFinalizat(true);
            imprumuturiService.saveImprumut(imprumut);
        }
        return "redirect:/bibliotecar_imprumuturi.html";
    }

    @PostMapping("/extendLoan/{id}")
    public String extendLoan(@PathVariable Integer id) {
        Imprumuturi imprumut = imprumuturiService.getImprumutById(id);
        if (imprumut != null) {
            Instant newReturnDate = imprumut.getDataOraReturn().plus(14, ChronoUnit.DAYS);
            imprumut.setDataOraReturn(newReturnDate);
            imprumuturiService.saveImprumut(imprumut);
        }
        return "redirect:/bibliotecar_imprumuturi.html";
    }






    @GetMapping("/bibliotecar_sali.html")
    public String afiseaa(Model model){
        List<SaliBiblioteca> saliBiblioteca=saliService.getAll();
        model.addAttribute("sali",saliBiblioteca);
        return "bibliotecar_sali";
    }
    @GetMapping("/bibliotecar_adauga_sala")
    public String returnBibliotecarAdaugaSala(Model model) {
        model.addAttribute("sala", new SaliBiblioteca());
        return "bibliotecar_adauga_sala";
    }
    @PostMapping("/adaugaSala")
    public String addSala(@ModelAttribute SaliBiblioteca saliBiblioteca) {
        SaliBiblioteca newSala = saliService.saveSala(saliBiblioteca);

        if (newSala != null) {
            return "redirect:/bibliotecar_sali.html";

        } else {
            return "error_page";
        }
    }
    @GetMapping("bibliotecar_sali/edit/{id}")
    public String editSali(@PathVariable("id") Integer id, Model model)
    {
        SaliBiblioteca saliBiblioteca=saliService.getSalaById(id);

        model.addAttribute("sali", saliBiblioteca);
        return "bibliotecar_editeaza_sala";
    }
    @GetMapping("/bibliotecar_editeaza_sala/{id}")
    public String afiseazaFormularEditareSala(@PathParam("id") Long id, Model model) {
        return "bibliotecar_editeaza_sala";
    }
    @PostMapping("/bibliotecar_sali/{id}")

    public String actualizeazaSala(@PathVariable Integer id, @ModelAttribute("sali") SaliBiblioteca saliBiblioteca, Model model) {


        SaliBiblioteca existingSala=saliService.getSalaById(id);


        if (existingSala != null) {
            existingSala.setNume(saliBiblioteca.getNume());
            existingSala.setLocuriDisponibile(saliBiblioteca.getLocuriDisponibile());
            SaliBiblioteca updateedSala=saliService.updateSala(existingSala);


            if (updateedSala != null) {
//                return "admin_cititori";
                return "redirect:/bibliotecar_sali.html";

//                return "admin_sali_biblioteca";

            }
        }

        return "error_page";
    }
    @GetMapping("/bibliotecar_sali/{id}")
    public String deleteSala(@PathVariable Integer id){
        saliService.deleteSalaById(id);
        return "redirect:/bibliotecar_sali.html";
    }
    @GetMapping("/bibliotecar_setari_profil")
    public String vizualizareSetariProfil(Model model){
        Integer idUtilizatorLogat = userSession.getUserId();
        Utilizatori utilizator = usersService.getStudentById(idUtilizatorLogat);
        model.addAttribute("utilizator", utilizator);

        return "bibliotecar_setari_profil";
    }

    @GetMapping("/bibliotecar_editare_profil")
    public String afiseazaFormularEditareProfil(Model model) {

        Integer idUtilizatorLogat = userSession.getUserId();
        Utilizatori utilizator = usersService.getStudentById(idUtilizatorLogat);
        model.addAttribute("utilizatorForm", utilizator);
        return "bibliotecar_editare_profil";
    }
    @PostMapping("/bibliotecar_editare_profil")
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

        return "redirect:/bibliotecar_setari_profil";
    }

}
