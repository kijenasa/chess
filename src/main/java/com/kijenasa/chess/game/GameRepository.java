package com.kijenasa.chess.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    @Query("SELECT g FROM Game g WHERE g.id = ?1")
    Optional<Game> findGameById(long id);

    @Query("SELECT g FROM Game g WHERE g.uuid = ?1")
    Optional<Game> findGameByUuid(UUID uuid);
}
