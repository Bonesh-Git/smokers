package com.to.frownies.repository;

import com.to.frownies.entity.Wallet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WalletRepo extends CrudRepository<Wallet, UUID> {
}
