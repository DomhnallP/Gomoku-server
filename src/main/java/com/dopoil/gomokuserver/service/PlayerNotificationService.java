package com.dopoil.gomokuserver.service;

import com.dopoil.gomokuserver.domain.Game;
import com.dopoil.gomokuserver.domain.Player;
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

@Component
@AllArgsConstructor
public class PlayerNotificationService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void notifyPlayerOfMoveMade(Player player, Game newGameState) throws IOException {

        CloseableHttpClient client = HttpClients.createDefault();
        byte[] bytes = objectMapper.writeValueAsBytes(newGameState);
        HttpEntity entity = EntityBuilder.create().setBinary(bytes).build();
        HttpPost httpPost = new HttpPost(player.getNetworkId().toString());
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = client.execute(httpPost);
        client.close();


    }
}
