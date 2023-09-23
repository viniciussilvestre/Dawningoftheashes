package com.example.dawningoftheashes.model;

import androidx.lifecycle.ViewModel;

public class IdViewModel extends ViewModel
{
    private int selectedId;

    public void setId(int id)
    {
        selectedId = id;
    }

    public int getSelectedItem()
    {
        return selectedId;
    }
}
