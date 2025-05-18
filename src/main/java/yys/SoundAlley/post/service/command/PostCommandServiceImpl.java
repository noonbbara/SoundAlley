package yys.SoundAlley.post.service.command;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yys.SoundAlley.global.apiPayload.exception.GeneralException;
import yys.SoundAlley.member.entity.Member;
import yys.SoundAlley.member.exception.MemberErrorCode;
import yys.SoundAlley.member.repository.MemberRepository;
import yys.SoundAlley.post.dto.PostRequestDTO;
import yys.SoundAlley.post.entity.Post;
import yys.SoundAlley.post.repository.PostRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class PostCommandServiceImpl implements PostCommandService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Override
    public Post createPost(PostRequestDTO.CreatePostRequestDTO dto) {
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new GeneralException(MemberErrorCode.NOT_FOUND));

        Post post = dto.toEntity(member);
        return postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId, Member member) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        // 작성자 본인인지 검증
        if (!post.getMember().getId().equals(member.getId())) {
            throw new AccessDeniedException("본인이 작성한 게시글만 삭제할 수 있습니다.");
        }

        postRepository.delete(post);
    }

}
