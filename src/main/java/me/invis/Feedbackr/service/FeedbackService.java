package me.invis.Feedbackr.service;

import me.invis.Feedbackr.storage.feedback.FeedbackRepository;
import me.invis.Feedbackr.storage.feedback.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public void submitNewFeedback(Feedback feedback) {
        feedbackRepository.save(feedback);
    }

    public List<Feedback> getFeedbacksByReceiver(String receiver) {
        return feedbackRepository.findAllByReceiver(receiver).orElse(null);
    }
}
