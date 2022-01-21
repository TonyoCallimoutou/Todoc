package com.cleanup.todoc.ui;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repository.ProjectRepository;
import com.cleanup.todoc.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class TaskViewModel extends ViewModel {

    // Repository

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    private final Executor executor;

    public TaskViewModel(TaskRepository taskRepository,
                         ProjectRepository projectRepository,
                         Executor executor) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.executor = executor;
    }

    // Project

    public void createProject(Project project) {
        executor.execute(() -> {
            projectRepository.createProject(project);
        });
    }

    public LiveData<Project[]> getArrayProjects() {
        return projectRepository.getArrayProject();
    }


    // Tasks

    public LiveData<List<Task>> getTasks() {
        return taskRepository.getTasks();
    }

    public void createTask(Task task) {
        executor.execute(() -> {
            taskRepository.addTask(task);
        });
    }

    public void removeTask(Task task) {
        executor.execute(() -> {
            taskRepository.removeTask(task.getId());
        });
    }


}
