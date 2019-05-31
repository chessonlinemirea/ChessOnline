package com.example.chess.Data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.chess.Adapter.ColumnsAdapter;
import com.example.chess.AsyncTasks.AsyncTaskEndGame;
import com.example.chess.AsyncTasks.AsyncTaskMove;
import com.example.chess.Class.Cell;
import com.example.chess.Enum.ColorEnum;
import com.example.chess.Class.Piece;
import com.example.chess.Enum.PieceEnum;
import com.example.chess.Enum.PointColorEnum;

import java.util.ArrayList;

public class Data {
    private static ArrayList<Piece> playerDark;
    private static ArrayList<Piece> playerLight;
    private static ArrayList<ArrayList<Cell>> field = new ArrayList<>();
    private static boolean greenWay;
    public static boolean canMove;
    private static Piece tempPiece;
    private static Piece tempPieceRedPoint;
    private static ColorEnum colorEnum;
    static Context context;
    private static ColumnsAdapter columnsAdapter;
    private static boolean isShah;
    private static boolean isMat;
    private static boolean outPutRedLine = false;
    private static boolean outPutRedLineShah = true;

    public static boolean isCanMove() {
        return canMove;
    }

    public static void setCanMove(boolean canMove) {
        Data.canMove = canMove;
    }

    public static void create(Context contextt)
    {
        context = contextt;
        createPieces();
        createField();
        greenWay = false;
        tempPiece = null;
        if(colorEnum == ColorEnum.LIGHT){
            canMove = true;
        }
        Log.d("canMove", String.valueOf(canMove));
        isShah = false;
        isMat = false;
    }

    public static void clear(){
        field.clear();
        playerLight.clear();
        playerDark.clear();
    }

