package com.university.uch_university.service;

import com.university.uch_university.model.ProjectModel;

import java.util.List;
import java.util.UUID;

public interface ProjectService {

    List<ProjectModel> findAll();

    ProjectModel findById(UUID id);

    ProjectModel add(ProjectModel project);

    ProjectModel update(ProjectModel project);

    void deleteById(UUID id);

    void deleteMultiple(List<UUID> ids);

    List<ProjectModel> findAllById(List<UUID> projectIds);
}
