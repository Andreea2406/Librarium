package com.andreea.librarium.service;
import java.util.Optional;
import java.util.Set;
import java.util.Collections;

import com.andreea.librarium.repositories.RolRepository;

import com.andreea.librarium.model.Rol;
import com.andreea.librarium.model.RoluriUtilizatori;
import com.andreea.librarium.model.Utilizatori;
import com.andreea.librarium.repositories.RoluriUtilizatoriRepository;
import com.andreea.librarium.repositories.UtilizatoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    private final UtilizatoriRepository utilizatoriRepository;
    private RoluriUtilizatoriRepository roluriUtilizatoriRepository;
    private RolRepository rolRepository;

    @Autowired
    public UsersService(UtilizatoriRepository utilizatoriRepository,RolRepository rolRepository, RoluriUtilizatoriRepository roluriUtilizatoriRepository) {
        this.utilizatoriRepository = utilizatoriRepository;
        this.rolRepository = rolRepository;
        this.roluriUtilizatoriRepository = roluriUtilizatoriRepository;


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
    public RoluriUtilizatori getRolByIDUSER(Integer id){
        RoluriUtilizatori optionalRol=roluriUtilizatoriRepository.findByIdUtilizator(id);
        return roluriUtilizatoriRepository.findByIdUtilizator(id);
    }
    public Utilizatori updateUtilizator(Utilizatori utilizatori){

        return utilizatoriRepository.save(utilizatori);
    }

    public Utilizatori deleteUserById(Integer id) {
        // Fetch the entity by ID
        Utilizatori utilizatori = utilizatoriRepository.findById(id);

        // Check if the entity exists
        if (utilizatori != null) {
            // Delete the entity
            utilizatoriRepository.delete(utilizatori);

            // Return the deleted entity or relevant information
            return utilizatori;
        } else {
            // If the entity does not exist, you might handle it accordingly
            return null;
        }
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

    public Utilizatori registrationUser(String nume, String prenume, String CNP, String telefon, String email,
                                        String strada, String oras, String codPostal, String judet, String apartament,
                                        String numar, String scara, String ocupatie, String parola) {
        if (email == null || parola == null || nume == null || prenume == null || CNP == null ||
                telefon == null || strada == null || oras == null || codPostal == null || judet == null
                || apartament == null || numar == null || scara == null) {
            return null;
        } else {
            if (utilizatoriRepository.findByEmail(email).isPresent()) {
                System.out.println("Duplicate login");
                return null;
            }
            Utilizatori utilizatori = new Utilizatori();
            utilizatori.setNume(nume);
            utilizatori.setPrenume(prenume);
            utilizatori.setCNP(CNP);
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

//    public Utilizatori  makeUserBibliotecar(Integer userId) {
//        Utilizatori utilizatori = utilizatoriRepository.findById(userId);
//
//        if (utilizatori != null) {
//            // Assuming role with id 3 is bibliotecar role
//            Rol bibliotecarRole = rolRepository.findById(3).orElse(null);
//
//            if (bibliotecarRole != null) {
//                // Find the existing entry in roluri_utilizatori
//                if (roluriUtilizatoriRepository != null) {
//                    RoluriUtilizatori roluriUtilizatori = roluriUtilizatoriRepository.findByIdUtilizator(userId);
//
//                    if (roluriUtilizatori != null) {
//                        // Update the existing entry with the new role
//                        roluriUtilizatori.setIdRol(bibliotecarRole);
//                        roluriUtilizatoriRepository.save(roluriUtilizatori);
//                    } else {
//                        // Afisati un mesaj de eroare sau tratati situatia in functie de caz
//                        System.out.println("RoluriUtilizatori not found for userId: " + userId);
//                    }
//                } else {
//                    // Afisati un mesaj de eroare sau tratati situatia in functie de caz
//                    System.out.println("roluriUtilizatoriRepository is null");
//                }
//            } else {
//                // Afisati un mesaj de eroare sau tratati situatia in functie de caz
//                System.out.println("Bibliotecar Role not found with id: 3");
//            }
//        } else {
//            // Afisati un mesaj de eroare sau tratati situatia in functie de caz
//            System.out.println("Utilizator not found with id: " + userId);
//        }
//        return null;
//    }



//    public Utilizatori makeBibliotecar(Long id) {
//        return makeBibliotecar(Math.toIntExact(id));
//    }

//    public Utilizatori makeBibliotecar(Integer id) {
//        Utilizatori utilizator = utilizatoriRepository.findById(id);
//
//        if (utilizator != null) {
//            Rol bibliotecarRole = rolRepository.findById(3).orElse(null);
//
//            if (bibliotecarRole != null) {
//                RoluriUtilizatori roluriUtilizatori = roluriUtilizatoriRepository.findByIdUtilizator(id);
//
//                if (roluriUtilizatori != null) {
//                    roluriUtilizatori.setIdRol(bibliotecarRole);
//                    roluriUtilizatoriRepository.save(roluriUtilizatori);
//
//                    return utilizator; // Returnăm utilizatorul actualizat
//                } else {
//                    System.out.println("RoluriUtilizatori not found for userId: " + id);
//                }
//            } else {
//                System.out.println("Bibliotecar Role not found with id: 3");
//            }
//        } else {
//            System.out.println("Utilizator not found with id: " + id);
//        }
//
//        return null;
//    }








//    public void makeUserBibliotecar(Integer id) {
//        Utilizatori utilizatori = utilizatoriRepository.findById(id);
//        if (utilizatori != null) {
//            Optional<Rol> bibliotecarRolOptional = rolRepository.findByNumeRol("bibliotecar");
//
//            if (bibliotecarRolOptional.isPresent()) {
//                Rol bibliotecarRol = bibliotecarRolOptional.get();
//                Set<RoluriUtilizatori> roluriUtilizatoriSet = utilizatori.getRoluriUtilizatoris();
//                RoluriUtilizatori roluriUtilizatori = new RoluriUtilizatori();
//                roluriUtilizatori.setIdUtilizator(utilizatori);
//                roluriUtilizatori.setIdRol(bibliotecarRol);
//                roluriUtilizatoriSet.add(roluriUtilizatori);
//
//                utilizatoriRepository.save(utilizatori);
//            }
//        }
//    }




//    public Utilizatori getUtilizatorById(Integer id) {
//        Optional<Utilizatori> utilizatori = utilizatoriRepository.findById(id);
//        return utilizatori.orElse(null);
//    }
}
