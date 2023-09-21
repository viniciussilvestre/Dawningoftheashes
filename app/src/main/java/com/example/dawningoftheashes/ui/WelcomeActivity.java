package com.example.dawningoftheashes.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.example.dawningoftheashes.MainActivity;
import com.example.dawningoftheashes.R;
import com.example.dawningoftheashes.databinding.ActivityWelcomeBinding;
import com.example.dawningoftheashes.model.Player;
import com.example.dawningoftheashes.model.PlayerViewModel;
import com.example.dawningoftheashes.util.Util;
import com.google.android.material.elevation.SurfaceColors;
import com.google.android.material.snackbar.Snackbar;

public class WelcomeActivity extends AppCompatActivity
{
    private ActivityWelcomeBinding binding;
    private PlayerViewModel playerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(SurfaceColors.SURFACE_2.getColor(this));
        getWindow().setNavigationBarColor(SurfaceColors.SURFACE_2.getColor(this));
        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome);
        playerViewModel = new ViewModelProvider.AndroidViewModelFactory(WelcomeActivity.this.getApplication()).create(PlayerViewModel.class);
        ;

        playerViewModel.getLocalPlayer().observe(this, localPlayer ->
        {
            if (localPlayer != null)
            {
                binding.loginButton.setVisibility(View.GONE);
                binding.playerNameTextField.setVisibility(View.GONE);
                binding.startButton.setVisibility(View.VISIBLE);
                binding.nameLabel.setVisibility(View.VISIBLE);
                binding.nameLabel.setText(localPlayer.getName());

                binding.startButton.setOnClickListener(view ->
                {
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    intent.putExtra(Util.HAS_USER, Util.HAS_USER_TRUE);
                    startActivity(intent);
                });
            }
            else
            {
                binding.loginButton.setVisibility(View.VISIBLE);
                binding.playerNameTextField.setVisibility(View.VISIBLE);
                binding.startButton.setVisibility(View.GONE);
                binding.nameLabel.setVisibility(View.GONE);

                binding.loginButton.setOnClickListener(view ->
                {
                    if (!TextUtils.isEmpty(binding.playerNameTextField.getText()))
                    {
                        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                        String name = binding.playerNameTextField.getText().toString();
                        intent.putExtra(Util.HAS_USER, Util.HAS_USER_FALSE);
                        intent.putExtra(Util.LOCAL_PLAYER_NAME, name);
                        startActivity(intent);
                    }
                    else
                    {
                        Snackbar.make(binding.loginButton, R.string.notAPlayerYet, Snackbar.LENGTH_SHORT).show();
                    }
                    finish();
                });
            }
        });
    }
}