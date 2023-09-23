package com.example.dawningoftheashes.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.dawningoftheashes.MainActivity;
import com.example.dawningoftheashes.R;
import com.example.dawningoftheashes.databinding.ActivityCreateTableBinding;
import com.example.dawningoftheashes.model.Player;
import com.example.dawningoftheashes.model.PlayerViewModel;
import com.example.dawningoftheashes.util.Util;
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

        binding.saveButton.setOnClickListener(view ->
        {
            Intent replyIntent = new Intent();
            if (!TextUtils.isEmpty(binding.tableNameField.getText()) && !TextUtils.isEmpty(binding.tableLevelField.getText()))
            {
                String name = binding.tableNameField.getText().toString();
                String stringLevel = binding.tableLevelField.getText().toString();
                int level = Integer.parseInt(stringLevel);
                replyIntent.putExtra(Util.NAME_REPLY, name);
                replyIntent.putExtra(Util.STARTING_LEVEL, level);
                setResult(RESULT_OK, replyIntent);
            }
            else
            {
                setResult(RESULT_CANCELED, replyIntent);
            }
            finish();
        });
    }
}