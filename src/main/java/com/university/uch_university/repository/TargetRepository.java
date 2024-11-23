package com.university.uch_university.repository;

import com.university.uch_university.model.TargetModel;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TargetRepository extends CrudRepository<TargetModel, UUID> {

}
