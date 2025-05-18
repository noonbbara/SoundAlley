package yys.SoundAlley.post.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yys.SoundAlley.member.entity.Member;
import yys.SoundAlley.post.dto.PostResponseDTO;
import yys.SoundAlley.post.entity.Post;
import yys.SoundAlley.post.exception.PostErrorCode;
import yys.SoundAlley.post.exception.PostException;
import yys.SoundAlley.post.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostQueryServiceImpl implements PostQueryService {

    private final PostRepository postRepository;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<PostResponseDTO.GetPostResponseDTO> getPostsByMember(Member member) {
        List<Post> posts = postRepository.findByMember(member);
        return posts.stream()
                .map(PostResponseDTO.GetPostResponseDTO::from)
                .collect(Collectors.toList());
    }

}
