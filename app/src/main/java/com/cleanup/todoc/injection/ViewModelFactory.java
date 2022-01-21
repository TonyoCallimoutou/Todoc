package com.cleanup.todoc.injection;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.cleanup.todoc.ui.TaskViewModel;
import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.repository.ProjectRepository;
import com.cleanup.todoc.repository.TaskRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ViewModelFactory implements ViewModelProvider.Factory {


    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    private final Executor executor;


    private static ViewModelFactory factory;

    public static ViewModelFactory getInstance(Context context) {
        if (factory == null) {
            synchronized (ViewModelFactory.class) {
                if (factory == null) {
                    factory = new ViewModelFactory(context);
                }
            }
        }
        return factory;
    }

    private ViewModelFactory (Context context) {
        TodocDatabase database = TodocDatabase.getInstance(context);

        this.taskRepository = new TaskRepository(database.taskDao());

        this.projectRepository = new ProjectRepository(database.projectDao());

        this.executor = Executors.newSingleThreadExecutor();
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(TaskViewModel.class)) {
            return (T) new TaskViewModel(taskRepository, projectRepository, executor);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
