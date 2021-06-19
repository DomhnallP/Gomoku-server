package com.dopoil.gomokuserver.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Move {

    public UUID gameId;
    private int type;
    private Integer rowIndex;
    private Integer columnIndex;
    private Player player;
}
