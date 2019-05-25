package com.liubo.hadoop.util.exception;

import com.liubo.hadoop.util.RespCode;
import lombok.Data;

/**
 * 通用业务异常类
 *
 * @author leibing.song
 */
@Data
public class MyException extends RuntimeException {
    private Integer code;
    private String msg;

    public MyException(String msg) {
        super(msg);
        this.code = RespCode.ERROR.getCode();
        this.msg = msg;
    }

    public MyException(RespCode respCode) {
        super(respCode.getMsg());
        this.code = respCode.getCode();
        this.msg = respCode.getMsg();
    }

    public MyException() {
        this(RespCode.ERROR);
    }
}
