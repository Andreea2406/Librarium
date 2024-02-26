package com.andreea.librarium.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
@Table(name = "imprumuturi")
public class Imprumuturi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imrpumut", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_utilizator", nullable = false)
    private Utilizatori idUtilizator;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_carte", nullable = false)
    private Carti idCarte;

    @NotNull
    @Column(name = "data_ora_imprumut", nullable = false)
    private Instant dataOraImprumut;

    @NotNull
    @Column(name = "data_ora_return", nullable = false)
    private Instant dataOraReturn;
    @NotNull
    @Column(name = "isFinalizat", nullable = false)
    private Boolean isFinalizat;

    // Getter și setter pentru isFinalizat
    public Boolean getIsFinalizat() {
        return isFinalizat;
    }

    public void setIsFinalizat(Boolean isFinalizat) {
        this.isFinalizat = isFinalizat;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Utilizatori getIdUtilizator() {
        return idUtilizator;
    }

    public void setIdUtilizator(Utilizatori idUtilizator) {
        this.idUtilizator = idUtilizator;
    }

    public Carti getIdCarte() {
        return idCarte;
    }

    public void setIdCarte(Carti idCarte) {
        this.idCarte = idCarte;
    }

    public Instant getDataOraImprumut() {
        return dataOraImprumut;
    }

    public void setDataOraImprumut(Instant dataOraImprumut) {
        this.dataOraImprumut = dataOraImprumut;
    }

    public Instant getDataOraReturn() {
        return dataOraReturn;
    }

    public void setDataOraReturn(Instant dataOraReturn) {
        this.dataOraReturn = dataOraReturn;
    }

}