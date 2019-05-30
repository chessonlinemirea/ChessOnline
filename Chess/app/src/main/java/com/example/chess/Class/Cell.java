package com.example.chess.Class;

import android.graphics.Color;

import com.example.chess.Enum.PointColorEnum;

public class Cell {
    private Piece piece;
    private int column;
    private int line;
    private int fieldColor;
    private boolean point;
    private PointColorEnum pointColorEnum;

    public Cell(Piece piece, int column, int line) {
        this.piece = piece;
        this.column = column;
        this.line = line;
        this.point = false;
    }

    @Override
    public String toString() {
        if (piece != null){
            return piece.toString();
        }
        else {
            return "0";
        }
    }

    public boolean isPoint() {
        return point;
    }

    public PointColorEnum getPointColorEnum() {
        return pointColorEnum;
    }

    public void setPoint(PointColorEnum pointColorEnum) {
        this.point = true;
        this.pointColorEnum = pointColorEnum;
    }

    public void deletePoint(){
        this.point = false;
        this.pointColorEnum = null;
    }

    public Cell(int column, int line) {
        this.piece = null;
        this.column = column;
        this.line = line;
        this.point = false;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
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

    public int getFieldColor() {
        if ((getColumn() + getLine()) % 2 != 0){
            return Color.parseColor("#d18b47");
        }
        else{
            return Color.parseColor("#ffce9e");
        }
    }

    public boolean havePiece(){
        return piece != null;
    }

    public void deletePiece(){
        piece = null;
    }
}
