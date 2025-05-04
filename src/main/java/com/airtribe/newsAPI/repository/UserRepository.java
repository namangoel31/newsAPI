package com.airtribe.newsAPI.repository;

import com.airtribe.newsAPI.entity.newsUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<newsUser, Long> {
//    List<User> username(String username);

    newsUser findByUsername(String username);
}