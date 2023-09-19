package com.example.dawningoftheashes.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import com.example.dawningoftheashes.data.Repository;

public class MonsterViewModel extends AndroidViewModel
{
    public static Repository repository;
    public final LiveData<List<Monster>> allMonsters;

    public MonsterViewModel(@NonNull Application application)
    {
        super(application);
        repository = new Repository(application);
        allMonsters = repository.getAllMonsters();
    }

    public LiveData<List<Monster>> getAllMonsters()
    {
        return allMonsters;
    }

    public static void insertMonster(Monster monster)
    {
        repository.insertMonster(monster);
    }

    public static void updateMonster(Monster monster)
    {
        repository.updateMonster(monster);
    }

    public LiveData<Monster> getMonster(int id)
    {
        return repository.getMonster(id);
    }

    public static void deleteMonster(Monster monster)
    {
        repository.deleteMonster(monster);
    }
}
