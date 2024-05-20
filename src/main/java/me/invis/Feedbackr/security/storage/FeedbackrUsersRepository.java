package me.invis.Feedbackr.security.storage;

import me.invis.Feedbackr.security.model.FeedbackrUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FeedbackrUsersRepository extends MongoRepository<FeedbackrUser, String> {
    Optional<FeedbackrUser> findByUsername(String username);
}
