package me.invis.Feedbackr.service;

import me.invis.Feedbackr.exception.ReceiverDupeException;
import me.invis.Feedbackr.security.service.LocalUsersService;
import me.invis.Feedbackr.storage.receiver.FeedbackReceiverRepository;
import me.invis.Feedbackr.storage.receiver.model.FeedbackReceiver;
import me.invis.Feedbackr.storage.receiver.model.PublicFeedbackReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeedbackReceiverService {

    @Autowired
    private FeedbackReceiverRepository feedbackReceiverRepository;

    @Autowired
    private LocalUsersService localUsersService;

    public void registerNewFeedbackReceiver(FeedbackReceiver receiver) {
        if (feedbackReceiverRepository.existsByEmail(receiver.getEmail()))
            throw new ReceiverDupeException(ReceiverDupeException.DupeType.EMAIL);

        if (feedbackReceiverRepository.existsByCompanyName(receiver.getCompanyName()))
            throw new ReceiverDupeException(ReceiverDupeException.DupeType.COMPANY_NAME);

        if (feedbackReceiverRepository.existsByCompanyWebsite(receiver.getCompanyWebsite()))
            throw new ReceiverDupeException(ReceiverDupeException.DupeType.COMPANY_WEBSITE);

        feedbackReceiverRepository.save(receiver);
        localUsersService.addUser(receiver.getEmail(), receiver.getPassword(), new PublicFeedbackReceiver(receiver));
    }

    public boolean receiverWithNameExists(String name) {
        return feedbackReceiverRepository.existsByCompanyName(name);
    }

    public boolean receiverWithWebsiteExists(String website) {
        String t1 = "https://" + website;
        String t2 = "http://" + website;
        return feedbackReceiverRepository.existsByCompanyWebsite(t1) || feedbackReceiverRepository.existsByCompanyWebsite(t2);
    }

    public boolean receiverWithEmailExists(String email) {
        return feedbackReceiverRepository.existsByEmail(email);
    }

    public PublicFeedbackReceiver getPublicReceiverInfoById(String id) {
        Optional<FeedbackReceiver> receiver = feedbackReceiverRepository.findById(id);
        if (receiver.isEmpty()) return null;

        FeedbackReceiver feedbackReceiver = receiver.get();
        return new PublicFeedbackReceiver(
                feedbackReceiver.getId(),
                feedbackReceiver.getCompanyName(),
                feedbackReceiver.getCompanyWebsite(),
                feedbackReceiver.getCompany_logo_link()
        );
    }

    public FeedbackReceiver getReceiverByEmail(String email) {
        Optional<FeedbackReceiver> receiver = feedbackReceiverRepository.findByEmail(email);
        return receiver.orElse(null);
    }

    public FeedbackReceiver getReceiverById(String id) {
        Optional<FeedbackReceiver> receiver = feedbackReceiverRepository.findById(id);
        return receiver.orElse(null);
    }

    public void updateFeedbackReceiver(PublicFeedbackReceiver receiver) {
        Optional<FeedbackReceiver> feedbackReceiver = feedbackReceiverRepository.findById(receiver.getId());
        if (feedbackReceiver.isEmpty()) return;

        FeedbackReceiver receiverToUpdate = feedbackReceiver.get();
        receiverToUpdate.setCompanyName(receiver.getCompany_name());
        receiverToUpdate.setCompanyWebsite(receiver.getCompany_website());
        receiverToUpdate.setCompany_logo_link(receiver.getCompany_logo_link());

        feedbackReceiverRepository.save(receiverToUpdate);
    }
}
