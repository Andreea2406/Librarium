package com.andreea.librarium.service;

import com.andreea.librarium.model.Rol;
import com.andreea.librarium.model.RoluriUtilizatori;
import com.andreea.librarium.model.Utilizatori;
import com.andreea.librarium.repositories.RoluriUtilizatoriRepository;
import com.andreea.librarium.repositories.UtilizatoriRepository;
import org.springframework.stereotype.Service;

@Service
public class UsersService {



    private UtilizatoriRepository utilizatoriRepository;
    private RoluriUtilizatoriRepository roluriUtilizatoriRepository;
    public UsersService(UtilizatoriRepository utilizatoriRepository, RoluriUtilizatoriRepository roluriUtilizatoriRepository) {
        this.utilizatoriRepository = utilizatoriRepository;
        this.roluriUtilizatoriRepository=roluriUtilizatoriRepository;
    }
    public void saveUserRole(RoluriUtilizatori roluriUtilizatori) {
        roluriUtilizatoriRepository.save(roluriUtilizatori);
    }

    public Utilizatori registrationUser( String nume, String prenume, Integer varsta, String telefon, String email, String strada, String oras, String codPostal, String judet, String apartament, String numar, String scara, String ocupatie, String parola){
//        if (email == null || parola == null) {
        if (email == null || parola == null || nume == null || prenume == null || varsta == null ||
                telefon == null || strada == null || oras == null || codPostal == null || judet == null
                || apartament == null || numar == null || scara == null ) {


            return null;
        } else {
            if(utilizatoriRepository.findByEmail(email).isPresent()){
                System.out.println("Duplicate login");
                return null;
            }
        Utilizatori utilizatori=new Utilizatori();
        utilizatori.setNume(nume);utilizatori.setPrenume(prenume);
        utilizatori.setVarsta(varsta); utilizatori.setTelefon(telefon);
            utilizatori.setEmail(email);utilizatori.setStrada(strada);
            utilizatori.setOras(oras);utilizatori.setCodPostal(codPostal); utilizatori.setJudet(judet);utilizatori.setApartament(apartament);

        utilizatori.setNumar(numar); utilizatori.setScara(scara);utilizatori.setOcupatie(ocupatie);utilizatori.setParola(parola);

           return utilizatoriRepository.save(utilizatori);


        }
    }
    public Utilizatori authenticate(String email, String parola){
        return utilizatoriRepository.findByEmailAndParola(email,parola).orElse(null);
    }
}
