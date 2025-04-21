package com.example.spring.Service;

import com.example.spring.DTO.PostDTO;
import com.example.spring.Exceptions.PostNotFoundException;
import com.example.spring.Model.Post;
import jakarta.transaction.Transactional;

import java.util.List;

public interface PostService {
 PostDTO addNewPost(PostDTO postDTO);
 List<PostDTO> getAllApprovedPosts();
 List<PostDTO> getAllPosts();
 PostDTO getPostById(int id) throws PostNotFoundException;
 void deletePostById(int id)throws PostNotFoundException;
 PostDTO approvePost(int id)throws PostNotFoundException;
 PostDTO updatePost(int id, PostDTO postDTO)throws PostNotFoundException;
 boolean existsByExternalId(int externalId);
 Post save(Post post);

}
