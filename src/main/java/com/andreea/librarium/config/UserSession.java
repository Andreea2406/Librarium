package com.andreea.librarium.config;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.aop.support.DefaultIntroductionAdvisor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Component
@SessionScope
public class UserSession {

    @JsonIgnore
    private List<DefaultIntroductionAdvisor> advisors;
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
