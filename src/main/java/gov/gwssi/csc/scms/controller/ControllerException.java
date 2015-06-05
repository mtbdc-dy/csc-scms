package gov.gwssi.csc.scms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * Created by Lei on 2015/5/15.
 * 处理全局controller异常
 */
@ControllerAdvice(annotations = RestController.class)
public class ControllerException {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorJSON defaultException(Exception ex) {
        int statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        ErrorJSON ejson = new ErrorJSON(statusCode, statusCode,
                ex.getMessage(), Arrays.toString(ex.getStackTrace()));
        return ejson;
    }

    class ErrorJSON {
        private Integer status;
        private Integer errorCode;
        private String message;
        private String moreInfo;

        public ErrorJSON(Integer status, Integer errorCode, String message, String moreInfo) {
            this.status = status;
            this.errorCode = errorCode;
            this.message = message;
            this.moreInfo = moreInfo;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Integer getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(Integer errorCode) {
            this.errorCode = errorCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getMoreInfo() {
            return moreInfo;
        }

        public void setMoreInfo(String moreInfo) {
            this.moreInfo = moreInfo;
        }
    }
}
