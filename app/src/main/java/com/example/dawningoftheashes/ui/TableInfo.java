package com.example.dawningoftheashes.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dawningoftheashes.R;
import com.example.dawningoftheashes.adapter.RecyclerViewAdapterNotes;
import com.example.dawningoftheashes.databinding.FragmentTableInfoBinding;
import com.example.dawningoftheashes.model.IdViewModel;
import com.example.dawningoftheashes.model.Table;
import com.example.dawningoftheashes.model.TableViewModel;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class TableInfo extends Fragment
{
    private FragmentTableInfoBinding binding;
    private TableViewModel tableViewModel;
    private int id;
    private Table currentTable;

    ActivityResultLauncher<PickVisualMediaRequest> pickMedia = registerForActivityResult(
            new ActivityResultContracts.PickVisualMedia(), uri ->
            {
                if (uri != null)
                {
                    binding.tableProfileImage.setImageURI(uri);
                }
                else
                {
                    Log.d("PhotoPicker", "No media selected");
                }

            });

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        tableViewModel = new ViewModelProvider.AndroidViewModelFactory(TableInfo.this.requireActivity().getApplication()).create(TableViewModel.class);
        IdViewModel idViewModel = new ViewModelProvider(requireActivity()).get(IdViewModel.class);
        id = idViewModel.getSelectedItem();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentTableInfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        tableViewModel.getTable(id).observe(getViewLifecycleOwner(), table ->
        {
            if (table != null)
            {
                currentTable = table;
                binding.tableNameTextField.setText(currentTable.getName());
                binding.levelTextView.setText(String.valueOf(currentTable.getCurrentLevel()));
            }
        });

        binding.tableProfileImage.setOnClickListener(view ->
        {
            pickMedia.launch(new PickVisualMediaRequest.Builder().setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE).build());
        });

        return root;
    }
}