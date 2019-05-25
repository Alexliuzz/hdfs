package com.liubo.hadoop.app.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 数据返回报文
 *
 * @author leibing.song
 */
@Data
@Builder
public class Response<T> {
    private int code;
    private String msg = "";
    private long count;
    private List<T> data;
}
