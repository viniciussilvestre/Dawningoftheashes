package com.example.dawningoftheashes.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sheets_table")
public class Sheet implements Parcelable
{
    @PrimaryKey(autoGenerate = true)
    private int sheetId;
    private String sheetName;
    private int playerId;
    private int tableId;

    public Sheet()
    {

    }

    public Sheet(int sheetId, String sheetName, int playerId, int tableId)
    {
        this.sheetId = sheetId;
        this.sheetName = sheetName;
        this.playerId = playerId;
        this.tableId = tableId;
    }

    public Sheet(Parcel source)
    {
        this.sheetId = source.readInt();
        this.sheetName = source.readString();
        this.playerId = source.readInt();
        this.tableId = source.readInt();
    }

    public int getSheetId()
    {
        return sheetId;
    }

    public void setSheetId(int sheetId)
    {
        this.sheetId = sheetId;
    }

    public String getSheetName()
    {
        return sheetName;
    }

    public void setSheetName(String sheetName)
    {
        this.sheetName = sheetName;
    }

    public int getPlayerId()
    {
        return playerId;
    }

    public void setPlayerId(int playerId)
    {
        this.playerId = playerId;
    }

    public int getTableId()
    {
        return tableId;
    }

    public void setTableId(int tableId)
    {
        this.tableId = tableId;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags)
    {
        dest.writeInt(sheetId);
        dest.writeString(sheetName);
        dest.writeInt(sheetId);
        dest.writeInt(tableId);
    }

    public static final Parcelable.Creator<Sheet> CREATOR = new Creator<Sheet>()
    {
        @Override
        public Sheet createFromParcel(Parcel source)
        {
            return new Sheet(source);
        }

        @Override
        public Sheet[] newArray(int size)
        {
            return new Sheet[0];
        }
    };
}
