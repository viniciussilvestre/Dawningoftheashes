package data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import model.Table;
import util.SheetRoomDatabase;

public class Repository
{
    private TableDao tableDao;
    private final LiveData<List<Table>> allTables;

    public Repository(Application application)
    {
        SheetRoomDatabase db = SheetRoomDatabase.getDatabase(application);
        tableDao = db.tableDao();
        allTables = tableDao.getAllTables();
    }

    public LiveData<List<Table>> getAllTables()
    {
        return allTables;
    }

    public LiveData<Table> getTable(int id)
    {
        return tableDao.getTable(id);
    }

    public void insertTable(Table table)
    {
        SheetRoomDatabase.databaseWriteExecutor.execute(() ->
        {
            tableDao.insert(table);
        });
    }

    public void updateTable(Table table)
    {
        SheetRoomDatabase.databaseWriteExecutor.execute(() ->
        {
            tableDao.update(table);
        });
    }

    public void deleteTable(Table table)
    {
        SheetRoomDatabase.databaseWriteExecutor.execute(() ->
        {
            tableDao.delete(table);
        });
    }

    public LiveData<List<Table>> searchDatabase(String searchQuery)
    {
        return tableDao.searchDatabase(searchQuery);
    }
}
