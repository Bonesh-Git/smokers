package com.to.frownies.repository;

import com.to.frownies.entity.User;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo extends CrudRepository<User, UUID> {
    boolean existsUserByUsername(@NonNull String username);
    Optional<User> findUserByUsername(@NonNull String username);
    Optional<User> findUserById(@NonNull UUID id);
}
