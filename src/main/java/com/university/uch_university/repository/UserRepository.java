package com.university.uch_university.repository;

import com.university.uch_university.model.UserModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends CrudRepository<UserModel, UUID> {
    UserModel findByUsername(String username);

    boolean existsByUsername(String username);

}
