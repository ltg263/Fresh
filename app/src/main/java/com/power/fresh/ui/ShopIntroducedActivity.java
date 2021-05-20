package com.power.fresh.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.concise.Response;
import com.chen.concise.RxHttp;
import com.hjq.toast.ToastUtils;
import com.noober.background.view.BLImageView;
import com.power.common_opensurce.App;
import com.power.fresh.BR;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.CommentList;
import com.power.fresh.bean.commodity.CommodityInfo;
import com.power.fresh.utils.Constant;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;
import com.powerrich.common.image.GlideLoad;
import com.powerrich.common.widget.SimpleDividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

import static com.powerrich.common.helper.PublicUtil.dp2px;

/**
 * 店铺印象
 */
public class ShopIntroducedActivity extends UIActivity {

    @BindView(R.id.rv_comment)
    RecyclerView rvComment;
    @BindView(R.id.iv_site_head)
    BLImageView ivSiteHead;
    private int businessId;

    CommonRvAdapter<CommentList.ListBean> mCommonRvAdapter;
    private CommodityInfo mIt1;

    public static void startActivity(Context context, int businessId) {
        Intent intent = new Intent(context, ShopIntroducedActivity.class);
        intent.putExtra("businessId", businessId);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_introduced;
    }


    @Override
    protected boolean useDataBinding() {
        return true;
    }

    @Override
    protected void initView() {
        businessId = getIntent().getIntExtra("businessId", 0);
        initRecycle();
    }

    public Float convertToFloat(Double doubleValue) {
        return doubleValue == null ? null : doubleValue.floatValue();
    }

    private void initRecycle() {

        mCommonRvAdapter = new CommonRvAdapter<CommentList.ListBean>(getActivity(), null, R.layout.item_comment) {
            @Override
            public void convert(CommonViewHolderRv holder, CommentList.ListBean item, int position) {

                holder.setImageUrl(R.id.iv_shop_head, item.getAvatar());

                holder.setText(R.id.tv_type, item.getNickname());
                holder.setText(R.id.tv_content, item.getContent());
                holder.setText(R.id.tv_create_time, item.getCreateTime());
                AppCompatRatingBar rbar_star = holder.getItemView(R.id.rbar_star);
                rbar_star.setRating(convertToFloat(item.getScore()));
                List<String> imgUrls = item.getImgUrls();
                RecyclerView recyclerView = holder.getItemView(R.id.rv_comment_chird);

                GridLayoutManager manager = new GridLayoutManager(getActivity(), 4);
                manager.setOrientation(RecyclerView.VERTICAL);
                recyclerView.setLayoutManager(manager);


                CommonRvAdapter<String> commonRvAdapter = new CommonRvAdapter<String>(getActivity(), imgUrls, R.layout.item_comment_chird) {
                    @Override
                    public void convert(CommonViewHolderRv holder, String item, int position) {
                        Log.e("jsc", "ShopIntroducedActivity-convert:" + item);
                        holder.setImageUrl(R.id.iv_comment_icon, item);
                    }
                };
                recyclerView.setAdapter(commonRvAdapter);
            }
        };
        SimpleDividerItemDecoration simpleDividerItemDecoration = new SimpleDividerItemDecoration(getActivity(), 0, dp2px(getActivity(), 1));
        mCommonRvAdapter.setDividerItemDecoration(simpleDividerItemDecoration);
        rvComment.setAdapter(mCommonRvAdapter);
    }

    @Override
    protected void initData() {
        requestCommodityInfo();
        requestCommentList();
    }

    /**
     * 获取商铺信息
     */
    private void requestCommodityInfo() {
        Observable<Response<CommodityInfo>> responseObservable;
        if (App.getUserInfoCc().getLogPort() == Constant.经销商登陆) {
            responseObservable = RxHttp.getRetrofitService(FreshService.class).businessGetOne(businessId);
        } else {
            responseObservable = RxHttp.getRetrofitService(FreshService.class).appBusinessGetSimple(businessId);
        }

        exeHttpWithDialog(responseObservable)
                .subscribe(new BaseObserver<CommodityInfo>() {
                    @Override
                    public void onSuccess(CommodityInfo it) {
                        getViewDataBinding().setVariable(BR.info, it);
                        mIt1 = it;
                        GlideLoad.loadImage(ivSiteHead,it.getSiteHeaderUri());
                    }
                });
    }


    private void requestCommentList() {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).commentList(businessId, 1, 20))
                .subscribe(new BaseObserver<CommentList>() {
                    @Override
                    public void onSuccess(CommentList it) {
                        List<CommentList.ListBean> list = it.getList();
                        mCommonRvAdapter.setData(list);
                    }
                });
    }



    @OnClick(R.id.ll_qr_code)
    public void onViewClicked() {
        if (mIt1!=null) {
            ShopQRCodeActivity.startActivity(this,businessId,mIt1.getSiteName());
        }else{
            ToastUtils.show("数据加载中...");
        }
    }
}
