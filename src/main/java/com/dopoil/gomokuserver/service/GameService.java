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

@Service
@AllArgsConstructor
public class GameService {

    private final BoardProperties boardProperties;
    private final PlayerNotificationService playerNotificationService;
    private final GameRepository gameRepository;

    public Game createGame(Player player){
      Game game = new Game();
      game.setBoard(new Board(
              boardProperties.getHeight(),
              boardProperties.getWidth(),
              boardProperties.getWinCondition()
              )
      );
      game.setGameId(UUID.randomUUID());
      game.setPlayer1(player);
      game.setGameStatus(NEW);
      game.setWinner(0);

        gameRepository.save(game);

      return game;

    }

    public Game connectById(Player player2, UUID gameId) throws GameNotJoinableException {

        if(!gameRepository.existsById(gameId)){
            throw new GameNotJoinableException("Game with Id + " + gameId.toString() + " does nto exist");
        }

        Game currentGame = gameRepository.getById(gameId);

        if(currentGame.getPlayer2() != null ){
            throw new GameNotJoinableException("Game is full, please try another");
        }
        else {
            currentGame.setPlayer2(player2);
            currentGame.setGameStatus(IN_PROGRESS);
        }

        gameRepository.save(currentGame);
        return currentGame;
    }

    public Game connectToLatestGame(Player player2) throws GameNotJoinableException {
        
       Game currentGame= gameRepository.findByGameStatus(NEW).get(0);

        if(currentGame.getPlayer2() != null ){
            throw new GameNotJoinableException("Game is full, please try another");
        }
        else {
            currentGame.setPlayer2(player2);
            currentGame.setGameStatus(IN_PROGRESS);
        }

        gameRepository.save(currentGame);
        return currentGame;
    }

    public Game makeMove(Move move) throws GameNotFoundException, IOException, InvalidMoveException {

        if(!gameRepository.existsById(move.gameId)){
            throw new GameNotFoundException("Game with Id + " + move.gameId.toString() + " cannot be found");
        }

        if(!gameRepository.existsById(move.gameId)){
            throw new GameNotFoundException("Game with Id + " + move.gameId.toString() + " cannot be found");
        }

        Game currentGame = gameRepository.getById(move.gameId);

        if(!isMoveLegal(currentGame.getBoard(), move)){
            throw new InvalidMoveException("That is not a valid move, please try again");
        }
        currentGame.getBoard().applyMove(move);

        currentGame.getBoard().checkForWinner().ifPresent(currentGame::setWinner);
        gameRepository.save(currentGame);
//        playerNotificationService.notifyPlayerOfMoveMade(changePlayer(move.getPlayer(), currentGame), currentGame);

        return currentGame;

    }

    public boolean isMoveLegal(Board board, Move move){

        return board.getBoard()[move.getColumnIndex()][0]!=0 ? false : true;

    }

    private Map<Integer, Player> createPlayersType(final Player firstPlayer, final Player secondPlayer) {
        final Map<Integer, Player> playersWithId = new HashMap<>();
        playersWithId.put(1, firstPlayer);
        playersWithId.put(2, secondPlayer);
        return playersWithId;
    }
    private Player changePlayer(final Player actualPlayer, final Game currentGame) {
        return currentGame.getPlayer1().equals(actualPlayer) ? currentGame.getPlayer2() : currentGame.getPlayer1();
    }

    private boolean boardIsNotFullAndThereIsNoWinner(final Board board) {
        return !board.getWinner().isPresent() && !board.isFull();
    }

}
