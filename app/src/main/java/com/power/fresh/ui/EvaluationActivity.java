package com.power.fresh.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.concise.RxHttp;
import com.hjq.toast.ToastUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.power.common_opensurce.dialog.EvaluationDialog;
import com.power.common_opensurce.engine.GlideEngine;
import com.power.common_opensurce.widget.layout.RadiusShadowLinearLayout;
import com.power.common_opensurce.widget.ratingstar.RatingStarView;
import com.power.fresh.R;
import com.power.fresh.adapter.PhotoAdapter;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.UploadBean;
import com.power.fresh.bean.commodity.CommodityInfo;
import com.power.fresh.bean.reponse.UploadReponse;
import com.power.fresh.utils.FileUpload;
import com.power.fresh.utils.PhotoCameraUtil;
import com.powerrich.common.base.BaseDialog;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.base.adapter.BaseRecyclerViewAdapter;
import com.powerrich.common.dialog.WaitDialog;
import com.powerrich.common.image.GlideLoad;
import com.powerrich.common.widget.TitleBar;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 评价晒单
 */
public class EvaluationActivity extends UIActivity {
    private final String TAG = EvaluationActivity.class.getName();

    @BindView(R.id.mTitleBar)
    TitleBar mTitleBar;
    @BindView(R.id.rvPhoto)
    RecyclerView rvPhoto;
    @BindView(R.id.layout_for_test)
    RadiusShadowLinearLayout radiusShadowLinearLayout;
    @BindView(R.id.rating)
    RatingStarView rating;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.iv_evaluation)
    ImageView ivEvaluation;
    @BindView(R.id.tv_evaluation_name)
    TextView tvEvaluationName;
    @BindView(R.id.tv_unit)
    TextView tvUnit;
    private List<UploadBean> uploadBeans;
    private PhotoAdapter adapter;
    private BaseDialog mDialog;


    @Override
    public boolean statusBarDarkFont() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    /**
     * 获取商铺信息
     */
    private void requestCommodityInfo() {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).appBusinessGetSimple(mBusinessId))
                .subscribe(new BaseObserver<CommodityInfo>() {
                    @Override
                    public void onSuccess(CommodityInfo it) {
                        GlideLoad.loadImage(ivEvaluation, it.getSiteHeaderUri());
                        tvEvaluationName.setText(it.getSiteName());
                    }
                });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_evaluation;
    }

    @Override
    protected int getTitleId() {
        return R.id.mTitleBar;
    }


    public static void startActivity(Context context, int orderid, int businessId) {
        Intent intent = new Intent(context, EvaluationActivity.class);
        intent.putExtra("orderid", orderid);
        intent.putExtra("businessId", businessId);
        context.startActivity(intent);
    }


    @Override
    protected void initView() {



        mOrderId = getIntent().getIntExtra("orderid", 0);
        mBusinessId = getIntent().getIntExtra("businessId", 0);

        setTitle(R.string.activity_evaluation_title);
        mTitleBar.setLeftResId(R.mipmap.icon_public_arrow_back_white);
        mTitleBar.setOnTitleBarListener(new TitleBar.OnTitleBarListener() {
            @Override
            public void onLeftClick() {
                BaseDialog baseDialog = new EvaluationDialog.Builder(EvaluationActivity.this).setMessage("\n" +
                        "您尚未发布\n" +
                        "是否对已编辑内容进行保存\n").setConfirm("保存").setCancel("不保存").setListener(new EvaluationDialog.OnListener() {
                    @Override
                    public void onConfirm(Dialog dialog) {
                        finish();
                    }

                    @Override
                    public void onCancel(Dialog dialog) {
                        finish();
                    }
                }).create();
                baseDialog.show();
            }

            @Override
            public void onRightClick() {
            }
        });
        radiusShadowLinearLayout.setShadowColor(0xff0000ff);
        adapter = new PhotoAdapter(this);
        adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView recyclerView, View itemView, int position) {

                PictureSelector.create(EvaluationActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .loadImageEngine(GlideEngine.createGlideEngine())
                        .isOriginalImageControl(true).forResult(PictureConfig.CHOOSE_REQUEST);
            }

            @Override
            public void onItemRemove(int position) {
                try {
                    certificateData.remove(position);
                    adapter.removeItem(position);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



        UploadBean item = new UploadBean();
        item.setType(-1);
        item.setUrl("添加");
        uploadBeans = new ArrayList<>();
        uploadBeans.add(item);
        adapter.setData(uploadBeans);
        rvPhoto.setLayoutManager(new GridLayoutManager(this, 4) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvPhoto.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        requestCommodityInfo();
    }

    private ArrayList<String> certificateData = new ArrayList<>();



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    mDialog = new WaitDialog.Builder(this).show();

                    postDelayed(() -> {
                        if (mDialog.isShowing()) {
                            mDialog.dismiss();
                        }
                    },5000);


                   // certificateData.clear();
                    // 图片选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回五种path
                    // 1.media.getPath(); 原图path，但在Android Q版本上返回的是content:// Uri类型
                    // 2.media.getCutPath();裁剪后path，需判断media.isCut();切勿直接使用
                    // 3.media.getCompressPath();压缩后path，需判断media.isCompressed();切勿直接使用
                    // 4.media.getOriginalPath()); media.isOriginal());为true时此字段才有值
                    // 5.media.getAndroidQToPath();Android Q版本特有返回的字段，但如果开启了压缩或裁剪还是取裁剪或压缩路
//                    径；注意：.isAndroidQTransform(false);此字段将返回空
                    // 如果同时开启裁剪和压缩，则取压缩路径为准因为是先裁剪后压缩
//                    for (LocalMedia media : selectList) {
//                        Log.i(TAG, "压缩::" + media.getCompressPath());
//                        Log.i(TAG, "原图::" + media.getPath());
//                        Log.i(TAG, "裁剪::" + media.getCutPath());
//                        Log.i(TAG, "是否开启原图::" + media.isOriginal());
//                        Log.i(TAG, "原图路径::" + media.getOriginalPath());
//                        Log.i(TAG, "Android Q 特有Path::" + media.getAndroidQToPath());
//                        String path;
//                        if (media.isCut() && !media.isCompressed()) {
//                            // 裁剪过
//                            path = media.getCutPath();
//                        } else if (media.isCompressed() || (media.isCut() && media.isCompressed())) {
//                            // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
//                            path = media.getCompressPath();
//                        } else {
//                            // 原图
//                            path = media.getPath();
//                        }
//                        UploadBean item = new UploadBean();
//                        item.setUrl(path);
//                        item.setType(0);
//                        adapter.addItem(adapter.getData().size() - 1, item);
//                        certificateData.add(path);
//                    }


                    PhotoCameraUtil.getInstance(getActivity()).disposeMoreData(selectList, null, new PhotoCameraUtil.IListener<List<String>>() {
                        @Override
                        public void onSuccess(List<String> stringList) {

                            for (int i = 0; i < stringList.size(); i++) {
                                FileUpload.uploadPic(getActivity(), new File(stringList.get(i)), new FileUpload.onFileListener<UploadReponse>() {
                                    @Override
                                    public void onSuccess(UploadReponse uploadReponse) {
                                        mDialog.dismiss();
                                        String path = uploadReponse.getUrl();
                                        UploadBean item = new UploadBean();
                                        item.setUrl(path);
                                        item.setType(0);
                                        adapter.addItem(adapter.getData().size() - 1, item);
                                        certificateData.add(path);

                                    }
                                });
                            }


                        }
                    });


                    break;
            }
        }
    }


    private int mOrderId;
    private int mBusinessId;
    private int mScore;
    private String mContent;
    private String mImgUrl;

    @OnClick(R.id.btn_submit)
    public void onViewClicked() {


        mScore = (int) rating.getRating();
        mContent = etContent.getText().toString();


        StringBuilder sb = new StringBuilder();
        for (String s : certificateData) {//aa就是你想转化的集合
            sb.append(s + ",");
        }


        if (mScore == 0) {
            ToastUtils.show("请选择评分");
            return;
        }

        if (TextUtils.isEmpty(mContent)) {
            ToastUtils.show("请输入内容");
            return;
        }

        mImgUrl = sb.toString();//这样就可以把集合转化为字符串了。
//
//        if (TextUtils.isEmpty(mImgUrl)) {
//            ToastUtils.show("请选择图片");
//            return;
//        }


        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userCommentSave(mBusinessId,mOrderId, mScore, mContent, mImgUrl))
                .subscribe(new BaseObserver<Object>() {
                    @Override
                    public void onSuccess(Object it) {
                        finish();

                    }
                });

    }
}
