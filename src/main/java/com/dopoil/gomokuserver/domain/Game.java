package com.dopoil.gomokuserver.domain;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class Game {

    @Id
    private UUID gameId;

    @OneToOne(cascade = {CascadeType.ALL})
    private Player player1;

    @OneToOne(cascade = {CascadeType.ALL})
    private Player player2;

    private GameStatus gameStatus;

    @NotNull
    private Integer winner;

    @OneToOne(cascade = {CascadeType.ALL})
    private Board board;

}
