package com.jaafar.poc.model;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 7/11/2019 4:36 PM
 */
public class BaseResponse<T> {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BaseResponse data(T data) {
        this.data = data;
        return this;
    }
}
