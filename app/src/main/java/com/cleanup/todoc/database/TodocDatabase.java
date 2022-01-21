package com.cleanup.todoc.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

@Database(entities = {Task.class, Project.class},version = 1, exportSchema = false)
public abstract class TodocDatabase extends RoomDatabase {

    // Singleton
    private static volatile TodocDatabase INSTANCE;

    // DAO
    public abstract TaskDao taskDao();
    public abstract ProjectDao projectDao();

    // Instance
    public static TodocDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (TodocDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TodocDatabase.class,
                            "MyDatabase.db")
                            .build();
                }
            }
        }

        return INSTANCE;
    }


}
