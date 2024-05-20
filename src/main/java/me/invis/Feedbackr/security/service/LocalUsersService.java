package me.invis.Feedbackr.security.service;

import me.invis.Feedbackr.security.model.FeedbackrRole;
import me.invis.Feedbackr.security.model.FeedbackrUser;
import me.invis.Feedbackr.security.model.PEncoder;
import me.invis.Feedbackr.security.storage.FeedbackrUsersRepository;
import me.invis.Feedbackr.storage.receiver.model.PublicFeedbackReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalUsersService {

    @Autowired
    FeedbackrUsersRepository usersRepo;

    public void addUser(String username, String password, PublicFeedbackReceiver publicFeedbackReceiver) {
        FeedbackrUser feedbackrUser = new FeedbackrUser();

        feedbackrUser.setReceiverDetails(publicFeedbackReceiver);
        feedbackrUser.setUsername(username);
        feedbackrUser.setPassword(new PEncoder().encode(password));
        feedbackrUser.setGrantedAuths(
                new FeedbackrRole[]{
                        new FeedbackrRole("USER")
                });

        usersRepo.save(feedbackrUser);
    }

}
