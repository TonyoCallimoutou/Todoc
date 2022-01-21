package com.cleanup.todoc.database.dao;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.cleanup.todoc.model.Project;

@Dao
public interface ProjectDao {

    @Insert(onConflict = REPLACE)
    void createProject(Project project);


    @Query("SELECT * FROM Project")
    LiveData<Project[]> getArrayProjects();
}
