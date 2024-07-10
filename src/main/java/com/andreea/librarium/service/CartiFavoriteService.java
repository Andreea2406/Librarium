package com.andreea.librarium.service;
import com.andreea.librarium.model.CartiFavorite;

import com.andreea.librarium.repositories.CartiFavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CartiFavoriteService {

    private final CartiFavoriteRepository cartiFavoriteRepository;

    @Autowired
    public CartiFavoriteService(CartiFavoriteRepository cartiFavoriteRepository) {
        this.cartiFavoriteRepository = cartiFavoriteRepository;
    }
    public Map<String, Long> countFavoriteBooksByCategory() {
        List<CartiFavorite> favorites = cartiFavoriteRepository.findAll();
        return favorites.stream()
                .collect(Collectors.groupingBy(fav -> fav.getCarte().getCategorie(), Collectors.counting()));
    }

    public boolean esteCarteFavorita(Integer utilizatorId, Integer carteId) {
        return cartiFavoriteRepository.existsByUtilizatorIdAndCarteId(utilizatorId, carteId);
    }

    public List<CartiFavorite> findFavoriteCartiIdsByUserId(Integer utilizatorId) {
        return cartiFavoriteRepository.findWithCartiByUserId(utilizatorId);
    }


    public void adaugaLaFavorite(Integer utilizatorId, Integer carteId) {
        if (!esteCarteFavorita(utilizatorId, carteId)) {
            cartiFavoriteRepository.save(new CartiFavorite(utilizatorId, carteId));
        }
    }

    @Transactional
    public void eliminaDinFavorite(Integer utilizatorId, Integer carteId) {
        cartiFavoriteRepository.deleteByUtilizatorIdAndCarteId(utilizatorId, carteId);
    }
}
