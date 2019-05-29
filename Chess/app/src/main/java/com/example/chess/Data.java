package com.example.chess;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class Data {
    private static ArrayList<Piece> playerDark;
    private static ArrayList<Piece> playerLight;
    private static ArrayList<ArrayList<Cell>> field = new ArrayList<>();
    private static boolean greenWay;
    private static Piece tempPiece;
    private static ColorEnum colorEnum;
    static Context context;

    public static void create(Context contextt)
    {
        context = contextt;
        createPieces();
        createField();
        greenWay = false;
        tempPiece = null;
        colorEnum = ColorEnum.DARK;
    }

    private static void createField(){
        for (int i = 0; i < 8; i++) {
            field.add(new ArrayList<Cell>());
            for (int j = 0; j < 8; j++) {
                field.get(i).add(new Cell(i, j));
            }
        }
        for (int i = 0; i < playerDark.size(); i++) {
            field.get(playerDark.get(i).getColumn()).get(playerDark.get(i).getLine()).setPiece(playerDark.get(i));
            field.get(playerLight.get(i).getColumn()).get(playerLight.get(i).getLine()).setPiece(playerLight.get(i));
        }
    }

    private static void createPieces(){
        playerDark = new ArrayList<>();
        ColorEnum color = ColorEnum.DARK;
        playerDark.add(new Piece(PieceEnum.ROOK, color, playerDark.size(), 0, 0));
        playerDark.add(new Piece(PieceEnum.KNIGHT, color, playerDark.size(), 1, 0));
        playerDark.add(new Piece(PieceEnum.BISHOP, color, playerDark.size(), 2, 0));
        playerDark.add(new Piece(PieceEnum.QUEEN, color, playerDark.size(), 3, 0));
        playerDark.add(new Piece(PieceEnum.KING, color, playerDark.size(), 4, 0));
        playerDark.add(new Piece(PieceEnum.BISHOP, color, playerDark.size(), 5, 0));
        playerDark.add(new Piece(PieceEnum.KNIGHT, color, playerDark.size(), 6, 0));
        playerDark.add(new Piece(PieceEnum.ROOK, color, playerDark.size(), 7, 0));

        for (int i = 0; i < 8; i++) {
            playerDark.add(new Piece(PieceEnum.PAWN, color, playerDark.size(), i, 1));
        }

        playerLight = new ArrayList<>();
        color = ColorEnum.LIGHT;

        playerLight.add(new Piece(PieceEnum.ROOK, color, playerLight.size(), 0, 7));
        playerLight.add(new Piece(PieceEnum.KNIGHT, color, playerLight.size(), 1, 7));
        playerLight.add(new Piece(PieceEnum.BISHOP, color, playerLight.size(), 2, 7));
        playerLight.add(new Piece(PieceEnum.QUEEN, color, playerLight.size(), 3, 7));
        playerLight.add(new Piece(PieceEnum.KING, color, playerLight.size(), 4, 7));
        playerLight.add(new Piece(PieceEnum.BISHOP, color, playerLight.size(), 5, 7));
        playerLight.add(new Piece(PieceEnum.KNIGHT, color, playerLight.size(), 6, 7));
        playerLight.add(new Piece(PieceEnum.ROOK, color, playerLight.size(), 7, 7));

        for (int i = 0; i < 8; i++) {
            playerLight.add(new Piece(PieceEnum.PAWN, color, playerLight.size(), i, 6));
        }
    }
    public static void addAll(ArrayList<Piece> arrayListLidht, ArrayList<Piece> arrayListDark) {
        playerDark.addAll(arrayListDark);
        playerLight.addAll(arrayListLidht);
    }

    public static Piece getDark(int index) {
        return playerDark.get(index);
    }

    public static Piece getLight(int index) {
        return playerLight.get(index);
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
//        Log.d("color", field.get(column).get(line).getPiece().getColor().toString());
//        Log.d("color piece", colorEnum.toString());
        if(!greenWay && field.get(column).get(line).havePiece() && field.get(column).get(line).getPiece().getColor() == colorEnum){
            tempPiece = field.get(column).get(line).getPiece();
            removeGreenWay();
            setGreenWayPiece(column, line);
        }
        else if (greenWay){
            if (field.get(column).get(line).isPoint()){
                movePiece(tempPiece, column, line);
            }
            else {
                removeGreenWay();
            }
        }
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
                field.get(column).get(line).setPoint(PointColorEnum.GREEN);
                greenWay = true;
                return true;
            }
            else if (tempPiece.getColor() != field.get(column).get(line).getPiece().getColor()){
                field.get(column).get(line).setPoint(PointColorEnum.RED);
                greenWay = true;
                return false;
            }
        }
        return false;
    }

    private static boolean setPointPawn(int column, int line){
        if (column >= 0 && line >= 0 && column < 8 && line < 8){
            if (!field.get(column).get(line).havePiece()){
                field.get(column).get(line).setPoint(PointColorEnum.GREEN);
                greenWay = true;
                return true;
            }
        }
        return false;
    }

    private static boolean setPointPawnSide(int column, int line){
        if (column >= 0 && line >= 0 && column < 8 && line < 8){
            if (field.get(column).get(line).havePiece()){
                field.get(column).get(line).setPoint(PointColorEnum.RED);
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
                field.get(i).get(j).deletePoint();
            }
        }
    }

    public static void movePiece(Piece piece, int column, int line){
        field.get(piece.getColumn()).get(piece.getLine()).deletePiece();
        piece.setColumn(column);
        piece.setLine(line);
        field.get(column).get(line).setPiece(piece);

        if (colorEnum == ColorEnum.DARK){
            colorEnum = ColorEnum.LIGHT;
        }
        else if (colorEnum == ColorEnum.LIGHT){
            colorEnum = ColorEnum.DARK;
        }

        removeGreenWay();
        checkGreenWay();
    }

    public static void checkGreenWay(){
//        Log.d("color", field.get(column).get(line).getPiece().getColor().toString());
//        Log.d("color piece", colorEnum.toString());

        if (colorEnum == ColorEnum.LIGHT){
            for (int i = 0; i < playerDark.size(); i++) {
                checkGreenWayPiece(playerDark.get(i).getColumn(), playerDark.get(i).getLine());
            }
        }
        else if (colorEnum == ColorEnum.DARK){
            for (int i = 0; i < playerLight.size(); i++) {
                checkGreenWayPiece(playerLight.get(i).getColumn(), playerLight.get(i).getLine());
            }
        }
    }

    private static void checkGreenWayPiece(int column, int line){
        Log.d("check", colorEnum.toString());
        switch (field.get(column).get(line).getPiece().getPieceEnum()){
            case PAWN:
                //Log.d("check", "Pawn");
                checkGreenWayPawn(column,line);
                break;
            case ROOK:
                //Log.d("check", "Rook");
                checkGreenWayRook(column, line);
                break;
            case BISHOP:
                //Log.d("check", "Bishop");
                checkGreenWayBishop(column, line);
                break;
            case KNIGHT:
                //Log.d("check", "Knight");
                checkGreenWayKnight(column, line);
                break;
            case KING:
                //Log.d("check", "King");
                checkGreenWayKing(column, line);
                break;
            case QUEEN:
                //Log.d("check", "Queen");
                checkGreenWayQueen(column, line);
                break;
        }
    }

    private static void checkGreenWayPawn(int column, int line){
        if(field.get(column).get(line).getPiece().getColor() == ColorEnum.DARK){
            checkPointPawnSide(column - 1, line + 1);
            checkPointPawnSide(column + 1, line + 1);
        }
        else if(field.get(column).get(line).getPiece().getColor() == ColorEnum.LIGHT){
            checkPointPawnSide(column - 1, line - 1);
            checkPointPawnSide(column + 1, line - 1);
        }
    }

    private static void checkGreenWayRook(int column, int line){
        int i = 1;
        while (checkPoint(column, line + i)){
            i++;
        }
        i = 1;
        while (checkPoint(column, line - i)){
            i++;
        }
        i = 1;
        while (checkPoint(column - i, line)){
            i++;
        }
        i = 1;
        while (checkPoint(column + i, line)){
            i++;
        }
    }

    private static void checkGreenWayBishop(int column, int line){
        int i = 1;
        while (checkPoint(column + i, line + i)){
            i++;
        }
        i = 1;
        while (checkPoint(column - i, line + i)){
            i++;
        }
        i = 1;
        while (checkPoint(column - i, line - i)){
            i++;
        }
        i = 1;
        while (checkPoint(column + i, line - i)){
            i++;
        }
    }

    private static void checkGreenWayKnight(int column, int line){
        checkPoint(column + 1, line + 2);
        checkPoint(column - 1, line - 2);
        checkPoint(column + 1, line - 2);
        checkPoint(column - 1, line + 2);

        checkPoint(column + 2, line + 1);
        checkPoint(column - 2, line - 1);
        checkPoint(column + 2, line - 1);
        checkPoint(column - 2, line + 1);
    }

    private static void checkGreenWayKing(int column, int line){
        checkPoint(column + 1, line + 1);
        checkPoint(column - 1, line - 1);
        checkPoint(column - 1, line + 1);
        checkPoint(column + 1, line - 1);

        checkPoint(column, line + 1);
        checkPoint(column, line - 1);
        checkPoint(column + 1, line);
        checkPoint(column - 1, line);
    }

    private static void checkGreenWayQueen(int column, int line){
        checkGreenWayBishop(column, line);
        checkGreenWayRook(column, line);
    }

    private static boolean checkPoint(int column, int line){
        if (column >= 0 && line >= 0 && column < 8 && line < 8){
            if (!field.get(column).get(line).havePiece()){
                return true;
            }
            else if (colorEnum == field.get(column).get(line).getPiece().getColor()){
                //Log.d(field.get(column).get(line).getPiece().getColor().toString(), column + " " + line);
                //field.get(column).get(line).setPoint(PointColorEnum.RED);
                if(field.get(column).get(line).getPiece().getPieceEnum() == PieceEnum.KING)
                {
                    Toast.makeText(context, "Шах", Toast.LENGTH_LONG).show();
                }
                return false;
            }
        }

//        Log.d(String.valueOf(column), String.valueOf(line));
//        if (column >= 0 && line >= 0 && column < 8 && line < 8){
//            if (field.get(column).get(line).havePiece() && colorEnum != field.get(column).get(line).getPiece().getColor()){
//                Log.d(colorEnum.toString() + " " + field.get(column).get(line).getPiece().getColor().toString(), column + " " + line);
//                field.get(column).get(line).setPoint(PointColorEnum.RED);
//                return false;
//            }
//        }
        return false;
    }

    private static boolean checkPointPawnSide(int column, int line){
        if (column >= 0 && line >= 0 && column < 8 && line < 8){
            if (field.get(column).get(line).havePiece()){
                field.get(column).get(line).setPoint(PointColorEnum.RED);
                return true;
            }
        }
        return false;
    }

    public void checkShah(){

    }
}
