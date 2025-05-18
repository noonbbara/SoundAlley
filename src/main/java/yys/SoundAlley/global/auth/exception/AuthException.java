package yys.SoundAlley.global.auth.exception;

import yys.SoundAlley.global.apiPayload.exception.GeneralException;

public class AuthException extends GeneralException {
    public AuthException(AuthErrorCode code) {
        super(code);
    }
}