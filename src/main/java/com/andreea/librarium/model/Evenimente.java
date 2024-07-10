package com.andreea.librarium.model;

import com.andreea.librarium.serialization.TimeStampDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.security.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "evenimente")
public class Evenimente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_eveniment", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "nume", nullable = false)
    private String nume;


    public String getDataInceput() {
        return dataInceput;
    }

    @NotNull
    @Column(name = "data_inceput", nullable = false)
    @JsonFormat(pattern = "dd.MM.yyyy")

    private String dataInceput;

    @NotNull
    @Column(name = "ora_inceput", nullable = false)
    @JsonFormat(pattern = "HH:mm")

    private String oraInceput;

    @NotNull
    @Column(name = "data_final", nullable = false)
    @JsonFormat(pattern = "dd.MM.yyyy")

    private String dataFinal;

    @NotNull
    @Column(name = "ora_final", nullable = false)
    @JsonFormat(pattern = "HH:mm")

    private String oraFinal;


    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_sala", nullable = false)
    private SaliBiblioteca idSala;

    @Size(max = 65535)
    @NotNull
    @Column(name = "descriere", nullable = false)
    private String descriere;

    @OneToMany(mappedBy = "idEveniment")
    private Set<InregistrareEveniment> inregistrareEveniments = new LinkedHashSet<>();

    public Evenimente() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }



    public void setDataInceput(String dataInceput) {
        this.dataInceput = dataInceput;
    }

    public String getOraInceput() {
        return oraInceput;
    }

    public void setOraInceput(String oraInceput) {
        this.oraInceput = oraInceput;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getOraFinal() {
        return oraFinal;
    }

    public void setOraFinal(String oraFinal) {
        this.oraFinal = oraFinal;
    }

    public String  getDataFinal() {
        return dataFinal;
    }



    public SaliBiblioteca getIdSala() {
        return idSala;
    }

    public void setIdSala(SaliBiblioteca idSala) {
        this.idSala = idSala;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public Set<InregistrareEveniment> getInregistrareEveniments() {
        return inregistrareEveniments;
    }

    public void setInregistrareEveniments(Set<InregistrareEveniment> inregistrareEveniments) {
        this.inregistrareEveniments = inregistrareEveniments;
    }






}