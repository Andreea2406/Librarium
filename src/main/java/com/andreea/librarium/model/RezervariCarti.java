package com.andreea.librarium.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "rezervari_carti")
public class RezervariCarti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rezervare", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_carte", nullable = false)
    private Carti idCarte;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_utilizator", nullable = false)
    private Utilizatori idUtilizator;

    @NotNull
    @Column(name = "data_rezervare", nullable = false)
    private LocalDate dataRezervare;

    @NotNull
    @Column(name = "stare_rezervare", nullable = false)
    private Boolean stareRezervare = false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Carti getIdCarte() {
        return idCarte;
    }

    public void setIdCarte(Carti idCarte) {
        this.idCarte = idCarte;
    }

    public Utilizatori getIdUtilizator() {
        return idUtilizator;
    }

    public void setIdUtilizator(Utilizatori idUtilizator) {
        this.idUtilizator = idUtilizator;
    }

    public LocalDate getDataRezervare() {
        return dataRezervare;
    }

    public void setDataRezervare(LocalDate dataRezervare) {
        this.dataRezervare = dataRezervare;
    }

    public Boolean getStareRezervare() {
        return stareRezervare;
    }

    public void setStareRezervare(Boolean stareRezervare) {
        this.stareRezervare = stareRezervare;
    }

}