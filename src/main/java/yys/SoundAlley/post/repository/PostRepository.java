package yys.SoundAlley.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yys.SoundAlley.post.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
