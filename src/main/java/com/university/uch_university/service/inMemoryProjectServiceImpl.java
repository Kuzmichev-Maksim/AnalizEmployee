package com.university.uch_university.service;

import com.university.uch_university.model.ProjectModel;
import com.university.uch_university.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class inMemoryProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public inMemoryProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<ProjectModel> findAll() {
        return (List<ProjectModel>) projectRepository.findAll();
    }

    @Override
    public ProjectModel findById(UUID id) {
        return projectRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public ProjectModel add(ProjectModel project) {
        return projectRepository.save(project);
    }

    @Override
    @Transactional
    public ProjectModel update(ProjectModel project) {
        return projectRepository.save(project);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        projectRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteMultiple(List<UUID> ids) {
        ids.forEach(projectRepository::deleteById);
    }

    @Override
    public List<ProjectModel> findAllById(List<UUID> ids) {
        return (List<ProjectModel>) projectRepository.findAllById(ids);
    }
}
