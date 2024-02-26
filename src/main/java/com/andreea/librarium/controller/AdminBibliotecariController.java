package com.andreea.librarium.controller;


import com.andreea.librarium.model.Rol;
import com.andreea.librarium.model.RoluriUtilizatori;
import com.andreea.librarium.model.Utilizatori;
import com.andreea.librarium.repositories.BibliotecariRepository;
import com.andreea.librarium.repositories.RolRepository;
import com.andreea.librarium.service.BibliotecariService;
import com.andreea.librarium.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Controller
public class AdminBibliotecariController {
    private BibliotecariService bibliotecariService;
    private BibliotecariRepository bibliotecariRepository;
private UsersService usersService;
private RolRepository rolRepository;
    @Autowired
    public AdminBibliotecariController(BibliotecariService bibliotecariService, UsersService usersService, RolRepository rolRepository) {
        this.bibliotecariService = bibliotecariService;
        this.usersService=usersService;
        this.rolRepository=rolRepository;
    }
    @GetMapping("/admin_bibliotecari.html")
    public String afiseazaBibliotecarii(Model model) {
        List<Utilizatori> bibliotecari = bibliotecariService.getAllBibliotecari();
        model.addAttribute("bibliotecari", bibliotecari);
        return "admin_bibliotecari";
    }
    @GetMapping("/admin_editeaza_bibliotecar/{id}")
    public String afiseazaFormularEditare(@PathParam("id") Long id, Model model) {

        return "admin_editeaza_bibliotecar";
    }
    @GetMapping("admin_bibliotecari/edit/{id}")
    public String read(@PathVariable("id") Integer id, Model model) {
        Utilizatori utilizatori = usersService.getStudentById(id);
        Set<RoluriUtilizatori> roluriUtilizatoris = utilizatori.getRoluriUtilizatoris();

        if (!roluriUtilizatoris.isEmpty()) {
            Iterator<RoluriUtilizatori> iterator = roluriUtilizatoris.iterator();
            RoluriUtilizatori firstRol = iterator.next();
            String currentRole = firstRol.getIdRol().getNumeRol();
            utilizatori.setSelectedRole(currentRole);
            model.addAttribute("currentRole", currentRole);
        } else {
            model.addAttribute("currentRole", ""); // or handle the case when no roles are present
        }

        model.addAttribute("utilizatori", utilizatori);

        String otherRole;
        if ("bibliotecar".equals(model.getAttribute("currentRole"))) {
            otherRole = "user";
        } else {
            otherRole = "bibliotecar";
        }
        model.addAttribute("otherRole", otherRole);

        List<Rol> listaRoluri = usersService.obtineRoluri();
        model.addAttribute("listaRoluri", listaRoluri);

        return "admin_editeaza_bibliotecar";
    }
    @PostMapping("/admin_bibliotecari/{id}")
    public String actualizeazaUtilizator(@PathVariable Integer id,
                                         @ModelAttribute("utilizatori") Utilizatori utilizator,
                                         @RequestParam(name = "role", required = false) List<Integer> selectedRoles,
                                         Model model) {

        Utilizatori existingUtilizator = usersService.getStudentById(id);

        if (existingUtilizator != null) {
            existingUtilizator.setNume(utilizator.getNume());
            existingUtilizator.setPrenume(utilizator.getPrenume());
            existingUtilizator.setCNP(utilizator.getCNP());
            existingUtilizator.setTelefon(utilizator.getTelefon());
            existingUtilizator.setEmail(utilizator.getEmail());
            existingUtilizator.setStrada(utilizator.getStrada());
            existingUtilizator.setOras(utilizator.getOras());
            existingUtilizator.setCodPostal(utilizator.getCodPostal());
            existingUtilizator.setJudet(utilizator.getJudet());
            existingUtilizator.setApartament(utilizator.getApartament());
            existingUtilizator.setNumar(utilizator.getNumar());
            existingUtilizator.setScara(utilizator.getScara());
            existingUtilizator.setOcupatie(utilizator.getOcupatie());
            existingUtilizator.setParola(utilizator.getParola());

            // Clear existing roles
            if (selectedRoles != null) {
                Set<Rol> rolesToAdd = new HashSet<>();

                for (Integer roleId : selectedRoles) {
                    rolRepository.findById(roleId).ifPresent(rolesToAdd::add);
                }

                existingUtilizator.setRole(rolesToAdd);
            }
            Utilizatori updatedUtilizator = usersService.updateUtilizator(existingUtilizator);

            if (updatedUtilizator != null) {
                return "redirect:/admin_bibliotecari";
            }
        }

        return "error_page";
    }
    @GetMapping("/admin_bibliotecari/{id}")
    public String deleteUser(@PathVariable Integer id){
        usersService.deleteUserById(id);
        return "redirect:/admin_bibliotecari";
    }
}
