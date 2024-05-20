package me.invis.Feedbackr.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import me.invis.Feedbackr.service.FeedbackReceiverService;
import me.invis.Feedbackr.storage.receiver.FeedbackReceiverRepository;
import me.invis.Feedbackr.storage.receiver.model.FeedbackReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FeedbackReceiverController {

    @Autowired
    private FeedbackReceiverService feedbackReceiverService;
    @Autowired
    private FeedbackReceiverRepository feedbackReceiverRepository;

    private boolean initSession(String str) {
        Session session;
        try {
            session = Session.retrieve(str);
        } catch (StripeException e) { return false; }
        if (!session.getPaymentLink().equals("plink_1P84G3A4WH7Apzqkp48EFQnd")) return false;
        if (feedbackReceiverRepository.existsByStripeSessionId(str)) return false;

        return true;
    }

    @GetMapping("/register/{payment_session_id}")
    public String getRegisterPage(@PathVariable String payment_session_id) {
        if(!initSession(payment_session_id)) return "404";
        return "register";
    }

    @PostMapping("/register/{payment_session_id}")
    public String registerNewFeedbackPage(@PathVariable String payment_session_id,
                                          @RequestParam String first_name, @RequestParam String last_name,
                                          @RequestParam String email, @RequestParam String password,
                                          @RequestParam String company_name, @RequestParam String company_url,
                                          @RequestParam String company_logo_url) {

        if(!initSession(payment_session_id)) return "404";

        FeedbackReceiver feedbackReceiver = new FeedbackReceiver();
        feedbackReceiver.setFull_name(first_name + " " + last_name);
        feedbackReceiver.setEmail(email);
        feedbackReceiver.setPassword(password);
        feedbackReceiver.setCompanyName(company_name);
        feedbackReceiver.setCompanyWebsite(company_url);
        feedbackReceiver.setCompany_logo_link(company_logo_url);
        feedbackReceiver.setStripeSessionId(payment_session_id);

        feedbackReceiverService.registerNewFeedbackReceiver(feedbackReceiver);
        return "redirect:/login";
    }

}
