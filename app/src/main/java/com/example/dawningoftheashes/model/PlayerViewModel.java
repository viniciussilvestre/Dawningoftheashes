package com.example.dawningoftheashes.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dawningoftheashes.data.Repository;

import java.util.List;

public class PlayerViewModel extends AndroidViewModel
{
    public static Repository repository;
    public final LiveData<List<Player>> allPlayers;

    public PlayerViewModel(@NonNull Application application)
    {
        super(application);
        repository = new Repository(application);
        allPlayers = repository.getAllPlayers();
    }

    public LiveData<List<Player>> getAllPlayers()
    {
        return allPlayers;
    }

    public LiveData<Player> getLocalPlayer()
    {
        return repository.getLocalPlayer();
    }

    public static void insertPlayer(Player player)
    {
        repository.insertPlayer(player);
    }

    public static void updatePlayer(Player player)
    {
        repository.updatePlayer(player);
    }

    public LiveData<Player> getPlayer(int id)
    {
        return repository.getPlayer(id);
    }

    public static void deletePlayer(Player player)
    {
        repository.deletePlayer(player);
    }
}
