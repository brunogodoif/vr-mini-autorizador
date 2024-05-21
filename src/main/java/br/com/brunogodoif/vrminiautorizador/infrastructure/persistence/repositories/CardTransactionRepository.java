package br.com.brunogodoif.vrminiautorizador.infrastructure.persistence.repositories;

import br.com.brunogodoif.vrminiautorizador.infrastructure.persistence.entities.CardTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CardTransactionRepository extends JpaRepository<CardTransactionEntity, UUID> {
}
