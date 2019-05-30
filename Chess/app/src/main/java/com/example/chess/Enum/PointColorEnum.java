package com.example.chess.Enum;

public enum PointColorEnum {
    RED("red"),
    GREEN("green");

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    PointColorEnum(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
