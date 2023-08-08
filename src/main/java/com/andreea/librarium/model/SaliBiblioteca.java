package com.andreea.librarium.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sali_biblioteca")
public class SaliBiblioteca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sala", nullable = false)
    private Integer id;

    @Column(name = "nume", nullable = false)
    private String nume;

    @Column(name = "locuri_disponibile", nullable = false)
    private Integer locuriDisponibile;

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

}