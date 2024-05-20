package me.invis.Feedbackr.controller;

import me.invis.Feedbackr.service.FeedbackReceiverService;
import me.invis.Feedbackr.service.FeedbackService;
import me.invis.Feedbackr.service.SupportTicketService;
import me.invis.Feedbackr.storage.feedback.model.Feedback;
import me.invis.Feedbackr.storage.receiver.model.FeedbackReceiver;
import me.invis.Feedbackr.storage.receiver.model.PublicFeedbackReceiver;
import me.invis.Feedbackr.storage.support.model.SupportTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;

@Controller
public class MainController {

    //https://placehold.co/512x512

    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private FeedbackReceiverService feedbackReceiverService;
    @Autowired
    private SupportTicketService supportTicketService;

    @GetMapping("/{feedbackReceiverId}")
    public String getFeedbackForm(Model model, @PathVariable String feedbackReceiverId) {
        PublicFeedbackReceiver receiverInfo = feedbackReceiverService.getPublicReceiverInfoById(feedbackReceiverId);
        if (receiverInfo == null) return "redirect:/404";

        if (receiverInfo.getCompany_logo_link() == null) receiverInfo.setCompany_logo_link("https://placehold.co/512x512");

        model.addAttribute("receiverLogo", receiverInfo.getCompany_logo_link());
        model.addAttribute("receiverName", receiverInfo.getCompany_name());

        return "post";
    }

    @PostMapping("/{feedbackReceiverId}")
    public String submitFeedback(@PathVariable String feedbackReceiverId, @RequestParam String email, @RequestParam String content) {
        FeedbackReceiver receiver = feedbackReceiverService.getReceiverById(feedbackReceiverId);

        if (receiver == null) return "redirect:/404";

        Feedback feedback = new Feedback();

        feedback.setReceiver(receiver.getId());
        feedback.setSenderEmail(email);
        feedback.setFeedbackContent(content);
        feedback.setTimestamp(Timestamp.from(Instant.now()).toString());

        feedbackService.submitNewFeedback(feedback);
        return "redirect:/success";
    }

    @GetMapping("/")
    public String landing() {
        return "landing";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/404")
    public String notFound() {
        return "404";
    }

    @GetMapping("/success")
    public String feedbackSubmitted() {
        return "feedback-submitted";
    }

    @PostMapping("/support")
    public String receiveSupportMessage(@RequestParam String email, @RequestParam String ticket_content) {
        SupportTicket supportTicket = new SupportTicket();

        supportTicket.setEmail(email);
        supportTicket.setTicket_content(ticket_content);

        supportTicketService.registerNewSupportTicket(supportTicket);

        return "redirect:/";
    }
}
