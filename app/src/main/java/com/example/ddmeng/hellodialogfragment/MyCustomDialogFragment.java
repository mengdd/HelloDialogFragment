package com.example.ddmeng.hellodialogfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

public class MyCustomDialogFragment extends DialogFragment {
    private String title;
    private String message;
    private String positive;
    private String negative;
    private DialogInterface.OnClickListener positiveListener;
    private DialogInterface.OnClickListener negativeListener;
    private boolean cancelable;
    private DialogInterface.OnCancelListener cancelListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message);
        if (negative != null) {
            builder.setNegativeButton(negative, negativeListener);
        }
        if (positive != null) {
            builder.setPositiveButton(positive, positiveListener);
        }
        setCancelable(cancelable);
        return builder.create();
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        if (cancelListener != null)
            cancelListener.onCancel(getDialog());
    }

    public static class Builder {
        private Context context;
        private String title = null;
        private String message = null;
        private String positive = null;
        private DialogInterface.OnClickListener positiveListener = null;
        private String negative = null;
        private DialogInterface.OnClickListener negativeListener = null;
        private boolean cancelable = true;
        private DialogInterface.OnCancelListener cancelListener = null;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitle(int resId) {
            return setTitle(context.getString(resId));
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setMessage(int resId) {
            return setMessage(context.getString(resId));
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setPositiveButton(int resId, DialogInterface.OnClickListener listener) {
            return setPositiveButton(context.getString(resId), listener);
        }

        public Builder setPositiveButton(String text, DialogInterface.OnClickListener listener) {
            this.positive = text;
            this.positiveListener = listener;
            return this;
        }

        public Builder setNegativeButton(int resId, DialogInterface.OnClickListener listener) {
            return setNegativeButton(context.getString(resId), listener);
        }

        public Builder setNegativeButton(String text, DialogInterface.OnClickListener listener) {
            this.negative = text;
            this.negativeListener = listener;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setOnCancelListener(DialogInterface.OnCancelListener listener) {
            this.cancelListener = listener;
            return this;
        }

        public void show(FragmentManager fragmentManager) {
            MyCustomDialogFragment fragment = new MyCustomDialogFragment();
            fragment.title = title;
            fragment.message = message;
            fragment.positive = positive;
            fragment.negative = negative;
            fragment.positiveListener = positiveListener;
            fragment.negativeListener = negativeListener;
            fragment.cancelable = cancelable;
            fragment.cancelListener = cancelListener;
            fragment.show(fragmentManager, MyCustomDialogFragment.class.getSimpleName());
        }
    }

}
