package com.power.fresh.ui;


import android.os.Handler;
import android.os.Message;

import androidx.recyclerview.widget.RecyclerView;

import com.chen.concise.RxHttp;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.CommodityDetails;
import com.power.fresh.bean.TempBean;

import com.power.fresh.temp.House;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;
import com.powerrich.common.base.UIActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ATempActivity extends UIActivity {

    private TempBean mTempBean;
    private RecyclerView rvContent;
    private CommonRvAdapter mCommonRvAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_a_temp;
    }




    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();


    }



    @Override
    protected void initView() {

        mHandler.sendMessage(Message.obtain());
        okHttpUse();

//        rvContent = (RecyclerView) findViewById(R.id.rv_content);
//        List<TempBean2> booths = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            TempBean2 tempBean2 = new TempBean2();
//            tempBean2.setName("名称321："+i);
//            booths.add(tempBean2);
//        }
//        mCommonRvAdapter = new CommonRvAdapter<TempBean2>(this, booths, R.layout.a_item_temp) {
//            @Override
//            public void convert(CommonViewHolderRv holder, TempBean2 it, int position) {
//
////                TextView tv_action = holder.itemView.findViewById(R.id.tv_action);
////                if (position % 2 != 0) {
////                    tv_action.setEnabled(true);
////                    tv_action.setText("限时热销\n马上抢");
////                } else {
////                    tv_action.setEnabled(false);
////                    tv_action.setText("限时热销\n已抢完");
////
////                }
//            }
//        };
//
//        rvContent.setNestedScrollingEnabled(false);
//        rvContent.setAdapter(mCommonRvAdapter);


    }

    private void okHttpUse() {


        House build = new House.Build().setWidth(180).build();

        String url="";
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }




    @Override
    protected void initData() {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).commodityGet(1))
                .subscribe(new BaseObserver<CommodityDetails>() {
                    @Override
                    public void onSuccess(CommodityDetails it) {

                    }
                });
    }
}
