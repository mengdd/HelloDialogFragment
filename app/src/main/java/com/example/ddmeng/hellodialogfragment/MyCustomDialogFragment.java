package com.example.ddmeng.hellodialogfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyCustomDialogFragment extends DialogFragment {
    private static final String LOG_TAG = MyCustomDialogFragment.class.getSimpleName();

    private static final String TITLE = "title";
    private static final String MESSAGE = "message";
    private static final String POSITIVE = "positive";
    private static final String NEGATIVE = "negative";
    private static final String CANCELABLE = "cancelable";

    private String title;
    private String message;
    private String positive;
    private String negative;
    private boolean cancelable;

    @Override
    public String toString() {
        return "MyCustomDialogFragment{" +
                "title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", positive='" + positive + '\'' +
                ", negative='" + negative + '\'' +
                ", cancelable=" + cancelable +
                '}';
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG, "onCreate: " + savedInstanceState);
        super.onCreate(savedInstanceState);

        //we restore our date here, because onCreate() called before onCreateDialog()
        //and onCreateView() called after onCreateDialog()
        if (null != savedInstanceState) {
            title = savedInstanceState.getString(TITLE);
            message = savedInstanceState.getString(MESSAGE);
            positive = savedInstanceState.getString(POSITIVE);
            negative = savedInstanceState.getString(NEGATIVE);
            cancelable = savedInstanceState.getBoolean(CANCELABLE);
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(LOG_TAG, "onCreateView: " + savedInstanceState);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.i(LOG_TAG, "onCreateDialog ");
        Log.i(LOG_TAG, "Fields toString(): " + this.toString());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message);

        if (positive != null) {
            builder.setPositiveButton(positive, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ((DialogFragmentCallback) getActivity()).doPositiveClick(MyCustomDialogFragment.class.getName());
                }
            });
        }
        if (negative != null) {
            builder.setNegativeButton(negative, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ((DialogFragmentCallback) getActivity()).doNegativeClick(MyCustomDialogFragment.class.getName());
                }
            });
        }
        setCancelable(cancelable);
        return builder.create();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.i(LOG_TAG, "onSavedInstanceState");
        super.onSaveInstanceState(outState);
        // save our custom data here
        outState.putString(TITLE, title);
        outState.putString(MESSAGE, message);
        outState.putString(POSITIVE, positive);
        outState.putString(NEGATIVE, negative);
        outState.putBoolean(CANCELABLE, cancelable);

        // but I did not find a way to put listeners to outState Bundle
        // so I changed the way to do the work in callbacks in activity


    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        Log.i(LOG_TAG, "onDismiss");
        super.onDismiss(dialog);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        Log.i(LOG_TAG, "onCancel");
        super.onCancel(dialog);
    }

    public static class Builder {
        private Context context;
        private String title = null;
        private String message = null;
        private String positive = null;
        private String negative = null;
        private boolean cancelable = true;

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

        public Builder setPositiveButton(int resId) {
            return setPositiveButton(context.getString(resId));
        }

        public Builder setPositiveButton(String text) {
            this.positive = text;
            return this;
        }

        public Builder setNegativeButton(int resId) {
            return setNegativeButton(context.getString(resId));
        }

        public Builder setNegativeButton(String text) {
            this.negative = text;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public void show(FragmentManager fragmentManager) {
            MyCustomDialogFragment fragment = new MyCustomDialogFragment();
            fragment.title = title;
            fragment.message = message;
            fragment.positive = positive;
            fragment.negative = negative;
            fragment.cancelable = cancelable;
            fragment.show(fragmentManager, MyCustomDialogFragment.class.getSimpleName());
        }
    }

}
