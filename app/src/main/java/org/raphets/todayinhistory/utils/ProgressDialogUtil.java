package org.raphets.todayinhistory.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;

import org.raphets.todayinhistory.R;

/**
 * Created by RaphetS on 2016/10/16.
 */

public class ProgressDialogUtil {
    private static ProgressDialog mDialog;

    public static void showDialog(Context context) {
        mDialog = new ProgressDialog(context);
        mDialog.setView(LayoutInflater.from(context).inflate(R.layout.layout_loading,null));
        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
    }

    public static void dismissDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }
}
