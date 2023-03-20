package com.webshop.webshopbackend.domain.DAO;

import com.webshop.webshopbackend.domain.entity.User;
import com.webshop.webshopbackend.domain.entity.VerificationToken;
import com.webshop.webshopbackend.domain.repository.UserRepository;
import com.webshop.webshopbackend.domain.repository.VerificationTokenRepository;
import com.webshop.webshopbackend.exception.NotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDAO implements DAO<User>{

    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;

    @Override
    public User getById(String id) throws NotFound {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFound("User", id));
    }

    public User getByEmail(String email) throws NotFound {
        Optional<User> result = userRepository.findByEmail(email);
        if(result.isPresent()) {
            return result.get();
        }else {
            throw new NotFound("User", email);
        }
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User saveToDatabase(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User update(String id, User userRequest) throws NotFound {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new NotFound("User", id));

        user.setName(userRequest.getName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        return userRepository.save(user);
    }

    @Override
    public void delete(String id) throws NotFound {
        if(userRepository.existsById(id))
            this.userRepository.deleteById(id);
        else {
            throw new NotFound("User", id);
        }
    }

    @Override
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        verificationTokenRepository.save(myToken);
    }

    public VerificationToken getVerificationToken(String token) {
        return verificationTokenRepository.findByToken(token)
                .orElseThrow(() -> new NotFound("Verification Token", token));
    }
}
