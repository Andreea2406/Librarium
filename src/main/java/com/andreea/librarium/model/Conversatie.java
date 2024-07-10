package com.andreea.librarium.model;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "conversatii")
public class Conversatie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conversatie")
    private Integer id;

    @Column(name = "titlu")
    private String titlu;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_creare")
    @CreatedDate
    private Date dataCreare = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ultima_actualizare")
    @LastModifiedDate

    private Date ultimaActualizare = new Date();

    @OneToMany(mappedBy = "conversatie")
    private Set<Mesaj> mesaje;
    @Column(name = "estePrivat")
    private boolean estePrivat= false;


    public boolean isEstePrivat() {
        return estePrivat;
    }

    public void setEstePrivat(boolean estePrivat) {
        this.estePrivat = estePrivat;
    }




    public void addUtilizator(Utilizatori utilizator) {
        if (this.utilizatori == null) {
            this.utilizatori = new HashSet<>();
        }
        this.utilizatori.add(utilizator);
    }
    @OneToMany(mappedBy = "conversatie", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ParticipantConversatie> participanti;

    @ManyToMany
    @JoinTable(
            name = "conversatie_utilizatori",
            joinColumns = @JoinColumn(name = "conversatie_id"),
            inverseJoinColumns = @JoinColumn(name = "utilizator_id")
    )
    private Set<Utilizatori> utilizatori = new HashSet<>();

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

    public Date getDataCreare() {
        return dataCreare;
    }

    public void setDataCreare(Date dataCreare) {
        this.dataCreare = dataCreare;
    }

    public Date getUltimaActualizare() {
        return ultimaActualizare;
    }

    public void setUltimaActualizare(Date ultimaActualizare) {
        this.ultimaActualizare = ultimaActualizare;
    }

    public Set<Mesaj> getMesaje() {
        return mesaje;
    }

    public void setMesaje(Set<Mesaj> mesaje) {
        this.mesaje = mesaje;
    }

    public void setUser(Utilizatori user) {
    }

    public void setAdmin(Utilizatori admin) {
    }

    public void setMessages(ArrayList<Object> objects) {
    }
}
