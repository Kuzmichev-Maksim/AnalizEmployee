package com.university.uch_university.service;

import com.university.uch_university.model.DepartmentModel;
import com.university.uch_university.model.EmployeeModel;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {

    List<DepartmentModel> findAll();

    DepartmentModel findById(UUID id);

    DepartmentModel add(DepartmentModel department);

    DepartmentModel update(DepartmentModel department);

    void deleteById(UUID id);

    void deleteMultiple(List<UUID> ids);

    List<DepartmentModel> findAllById(List<UUID> departmentIds);
}
