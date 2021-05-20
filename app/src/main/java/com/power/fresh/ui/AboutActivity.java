package com.power.fresh.ui;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import com.power.fresh.R;
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.helper.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends UIActivity {


    @BindView(R.id.tv_version_code)
    TextView tvVersionCode;

    public AboutActivity() {
        super();
        LogUtils.i("jsc", "AboutActivity-AboutActivity:");

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }


    @Override
    protected void initView() {
        tvVersionCode.setText(getVerName(this));
    }

    @Override
    protected void initData() {

    }

    public  String getVerName(Context context) {
        String verName = "";
        try {

            verName = context.getPackageManager().
                    getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "v" + verName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }
}
