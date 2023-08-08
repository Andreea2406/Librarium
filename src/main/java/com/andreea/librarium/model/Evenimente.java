package com.andreea.librarium.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "evenimente")
public class Evenimente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_eveniment", nullable = false)
    private Integer id;

    @Column(name = "nume", nullable = false)
    private String nume;

    @Column(name = "data_ora_inceput", nullable = false)
    private Instant dataOraInceput;

    @Column(name = "data_ora_final", nullable = false)
    private Instant dataOraFinal;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_sala", nullable = false)
    private SaliBiblioteca idSala;

    @Column(name = "descriere", nullable = false)
    private String descriere;

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

    public Instant getDataOraInceput() {
        return dataOraInceput;
    }

    public void setDataOraInceput(Instant dataOraInceput) {
        this.dataOraInceput = dataOraInceput;
    }

    public Instant getDataOraFinal() {
        return dataOraFinal;
    }

    public void setDataOraFinal(Instant dataOraFinal) {
        this.dataOraFinal = dataOraFinal;
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

}