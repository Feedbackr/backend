package me.invis.Feedbackr.storage.receiver;

import me.invis.Feedbackr.storage.receiver.model.FeedbackReceiver;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FeedbackReceiverRepository extends MongoRepository<FeedbackReceiver, String> {
    boolean existsByEmail(String email);
    boolean existsByCompanyName(String companyName);
    boolean existsByCompanyWebsite(String companyWebsite);
    boolean existsByStripeSessionId(String stripeSessionId);

    Optional<FeedbackReceiver> findByEmail(String email);
}
