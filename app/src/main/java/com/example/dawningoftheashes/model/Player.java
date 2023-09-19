package com.example.dawningoftheashes.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "player_tables")
public class Player implements Parcelable
{
    @PrimaryKey(autoGenerate = true)
    private int playerId;
    private String name;

    public Player()
    {

    }

    public Player(int playerId, String name)
    {
        this.playerId = playerId;
        this.name = name;
    }

    public Player(Parcel source)
    {
        this.playerId = source.readInt();
        this.name = source.readString();
    }

    public int getPlayerId()
    {
        return playerId;
    }

    public void setPlayerId(int playerId)
    {
        this.playerId = playerId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags)
    {
        dest.writeInt(playerId);
        dest.writeString(name);
    }

    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>()
    {
        @Override
        public Player createFromParcel(Parcel source)
        {
            return new Player(source);
        }

        @Override
        public Player[] newArray(int size)
        {
            return new Player[0];
        }
    };
}
