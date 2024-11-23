package com.university.uch_university.service;

import com.university.uch_university.model.EmployeeModel;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    List<EmployeeModel> findAll();

    EmployeeModel findById(UUID id);

    EmployeeModel add(EmployeeModel employee);

    EmployeeModel update(EmployeeModel employee);

    void deleteById(UUID id);

    void deleteMultiple(List<UUID> ids);

    List<EmployeeModel> findAllById(List<UUID> employeeIds);
}
