package com.andreea.librarium.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "inregistrare_eveniment")
public class InregistrareEveniment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inregistrare", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_eveniment", nullable = false)
    private Evenimente idEveniment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_utilizator", nullable = false)
    private Utilizatori idUtilizator;

    @Column(name = "data_inregistrarii", nullable = false)
    private LocalDate dataInregistrarii;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Evenimente getIdEveniment() {
        return idEveniment;
    }

    public void setIdEveniment(Evenimente idEveniment) {
        this.idEveniment = idEveniment;
    }

    public Utilizatori getIdUtilizator() {
        return idUtilizator;
    }

    public void setIdUtilizator(Utilizatori idUtilizator) {
        this.idUtilizator = idUtilizator;
    }

    public LocalDate getDataInregistrarii() {
        return dataInregistrarii;
    }

    public void setDataInregistrarii(LocalDate dataInregistrarii) {
        this.dataInregistrarii = dataInregistrarii;
    }

}