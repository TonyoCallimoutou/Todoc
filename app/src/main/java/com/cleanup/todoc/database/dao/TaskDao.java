package com.cleanup.todoc.database.dao;

import static androidx.room.OnConflictStrategy.REPLACE;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cleanup.todoc.model.Task;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM Task")
    LiveData<List<Task>> getTasks ();

    @Insert (onConflict = REPLACE)
    long insertTask (Task task);

    @Query("DELETE FROM Task WHERE id = :id")
    int deleteTask(long id);

}
