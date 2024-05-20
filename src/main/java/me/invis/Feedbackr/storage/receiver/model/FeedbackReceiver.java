package me.invis.Feedbackr.storage.receiver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("feedback_collectors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackReceiver {

    @Id
    private String id;

    private String full_name;

    @Indexed(unique = true)
    private String email;

    private String password;

    @Indexed(unique = true)
    private String companyName;

    @Indexed(unique = true)
    private String companyWebsite;

    private String company_logo_link;

    @Indexed(unique = true)
    private String stripeSessionId;
}
