package com.university.uch_university.service;

import com.university.uch_university.model.PostModel;

import java.util.List;
import java.util.UUID;

public interface PostService {
    public List<PostModel> findAll();

    public PostModel findById(UUID id);

    public PostModel add(PostModel post);

    public PostModel update(PostModel post);

    public void delete(UUID id);

    List<PostModel> findByIds(List<UUID> postIds);
}
