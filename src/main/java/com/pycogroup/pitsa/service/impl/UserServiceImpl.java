package com.pycogroup.pitsa.service.impl;

import com.pycogroup.pitsa.model.User;
import com.pycogroup.pitsa.repository.UserRepository;
import com.pycogroup.pitsa.security.JwtProvider;
import com.pycogroup.pitsa.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;

    private AuthenticationManager authenticationManager;

    private PasswordEncoder passwordEncoder;

    private JwtProvider jwtProvider;

    private boolean emailConfirmed;
    private boolean phoneConfirmed;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    /**
     * Create a new user in the database.
     *
     * @param email username
     * @param phone username
     * @param password password
     * @param firstname first name
     * @param lastname last name
     * @return Optional of the Java Web Token, empty otherwise.
     */
    @Override
    public Optional<String> signUp(String phone, String password, String lastname, String firstname, String email) {
        LOGGER.info("New user is registering a new account ");
        Optional<User> user;
        Optional<String> token = Optional.empty();

        if (!userRepository.findByPhone(phone).isPresent() &&
                !userRepository.findByEmail(email).isPresent()){

            user = Optional.of(userRepository.save(new User(
                    phone, passwordEncoder.encode(password), lastname,
                    firstname,email)));

            token = Optional.of(jwtProvider.createToken(user.get()));
            LOGGER.info("token generated {}", token);
        }
        return token;
    }

    @Override
    public Optional<User> signUpToUser(String phone, String email, String firstName, String lastName, String password) {
        Optional<User> user = null;

        if (!userRepository.findByPhone(phone).isPresent() &&
                !userRepository.findByEmail(email).isPresent()){

            user = Optional.of(userRepository.save(new User(
                    phone, passwordEncoder.encode(password), lastName,
                    firstName,email)));
        }
        return user;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    /**
     * Sign in a user into the application, with JWT-enabled authentication
     *
     * @param requiredEntry username
     * @param password      password
     * @return Optional of the Java Web Token, empty otherwise
     */
    @Override
    public Optional<String> signIn(String requiredEntry, String password) {
        Optional<User> user;

        if (isNumeric(requiredEntry)) {
            user = userRepository.findByPhone(requiredEntry);
        } else {
            user = userRepository.findByEmail(requiredEntry);
        }

        Optional<String> token = Optional.empty();

        if (user.isPresent()) {
            try {
                Authentication authentication = authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(requiredEntry, password));
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();

                if(isNumeric(requiredEntry)){
                    user = userRepository.findByPhone(userDetails.getUsername());
                }
                else{
                    user = userRepository.findByEmail(userDetails.getUsername());
                }

                token = Optional.of(jwtProvider.createToken(user.get()));
                LOGGER.info("token generated {}", token);
            } catch (AuthenticationException e) {
                LOGGER.info("Log in failed for user {}", requiredEntry);
            }
        }
        return token;
    }


    @Override
    public Optional<User> signInToUser(String requiredEntry, String password) {
        Optional<User> user;

        if (isNumeric(requiredEntry)) {
            user = userRepository.findByPhone(requiredEntry);
        } else {
            user = userRepository.findByEmail(requiredEntry);
        }

        if (user.isPresent()) {
            try {
                Authentication authentication = authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(requiredEntry, password));
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();

                if(isNumeric(requiredEntry)){
                    user = userRepository.findByPhone(userDetails.getUsername());
                }
                else{
                    user = userRepository.findByEmail(userDetails.getUsername());
                }
            } catch (AuthenticationException e) {
                LOGGER.info("Log in failed for user {}", requiredEntry);
            }
        }
        return user;
    }

    public String formatToken(Optional<String> token){
        /**
         * Remove the redundant "optional" part from the generated token string
         */
        return token.toString().substring("Optional[".length(),token.toString().length()-2);
    }

    @Override
    /**
     * The function will return true if the input is phone number
     */
    public boolean isNumeric (String requiredInput){
        if (requiredInput == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(requiredInput);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
