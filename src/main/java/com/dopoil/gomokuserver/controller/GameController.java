package com.dopoil.gomokuserver.controller;


import com.dopoil.gomokuserver.domain.Game;
import com.dopoil.gomokuserver.domain.GameStatus;
import com.dopoil.gomokuserver.domain.Move;
import com.dopoil.gomokuserver.domain.Player;
import com.dopoil.gomokuserver.exception.GameNotFoundException;
import com.dopoil.gomokuserver.exception.GameNotJoinableException;
import com.dopoil.gomokuserver.exception.InvalidMoveException;
import com.dopoil.gomokuserver.repository.GameRepository;
import com.dopoil.gomokuserver.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.PublicKey;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping
public class GameController {

    private final GameService gameService;

    private final GameRepository gameRepository;

    @PostMapping("/game")
    @ResponseBody
    public ResponseEntity<Game> createGame (@RequestBody Player player) throws GameNotJoinableException {

        if(gameRepository.findByGameStatus(GameStatus.NEW).size()>0) {
            return ResponseEntity.ok(gameService.connectToLatestGame(player));
        }

        return ResponseEntity.ok(gameService.createGame(player));

    }

    @PostMapping("/move")
    @ResponseBody
    public Game move(@RequestBody Move move) throws GameNotFoundException, IOException, InvalidMoveException {

        gameService.makeMove(move);

        return gameRepository.getById(move.getGameId());
    }

}
