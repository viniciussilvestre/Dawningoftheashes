package com.example.dawningoftheashes.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.example.dawningoftheashes.R;
import com.example.dawningoftheashes.databinding.ActivityCreateCampaignNotesBinding;
import com.example.dawningoftheashes.util.Util;
import com.google.android.material.elevation.SurfaceColors;

import java.util.Date;

public class CreateCampaignNotes extends AppCompatActivity
{
    private ActivityCreateCampaignNotesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(SurfaceColors.SURFACE_2.getColor(this));
        getWindow().setNavigationBarColor(SurfaceColors.SURFACE_2.getColor(this));
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_campaign_notes);

        binding.saveNotesButton.setOnClickListener(view ->
        {
            if (!binding.noteTitleText.getText().toString().isEmpty())
            {
                Intent replyIntent = new Intent();
                String noteTitle = binding.noteTitleText.getText().toString();
                String noteContent = binding.campaignNotesText.getText().toString();
                Date creationDate = new Date();
                replyIntent.putExtra(Util.NOTE_TITLE, noteTitle);
                replyIntent.putExtra(Util.NOTE_CONTENT, noteContent);
                replyIntent.putExtra(Util.CREATION_DATE, creationDate.getTime());
                replyIntent.putExtra(Util.MODIFIED_DATE, creationDate.getTime());
                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });
    }
}