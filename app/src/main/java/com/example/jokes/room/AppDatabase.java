package com.example.jokes.room;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.jokes.entities.Joke;

@Database(entities = { Joke.class }, exportSchema = false, version = 34)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AppDao getDao();

    private static volatile AppDatabase appDatabase;
    public static AppDatabase getAppDatabase(Context context) {

        // Android concept - Double checked locking
        // Safe threading
        // Only created once it is needed
        // Once the instance is created, the instance won't be changed and the next thread will use the same instance rather to create a new instance.

        if (appDatabase == null) {
            synchronized (AppDatabase.class) {
                if (appDatabase == null) {
                    appDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "DB")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .addCallback(callback)
                            .build();
                }
            }
        }
        return appDatabase;
    }

    private static AppDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            Log.d("TAG", "created");
            super.onCreate(db);
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            Log.d("TAG", "opened");
            super.onOpen(db);
        }

        @Override
        public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
            Log.d("TAG", "dropped");
            super.onDestructiveMigration(db);
        }
    };
}