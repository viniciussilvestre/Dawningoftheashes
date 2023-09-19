package com.example.dawningoftheashes.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.dawningoftheashes.data.TableDao;
import com.example.dawningoftheashes.model.Monster;
import com.example.dawningoftheashes.model.Player;
import com.example.dawningoftheashes.model.Sheet;
import com.example.dawningoftheashes.model.Table;

@Database(
        entities = {Table.class, Monster.class, Sheet.class, Player.class},
        version = 1,
        exportSchema = false
)
@TypeConverters({Converters.class})
public abstract class SheetRoomDatabase extends RoomDatabase
{
    public abstract TableDao tableDao();
    public static final int NUMBER_OF_THREADS = 4;

    private static volatile SheetRoomDatabase INSTANCE;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static SheetRoomDatabase getDatabase(final Context context)
    {
        if (INSTANCE == null)
        {
            synchronized (SheetRoomDatabase.class)
            {
                if (INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), SheetRoomDatabase.class, "sheet_database")
                            //.addCallback(sRoomDatabaseCallBack)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback sRoomDatabaseCallBack = new RoomDatabase.Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db)
        {
            super.onCreate(db);
            databaseWriteExecutor.execute(() ->
            {
                TableDao tableDao = INSTANCE.tableDao();
            });
        }
    };
}
