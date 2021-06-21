package com.dopoil.gomokuserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Game {

    @Id
    private UUID gameId;

    @OneToOne(cascade = {CascadeType.ALL})
    private Player player1;

    @OneToOne(cascade = {CascadeType.ALL})
    private Player player2;

    private int activeToken;

    private GameStatus gameStatus;

    @NotNull
    private Integer winner;

    @OneToOne(cascade = {CascadeType.ALL})
    private Board board;



}
