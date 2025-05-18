package yys.SoundAlley.member.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yys.SoundAlley.global.apiPayload.CustomResponse;
import yys.SoundAlley.member.dto.MemberRequestDTO;
import yys.SoundAlley.member.dto.MemberResponseDTO;
import yys.SoundAlley.member.entity.Member;
import yys.SoundAlley.member.service.OAuth2ServiceImpl;
import yys.SoundAlley.member.service.command.MemberCommandService;

import java.util.Map;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class MemberController {

    // private final MemberCommandService memberCommandService;
    private final OAuth2ServiceImpl oAuth2Service;


//    @GetMapping("/oauth2/callback/kakao")
//    public CustomResponse<MemberResponseDTO.MemberTokenDTO> loginWithKakao(@RequestParam("code") String code) {
//        MemberResponseDTO.MemberTokenDTO tokenDTO = oAuth2Service.login(code);
//        return CustomResponse.ok(tokenDTO);
//    }

    @PostMapping("/api/auth/kakao")
    public CustomResponse<MemberResponseDTO.MemberTokenDTO> loginWithKakao(@RequestBody Map<String, String> body) {
        String code = body.get("code");
        MemberResponseDTO.MemberTokenDTO tokenDTO = oAuth2Service.login(code);
        return CustomResponse.ok(tokenDTO);
    }


}