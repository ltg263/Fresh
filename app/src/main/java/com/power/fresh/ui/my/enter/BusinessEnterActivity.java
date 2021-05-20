package com.power.fresh.ui.my.enter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.concise.RxHttp;
import com.hjq.toast.ToastUtils;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.noober.background.view.BLImageView;
import com.power.common_opensurce.App;
import com.power.common_opensurce.utils.LocationUtil;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.common_opensurce.utils.LocationDataBean;
import com.power.fresh.bean.bussiness.BussinessUserInfo;
import com.power.fresh.bean.reponse.BusinessReponse;
import com.power.fresh.bean.reponse.UploadReponse;
import com.power.fresh.ui.ImgPreviewActivity;
import com.power.fresh.utils.Constant;
import com.power.fresh.utils.FileUpload;
import com.power.fresh.utils.PhotoCameraUtil;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;
import com.powerrich.common.helper.SPUtils;
import com.powerrich.common.image.GlideLoad;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 商家入驻
 */
public class BusinessEnterActivity extends UIActivity {


    @BindView(R.id.iv_card_zhen)
    BLImageView ivCardZhen;
    @BindView(R.id.iv_card_fan)
    BLImageView ivCardFan;

    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.et_dpmc)
    EditText etDpmc;
    @BindView(R.id.et_fzrmc)
    EditText etFzrmc;
    @BindView(R.id.et_lxfs)
    EditText etLxfs;
    @BindView(R.id.tv_location_value)
    TextView tvLocationValue;
    @BindView(R.id.iv_dpdz)
    BLImageView ivDpdz;
    @BindView(R.id.iv_yyzz)
    BLImageView ivYyzz;
    @BindView(R.id.et_dpjj)
    EditText etDpjj;

    @BindView(R.id.rv_certificate)
    RecyclerView rvCertificate;

    /**
     * 营业执照
     */
    String businessLicense;
    /**
     * 站点图片（头像）
     */
    String siteHeaderUri;
    /**
     * 商家海报 translate
     */
    String translate;
    /**
     *
     */
    String card_zheng;
    /**
     *
     */
    String card_fan;


    @BindView(R.id.btn_commit)
    Button btnCommit;

    private double lng, lat;

    private String regionId;
    private int mAuthStatus;

    private Integer mId;

    private ArrayList<String> certificateData = new ArrayList<>();

    private CommonRvAdapter<String> mAdapter;

    private boolean mIsEdit =false;

    public static void startActivity(Context context, String title) {
        Intent intent = new Intent(context, BusinessEnterActivity.class);
        intent.putExtra("Title", title);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_business_enter;
    }

    @Override
    protected void initView() {

        String title = getIntent().getStringExtra("Title");

        setTitle(title);

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        manager.setOrientation(RecyclerView.VERTICAL);
        rvCertificate.setLayoutManager(manager);


        rvCertificate.setAdapter(getCertificateAdapter());

    }

    private RecyclerView.Adapter getCertificateAdapter() {

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


                        if (item.equals("Default")) {

                            if (certificateData.size() > 9) {
                                ToastUtils.show("最多不能超过9张");
                                return;
                            }

                            PhotoCameraUtil.getInstance(getActivity()).setMaxNum(10 - certificateData.size()).setOnResultCallbackListener(new OnResultCallbackListener<LocalMedia>() {
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


                        } else {
                            ImgPreviewActivity.startActivity(getActivity(), certificateData, position);
                        }


                    }
                });

            }
        };


        return mAdapter;
    }

    @Override
    protected void initData() {

        mAuthStatus = App.getUserInfoCc().getAuthStatus();


        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userBusinessGetInfo())
                .subscribe(new BaseObserver<BussinessUserInfo>() {
                    @Override
                    public void onSuccess(BussinessUserInfo it) {
                        mId = it.getId();
                        regionId = String.valueOf(it.getRegionId());
                        siteHeaderUri = it.getSiteHeaderUri();
                        businessLicense = it.getBusinessLicense();
                        translate = it.getTranslate();
                        GlideLoad.loadImage(ivHead, it.getSiteHeaderUri(), 4);
                        etDpmc.setText(it.getSiteName());
                        etFzrmc.setText(it.getLeaderName());
                        etLxfs.setText(it.getMobile());
                        tvLocationValue.setText(it.getAddress());

                        GlideLoad.loadImage(ivDpdz, it.getTranslate(), 4);
                        GlideLoad.loadImage(ivYyzz, it.getBusinessLicense(), 4);
                        etDpjj.setText(it.getSimpleInfo());

                        /** 7.20 后面加的字段 */

                        card_zheng = it.getCardUri();
                        card_fan = it.getCardBankUri();

                        GlideLoad.loadImage(ivCardZhen, card_zheng, 4);
                        GlideLoad.loadImage(ivCardFan, card_fan, 4);


                        certificateData.clear();
                        certificateData.add("Default");


                        if (!TextUtils.isEmpty(it.getLicense()) && it.getLicense().contains(",")) {
                            String[] split = it.getLicense().split(",");
                            for (int i = 0; i < split.length; i++) {
                                certificateData.add(split[i]);
                            }
                        }


                        mAdapter.setData(certificateData);


                    }

                    @Override
                    protected void onError(String errorStr, int code) {

                    }
                });


        if (mAuthStatus == 11 || mAuthStatus == 13 || mAuthStatus == 21 || mAuthStatus == 23 || mAuthStatus == 31 || mAuthStatus == 33) {
            btnCommit.setText("提交修改");
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    @OnClick({R.id.iv_yyzz, R.id.iv_dpdz, R.id.iv_card_zhen, R.id.iv_card_fan, R.id.ll_head, R.id.ll_location, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_yyzz:
                PhotoCameraUtil.getInstance(getActivity()).setMaxNum(1).setOnResultCallbackListener(new OnResultCallbackListener<LocalMedia>() {
                    @Override
                    public void onResult(List<LocalMedia> result) {
                        PhotoCameraUtil.getInstance(getActivity()).disposeData(result, ivYyzz, new PhotoCameraUtil.IListener<String>() {
                            @Override
                            public void onSuccess(String s) {
                                Log.e("jsc", "BusinessEnterActivity-onSuccess:" + s);
                                FileUpload.uploadPic(getActivity(), new File(s), new FileUpload.onFileListener<UploadReponse>() {
                                    @Override
                                    public void onSuccess(UploadReponse uploadReponse) {
                                        businessLicense = uploadReponse.getUrl();
                                    }
                                });
                            }
                        });


                    }

                    @Override
                    public void onCancel() {

                    }
                }).showSelectDialog();


                break;
            /** 商家海报 */
            case R.id.iv_dpdz:
                PhotoCameraUtil.getInstance(getActivity()).setMaxNum(1).setOnResultCallbackListener(new OnResultCallbackListener<LocalMedia>() {
                    @Override
                    public void onResult(List<LocalMedia> result) {
                        PhotoCameraUtil.getInstance(getActivity()).disposeData(result, ivDpdz, new PhotoCameraUtil.IListener<String>() {
                            @Override
                            public void onSuccess(String s) {
                                FileUpload.uploadPic(getActivity(), new File(s), new FileUpload.onFileListener<UploadReponse>() {
                                    @Override
                                    public void onSuccess(UploadReponse uploadReponse) {
                                        translate = uploadReponse.getUrl();
                                    }
                                });
                            }
                        });
                    }

                    @Override
                    public void onCancel() {

                    }
                }).showSelectDialog();


                break;
            case R.id.ll_head:

                PhotoCameraUtil.getInstance(getActivity()).setMaxNum(1).setOnResultCallbackListener(new OnResultCallbackListener<LocalMedia>() {
                    @Override
                    public void onResult(List<LocalMedia> result) {
                        PhotoCameraUtil.getInstance(getActivity()).disposeData(result, ivHead, new PhotoCameraUtil.IListener<String>() {
                            @Override
                            public void onSuccess(String s) {
                                FileUpload.uploadPic(getActivity(), new File(s), new FileUpload.onFileListener<UploadReponse>() {
                                    @Override
                                    public void onSuccess(UploadReponse uploadReponse) {
                                        siteHeaderUri = uploadReponse.getUrl();
                                    }
                                });
                            }
                        });
                    }

                    @Override
                    public void onCancel() {

                    }
                }).showSelectDialog();


                break;
            case R.id.ll_location:
                String locationStr = (String) SPUtils.get(Constant.CITYENTITY, "");

                LocationDataBean aMapLocationCache = App.getmGson().fromJson(locationStr, LocationDataBean.class);
                String ssqCache = aMapLocationCache.getProvince() + aMapLocationCache.getCity() + aMapLocationCache.getDistrict();
                String addressCache = aMapLocationCache.getRoad() + aMapLocationCache.getPoiname() + aMapLocationCache.getStreet() + aMapLocationCache.getStreetNum();

                String s1 = ssqCache + addressCache;
                Log.e("jsc", "BusinessEnterActivity-onViewClicked:"+s1);

                tvLocationValue.setText(s1);

                regionId = aMapLocationCache.getAdCode();
                lng = aMapLocationCache.getLongitude();
                lat = aMapLocationCache.getLatitude();





                LocationUtil.getInstance().getLocation(getActivity(), aMapLocation -> {
                    String ssq = aMapLocation.getProvince() + aMapLocation.getCity() + aMapLocation.getDistrict();
                    String address = aMapLocation.getRoad() + aMapLocation.getPoiName() + aMapLocation.getStreet() + aMapLocation.getStreetNum();
                    String s = ssq + address;
                    Log.e("jsc", "BusinessEnterActivity-onViewClicked2:"+s);


                    tvLocationValue.setText(s);

                    regionId = aMapLocation.getAdCode();
                    lng = aMapLocation.getLongitude();
                    lat = aMapLocation.getLatitude();


                });

                break;
            case R.id.btn_commit:
                if (!isEmpty()) {
                    BusinessReponse reponse = new BusinessReponse();


                    reponse.setId(mId);
                    reponse.setBusinessLicense(businessLicense);
                    reponse.setSiteHeaderUri(siteHeaderUri);
                    reponse.setTranslate(translate);
                    reponse.setMobile(etLxfs.getText().toString());

                    /** 1, 新增;2,更替 */
                    if (mIsEdit) {
                        reponse.setInfoSubmitType("1");
                    }else{
                        reponse.setInfoSubmitType("2");
                    }

                    reponse.setSimpleInfo(etDpjj.getText().toString());
                    /** 1经销商；2供应商;*/
                    if (App.getUserInfoCc().getLogPort() == Constant.经销商登陆) {
                        reponse.setType("1");
                    }else{
                        reponse.setType("2");
                    }


                    reponse.setLat(String.valueOf(lat));
                    reponse.setAddress(tvLocationValue.getText().toString());
                    reponse.setRegionId(regionId); //regionId
                    reponse.setLeaderName(etFzrmc.getText().toString());
                    reponse.setSiteName(etDpmc.getText().toString());
                    reponse.setLng(String.valueOf(lng));

                    reponse.setCardUri(card_zheng);
                    reponse.setCardBankUri(card_fan);

                    StringBuilder sb = new StringBuilder();
                    for (String s : certificateData) {//aa就是你想转化的集合
                        if (!s.contains("Default")) {
                            sb.append(s + ",");
                        }
                    }
                    sb.toString();//这样就可以把集合转化为字符串了。

                    Log.e("jsc", "BusinessEnterActivity-setLicense:"+sb.toString());
                    reponse.setLicense(sb.toString());




                    exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userBusinessSaveOrUpdate(reponse))
                            .subscribe(new BaseObserver<Object>() {
                                @Override
                                public void onSuccess(Object it) {
                                    ToastUtils.show("提交成功");
                                    finish();

                                }
                            });
                }


                break;

            //身份正面
            case R.id.iv_card_zhen:

                PhotoCameraUtil.getInstance(getActivity()).setMaxNum(1).setOnResultCallbackListener(new OnResultCallbackListener<LocalMedia>() {
                    @Override
                    public void onResult(List<LocalMedia> result) {
                        PhotoCameraUtil.getInstance(getActivity()).disposeData(result, ivCardZhen, new PhotoCameraUtil.IListener<String>() {
                            @Override
                            public void onSuccess(String s) {
                                FileUpload.uploadPic(getActivity(), new File(s), new FileUpload.onFileListener<UploadReponse>() {
                                    @Override
                                    public void onSuccess(UploadReponse uploadReponse) {
                                        card_zheng = uploadReponse.getUrl();
                                    }
                                });
                            }
                        });
                    }

                    @Override
                    public void onCancel() {

                    }
                }).showSelectDialog();


                break;


            //身份反面
            case R.id.iv_card_fan:
                PhotoCameraUtil.getInstance(getActivity()).setMaxNum(1).setOnResultCallbackListener(new OnResultCallbackListener<LocalMedia>() {
                    @Override
                    public void onResult(List<LocalMedia> result) {
                        PhotoCameraUtil.getInstance(getActivity()).disposeData(result, ivCardFan, new PhotoCameraUtil.IListener<String>() {
                            @Override
                            public void onSuccess(String s) {
                                FileUpload.uploadPic(getActivity(), new File(s), new FileUpload.onFileListener<UploadReponse>() {
                                    @Override
                                    public void onSuccess(UploadReponse uploadReponse) {
                                        card_fan = uploadReponse.getUrl();
                                    }
                                });
                            }
                        });
                    }

                    @Override
                    public void onCancel() {

                    }
                }).showSelectDialog();
                break;

        }
    }

    private boolean isEmpty() {

        if (TextUtils.isEmpty(siteHeaderUri)) {
            ToastUtils.show("请选择店铺头像");
            return true;
        } else if (TextUtils.isEmpty(etDpmc.getText().toString())) {
            ToastUtils.show("请输入店铺名称");
            return true;
        } else if (TextUtils.isEmpty(etFzrmc.getText().toString())) {
            ToastUtils.show("请输入负责人名称");
            return true;
        } else if (TextUtils.isEmpty(etLxfs.getText().toString())) {
            ToastUtils.show("请输入联系方式");
            return true;
        } else if (TextUtils.isEmpty(tvLocationValue.getText().toString())) {
            ToastUtils.show("请选择店铺地址");
            return true;
        } else if (TextUtils.isEmpty(card_zheng)) {
            ToastUtils.show("请选择身份证正面");
            return true;
        } else if (TextUtils.isEmpty(card_fan)) {
            ToastUtils.show("请选择身份证反面");
            return true;
        }else if (TextUtils.isEmpty(translate)) {
            ToastUtils.show("请选择商家海报");
            return true;
        } else if (TextUtils.isEmpty(businessLicense)) {
            ToastUtils.show("请选择营业执照");
            return true;
        }
        return false;
    }

}
