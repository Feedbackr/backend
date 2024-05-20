package me.invis.Feedbackr.security.model;

import org.springframework.security.core.GrantedAuthority;

public record FeedbackrRole(String authority) implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return authority;
    }
}
