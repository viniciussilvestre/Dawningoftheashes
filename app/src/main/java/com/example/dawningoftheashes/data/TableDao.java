package com.example.dawningoftheashes.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.dawningoftheashes.model.Monster;
import com.example.dawningoftheashes.model.Player;
import com.example.dawningoftheashes.model.Sheet;

import java.util.List;

import com.example.dawningoftheashes.model.Table;

@Dao
public interface TableDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Table table);
    @Delete
    void delete(Table table);
    @Query("SELECT * FROM rpg_tables ORDER BY name ASC")
    LiveData<List<Table>> getAllTables();
    @Query("SELECT * FROM rpg_tables WHERE rpg_tables.id == :id")
    LiveData<Table> getTable(int id);
    @Update
    void update(Table table);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Monster monster);
    @Delete()
    void delete(Monster monster);
    @Query("SELECT * FROM monster_table ORDER BY monsterName ASC")
    LiveData<List<Monster>> getAllMonsters();
    @Query("SELECT * FROM monster_table WHERE monster_table.monsterId == :id")
    LiveData<Monster> getMonster(int id);
    @Update
    void updateMonster(Monster monster);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Sheet sheet);
    @Delete()
    void delete(Sheet sheet);
    @Query("SELECT * FROM sheets_table ORDER BY sheetName ASC")
    LiveData<List<Sheet>> getAllSheets();
    @Query("SELECT * FROM sheets_table WHERE sheets_table.sheetId == :id")
    LiveData<Sheet> getSheet(int id);
    @Update
    void updateSheet(Sheet sheet);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Player player);
    @Delete()
    void delete(Player player);
    @Query("SELECT * FROM player_tables ORDER BY name ASC")
    LiveData<List<Player>> getAllPlayers();
    @Query("SELECT * FROM player_tables WHERE player_tables.playerId == :id")
    LiveData<Player> getPlayer(int id);
    @Update
    void updatePlayer(Player player);

    @Query("SELECT * FROM rpg_tables WHERE name LIKE :searchQuery")
    LiveData<List<Table>> searchDatabase(String searchQuery);

    @Transaction
    @Query("SELECT * FROM rpg_tables")
    LiveData<List<TableAndSheet>> getTableAndSheets();

    @Transaction
    @Query("SELECT * FROM player_tables")
    LiveData<List<PlayerAndSheet>> getPlayerAndSheets();
}
