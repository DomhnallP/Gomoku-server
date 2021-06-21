package com.dopoil.gomokuserver.controller;


import com.dopoil.gomokuserver.domain.Game;
import com.dopoil.gomokuserver.domain.GameStatus;
import com.dopoil.gomokuserver.domain.Move;
import com.dopoil.gomokuserver.domain.Player;
import com.dopoil.gomokuserver.exception.GameNotFoundException;
import com.dopoil.gomokuserver.exception.GameNotJoinableException;
import com.dopoil.gomokuserver.exception.InvalidMoveException;
import com.dopoil.gomokuserver.repository.GameRepository;
import com.dopoil.gomokuserver.service.impl.GameServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * The type Game controller.
 */
@RestController
@AllArgsConstructor
@RequestMapping
public class GameController {

    private final GameServiceImpl gameService;

    private final GameRepository gameRepository;

    /**
     * Create New Game.
     *
     * @param player the player creating the game
     * @return the response entity containing the Game Object
     * @throws GameNotJoinableException the game not joinable exception
     * @throws IOException              the io exception
     */
    @PostMapping("/game")
    @ResponseBody
    public ResponseEntity<Game> createGame (@RequestBody Player player) throws GameNotJoinableException, IOException {

        if(gameRepository.findByGameStatus(GameStatus.NEW).size()>0) {
            player.setToken(2);
            return ResponseEntity.ok(gameService.connectToLatestGame(player));
        }
        player.setToken(1);
        return ResponseEntity.ok(gameService.createGame(player));

    }

    /**
     * Apply a Move to an open game by ID.
     *
     * @param move the move to be applied to the game
     * @return the response entity
     * @throws GameNotFoundException the game not found exception
     * @throws IOException           the io exception
     * @throws InvalidMoveException  the invalid move exception
     */
    @PostMapping("/move")
    @ResponseBody
    public ResponseEntity<Game> move(@RequestBody Move move) throws GameNotFoundException, IOException, InvalidMoveException {
        return ResponseEntity.ok(gameService.makeMove(move));
    }

}
