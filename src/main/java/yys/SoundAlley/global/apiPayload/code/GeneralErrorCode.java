package yys.SoundAlley.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralErrorCode implements BaseErrorCode {

    BAD_REQUEST_400(HttpStatus.BAD_REQUEST, "COMMON400_0", "잘못된 요청입니다"),
    //MEMBER_400(HttpStatus.BAD_REQUEST, "MEMBER400", "잘못된 요청입니다"),
    UNAUTHORIZED_401(HttpStatus.UNAUTHORIZED, "COMMON401_0", "인증이 필요합니다"),
    FORBIDDEN_403(HttpStatus.FORBIDDEN, "COMMON403_0", "접근이 금지되었습니다"),
    NOT_FOUND_404(HttpStatus.NOT_FOUND, "COMMON404_0", "요청한 자원을 찾을 수 없습니다"),
    INTERNAL_SERVER_ERROR_500(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500_0", "서버 내부 오류가 발생했습니다"),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}