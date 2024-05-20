package me.invis.Feedbackr.service;

import me.invis.Feedbackr.storage.support.SupportTicketsRepository;
import me.invis.Feedbackr.storage.support.model.SupportTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupportTicketService {

    @Autowired
    private SupportTicketsRepository supportTicketsRepository;

    public void registerNewSupportTicket(SupportTicket supportTicket) {
        supportTicketsRepository.save(supportTicket);
    }

}
