package com.dopoil.gomokuserver.service;

import com.dopoil.gomokuserver.domain.Game;
import com.dopoil.gomokuserver.domain.Move;
import com.dopoil.gomokuserver.domain.Player;
import com.dopoil.gomokuserver.exception.GameNotFoundException;
import com.dopoil.gomokuserver.exception.GameNotJoinableException;
import com.dopoil.gomokuserver.exception.InvalidMoveException;

import java.io.IOException;

public interface GameService {

    Game createGame(Player player);

    Game connectToLatestGame(Player player) throws GameNotJoinableException, IOException;

    Game makeMove(Move move) throws GameNotFoundException, IOException, InvalidMoveException;

}
