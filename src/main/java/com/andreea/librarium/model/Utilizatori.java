package com.andreea.librarium.model;

import jakarta.persistence.*;

@Entity
@Table(name = "utilizatori")
public class Utilizatori {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utilizator", nullable = false)
    private Integer id;

    @Column(name = "nume", nullable = false)
    private String nume;

    @Column(name = "prenume", nullable = false)
    private String prenume;

    @Column(name = "varsta", nullable = false)
    private Integer varsta;

    @Column(name = "telefon", nullable = false)
    private Integer telefon;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "strada", nullable = false)
    private String strada;

    @Column(name = "oras", nullable = false)
    private String oras;

    @Column(name = "cod_postal", nullable = false, length = 50)
    private String codPostal;

    @Column(name = "judet", nullable = false)
    private String judet;

    @Column(name = "apartament", nullable = false, length = 50)
    private String apartament;

    @Column(name = "bloc", nullable = false)
    private String bloc;

    @Column(name = "scara", nullable = false)
    private String scara;

    @Column(name = "ocupatie", nullable = false)
    private String ocupatie;

    @Column(name = "parola", nullable = false)
    private String parola;

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

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public Integer getVarsta() {
        return varsta;
    }

    public void setVarsta(Integer varsta) {
        this.varsta = varsta;
    }

    public Integer getTelefon() {
        return telefon;
    }

    public void setTelefon(Integer telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStrada() {
        return strada;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }

    public String getOras() {
        return oras;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public String getJudet() {
        return judet;
    }

    public void setJudet(String judet) {
        this.judet = judet;
    }

    public String getApartament() {
        return apartament;
    }

    public void setApartament(String apartament) {
        this.apartament = apartament;
    }

    public String getBloc() {
        return bloc;
    }

    public void setBloc(String bloc) {
        this.bloc = bloc;
    }

    public String getScara() {
        return scara;
    }

    public void setScara(String scara) {
        this.scara = scara;
    }

    public String getOcupatie() {
        return ocupatie;
    }

    public void setOcupatie(String ocupatie) {
        this.ocupatie = ocupatie;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

}