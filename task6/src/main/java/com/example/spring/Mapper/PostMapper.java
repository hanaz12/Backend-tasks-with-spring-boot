package com.example.spring.Mapper;

import com.example.spring.DTO.ExternalPostDTO;
import com.example.spring.DTO.PostDTO;
import com.example.spring.Model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "id" , ignore = true)
    @Mapping(target="externalId" , ignore = true)
    @Mapping(target="approved" , defaultValue = "false")
    @Mapping(target="userId" , ignore = true)
    Post toEntity(PostDTO postDTO);

    PostDTO toDTO(Post post);

    @Mapping(target = "id" , ignore = true)
    @Mapping(target="externalId" , ignore = true)
    @Mapping(target="approved" , defaultValue = "false")
    @Mapping(target="userId" , ignore = true)
    void updatePostFromDTO( PostDTO postDTO , @MappingTarget Post post);


@Mapping(target="externalId" , source="id")
@Mapping(target="approved" , ignore = true)
@Mapping(target="id" , ignore = true)
    Post toEntityFromExternal (ExternalPostDTO externalPostDTO);

}
