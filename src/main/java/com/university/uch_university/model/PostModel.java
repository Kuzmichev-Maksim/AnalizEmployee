package com.university.uch_university.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "posts")
public class PostModel {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Size(min = 2, message = "Название не менее 2 символов")
    private String name;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeModel> employees = new ArrayList<>();

    public PostModel() {
    }

    public PostModel(UUID id, String name, List<EmployeeModel> employees) {
        this.id = id;
        this.name = name;
        this.employees = employees;
    }

    public UUID getId() {
        return id;
    }

    public @Size(min = 2, message = "Название не менее 2 символов") String getName() {
        return name;
    }

    public List<EmployeeModel> getEmployees() {
        return employees;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(@Size(min = 2, message = "Название не менее 2 символов") String name) {
        this.name = name;
    }

    public void setEmployees(List<EmployeeModel> employees) {
        this.employees = employees;
    }
}
