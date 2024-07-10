package com.andreea.librarium.controller;

import com.andreea.librarium.config.UserSession;
import com.andreea.librarium.dto.ConversatieDto;
import com.andreea.librarium.dto.MesajDto;
import com.andreea.librarium.dto.UserChatGroupDto;
import com.andreea.librarium.model.Conversatie;
import com.andreea.librarium.model.Mesaj;
import com.andreea.librarium.model.ParticipantConversatie;
import com.andreea.librarium.model.Utilizatori;
import com.andreea.librarium.repositories.ConversatieRepository;
import com.andreea.librarium.repositories.MesajRepository;
import com.andreea.librarium.repositories.ParticipantConversatieRepository;
import com.andreea.librarium.repositories.UtilizatoriRepository;
import com.andreea.librarium.service.ConversatieService;
import com.andreea.librarium.service.MesajService;
import com.andreea.librarium.service.ParticipantConversatieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bibliotecari")
public class BibliotecariMesajeController {

    private final UtilizatoriRepository utilizatoriRepository;
    private final UserSession userSession;
    private final ConversatieService conversatieService;
    private final ParticipantConversatieService participantConversatieService;
    private  final ConversatieRepository conversatieRepository;
    private  final ParticipantConversatieRepository participantConversatieRepository;
    private final MesajService mesajService;
    private final MesajRepository mesajRepository;


    @Autowired
    public BibliotecariMesajeController(UtilizatoriRepository utilizatoriRepository, UserSession userSession, ConversatieService conversatieService, ParticipantConversatieService participantConversatieService, ConversatieRepository conversatieRepository, ParticipantConversatieRepository participantConversatieRepository, MesajService mesajService, MesajRepository mesajRepository) {
        this.utilizatoriRepository = utilizatoriRepository;
        this.userSession = userSession;
        this.conversatieService = conversatieService;
        this.participantConversatieService = participantConversatieService;
        this.conversatieRepository = conversatieRepository;
        this.participantConversatieRepository = participantConversatieRepository;
        this.mesajService = mesajService;
        this.mesajRepository = mesajRepository;
    }

    @PostMapping("/addUserToConversatie")
    public ResponseEntity<?> addUserToConversatie(@RequestBody UserChatGroupDto dto) {
        try {
            Utilizatori user = utilizatoriRepository.findByEmail(dto.getEmail())
                    .orElseThrow(() -> new IllegalArgumentException("User not found."));
            Conversatie conversation = conversatieRepository.findById(dto.getConversatieId())
                    .orElseThrow(() -> new IllegalArgumentException("Conversation not found."));

            if (participantConversatieRepository.existsByUtilizatoriIdAndConversatieId(user.getId(), conversation.getId())) {
                return ResponseEntity.badRequest().body("User already a participant.");
            }

            ParticipantConversatie participant = new ParticipantConversatie();
            participant.setConversatie(conversation);
            participant.setUtilizatori(user);
            participantConversatieRepository.save(participant);

            return ResponseEntity.ok().body("User added successfully to conversation.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/conversatii")
    public ResponseEntity<List<ConversatieDto>> getAllConversatiiBibliotecari() {
        List<Conversatie> conversatii = conversatieService.findAll();
        List<ConversatieDto> conversatieDtos = conversatii.stream()
                .map(conversatie -> new ConversatieDto(conversatie.getId(), conversatie.getTitlu()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(conversatieDtos);
    }
    private Long getCurrentBibliotecarId() {
        return userSession.getUserId().longValue();
    }
    @PostMapping("/conversatii/create")
    public ResponseEntity<?> createConversatie() {
        try {
            Conversatie conversatie = conversatieService.creazaConversatieNouaCititori();
            return ResponseEntity.ok(conversatie);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Eroare la crearea conversatiei: " + e.getMessage());
        }
    }
    @GetMapping("/current-bibliotecar/conversations")
    public ResponseEntity<List<ConversatieDto>> getCurrentBibliotecarConversations(Principal principal) {
        Integer userId = userSession.getUserId();
        Long bibliotecarId = userId.longValue();


        List<ConversatieDto> conversatii = conversatieService.getConversationsForUser(bibliotecarId);

        return ResponseEntity.ok(conversatii);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserName(@PathVariable("userId") Integer userId) {
        Utilizatori user = utilizatoriRepository.findById(userId);

        if (user != null) {

            return ResponseEntity.ok(Collections.singletonMap("userName", user.getNume()));

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
    @GetMapping("/conversations/{conversatieId}/messages")
    public ResponseEntity<List<MesajDto>> getMessagesForConversation(@PathVariable Integer conversatieId) {

        List<MesajDto> messages = mesajService.findMessagesForConversation(conversatieId);
        return ResponseEntity.ok(messages);
    }
    @MessageMapping("/bibliotecar/sendMessage/{idConversatie}")
    @SendTo("/topic/bibliotecar/{idConversatie}")
    public MesajDto broadcastBibliotecariMessage(@Payload MesajDto mesajDto, @DestinationVariable Integer idConversatie) {
        Mesaj mesaj = convertToEntity(mesajDto);

        Conversatie conversatie = conversatieRepository.findById((int) idConversatie.longValue())
                .orElseThrow(() -> new RuntimeException("Conversația nu a fost găsită."));

        mesaj.setConversatie(conversatie);

        Utilizatori utilizator = utilizatoriRepository.findById((mesajDto.getIdUtilizator()).intValue());
        mesaj.setUtilizatori(utilizator);

        mesaj = mesajRepository.save(mesaj);

        return convertToDto(mesaj);
    }
    private Mesaj convertToEntity(MesajDto mesajDto) {

        Mesaj mesaj = new Mesaj();
        mesaj.setTextMesaj(mesajDto.getTextMesaj());
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Europe/Bucharest"));
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        mesaj.setDataOraMesaj(date);

        return mesaj;
    }
    private MesajDto convertToDto(Mesaj mesaj) {

        MesajDto mesajDto = new MesajDto();

        return mesajDto;
    }

}
