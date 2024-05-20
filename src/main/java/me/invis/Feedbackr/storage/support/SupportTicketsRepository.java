package me.invis.Feedbackr.storage.support;

import me.invis.Feedbackr.storage.support.model.SupportTicket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SupportTicketsRepository extends MongoRepository<SupportTicket, String> {}
