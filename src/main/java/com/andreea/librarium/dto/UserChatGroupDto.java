package com.andreea.librarium.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.relational.core.sql.In;

public class UserChatGroupDto {
    private String email;


    public UserChatGroupDto(Integer conversatieId) {
        this.conversatieId = conversatieId;
    }

    public UserChatGroupDto() {
    }

    public Integer getConversatieId() {
        return conversatieId;
    }


        @JsonCreator
        public UserChatGroupDto(@JsonProperty("email") String email,
                                @JsonProperty("conversatieId") Integer conversatieId) {
            this.email = email;
            this.conversatieId = conversatieId;
        }




    public void setConversatieId(Integer conversatieId) {
        this.conversatieId = conversatieId;
    }

    private Integer conversatieId;



    public UserChatGroupDto(String email) {
        this.email = email;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
