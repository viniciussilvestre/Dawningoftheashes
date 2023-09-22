package com.example.dawningoftheashes.data;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.dawningoftheashes.model.Player;
import com.example.dawningoftheashes.model.Sheet;

import java.util.List;

public class PlayerAndSheet
{
    @Embedded public Player player;
    @Relation(
            parentColumn = "playerId",
            entityColumn = "playerId"
    )
    public List<Sheet> sheets;
}
