package me.invis.Feedbackr.controller;

import me.invis.Feedbackr.service.FeedbackReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicAPIController {

    @Autowired
    private FeedbackReceiverService feedbackReceiverService;

    @GetMapping("/api/check/company_name/{company}")
    public ResponseEntity<?> checkCompany(@PathVariable String company) {
        return translateBoolean(feedbackReceiverService.receiverWithNameExists(company));
    }

    @GetMapping("/api/check/email/{email}")
    public ResponseEntity<?> checkEmail(@PathVariable String email) {
        return translateBoolean(feedbackReceiverService.receiverWithEmailExists(email));
    }

    @GetMapping("/api/check/company_website/{website}")
    public ResponseEntity<?> checkWebsite(@PathVariable String website) {
        return translateBoolean(feedbackReceiverService.receiverWithWebsiteExists(website));
    }

    private ResponseEntity<?> translateBoolean(boolean b) {
        return b ? ResponseEntity.status(200).build() : ResponseEntity.status(404).build();
    }
}
