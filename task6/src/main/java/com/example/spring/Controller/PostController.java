package com.example.spring.Controller;

import com.example.spring.DTO.PostDTO;
import com.example.spring.Exceptions.SuccessResponse;
import com.example.spring.Service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<SuccessResponse> addNewPost(@RequestBody PostDTO postDTO) {
        PostDTO newPostDTO = postService.addNewPost(postDTO);
        SuccessResponse response=
                new SuccessResponse("Post added Successfully" , HttpStatus.OK.value(),newPostDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<SuccessResponse> getAllApprovedPosts() {
      List<PostDTO> approvedPosts = postService.getAllApprovedPosts();
        SuccessResponse response=
                new SuccessResponse("Approved Posts retreived Successfully" , HttpStatus.OK.value(),approvedPosts);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/all")
    public ResponseEntity<SuccessResponse> getAllPosts() {
        List<PostDTO> approvedPosts = postService.getAllPosts();
        SuccessResponse response=
                new SuccessResponse("Post retreived Successfully" , HttpStatus.OK.value(),approvedPosts);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse> getPostById(@PathVariable int id) {
        PostDTO postDTO = postService.getPostById(id);
        SuccessResponse response=
                new SuccessResponse("Post retreived Successfully" , HttpStatus.OK.value(),postDTO);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse> updatePostById(@PathVariable int id , @RequestBody PostDTO postDTO) {
        PostDTO updatedPostDTO = postService.updatePost(id,postDTO);
        SuccessResponse response=
                new SuccessResponse("Post updated Successfully" , HttpStatus.OK.value(),updatedPostDTO);
        return ResponseEntity.ok(response);
    }


}
