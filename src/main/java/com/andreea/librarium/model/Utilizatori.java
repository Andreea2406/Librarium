package com.andreea.librarium.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "utilizatori")
public class Utilizatori {
    private String selectedRole;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utilizator", unique = true, nullable = false)
    private Integer id;

    @Size(max = 255)
    // @NotNull
    @Column(name = "nume", nullable = false)
    private String nume;

    @Size(max = 255)
    //@NotNull
    @Column(name = "prenume", nullable = false)
    private String prenume;

    //@NotNull
    @Size(max = 255)
    @Column(name = "CNP", nullable = false)
    private String CNP;

    @Size(max = 255)
    // @NotNull
    @Column(name = "telefon", nullable = false)
    private String telefon;

    @Size(max = 255)
    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Size(max = 255)
    //@NotNull
    @Column(name = "strada", nullable = false)
    private String strada;

    @Size(max = 255)
    //@NotNull
    @Column(name = "oras", nullable = false)
    private String oras;

    @Size(max = 50)
    //@NotNull
    @Column(name = "cod_postal", nullable = false, length = 50)
    private String codPostal;

    @Size(max = 255)
    //@NotNull
    @Column(name = "judet", nullable = false)
    private String judet;

    @Size(max = 50)
    //@NotNull
    @Column(name = "apartament", nullable = false, length = 50)
    private String apartament;

    @Size(max = 255)
    //@NotNull
    @Column(name = "numar", nullable = false)
    private String numar;

    @Size(max = 255)
    //@NotNull
    @Column(name = "scara", nullable = false)
    private String scara;

    @Size(max = 255)
    @Column(name = "ocupatie")
    private String ocupatie;

    @Size(max = 255)
    @NotNull
    @Column(name = "parola", nullable = false)
    private String parola;

    @OneToMany(mappedBy = "idUtilizator")
    private Set<RezervariSali> rezervariSalis = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUtilizator")
    private Set<RezervariCarti> rezervariCartis = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUtilizator")
    private Set<Imprumuturi> imprumuturis = new LinkedHashSet<>();

    //    @OneToMany(mappedBy = "idUtilizator")
//    private Set<RoluriUtilizatori> roluriUtilizatoris = new LinkedHashSet<>();
    @OneToMany(mappedBy = "idUtilizator", fetch = FetchType.EAGER)
    private Set<RoluriUtilizatori> roluriUtilizatoris = new LinkedHashSet<>();


    //    public Set<Rol> getRoles() {
//        return roluriUtilizatoris.stream().map(RoluriUtilizatori::getIdRol).collect(Collectors.toSet());
//    }
    public Set<Rol> getRole() {
        return roluriUtilizatoris.stream()
                .map(RoluriUtilizatori::getIdRol)
                .collect(Collectors.toSet());
    }


    @OneToMany(mappedBy = "idUtilizator")
    private Set<InregistrareEveniment> inregistrareEveniments = new LinkedHashSet<>();

    public Utilizatori() {
    }

    public Utilizatori(Integer id, String nume, String prenume, String CNP, String telefon, String email, String strada, String oras, String codPostal, String judet, String apartament, String numar, String scara, String ocupatie, String parola) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.CNP = CNP;
        this.telefon = telefon;
        this.email = email;
        this.strada = strada;
        this.oras = oras;
        this.codPostal = codPostal;
        this.judet = judet;
        this.apartament = apartament;
        this.numar = numar;
        this.scara = scara;
        this.ocupatie = ocupatie;
        this.parola = parola;
        this.rezervariSalis = rezervariSalis;
        this.rezervariCartis = rezervariCartis;
        this.imprumuturis = imprumuturis;
        this.roluriUtilizatoris = roluriUtilizatoris;
        this.inregistrareEveniments = inregistrareEveniments;
    }

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

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
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
    //    public String getStrada() {
//        return strada;
//    }

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

    public String getNumar() {
        return numar;
    }

    public void setNumar(String numar) {
        this.numar = numar;
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

    public Set<RezervariSali> getRezervariSalis() {
        return rezervariSalis;
    }

    public void setRezervariSalis(Set<RezervariSali> rezervariSalis) {
        this.rezervariSalis = rezervariSalis;
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

    public Set<RoluriUtilizatori> getRoluriUtilizatoris() {
        return roluriUtilizatoris;
    }

    public void setRoluriUtilizatoris(Set<RoluriUtilizatori> roluriUtilizatoris) {
        this.roluriUtilizatoris = roluriUtilizatoris;
    }

    public Set<InregistrareEveniment> getInregistrareEveniments() {
        return inregistrareEveniments;
    }

    public void setInregistrareEveniments(Set<InregistrareEveniment> inregistrareEveniments) {
        this.inregistrareEveniments = inregistrareEveniments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilizatori that = (Utilizatori) o;
        return Objects.equals(id, that.id) && Objects.equals(nume, that.nume) && Objects.equals(prenume, that.prenume) && Objects.equals(CNP, that.CNP) && Objects.equals(telefon, that.telefon) && Objects.equals(email, that.email) && Objects.equals(strada, that.strada) && Objects.equals(oras, that.oras) && Objects.equals(codPostal, that.codPostal) && Objects.equals(judet, that.judet) && Objects.equals(apartament, that.apartament) && Objects.equals(numar, that.numar) && Objects.equals(scara, that.scara) && Objects.equals(ocupatie, that.ocupatie) && Objects.equals(parola, that.parola);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, nume, prenume, CNP, telefon, email, strada, oras, codPostal, judet, apartament, numar, scara, ocupatie, parola);
    }

    @Override
    public String toString() {
        return "Utilizatori{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", CNP=" + CNP +
                ", telefon='" + telefon + '\'' +
                ", email='" + email + '\'' +
                ", strada='" + strada + '\'' +
                ", oras='" + oras + '\'' +
                ", codPostal='" + codPostal + '\'' +
                ", judet='" + judet + '\'' +
                ", apartament='" + apartament + '\'' +
                ", numar='" + numar + '\'' +
                ", scara='" + scara + '\'' +
                ", ocupatie='" + ocupatie + '\'' +

                '}';
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "roluri_utilizatori",
            joinColumns = @JoinColumn(name = "id_utilizator"),
            inverseJoinColumns = @JoinColumn(name = "id_rol")
    )

    private Set<Rol> role = new HashSet<>();

    public void addRole(Rol role) {
        this.role.add(role);
    }

//        @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @ManyToMany
    @JoinTable(
            name = "roluri_utilizatori",
            joinColumns = @JoinColumn(name = "id_utilizator"),
            inverseJoinColumns = @JoinColumn(name = "id_rol")
    )
//    private Set<Rol> roles = new HashSet<>();
    public String getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(String selectedRole) {
        this.selectedRole = selectedRole;
    }

    public void setRole(Set<Rol> role) {
        this.role = role;
    }
    public void getRoles(Set<Rol> roles) {
        this.role = roles;
    }

    public Utilizatori orElseThrow(Object o) {
        return null;
    }
}