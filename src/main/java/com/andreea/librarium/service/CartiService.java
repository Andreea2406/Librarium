package com.andreea.librarium.service;

import com.andreea.librarium.model.Carti;
import com.andreea.librarium.model.CartiFavorite;
import com.andreea.librarium.model.Utilizatori;
import com.andreea.librarium.repositories.CartiFavoriteRepository;
import com.andreea.librarium.repositories.CartiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class CartiService {

@Autowired
    private final CartiRepository cartiRepository;

    @Autowired
    private  final CartiFavoriteRepository cartiFavoriteRepository;

    @Autowired
    public CartiService(CartiRepository cartiRepository,
                        CartiFavoriteRepository cartiFavoriteRepository) {
        this.cartiRepository = cartiRepository;
        this.cartiFavoriteRepository=cartiFavoriteRepository;
    }
    public List<Carti> searchBooksByTitle(String title) {
        return cartiRepository.findByTitluContainingIgnoreCase(title);
    }

    public List<Carti> searchBooks(String query) {
        return cartiRepository.findByTitluContainingIgnoreCaseOrAutorContainingIgnoreCase(query, query);
    }
    public List<Carti> searchBooksByTerm(String searchTerm) {
        return cartiRepository.findByTitluContainingIgnoreCaseOrAutorContainingIgnoreCase(searchTerm, searchTerm);
    }
    public List<Carti> filterBooks(String query, String categorie, String autor, String editura, String limba) {

        return cartiRepository.findByCriteria(query, categorie, autor, editura, limba);
    }
    public List<Carti> getRandomBooks(int numberOfBooks) {
        List<Carti> allBooks = cartiRepository.findAll();
        Collections.shuffle(allBooks);
        return allBooks.stream().limit(numberOfBooks).collect(Collectors.toList());
    }


    public Carti saveCarte(Carti carte) {
        return cartiRepository.save(carte);
    }
    public List<Carti> getAllBooks() {
        return cartiRepository.findAll();
    }

    public Carti getBookById(Integer id){
        return cartiRepository.findById(id).orElse(null);

    }

    public List<Carti> findFirst9() {
        return cartiRepository.findTop9ByOrderByIdAsc();
    }

    public Carti updateCarte(Carti carti){
        return cartiRepository.save(carti);
    }

    public Carti deleteCarteById(Integer id) {
        Carti carti =cartiRepository.findById(id).orElse(null);

        if (carti != null) {
            cartiRepository.delete(carti);

            return carti;
        } else {
            return null;
        }
    }

    public boolean esteCarteFavorita(Integer utilizatorId, Integer carteId) {
        return cartiFavoriteRepository.existsByUtilizatorIdAndCarteId(utilizatorId, carteId);
    }


}
