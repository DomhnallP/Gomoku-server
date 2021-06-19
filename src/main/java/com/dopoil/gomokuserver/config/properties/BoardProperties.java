package com.dopoil.gomokuserver.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("board")
@Component
public class BoardProperties {

    private int width;

    private int height;

    private int winCondition;

    public BoardProperties() {

    }

    public BoardProperties(final int width, final int height, final int winCondition) {
        this.width = width;
        this.height = height;
        this.winCondition = winCondition;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(final int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(final int height) {
        this.height = height;
    }

    public int getWinCondition() {
        return winCondition;
    }

    public void setWinCondition(final int winCondition) {
        this.winCondition = winCondition;
    }

}