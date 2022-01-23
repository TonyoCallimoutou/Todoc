package com.cleanup.todoc.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.runner.AndroidJUnit4;

import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.injection.ViewModelFactory;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.ui.TaskViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4ClassRunner.class)
public class TaskDaoTest {

    private TodocDatabase database;

    private Project [] projects;


    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();



    @Before
    public void initDb() throws Exception {


        Context context = ApplicationProvider.getApplicationContext();
        database = Room.inMemoryDatabaseBuilder(context, TodocDatabase.class)
                .allowMainThreadQueries()
                .build();


        for (int i=0; i<Project.getAllProjects().length; i++) {
            database.projectDao().createProject(Project.getAllProjects()[i]);
        }

        projects = LivedataTestUtils.getValue(database.projectDao().getArrayProjects());

    }


    @After
    public void closeDb() {
        database.close();
    }




    @Test
    public void insertAndGetTask() throws InterruptedException {

        Task newTask = new Task(projects[1].getId(),"Test1",100);

        List<Task> emptyList = LivedataTestUtils.getValue(database.taskDao().getTasks());

        assertEquals(0, emptyList.size());


        database.taskDao().insertTask(newTask);

        List<Task> tasks = LivedataTestUtils.getValue(database.taskDao().getTasks());

        assertEquals(1, tasks.size());

    }

    @Test
    public void removeTask() throws InterruptedException {

        Task taskToDelete = new Task(projects[1].getId(),"Task to delete", 200);


        database.taskDao().insertTask(taskToDelete);

        List<Task> tasks = LivedataTestUtils.getValue(database.taskDao().getTasks());

        assertEquals(1, tasks.size());



        database.taskDao().deleteTask(tasks.get(0).getId());

        List<Task> emptyList = LivedataTestUtils.getValue(database.taskDao().getTasks());

        assertEquals(0, emptyList.size());

    }
}
