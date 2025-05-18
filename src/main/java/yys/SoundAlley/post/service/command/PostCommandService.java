package yys.SoundAlley.post.service.command;

import yys.SoundAlley.member.entity.Member;
import yys.SoundAlley.post.dto.PostRequestDTO;
import yys.SoundAlley.post.entity.Post;

public interface PostCommandService {
    Post createPost(PostRequestDTO.CreatePostRequestDTO dto);
    void deletePost(Long postId, Member member);
}
