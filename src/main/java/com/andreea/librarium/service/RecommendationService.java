package com.andreea.librarium.service;

import com.andreea.librarium.model.Carti;
import com.andreea.librarium.repositories.CartiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class RecommendationService {
    @Autowired
    private CartiRepository cartiRepository;



    public Set<String> getUserBookCategories(List<Carti> userBooks) {
        return userBooks.stream()
                .map(Carti::getCategorie)
                .collect(Collectors.toSet());
    }

    public List<Carti> getRecommendedBooks(Set<String> categorie, List<Carti> userBooks) {
        Set<Integer> userBookIds = userBooks.stream()
                .map(Carti::getId)
                .collect(Collectors.toSet());

        return cartiRepository.findByCategorieIn(categorie).stream()
                .filter(book -> !userBookIds.contains(book.getId()))
                .collect(Collectors.toList());
    }
}
