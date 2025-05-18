package yys.SoundAlley.post.service.query;

import yys.SoundAlley.member.entity.Member;
import yys.SoundAlley.post.dto.PostResponseDTO;
import yys.SoundAlley.post.entity.Post;

import java.util.List;

public interface PostQueryService {
    List<Post> getAllPosts();
    List<PostResponseDTO.GetPostResponseDTO> getPostsByMember(Member member);
}
