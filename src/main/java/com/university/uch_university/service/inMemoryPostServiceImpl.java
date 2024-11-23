package com.university.uch_university.service;

import com.university.uch_university.model.PostModel;
import com.university.uch_university.repository.PostRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class inMemoryPostServiceImpl implements PostService {
    private final PostRepository postRepository;

    public inMemoryPostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<PostModel> findAll() {
        return postRepository.findAll(Sort.by("id"));
    }

    @Override
    public PostModel findById(UUID id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public PostModel add(PostModel post) {
        return postRepository.save(post);
    }

    @Override
    public PostModel update(PostModel post) {
        if (postRepository.existsById(post.getId())) {
            return postRepository.save(post);
        }
        return null;
    }

    @Override
    public void delete(UUID id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
        }
    }

    @Override
    public List<PostModel> findByIds(List<UUID> postIds) {
            return postRepository.findAllById(postIds);
    }
}
