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
import android.os.Bundle;

import com.example.dawningoftheashes.databinding.ActivityMainBinding;
import com.google.android.material.elevation.SurfaceColors;

import java.util.List;
import java.util.Objects;

import adapter.RecyclerViewAdapter;
import data.Repository;
import model.Table;
import model.TableViewModel;
import util.Util;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnContactClickListener
{
    private ActivityMainBinding binding;
    private TableViewModel tableViewModel;
    private RecyclerViewAdapter recyclerViewAdapter;

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
    }

    public void openCharacterSheet(Intent intent)
    {
        launchOpenTable.launch(intent);
    }

    @Override
    public void onContactClick(int position)
    {
        Table table = Objects.requireNonNull(tableViewModel.getAllTables().getValue()).get(position);
        Intent intent = new Intent(MainActivity.this, Table.class);
        intent.putExtra(Util.CHARACTER_ID, table.getId());
        openCharacterSheet(intent);
    }
}