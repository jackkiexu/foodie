package com.lami.foodie.fangxing.IProcessResponse;

/**
 * Created by xjk on 2/23/18.
 */
public interface IProcessResponse<T> {
    public void onProcessCompleted(T result);
}
