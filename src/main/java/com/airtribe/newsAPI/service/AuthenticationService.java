package com.airtribe.newsAPI.service;

import com.airtribe.newsAPI.dto.UserDTO;
import com.airtribe.newsAPI.entity.newsUser;
import com.airtribe.newsAPI.entity.verificationToken;
import com.airtribe.newsAPI.repository.UserRepository;
import com.airtribe.newsAPI.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.airtribe.newsAPI.utils.JwtUtil;

import java.util.Collections;
import java.util.Date;

import static com.airtribe.newsAPI.utils.JwtUtil.getUsernameFromJwtToken;
import static com.airtribe.newsAPI.utils.JwtUtil.validateJwtToken;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer;

    public newsUser registerUser(UserDTO user){
        newsUser newUser = new newsUser();
        System.out.println((user.getUsername()));
        System.out.println(user.getPassword());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setPreferences(user.getPreferences());
        return userRepository.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        newsUser user = userRepository.findByUsername(username);
        if (user ==null){
            throw new UsernameNotFoundException("User" + username + " not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.emptyList());
    }

    public void registerVerificationToken(newsUser registeredUser, String generatedToken){
        verificationToken token = new verificationToken();
        token.setToken(generatedToken);
        token.setUser(registeredUser);
        Long expiryDate = (new java.util.Date().getTime() + 1000 * 60 * 60 * 24);
        token.setExpiryDate(new Date(expiryDate));
        verificationTokenRepository.save(token);

    }

    public String loginUser(String username, String password) {
        newsUser registeredUser = userRepository.findByUsername(username);
        if (registeredUser == null) {
            return "User '" + username + "' not registered";
        }

        System.out.println(password + "-" + registeredUser.getPassword());

        boolean arePasswordsMatch = passwordEncoder.matches(password, registeredUser.getPassword());
        if (arePasswordsMatch) {
//            verificationToken tokenObj = new verificationToken();
            String token = JwtUtil.generateToken(username);
//            tokenObj.setToken(JwtUtil.generateToken(username));
//            tokenObj.setUser(registeredUser);
//            verificationTokenRepository.save(tokenObj);
            return token;

        }
        return "user not authenticated";
    }

    public String getUserPreferences(String token) {
//        verificationToken tokenObj = verificationTokenRepository.findByToken(token);
//        newsUser user = tokenObj.getUser();
//        return user.getPreferences();
        boolean validToken = validateJwtToken(token);
        if (validToken) {
            String username = getUsernameFromJwtToken(token);
            newsUser userDetails = userRepository.findByUsername(username);
            return userDetails.getPreferences();
        }else {
            return "user not authenticated";
        }
    }

    public newsUser updateUserPreferences(String token, String preferences) {
//        verificationToken tokenObj = verificationTokenRepository.findByToken(token);
//        newsUser user = tokenObj.getUser();
//        user.setPreferences(preferences);
        boolean validToken = validateJwtToken(token);
        if (validToken) {
            String username = getUsernameFromJwtToken(token);
            newsUser user = userRepository.findByUsername(username);
            user.setPreferences(preferences);
            userRepository.save(user);
            return user;
        }else{
            return null;
        }
    }

//    public String getNewsArticles(String token) {
//        verificationToken tokenObj = verificationTokenRepository.findByToken(token);
//        newsUser user = tokenObj.getUser();
//        String pref = user.getPreferences();
//    }
}
