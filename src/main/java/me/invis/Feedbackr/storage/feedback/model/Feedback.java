package me.invis.Feedbackr.storage.feedback.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("feedbacks")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {

    @Id
    private String id;
    private String timestamp;
    private String receiver, senderEmail, feedbackContent;

}
