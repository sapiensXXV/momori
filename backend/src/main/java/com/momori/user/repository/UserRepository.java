package com.momori.user.repository;

import com.momori.user.domain.ProviderInfo;
import com.momori.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.identifier = :userIdentifier and u.provider = :providerInfo")
    Optional<User> findByOAuthInfo(
        @Param("userIdentifier") String userIdentifier,
        @Param("providerInfo") ProviderInfo providerInfo
    );

    Optional<User> findByIdentifier(String identifier);
}