    public static void bildRecyclerView(RecyclerView columns) {
        columnsAdapter = new ColumnsAdapter();
        columns.setAdapter(columnsAdapter);
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
        if (colorEnum == ColorEnum.LIGHT){
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
        else {
            playerDark = new ArrayList<>();
            ColorEnum color = ColorEnum.DARK;
            playerDark.add(new Piece(PieceEnum.ROOK, color, playerDark.size(), 7, 7));
            playerDark.add(new Piece(PieceEnum.KNIGHT, color, playerDark.size(), 6, 7));
            playerDark.add(new Piece(PieceEnum.BISHOP, color, playerDark.size(), 5, 7));
            playerDark.add(new Piece(PieceEnum.QUEEN, color, playerDark.size(), 4, 7));
            playerDark.add(new Piece(PieceEnum.KING, color, playerDark.size(), 3, 7));
            playerDark.add(new Piece(PieceEnum.BISHOP, color, playerDark.size(), 2, 7));
            playerDark.add(new Piece(PieceEnum.KNIGHT, color, playerDark.size(), 1, 7));
            playerDark.add(new Piece(PieceEnum.ROOK, color, playerDark.size(), 0, 7));

            for (int i = 0; i < 8; i++) {
                playerDark.add(new Piece(PieceEnum.PAWN, color, playerDark.size(), i, 6));
            }

            playerLight = new ArrayList<>();
            color = ColorEnum.LIGHT;

            playerLight.add(new Piece(PieceEnum.ROOK, color, playerLight.size(), 7, 0));
            playerLight.add(new Piece(PieceEnum.KNIGHT, color, playerLight.size(), 6, 0));
            playerLight.add(new Piece(PieceEnum.BISHOP, color, playerLight.size(), 5, 0));
            playerLight.add(new Piece(PieceEnum.QUEEN, color, playerLight.size(), 4, 0));
            playerLight.add(new Piece(PieceEnum.KING, color, playerLight.size(), 3, 0));
            playerLight.add(new Piece(PieceEnum.BISHOP, color, playerLight.size(), 2, 0));
            playerLight.add(new Piece(PieceEnum.KNIGHT, color, playerLight.size(), 1, 0));
            playerLight.add(new Piece(PieceEnum.ROOK, color, playerLight.size(), 0, 0));

            for (int i = 0; i < 8; i++) {
                playerLight.add(new Piece(PieceEnum.PAWN, color, playerLight.size(), i, 1));
            }
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
        if(canMove){
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
        if(colorEnum == ColorEnum.LIGHT){
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
        else {
            if(field.get(column).get(line).getPiece().getColor() == ColorEnum.DARK){
                if (setPointPawn(column, line - 1) && line == 6){
                    setPointPawn(column, line - 2);
                }
                setPointPawnSide(column - 1, line - 1);
                setPointPawnSide(column + 1, line - 1);
            }
            else if(field.get(column).get(line).getPiece().getColor() == ColorEnum.LIGHT){
                if (setPointPawn(column, line + 1) && line == 1){
                    setPointPawn(column, line + 2);
                }
                setPointPawnSide(column - 1, line + 1);
                setPointPawnSide(column + 1, line + 1);
            }
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
            if (field.get(column).get(line).havePiece() && field.get(column).get(line).getPiece().getColor() != tempPiece.getColor()){
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

    public static int reverse(int line){
        return 7 - line;
    }

    public static void movePiece(Piece piece, int column, int line){
        int startColumn = piece.getColumn();
        int startLine = piece.getLine();
        int endColumn = column;
        int endLine = line;


        field.get(startColumn).get(startLine).deletePiece();
        piece.setColumn(endColumn);
        piece.setLine(endLine);
        field.get(endColumn).get(endLine).setPiece(piece);

        if (field.get(endColumn).get(endLine).havePiece() && field.get(endColumn).get(endLine).getPiece().getPieceEnum() == PieceEnum.KING){
            //Toast.makeText(context, "Вы победили", Toast.LENGTH_LONG).show();
            AsyncTaskEndGame asyncTaskEndGame = new AsyncTaskEndGame(context, true);
            asyncTaskEndGame.execute();
        }

        if (colorEnum == ColorEnum.DARK){
            startColumn = reverse(startColumn);
            startLine = reverse(startLine);
            endColumn = reverse(endColumn);
            endLine = reverse(endLine);
        }
        String move = "";
        move += startColumn + " ";
        move += startLine + " ";
        move += endColumn + " ";
        move += endLine;



        removeGreenWay();
        AsyncTaskMove asyncTaskMove = new AsyncTaskMove(context, move);
        asyncTaskMove.execute();
    }

    public static void moveOpponent(String move){
        String splitMove[] = move.split(" ");
        int startColumn = Integer.valueOf(splitMove[0]);
        int startLine = Integer.valueOf(splitMove[1]);
        int endColumn = Integer.valueOf(splitMove[2]);
        int endLine = Integer.valueOf(splitMove[3]);
        if (colorEnum == ColorEnum.DARK){
            startColumn = reverse(startColumn);
            startLine = reverse(startLine);
            endColumn = reverse(endColumn);
            endLine = reverse(endLine);
        }
        Log.d("Split Move", startColumn + " | " + startLine + " | " + endColumn + " | " + endLine);

        if (field.get(endColumn).get(endLine).havePiece() && field.get(endColumn).get(endLine).getPiece().getPieceEnum() == PieceEnum.KING){
            //Toast.makeText(context, "Вы проиграли", Toast.LENGTH_LONG).show();
            AsyncTaskEndGame asyncTaskEndGame = new AsyncTaskEndGame(context, false);
            asyncTaskEndGame.execute();
        }
        Piece piece = field.get(startColumn).get(startLine).getPiece();

        field.get(startColumn).get(startLine).deletePiece();
        piece.setColumn(endColumn);
        piece.setLine(endLine);
        field.get(endColumn).get(endLine).setPiece(piece);
        columnsAdapter.update();
        //checkShah();

//        if (isShah){
//            Toast.makeText(context, "Вам шах", Toast.LENGTH_LONG);
//        }
        /*else if (isMat){
            Toast.makeText(context, "Вам шах и мат", Toast.LENGTH_LONG);
        }*/
        isShah = false;
        //isMat = false;

        clearDeath();
    }

    public static void clearDeath(){
        for (Piece piece : playerDark){
            piece.setDeath(false);
        }
        for (Piece piece : playerLight){
            piece.setDeath(false);
        }
    }

    private static void checkMat() {
        Piece check = null;
        if(colorEnum == ColorEnum.LIGHT){
            for (Piece piece : playerLight){
                if (piece.getPieceEnum() == PieceEnum.KING){
                    check = piece;
                }
            }
        }
        else if(colorEnum == ColorEnum.DARK){
            for (Piece piece : playerDark){
                if (piece.getPieceEnum() == PieceEnum.KING){
                    check = piece;
                }
            }
        }
        isMat = checkKing(check.getColumn(), check.getLine());
    }

    private static boolean checkKing(int column, int line){
        boolean temp = false;

//        if(!field.get(column + 1).get(line + 1).getPiece().isDeath()){
//            temp = false;
//        }
//        if(!field.get(column - 1).get(line - 1).getPiece().isDeath()){
//            temp = false;
//        }
//        if(!field.get(column - 1).get(line + 1).getPiece().isDeath()){
//            temp = false;
//        }
//        if(!field.get(column + 1).get(line - 1).getPiece().isDeath()){
//            temp = false;
//        }
//
//        if(!field.get(column).get(line + 1).getPiece().isDeath()){
//            temp = false;
//        }
//        if(!field.get(column).get(line - 1).getPiece().isDeath()){
//            temp = false;
//        }
//        if(!field.get(column + 1).get(line).getPiece().isDeath()){
//            temp = false;
//        }
//        if(!field.get(column - 1).get(line).getPiece().isDeath()){
//            temp = false;
//        }

        return temp;
    }

    public static void checkShah(){
//        Log.d("color", field.get(column).get(line).getPiece().getColor().toString());
//        Log.d("color piece", colorEnum.toString());

        if (colorEnum == ColorEnum.LIGHT){
            for (int i = 0; i < playerDark.size(); i++) {
                checkShahPiece(playerDark.get(i).getColumn(), playerDark.get(i).getLine());
            }
        }
        else if (colorEnum == ColorEnum.DARK){
            for (int i = 0; i < playerLight.size(); i++) {
                //try {
                    tempPieceRedPoint = playerLight.get(i);
                    checkShahPiece(playerLight.get(i).getColumn(), playerLight.get(i).getLine());
//                }
//                catch (NullPointerException e){
//                    Log.d("ERRRRRRRRRROR", playerLight.get(i).getColumn() + " " + playerLight.get(i).getLine());
//                }
            }
        }
    }

    private static void checkShahPiece(int column, int line){
        Log.d("check", colorEnum.toString());
        switch (field.get(column).get(line).getPiece().getPieceEnum()){
            case PAWN:
                //Log.d("check", "Pawn");
                checkShahPawn(column, line);
                break;
            case ROOK:
                //Log.d("check", "Rook");
                checkShahRook(column, line);
                break;
            case BISHOP:
                //Log.d("check", "Bishop");
                checkShahBishop(column, line);
                break;
            case KNIGHT:
                //Log.d("check", "Knight");
                checkShahKnight(column, line);
                break;
            case KING:
                //Log.d("check", "King");
                checkShahKing(column, line);
                break;
            case QUEEN:
                //Log.d("check", "Queen");
                checkShahQueen(column, line);
                break;
        }
    }

    private static void checkShahPawn(int column, int line){
        if(colorEnum == ColorEnum.LIGHT){
            if(field.get(column).get(line).getPiece().getColor() == ColorEnum.DARK){
                checkShahPawnSide(column - 1, line + 1);
                checkShahPawnSide(column + 1, line + 1);
            }
            else if(field.get(column).get(line).getPiece().getColor() == ColorEnum.LIGHT){
                checkShahPawnSide(column - 1, line - 1);
                checkShahPawnSide(column + 1, line - 1);
            }
        }
        else {
            if(field.get(column).get(line).getPiece().getColor() == ColorEnum.DARK){
                checkShahPawnSide(column - 1, line - 1);
                checkShahPawnSide(column + 1, line - 1);
            }
            else if(field.get(column).get(line).getPiece().getColor() == ColorEnum.LIGHT){
                checkShahPawnSide(column - 1, line + 1);
                checkShahPawnSide(column + 1, line + 1);
            }
        }
    }

    private static void checkShahRook(int column, int line){
        int i = 1;
        while (checkShahCell(column, line + i)){
            i++;
        }
        i = 1;
        while (checkShahCell(column, line - i)){
            i++;
        }
        i = 1;
        while (checkShahCell(column - i, line)){
            i++;
        }
        i = 1;
        while (checkShahCell(column + i, line)){
            i++;
        }
    }

    private static void checkShahBishop(int column, int line){
        int i = 1;
        while (checkShahCell(column + i, line + i)){
            i++;
        }
        i = 1;
        while (checkShahCell(column - i, line + i)){
            i++;
        }
        i = 1;
        while (checkShahCell(column - i, line - i)){
            i++;
        }
        i = 1;
        while (checkShahCell(column + i, line - i)){
            i++;
        }
    }

    private static void checkShahKnight(int column, int line){
        checkShahCell(column + 1, line + 2);
        checkShahCell(column - 1, line - 2);
        checkShahCell(column + 1, line - 2);
        checkShahCell(column - 1, line + 2);

        checkShahCell(column + 2, line + 1);
        checkShahCell(column - 2, line - 1);
        checkShahCell(column + 2, line - 1);
        checkShahCell(column - 2, line + 1);
    }

    private static void checkShahKing(int column, int line){
        checkShahCell(column + 1, line + 1);
        checkShahCell(column - 1, line - 1);
        checkShahCell(column - 1, line + 1);
        checkShahCell(column + 1, line - 1);

        checkShahCell(column, line + 1);
        checkShahCell(column, line - 1);
        checkShahCell(column + 1, line);
        checkShahCell(column - 1, line);
    }

    private static void checkShahQueen(int column, int line){
        checkShahBishop(column, line);
        checkShahRook(column, line);
    }

    private static boolean checkShahCell(int column, int line){
        if (column >= 0 && line >= 0 && column < 8 && line < 8){
            if (outPutRedLine) {
                field.get(column).get(line).setPoint(PointColorEnum.RED);
            }
            if (!field.get(column).get(line).havePiece()){
                return true;
            }
            else if (colorEnum == field.get(column).get(line).getPiece().getColor()){
                //Log.d(field.get(column).get(line).getPiece().getColor().toString(), column + " " + line);
                if (outPutRedLineShah){
                    field.get(column).get(line).setPoint(PointColorEnum.RED);
                    Log.d("Set red point shah", column + " " + line);
                    Log.d("Set red point shah", tempPieceRedPoint.getColumn() + " " + tempPieceRedPoint.getLine() + " " + tempPieceRedPoint.toString() + " " + column + " " + line);
                }
                field.get(column).get(line).getPiece().setDeath(true);
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

    private static boolean checkShahPawnSide(int column, int line){
        if (column >= 0 && line >= 0 && column < 8 && line < 8){
            if (outPutRedLine) {
                field.get(column).get(line).setPoint(PointColorEnum.RED);
            }

            if (field.get(column).get(line).havePiece() && colorEnum == field.get(column).get(line).getPiece().getColor()){
                if (outPutRedLineShah){
                    Log.d("Set red point shah", column + " " + line);
                    Log.d("Set red point shah", tempPieceRedPoint.getColumn() + " " + tempPieceRedPoint.getLine() + " " + tempPieceRedPoint.toString() + " " + column + " " + line);
                    field.get(column).get(line).setPoint(PointColorEnum.RED);
                }
                field.get(column).get(line).getPiece().setDeath(true);
                if(field.get(column).get(line).getPiece().getPieceEnum() == PieceEnum.KING)
                {
                    isShah = true;
                    Toast.makeText(context, "Шах", Toast.LENGTH_LONG).show();
                }
                return true;
            }
        }
        return false;
    }

    public static void setColorEnum(ColorEnum colorEnum) {
        Data.colorEnum = colorEnum;
    }

}
