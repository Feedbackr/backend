package me.invis.Feedbackr.storage.support.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("opened_support_tickets")
@Getter @Setter
public class SupportTicket {
    @Id
    private String id;

    private String email;
    private String ticket_content;
}
