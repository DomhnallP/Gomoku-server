package com.dopoil.gomokuserver.service;

import com.dopoil.gomokuserver.domain.Game;
import com.dopoil.gomokuserver.domain.Player;

import java.io.IOException;

public interface PlayerNotificationService {

    void notifyPlayerOfNewGameState(Player player, Game newGameState) throws IOException;
}
