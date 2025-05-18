package yys.SoundAlley.member.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import yys.SoundAlley.global.auth.util.JwtUtil;
import yys.SoundAlley.member.dto.MemberResponseDTO;
import yys.SoundAlley.member.dto.OAuth2DTO;
import yys.SoundAlley.member.entity.Member;
import yys.SoundAlley.member.exception.MemberErrorCode;
import yys.SoundAlley.member.exception.MemberException;
import yys.SoundAlley.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class OAuth2ServiceImpl implements OAuth2Service {

    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String tokenURI;

    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String userInfoURI;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String redirectURI;

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    //    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final WebClient webClient = WebClient.builder().build();

    @Override
    public MemberResponseDTO.MemberTokenDTO login(String code) {
        // 1. 인가 코드 → 액세스 토큰
        OAuth2DTO.OAuth2TokenDTO tokenDTO = requestAccessToken(code);

        // 2. 액세스 토큰 → 사용자 정보
        OAuth2DTO.KakaoProfile profile = requestUserInfo(tokenDTO.getAccess_token());

        // 3. 이메일로 회원 조회 또는 신규 회원 등록
        Long id = profile.getId();
        String username = profile.getKakao_account().getProfile().getNickname();
        Member member = findOrCreateMember(id, username);

        // 4. JWT 생성 및 반환
        return createTokenDTO(member);
    }

    // 🔹 1. 인가 코드 → 액세스 토큰
    private OAuth2DTO.OAuth2TokenDTO requestAccessToken(String code) {

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "authorization_code");
        formData.add("client_id", clientId);
        formData.add("redirect_uri", redirectURI);
        formData.add("code", code);

        try {
            return webClient.post()
                    .uri(tokenURI)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                    .bodyValue(formData)
                    .retrieve()
                    .bodyToMono(OAuth2DTO.OAuth2TokenDTO.class)
                    .block();
        } catch (Exception e) {
            throw new MemberException(MemberErrorCode.OAUTH_TOKEN_FAIL);
        }
    }

    // 🔹 2. 액세스 토큰 → 사용자 정보
    private OAuth2DTO.KakaoProfile requestUserInfo(String accessToken) {
        try {
            return webClient.get()
                    .uri(userInfoURI)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                    .header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8")
                    .retrieve()
                    .bodyToMono(OAuth2DTO.KakaoProfile.class)
                    .block();
        } catch (Exception e) {
            throw new MemberException(MemberErrorCode.OAUTH_USER_INFO_FAIL);
        }
    }

    // 🔹 3. 사용자 조회 또는 회원가입
    private Member findOrCreateMember(Long id, String username) {
        return memberRepository.findById(id)
                .orElseGet(() -> memberRepository.save(
                        Member.builder()
                                .username(username)
                                .build()
                ));
    }

    // 🔹 4. JWT 생성 후 DTO로 반환
    private MemberResponseDTO.MemberTokenDTO createTokenDTO(Member member) {
        return MemberResponseDTO.MemberTokenDTO.builder()
                .accessToken(jwtUtil.createAccessToken(member))
                .refreshToken(jwtUtil.createRefreshToken(member))
                .build();
    }
}