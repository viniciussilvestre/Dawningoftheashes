package com.example.dawningoftheashes.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dawningoftheashes.R;
import com.example.dawningoftheashes.adapter.RecyclerViewAdapterNotes;
import com.example.dawningoftheashes.databinding.FragmentCampaignNotesBinding;
import com.example.dawningoftheashes.model.IdViewModel;
import com.example.dawningoftheashes.model.Notes;
import com.example.dawningoftheashes.model.Table;
import com.example.dawningoftheashes.model.TableViewModel;
import com.example.dawningoftheashes.util.Util;

import java.util.ArrayList;
import java.util.Date;

public class CampaignNotes extends Fragment implements RecyclerViewAdapterNotes.OnContactClickListener
{
    private FragmentCampaignNotesBinding binding;
    private TableViewModel tableViewModel;
    private RecyclerViewAdapterNotes recyclerViewAdapterNotes;
    private int id;
    private Table currentTable;
    private ArrayList<Notes> notes;

    ActivityResultLauncher<Intent> launchCreateNote = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result ->
            {
                if (result.getResultCode() == Activity.RESULT_OK)
                {
                    Intent data = result.getData();
                    assert data != null;
                    boolean noteExist = data.getBooleanExtra(Util.NOTE_EXIST, false);
                    if (noteExist)
                    {
                        int noteId = data.getIntExtra(Util.NOTES_ID, 0);
                        Date creationDate = new Date();
                        creationDate.setTime(data.getLongExtra(Util.CREATION_DATE, -1));
                        Date modifiedDate = new Date();
                        modifiedDate.setTime(data.getLongExtra(Util.MODIFIED_DATE, -1));
                        Notes newNote = new Notes(data.getStringExtra(Util.NOTE_TITLE), data.getStringExtra(Util.NOTE_CONTENT), creationDate, modifiedDate);
                        newNote.setNoteId(noteId);
                        addNote(newNote, noteExist);
                        updateTable();
                    }
                    else
                    {
                        Date creationDate = new Date();
                        creationDate.setTime(data.getLongExtra(Util.CREATION_DATE, -1));
                        Date modifiedDate = new Date();
                        modifiedDate.setTime(data.getLongExtra(Util.MODIFIED_DATE, -1));
                        Notes newNote = new Notes(data.getStringExtra(Util.NOTE_TITLE), data.getStringExtra(Util.NOTE_CONTENT), creationDate, modifiedDate);
                        addNote(newNote, noteExist);
                        updateTable();
                    }
                }
            }
    );

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        tableViewModel = new ViewModelProvider.AndroidViewModelFactory(CampaignNotes.this.requireActivity().getApplication()).create(TableViewModel.class);
        IdViewModel idViewModel = new ViewModelProvider(requireActivity()).get(IdViewModel.class);
        id = idViewModel.getSelectedItem();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentCampaignNotesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(CampaignNotes.this.requireActivity()));
        tableViewModel.getTable(id).observe(getViewLifecycleOwner(), table ->
        {
            if (table != null)
            {
                currentTable = table;
                notes = table.getNotes();
                recyclerViewAdapterNotes = new RecyclerViewAdapterNotes(notes, CampaignNotes.this.requireActivity(), this);
                binding.recyclerView.setAdapter(recyclerViewAdapterNotes);
            }
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
            Intent intent = new Intent(getActivity(), CreateCampaignNotes.class);
            openCreateNote(intent);
        });

        return root;
    }

    public void addNote(Notes note, boolean noteExist)
    {
        if (noteExist)
        {
            for (int i = 0; i < notes.size(); i++)
            {
                if (notes.get(i).getNoteId() == note.getNoteId())
                {
                    notes.set(i, note);
                }
            }
        }
        else
        {
            notes.add(note);
        }
        currentTable.setNotes(notes);
    }

    public void updateTable()
    {
        Table table = getNewTableData();
        TableViewModel.updateTable(table);
    }

    public void openCreateNote(Intent intent)
    {
        launchCreateNote.launch(intent);
    }

    @Override
    public void onContactClick(int position)
    {
        Notes note = currentTable.getNotes().get(position);
        Intent intent = new Intent(getActivity(), CreateCampaignNotes.class);
        intent.putExtra(Util.NOTES_ID, note.getNoteId());
        intent.putExtra(Util.NOTE_TITLE, note.getTitle());
        intent.putExtra(Util.NOTE_CONTENT, note.getContent());
        intent.putExtra(Util.CREATION_DATE, note.getCreationDate().getTime());
        openCreateNote(intent);
    }

    public Table getNewTableData()
    {
        return new Table(currentTable);
    }
}