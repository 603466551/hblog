package com.hblog.util;

import lombok.Data;

@Data
public class R {
    private String code;
    private String message;
    private Object data;
    public static R succ(String mess){
        R m = new R();
        m.setCode("200");
        m.setData(null);
        m.setMessage(mess);
        return m;
    }
    public static R succ(String mess,Object data){
        R m = new R();
        m.setCode("200");
        m.setData(data);
        m.setMessage(mess);
        return m;
    }
    public static R fail(String mess){
        R m = new R();
        m.setCode("400");
        m.setData(null);
        m.setMessage(mess);
        return m;
    }
    public static R fail(String mess,Object data){
        R m = new R();
        m.setCode("400");
        m.setData(data);
        m.setMessage(mess);
        return m;
    }
}
