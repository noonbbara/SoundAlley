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
import yys.SoundAlley.member.exception.MemberErrorCode;
import yys.SoundAlley.member.repository.MemberRepository;
import yys.SoundAlley.post.dto.PostRequestDTO;
import yys.SoundAlley.post.dto.PostResponseDTO;
import yys.SoundAlley.post.entity.Post;
import yys.SoundAlley.post.service.command.PostCommandService;
import yys.SoundAlley.post.service.query.PostQueryService;

import java.util.List;

import static yys.SoundAlley.global.apiPayload.code.GeneralSuccessCode.NO_CONTENT;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostCommandService postCommandService;
    private final PostQueryService postQueryService;
    private final MemberRepository memberRepository;


    @PostMapping
    @Operation(summary = "게시글 생성 API", description = "게시글 생성하는 API")
    public CustomResponse<PostResponseDTO.CreatePostResponseDTO> createPost(
            @RequestBody PostRequestDTO.CreatePostRequestDTO dto,
            @AuthenticationPrincipal CustomUserDetails userDetails  // JWT에서 추출한 로그인 유저 정보
    ) {
        // userDetails로부터 로그인한 회원 ID 가져오기
        Long memberId = userDetails.getId();

        // memberId를 dto에 넘겨주거나, 서비스 레이어에 같이 넘김
        Post post = postCommandService.createPost(dto, memberId);

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

        Member member = memberRepository.findById(userDetails.getId())
                .orElseThrow(() -> new RuntimeException(MemberErrorCode.NOT_FOUND.getMessage()));
        List<PostResponseDTO.GetPostResponseDTO> posts = postQueryService.getPostsByMember(member);
        return CustomResponse.ok(posts);
    }

    @DeleteMapping("/{postId}")
    public CustomResponse<Void> deletePost(
            @PathVariable Long postId,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        Member member = memberRepository.findById(userDetails.getId())
                .orElseThrow(() -> new RuntimeException(MemberErrorCode.NOT_FOUND.getMessage()));
        postCommandService.deletePost(postId, member);
        return CustomResponse.ok(null);
    }
}
