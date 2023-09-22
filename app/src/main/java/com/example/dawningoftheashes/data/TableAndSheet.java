package com.example.dawningoftheashes.data;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.dawningoftheashes.model.Sheet;
import com.example.dawningoftheashes.model.Table;

import java.util.List;

public class TableAndSheet
{
    @Embedded public Table table;
    @Relation(
            parentColumn = "id",
            entityColumn = "tableId"
    )
    public List<Sheet> sheets;
}
