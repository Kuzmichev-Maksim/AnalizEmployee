package com.university.uch_university.service;

import com.university.uch_university.model.PostModel;
import com.university.uch_university.model.TargetModel;

import java.util.List;
import java.util.UUID;

public interface TargetService {
    public List<TargetModel> findAll();

    public TargetModel findById(UUID id);

    public TargetModel add(TargetModel target);

    public TargetModel update(TargetModel target);

    public void deleteById(UUID id);

    List<TargetModel> findAllById(List<UUID> targetIds);
}
