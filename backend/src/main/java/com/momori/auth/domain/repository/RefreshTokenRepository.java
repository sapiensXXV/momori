package com.momori.auth.domain.repository;

import com.momori.auth.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {

    RefreshToken findByRefreshToken(String refreshToken);

}
