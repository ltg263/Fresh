package com.powerrich.common.other;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.hjq.toast.ToastUtils;
import com.powerrich.common.helper.LogUtils;

/**
 * @author AlienChao
 * @date 2019/10/17 10:23
 */
public class TextMaxInputWatcher implements TextWatcher {
    private int editStart;
    private int editEnd;
    // the max byte
    private int maxLen = 200;

    private EditText etContent;

    public TextMaxInputWatcher(EditText etContent) {
        this.etContent = etContent;
    }

    public TextMaxInputWatcher(EditText etContent, int maxLenth) {
        this.etContent = etContent;
        this.maxLen = maxLenth;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String str = s.toString();
        LogUtils.i("jsc", "TextMaxInputWatcher-onTextChanged:"+str.length());
        if (str.length() > maxLen) {
            ToastUtils.show("超出" + maxLen + "字数限制!");
            str = str.substring(0, maxLen);
            etContent.setText(str);
            etContent.setSelection(str.length());
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
