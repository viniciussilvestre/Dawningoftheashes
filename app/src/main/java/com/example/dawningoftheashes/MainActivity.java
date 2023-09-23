package com.example.dawningoftheashes;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.dawningoftheashes.databinding.ActivityMainBinding;
import com.example.dawningoftheashes.model.Notes;
import com.example.dawningoftheashes.model.Player;
import com.example.dawningoftheashes.model.PlayerViewModel;
import com.example.dawningoftheashes.ui.CreateTable;
import com.google.android.material.elevation.SurfaceColors;

import java.util.ArrayList;
import java.util.Objects;

import com.example.dawningoftheashes.adapter.RecyclerViewAdapter;

import com.example.dawningoftheashes.model.Table;
import com.example.dawningoftheashes.model.TableViewModel;
import com.example.dawningoftheashes.util.Util;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnContactClickListener
{
    private ActivityMainBinding binding;
    private TableViewModel tableViewModel;
    private PlayerViewModel playerViewModel;
    private RecyclerViewAdapter recyclerViewAdapter;
    private Player currentLocalPlayer;

    ActivityResultLauncher<Intent> launchOpenTable = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result ->
            {
                if (result.getResultCode() == Activity.RESULT_OK)
                {
                    Intent data = result.getData();
                    assert data != null;
                }
            }
    );

    ActivityResultLauncher<Intent> launchCreateTable = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result ->
            {
                if (result.getResultCode() == Activity.RESULT_OK)
                {
                    Intent data = result.getData();
                    assert data != null;
                    Table table = new Table();
                    table.setGm(data.getParcelableExtra(Util.GM_REPLY));
                    int startingLevel = data.getIntExtra(Util.STARTING_LEVEL, 1);
                    table.setStartingLevel(startingLevel);
                    table.setCurrentLevel(startingLevel);
                    table.setName(data.getStringExtra(Util.NAME_REPLY));
                    table.setGm(currentLocalPlayer);
                    table.setNotes(new ArrayList<>());
                    table.setMonsters(new ArrayList<>());
                    table.setPlayers(new ArrayList<>());
                    table.setSheets(new ArrayList<>());
                    TableViewModel.insertTable(table);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(SurfaceColors.SURFACE_2.getColor(this));
        getWindow().setNavigationBarColor(SurfaceColors.SURFACE_2.getColor(this));
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tableViewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this.getApplication()).create(TableViewModel.class);
        playerViewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this.getApplication()).create(PlayerViewModel.class);
        handleIntent(getIntent());

        tableViewModel.getAllTables().observe(this, tables ->
        {
            recyclerViewAdapter = new RecyclerViewAdapter(tables, MainActivity.this, this);
            binding.recyclerView.setAdapter(recyclerViewAdapter);
        });

        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState)
            {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && recyclerView.canScrollVertically(Integer.MIN_VALUE))
                {
                    binding.addButton.hide();
                }
                else
                {
                    binding.addButton.show();
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy)
            {
                if (dy != 0 && binding.addButton.isExtended())
                {
                    binding.addButton.shrink();
                }
                if (dy < 0 && !binding.addButton.isExtended())
                {
                    binding.addButton.extend();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        binding.addButton.setOnClickListener(view ->
        {
            Intent intent = new Intent(MainActivity.this, CreateTable.class);
            intent.putExtra(Util.PLAYER_ID, currentLocalPlayer.getPlayerId());
            openCreateTable(intent);
        });
    }

    private void handleIntent(Intent intent)
    {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            boolean hasUser = extras.getBoolean(Util.HAS_USER);
            if (!hasUser)
            {
                Player localPlayer = new Player();
                localPlayer.setName(extras.getString(Util.LOCAL_PLAYER_NAME));
                localPlayer.setLocalUser(true);
                PlayerViewModel.insertPlayer(localPlayer);
                currentLocalPlayer = localPlayer;
            }
            else
            {
                currentLocalPlayer = playerViewModel.getLocalPlayer().getValue();
            }
        }
    }

    private void openCreateTable(Intent intent)
    {
        launchCreateTable.launch(intent);
    }

    public void openTable(Intent intent)
    {
        launchOpenTable.launch(intent);
    }

    @Override
    public void onContactClick(int position)
    {
        Table table = Objects.requireNonNull(tableViewModel.getAllTables().getValue()).get(position);
        Intent intent = new Intent(MainActivity.this, ShowTable.class);
        intent.putExtra(Util.TABLE_ID, table.getId());
        openTable(intent);
    }
}