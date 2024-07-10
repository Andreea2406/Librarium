package com.andreea.librarium.dto;

public class ConversatieDto {
    private Integer id;
    private String titlu;

    public ConversatieDto(Integer id, String titlu) {
        this.id = id;
        this.titlu = titlu;
    }

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
}
