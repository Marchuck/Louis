package pl.kitowcy.louis.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

/**
 * Project "GoingAir"
 * <p/>
 * Created by Lukasz Marczak
 * on 07.10.16.
 */
public class ErrorDialog extends AlertDialog {

    @Nullable
    private DialogInterface.OnClickListener onEnable, onRetry, onClose;
    private String message = "Error";

    public ErrorDialog(Context context) {
        super(context);
    }

    public ErrorDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ErrorDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public ErrorDialog withCloseListener(DialogInterface.OnClickListener onClose) {
        this.onClose = onClose;
        return this;
    }

    public ErrorDialog withMessage(String message) {
        this.message = message;
        return this;
    }

    public ErrorDialog withRetryListener(DialogInterface.OnClickListener onRetry) {
        this.onRetry = onRetry;
        return this;
    }

    public ErrorDialog withEnableOptionsListener(DialogInterface.OnClickListener onEnable) {
        this.onEnable = onEnable;
        return this;
    }

    @Override
    public void show() {
        init(message);
        super.show();
    }

    private ErrorDialog init(String errorMessage) {
        // setContentView(R.layout.error_dialog);
        setTitle("An error occurred");
        setMessage(errorMessage);
        setCancelable(false);

        return this;
    }

    public ErrorDialog setPositiveButton(String txt, DialogInterface.OnClickListener listener) {
        setButton(DialogInterface.BUTTON_POSITIVE, txt, listener);
        return this;
    }

    public ErrorDialog setNegativeButton(String txt, DialogInterface.OnClickListener listener) {
        setButton(DialogInterface.BUTTON_NEGATIVE, txt, listener);
        return this;
    }

    public ErrorDialog setNeutralButton(String txt, DialogInterface.OnClickListener listener) {
        setButton(DialogInterface.BUTTON_NEUTRAL, txt, listener);
        return this;
    }
}
