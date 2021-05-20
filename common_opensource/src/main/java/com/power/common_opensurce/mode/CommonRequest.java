package com.power.common_opensurce.mode;

import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.chen.concise.RxHttp;
import com.power.common_opensurce.api.FreshService;
import com.power.common_opensurce.dialog.WLDialog;
import com.power.common_opensurce.utils.MarketOrderPopWindow;
import com.powerrich.common.base.BaseActivity;
import com.powerrich.common.base.BaseDialog;
import com.powerrich.common.base.BaseObserver;

import static android.app.Activity.RESULT_OK;

/**
 * @author AlienChao
 * @date 2020/06/15 18:02
 */
public class CommonRequest extends BaseMode {

    public CommonRequest(FragmentActivity mFragmentActivity) {
        super(mFragmentActivity);
    }

    public interface IListener<T> {
        void onSuccess(T t);

        default void startDeliveryManManage(){};

    }


    public  void showPop(int orderId, IListener<Object> iListener) {

        new MarketOrderPopWindow(mFragmentActivity).showBottomView(new MarketOrderPopWindow.ChooseImgImpl() {
            BaseDialog baseDialog;
            @Override
            public void wlps() {
                baseDialog = new WLDialog.Builder(mFragmentActivity).setInterfaceClick(new WLDialog.InterfaceClick() {
                    @Override
                    public void commit(String info) {
                        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userBusinessOrderAddExpress(orderId, info))
                                .subscribe(new BaseObserver<Object>() {
                                    @Override
                                    public void onSuccess(Object it) {

                                        if (baseDialog!=null) {
                                            baseDialog.dismiss();
                                        }
                                        //reFreshData();
                                        iListener.onSuccess(null);
                                    }
                                });
                    }
                }).show();
            }

            @Override
            public void tvPsy() {

                if (baseDialog!=null) {
                    baseDialog.dismiss();
                }
                iListener.startDeliveryManManage();




            }
        });
    }


}
