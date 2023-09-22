package com.andreea.librarium.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
@Table(name = "rezervari_sali")
public class RezervariSali {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rezervare_sala", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_utilizator", nullable = false)
    private Utilizatori idUtilizator;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_sala", nullable = false)
    private SaliBiblioteca idSala;

    @NotNull
    @Column(name = "data_ora_inceput", nullable = false)
    private Instant dataOraInceput;

    @NotNull
    @Column(name = "data_ora_final", nullable = false)
    private Instant dataOraFinal;

    @NotNull
    @Column(name = "stare_rezervare", nullable = false)
    private Boolean stareRezervare = false;

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

    public SaliBiblioteca getIdSala() {
        return idSala;
    }

    public void setIdSala(SaliBiblioteca idSala) {
        this.idSala = idSala;
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

    public Boolean getStareRezervare() {
        return stareRezervare;
    }

    public void setStareRezervare(Boolean stareRezervare) {
        this.stareRezervare = stareRezervare;
    }

}