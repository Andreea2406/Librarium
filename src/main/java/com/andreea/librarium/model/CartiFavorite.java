package com.andreea.librarium.model;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "carti_favorite")
public class CartiFavorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "id_utilizator")
    private Integer utilizatorId;

    @Column(name = "id_carte")
    private Integer carteId;

    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_carte", insertable = false, updatable = false)
    private Carti carte;


    public Carti getCarte() {
        return carte;
    }

    public void setCarte(Carti carte) {
        this.carte = carte;
    }
    public CartiFavorite() {
    }

    public CartiFavorite(Integer utilizatorId, Integer carteId) {
        this.utilizatorId = utilizatorId;
        this.carteId = carteId;
        this.data = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUtilizatorId() {
        return utilizatorId;
    }

    public void setUtilizatorId(Integer utilizatorId) {
        this.utilizatorId = utilizatorId;
    }

    public Integer getCarteId() {
        return carteId;
    }

    public void setCarteId(Integer carteId) {
        this.carteId = carteId;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}