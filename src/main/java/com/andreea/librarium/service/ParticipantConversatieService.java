package com.andreea.librarium.service;

import com.andreea.librarium.model.Conversatie;
import com.andreea.librarium.model.ParticipantConversatie;
import com.andreea.librarium.model.Utilizatori;
import com.andreea.librarium.repositories.ConversatieRepository;
import com.andreea.librarium.repositories.ParticipantConversatieRepository;
import com.andreea.librarium.repositories.UtilizatoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ParticipantConversatieService {

    private final ParticipantConversatieRepository participantConversatieRepository;
    private final ConversatieRepository conversatieRepository;
    private final UtilizatoriRepository utilizatoriRepository;

    @Autowired
    public ParticipantConversatieService(ParticipantConversatieRepository participantConversatieRepository,
                                         ConversatieRepository conversatieRepository,
                                         UtilizatoriRepository utilizatoriRepository) {
        this.participantConversatieRepository = participantConversatieRepository;
        this.conversatieRepository = conversatieRepository;
        this.utilizatoriRepository = utilizatoriRepository;
    }
    @Transactional
    public ParticipantConversatie addUser(Integer idConversatie, Integer idUtilizator) {
        Conversatie conversatie = conversatieRepository.findById(idConversatie)
                .orElseThrow(() -> new IllegalArgumentException("Conversația nu a fost găsită."));

        Utilizatori utilizator = utilizatoriRepository.findByIdAsInteger(idUtilizator)
                .orElseThrow(() -> new IllegalArgumentException("Utilizatorul nu a fost găsit."));

        ParticipantConversatie participant = new ParticipantConversatie();
        participant.setConversatie(conversatie);
        participant.setUtilizatori(utilizator);

        return participantConversatieRepository.save(participant);
    }
    @Transactional
    public ParticipantConversatie adaugaParticipant(Integer idConversatie, Long idUtilizator) {
        Conversatie conversatie = conversatieRepository.findById(idConversatie)
                .orElseThrow(() -> new IllegalArgumentException("Conversația nu a fost găsită."));


        Utilizatori utilizator = utilizatoriRepository.findById(idUtilizator)
                .orElseThrow(() -> new IllegalArgumentException("Utilizatorul nu a fost găsit."));

        ParticipantConversatie participant = new ParticipantConversatie();
        participant.setConversatie(conversatie);
        participant.setUtilizatori(utilizator);

        return participantConversatieRepository.save(participant);
    }

    @Transactional
    public void eliminaParticipant(Integer idParticipant) {
        ParticipantConversatie participant = participantConversatieRepository.findById(idParticipant)
                .orElseThrow(() -> new IllegalArgumentException("Participantul nu a fost găsit."));
        participantConversatieRepository.delete(participant);
    }
    public boolean existaRelatiaUtilizatorConversatie(Integer userId, Integer conversatieId) {

        return participantConversatieRepository.existsByUtilizatoriIdAndConversatieId(userId, conversatieId);
    }
}
