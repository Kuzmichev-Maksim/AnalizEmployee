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
@Table(name = "projects")
public class ProjectModel {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Size(min = 2, message = "Имя не менее 2 символов")
    private String name;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private DepartmentModel department;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TargetModel> targets = new ArrayList<>();

    public ProjectModel() {

    }

    public ProjectModel(UUID id, String name, DepartmentModel department, List<TargetModel> targets) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.targets = targets;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @Size(min = 2, message = "Имя не менее 2 символов") String getName() {
        return name;
    }

    public void setName(@Size(min = 2, message = "Имя не менее 2 символов") String name) {
        this.name = name;
    }

    public DepartmentModel getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentModel department) {
        this.department = department;
    }

    public List<TargetModel> getTargets() {
        return targets;
    }

    public void setTargets(List<TargetModel> targets) {
        this.targets = targets;
    }
}
