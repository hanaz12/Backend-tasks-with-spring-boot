package com.example.spring.Repository;

import com.example.spring.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    public List<Post> findAllByApprovedTrue();
    boolean existsByExternalId(int externalId);
}
