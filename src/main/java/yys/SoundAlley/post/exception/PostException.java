package yys.SoundAlley.post.exception;

import yys.SoundAlley.global.apiPayload.exception.GeneralException;

public class PostException extends GeneralException {
    public PostException(PostErrorCode code) {
        super(code);
    }
}
