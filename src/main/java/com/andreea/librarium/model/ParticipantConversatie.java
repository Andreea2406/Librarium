package com.andreea.librarium.model;

import org.springframework.data.relational.core.sql.In;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "participanti_conversatie")
public class ParticipantConversatie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_participant")
    private Integer idParticipant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_conversatie", nullable = false)
    private Conversatie conversatie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_utilizator", nullable = false)
    private Utilizatori utilizatori;



    public Integer getIdParticipant() {
        return idParticipant;
    }

    public void setIdParticipant(Integer idParticipant) {
        this.idParticipant = idParticipant;
    }

    public Conversatie getConversatie() {
        return conversatie;
    }

    public void setConversatie(Conversatie conversatie) {
        this.conversatie = conversatie;
    }

    public Utilizatori getUtilizatori() {
        return utilizatori;
    }

    public void setUtilizatori(Utilizatori utilizatori) {
        this.utilizatori = utilizatori;
    }
}

