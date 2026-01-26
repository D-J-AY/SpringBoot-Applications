package com.JPA.hospitalManagement.repository;

import com.JPA.hospitalManagement.entity.User;
import com.JPA.hospitalManagement.entity.type.AuthProviderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.*;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUsername(String username);

    Optional<User> findByProviderIdAndProviderType(String providerId, AuthProviderType providerType);
}