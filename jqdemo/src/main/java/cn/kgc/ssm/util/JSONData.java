package cn.kgc.ssm.util;

import lombok.Data;

@Data
public class JSONData {
    private Integer errorCode=0;
    private String message;
    private Object data;
}
