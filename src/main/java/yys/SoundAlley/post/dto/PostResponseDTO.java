package yys.SoundAlley.post.dto;

import lombok.*;
import org.springframework.data.domain.Slice;
import yys.SoundAlley.member.dto.MemberResponseDTO;
import yys.SoundAlley.member.entity.Member;
import yys.SoundAlley.post.entity.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PostResponseDTO {

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class CreatePostResponseDTO {
        private Long id;
        private String title;
        private String description;
        private LocalDateTime createdAt;
        private String place;
        private String date;

        public static CreatePostResponseDTO from(Post post) {
            return CreatePostResponseDTO.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .description(post.getDescription())
                    .place(post.getPlace())
                    .date(post.getDate())
                    .createdAt(post.getCreatedAt())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class GetPostResponseDTO {
        private Long id;
        private String title;
        private String description;
        private LocalDateTime createdAt;
        private String place;
        private String date;
        private String writerName;

        public static GetPostResponseDTO from(Post post) {
            return GetPostResponseDTO.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .description(post.getDescription())
                    .place(post.getPlace())
                    .date(post.getDate())
                    .createdAt(post.getCreatedAt())
                    .writerName(post.getMember().getUsername())
                    .build();
        }

        public static List<GetPostResponseDTO> fromList(List<Post> posts) {
            return posts.stream()
                    .map(GetPostResponseDTO::from)
                    .collect(Collectors.toList());
        }
    }

}
