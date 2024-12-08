package com.kijenasa.chess.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    @Query("SELECT s FROM Game s WHERE s.id = ?1")
    Optional<Game> findGameById(long id);
}
