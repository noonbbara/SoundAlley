package yys.SoundAlley.member.service.command;

import yys.SoundAlley.member.dto.MemberRequestDTO;
import yys.SoundAlley.member.dto.MemberResponseDTO;
import yys.SoundAlley.member.entity.Member;

public interface MemberCommandService {
    Member signUp(MemberRequestDTO.SignUpRequestDTO dto);
    MemberResponseDTO.LoginResponseDTO login(MemberRequestDTO.LoginRequestDTO dto);
}
