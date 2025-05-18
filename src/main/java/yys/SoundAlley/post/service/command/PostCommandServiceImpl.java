package yys.SoundAlley.post.service.command;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
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
}
