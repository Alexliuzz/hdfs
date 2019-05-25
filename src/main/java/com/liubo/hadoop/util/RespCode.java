package com.liubo.hadoop.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 返回码
 *
 * @author leibing.song
 */
@Getter
@AllArgsConstructor
public enum RespCode {
    SUCCESS(0, "成功"), ERROR(1, "失败");

    private Integer code;
    private String msg;
}
