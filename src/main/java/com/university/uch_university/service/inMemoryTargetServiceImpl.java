package com.university.uch_university.service;

import com.university.uch_university.model.TargetModel;
import com.university.uch_university.repository.TargetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class inMemoryTargetServiceImpl implements TargetService {

    private final TargetRepository targetRepository;

    public inMemoryTargetServiceImpl(TargetRepository targetRepository) {
        this.targetRepository = targetRepository;
    }

    @Override
    public List<TargetModel> findAll() {
        return (List<TargetModel>) targetRepository.findAll();
    }

    @Override
    public TargetModel findById(UUID id) {
        return targetRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public TargetModel add(TargetModel target) {
        return targetRepository.save(target);
    }

    @Override
    @Transactional
    public TargetModel update(TargetModel target) {
        return targetRepository.save(target);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        targetRepository.deleteById(id);
    }

    @Override
    public List<TargetModel> findAllById(List<UUID> ids) {
        return (List<TargetModel>) targetRepository.findAllById(ids);
    }
}
