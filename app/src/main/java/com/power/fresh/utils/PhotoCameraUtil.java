package com.power.fresh.utils;

import android.widget.ImageView;

import androidx.fragment.app.FragmentActivity;

import com.luck.picture.lib.PictureSelectionModel;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.power.common_opensurce.engine.GlideEngine;
import com.powerrich.common.image.GlideLoad;
import com.powerrich.common.permission.CcPermissions;
import com.powerrich.common.permission.Consumer;
import com.powerrich.common.permission.PermissionName;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AlienChao
 * @date 2020/05/22 15:43
 */
public class PhotoCameraUtil {

    private OnResultCallbackListener<LocalMedia> mOnResultCallbackListener;

    private int maxNum = 0;
    private boolean openGallery = true;

    static FragmentActivity mActivity;

    public static final PhotoCameraUtil getInstance(FragmentActivity activity) {
        mActivity = activity;
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final PhotoCameraUtil INSTANCE = new PhotoCameraUtil();
    }

    private PhotoCameraUtil() {

    }

    public PhotoCameraUtil setOnResultCallbackListener(OnResultCallbackListener<LocalMedia> onResultCallbackListener) {
        mOnResultCallbackListener = onResultCallbackListener;
        return this;
    }

    public PhotoCameraUtil setMaxNum(int maxNum) {
        this.maxNum = maxNum;
        return this;
    }

    public void showSelectDialog() {
        new ChooseImgPopWindow(mActivity).showBottomView(new ChooseImgPopWindow.ChooseImgImpl() {
            @Override
            public void chooseImg() {
                pickPhoto();
            }

            @Override
            public void openCamera() {
                startCamera();
            }
        });
    }

    private void pickPhoto() {

        CcPermissions.with(mActivity).permission(PermissionName.WRITE_EXTERNAL_STORAGE)
                .request(new Consumer() {
                    @Override
                    public void accept(List<String> granted, boolean isAll) {


                        PictureSelectionModel compress = PictureSelector.create(mActivity)
                                .openGallery(PictureMimeType.ofImage())
                                .loadImageEngine(GlideEngine.createGlideEngine()) // 请参考Demo GlideEngine.java
                                .compress(true);
                        if (maxNum > 0) {
                            compress.maxSelectNum(maxNum);
                        }

                        if (openGallery) {
                            compress.enableCrop(true).showCropGrid(true);
                        }

//                                .circleDimmedLayer(false)
//                                .showCropFrame(false)
                        compress
                                //小于100kb的图片不压缩
                                .minimumCompressSize(100)
                                .forResult(mOnResultCallbackListener);


                    }
                });

    }

    private void startCamera() {

        PictureSelector.create(mActivity)
                .openCamera(PictureMimeType.ofImage())
                .loadImageEngine(GlideEngine.createGlideEngine()) // 请参考Demo GlideEngine.java
                .compress(true)
                //小于100kb的图片不压缩
                .minimumCompressSize(100)
                .forResult(mOnResultCallbackListener);
    }


    public String disposeData(List<LocalMedia> localMedia, ImageView ivHead){
        String path="";
        for (int i = 0; i < localMedia.size(); i++) {
            LocalMedia media = localMedia.get(i);

            if (media.isCut() && !media.isCompressed()) {
                // 裁剪过
                path = media.getCutPath();
            } else if (media.isCompressed() || (media.isCut() && media.isCompressed())) {
                // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
                path = media.getCompressPath();
            } else {
                // 原图
                path = media.getPath();
            }
            GlideLoad.loadImage(ivHead,path);
            break;
        }
        return path;
    }



    public  List<String> disposeMoreData(List<LocalMedia> localMedia){
        List<String> pathList=new ArrayList<>();
        for (int i = 0; i < localMedia.size(); i++) {
            String path ="";
            LocalMedia media = localMedia.get(i);

            if (media.isCut() && !media.isCompressed()) {
                // 裁剪过
                path = media.getCutPath();
            } else if (media.isCompressed() || (media.isCut() && media.isCompressed())) {
                // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
                path = media.getCompressPath();
            } else {
                // 原图
                path = media.getPath();
            }
            pathList.add(path);
        }
        return pathList;
    }


    public void disposeData(List<LocalMedia> localMedia, ImageView ivHead,IListener<String> stringIListener){

        String path = disposeData(localMedia, ivHead);

        if (stringIListener!=null) {
                stringIListener.onSuccess(path);
            }


    }


    public void disposeMoreData(List<LocalMedia> localMedia, ImageView ivHead,IListener<List<String>> stringIListener){

        List<String> strings = disposeMoreData(localMedia);

        if (stringIListener!=null) {
            stringIListener.onSuccess(strings);
        }

    }


    public interface IListener<T>{
        void onSuccess(T t);
    }



}
