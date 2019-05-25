package com.liubo.hadoop.app.request.file;

import com.liubo.hadoop.app.request.Request;
import lombok.Data;

@Data
public class FileQueryRequest extends Request {
    private String fileName;
}
