package com.example.dawningoftheashes.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "monster_table")
public class Monster implements Parcelable
{
    @PrimaryKey(autoGenerate = true)
    private int monsterId;
    private String monsterName;

    public Monster()
    {

    }

    public Monster(int monsterId, String monsterName)
    {
        this.monsterId = monsterId;
        this.monsterName = monsterName;
    }

    public Monster(Parcel source)
    {
        this.monsterId = source.readInt();
        this.monsterName = source.readString();
    }

    public int getMonsterId()
    {
        return monsterId;
    }

    public void setMonsterId(int monsterId)
    {
        this.monsterId = monsterId;
    }

    public String getMonsterName()
    {
        return monsterName;
    }

    public void setMonsterName(String monsterName)
    {
        this.monsterName = monsterName;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags)
    {
        dest.writeInt(monsterId);
        dest.writeString(monsterName);
    }

    public static final Parcelable.Creator<Monster> CREATOR = new Creator<Monster>()
    {
        @Override
        public Monster createFromParcel(Parcel source)
        {
            return new Monster(source);
        }

        @Override
        public Monster[] newArray(int size)
        {
            return new Monster[0];
        }
    };
}
