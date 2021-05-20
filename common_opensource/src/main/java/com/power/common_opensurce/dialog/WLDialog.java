package com.power.common_opensurce.dialog;

import android.text.TextUtils;
import android.view.Gravity;
import android.widget.EditText;

import androidx.fragment.app.FragmentActivity;

import com.hjq.toast.ToastUtils;

import com.power.common_opensurce.R;
import com.powerrich.common.base.BaseDialog;
import com.powerrich.common.base.BaseDialogFragment;
import com.powerrich.common.dialog.ACopyDialog;

/**
 * 物流 Dialog
 * @author AlienChao
 * @date 2020/04/18 20:25
 */
public class WLDialog {

    public interface InterfaceClick {
        void commit(String info);
    }


    public static final class Builder
            extends BaseDialogFragment.Builder<ACopyDialog.Builder> {



        private EditText et_wlgs;
        private EditText et_wldh;
        private InterfaceClick mInterfaceClick;


        public Builder setInterfaceClick(InterfaceClick interfaceClick) {
            mInterfaceClick = interfaceClick;
            return this;
        }

        public Builder(FragmentActivity activity) {
            super(activity);

            setContentView(R.layout.dialog_wl);
           setAnimStyle(BaseDialog.AnimStyle.IOS);
            //  setGravity(Gravity.BOTTOM);
            setGravity(Gravity.CENTER);

            et_wlgs = findViewById(R.id.et_wlgs);
            et_wldh = findViewById(R.id.et_wldh);




            findViewById(R.id.iv_dialog_close).setOnClickListener(v -> dismiss());


            findViewById(R.id.btn_commit).setOnClickListener(v -> {

                if (TextUtils.isEmpty(et_wlgs.getText().toString())) {
                    ToastUtils.show("请输入物流公司名称");
                    return;
                }
                if (TextUtils.isEmpty(et_wldh.getText().toString())) {
                    ToastUtils.show("请输入物流单号");
                    return;
                }

                if (mInterfaceClick!=null) {
                    mInterfaceClick.commit(et_wlgs.getText().toString()+":"+et_wldh.getText().toString());
                }



            });


        }

    }
}
