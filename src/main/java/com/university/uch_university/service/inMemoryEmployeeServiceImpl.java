package com.university.uch_university.service;

import com.university.uch_university.model.EmployeeModel;
import com.university.uch_university.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class inMemoryEmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public inMemoryEmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeModel> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public EmployeeModel findById(UUID id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public EmployeeModel add(EmployeeModel employee) {
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public EmployeeModel update(EmployeeModel employee) {
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        employeeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteMultiple(List<UUID> ids) {
        ids.forEach(employeeRepository::deleteById);
    }

    @Override
    public List<EmployeeModel> findAllById(List<UUID> ids) {
        return employeeRepository.findAllById(ids);
    }
}
