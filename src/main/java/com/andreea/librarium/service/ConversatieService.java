package com.andreea.librarium.service;
import com.andreea.librarium.config.UserSession;
import com.andreea.librarium.dto.ConversatieDto;
import com.andreea.librarium.model.ParticipantConversatie;
import com.andreea.librarium.model.Utilizatori;

import com.andreea.librarium.model.Conversatie;
import com.andreea.librarium.repositories.ConversatieRepository;
import com.andreea.librarium.repositories.MesajRepository;
import com.andreea.librarium.repositories.ParticipantConversatieRepository;
import com.andreea.librarium.repositories.UtilizatoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ConversatieService {
    private final ConversatieRepository conversatieRepository;
    private final MesajRepository mesajRepository;
    private final UtilizatoriRepository utilizatoriRepository;
    private  final UserSession userSession;
private ParticipantConversatieRepository participantConversatieRepository;
private ParticipantConversatieService participantConversatieService;
    @Autowired
    public ConversatieService(ConversatieRepository conversatieRepository, MesajRepository mesajRepository, UtilizatoriRepository utilizatoriRepository, UserSession userSession, ParticipantConversatieRepository participantConversatieRepository, ParticipantConversatieService participantConversatieService) {
        this.conversatieRepository = conversatieRepository;
        this.mesajRepository = mesajRepository;
        this.utilizatoriRepository = utilizatoriRepository;
        this.userSession = userSession;
        this.participantConversatieRepository = participantConversatieRepository;
        this.participantConversatieService = participantConversatieService;
    }
    public Conversatie creazaConversatie(List<Utilizatori> participanti, Boolean estePrivat) {
        Conversatie conversatie = new Conversatie();
        for (Utilizatori participant : participanti) {
            conversatie.addUtilizator(participant);
        }
        conversatie.setEstePrivat(estePrivat);
        conversatieRepository.save(conversatie);
        return conversatie;
    }
    public Conversatie creazaConversatieNouaCititori(String titlu) {
        Conversatie conversatie = new Conversatie();
        conversatie.setTitlu(titlu);
        conversatie.setDataCreare(new Date());
        conversatie.setUltimaActualizare(new Date());
        return conversatieRepository.save(conversatie);
    }

    public Conversatie createOrUpdateChatGroupForReaders() {
        List<Utilizatori> cititori = utilizatoriRepository.findAllByRole_Name("user");
        Conversatie conversatieGrup;

        Optional<Conversatie> conversatieExistenta = conversatieRepository.findByTitlu("Grup Cititori");
        if (conversatieExistenta.isPresent()) {
            conversatieGrup = conversatieExistenta.get();
        } else {
            conversatieGrup = new Conversatie();
            conversatieGrup.setTitlu("Grup Cititori");
            conversatieGrup.setDataCreare(new Date());
            conversatieGrup.setUltimaActualizare(new Date());
        }

        for (Utilizatori cititor : cititori) {
            conversatieGrup.addUtilizator(cititor);
        }
        conversatieGrup.setUltimaActualizare(new Date());

        return conversatieRepository.save(conversatieGrup);
    }
    public Conversatie adaugaUtilizatorInConversatie(String emailUtilizator, Long idConversatieLong) {
        int idConversatie;
        if (idConversatieLong != null && idConversatieLong <= Integer.MAX_VALUE && idConversatieLong >= Integer.MIN_VALUE) {
            idConversatie = idConversatieLong.intValue();
        } else {
            throw new IllegalArgumentException("ID-ul conversației este invalid.");
        }

        Utilizatori utilizator = utilizatoriRepository.findByEmail(emailUtilizator)
                .orElseThrow(() -> new IllegalArgumentException("Utilizatorul nu a fost găsit."));
        Conversatie conversatie = conversatieRepository.findById(idConversatie)
                .orElseThrow(() -> new IllegalArgumentException("Conversația nu a fost găsită."));

        conversatie.addUtilizator(utilizator);
        conversatie.setUltimaActualizare(new Date());

        return conversatieRepository.save(conversatie);
    }
    public Conversatie creazaConversatieNouaCititori() {
        Conversatie conversatie = new Conversatie();


        conversatie.setTitlu("Iubitorii de carte");
        conversatie.setDataCreare(new Date());
        conversatie.setUltimaActualizare(new Date());
        conversatie.setEstePrivat(false);
        return conversatieRepository.save(conversatie);
    }


    private Integer getCurrentBibliotecarId() {
        return Math.toIntExact(1L);
    }
    public List<Conversatie> findAll() {
        return conversatieRepository.findAll();
    }
    public List<ConversatieDto> getConversationsForBibliotecar(Long userId) {
        List<Conversatie> conversatii = conversatieRepository.findAllByBibliotecarId(userId);
        return conversatii.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    private ConversatieDto convertToDto(Conversatie conversatie) {
        return new ConversatieDto(conversatie.getId(), conversatie.getTitlu());
    }

    public List<ConversatieDto> getConversationsForUser(Long userId) {

        Integer safeUserId = userId.intValue();



        Utilizatori utilizator = utilizatoriRepository.findById(safeUserId);

        if (utilizator == null) {
            System.out.println("eroareee");
            throw new IllegalArgumentException("Utilizatorul nu a fost găsit.");
        }



        List<ParticipantConversatie> participantConversaties = participantConversatieRepository.findByUtilizatoriId(userId.intValue());
        List<ConversatieDto> conversatieDtos = participantConversaties.stream()
                .map(participant -> participant.getConversatie())
                .distinct()
                .map(conversatie -> new ConversatieDto(conversatie.getId(), conversatie.getTitlu()))
                .collect(Collectors.toList());

        return conversatieDtos;
    }


}
