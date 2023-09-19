package data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import model.Table;

@Dao
public interface TableDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Table table);
    @Delete
    void delete(Table table);
    @Query("SELECT * FROM rpg_tables ORDER BY name ASC")
    LiveData<List<Table>> getAllTables();
    @Query("SELECT * FROM rpg_tables WHERE rpg_tables.id == :id")
    LiveData<Table> getTable(int id);
    @Update
    void update(Table table);

    @Query("SELECT * FROM rpg_tables WHERE name LIKE :searchQuery")
    LiveData<List<Table>> searchDatabase(String searchQuery);
}
