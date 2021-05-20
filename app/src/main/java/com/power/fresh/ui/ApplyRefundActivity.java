package com.power.fresh.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hjq.toast.ToastUtils;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.power.common_opensurce.App;
import com.power.fresh.R;
import com.power.fresh.bean.order.OrderDetails;
import com.power.fresh.bean.reponse.UploadReponse;
import com.power.fresh.utils.ChooseSharePopWindow;
import com.power.fresh.utils.Constant;
import com.power.fresh.utils.FileUpload;
import com.power.fresh.utils.PhotoCameraUtil;
import com.power.fresh.utils.RadioModePopWindow;
import com.power.fresh.utils.RadioPopWindow;
import com.power.fresh.wxapi.ShareUtils;
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;
import com.powerrich.common.image.GlideLoad;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 申请退款
 */
public class ApplyRefundActivity extends UIActivity {


    @BindView(R.id.rv_certificate)
    RecyclerView rvCertificate;
    @BindView(R.id.tv_reson_value)
    TextView tvResonValue;
    @BindView(R.id.tv_num_value)
    TextView tvNumValue;
    @BindView(R.id.tv_price_value)
    TextView tvPriceValue;
    @BindView(R.id.tv_mode_value)
    TextView tvModeValue;
    @BindView(R.id.et_reson_value)
    EditText etResonValue;
    @BindView(R.id.iv_goods_icon)
    ImageView ivGoodsIcon;
    @BindView(R.id.tv_goods_name)
    TextView tvGoodsName;
    @BindView(R.id.tv_goods_desc)
    TextView tvGoodsDesc;

    @BindView(R.id.tv_goods_num_value)
    TextView tvGoodsNumValue;
    @BindView(R.id.tv_goods_price)
    TextView tvGoodsPrice;

    private CommonRvAdapter<String> mAdapter;

    private ArrayList<String> certificateData = new ArrayList<>();
    /**
     * 退款数量
     */
    private int mNum;
    private OrderDetails.OrderBean.OrderDetailsDTOBean mItem1;
    private String m退款原因;
    private int m退款方式=0;

    private int mDetailsOrderId;


    public static void startActivity(Context context, OrderDetails.OrderBean.OrderDetailsDTOBean orderDetailsDTOBean,int DetailsOrderId) {
        if (orderDetailsDTOBean == null) {
            return;
        }
        Intent intent = new Intent(context, ApplyRefundActivity.class);
        intent.putExtra("DATA", App.getmGson().toJson(orderDetailsDTOBean));
        intent.putExtra("DetailsOrderId",DetailsOrderId);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_refund;
    }

    @Override
    protected void initView() {
        String data = getIntent().getStringExtra("DATA");
        if (!TextUtils.isEmpty(data)) {
            OrderDetails.OrderBean.OrderDetailsDTOBean item = App.getmGson().fromJson(data, OrderDetails.OrderBean.OrderDetailsDTOBean.class);
            mNum = item.getNum();
            tvNumValue.setText(String.valueOf(mNum));
            tvPriceValue.setText(Constant.get().canvetDouble(item.getSalePrice()));
            mDetailsOrderId = getIntent().getIntExtra("DetailsOrderId", 0);
            mItem1 = item;
            GlideLoad.loadImage(ivGoodsIcon, item.getCommodityHeaderUri());
            tvGoodsName.setText(item.getCommodityName());
            tvGoodsDesc.setText( item.getNormsName());
            tvGoodsPrice.setText(Constant.￥ + item.getSalePrice());
            tvGoodsNumValue.setText("X" + item.getNum());



        }


        /**  提交按钮 网络请求（参数 待添加） */

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        manager.setOrientation(RecyclerView.VERTICAL);
        rvCertificate.setLayoutManager(manager);

        setAdapter();

    }

    private void setAdapter() {
        certificateData.clear();
        certificateData.add("Default");

        mAdapter = new CommonRvAdapter<String>(this, certificateData, R.layout.item_business_entity_data) {
            @Override
            public void convert(CommonViewHolderRv holder, String item, int position) {

                ImageView viewById = holder.itemView.findViewById(R.id.iv_icon);
                ImageView ivDelete = holder.itemView.findViewById(R.id.iv_right_delete);

                if (item.equals("Default")) {
                    GlideLoad.loadImage(viewById, R.mipmap.add_certificate);
                    ivDelete.setVisibility(View.GONE);
                } else {
                    GlideLoad.loadImage(viewById, item);
                    ivDelete.setVisibility(View.VISIBLE);
                }


                ivDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        certificateData.remove(position);
                        mAdapter.notifyDataSetChanged();
                    }
                });


                viewById.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        //  if (item.equals("Default")) {

                        if (certificateData.size() > 3) {
                            ToastUtils.show("最多不能超过3张");
                            return;
                        }

                        PhotoCameraUtil.getInstance(getActivity()).setMaxNum(4 - certificateData.size()).setOnResultCallbackListener(new OnResultCallbackListener<LocalMedia>() {
                            @Override
                            public void onResult(List<LocalMedia> result) {
                                PhotoCameraUtil.getInstance(getActivity()).disposeMoreData(result, null, new PhotoCameraUtil.IListener<List<String>>() {
                                    @Override
                                    public void onSuccess(List<String> stringList) {

                                        for (int i = 0; i < stringList.size(); i++) {
                                            FileUpload.uploadPic(getActivity(), new File(stringList.get(i)), new FileUpload.onFileListener<UploadReponse>() {
                                                @Override
                                                public void onSuccess(UploadReponse uploadReponse) {
                                                    String url = uploadReponse.getUrl();
                                                    certificateData.add(certificateData.size() - 1, url);
                                                    mAdapter.notifyDataSetChanged();

                                                }
                                            });
                                        }


                                    }
                                });


                            }

                            @Override
                            public void onCancel() {

                            }
                        }).showSelectDialog();


//                        } else {
//                            ImgPreviewActivity.startActivity(getActivity(), certificateData, position);
//                        }


                    }
                });


            }
        };

        rvCertificate.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.ll_reson, R.id.ll_mode, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_reson:
                new RadioPopWindow(this).showBottomView(new RadioPopWindow.OnItemClickListener() {
                    @Override
                    public void onItemClick(String content, int position) {
                        m退款原因 = content;
                        tvResonValue .setText(content);
                    }
                });
                break;
            case R.id.ll_mode:

                new RadioModePopWindow(this).showBottomView(new RadioModePopWindow.OnItemClickListener() {
                    @Override
                    public void onItemClick(String content, int position) {
                        m退款方式 = position;
                        tvModeValue .setText(content);
                    }
                });

                break;
            case R.id.btn_commit:
                if (!isEmpty()) {

                    StringBuilder sb = new StringBuilder();
                    for (String s : certificateData) {//aa就是你想转化的集合
                        if (!s.contains("Default")) {
                            sb.append(s + ";");
                        }
                    }
                    sb.toString();//这样就可以把集合转化为字符串了。

                    Constant.get().payOrder退款(this, mDetailsOrderId, mItem1.getNum(), m退款原因, sb.toString(),m退款方式,etResonValue.getText().toString());
                }


                break;
        }
    }

    private boolean isEmpty() {
        boolean flag = true;
        if (TextUtils.isEmpty(m退款原因)) {
            ToastUtils.show("请选择退款原因");
        } else if (m退款方式 ==0) {
            ToastUtils.show("请选择退款方式");
        } else {
            flag = false;
        }
        return flag;
    }

}