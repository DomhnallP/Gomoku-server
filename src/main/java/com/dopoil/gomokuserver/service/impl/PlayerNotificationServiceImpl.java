package com.dopoil.gomokuserver.service.impl;

import com.dopoil.gomokuserver.domain.Game;
import com.dopoil.gomokuserver.domain.Player;
import com.dopoil.gomokuserver.service.PlayerNotificationService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * The type Player notification service. syncs the board between players by sending the updates game state to both players whenever the game state changes.
 */
@Component
@AllArgsConstructor
public class PlayerNotificationServiceImpl implements PlayerNotificationService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void notifyPlayerOfNewGameState(Player player, Game newGameState) throws IOException {

        CloseableHttpClient client = HttpClients.createDefault();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        byte[] bytes = objectMapper.writeValueAsBytes(newGameState);
        HttpEntity entity = EntityBuilder.create().setBinary(bytes).build();
        HttpPost httpPost = new HttpPost(player.getNetworkId().toString() + "/sync");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = client.execute(httpPost);
        client.close();

    }

}
