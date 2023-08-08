package com.andreea.librarium.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "carti")
public class Carti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carte", nullable = false)
    private Integer id;

    @Column(name = "titlu", nullable = false)
    private String titlu;

    @Column(name = "autor", nullable = false)
    private String autor;

    @Column(name = "categorie", nullable = false)
    private String categorie;

    @Column(name = "limba", nullable = false)
    private String limba;

    @Column(name = "editura", nullable = false)
    private String editura;

    @Column(name = "tip_coperta", nullable = false)
    private String tipCoperta;

    @Column(name = "nr_pagini", nullable = false)
    private Integer nrPagini;

    @Column(name = "colectie", nullable = false)
    private String colectie;

    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Column(name = "latime", nullable = false)
    private String latime;

    @Column(name = "inaltime", nullable = false)
    private String inaltime;

    @Column(name = "descriere", nullable = false)
    private String descriere;

    @Column(name = "data_publicarii", nullable = false)
    private LocalDate dataPublicarii;

    @Column(name = "disponibilitate", nullable = false)
    private Integer disponibilitate;

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

    public LocalDate getDataPublicarii() {
        return dataPublicarii;
    }

    public void setDataPublicarii(LocalDate dataPublicarii) {
        this.dataPublicarii = dataPublicarii;
    }

    public Integer getDisponibilitate() {
        return disponibilitate;
    }

    public void setDisponibilitate(Integer disponibilitate) {
        this.disponibilitate = disponibilitate;
    }

}