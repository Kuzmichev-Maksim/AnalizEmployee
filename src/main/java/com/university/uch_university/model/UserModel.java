package com.university.uch_university.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
public class UserModel {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID idUser;

    @NotBlank(message = "Логин не может быть пустым")
    @Size(min = 4, max = 50, message = "Логин должен быть от 4 до 50 символов")
    private String username;

    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 8, message = "Пароль должен содержать минимум 8 символов")
    private String password;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ElementCollection(targetClass = RoleEnum.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<RoleEnum> roles;


    public UserModel() {

    }

    public UserModel(UUID idUser, String username, String password, Set<RoleEnum> roles) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public UUID getIdUser() {
        return idUser;
    }

    public @NotBlank(message = "Логин не может быть пустым") @Size(min = 4, max = 50, message = "Логин должен быть от 4 до 50 символов") String getUsername() {
        return username;
    }

    public @NotBlank(message = "Пароль не может быть пустым") @Size(min = 8, message = "Пароль должен содержать минимум 8 символов") String getPassword() {
        return password;
    }

    public Set<RoleEnum> getRoles() {
        return roles;
    }

    public void setIdUser(UUID idUser) {
        this.idUser = idUser;
    }

    public void setUsername(@NotBlank(message = "Логин не может быть пустым") @Size(min = 4, max = 50, message = "Логин должен быть от 4 до 50 символов") String username) {
        this.username = username;
    }

    public void setPassword(@NotBlank(message = "Пароль не может быть пустым") @Size(min = 8, message = "Пароль должен содержать минимум 8 символов") String password) {
        this.password = password;
    }

    public void setRoles(Set<RoleEnum> roles) {
        this.roles = roles;
    }
}

