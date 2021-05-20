package com.power.fresh.ui.my.enter;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chen.concise.RxHttp;
import com.hjq.toast.ToastUtils;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.noober.background.view.BLImageView;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.bussiness.DeliveryUserInfo;
import com.power.fresh.bean.reponse.UploadReponse;
import com.power.fresh.utils.FileUpload;
import com.power.fresh.utils.PhotoCameraUtil;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.image.GlideLoad;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 配送员入驻
 */
public class DeliveryManActivity extends UIActivity {

    @BindView(R.id.et_real_name)
    EditText etRealName;
    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.iv_id_card_positive)
    BLImageView ivIdCardPositive;
    @BindView(R.id.iv_id_card_negative)
    BLImageView ivIdCardNegative;
    @BindView(R.id.btn_commit)
    Button btnCommit;


    private Integer mId;

    private String idCardPositiveUrl;
    private String idCardNegativeUrl;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_delivery_man;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {


        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userDeliveryGetDelivery())
                .subscribe(new BaseObserver<DeliveryUserInfo>() {
                    @Override
                    public void onSuccess(DeliveryUserInfo it) {
                        if (it==null) {
                            return;
                        }

                        mId = it.getId();
                        etRealName.setText(it.getName());
                        etPhoneNumber.setText(it.getMobile());
                        idCardPositiveUrl = it.getCardUrl();
                        idCardNegativeUrl = it.getCardUrlBack();

                        GlideLoad.loadImage(ivIdCardPositive,idCardPositiveUrl);
                        GlideLoad.loadImage(ivIdCardNegative,idCardNegativeUrl);

                        btnCommit.setText("提交修改");

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    protected void onError(String errorStr, int code) {

                    }
                });


    }


    @OnClick({R.id.iv_id_card_positive, R.id.iv_id_card_negative, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_id_card_positive:


                PhotoCameraUtil.getInstance(getActivity()).setMaxNum(1).setOnResultCallbackListener(new OnResultCallbackListener<LocalMedia>() {
                    @Override
                    public void onResult(List<LocalMedia> result) {
                        PhotoCameraUtil.getInstance(getActivity()).disposeData(result, ivIdCardPositive, new PhotoCameraUtil.IListener<String>() {
                            @Override
                            public void onSuccess(String s) {
                                FileUpload.uploadPic(getActivity(), new File(s), new FileUpload.onFileListener<UploadReponse>() {
                                    @Override
                                    public void onSuccess(UploadReponse uploadReponse) {
                                        idCardPositiveUrl = uploadReponse.getUrl();
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
            case R.id.iv_id_card_negative:


                PhotoCameraUtil.getInstance(getActivity()).setMaxNum(1).setOnResultCallbackListener(new OnResultCallbackListener<LocalMedia>() {
                    @Override
                    public void onResult(List<LocalMedia> result) {
                        PhotoCameraUtil.getInstance(getActivity()).disposeData(result, ivIdCardNegative, new PhotoCameraUtil.IListener<String>() {
                            @Override
                            public void onSuccess(String s) {
                                FileUpload.uploadPic(getActivity(), new File(s), new FileUpload.onFileListener<UploadReponse>() {
                                    @Override
                                    public void onSuccess(UploadReponse uploadReponse) {
                                        idCardNegativeUrl = uploadReponse.getUrl();
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
            case R.id.btn_commit:


                if (isEmpty()) {
                    return;
                }

                /** 1新增 2更替 */
                int infoSubmitType;
                if (mId == null) {
                    infoSubmitType = 1;
                } else {
                    infoSubmitType = 2;
                }

                exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userDeliverySaveOrUpdate(mId, infoSubmitType, etRealName.getText().toString(), etPhoneNumber.getText().toString(),
                        idCardPositiveUrl, idCardNegativeUrl))
                        .subscribe(new BaseObserver<Object>() {
                            @Override
                            public void onSuccess(Object it) {
                                ToastUtils.show("提交成功");
                                finish();

                            }
                        });


                break;
        }
    }

    private boolean isEmpty() {
        if (TextUtils.isEmpty(etRealName.getText().toString())) {
            ToastUtils.show("请输入真实姓名");
            return true;
        } else if (TextUtils.isEmpty(etPhoneNumber.getText().toString())) {
            ToastUtils.show("请输入手机号码");
            return true;
        } else if (TextUtils.isEmpty(idCardPositiveUrl)) {
            ToastUtils.show("请选择身份证照片");
            return true;
        } else if (TextUtils.isEmpty(idCardNegativeUrl)) {
            ToastUtils.show("请选择身份证反面照片");
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }
}
