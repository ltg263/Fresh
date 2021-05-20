package com.power.fresh.utils;

import androidx.fragment.app.FragmentActivity;

import com.chen.concise.RxHttp;
import com.power.common_opensurce.mode.BaseMode;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.marki.MarkiOrderBean;
import com.powerrich.common.base.BaseObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author AlienChao
 * @date 2020/06/16 10:25
 */
public class AppRequest extends BaseMode {

    public AppRequest(FragmentActivity mFragmentActivity) {
        super(mFragmentActivity);
    }

    /**
     * //1.23.5 订单分配
     */
    public void userDeliveryOrderService(int orderDeliveryId, Constant.IListener iListener) {

        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userDeliveryOrderService(orderDeliveryId))
                .subscribe(new BaseObserver<Object>() {
                    @Override
                    public void onSuccess(Object it) {
                        if (iListener!=null) {
                            iListener.onSuccess(null);
                        }
                    }
                });



    }


}
