package com.example.chess.Player;

import java.util.ArrayList;

public class Player
{
    private String name;    //Имя игрока

    private int id;
    boolean isInvite;

    public Player(String name)
    {
        this.name = name;
        this.isInvite = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isInvite() {
        return isInvite;
    }

    public void setInvite(boolean invite) {
        isInvite = invite;
    }

    public Player(String name, int id)
    {
        this.id = id;
        this.name = name;
        this.isInvite = false;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}