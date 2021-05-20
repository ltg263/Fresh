package com.powerrich.common.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.hjq.common.R;
import com.powerrich.common.helper.FixMemLeak;
import com.powerrich.common.other.RxSchedulers;
import com.powerrich.common.dialog.WaitDialog;
import com.powerrich.common.helper.LogUtils;

import java.util.Random;

import io.reactivex.Observable;
import me.jessyan.autosize.AutoSizeCompat;

/**
 * Activity 基类
 *
 * @author AlienChao
 * @desc 规范Activity的结构 抽象方法：引入布局、初始化控件、初始化数据、标题栏
 * 简化延迟操作、简化startActivity/startActivityForResult/getActivity/、以及键盘的隐藏等共性问题
 * @date 2019/7/25
 */
public abstract class BaseActivity extends AppCompatActivity {

    private static final Handler HANDLER = new Handler(Looper.getMainLooper());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.i("Activity", "" + this.getClass().getSimpleName());
        initActivity();
    }




    @Override
    public Resources getResources() {

        Resources res = super.getResources();

        Configuration newConfig = new Configuration();

        //控制字体缩放 1.0为默认

        newConfig.fontScale = 1.0f;

        DisplayMetrics displayMetrics = res.getDisplayMetrics();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {

            //7.0以上系统手机 显示大小 对APP的影响

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                if (displayMetrics.density < DisplayMetrics.DENSITY_DEVICE_STABLE / (float) DisplayMetrics.DENSITY_DEFAULT) {

                    displayMetrics.densityDpi = (int) (DisplayMetrics.DENSITY_DEVICE_STABLE * 0.92);

                } else {

                    displayMetrics.densityDpi = DisplayMetrics.DENSITY_DEVICE_STABLE;

                }

                newConfig.densityDpi = displayMetrics.densityDpi;

            }

            createConfigurationContext(newConfig);

        }

        res.updateConfiguration(newConfig, displayMetrics);
        AutoSizeCompat.autoConvertDensity(super.getResources(), 667, false);//如果有自定义需求就用这个方法

        return res;

    }

    /**
     * 执行http请求
     */
    public <T> Observable<T> exeHttp(Observable<T> observable) {
        return observable.compose(RxSchedulers.observableIO2Main(this));
    }

    /**
     * 带有dialog的请求
     */
    public <T> Observable<T> exeHttpWithDialog(Observable<T> observable) {
        BaseDialog dialog = new WaitDialog.Builder(this).show();
        Observable<T> compose = exeHttp(observable).compose(RxSchedulers.<T>loadingDialog(this,dialog));
        return compose;
    }

