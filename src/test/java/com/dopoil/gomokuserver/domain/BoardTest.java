package com.dopoil.gomokuserver.domain;

import com.dopoil.gomokuserver.hibernate.custom.BoardObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static java.util.Arrays.fill;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    Board board;
    int height = 6;
    int width = 9;
    int winCondition = 5;

    @BeforeEach
    void setUp() {
        this.board = new Board(height, width, winCondition);

    }

    @Test
    void getWidth() {
        assertEquals(width, board.getWidth());
    }

    @Test
    void setWidth() {
        int newWidth = 10;
        this.board.setWidth(newWidth);
        assertEquals(newWidth, this.board.getWidth());
    }

    @Test
    void getHeight() {
        assertEquals(height, this.board.getHeight());
    }

    @Test
    void setHeight() {
        int newHeight = 10;
        this.board.setHeight(newHeight);
        assertEquals(newHeight, this.board.getHeight());
    }

    @Test
    void applyMove() {
        Move move = new Move();
        move.setColumnIndex(1);
        move.setType(1);
        move.setGameId(UUID.randomUUID());
        board.applyMove(move);

        assertEquals( board.getBoard()[5][1], 1);
    }
}