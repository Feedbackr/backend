package me.invis.Feedbackr.storage.receiver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublicFeedbackReceiver implements Serializable {

    public String id;
    private String company_name;
    private String company_website;
    private String company_logo_link;

    public PublicFeedbackReceiver(FeedbackReceiver receiver) {
        this(receiver.getId(), receiver.getCompanyName(), receiver.getCompanyWebsite(), receiver.getCompany_logo_link());
    }

}
