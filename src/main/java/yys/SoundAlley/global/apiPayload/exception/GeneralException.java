package yys.SoundAlley.global.apiPayload.exception;

import lombok.Getter;
import yys.SoundAlley.global.apiPayload.code.BaseErrorCode;

@Getter
public class GeneralException extends RuntimeException{

    // 예외에서 발생한 에러의 상세 내용
    private final BaseErrorCode code;

    // 생성자
    public GeneralException(BaseErrorCode code) {
        this.code = code;
    }
}