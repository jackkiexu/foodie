package com.lami.foodie.fangxing.IProcessResponse;

/**
 * Created by xjk on 2/23/18.
 */
public abstract class AbstractProcessResponse<T> {
    public abstract void onProcessCompleted(T result);
    private void commonProcess(){}
}

