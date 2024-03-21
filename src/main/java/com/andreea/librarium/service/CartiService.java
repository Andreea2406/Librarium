package com.andreea.librarium.service;

import com.andreea.librarium.model.Carti;
import com.andreea.librarium.model.CartiFavorite;
import com.andreea.librarium.model.Utilizatori;
import com.andreea.librarium.repositories.CartiFavoriteRepository;
import com.andreea.librarium.repositories.CartiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class CartiService {


    private final CartiRepository cartiRepository;
//    @Autowired
//    private  final CartiFavorite cartiFavorite;
    @Autowired
    private  final CartiFavoriteRepository cartiFavoriteRepository;

    @Autowired
    public CartiService(CartiRepository cartiRepository,
                        CartiFavoriteRepository cartiFavoriteRepository) {
        this.cartiRepository = cartiRepository;
//        this.cartiFavorite=cartiFavorite;
        this.cartiFavoriteRepository=cartiFavoriteRepository;
    }

    public Carti saveCarte(Carti carte) {
        // Aici puteți adăuga logica pentru validare, prelucrare etc., apoi salvați în repository
        return cartiRepository.save(carte);
    }
    public List<Carti> getAllBooks() {
        return cartiRepository.findAll();
    }

    public Carti getBookById(Integer id){
//        Carti carti=cartiRepository.findById(id);
        return cartiRepository.findById(id).orElse(null);

    }

//    public Carti obtineTitluCarte(Integer carteId) {
//        Carti carti = cartiRepository.findById(carteId);
//
//        // Verificăm dacă cartea există și returnăm titlul sau un mesaj corespunzător
//        return carteOptional.map(Carti::getTitlu).orElse("Cartea nu a fost găsită pentru ID-ul specificat");
//    }
    public Carti updateCarte(Carti carti){
        return cartiRepository.save(carti);
    }

    public Carti deleteCarteById(Integer id) {
        // Fetch the entity by ID
        Carti carti =cartiRepository.findById(id).orElse(null);

        // Check if the entity exists
        if (carti != null) {
            // Delete the entity
            cartiRepository.delete(carti);

            // Return the deleted entity or relevant information
            return carti;
        } else {
            // If the entity does not exist, you might handle it accordingly
            return null;
        }
    }
    // În CartiService sau un serviciu nou dedicat gestionării favoritelor

    public boolean esteCarteFavorita(Integer utilizatorId, Integer carteId) {
        return cartiFavoriteRepository.existsByUtilizatorIdAndCarteId(utilizatorId, carteId);
    }

//    public void adaugaLaFavorite(Integer utilizatorId, Integer carteId) {
//        // Aici vei avea logica pentru a adăuga cartea la lista de favorite
////         Poti folosi JPA sau JDBC pentru a adăuga înregistrarea în baza de date
//        CartiFavorite cartiFavorite = new CartiFavorite(utilizatorId, carteId);
//        cartiFavoriteRepository.save(cartiFavorite);
//    }
//    // În clasa de serviciu pentru gestionarea favoritelor
//    public boolean esteCarteFavorita(Integer utilizatorId, Integer carteId) {
//        return cartiFavoriteRepository.existsByUtilizatorIdAndCarteId(utilizatorId, carteId);
//    }
//    public Map<Integer, Boolean> getCartiFavoriteMap(Integer userId) {
//        Map<Integer, Boolean> cartiFavoriteMap = new HashMap<>();
//        // Logică pentru a popula cartiFavoriteMap
//        // De exemplu, interogarea bazei de date pentru a găsi toate cărțile favorite ale utilizatorului
//        List<Integer> favoriteCartiIds = cartiFavoriteRepository.findFavoriteCartiIdsByUserId(userId);
//        for (Integer carteId : favoriteCartiIds) {
//            cartiFavoriteMap.put(carteId, Boolean.TRUE);
//        }
//        return cartiFavoriteMap;
//    }

//    public Map<Integer, Boolean> getCartiFavoriteMap(Integer userId) {
//        List<Integer> favoriteCartiIds = cartiFavoriteRepository.findFavoriteCartiIdsByUserId(userId);
//        Map<Integer, Boolean> cartiFavoriteMap = new HashMap<>();
//        for (Integer carteId : favoriteCartiIds) {
//            cartiFavoriteMap.put(carteId, true);
//        }
//        return cartiFavoriteMap;
//    }
//public Map<Integer, Boolean> getCartiFavoriteMap(Integer utilizatorId) {
//    List<Integer> favoriteCartiIds = cartiFavoriteRepository.findFavoriteCartiIdsByUserId(utilizatorId);
//    Map<Integer, Boolean> cartiFavoriteMap = new HashMap<>();
//    for (Integer carteId : favoriteCartiIds) {
//        cartiFavoriteMap.put(carteId, Boolean.TRUE);
//    }
//    return cartiFavoriteMap;
//}



}
