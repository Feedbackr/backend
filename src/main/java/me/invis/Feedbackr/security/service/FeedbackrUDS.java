package me.invis.Feedbackr.security.service;

import me.invis.Feedbackr.security.storage.FeedbackrUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FeedbackrUDS implements UserDetailsService {

    @Autowired
    private FeedbackrUsersRepository feedbackrUsersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return feedbackrUsersRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User with " + username + " not found!"));
    }
}
