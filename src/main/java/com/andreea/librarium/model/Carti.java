package com.andreea.librarium.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.Year;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "carti")
public class Carti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carte", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "titlu", nullable = false)
    private String titlu;

    @Size(max = 255)
    @NotNull
    @Column(name = "autor", nullable = false)
    private String autor;

    @Size(max = 255)
    @NotNull
    @Column(name = "categorie", nullable = false)
    private String categorie;

    @Size(max = 255)
    @NotNull
    @Column(name = "limba", nullable = false)
    private String limba;

    @Size(max = 255)
    @NotNull
    @Column(name = "editura", nullable = false)
    private String editura;

    @Size(max = 255)
    @NotNull
    @Column(name = "tip_coperta", nullable = false)
    private String tipCoperta;

    @NotNull
    @Column(name = "nr_pagini", nullable = false)
    private Integer nrPagini;

    @Size(max = 255)
    @NotNull
    @Column(name = "colectie", nullable = false)
    private String colectie;

    @Size(max = 255)
    @NotNull
    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Size(max = 255)
    @NotNull
    @Column(name = "latime", nullable = false)
    private String latime;

    @Size(max = 255)
    @NotNull
    @Column(name = "inaltime", nullable = false)
    private String inaltime;

    @Size(max = 65535)
    @NotNull
    @Column(name = "descriere", nullable = false, columnDefinition = "LONGTEXT")
    private String descriere;


    @NotNull
    @Column(name = "data_publicarii", nullable = false)
    private Integer dataPublicarii;

    @NotNull
    @Column(name = "disponibilitate", nullable = false)
    private Integer disponibilitate;

    @OneToMany(mappedBy = "idCarte")
    private Set<RezervariCarti> rezervariCartis = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idCarte")
    private Set<Imprumuturi> imprumuturis = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getLimba() {
        return limba;
    }

    public void setLimba(String limba) {
        this.limba = limba;
    }

    public String getEditura() {
        return editura;
    }

    public void setEditura(String editura) {
        this.editura = editura;
    }

    public String getTipCoperta() {
        return tipCoperta;
    }

    public void setTipCoperta(String tipCoperta) {
        this.tipCoperta = tipCoperta;
    }

    public Integer getNrPagini() {
        return nrPagini;
    }

    public void setNrPagini(Integer nrPagini) {
        this.nrPagini = nrPagini;
    }

    public String getColectie() {
        return colectie;
    }

    public void setColectie(String colectie) {
        this.colectie = colectie;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getLatime() {
        return latime;
    }

    public void setLatime(String latime) {
        this.latime = latime;
    }

    public String getInaltime() {
        return inaltime;
    }

    public void setInaltime(String inaltime) {
        this.inaltime = inaltime;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public Integer getDataPublicarii() {
        return dataPublicarii;
    }

    public void setDataPublicarii(Integer dataPublicarii) {
        this.dataPublicarii = dataPublicarii;
    }

    public Integer getDisponibilitate() {
        return disponibilitate;
    }

    public void setDisponibilitate(Integer disponibilitate) {
        this.disponibilitate = disponibilitate;
    }

    public Set<RezervariCarti> getRezervariCartis() {
        return rezervariCartis;
    }

    public void setRezervariCartis(Set<RezervariCarti> rezervariCartis) {
        this.rezervariCartis = rezervariCartis;
    }

    public Set<Imprumuturi> getImprumuturis() {
        return imprumuturis;
    }

    public void setImprumuturis(Set<Imprumuturi> imprumuturis) {
        this.imprumuturis = imprumuturis;
    }

}