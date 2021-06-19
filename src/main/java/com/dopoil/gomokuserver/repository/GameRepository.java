package com.dopoil.gomokuserver.repository;

import com.dopoil.gomokuserver.domain.Game;
import com.dopoil.gomokuserver.domain.GameStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GameRepository extends JpaRepository<Game, UUID> {

    public List<Game> findByGameStatus(GameStatus status);

}
