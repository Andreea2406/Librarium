package com.andreea.librarium.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
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
    private Instant dataRezervare;

    @NotNull
    @Column(name = "data_expirarii", nullable = false)
    private Instant dataExpirarii ;

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

    public Instant getDataRezervare() {
        return dataRezervare;
    }

    public void setDataRezervare(Instant dataRezervare) {
        this.dataRezervare = dataRezervare;
    }

    public Instant getDataExpirarii() {
        return dataExpirarii;
    }

    public void setDataExpirarii(Instant dataExpirarii) {
        this.dataExpirarii = dataExpirarii;
    }
}