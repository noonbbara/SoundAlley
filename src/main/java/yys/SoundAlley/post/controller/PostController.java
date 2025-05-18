package yys.SoundAlley.post.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yys.SoundAlley.global.apiPayload.CustomResponse;
import yys.SoundAlley.post.dto.PostRequestDTO;
import yys.SoundAlley.post.dto.PostResponseDTO;
import yys.SoundAlley.post.entity.Post;
import yys.SoundAlley.post.service.command.PostCommandService;
import yys.SoundAlley.post.service.query.PostQueryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostCommandService postCommandService;
    private final PostQueryService postQueryService;


    @PostMapping("/posts")
    @Operation(summary = "게시글 생성 API", description = "게시글 생성하는 API")
    public CustomResponse<PostResponseDTO.CreatePostResponseDTO> createPost(@RequestBody PostRequestDTO.CreatePostRequestDTO dto) {
        Post post = postCommandService.createPost(dto);
        return CustomResponse.created(PostResponseDTO.CreatePostResponseDTO.from(post));
    }

    @GetMapping("/posts")
    @Operation(summary = "게시글 조회 API", description = "게시글 조회하는 API")
    public CustomResponse<List<PostResponseDTO.GetPostResponseDTO>> getAllPosts() {
        List<Post> posts = postQueryService.getAllPosts();
        List<PostResponseDTO.GetPostResponseDTO> dtoList = PostResponseDTO.GetPostResponseDTO.fromList(posts);
        return CustomResponse.ok(dtoList);
    }

}
