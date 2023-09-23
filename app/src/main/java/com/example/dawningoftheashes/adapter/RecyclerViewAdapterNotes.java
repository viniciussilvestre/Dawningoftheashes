package com.example.dawningoftheashes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dawningoftheashes.R;
import com.example.dawningoftheashes.model.Notes;
import com.example.dawningoftheashes.model.Table;
import com.google.android.material.elevation.SurfaceColors;

import java.util.List;
import java.util.Objects;

public class RecyclerViewAdapterNotes extends RecyclerView.Adapter<RecyclerViewAdapterNotes.ViewHolderNotes>
{
    private OnContactClickListener contactClickListener;
    private List<Notes> notes;
    private Context context;

    public RecyclerViewAdapterNotes(List<Notes> notes, Context context, OnContactClickListener contactClickListener)
    {
        this.contactClickListener = contactClickListener;
        this.notes = notes;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderNotes onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_row_info, parent, false);
        return new ViewHolderNotes(view, contactClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderNotes holder, int position)
    {
        Notes note = Objects.requireNonNull(notes.get(position));
        String noteTitle = note.getTitle();
        String noteCreationDate = note.getCreationDate().toString();
        String noteModificationDate = note.getEditDate().toString();
        holder.cardView.setCardBackgroundColor(SurfaceColors.SURFACE_0.getColor(context));
        holder.noteTitle.setText(noteTitle);
        holder.creationDate.setText(noteCreationDate);
        holder.editDate.setText(noteModificationDate);
    }

    @Override
    public int getItemCount()
    {
        return notes.size();
    }

    public static class ViewHolderNotes extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        OnContactClickListener onContactClickListener;
        public TextView noteTitle;
        public TextView creationDate;
        public TextView editDate;
        public CardView cardView;

        public ViewHolderNotes(@NonNull View itemView, OnContactClickListener onContactClickListener)
        {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.noteTitleText);
            creationDate = itemView.findViewById(R.id.dateCreatedText);
            editDate = itemView.findViewById(R.id.dateModifiedText);
            cardView = itemView.findViewById(R.id.cardViewNotes);
            this.onContactClickListener = onContactClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            onContactClickListener.onContactClick(getAdapterPosition());
        }
    }

    public interface OnContactClickListener
    {
        void onContactClick(int position);
    }
}
