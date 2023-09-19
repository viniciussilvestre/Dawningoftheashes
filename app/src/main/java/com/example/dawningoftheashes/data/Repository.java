package com.example.dawningoftheashes.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.dawningoftheashes.model.Monster;
import com.example.dawningoftheashes.model.Player;
import com.example.dawningoftheashes.util.SheetRoomDatabase;

import java.util.List;

import com.example.dawningoftheashes.model.Sheet;
import com.example.dawningoftheashes.model.Table;

public class Repository
{
    private TableDao tableDao;
    private final LiveData<List<Table>> allTables;
    private final LiveData<List<Sheet>> allSheets;
    private final LiveData<List<Monster>> allMonsters;
    private final LiveData<List<Player>> allPlayers;

    public Repository(Application application)
    {
        SheetRoomDatabase db = SheetRoomDatabase.getDatabase(application);
        tableDao = db.tableDao();
        allTables = tableDao.getAllTables();
        allSheets = tableDao.getAllSheets();
        allMonsters = tableDao.getAllMonsters();
        allPlayers = tableDao.getAllPlayers();
    }

    public LiveData<List<Table>> getAllTables()
    {
        return allTables;
    }

    public LiveData<List<Sheet>> getAllSheets()
    {
        return allSheets;
    }

    public LiveData<List<Monster>> getAllMonsters()
    {
        return allMonsters;
    }

    public LiveData<List<Player>> getAllPLayers()
    {
        return allPlayers;
    }

    public LiveData<Table> getTable(int id)
    {
        return tableDao.getTable(id);
    }

    public LiveData<Sheet> getSheet(int id)
    {
        return tableDao.getSheet(id);
    }

    public LiveData<Monster> getMonster(int id)
    {
        return tableDao.getMonster(id);
    }

    public LiveData<Player> getPlayer(int id)
    {
        return tableDao.getPlayer(id);
    }

    public void insertTable(Table table)
    {
        SheetRoomDatabase.databaseWriteExecutor.execute(() ->
        {
            tableDao.insert(table);
        });
    }

    public void insertSheet(Sheet sheet)
    {
        SheetRoomDatabase.databaseWriteExecutor.execute(() ->
        {
            tableDao.insert(sheet);
        });
    }

    public void insertMonster(Monster monster)
    {
        SheetRoomDatabase.databaseWriteExecutor.execute(() ->
        {
            tableDao.insert(monster);
        });
    }

    public void insertPlayer(Player player)
    {
        SheetRoomDatabase.databaseWriteExecutor.execute(() ->
        {
            tableDao.insert(player);
        });
    }

    public void updateTable(Table table)
    {
        SheetRoomDatabase.databaseWriteExecutor.execute(() ->
        {
            tableDao.update(table);
        });
    }

    public void updateSheet(Sheet sheet)
    {
        SheetRoomDatabase.databaseWriteExecutor.execute(() ->
        {
            tableDao.updateSheet(sheet);
        });
    }

    public void updateMonster(Monster monster)
    {
        SheetRoomDatabase.databaseWriteExecutor.execute(() ->
        {
            tableDao.updateMonster(monster);
        });
    }

    public void updatePlayer(Player player)
    {
        SheetRoomDatabase.databaseWriteExecutor.execute(() ->
        {
            tableDao.updatePlayer(player);
        });
    }

    public void deleteTable(Table table)
    {
        SheetRoomDatabase.databaseWriteExecutor.execute(() ->
        {
            tableDao.delete(table);
        });
    }

    public void deleteSheet(Sheet sheet)
    {
        SheetRoomDatabase.databaseWriteExecutor.execute(() ->
        {
            tableDao.delete(sheet);
        });
    }

    public void deleteMonster(Monster monster)
    {
        SheetRoomDatabase.databaseWriteExecutor.execute(() ->
        {
            tableDao.delete(monster);
        });
    }

    public void deletePlayer(Player player)
    {
        SheetRoomDatabase.databaseWriteExecutor.execute(() ->
        {
            tableDao.delete(player);
        });
    }

    public LiveData<List<Table>> searchDatabase(String searchQuery)
    {
        return tableDao.searchDatabase(searchQuery);
    }
}
