package yys.SoundAlley.global.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import yys.SoundAlley.global.apiPayload.CustomResponse;
import yys.SoundAlley.global.apiPayload.code.BaseErrorCode;
import yys.SoundAlley.global.apiPayload.code.GeneralErrorCode;

import java.io.IOException;

@Component
public class CustomEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        BaseErrorCode code = GeneralErrorCode.UNAUTHORIZED_401;
        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(code.getHttpStatus().value());

        CustomResponse<Object> errorResponse = CustomResponse.onFailure(
                code.getCode(),
                code.getMessage(),
                null
        );

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), errorResponse);
    }
}