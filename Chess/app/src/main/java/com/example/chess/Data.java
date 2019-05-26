package com.example.chess;

import android.util.Log;

import java.util.ArrayList;

public class Data {
    private static ArrayList<Piece> pieces;
    private static ArrayList<ArrayList<Cell>> field = new ArrayList<>();
    private static boolean greenWay;
    private static Piece tempPiece;

    public static void create()
    {
        createPieces();
        createField();
        greenWay = false;
        tempPiece = null;
    }

    private static void createField(){
        for (int i = 0; i < 8; i++) {
            field.add(new ArrayList<Cell>());
            for (int j = 0; j < 8; j++) {
                field.get(i).add(new Cell(i, j));
            }
        }
        for (int i = 0; i < pieces.size(); i++) {
            field.get(pieces.get(i).getColumn()).get(pieces.get(i).getLine()).setPiece(pieces.get(i));
        }
    }

    private static void createPieces(){
        pieces = new ArrayList<>();
        ColorEnum color = ColorEnum.DARK;
        pieces.add(new Piece(PieceEnum.ROOK, color, pieces.size(), 0, 0));
        pieces.add(new Piece(PieceEnum.KNIGHT, color, pieces.size(), 1, 0));
        pieces.add(new Piece(PieceEnum.BISHOP, color, pieces.size(), 2, 0));
        pieces.add(new Piece(PieceEnum.QUEEN, color, pieces.size(), 3, 0));
        pieces.add(new Piece(PieceEnum.KING, color, pieces.size(), 4, 0));
        pieces.add(new Piece(PieceEnum.BISHOP, color, pieces.size(), 5, 0));
        pieces.add(new Piece(PieceEnum.KNIGHT, color, pieces.size(), 6, 0));
        pieces.add(new Piece(PieceEnum.ROOK, color, pieces.size(), 7, 0));

        for (int i = 0; i < 8; i++) {
            pieces.add(new Piece(PieceEnum.PAWN, color, pieces.size(), i, 1));
        }

        color = ColorEnum.LIGHT;

        pieces.add(new Piece(PieceEnum.ROOK, color, pieces.size(), 0, 7));
        pieces.add(new Piece(PieceEnum.KNIGHT, color, pieces.size(), 1, 7));
        pieces.add(new Piece(PieceEnum.BISHOP, color, pieces.size(), 2, 7));
        pieces.add(new Piece(PieceEnum.QUEEN, color, pieces.size(), 3, 7));
        pieces.add(new Piece(PieceEnum.KING, color, pieces.size(), 4, 7));
        pieces.add(new Piece(PieceEnum.BISHOP, color, pieces.size(), 5, 7));
        pieces.add(new Piece(PieceEnum.KNIGHT, color, pieces.size(), 6, 7));
        pieces.add(new Piece(PieceEnum.ROOK, color, pieces.size(), 7, 7));

        for (int i = 0; i < 8; i++) {
            pieces.add(new Piece(PieceEnum.PAWN, color, pieces.size(), i, 6));
        }
    }
    public static void addAll(ArrayList<Piece> arrayList) {
        pieces.addAll(arrayList);
    }

    public static Piece get(int index) {
        return pieces.get(index);
    }

    public static int getSize() {
        return field.size();
    }

    public static ArrayList<Cell> getLine(int i){
        return field.get(i);
    }

    public static Cell getCell(int column, int line){
        return field.get(column).get(line);
    }

    public static int getLineSize(int column){
        return field.get(column).size();
    }

    public static void setGreenWay(int column, int line){
        if(!greenWay && field.get(column).get(line).havePiece()){
            tempPiece = field.get(column).get(line).getPiece();
            setGreenWayPiece(column, line);
        }
        else if (greenWay){
            if (field.get(column).get(line).isPoint()){
                movePiece(tempPiece, column, line);
            }
            removeGreenWay();
        }
        Log.d("data", String.valueOf(greenWay));
    }
    
