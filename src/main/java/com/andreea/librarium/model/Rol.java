package com.andreea.librarium.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "nume_rol", nullable = false)
    private String numeRol;

    @OneToMany(mappedBy = "idRol")
    private Set<RoluriUtilizatori> roluriUtilizatoris = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeRol() {
        return numeRol;
    }

    public void setNumeRol(String numeRol) {
        this.numeRol = numeRol;
    }

    public Set<RoluriUtilizatori> getRoluriUtilizatoris() {
        return roluriUtilizatoris;
    }

    public void setRoluriUtilizatoris(Set<RoluriUtilizatori> roluriUtilizatoris) {
        this.roluriUtilizatoris = roluriUtilizatoris;
    }


}