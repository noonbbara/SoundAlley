package yys.SoundAlley.post.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import yys.SoundAlley.global.apiPayload.code.BaseErrorCode;

@AllArgsConstructor
@Getter
public enum PostErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE404", "게시글을 찾지 못했습니다."),
    UNSUPPORTED_QUERY(HttpStatus.BAD_REQUEST, "ARTICLE401", "지원하지 않는 쿼리입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}