    private static void setGreenWayPiece(int column, int line){
        switch (field.get(column).get(line).getPiece().getPieceEnum()){
            case PAWN:
                setGreenWayPawn(column,line);
                break;
            case ROOK:
                setGreenWayRook(column, line);
                break;
            case BISHOP:
                setGreenWayBishop(column, line);
                break;
            case KNIGHT:
                setGreenWayKnight(column, line);
                break;
            case KING:
                setGreenWayKing(column, line);
                break;
            case QUEEN:
                setGreenWayQueen(column, line);
                break;
        }
    }
    private static void setGreenWayPawn(int column, int line){
        if(field.get(column).get(line).getPiece().getColor() == ColorEnum.DARK){
            if (setPointPawn(column, line + 1) && line == 1){
                setPointPawn(column, line + 2);
            }
            setPointPawnSide(column - 1, line + 1);
            setPointPawnSide(column + 1, line + 1);
        }
        else if(field.get(column).get(line).getPiece().getColor() == ColorEnum.LIGHT){
            if (setPointPawn(column, line - 1) && line == 6){
                setPointPawn(column, line - 2);
            }
            setPointPawnSide(column - 1, line - 1);
            setPointPawnSide(column + 1, line - 1);
        }
    }

    private static void setGreenWayRook(int column, int line){
        int i = 1;
        while (setPoint(column, line + i)){
            i++;
        }
        i = 1;
        while (setPoint(column, line - i)){
            i++;
        }
        i = 1;
        while (setPoint(column - i, line)){
            i++;
        }
        i = 1;
        while (setPoint(column + i, line)){
            i++;
        }
    }

    private static void setGreenWayBishop(int column, int line){
        int i = 1;
        while (setPoint(column + i, line + i)){
            i++;
        }
        i = 1;
        while (setPoint(column - i, line + i)){
            i++;
        }
        i = 1;
        while (setPoint(column - i, line - i)){
            i++;
        }
        i = 1;
        while (setPoint(column + i, line - i)){
            i++;
        }
    }

    private static void setGreenWayKnight(int column, int line){
        setPoint(column + 1, line + 2);
        setPoint(column - 1, line - 2);
        setPoint(column + 1, line - 2);
        setPoint(column - 1, line + 2);

        setPoint(column + 2, line + 1);
        setPoint(column - 2, line - 1);
        setPoint(column + 2, line - 1);
        setPoint(column - 2, line + 1);
    }

    private static void setGreenWayKing(int column, int line){
        setPoint(column + 1, line + 1);
        setPoint(column - 1, line - 1);
        setPoint(column - 1, line + 1);
        setPoint(column + 1, line - 1);

        setPoint(column, line + 1);
        setPoint(column, line - 1);
        setPoint(column + 1, line);
        setPoint(column - 1, line);
    }

    private static void setGreenWayQueen(int column, int line){
        setGreenWayBishop(column, line);
        setGreenWayRook(column, line);
    }

    private static boolean setPoint(int column, int line){
        if (column >= 0 && line >= 0 && column < 8 && line < 8){
            if (!field.get(column).get(line).havePiece()){
                field.get(column).get(line).setPoint(true);
                greenWay = true;
                return true;
            }
            else if (tempPiece.getColor() != field.get(column).get(line).getPiece().getColor()){
                field.get(column).get(line).setPoint(true);
                greenWay = true;
                return false;
            }
        }
        return false;
    }

    private static boolean setPointPawn(int column, int line){
        if (column >= 0 && line >= 0 && column < 8 && line < 8){
            if (!field.get(column).get(line).havePiece()){
                field.get(column).get(line).setPoint(true);
                greenWay = true;
                return true;
            }
        }
        return false;
    }

    private static boolean setPointPawnSide(int column, int line){
        if (column >= 0 && line >= 0 && column < 8 && line < 8){
            if (field.get(column).get(line).havePiece()){
                field.get(column).get(line).setPoint(true);
                greenWay = true;
                return true;
            }
        }
        return false;
    }

    public static void removeGreenWay(){
        greenWay = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                field.get(i).get(j).setPoint(false);
            }
        }
    }

    public static void movePiece(Piece piece, int column, int line){
        field.get(piece.getColumn()).get(piece.getLine()).deletePiece();
        piece.setColumn(column);
        piece.setLine(line);
        field.get(column).get(line).setPiece(piece);
    }
}
