package com.powerrich.common.base;

import android.text.TextUtils;

import com.chen.concise.Response;
import com.chen.concise.RxExceptionUtil;
import com.hjq.toast.ToastUtils;
import com.powerrich.common.helper.ErrorCodeUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author AlienChao
 * @date 2019/08/05 10:27
 */
public abstract class BaseObserver<T> implements Observer<Response<T>> {


    @Override
    public void onNext(Response<T> tResponse) {
        if (tResponse.getStatus() == 0) {


                onSuccess(tResponse.getData());


        } else {
            /** 错误码在规定的范围内 */
            if (!TextUtils.isEmpty(ErrorCodeUtils.getErrorCodeMsg(tResponse.getStatus()))) {
                onError(ErrorCodeUtils.getErrorCodeMsg(tResponse.getStatus()),tResponse.getStatus());
            } else {
                onError("" + tResponse.getError(),tResponse.getStatus());
            }
        }



    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        onError(RxExceptionUtil.exceptionHandler(e),-1);
        ToastUtils.show(RxExceptionUtil.exceptionHandler(e));
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    public abstract void onSuccess(T t);


    protected void onError(String errorStr,int code) {
        if (!TextUtils.isEmpty(errorStr)) {
            ToastUtils.show(errorStr);
        }

    }
}
