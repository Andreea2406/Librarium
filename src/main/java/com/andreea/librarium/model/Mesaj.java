package com.andreea.librarium.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mesaje")
public class Mesaj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mesaj")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_conversatie", nullable = false)
    private Conversatie conversatie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_utilizator", nullable = false)
    private Utilizatori utilizatori;

    @Lob
    @Column(name = "text_mesaj")
    private String textMesaj;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_ora_mesaj")
    @CreatedDate

    private Date dataOraMesaj=new Date();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Conversatie getConversatie() {
        return conversatie;
    }

    public void setConversatie(Conversatie conversatie) {
        this.conversatie = conversatie;
    }

    public Utilizatori getUtilizatori() {
        return utilizatori;
    }

    public void setUtilizatori(Utilizatori utilizatori) {
        this.utilizatori = utilizatori;
    }

    public String getTextMesaj() {
        return textMesaj;
    }

    public void setTextMesaj(String textMesaj) {
        this.textMesaj = textMesaj;
    }

    public Date getDataOraMesaj() {
        return dataOraMesaj;
    }

    public void setDataOraMesaj(Date dataOraMesaj) {
        this.dataOraMesaj = dataOraMesaj;
    }
}

