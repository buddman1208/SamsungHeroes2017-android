package us.buddman.samsungheroes2017.utils;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Created by Junseok Oh on 2017-06-21.
 */

public class TextUtils {
    public static String colorPrimary = "#263C60";

    public static void setPartialColor(TextView textView, String... target) {
        SpannableStringBuilder builder = new SpannableStringBuilder(textView.getText().toString());
        String s = textView.getText().toString();
        for (String targetStr : target) {
            builder.setSpan(new ForegroundColorSpan(Color.parseColor(colorPrimary)), s.indexOf(targetStr), s.indexOf(targetStr) + targetStr.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        textView.setText(builder);
    }

    public static boolean isNotEmpty(EditText... editTexts) {
        for (EditText e : editTexts) {
            if (!e.getText().toString().trim().equals("")) return true;
        }
        return false;
    }


}
