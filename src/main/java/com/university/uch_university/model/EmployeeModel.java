package com.university.uch_university.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "employees")
public class EmployeeModel {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Size(min = 2, message = "Имя не менее 2 символов")
    private String name;
    @Size(min = 2, message = "Фамилия не менее 2 символов")
    private String firstName;
    @Nullable
    private String lastName;

//    @ManyToMany
//    @JoinTable(
//            name = "student_tariffs",
//            joinColumns = @JoinColumn(name = "student_id"),
//            inverseJoinColumns = @JoinColumn(name = "tariff_id")
//    )
//    private List<TariffModel> tariffs = new ArrayList<>();

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private PostModel post;

    public EmployeeModel() {
    }

    public EmployeeModel(UUID id, String name, String firstName, @Nullable String lastName, PostModel post) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.post = post;
    }

    public UUID getId() {
        return id;
    }

    public @Size(min = 2, message = "Имя не менее 2 символов") String getName() {
        return name;
    }

    public @Size(min = 2, message = "Фамилия не менее 2 символов") String getFirstName() {
        return firstName;
    }

    @Nullable
    public String getLastName() {
        return lastName;
    }

    public PostModel getPost() {
        return post;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(@Size(min = 2, message = "Имя не менее 2 символов") String name) {
        this.name = name;
    }

    public void setFirstName(@Size(min = 2, message = "Фамилия не менее 2 символов") String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(@Nullable String lastName) {
        this.lastName = lastName;
    }

    public void setPost(PostModel post) {
        this.post = post;
    }
}
