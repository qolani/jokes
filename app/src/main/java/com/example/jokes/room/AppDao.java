package com.example.jokes.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.paging.DataSource;

import com.example.jokes.entities.Joke;

@Dao
public interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long OnCreateJokes(Joke joke);

    @Query("DELETE FROM joke")
    void OnDeleteJokes();

    @Query("SELECT * FROM joke WHERE value LIKE :keyword")
    DataSource.Factory<Integer, Joke> OnSearchJokes(String keyword);

}
