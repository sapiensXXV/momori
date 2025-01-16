package com.poolygo.user.repository;

import com.poolygo.user.domain.ProviderInfo;
import com.poolygo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByOAuthInfo(String userIdentifier, ProviderInfo providerInfo);

    Optional<User> findByIdentifier(String identifier);
}
