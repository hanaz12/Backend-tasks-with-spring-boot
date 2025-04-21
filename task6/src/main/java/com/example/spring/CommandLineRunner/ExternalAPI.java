package com.example.spring.CommandLineRunner;

import com.example.spring.DTO.ExternalPostDTO;
import com.example.spring.Mapper.PostMapper;
import com.example.spring.Model.Post;
import com.example.spring.Service.PostService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class ExternalAPI implements CommandLineRunner {

    private final WebClient webClient;
    private final PostService postService;
    private final PostMapper postMapper;

    public ExternalAPI(WebClient.Builder webClientBuilder, PostService postService, PostMapper postMapper) {
        this.webClient = webClientBuilder.baseUrl("https://jsonplaceholder.typicode.com").build();
        this.postService = postService;
        this.postMapper = postMapper;
    }

    @Override
    public void run(String... args) throws Exception {

        Flux<ExternalPostDTO> externalPosts = webClient.get()
                .uri("/posts")
                .retrieve()
                .bodyToFlux(ExternalPostDTO.class)
                .onErrorResume(e -> Flux.empty());


        externalPosts.subscribe(externalPost -> {

            if (!postService.existsByExternalId(externalPost.getId())) {

                Post post = postMapper.toEntityFromExternal(externalPost);

                postService.save(post);
            }
        });
    }
}