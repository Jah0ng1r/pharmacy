package com.example.apteka;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Some note <br/>
 * <li>Always use locale US instead of default to make DecimalFormat work well in all language</li>
 */
public class EditTextWithSuffix extends androidx.appcompat.widget.AppCompatEditText {
    TextPaint textPaint = new TextPaint();
    private String suffix = " so'm";
    private float suffixPadding;

    public EditTextWithSuffix(Context context) {
        super(context);
    }

    public EditTextWithSuffix(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAttributes(context, attrs, 0);
    }

    public EditTextWithSuffix(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttributes(context, attrs, defStyleAttr);
    }

    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);
        int suffixXPosition = (int) textPaint.measureText(getText().toString()) + getPaddingLeft();
        if (getText().toString().isEmpty() && !getHint().toString().isEmpty()) {
            suffixXPosition = (int) textPaint.measureText(getHint().toString()) + getPaddingLeft();
        }
        c.drawText(suffix, Math.max(suffixXPosition, suffixPadding), getBaseline(), textPaint);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        textPaint.setColor(getCurrentTextColor());
        textPaint.setTextSize(getTextSize());
        textPaint.setTextAlign(Paint.Align.LEFT);
    }

    private void getAttributes(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.EditTextWithSuffix, defStyleAttr, 0);
        if (a != null) {
            suffix = a.getString(R.styleable.EditTextWithSuffix_suffix);
            if (suffix == null) {
                suffix = "";
            }
            suffixPadding = a.getDimension(R.styleable.EditTextWithSuffix_suffixPadding, 0);
        }
        a.recycle();
    }
}
