package com.example.dawningoftheashes.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.example.dawningoftheashes.R;
import com.example.dawningoftheashes.databinding.ActivityCreateCampaignNotesBinding;
import com.example.dawningoftheashes.model.Player;
import com.example.dawningoftheashes.model.PlayerViewModel;
import com.example.dawningoftheashes.util.Util;
import com.google.android.material.elevation.SurfaceColors;

import java.util.Date;

public class CreateCampaignNotes extends AppCompatActivity
{
    private ActivityCreateCampaignNotesBinding binding;
    private int noteId;
    private String noteTitle;
    private String noteContent;
    private Date creationDate;
    private boolean existingNote = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(SurfaceColors.SURFACE_2.getColor(this));
        getWindow().setNavigationBarColor(SurfaceColors.SURFACE_2.getColor(this));
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_campaign_notes);
        binding.getRoot().setBackgroundColor(SurfaceColors.SURFACE_2.getColor(this));
        binding.noteContentCard.setCardBackgroundColor(SurfaceColors.SURFACE_0.getColor(this));
        binding.noteTitleCard.setCardBackgroundColor(SurfaceColors.SURFACE_0.getColor(this));
        handleIntent(getIntent());

        if (existingNote)
        {
            binding.noteTitleText.setText(noteTitle);
            binding.campaignNotesText.setText(noteContent);
        }

        binding.saveNotesButton.setOnClickListener(view ->
        {
            if (!binding.noteTitleText.getText().toString().isEmpty())
            {
                Intent replyIntent = new Intent();
                String noteTitle = binding.noteTitleText.getText().toString();
                String noteContent = binding.campaignNotesText.getText().toString();
                replyIntent.putExtra(Util.NOTE_EXIST, existingNote);
                if (existingNote)
                {
                    replyIntent.putExtra(Util.NOTES_ID, noteId);
                    Date modificationDate = new Date();
                    replyIntent.putExtra(Util.CREATION_DATE, this.creationDate.getTime());
                    replyIntent.putExtra(Util.MODIFIED_DATE, modificationDate.getTime());
                }
                else {
                    Date creationDate = new Date();
                    replyIntent.putExtra(Util.CREATION_DATE, creationDate.getTime());
                    replyIntent.putExtra(Util.MODIFIED_DATE, creationDate.getTime());
                }
                replyIntent.putExtra(Util.NOTE_TITLE, noteTitle);
                replyIntent.putExtra(Util.NOTE_CONTENT, noteContent);
                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });
    }

    private void handleIntent(Intent intent)
    {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            noteId = extras.getInt(Util.NOTES_ID);
            existingNote = true;
            noteTitle = extras.getString(Util.NOTE_TITLE);
            noteContent = extras.getString(Util.NOTE_CONTENT);
            Date creationDate = new Date();
            creationDate.setTime(extras.getLong(Util.CREATION_DATE, -1));
            this.creationDate = creationDate;
        }
    }
}