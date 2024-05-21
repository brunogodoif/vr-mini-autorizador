package br.com.brunogodoif.vrminiautorizador.infrastructure.persistence.repositories;

import br.com.brunogodoif.vrminiautorizador.infrastructure.persistence.entities.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, UUID> {

    Optional<CardEntity> findByCardNumber(String cardNumber);
    boolean existsByCardNumber(String cardNumber);

}
