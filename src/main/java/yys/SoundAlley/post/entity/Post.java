package yys.SoundAlley.post.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import yys.SoundAlley.global.entity.BaseEntity;
import yys.SoundAlley.member.entity.Member;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "post")
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    @NotNull
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "place")
    private String place;

    @Column(name = "date")
    private String date;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
}
