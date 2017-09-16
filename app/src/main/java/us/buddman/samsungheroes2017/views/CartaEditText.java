package us.buddman.samsungheroes2017.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import us.buddman.samsungheroes2017.R;


/**
 * Created by Junseok Oh on 2017-06-21.
 */
public class CartaEditText extends android.support.v7.widget.AppCompatEditText {

    String errorHint, originHint;

    public CartaEditText(Context context) {
        super(context);
    }

    public CartaEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAttrs(attrs);
    }


    private void getAttrs(AttributeSet attrs) {
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.CartaEditText);
        setTypedArray(array);
    }

    private void setTypedArray(TypedArray array) {
        errorHint = array.getString(R.styleable.CartaEditText_errorHint);
        originHint = array.getString(R.styleable.CartaEditText_originHint);
        array.recycle();
    }

    public void setError() {
        setText("");
        setHint(errorHint);
        setBackground(ContextCompat.getDrawable(getContext(), R.drawable.et_bg_login_error));
    }

    public void setOrigin() {
        setHint(originHint);
        setBackground(ContextCompat.getDrawable(getContext(), R.drawable.et_bg_login));
    }

    public void setError(String errorMsg) {
        setText("");
        setHint(errorMsg);
        setBackground(ContextCompat.getDrawable(getContext(), R.drawable.et_bg_login_error));
    }

}