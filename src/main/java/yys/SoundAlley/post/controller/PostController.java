package yys.SoundAlley.post.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import yys.SoundAlley.global.apiPayload.CustomResponse;
import yys.SoundAlley.global.apiPayload.code.GeneralSuccessCode;
import yys.SoundAlley.global.auth.CustomUserDetails;
import yys.SoundAlley.member.entity.Member;
import yys.SoundAlley.post.dto.PostRequestDTO;
import yys.SoundAlley.post.dto.PostResponseDTO;
import yys.SoundAlley.post.entity.Post;
import yys.SoundAlley.post.service.command.PostCommandService;
import yys.SoundAlley.post.service.query.PostQueryService;

import java.util.List;

import static yys.SoundAlley.global.apiPayload.code.GeneralSuccessCode.NO_CONTENT;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostCommandService postCommandService;
    private final PostQueryService postQueryService;


    @PostMapping
    @Operation(summary = "게시글 생성 API", description = "게시글 생성하는 API")
    public CustomResponse<PostResponseDTO.CreatePostResponseDTO> createPost(@RequestBody PostRequestDTO.CreatePostRequestDTO dto) {
        Post post = postCommandService.createPost(dto);
        return CustomResponse.created(PostResponseDTO.CreatePostResponseDTO.from(post));
    }

    @GetMapping
    @Operation(summary = "게시글 조회 API", description = "게시글 조회하는 API")
    public CustomResponse<List<PostResponseDTO.GetPostResponseDTO>> getAllPosts() {
        List<Post> posts = postQueryService.getAllPosts();
        List<PostResponseDTO.GetPostResponseDTO> dtoList = PostResponseDTO.GetPostResponseDTO.fromList(posts);
        return CustomResponse.ok(dtoList);
    }

    @GetMapping("/mine")
    public CustomResponse<List<PostResponseDTO.GetPostResponseDTO>> getMyPosts(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Member member = userDetails.getMember();
        List<PostResponseDTO.GetPostResponseDTO> posts = postQueryService.getPostsByMember(member);
        return CustomResponse.ok(posts);
    }

    @DeleteMapping("/{postId}")
    public CustomResponse<Void> deletePost(
            @PathVariable Long postId,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        Member member = userDetails.getMember();
        postCommandService.deletePost(postId, member);
        return CustomResponse.ok(null);
    }
}
