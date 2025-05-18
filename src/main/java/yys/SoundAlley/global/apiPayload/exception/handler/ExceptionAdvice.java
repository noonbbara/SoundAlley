package yys.SoundAlley.global.apiPayload.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import yys.SoundAlley.global.apiPayload.CustomResponse;
import yys.SoundAlley.global.apiPayload.code.BaseErrorCode;
import yys.SoundAlley.global.apiPayload.code.GeneralErrorCode;
import yys.SoundAlley.global.apiPayload.exception.GeneralException;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class ExceptionAdvice {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<CustomResponse<String>> customException(GeneralException e) {
        log.warn("CustomException: {}", e.getCode().getMessage());
        BaseErrorCode code = e.getCode();
        CustomResponse<String> response = CustomResponse.onFailure(code.getCode(), code.getMessage(), e.getMessage());
        return ResponseEntity.status(code.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponse<String>> exception(Exception e) {
        log.error("Internal Server Error: {} ", e.getMessage());
        BaseErrorCode errorCode = GeneralErrorCode.INTERNAL_SERVER_ERROR_500;
        CustomResponse<String> response = CustomResponse.onFailure(
                errorCode.getCode(),
                errorCode.getMessage()
        );
        return ResponseEntity.status(errorCode.getHttpStatus()).body(response);
    }
}