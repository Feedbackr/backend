package me.invis.Feedbackr.storage.feedback;

import me.invis.Feedbackr.storage.feedback.model.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackRepository extends MongoRepository<Feedback, String> {
    @Query("{ 'receiver' : ?0 }")
    Optional<List<Feedback>> findAllByReceiver(String receiver);
}
