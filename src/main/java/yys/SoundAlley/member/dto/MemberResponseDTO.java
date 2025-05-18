package yys.SoundAlley.member.dto;

import lombok.Builder;
import lombok.Getter;
import yys.SoundAlley.member.entity.Member;

public class MemberResponseDTO {
    @Getter
    @Builder
    public static class SignUpResponseDTO {
        private Long id;
        public static SignUpResponseDTO from(Member member) {
            return SignUpResponseDTO.builder()
                    .id(member.getId())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class LoginResponseDTO {
        private Long id;
        private String accessToken;
        private String refreshToken;
        public static LoginResponseDTO from(Member member) {
            return LoginResponseDTO.builder()
                    .id(member.getId())
//                    .accessToken()
//                    .refreshToken()
                    .build();
        }
    }

    @Builder
    public record MemberTokenDTO(String accessToken, String refreshToken) {}
}
