package com.example.spring.ServiceImpl;

import com.example.spring.DTO.PostDTO;
import com.example.spring.Exceptions.PostNotFoundException;
import com.example.spring.Mapper.PostMapper;
import com.example.spring.Model.Post;
import com.example.spring.Repository.PostRepository;
import com.example.spring.Service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    @Override
    public PostDTO addNewPost(PostDTO postDTO) {
        Post post=postMapper.toEntity(postDTO);
        return postMapper.toDTO(postRepository.save(post));
    }

    @Override
    public List<PostDTO> getAllApprovedPosts() {
        return postRepository
                .findAllByApprovedTrue()
                .stream()
                .map(postMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository
                .findAll()
                .stream()
                .map(postMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO getPostById(int id) {
        Post post=postRepository
                .findById(id)
                .orElseThrow(()-> new PostNotFoundException(id));
        return postMapper.toDTO(post);
    }

    @Override
    public void deletePostById(int id) {
        Post post=postRepository
                .findById(id)
                .orElseThrow(()-> new PostNotFoundException(id));
        postRepository.delete(post);
    }

    @Override
    public PostDTO approvePost(int id) {
        Post post=postRepository
                .findById(id)
                .orElseThrow(()-> new PostNotFoundException(id));
        post.setApproved(true);
        postRepository.save(post);
        return postMapper.toDTO(post);
    }

    @Override
    public PostDTO updatePost(int id, PostDTO postDTO) throws PostNotFoundException {
        Post post=postRepository
                .findById(id)
                .orElseThrow(()-> new PostNotFoundException(id));
        return postMapper.toDTO(postRepository.save(postMapper.toEntity(postDTO)));
    }

    @Override
    public boolean existsByExternalId(int externalId) {
        return postRepository.existsByExternalId(externalId);
    }
    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }
}
