package com.to.frownies.repository;

import com.to.frownies.entity.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TranRepo extends CrudRepository<Transaction, UUID> {
}
