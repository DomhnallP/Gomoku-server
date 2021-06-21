package com.dopoil.gomokuserver.domain;

import com.dopoil.gomokuserver.hibernate.custom.BoardObject;
import com.dopoil.gomokuserver.hibernate.custom.serializers.BoardSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vladmihalcea.hibernate.type.array.IntArrayType;
import lombok.Data;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.Arrays.asList;
import static java.util.Arrays.stream;

import java.util.UUID;

import static java.util.Arrays.fill;

/**
 * The type Board.
 */
@Data
@Entity
@TypeDef(
        name = "board_array",
        typeClass = IntArrayType.class
)
@JsonSerialize(using = BoardSerializer.class)
public class Board {

    @Id
    @GeneratedValue
    private UUID id;
    private int width;
    private int height;
    @Lob
    private BoardObject board;
    private int winCondition;

    /**
     * Instantiates a new Board.
     */
    public Board() {
    }

    /**
     * Instantiates a new Board.
     *
     * @param height       the height of the board
     * @param width        the width of the board
     * @param winCondition the number of consecutive tokens required to win
     */
    public Board(final int height, final int width, final int winCondition) {
        this.height = height;
        this.width = width;
        this.winCondition = winCondition;
        this.board = new BoardObject(new Integer[height][width]);
        for (final Integer[] row : board.getData()) {
            fill(row, 0);
        }
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets width.
     *
     * @param width the width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets height.
     *
     * @param height the height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Get board integer [ ] [ ].
     *
     * @return the integer [ ] [ ]
     */
    public Integer[][] getBoard() {
        return board.getData();
    }

    /**
     * Sets board.
     *
     * @param board the board Integer Array to be added to the board Object
     */
    public void setBoard(Integer[][] board) {
        this.board.setData(board);
    }

    /**
     * Apply move.
     *
     * @param move the move being played on the board
     */
    public void applyMove(Move move) {

        for (int i = this.height - 1; i >= 0; i--) {
            if (this.board.getData()[i][move.getColumnIndex()] == 0) {
                this.board.getData()[i][move.getColumnIndex()] = move.getType();
                break;
            }
        }


    }

    /**
     * Check for winner optional.
     *
     * @return the optional
     */
    public Optional<Integer> checkForWinner() {
        Optional<Integer> winner = empty();
        for (final int token : asList(1, 2)) {
            if (hasWin(token)) {
                winner = of(token);
                break;
            }
        }
        return winner;
    }

    private boolean hasWin(int token){
        for (int r = height-1 ; r != 1 ; r--) {
            for (int c = 0 ; c != width ; c++) {
                return checkForWinInAnyDirection(r, c, 0, 1, winCondition, token)
                        || checkForWinInAnyDirection(r, c, 1, 0, winCondition, token)
                        || checkForWinInAnyDirection(r, c, 1, 1, winCondition, token)
                        || checkForWinInAnyDirection(r, c, 1, -1, winCondition, token);
            }
        }
        return false;
    }
    /**
     * Algorithm: Checks for win in any direction where dr & dc are the increments in the row and column axes respectively
     *
     * dr = 1 and dc = 0 == check for vertical win
     * dr = 0 and dc = 1 == check for horizontal win
     * dr = 1 and dc = 1 == check for ascending diagonals
     * dr = 1 and dc = -1 == check for descending diagonals
     *
    **/
    private boolean checkForWinInAnyDirection(int r0, int c0, int dr, int dc, int len, int num) {
        for (int k = 0 ; k != len ; k++) {
            int r = r0 - k*dr;
            int c = c0 + k*dc;
            if (r < 0 || c < 0 || r >= width || c > height || board.getData()[r][c] != num) {
                return false;
            }
        }
        return true;
    }

}
