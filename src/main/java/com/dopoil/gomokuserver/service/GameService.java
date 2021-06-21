package com.dopoil.gomokuserver.service;

import com.dopoil.gomokuserver.config.properties.BoardProperties;
import com.dopoil.gomokuserver.domain.*;
import com.dopoil.gomokuserver.exception.GameNotFoundException;
import com.dopoil.gomokuserver.exception.GameNotJoinableException;
import com.dopoil.gomokuserver.exception.InvalidMoveException;
import com.dopoil.gomokuserver.repository.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

import static com.dopoil.gomokuserver.domain.GameStatus.NEW;
import static com.dopoil.gomokuserver.domain.GameStatus.IN_PROGRESS;

/**
 * The Game Service handles the creation and modification of the Game object.
 */
@Service
@AllArgsConstructor
public class GameService {

    private final BoardProperties boardProperties;
    private final PlayerNotificationService playerNotificationService;
    private final GameRepository gameRepository;

    /**
     * Create new Game.
     *
     * @param player the player requesting the new Game
     * @return the game
     */
    public Game createGame(Player player) {
        Game game = new Game();
        game.setBoard(new Board(
                        boardProperties.getHeight(),
                        boardProperties.getWidth(),
                        boardProperties.getWinCondition()
                )
        );
        game.setGameId(UUID.randomUUID());
        player.setId(UUID.randomUUID());
        game.setPlayer1(player);
        game.setGameStatus(NEW);
        game.setActiveToken(1);
        game.setWinner(0);

        gameRepository.save(game);

        return game;

    }

    /**
     * Connect to latest game.
     *
     * @param player2 the 2nd player
     * @return the game
     * @throws GameNotJoinableException
     * @throws IOException
     */
    public Game connectToLatestGame(Player player2) throws GameNotJoinableException, IOException {

        Game currentGame = gameRepository.findByGameStatus(NEW).get(0);

        if (currentGame.getPlayer2() != null) {
            throw new GameNotJoinableException("Game is full, please try another");
        } else {
            player2.setId(UUID.randomUUID());
            currentGame.setPlayer2(player2);
            currentGame.setGameStatus(IN_PROGRESS);
        }

        playerNotificationService.notifyPlayerOfNewGameState(currentGame.getPlayer1(), currentGame);
        gameRepository.save(currentGame);
        return currentGame;
    }

    /**
     * Apply a Move to the current active game.
     *
     * @param move the move
     * @return the game
     * @throws GameNotFoundException
     * @throws IOException
     * @throws InvalidMoveException
     */
    public Game makeMove(Move move) throws GameNotFoundException, IOException, InvalidMoveException {

        if (!gameRepository.existsById(move.gameId)) {
            throw new GameNotFoundException("Game with Id + " + move.gameId.toString() + " cannot be found");
        }

        if (!gameRepository.existsById(move.gameId)) {
            throw new GameNotFoundException("Game with Id + " + move.gameId.toString() + " cannot be found");
        }

        Game currentGame = gameRepository.getById(move.gameId);

        currentGame.getBoard().applyMove(move);
        currentGame.setActiveToken(swapToken(currentGame.getActiveToken()));
        currentGame.getBoard().checkForWinner().ifPresent(currentGame::setWinner);
        gameRepository.save(currentGame);
        playerNotificationService.notifyPlayerOfNewGameState(changePlayer(move.getPlayer(), currentGame), currentGame);

        return currentGame;

    }

    //swap the active token for the game. forces players to wait their turn
    private int swapToken(int i) {
        if (i == 1) {
            return 2;
        } else {
            return 1;
        }
    }

    //returns the opponent of the input player value. used to notify the opponent when the player has made their move
    private Player changePlayer(final Player actualPlayer, final Game currentGame) {
        return currentGame.getPlayer1().equals(actualPlayer) ? currentGame.getPlayer2() : currentGame.getPlayer1();
    }


}
