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
    private boolean isLocalUser;

    public Player()
    {

    }

    public Player(int playerId, String name, boolean isLocalUser)
    {
        this.playerId = playerId;
        this.name = name;
        this.isLocalUser = isLocalUser;
    }

    public Player(Parcel source)
    {
        this.playerId = source.readInt();
        this.name = source.readString();
        this.isLocalUser = source.readByte() != 0;
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

    public boolean isLocalUser()
    {
        return isLocalUser;
    }

    public void setLocalUser(boolean localUser)
    {
        isLocalUser = localUser;
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
        dest.writeByte((byte) (isLocalUser ? 1 : 0));
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
