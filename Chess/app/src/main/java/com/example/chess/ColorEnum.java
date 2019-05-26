package com.example.chess;

public enum ColorEnum {
    DARK("dark"),
    LIGHT("light");

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    ColorEnum(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
