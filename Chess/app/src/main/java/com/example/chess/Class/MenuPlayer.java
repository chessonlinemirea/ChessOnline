package com.example.chess.Class;

public class MenuPlayer
{
    private String name;
    private boolean boolStatus;

    public MenuPlayer(String name, boolean boolStatus)
    {
        this.name = name;
        this.boolStatus = boolStatus;
    }

    public MenuPlayer(String name)
    {
        this.name = name;
//        strStatus = "not play";
    }

    public String getName()
    {
        return name;
    }


    public boolean isBoolStatus()
    {
        return boolStatus;
    }

    public void setBoolStatus(boolean boolStatus)
    {
        this.boolStatus = boolStatus;
    }
}