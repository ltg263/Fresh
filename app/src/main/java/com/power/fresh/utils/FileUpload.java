package com.power.fresh.utils;

import android.os.Environment;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import com.chen.concise.Response;
import com.chen.concise.RxExceptionUtil;
import com.chen.concise.RxHttp;
import com.power.common_opensurce.App;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.reponse.UploadReponse;
import com.powerrich.common.base.BaseDialog;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.dialog.WaitDialog;
import com.powerrich.common.helper.LogUtils;
import com.powerrich.common.other.RxSchedulers;


import java.io.File;
import java.io.IOException;
import java.util.Date;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * @author AlienChao
 * @date 2020/04/10 08:42
 */
public class FileUpload {


    public  static abstract class onFileListener<T> {
        public abstract void onSuccess(T t);

        public void onError(String error) {
        }


    }


    public static void uploadPic(FragmentActivity context, File file,  onFileListener<UploadReponse> onFileListener) {
        String s = Environment.getExternalStorageDirectory().toString();


        // File file = new File(s + "/" + "333.jpg");
        if (file.exists()) {
            Log.e("jsc", "MeFragment-aa存在:" + file.getName());
        } else {
            Log.e("jsc", "MeFragment-aa不存在:");
        }



        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
//                .addFormDataPart("type", "upload")
//                .addFormDataPart("uploadType", "app") multipart/form-data
                .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file))
                .build();
      //  BaseDialog dialog = new WaitDialog.Builder(context).show();

        RxHttp.getRetrofitService(FreshService.class)
                .upLoad(requestBody)
                .compose(RxSchedulers.observableIO2Main())
               // .compose(RxSchedulers.loadingDialog(dialog))
                .subscribe(new BaseObserver<UploadReponse>() {
                    @Override
                    public void onSuccess(UploadReponse uploadReponse) {
                        onFileListener.onSuccess(uploadReponse);
                    }
                });

//        BaseDialog dialog = new WaitDialog.Builder(context).show();
//
//        RxHttp.getInstance().getRetrofit(RxHttp.BaseUrl)
//                .create(FreshService.class)
//                .upLoad( requestBody)
//                .compose(RxSchedulers.observableIO2Main())
//                // .compose(RxSchedulers.loadingDialog(dialog))
//                .subscribe(new Observer<Response<UploadReponse>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        dialog.show();
//                    }
//
//                    @Override
//                    public void onNext(Response<UploadReponse> responseBody) {
//                        try {
//                            dialog.dismiss();
//                            onFileListener.onSuccess("上传成功");
//                         //   Log.e("jsc", "AskLeaveUndoDetailsActivity-onNext:" + responseBody.string());
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        dialog.dismiss();
//                        if (onFileListener != null) {
//                            onFileListener.onError("上传失败");
//                        }
//                        LogUtils.i("AskLeaveUndoDetailsActivity-onError:" + RxExceptionUtil.exceptionHandler(e));
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

    }
}
