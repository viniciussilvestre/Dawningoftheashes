package com.example.dawningoftheashes.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.dawningoftheashes.R;
import com.example.dawningoftheashes.databinding.ActivityConnectNewPlayerBinding;
import com.google.android.material.elevation.SurfaceColors;

public class ConnectNewPlayer extends AppCompatActivity
{
    private ActivityConnectNewPlayerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(SurfaceColors.SURFACE_2.getColor(this));
        getWindow().setNavigationBarColor(SurfaceColors.SURFACE_2.getColor(this));
        binding = DataBindingUtil.setContentView(this, R.layout.activity_connect_new_player);
    }
}