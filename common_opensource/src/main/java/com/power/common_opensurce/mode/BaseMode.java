package com.power.common_opensurce.mode;

import androidx.fragment.app.FragmentActivity;

import com.powerrich.common.base.BaseDialog;
import com.powerrich.common.dialog.WaitDialog;
import com.powerrich.common.other.RxSchedulers;

import io.reactivex.Observable;

/**
 * @author AlienChao
 * @date 2019/08/19 14:58
 */
public class BaseMode {

    protected FragmentActivity mFragmentActivity;

    public FragmentActivity getActivity() {
        return mFragmentActivity;
    }

    public BaseMode(FragmentActivity mFragmentActivity) {
        this.mFragmentActivity = mFragmentActivity;
    }
//
//

    /**
     * 执行http请求
     */
    public <T> Observable<T> exeHttp(Observable<T> observable) {
        return observable.compose(RxSchedulers.observableIO2Main());
    }

    /**
     * 带有dialog的请求
     */
    public <T> Observable<T> exeHttpWithDialog(Observable<T> observable) {
        BaseDialog dialog = new WaitDialog.Builder(mFragmentActivity).show();
        return exeHttp(observable).compose(RxSchedulers.<T>loadingDialog(mFragmentActivity, dialog));
    }


}
