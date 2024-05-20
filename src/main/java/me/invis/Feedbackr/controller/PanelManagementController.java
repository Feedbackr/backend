package me.invis.Feedbackr.controller;

import me.invis.Feedbackr.security.model.FeedbackrUser;
import me.invis.Feedbackr.service.FeedbackReceiverService;
import me.invis.Feedbackr.service.FeedbackService;
import me.invis.Feedbackr.storage.receiver.model.PublicFeedbackReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PanelManagementController {

    @Autowired
    private FeedbackReceiverService feedbackReceiverService;
    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/panel")
    public String getPanel(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!authentication.isAuthenticated()) return "redirect:/login";

        FeedbackrUser user = (FeedbackrUser) authentication.getPrincipal();

        PublicFeedbackReceiver receiverInfo = user.getReceiverDetails();
        if (receiverInfo == null) return "redirect:/404";

        model.addAttribute("receiverId", receiverInfo.getId());
        model.addAttribute("receiverLogo", receiverInfo.getCompany_logo_link());
        model.addAttribute("feedbacks", feedbackService.getFeedbacksByReceiver(receiverInfo.getId()).reversed());

        return "panel";
    }

}