//
//    public static FreshService getDefaultService() {
//        return RxHttp.getInstance().getRetrofit(BaseUrl).create(FreshService.class);
//    }

    protected void initActivity() {
        initLayout();
        initView();
        initData();
    }

    ViewDataBinding viewDataBinding;

    protected void initLayout() {


        if (getLayoutId() > 0) {
            if (useDataBinding()) {
                viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
            } else {
                setContentView(getLayoutId());
            }
        }
    }

    public ViewDataBinding getViewDataBinding() {
        return viewDataBinding;
    }

    protected boolean useDataBinding() {
        return false;
    }

    ;

    // 引入布局
    protected abstract int getLayoutId();

    // 标题栏  可以设置沉浸式 也可以提供对应的监听方法
    protected abstract int getTitleId();

    // 初始化控件
    protected abstract void initView();

    // 初始化数据
    protected abstract void initData();

    @Override
    public void finish() {
        hideSoftKeyboard();
        super.finish();
    }

    /**
     * 延迟执行
     */
    public final boolean post(Runnable r) {
        return postDelayed(r, 0);
    }

    /**
     * 延迟一段时间执行
     */
    public final boolean postDelayed(Runnable r, long delayMillis) {
        if (delayMillis < 0) {
            delayMillis = 0;
        }
        return postAtTime(r, SystemClock.uptimeMillis() + delayMillis);
    }

    /**
     * 在指定的时间执行
     */
    public final boolean postAtTime(Runnable r, long uptimeMillis) {
        return HANDLER.postAtTime(r, this, uptimeMillis);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // FixMemLeak.fixLeak(this);
        // 移除和这个 Activity 相关的消息回调
        HANDLER.removeCallbacksAndMessages(this);
    }

    /**
     * 无论什么模式，只有activity是同一个实例的情况下，intent发生了变化，就会进入onNewIntent中
     * 如果当前的 Activity（singleTop 启动模式） 被复用时会回调
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        // 设置为当前的 Intent，避免 Activity 被杀死后重启 Intent 还是最原先的那个
        setIntent(intent);
    }

    /**
     * 获取当前 Activity 对象
     */
    @SuppressWarnings("unchecked")
    public <A extends BaseActivity> A getActivity() {
        return (A) this;
    }

    /**
     * 和 setContentView 对应的方法
     */
    public View getContentView() {
        return getWindow().getDecorView();
    }

    /**
     * startActivity 方法优化
     */

    public void startActivity(Class<? extends Activity> cls) {
        startActivity(new Intent(this, cls));
    }

    public void startActivityFinish(Class<? extends Activity> cls) {
        startActivityFinish(new Intent(this, cls));
    }

    public void startActivityFinish(Intent intent) {
        startActivity(intent);
        finish();
    }

    /**
     * setResult 方法优化
     */

    public void finishResult() {
        finishResult(RESULT_OK, null);
    }

    public void finishResult(int resultCode) {
        finishResult(resultCode, null);
    }

    public void finishResult(int resultCode, Intent data) {
        setResult(resultCode, data);
        finish();
    }

    /**
     * startActivityForResult 方法优化
     */

    private ActivityCallback mActivityCallback;
    private int mActivityRequestCode;

    public void startActivityForResult(Class<? extends Activity> cls, ActivityCallback callback) {
        startActivityForResult(new Intent(this, cls), null, callback);
    }

    public void startActivityForResult(Intent intent, ActivityCallback callback) {
        startActivityForResult(intent, null, callback);
    }

    public void startActivityForResult(Intent intent, @Nullable Bundle options, ActivityCallback callback) {
        if (mActivityCallback == null) {
            mActivityCallback = callback;
            // 随机生成请求码，这个请求码在 0 - 255 之间
            mActivityRequestCode = new Random().nextInt(255);
            startActivityForResult(intent, mActivityRequestCode, options);
        } else {
            // 回调还没有结束，所以不能再次调用此方法，这个方法只适合一对一回调，其他需求请使用原生的方法实现
            throw new IllegalArgumentException("Error, The callback is not over yet");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (mActivityCallback != null && mActivityRequestCode == requestCode) {
            mActivityCallback.onActivityResult(resultCode, data);
            mActivityCallback = null;
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        if (startActivitySelfCheck(intent)) {
            LogUtils.i("Activity", "startActivityForResult:" + this.getClass().getSimpleName());

            hideSoftKeyboard();
            // 查看源码得知 startActivity 最终也会调用 startActivityForResult
            super.startActivityForResult(intent, requestCode, options);
        }
    }

    //重复跳转做的处理
    private String mStartActivityTag;
    private long mStartActivityTime;

    /**
     * 检查当前 Activity 是否重复跳转了，不需要检查则重写此方法并返回 true 即可
     *
     * @param intent 用于跳转的 Intent 对象
     * @return 检查通过返回true, 检查不通过返回false
     */
    protected boolean startActivitySelfCheck(Intent intent) {
        // 默认检查通过
        boolean result = true;
        // 标记对象
        String tag;
        if (intent.getComponent() != null) { // 显式跳转
            tag = intent.getComponent().getClassName();
        } else if (intent.getAction() != null) { // 隐式跳转
            tag = intent.getAction();
        } else { // 其他方式
            return true;
        }

        if (tag.equals(mStartActivityTag) && mStartActivityTime >= SystemClock.uptimeMillis() - 500) {
            // 检查不通过
            result = false;
        }

        mStartActivityTag = tag;
        mStartActivityTime = SystemClock.uptimeMillis();
        return result;
    }

    /**
     * 隐藏软键盘
     */
    private void hideSoftKeyboard() {
        // 隐藏软键盘，避免软键盘引发的内存泄露
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (manager != null) {
                manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    /**
     * Activity 回调接口
     */
    public interface ActivityCallback {

        /**
         * 结果回调
         *
         * @param resultCode 结果码
         */
        void onActivityResult(int resultCode, @Nullable Intent intent);
    }
}