package com.qw.Exception;

import com.qw.utils.QWJSONResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class CustomExceptionHandler {
    //
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public QWJSONResult handleMaxUploadFile(MaxUploadSizeExceededException e){
        return QWJSONResult.errorMsg("文件大小不能超过500B, 降低文件大小或品质再上传");
    }
}
