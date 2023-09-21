package com.example.dawningoftheashes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.dawningoftheashes.databinding.ActivityTableViewBinding;
import com.google.android.material.elevation.SurfaceColors;

public class TableView extends AppCompatActivity
{
    private ActivityTableViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(SurfaceColors.SURFACE_2.getColor(this));
        getWindow().setNavigationBarColor(SurfaceColors.SURFACE_2.getColor(this));
        binding = DataBindingUtil.setContentView(this, R.layout.activity_table_view);
    }
}