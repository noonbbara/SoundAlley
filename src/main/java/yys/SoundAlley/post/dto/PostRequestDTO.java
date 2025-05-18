package yys.SoundAlley.post.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import yys.SoundAlley.member.entity.Member;
import yys.SoundAlley.post.entity.Post;

public class PostRequestDTO {

    @Getter
    public static class CreatePostRequestDTO {
        private String title;
        private String description;
        private String place;
        private String date;
        //private Long memberId;

        public Post toEntity() {
            return Post.builder()
                    .title(this.title)
                    .description(this.description)
                    .place(this.place)
                    .date(this.date)
                    //.member(member)
                    .build();
        }
    }
}
