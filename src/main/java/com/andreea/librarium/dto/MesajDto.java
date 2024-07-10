package com.andreea.librarium.dto;

import java.util.Date;

public class MesajDto {

    private Long id;
    private Integer idConversatie;
    private Long idUtilizator;
    private String textMesaj;
    private Date dataOraMesaj;


    public MesajDto() {
    }


    public MesajDto(Long id, Integer idConversatie, Long idUtilizator, String textMesaj, Date dataOraMesaj) {
        this.id = id;
        this.idConversatie = idConversatie;
        this.idUtilizator = idUtilizator;
        this.textMesaj = textMesaj;
        this.dataOraMesaj = dataOraMesaj;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdConversatie() {
        return idConversatie;
    }

    public void setIdConversatie(Integer idConversatie) {
        this.idConversatie = idConversatie;
    }

    public Long getIdUtilizator() {
        return idUtilizator;
    }

    public void setIdUtilizator(Long idUtilizator) {
        this.idUtilizator = idUtilizator;
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
