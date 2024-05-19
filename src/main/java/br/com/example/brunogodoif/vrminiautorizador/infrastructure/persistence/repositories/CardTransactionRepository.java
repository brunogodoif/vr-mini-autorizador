package br.com.example.brunogodoif.vrminiautorizador.infrastructure.persistence.repositories;

import br.com.example.brunogodoif.vrminiautorizador.infrastructure.persistence.entities.CardTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CardTransactionRepository extends JpaRepository<CardTransaction, UUID> {
}
