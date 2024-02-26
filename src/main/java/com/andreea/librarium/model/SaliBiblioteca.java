package com.andreea.librarium.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "sali_biblioteca")
public class SaliBiblioteca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sala", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "nume", nullable = false)
    private String nume;

    @NotNull
    @Column(name = "locuri_disponibile", nullable = false)
    private Integer locuriDisponibile;

    @OneToMany(mappedBy = "idSala")
    private Set<RezervariSali> rezervariSalis = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idSala")
    private Set<Evenimente> evenimentes = new LinkedHashSet<>();

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

    public Integer getLocuriDisponibile() {
        return locuriDisponibile;
    }

    public void setLocuriDisponibile(Integer locuriDisponibile) {
        this.locuriDisponibile = locuriDisponibile;
    }

    public Set<RezervariSali> getRezervariSalis() {
        return rezervariSalis;
    }

    public void setRezervariSalis(Set<RezervariSali> rezervariSalis) {
        this.rezervariSalis = rezervariSalis;
    }

    public Set<Evenimente> getEvenimentes() {
        return evenimentes;
    }

    public void setEvenimentes(Set<Evenimente> evenimentes) {
        this.evenimentes = evenimentes;
    }

    public SaliBiblioteca orElseThrow(Object o) {
        return null;
    }
}