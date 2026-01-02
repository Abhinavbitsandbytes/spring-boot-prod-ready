package com.example.productioReady.productioReady.services;

import com.example.productioReady.productioReady.dto.PostDTO;
import com.example.productioReady.productioReady.entities.PostEntity;
import com.example.productioReady.productioReady.exceptions.ResourceNotFoundExceptions;
import com.example.productioReady.productioReady.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // this will create constructor and do DI
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PostDTO> getAllPosts() {
        List<PostEntity> postEntities = postRepository.findAll();
        return postEntities.stream()
                .map(entity -> modelMapper.map(entity, PostDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO createNewPost(PostDTO inputPost) {
        // Step 1: Convert DTO → Entity
        PostEntity postEntity = modelMapper.map(inputPost, PostEntity.class);

        // Step 2: Persist entity
        PostEntity savedPost = postRepository.save(postEntity);

        // Step 3: Convert Entity → DTO
        PostDTO responsePost = modelMapper.map(savedPost, PostDTO.class);

        return responsePost;
    }

    @Override
    public PostDTO getPostById(Long postId) {
//        PostEntity postEntity = postRepository.findById(postId); // it will return a optional here

        PostEntity postEntity = postRepository.
                findById(postId).
                orElseThrow(() -> new ResourceNotFoundExceptions("post Not found with id" + postId));
        return modelMapper.map(postEntity, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO inputPost, Long postId) {
        PostEntity olderPost = postRepository.
                findById(postId).
                orElseThrow(() -> new ResourceNotFoundExceptions("post Not found with id" + postId));
        inputPost.setId(postId);
        modelMapper.map(inputPost, olderPost);
        PostEntity savedPostEntity = postRepository.save(olderPost);
        return modelMapper.map(savedPostEntity, PostDTO.class);
    }
    // in this method we are doing partial update
    // the steps are
    // 1. fetch the older entity from DB
    // 2. update the fields which are non null in input DTO to older entity
    // 3. save the older entity
    // 4. convert saved entity to DTO and return
    // conversion from DTO to entity and entity to DTO is required because we are using ModelMapper
    // which works with objects not with individual fields
    // we can use libraries like MapStruct to do field by field mapping.


}
