package com.andreea.librarium.service;
import java.util.*;
import java.util.stream.Collectors;

import com.andreea.librarium.model.*;
import com.andreea.librarium.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UsersService {
    @Autowired
    private final UtilizatoriRepository utilizatoriRepository;
    private RoluriUtilizatoriRepository roluriUtilizatoriRepository;
    private RolRepository rolRepository;
    private ImprumuturiRepository imprumuturiRepository;
    private CartiFavoriteRepository cartiFavoriteRepository;

    @Autowired
    public UsersService(UtilizatoriRepository utilizatoriRepository,RolRepository rolRepository,
                        RoluriUtilizatoriRepository roluriUtilizatoriRepository,
                        ImprumuturiRepository imprumuturiRepository, CartiFavoriteRepository cartiFavoriteRepository
                        ) {
        this.utilizatoriRepository = utilizatoriRepository;
        this.rolRepository = rolRepository;
        this.roluriUtilizatoriRepository = roluriUtilizatoriRepository;
        this.imprumuturiRepository=imprumuturiRepository;
        this.cartiFavoriteRepository=cartiFavoriteRepository;


    }

    public List<Carti> getUserInteractedBooks(Integer userId) {
        List<Imprumuturi> borrowedBooks = imprumuturiRepository.findByIdUtilizator_Id(userId);
        List<CartiFavorite> favoriteBooks = cartiFavoriteRepository.findByUtilizatorId(userId);


        Set<Carti> userBooks = new HashSet<>();
                userBooks.addAll(borrowedBooks.stream().map(Imprumuturi::getIdCarte).collect(Collectors.toList()));
        userBooks.addAll(favoriteBooks.stream().map(CartiFavorite::getCarte).collect(Collectors.toList()));
        return new ArrayList<>(userBooks);
    }

    public Utilizatori saveUser(Utilizatori utilizatori) {
        return utilizatoriRepository.save(utilizatori);
    }
    public void saveUserRole(RoluriUtilizatori roluriUtilizatori) {
        roluriUtilizatoriRepository.save(roluriUtilizatori);
    }
    public List<Utilizatori> getAllUsers() {
        return utilizatoriRepository.findAll();
    }
    public Utilizatori getStudentById(Integer id){

        Utilizatori optionalUtilizatori = utilizatoriRepository.findById(id);

        return utilizatoriRepository.findById(id);
    }
    public Utilizatori getUserById(Integer id) {
        return utilizatoriRepository.findById(id);
    }

    public RoluriUtilizatori getRolByIDUSER(Integer id){
        RoluriUtilizatori optionalRol=roluriUtilizatoriRepository.findByIdUtilizator(id);
        return roluriUtilizatoriRepository.findByIdUtilizator(id);
    }
    public Utilizatori updateUtilizator(Utilizatori utilizatori){

        return utilizatoriRepository.save(utilizatori);
    }
    @Transactional

    public Utilizatori deleteUserById(Integer id) {
        // Fetch the entity by ID
        Utilizatori utilizatori = utilizatoriRepository.findById(id);

        if (utilizatori != null) {
            imprumuturiRepository.deleteByIdUtilizator(utilizatori);
            cartiFavoriteRepository.deleteByUtilizatorId(id);
            utilizatoriRepository.delete(utilizatori);

            return utilizatori;
        } else {
            return null;
        }
    }
    public List<Utilizatori> searchUsersByName(String nume) {
        return utilizatoriRepository.findByNumeContainingIgnoreCase(nume);
    }
    public Set<Rol> getRolesForUser(Integer userId) {
        Optional<Utilizatori> optionalUtilizatori = Optional.ofNullable(utilizatoriRepository.findById(userId));

        if (optionalUtilizatori.isPresent()) {
            Utilizatori utilizatori = optionalUtilizatori.get();
            return utilizatori.getRole();
        } else {
            return Collections.emptySet();
        }
    }


    public List<Rol> obtineRoluri(){
        return  rolRepository.findAll();
    }

    public Utilizatori registrationUser(String nume, String prenume, String varsta, String telefon, String email,
                                        String strada, String oras, String codPostal, String judet, String apartament,
                                        String numar, String scara, String ocupatie, String parola) {
        if (email == null || parola == null || nume == null || prenume == null || varsta == null ||
                telefon == null || strada == null || oras == null || codPostal == null || judet == null
                || apartament == null || numar == null || scara == null) {
            return null;
        } else {
            if (utilizatoriRepository.findByEmail(email).isPresent()) {
                return null;
            }
            Utilizatori utilizatori = new Utilizatori();
            utilizatori.setNume(nume);
            utilizatori.setPrenume(prenume);
            utilizatori.setVarsta(varsta);
            utilizatori.setTelefon(telefon);
            utilizatori.setEmail(email);
            utilizatori.setStrada(strada);
            utilizatori.setOras(oras);
            utilizatori.setCodPostal(codPostal);
            utilizatori.setJudet(judet);
            utilizatori.setApartament(apartament);
            utilizatori.setNumar(numar);
            utilizatori.setScara(scara);
            utilizatori.setOcupatie(ocupatie);
            utilizatori.setParola(parola);
            return utilizatoriRepository.save(utilizatori);
        }
    }

    public Utilizatori authenticate(String email, String parola) {
        return utilizatoriRepository.findByEmailAndParola(email, parola).orElse(null);
    }
    public List<Utilizatori> findAllReaders() {
        return utilizatoriRepository.findAllByRol(2L);}


}
