package yys.SoundAlley.post.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yys.SoundAlley.post.entity.Post;
import yys.SoundAlley.post.exception.PostErrorCode;
import yys.SoundAlley.post.exception.PostException;
import yys.SoundAlley.post.repository.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostQueryServiceImpl implements PostQueryService {

    private final PostRepository postRepository;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

}
