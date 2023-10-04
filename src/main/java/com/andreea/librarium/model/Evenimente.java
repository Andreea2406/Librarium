package com.andreea.librarium.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
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

    @NotNull
    @Column(name = "data_ora_inceput", nullable = false)
    private Instant dataOraInceput;

    @NotNull
    @Column(name = "data_ora_final", nullable = false)
    private Instant dataOraFinal;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_sala", nullable = false)
    private SaliBiblioteca idSala;

    @Size(max = 255)
    @NotNull
    @Column(name = "descriere", nullable = false)
    private String descriere;

    @OneToMany(mappedBy = "idEveniment")
    private Set<InregistrareEveniment> inregistrareEveniments = new LinkedHashSet<>();

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

    public Set<InregistrareEveniment> getInregistrareEveniments() {
        return inregistrareEveniments;
    }

    public void setInregistrareEveniments(Set<InregistrareEveniment> inregistrareEveniments) {
        this.inregistrareEveniments = inregistrareEveniments;
    }

}