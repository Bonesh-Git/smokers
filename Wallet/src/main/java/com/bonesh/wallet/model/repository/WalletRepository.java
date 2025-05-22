package com.bonesh.wallet.model.repository;

import com.bonesh.wallet.model.entity.Wallet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WalletRepository extends CrudRepository<Wallet, UUID> {
}
