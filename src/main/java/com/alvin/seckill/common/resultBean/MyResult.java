package com.alvin.seckill.common.resultBean;

import com.alvin.seckill.common.enums.ResultStatus;

import java.io.Serializable;

public class MyResult<T> extends BaseResult implements Serializable {
    private static final long serialVersionUID = 867933019328199779L;
    // 数据
    private T data;
    // count 暂时没用
    private Integer count;

    protected MyResult(ResultStatus status, String message) {
        super(status, message);
    }
    protected MyResult(ResultStatus status) {
        super(status);
    }

    public static <T> MyResult<T> build() {
        return new MyResult(ResultStatus.SUCCESS, (String)null);
    }

    public static <T> MyResult<T> build(String message) {
        return new MyResult(ResultStatus.SUCCESS, message);
    }

    public static <T> MyResult<T> error(ResultStatus status) {
        return new MyResult<T>(status);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void success(T value) {
        this.success();
        this.data = value;
        this.count = 0;
    }
}
