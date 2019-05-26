package com.example.chess;

public enum PieceEnum {
    KING("king"),
    QUEEN("queen"),
    BISHOP("bishop"),
    KNIGHT("knight"),
    ROOK("rook"),
    PAWN("pawn");

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    PieceEnum(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
