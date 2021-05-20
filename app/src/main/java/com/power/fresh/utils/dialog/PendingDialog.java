package com.power.fresh.utils.dialog;

import android.view.Gravity;
import android.view.View;


import androidx.fragment.app.FragmentActivity;

import com.power.fresh.R;

import com.powerrich.common.base.BaseDialog;
import com.powerrich.common.base.BaseDialogFragment;



/**
 *
 * @author AlienChao
 * @date 2020/04/18 20:25
 */
public class PendingDialog {



    public static final class Builder
            extends BaseDialogFragment.Builder<Builder> {


        DialogListener mDialogListener;

        public Builder setDialogListener(DialogListener dialogListener) {
            mDialogListener = dialogListener;
            if (mDialogListener!=null) {
                mDialogListener.canvetView(getContentView());
            }
            return this;
        }

        public  interface  DialogListener{
            void canvetView(View view);
        }

        public Builder(FragmentActivity activity,int layoutId) {
            super(activity);
            setContentView(layoutId);
            setAnimStyle(BaseDialog.AnimStyle.SCALE);
            //setGravity(Gravity.BOTTOM);
            setGravity(Gravity.CENTER);
        }

    }


}
