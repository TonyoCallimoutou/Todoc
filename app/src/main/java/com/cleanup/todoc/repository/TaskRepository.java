package com.cleanup.todoc.repository;

import android.database.Cursor;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    private final TaskDao taskDao;

    public TaskRepository(TaskDao taskDao) {
        this.taskDao = taskDao;
    }


    public LiveData<List<Task>> getTasks() {
        return taskDao.getTasks();
    }

    public void addTask(Task task) {
        taskDao.insertTask(task);
    }

    public void removeTask(long id) {
        taskDao.deleteTask(id);
    }

}
