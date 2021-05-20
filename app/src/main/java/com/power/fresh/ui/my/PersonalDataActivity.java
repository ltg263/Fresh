package com.power.fresh.ui.my;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.chen.concise.RxHttp;
import com.hjq.toast.ToastUtils;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.power.common_opensurce.App;
import com.power.common_opensurce.UserInfoCc;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.CommodityDetails;
import com.power.fresh.bean.event.EventTimeOut;
import com.power.fresh.bean.reponse.UploadReponse;
import com.power.fresh.utils.FileUpload;
import com.power.fresh.utils.OptionUtil;
import com.power.fresh.utils.OptionUtil1;
import com.power.fresh.utils.PhotoCameraUtil;
import com.power.fresh.utils.PickerViewUtils;
import com.power.fresh.utils.option.OptionBean;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.image.GlideLoad;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 个人资料
 */
public class PersonalDataActivity extends UIActivity {


    @BindView(R.id.tv_user_name_value)
    EditText tvUserNameValue;
    @BindView(R.id.tv_gender_value)
    TextView tvGenderValue;
    @BindView(R.id.tv_date_value)
    TextView tvDateValue;
    @BindView(R.id.tv_mobile_value)
    TextView tvMobileValue;
    @BindView(R.id.tv_location_value)
    TextView tvLocationValue;
    @BindView(R.id.iv_head)
    ImageView ivHead;


    private String mPortraitUri;
    /**
     * 1，男；2女；0
     */
    private int mGerner;
    private int mCityId;
    private int mProvinceId;
    private int mRegionId;
    private UserInfoCc mUserInfoCc;
    private String mProvince;
    private String mCity;
    private String mRegionId1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_data;
    }




    @Override
    protected void initView() {

        mUserInfoCc = App.getUserInfoCc();
        GlideLoad.loadImage(ivHead, mUserInfoCc.getPortraitUri());

        mPortraitUri = mUserInfoCc.getPortraitUri();
        tvUserNameValue.setText(mUserInfoCc.getNickname());


        StringBuilder stringBuilder=new StringBuilder(mUserInfoCc.getProvinceStr()+"-"+ mUserInfoCc.getCityStr());

        if (!TextUtils.isEmpty(mUserInfoCc.getRegionStr())) {
            stringBuilder.append("-"+ mUserInfoCc.getRegionStr());
        }

        tvLocationValue.setText(stringBuilder.toString());
        int gender = mUserInfoCc.getGender();
        if (gender==1) {
            tvGenderValue.setText("男");
        }else{
            tvGenderValue.setText("女");
        }

        GlideLoad.loadImage(ivHead, App.getUserInfo().getUserBase().getPortraitUri(), 10);

        tvDateValue.setText(mUserInfoCc.getBirth());
        tvMobileValue.setText(mUserInfoCc.getUserNo());
        mCityId = mUserInfoCc.getCityId();
        mProvinceId = mUserInfoCc.getProvinceId();
        mRegionId = mUserInfoCc.getRegionId();

        mProvince = mUserInfoCc.getProvinceStr();
        mCity =mUserInfoCc.getCityStr();
        mRegionId1= mUserInfoCc.getRegionStr();


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    @OnClick({R.id.ll_head, R.id.ll_user_name, R.id.ll_gender, R.id.ll_date, R.id.ll_location, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_head:
                PhotoCameraUtil.getInstance(this).setMaxNum(1).setOnResultCallbackListener(new OnResultCallbackListener<LocalMedia>() {
                    @Override
                    public void onResult(List<LocalMedia> localMedia) {

                        PhotoCameraUtil.getInstance(getActivity()).disposeData(localMedia, ivHead, new PhotoCameraUtil.IListener<String>() {
                            @Override
                            public void onSuccess(String s) {

                                FileUpload.uploadPic(getActivity(), new File(s), new FileUpload.onFileListener<UploadReponse>() {
                                    @Override
                                    public void onSuccess(UploadReponse uploadReponse) {
                                        mPortraitUri = uploadReponse.getUrl();
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
            case R.id.ll_user_name:
                break;
            case R.id.ll_gender:

                OptionUtil.getInstance().showOption(getActivity(), getGenderOption(), new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        try {
                            mGerner = Integer.valueOf(getGenderOption().get(options1).getTag());
                            tvGenderValue.setText(getGenderOption().get(options1).getName());
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }
                });

                break;

            case R.id.ll_date:
                PickerViewUtils.showDatePickerView(getActivity(), tvDateValue.getText().toString(), new PickerViewUtils.OnTimeSelectAndCancel() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        tvDateValue.setText(PickerViewUtils.getTime(date,"yyyy-MM-dd"));
                    }
                });
                break;
            case R.id.ll_location:


                OptionUtil1.getInstance().setCityListerner((a, b, c)->{
                    mProvinceId = a.id;
                    mProvince = a.name;
                    mCityId =  b.id;
                    mCity = b.name;
                    mRegionId =  c.id;
                    mRegionId1 = c.name;
                    tvLocationValue.setText(a.name+"-"+b.name+'-'+c.name);
                }).showAdressView(this);




                break;
            case R.id.btn_commit:


                if (!isEmpty()) {


                    exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userUpdateInfo(tvUserNameValue.getText().toString(), mPortraitUri
                            , mGerner, mCityId, mProvinceId,mRegionId, tvDateValue.getText().toString()))
                            .subscribe(new BaseObserver<Object>() {
                                @Override
                                public void onSuccess(Object it) {
                                    mUserInfoCc.setProvinceStr(mProvince);
                                    mUserInfoCc.setCityStr(mCity);
                                    mUserInfoCc.setRegionStr(mRegionId1);
                                    mUserInfoCc.setBirth(tvDateValue.getText().toString());
                                    mUserInfoCc.setGender(mGerner);
                                    mUserInfoCc.setNickname(tvUserNameValue.getText().toString());
                                    mUserInfoCc.setPortraitUri(mPortraitUri);
                                    ToastUtils.show("修改成功");
                                    EventBus.getDefault().post(new EventTimeOut("123"));
                                    finish();


                                }
                            });

                }

                break;
        }
    }

    private boolean isEmpty() {

        if (TextUtils.isEmpty(tvUserNameValue.getText().toString())) {
            ToastUtils.show("昵称不能为空");
            return true;
        } else if (TextUtils.isEmpty(tvLocationValue.getText().toString())) {
            ToastUtils.show("地区不能为空");
            return true;
        }

        return false;

    }


    private List<OptionBean> getGenderOption() {

        List<OptionBean> optionBeans = new ArrayList<>();
        optionBeans.add(new OptionBean().setName("男").setTag("1"));
        optionBeans.add(new OptionBean().setName("女").setTag("2"));
        return optionBeans;

    }


}
