package com.example.dawningoftheashes.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "rpg_tables")
public class Table
{
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private ArrayList<Player> players;
    private ArrayList<Sheet> sheets;
    private String notes;
    private ArrayList<Monster> monsters;

    public Table()
    {

    }

    public Table(int id, String name, ArrayList<Player> players, ArrayList<Sheet> sheets, String notes, ArrayList<Monster> monsters)
    {
        this.id = id;
        this.name = name;
        this.players = players;
        this.sheets = sheets;
        this.notes = notes;
        this.monsters = monsters;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ArrayList<Player> getPlayers()
    {
        return players;
    }

    public void setPlayers(ArrayList<Player> players)
    {
        this.players = players;
    }

    public ArrayList<Sheet> getSheets()
    {
        return sheets;
    }

    public void setSheets(ArrayList<Sheet> sheets)
    {
        this.sheets = sheets;
    }

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    public ArrayList<Monster> getMonsters()
    {
        return monsters;
    }

    public void setMonsters(ArrayList<Monster> monsters)
    {
        this.monsters = monsters;
    }
}
