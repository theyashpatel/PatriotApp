package ypdp.service.impl;

import ypdp.api.LoginRequest;
import ypdp.api.LoginResponse;
import ypdp.dao.Session;
import ypdp.dao.Users;
import ypdp.dao.repository.SessionRepository;
import ypdp.dao.repository.UserRepository;
import ypdp.exception.AuthenticationException;
import ypdp.exception.BadRequestException;
import ypdp.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        validateEmail(email);
        Users user = userRepository.findByEmail(email);
        if (user == null) {
            throw new AuthenticationException();
        }
        boolean passwordMatched = BCrypt.checkpw(password, user.getPassword());
        if (!passwordMatched) {
            throw new AuthenticationException();
        }

        String token = randomId();

        LoginResponse lr = new LoginResponse();
        lr.setToken(token);
        lr.setFirstName(user.getFirstName());
        lr.setLastName(user.getLastName());
        lr.setgNumber(user.getgNumber());

        saveSession(user.getId(), token);
        return lr;
    }

    private void saveSession(String userId, String token) {
        Session s = new Session();
        s.setId(token);
        s.setStartDate(getCurrentDate());
        s.setUserId(userId);

        sessionRepository.save(s);
    }

    private String encrypt(String value) {
        String salt = BCrypt.gensalt(6);
        return BCrypt.hashpw(value, salt);
    }

    private String randomId() {
        return UUID.randomUUID().toString();
    }

    private Date getCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    private void validateEmail(String email) {
        if (StringUtils.isBlank(email)) {
            throw new BadRequestException("MISSING_EMAIL");
        }
    }
}
