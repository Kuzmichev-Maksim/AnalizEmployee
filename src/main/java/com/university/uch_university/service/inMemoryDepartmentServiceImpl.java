package com.university.uch_university.service;

import com.university.uch_university.model.DepartmentModel;
import com.university.uch_university.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class inMemoryDepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public inMemoryDepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<DepartmentModel> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public DepartmentModel findById(UUID id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public DepartmentModel add(DepartmentModel department) {
        return departmentRepository.save(department);
    }

    @Override
    @Transactional
    public DepartmentModel update(DepartmentModel department) {
        return departmentRepository.save(department);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        departmentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteMultiple(List<UUID> ids) {
        ids.forEach(departmentRepository::deleteById);
    }

    @Override
    public List<DepartmentModel> findAllById(List<UUID> ids) {
        return departmentRepository.findAllById(ids);
    }
}
