package br.com.example.brunogodoif.vrminiautorizador.infrastructure.persistence.repositories;

import br.com.example.brunogodoif.vrminiautorizador.infrastructure.persistence.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<Card, UUID> {

    Optional<Card> findByCardNumber(String cardNumber);

}
