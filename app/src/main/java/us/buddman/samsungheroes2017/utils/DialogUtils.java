package us.buddman.samsungheroes2017.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.afollestad.materialdialogs.MaterialDialog;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import us.buddman.samsungheroes2017.R;


/**
 * Created by Junseok Oh on 2017-06-10.
 */

public class DialogUtils {

    private MaterialDialog materialDialog;

    public void showDialog(Context context, String title, String content) {
        materialDialog = new MaterialDialog.Builder(context)
                .titleColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .contentColor(ContextCompat.getColor(context, R.color.textColorBright))
                .positiveColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .positiveText(context.getString(R.string.confirm))
                .title(title)
                .content(content)
                .show();
    }

    public void showListDialog(Context context, String title, MaterialDialog.ListCallback callback, @NotNull String... list) {
        materialDialog = new MaterialDialog.Builder(context)
                .titleColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .contentColor(ContextCompat.getColor(context, R.color.textColorBright))
                .title(title)
                .items(list)
                .itemsCallback(callback)
                .show();
    }
    public void showListDialog(Context context, String title, MaterialDialog.ListCallback callback, @NotNull ArrayList<String> list) {
        materialDialog = new MaterialDialog.Builder(context)
                .titleColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .contentColor(ContextCompat.getColor(context, R.color.textColorBright))
                .title(title)
                .items(list)
                .itemsCallback(callback)
                .show();
    }

    public void showProgressDialog(Context context, String title, String content) {
        materialDialog = new MaterialDialog.Builder(context)
                .titleColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .contentColor(ContextCompat.getColor(context, R.color.textColorBright))
                .positiveColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .positiveText(context.getString(R.string.confirm))
                .progress(true, 0)
                .title(title)
                .show();
    }

    public void dismissDialog() {
        if (materialDialog.isShowing())
            materialDialog.dismiss();
    }
}
