package yys.SoundAlley.member.service.command;

import yys.SoundAlley.member.dto.MemberResponseDTO;
import yys.SoundAlley.member.entity.Member;

public interface TokenCommandService {
    MemberResponseDTO.LoginResponseDTO createLoginToken(Member member);
}