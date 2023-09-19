package model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import data.Repository;

public class TableViewModel extends AndroidViewModel
{
    public static Repository repository;
    public final LiveData<List<Table>> allTables;

    public TableViewModel(@NonNull Application application)
    {
        super(application);
        repository = new Repository(application);
        allTables = repository.getAllTables();
    }

        public LiveData<List<Table>> getAllSheetsDnd()
    {
        return allTables;
    }

    public static void insertTable(Table table)
    {
        repository.insertTable(table);
    }

    public static void updateTable(Table table)
    {
        repository.updateTable(table);
    }

    public LiveData<Table> getTable(int id)
    {
        return repository.getTable(id);
    }

    public static void deleteTable(Table table)
    {
        repository.deleteTable(table);
    }

    public LiveData<List<Table>> searchDatabase(String searchQuery)
    {
        return repository.searchDatabase(searchQuery);
    }
}
