package com.example.dawningoftheashes.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import com.example.dawningoftheashes.data.Repository;

public class SheetViewModel extends AndroidViewModel
{
    public static Repository repository;
    public final LiveData<List<Sheet>> allSheets;

    public SheetViewModel(@NonNull Application application)
    {
        super(application);
        repository = new Repository(application);
        allSheets = repository.getAllSheets();
    }

    public LiveData<List<Sheet>> getAllSheets()
    {
        return allSheets;
    }

    public static void insertSheet(Sheet sheet)
    {
        repository.insertSheet(sheet);
    }

    public static void updateSheet(Sheet sheet)
    {
        repository.updateSheet(sheet);
    }

    public LiveData<Sheet> getSheet(int id)
    {
        return repository.getSheet(id);
    }

    public static void deleteSheet(Sheet sheet)
    {
        repository.deleteSheet(sheet);
    }
}
