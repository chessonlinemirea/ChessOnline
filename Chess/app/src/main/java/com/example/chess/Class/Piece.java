package com.example.chess.Class;

import com.example.chess.Enum.ColorEnum;
import com.example.chess.Enum.PieceEnum;

public class Piece {
    private PieceEnum pieceEnum;
    private int column;
    private int line;
    private ColorEnum color;
    private int id;
    private boolean isAlive;

    public boolean isAlive() {
        return isAlive;
    }

    public void dead() {
        isAlive = false;
    }

    public Piece(PieceEnum pieceEnum, ColorEnum color, int id, int column, int line) {
        this.pieceEnum = pieceEnum;
        this.column = column;
        this.line = line;
        this.color = color;
        this.id = id;
        isAlive = true;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    @Override
    public String toString() {
        return pieceEnum.toString() + color.toString();
    }

    public PieceEnum getPieceEnum() {
        return pieceEnum;
    }

    public void setPieceEnum(PieceEnum pieceEnum) {
        this.pieceEnum = pieceEnum;
    }

    public ColorEnum getColor() {
        return color;
    }

    public void setColor(ColorEnum color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
