package com.aboguslawski.blog.registration.token;

import com.aboguslawski.blog.model.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepo confirmationTokenRepo;

    public void saveConfirmationToken(ConfirmationToken token){
        confirmationTokenRepo
                .save(token);
    }

    public Optional<ConfirmationToken> getToken(String token){
        return confirmationTokenRepo
                .findByToken(token);
    }

    public int setConfirmedAt(String token){
        return confirmationTokenRepo
                .updateConfirmedAt(token, LocalDateTime.now());
    }

    public String deleteToken(User u){
        List<ConfirmationToken> a = confirmationTokenRepo.findAll().stream().filter(t -> t.getUser() == u).collect(Collectors.toList());
        confirmationTokenRepo.deleteAll(a);

        return "deleted tokens of user " + u.getEmail();
    }
}
