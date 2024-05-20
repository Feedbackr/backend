package me.invis.Feedbackr.security.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.invis.Feedbackr.storage.receiver.model.PublicFeedbackReceiver;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Setter
@Document("feedbackr_users")
public class FeedbackrUser implements UserDetails {

    @Id
    private String id;

    @Getter
    public PublicFeedbackReceiver receiverDetails;

    @Indexed(unique = true)
    private String username;

    private String password;
    private FeedbackrRole[] grantedAuths;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(grantedAuths);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
