package com.cleanup.todoc.repository;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.model.Project;

public class ProjectRepository {

    private final ProjectDao projectDao;

    public ProjectRepository(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }


    public void createProject(Project project) {
        projectDao.createProject(project);
    }


    public LiveData<Project[]> getArrayProject () {
        return projectDao.getArrayProjects();
    }
}
