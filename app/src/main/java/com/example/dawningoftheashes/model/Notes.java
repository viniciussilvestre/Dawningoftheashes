package com.example.dawningoftheashes.model;

import android.os.Parcelable;

import java.util.Date;

public class Notes
{
    private static int idCounter = 0;
    private int noteId;
    private String title;
    private String content;
    private final Date creationDate;
    private Date editDate;

    public Notes(String title, String content, Date creationDate, Date editDate)
    {
        this.noteId = idCounter++;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.editDate = editDate;
    }

    public static int getIdCounter()
    {
        return idCounter;
    }

    public int getNoteId()
    {
        return noteId;
    }

    public void setNoteId(int noteId)
    {
        this.noteId = noteId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public Date getEditDate()
    {
        return editDate;
    }

    public void setEditDate(Date editDate)
    {
        this.editDate = editDate;
    }
}
