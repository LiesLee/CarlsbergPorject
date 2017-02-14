package com.carlsberg.app.http.async;

/**
 * Created by Administrator on 2016/6/20 0020.
 */
public class JsonResult<T> {
    public int status;
    public T data;
    public String msg;
}
