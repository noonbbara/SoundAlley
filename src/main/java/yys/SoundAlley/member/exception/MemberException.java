package yys.SoundAlley.member.exception;

import yys.SoundAlley.global.apiPayload.exception.GeneralException;

public class MemberException extends GeneralException {

    public MemberException(MemberErrorCode code) {
        super(code);
    }
}

