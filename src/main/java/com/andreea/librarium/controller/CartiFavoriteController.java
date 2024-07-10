package com.andreea.librarium.controller;

import com.andreea.librarium.config.UserSession;
import com.andreea.librarium.model.Utilizatori;
import com.andreea.librarium.service.CartiFavoriteService;
import com.andreea.librarium.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class CartiFavoriteController {

    private final CartiFavoriteService cartiFavoriteService;
    @Autowired
    private UserSession userSession;
    @Autowired
    private UsersService usersService;

    @Autowired
    public CartiFavoriteController(CartiFavoriteService cartiFavoriteService,UserSession userSession) {
        this.cartiFavoriteService = cartiFavoriteService;
        this.userSession=userSession;
    }
    @GetMapping("/api/favorite-categories")
    @ResponseBody
    public Map<String, Long> getFavoriteCategories() {
        return cartiFavoriteService.countFavoriteBooksByCategory();
    }

    @PostMapping("/adauga_la_favorite/{carteId}")
    public String adaugaLaFavorite(@PathVariable Integer carteId, Model model) {
        Integer userId = userSession.getUserId();

        if (userId != null) {
            if (!cartiFavoriteService.esteCarteFavorita(userId, carteId)) {
                cartiFavoriteService.adaugaLaFavorite(userId, carteId);
                return "redirect:/cititor_catalog.html";
            } else {
                model.addAttribute("message", "Cartea este deja în lista ta de favorite.");
                return "pagina_informativa";
            }
        } else {
            model.addAttribute("error", "Trebuie să fii logat pentru a adăuga la favorite.");
            return "logare";
        }
    }

    @PostMapping("/elimina_din_favorite/{carteId}")
    public String eliminaDinFavorite(@PathVariable Integer carteId, Model model) {
        Integer userId = userSession.getUserId();

        if (userId != null) {

            cartiFavoriteService.eliminaDinFavorite(userId, carteId);
            return "redirect:/cititor_setari_profil";       } else {
            model.addAttribute("error", "Trebuie să fii logat pentru a elimina din favorite.");
            return "logare";
        }
    }
}
