package com.bonesh.wallet.model.repository;

import com.bonesh.wallet.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    Boolean existsByUsername(String username);
    Optional<User> findUserByUsername(String username);
}

