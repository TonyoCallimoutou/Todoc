package com.cleanup.todoc.dao;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4ClassRunner.class)
public class ProjectDaoTest {


    private TodocDatabase database;



    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();



    @Before
    public void initDb() {


        Context context = ApplicationProvider.getApplicationContext();
        database = Room.inMemoryDatabaseBuilder(context, TodocDatabase.class)
                .allowMainThreadQueries()
                .build();
    }




    @After
    public void closeDb() {
        database.close();
    }


    @Test
    public void insertAndGetProject() throws InterruptedException {

        Project newProject = Project.getAllProjects()[1];

        Project[] emptyArray = LivedataTestUtils.getValue(database.projectDao().getArrayProjects());

        assertEquals(0, emptyArray.length);


        database.projectDao().createProject(newProject);

        Project[] projects = LivedataTestUtils.getValue(database.projectDao().getArrayProjects());

        assertEquals(1, projects.length);
        assertEquals(newProject.getId(),projects[0].getId());
        assertEquals(newProject.getName(), projects[0].getName());
        assertEquals(newProject.getColor(), projects[0].getColor());

    }
}
