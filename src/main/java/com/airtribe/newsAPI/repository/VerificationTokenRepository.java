package com.airtribe.newsAPI.repository;

import com.airtribe.newsAPI.entity.newsUser;
import com.airtribe.newsAPI.entity.verificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<verificationToken, Long> {

    verificationToken findByToken(String token);

//    newsUser getUserByToken(String token);
}
