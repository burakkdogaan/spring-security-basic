package com.example.security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserAuthorizationService {
    @Autowired
    UserRepository userRepository;
    public boolean canUpdate(long loggedInUserId, long pathUserId) {
        if (loggedInUserId != pathUserId) {
            return false;
        }
        Optional<User1> optionalUser1 = userRepository.findById(pathUserId);
        if (!optionalUser1.isPresent())
            return false;
        User1 inDB = optionalUser1.get();
        LocalDateTime twentyFourHoursAgo = LocalDateTime.now().minusHours(24);
        if (inDB.getLastUpdated() != null && inDB.getLastUpdated().isAfter(twentyFourHoursAgo)) {
            return false;
        }
        return true;
    }
}
