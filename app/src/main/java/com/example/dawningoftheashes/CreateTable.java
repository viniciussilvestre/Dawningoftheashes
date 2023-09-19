package com.example.dawningoftheashes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.dawningoftheashes.databinding.ActivityCreateTableBinding;
import com.google.android.material.elevation.SurfaceColors;

public class CreateTable extends AppCompatActivity
{
    private ActivityCreateTableBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(SurfaceColors.SURFACE_2.getColor(this));
        getWindow().setNavigationBarColor(SurfaceColors.SURFACE_2.getColor(this));
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_table);
    }
}