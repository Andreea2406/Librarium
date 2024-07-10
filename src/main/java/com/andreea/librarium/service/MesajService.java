package com.andreea.librarium.service;

import com.andreea.librarium.model.Mesaj;
import com.andreea.librarium.repositories.MesajRepository;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;
import com.andreea.librarium.dto.MesajDto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MesajService {
    private final MesajRepository mesajRepository;

    public MesajService(MesajRepository mesajRepository) {
        this.mesajRepository = mesajRepository;
    }

    public List<Mesaj> getMesajeByConversatieId(Integer conversatieId) {
        return mesajRepository.findByConversatieId(conversatieId);
    }
    public Mesaj saveMessage(Mesaj mesaj) {
        return mesajRepository.save(mesaj);
    }
    public List<MesajDto> findMessagesForConversation(Integer conversatieId) {
        List<Mesaj> messages = mesajRepository.findByConversatieId(conversatieId);
        return messages.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private MesajDto convertToDto(Mesaj mesaj) {
        MesajDto dto = new MesajDto();
        dto.setId(mesaj.getId());
        dto.setIdConversatie(mesaj.getConversatie().getId());
        dto.setIdUtilizator(Long.valueOf(mesaj.getUtilizatori().getId()));
        dto.setTextMesaj(mesaj.getTextMesaj());
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Europe/Bucharest"));
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        mesaj.setDataOraMesaj(date);


        return dto;
    }
}