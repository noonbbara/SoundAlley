package yys.SoundAlley.member.service;

import yys.SoundAlley.member.dto.MemberResponseDTO;

public interface OAuth2Service {
    MemberResponseDTO.MemberTokenDTO login(String code);
}