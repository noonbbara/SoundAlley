package yys.SoundAlley.member.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yys.SoundAlley.global.auth.util.JwtUtil;
import yys.SoundAlley.member.dto.MemberResponseDTO;
import yys.SoundAlley.member.entity.Member;

@Service
@RequiredArgsConstructor
public class TokenCommandServiceImpl implements TokenCommandService {

    private final JwtUtil jwtUtil;

    public MemberResponseDTO.LoginResponseDTO createLoginToken(Member member) {
        String accessToken = jwtUtil.createAccessToken(member);
        String refreshToken = jwtUtil.createRefreshToken(member);
        return MemberResponseDTO.LoginResponseDTO.builder()
                .id(member.getId())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
